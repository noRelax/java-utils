/*
 * $Id: SummaryValue.java,v 1.1 2006/10/12 02:50:25 david_hall Exp $
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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jga.fn.EvaluationException;
import net.sf.jga.fn.Generator;
import net.sf.jga.fn.adaptor.Constant;
import net.sf.jga.parser.GeneratorRef;
import net.sf.jga.parser.UncheckedParseException;
import org.jdesktop.databuffer.event.DataTableEventAdapter;
import org.jdesktop.databuffer.event.RowChangeEvent;
import org.jdesktop.databuffer.event.TableChangeEvent;

/**
 * <p>A DataValue is represents an expression attached to a DataSet, which 
 * can be evaluated in the context of that DataSet to produce an Object end-value.
 * The expression is a String expression intended to be processed by a subclass
 * so that the {@link #getValue()} method returns an Object representing the 
 * expression's result. 
 *
 * <p>A <code>DataValue</code> has a name, which should be unique for a given 
 * <code>DataSet</code>.
 *
 * <p>A <code>DataValue</code> belongs to a single <code>DataSet</code>.
 *
 * @see DataSet
 *
 * @author rbair
 */

public class SummaryValue extends DataValue {
    /** The logger */
    private static final Logger LOG = Logger.getLogger(DataValue.class.getName());

    /** 
     * The expression which, when evaluated, returns the actual value for this
     * DataValue. 
     */
    private String expression;

    /**
     * The parsed form of the expression
     */
    private Generator<?> exprImpl;

    /** The type of this DataValue. */
    private Class type = Void.TYPE;
    
    /** 
     * Creates a new instance of DataValue, 
     * for a given DataSet.
     *
     * @param ds The DataSet that owns this DataValue
     * @param name The DataValue's name.
     * @param expression The expression that generates the actual value.  If null, then
     *     the value will be a constant null Object reference.
     *
     * @throw java.lang.IllegalArgumentException if any of the three arguments is missing
     * or invalid
     */
    public SummaryValue(DataSet ds, String name, String expression) {
        super(ds, name);

        this.expression = expression;

        try {
            parseExpression();
        }
        catch (UncheckedParseException x) {
            throw new IllegalArgumentException(x.getMessage(), x);
        }
    }
        
    /**
     * Returns the actual value resulting from the DataValue's expression being
     * evaluated in the context of a DataSet. This value may be constant or may
     * change on each invocation--that's up to the expression.
     *
     * @return the actual value of this DataValue, once evaluated.
     */
    // TODO: should value be cached??
    
    public Object getValue() {
        //do the eval
        try {
            return exprImpl.gen();
        } catch (EvaluationException e) {
            LOG.log(Level.WARNING, "Cannot evaluate \""+expression+"\": returning null. {1}",
                    new Object[]{expression, e.getMessage()});
            return null;
        }
    }


    /**
     * Returns the type of the value.  The type is not available until the expression
     * has been set and parsed successfully.
     */
    public Class getType() {
        return type;
    }

    public String getExpression() { return expression; }

    
    public String toString() {
        return super.toString()+" ["+getExpression()+"]";
    }

    
    Parser getParser() {
        return getDataSet().getParser();
    }

    private void parseExpression() {
        LOG.log(Level.FINE, "Attempting to create the expression implementation");
        if (expression == null || expression.equals("")) {
            exprImpl = new Constant<Object>(null);
            type = Object.class;
            return;
        } 

        // temporarily unregister this value to prevent a circular reference
        getDataSet().values.remove(getName());
        
        GeneratorRef genRef= getParser().parseDataValue(expression);
        type = genRef.getReturnType();
        exprImpl = genRef.getFunctor();
        sources = Parser.register(exprImpl, this, listener);
        
        // re-register this value.
        getDataSet().values.put(getName(), this);
    }
    
    
    // A set of columns and values that are mentioned in the expression.  It is assumed
    // that any change to any of these columns may result in a new value for the
    // corresponding row for this column.  The parser populates this set for us, but
    // since it is specific to each computed column, the parser returns it as a memento
    // from the registration
    
    private Set sources = new HashSet();

    // Listens to dataset elements upon which this value's expression depends.  Registration
    // adds this listener to the DataTable(s) containing column(s) referenced by the expression,
    // and to any DataValue(s) referenced.

    // DataTables fire cellChanged when a value in a particular (row,col) is changed: if this
    // formula references the given column, then this value should be recomputed, and its
    // 'value' property changed

    // DataValues fire propertyChange when the 'value' or 'expression' property is updated.
    // In response to a value change, this value should be recomputed, and its 'value' property
    // changed.

    DataTableEventAdapter listener = new DataTableEventAdapter() {
            public void propertyChange(PropertyChangeEvent evt) {
                super.propertyChange(evt);
                updateValue();
            }

            public void tableLoadComplete(TableChangeEvent evt) { updateValue(); }
            public void tableCleared(TableChangeEvent evt) { updateValue(); }
            public void rowAdded(TableChangeEvent evt) { updateValue(); }
            
            // NOTE: should a deleted row be returned in the rows() iteration???
//             public void rowDeleted(TableChangeEvent evt) { updateValue(); }
            public void rowDiscarded(TableChangeEvent evt) { updateValue(); }
            
            public void cellChanged(RowChangeEvent evt) {
                if (sources.contains(evt.getColumnAffected()))
                    updateValue();
            }
            
            public void columnRemoved(TableChangeEvent evt) {
                if (sources.contains(evt.getColumnAffected())) {
                    updateValue();
                }
            }
            
            public void columnChanged(TableChangeEvent evt) {  
                if (sources.contains(evt.getColumnAffected()))
                    updateValue();
            }
            
            // for now, we'll just recompute the value and send out a notification
            private void updateValue() {
                Object value = getValue();
                firePropertyChange("value", null, value);
            }
        };
}
