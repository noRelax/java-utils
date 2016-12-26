/*
 * $Id: Parser.java,v 1.10.2.1 2006/01/21 04:17:20 david_hall Exp $
 *
 * Copyright 2005 Sun Microsystems, Inc., 4150 Network Circle,
 * Santa Clara, California 95054, U.S.A. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */

package org.jdesktop.databuffer;

import java.io.StringBufferInputStream;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jga.fn.AdaptorVisitor;
import net.sf.jga.fn.BinaryFunctor;
import net.sf.jga.fn.Generator;
import net.sf.jga.fn.UnaryFunctor;
import net.sf.jga.fn.Visitable;
import net.sf.jga.fn.adaptor.ApplyUnary;
import net.sf.jga.fn.adaptor.Bind;
import net.sf.jga.fn.adaptor.CompoundUnary;
import net.sf.jga.fn.adaptor.Constant;
import net.sf.jga.fn.adaptor.ConstantUnary;
import net.sf.jga.fn.adaptor.GenerateUnary;
import net.sf.jga.fn.adaptor.Identity;
import net.sf.jga.fn.algorithm.Accumulate;
import net.sf.jga.fn.algorithm.Count;
import net.sf.jga.fn.algorithm.TransformUnary;
import net.sf.jga.fn.arithmetic.Average;
import net.sf.jga.fn.arithmetic.Divides;
import net.sf.jga.fn.arithmetic.Plus;
import net.sf.jga.fn.arithmetic.ValueOf;
import net.sf.jga.fn.comparison.Max;
import net.sf.jga.fn.comparison.Min;
import net.sf.jga.fn.property.ArrayBinary;
import net.sf.jga.fn.property.Construct;
import net.sf.jga.fn.property.GetProperty;
import net.sf.jga.fn.property.InvokeMethod;
import net.sf.jga.fn.property.InvokeNoArgMethod;
import net.sf.jga.parser.FunctorRef;
import net.sf.jga.parser.GeneratorRef;
import net.sf.jga.parser.JFXGParser;
import net.sf.jga.parser.ParseException;
import net.sf.jga.parser.UnaryFunctorRef;
import net.sf.jga.parser.UncheckedParseException;
import net.sf.jga.util.FilterIterator;
import org.jdesktop.databuffer.event.DataTableEventAdapter;

// NOTE: throughout this class, a generic parm <DataRow> had to be omitted, and has been
// replaced with '/**/'.  The getRowCountFn couldn't use the fully specified generic type
// List<DataRow>, because the compiler could not detect that List.class was the proper
// value for an argument of type Class<List<DataRow>>.  This may have been fixed in a
// later 1.5_0x compiler.

// NOTE: This parser is neither re-entrant nor thread safe.  Instances should not be
// shared across threads.

class Parser extends JFXGParser {
    /**
     * The Logger
     */
    private static final Logger LOG = Logger.getLogger(Parser.class.getName());

    // The default table for column references.
    private DataTable defaultTable;

    // The first table bound in an expression.  For summary expressions, this will be
    // the table whose rows are iterated.
    private DataTable boundTable;

    // In cases where the default table is not the same as the bound table, we'll be
    // receiving a row from the default table.  This functor will hold it for us...
    private Identity<DataRow> rowCache;

    // ... and this functor will return its value.
    private Generator<DataRow> getCachedRow;

    // Functor that returns the list of rows for a given table
    private UnaryFunctor<DataTable,List/**/> getRowsFn =
        new GetProperty<DataTable,List/**/>(DataTable.class, "Rows");

    // Functor that returns the number of rows in a list
    private UnaryFunctor<DataTable,Integer> getRowCountFn =
        new InvokeNoArgMethod<List/**/,Integer>(List.class, "size").compose(getRowsFn);

    // Functor that returns an iterator over the rows in a list
    private UnaryFunctor<DataTable,Iterator/**/> iterateTableFn =
        new InvokeNoArgMethod<List/**/,Iterator/**/>(List.class, "iterator").compose(getRowsFn);

    // Functor that returns the value of a DataValue
    private UnaryFunctor<DataValue,?> getValueFn =
        new GetProperty<DataValue,Object>(DataValue.class, "Value");
    
    // Functor that constructs a FilterIterator, given an InputIterator and a Predicate
    private Class[] filterCtorArgs = new Class[]{Iterator.class,UnaryFunctor.class};
    private BinaryFunctor<Iterator/**/,UnaryFunctor<DataRow,Boolean>,? extends Iterator/**/>
        makeFilterFn = new Construct<FilterIterator/**/>(filterCtorArgs,FilterIterator.class)
            .compose(new ArrayBinary<Iterator/**/,UnaryFunctor<DataRow,Boolean>>());

    // Functor that returns the first item in an iteration, or throws NoSuchElementException
    private UnaryFunctor<Iterator/**/,DataRow> firstItem =
        new InvokeNoArgMethod<Iterator/**/,DataRow>(Iterator.class, "next");
    
    public Parser(DataSet set) {
        bindThis(set);
        setUndecoratedDecimal(true);
    }
    
    // =============================
    // DataSet specific entry points
    // =============================

    /**
     * Parses a computed column expression, for the given table.
     * @throw net.sf.jga.parser.UncheckedParseException
     */
    synchronized UnaryFunctorRef parseComputedColumn(DataColumn column, String expression) {
        defaultTable = column.getTable();
        try {
            ReInit (new java.io.StringBufferInputStream(expression));
            return parseUnaryRef(DataRow.class);
        }
        catch(ParseException x) {
            LOG.log(Level.FINE, "The expression [{0}] is not valid. {1}",
                    new Object[]{expression, x});

            throw new UncheckedParseException(x.getMessage(), x);
        }
        finally {
            defaultTable = null;
            boundTable = null;
            rowCache = null;
            getCachedRow = null;
        }
    }

    /**
     * Parses an expression that computes a summary value for the collection of data
     * currently available in the bound dataset.
     * @throw net.sf.jga.parser.UncheckedParseException
     */
    synchronized GeneratorRef parseDataValue(String expression){
        try {
            ReInit (new StringBufferInputStream(expression));
            return parseGeneratorRef();
        }
        catch(ParseException x) {
            LOG.log(Level.FINE, "The expression [{0}] is not valid. {1}",
                    new Object[]{expression, x});

            throw new UncheckedParseException(x.getMessage(), x);
        }
        finally {
            defaultTable = null;
            boundTable = null;
            rowCache = null;
            getCachedRow = null;
        }
    }

    // =============================
    // Listener Handling
    // =============================

    /**
     * Examines the given functor, registering the listener with any dataset elements it
     * uses.  Returns the set of columns and values on which the functor depends.
     */
    static Set register(Visitable exp, Object element, DataTableEventAdapter listener) {
        if (exp == null) 
            return new HashSet();
        
        Registrar registrar = new Registrar(element, listener);
        exp.accept(registrar);
        return registrar.sources;
    }

 
    // Examines the current value generator, removing this as an observer to
    // any values on which this depends.
    static void unregister(Visitable exp, DataTableEventAdapter listener, Set dependencies)
    {
        if (exp != null)
            exp.accept(new Unregistrar(listener));

        dependencies.clear();
    }

    
    // Walks the expression functor, looking for dependencies on other dataset elements.
    // When one is found, the listener is registered with the element.
    static private class Registrar extends AdaptorVisitor
            implements ConstantUnary.Visitor, TransformUnary.Visitor
    {
        // A set of columns that are mentioned in the expression.  It is assumed that any
        // change to any of these columns may result in a new value for the corresponding
        // row for this column.
        private Set sources = new HashSet();

        // The object whose expression is being registered.  Nothing that is referenced
        // in the expression can depend on this object, as that would constitute a circular
        // reference
        private Object element;
        
        // The listener to be registered
        private DataTableEventAdapter listener;
        
        public Registrar(Object element, DataTableEventAdapter listener) {
            this.listener = listener;
            this.element = element;
        }
        
        public void visit(ConstantUnary constant) {
            Object value = constant.fn(null);
            if (value instanceof DataColumn) {
                DataColumn column = (DataColumn) value;
                column.getTable().addDataTableListener(listener);
                sources.add(column);
            }
        }
        
        public void visit(TransformUnary xform) {
            xform.getFunction().accept(this);
        }
        
        public void visit(Bind bind) {
            super.visit(bind);
            Object value = bind.getConstant();
            if (value instanceof DataTable) {
                DataTable table = (DataTable) value;
                table.addDataTableListener(listener);
            }
            else if (value instanceof DataValue) {
                DataValue data = (DataValue) value;
                data.addPropertyChangeListener("value", listener);
                data.addPropertyChangeListener("expression", listener);
                sources.add(data);
            }
        }
    }
        

    // Walks the expression functor, looking for dependencies on other dataset elements.
    // When one is found, the listener is unregistered with the element.
    static private class Unregistrar extends AdaptorVisitor
            implements ConstantUnary.Visitor, TransformUnary.Visitor
    {
        private DataTableEventAdapter listener;
        
        public Unregistrar(DataTableEventAdapter listener) {
            this.listener = listener;
        }
        
        public void visit(ConstantUnary constant) {
            Object value = constant.fn(null);
            if (value instanceof DataColumn) {
                DataTable table = ((DataColumn) value).getTable();
                if (table != null)
                    table.removeDataTableListener(listener);
            }
        }
        
        public void visit(TransformUnary xform) {
            xform.getFunction().accept(this);
        }
        
        public void visit(Bind bind) {
            super.visit(bind);
            Object value = bind.getConstant();
            if (value instanceof DataTable) {
                DataTable table = (DataTable) value;
                table.removeDataTableListener(listener);
            }
            else if (value instanceof DataValue) {
                DataValue data = (DataValue) value;
                data.removePropertyChangeListener("value", listener);
                data.removePropertyChangeListener("expression", listener);
            }
        }
    }

    // =============================
    // FunctorParser extensions
    // =============================
    

    protected FunctorRef reservedWord(String name) throws ParseException {
        // If the name is the same as a table we've already seen, then return a constant
        // reference to it
        
        if (defaultTable != null && name.equals(defaultTable.getName())) 
            return new GeneratorRef(new Constant<DataTable>(defaultTable), DataTable.class);

        if (boundTable != null && name.equals(boundTable.getName()))
            return new GeneratorRef(new Constant<DataTable>(boundTable), DataTable.class);
        
        // If the name is the name of a table, then we're going to return a reference to the
        // table.  If we haven't already defined a default table and/or bound the expression
        // to a table, then we'll do record the table reference appropriately.
        
        DataTable maybeTable = ((DataSet) getBoundObject()).getTable(name);
        if (maybeTable != null) {
            bindTable(maybeTable);

            // If the bound table is not the same as the default table, we'll need a
            // place to cache the row from the default table that we're given at runtime (the
            // overall expression we're parsing always gets records from the default table:
            // in this case, we need to stash the record somewhere while we iterate over
            // a different table)
            if (boundTable != defaultTable) {
                rowCache = new Identity<DataRow>();
                getCachedRow = new InvokeNoArgMethod<Identity/**/,DataRow>(Identity.class,"arg")
                                       .bind(rowCache);
            }
                
            return new GeneratorRef(new Constant<DataTable>(maybeTable), DataTable.class);
        }

        // If we already have a current table, then see if the name is a column reference
        // within the table
        
        if (defaultTable != null) {
            DataColumn col = defaultTable.getColumn(name);
            if (col != null) {
                return makeColumnRef(defaultTable, col);
            }
        }

        // Check to see the name is a DataValue
        
        DataValue maybeValue = ((DataSet) getBoundObject()).getValue(name);
        if (maybeValue != null) {
            return new GeneratorRef(getValueFn.bind(maybeValue), maybeValue.getType());
        }
        
        return null;
    }

    /**
     * Allows for (not necessarily constant) predefined fields to be added to the grammar
     */
    protected FunctorRef reservedField(FunctorRef prefix, String name) throws ParseException {
        if (isTableReference(prefix)) {
            DataTable table = getReferencedTable(prefix);
            DataColumn col = table.getColumn(name);
            if (col == null) {
                String err = "unknown column {0} in table {1}";
                String msg = MessageFormat.format(err, name, table.getName());
                throw new ParseException(msg);
            }

            return makeColumnRef(table, col);
        }                       

        return super.reservedField(prefix, name);
    }

    /**
     * Allows for function-style names to be added to the grammar.
     */
    protected FunctorRef reservedFunction(String name, FunctorRef[] args) throws ParseException {
        if (boundTable == null && defaultTable == null) {
            String msg = "Cannot parse \"{0}\" with unknown table";
            throw new ParseException(MessageFormat.format(msg, name));
        }

        if (boundTable == null)
            boundTable = defaultTable;

        // if the last argument returns a boolean, then it is interpreted as a
        // filter on the table: only those rows that meet the filter condition
        // are included in the computation
        FunctorRef lastArgRef = args[args.length - 1];
        boolean hasFilter = (lastArgRef.getReturnType() == Boolean.class);
        
        // If there is a row cache, we'll need to include it as a compound functor
        // along with the function we're building
            
        UnaryFunctor<DataRow,Boolean> filter = (hasFilter) ?
                ((UnaryFunctorRef) lastArgRef).getFunctor():
                new ConstantUnary<DataRow,Boolean>(Boolean.TRUE);
            
        // If count doesn't have a filter, then it doesn't require iterating over the
        // rows of the table: having the list of rows is good enough
        if ("count".equals(name)) {
            if (hasFilter) {
                // Iterates the table, counting those that satisfy the filter.  Count
                // returns Long, so we have to convert to Integer.
                UnaryFunctor<DataTable,Integer> countFn =
                    new ValueOf<Long,Integer>(Integer.class)
                            .compose(new Count(filter)).compose(iterateTableFn); 
                
                return makeCompound(new GeneratorRef(countFn.bind(boundTable), Integer.class));
            }
            else {
                return makeCompound(new GeneratorRef(getRowCountFn.bind(boundTable), Integer.class));
            }
        }

        UnaryFunctor<DataTable,? extends Iterator/**/> filterTableFn = (hasFilter) ?
            makeFilterFn.bind2nd(filter).compose(iterateTableFn): iterateTableFn;
        
        Generator<? extends Iterator/**/> iterateRows = filterTableFn.bind(boundTable);

        // The remaining functions evaluate an expression on every qualifying row
        // in the table.  The first argument is the expression to be evaluated,
        // so we'll set up a transform that returns the value of the expression
        // when passed the row
        Class type = args[0].getReturnType();
        if (type.isPrimitive())
            type = getBoxedType(type);
        
        TransformUnary xform = new TransformUnary(((UnaryFunctorRef) args[0]).getFunctor());
            
        // Average doesn't have an easy way to compute without iterating, but the
        // functor (like Count) takes an iteration and returns the computed value
        if ("avg".equals(name)) {
            validateArgument(Number.class, type, name);
            
            Average avg = new Average(type);
            Generator gen = avg.generate(xform.generate(iterateRows));
            return makeCompound(new GeneratorRef(gen, type));
        }
        
        // Lookup is another example of the same form: given an iterator, return a single
        // value.  in this case, we only need to look at the first item
        if ("lookup".equals(name)) {
            Generator gen = firstItem.generate(xform.generate(iterateRows));
            return makeCompound(new GeneratorRef(gen, type));
        }
        
        // All of the other summary functions require iterating over the list of
        // rows in the table, and applying a binary function to the current value
        // and the previous intermediate value
        BinaryFunctor bf = null;
        
        if ("max".equals(name)) {
            validateArgument(Comparable.class, type, name);
            bf = new Max(new net.sf.jga.util.ComparableComparator());
        }
        else if ("min".equals(name)) {
            validateArgument(Comparable.class, type, name);
            bf = new Min(new net.sf.jga.util.ComparableComparator());
        }
        else if ("sum".equals(name)) {
            validateArgument(Number.class, type, name);
            bf = new Plus(type);
        }
        
        // The simpler summary functions only require an iteration, with no further
        // processing.
        if (bf != null) {
            Generator gen = new Accumulate(bf).generate(xform.generate(iterateRows));
            return makeCompound(new GeneratorRef(gen, type));
        }

        return super.reservedFunction(name, args);
    }


    // ======================
    // implementation details
    // ======================

    
    /**
     * Binds the expression to the given table: if the expression includes a summary
     * function, then it is the table bound by this method that will be summarized.
     * Also sets the default table against which column references will be created if
     * it isn't already set.
     */
    private void bindTable(DataTable table) throws ParseException {
        if (boundTable != null) {
            String err = "Too many table references: expression is already using table {0}";
            throw new ParseException(MessageFormat.format(err, boundTable.getName()));
        }
            
        boundTable = table;

        // if we don't already have a default table for namespace purposes, use
        // the bound table from here on out.
        if (defaultTable == null) {
            defaultTable = table;
        }
    }

    /**
     * Builds a functor for a given table and column.  The functor takes a row in the
     * table and returns the value of the appropriate column.
     */
    private FunctorRef makeColumnRef(DataTable table, DataColumn column) {
        // Builds a functor that takes a Row, and returns an array consisting
        // of that row and the column we've been given
        ApplyUnary<DataRow> args =
            new ApplyUnary<DataRow>(new UnaryFunctor[]
                { new Identity<DataRow>(),new ConstantUnary<DataRow,DataColumn>(column) });
        
        // getValue(col,row) method as a functor
        InvokeMethod<DataTable,?> getValue =
            new InvokeMethod<DataTable,Object>(DataTable.class,"getValue",
                                               new Class[] {DataRow.class, DataColumn.class});
        
        // tie the two together.  The result is a functor that takes a row and returns
        // the value in the designated column
        UnaryFunctor<DataRow,?> value = getValue.bind1st(table).compose(args);

        // If there is a row cache, we'll use it to generate the argument passed to the
        // funtor we just built.
        if (rowCache != null && table.equals(defaultTable))
            return new GeneratorRef(value.generate(getCachedRow), column.getType());
        
        // Return a parser description of the functor we've built.
        return new UnaryFunctorRef(value, DataRow.class, ARG_NAME[0], column.getType());
    }
    

    /**
     * returns true if the functor reference is one that returns a specific data table.
     */
    private boolean isTableReference(FunctorRef ref) {
        return ref != null && ref.getReturnType().equals(DataTable.class) &&
               ref.getReferenceType() == FunctorRef.CONSTANT;
    }

    
    /**
     * returns the specific table to which the given functor refers.  Assumes isTableReference(ref),
     * will throw ClassCastException if not
     */
    private DataTable getReferencedTable(FunctorRef ref) {
        return (DataTable)((GeneratorRef) ref).getFunctor().gen();
    }


    /**
     * Constructs and throws a ParseException if the return type of a subexpression
     * (the found type) is not a subtype of the type required for the given function.
     */
    private void validateArgument(Class reqType, Class foundType, String fnName)
            throws ParseException
    {
        if (reqType.isAssignableFrom(foundType)) {
            return;
        }
        
        String msg = "Unable to compute {0} of type {1}";
        throw new ParseException(MessageFormat.format(msg, fnName, foundType.getSimpleName()));
    }

    
    /**
     * If there is a rowcache, then build a UnaryFunctor that takes a Row, stores it in the
     * rowcache, then generates a result.
     */
    private FunctorRef makeCompound(GeneratorRef gen) {
        if (rowCache == null)
            return gen;

        UnaryFunctor uf =
            new CompoundUnary(rowCache, new GenerateUnary((Generator) gen.getFunctor()));
        return new UnaryFunctorRef(uf, DataRow.class, "x", gen.getReturnType());
    }
}
