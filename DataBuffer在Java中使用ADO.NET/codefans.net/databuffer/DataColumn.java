/*
 * $Id: DataColumn.java,v 1.16.2.5 2006/01/21 05:22:34 david_hall Exp $
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
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jga.fn.UnaryFunctor;
import net.sf.jga.fn.adaptor.ConstantUnary;
import net.sf.jga.parser.ParseException;
import net.sf.jga.parser.UnaryFunctorRef;
import net.sf.jga.parser.UncheckedParseException;
import org.jdesktop.databuffer.event.DataTableEventAdapter;
import org.jdesktop.databuffer.event.RowChangeEvent;
import org.jdesktop.databuffer.event.TableChangeEvent;

/**
 * <p>A <code>DataColumn</code> defines information for values in a single column of a
 * {@link DataTable}.  The <code>DataColumn</code> doesn't contain an actual value for a column, but
 * rather gives the data type, and name, and tells us whether the column is required, whether it is
 * writeable, and whether it is the primary key column for the table. The data type for a value in a
 * <code>DataColumn</code> is always a Java Class.
 *
 * <p>A <code>DataColumn</code> is always associated with a specific <code>DataTable</code>, usually
 * the <code>DataTable</code> that instantiated it.
 *
 * <p>If a <code>DataColumn</code> is marked as a primary key, this is for tables with a single
 * column primary key; multiple-column keys are not supported.
 *
 * <p>Note as well that a <code>DataColumn</code> is purely passive and doesn't itself validate
 * actions against the <code>DataTable</code>. If the column is required, or if it is read-only, the
 * <code>DataColumn</code> will not enforce this, nor will it enforce uniqueness on primary key
 * columns.
 *
 * @author rbair
 */
public class DataColumn {
    /**
     * The Logger
     */
    private static final Logger LOG = Logger.getLogger(DataColumn.class.getName());
    
    //used for communicating changes to this JavaBean, especially necessary for
    //IDE tools, but also handy for any other component listening to this column
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    /**
     * The <code>DataTable</code> that created this DataColumn. This property
     * is set in the constructor
     */
    private DataTable table;
    /**
     * The name of this DataColumn. The name cannot contain any whitespace.
     * In general, it should conform to the same rules as an identifier
     * in Java.
     */
    private String name;
    /**
     * The Class of the data values for this DataColumn. This cannot be null. If
     * the type is unknown, then this should be Object.class.
     */
    private Class type = Object.class;
    /**
     * Flag indicating whether the fields within this column are readonly or
     * not.
     */
    private boolean readOnly = false;
    /**
     * Flag indicating whether the fields within this column are required.
     * If a column is required, then the field must be filled in prior to
     * a save, or an exception occurs.<br>
     * TODO constraint logic isn't specified yet. When it is, make sure to
     * include this check.
     */
    private boolean required = false;
    /**
     * The default value for the column. When a new row is added, the various
     * cells set their values to this default.
     */
    private Object defaultValue;
    /**
     * Indicates whether this DataColumn is a key column. Key Columns enforce
     * a unique constraint on the DataColumn (no two values in the column can
     * be the same, as determined by .equals()).
     */
    private boolean keyColumn;
    /**
     * Expression to be evaluated for computed columns
     */
    private String expression;
    private UnaryFunctor<DataRow,?> exprImpl;

    /**
     * Create a new DataColumn. 
     * @param table cannot be null. The <code>DataTable</code> of which this column
     * is a member
     */
    public DataColumn(DataTable table, String name) {
        this(table, name, Object.class);
    }

    /**
     * Create a new DataColumn. 
     * @param table the <code>DataTable</code> of which this column is a member.  Cannot be null. 
     * @param name the name of the column
     * @param type the class of values in the column
     * @throws java.lang.IllegalArgumentException if any argument is null or if the name is invalid
     */
    public DataColumn(DataTable table, String name, Class type) {
        if (table == null) 
            throw new IllegalArgumentException("DataTable is required");

        if (name == null)
            throw new IllegalArgumentException("DataColumn Name is required");

        if (!DataSetUtils.isValidName(name))
            throw new IllegalArgumentException("Invalid DataColumn Name");

        if (type == null)
            throw new IllegalArgumentException("DataColumn Type is required");

        this.table = table;
        this.name = name;
        this.type = type;
        table.columns.put(name,  this);
    }

    
    /**
     * Create a new calculated DataColumn 
     * @param table cannot be null. The <code>DataTable</code> of which this column
     * is a member
     * @param name the name of the column
     * @param expression the expression whose result is the value of this column
     * @throws java.lang.IllegalArgumentException if any argument is null or if the name is invalid
     */
    public DataColumn(DataTable table, String name, String expression) {
        if (table == null) 
            throw new IllegalArgumentException("DataTable is required");

        if (name == null)
            throw new IllegalArgumentException("DataColumn Name is required");

        if (!DataSetUtils.isValidName(name))
            throw new IllegalArgumentException("Invalid DataColumn Name");

        this.table = table;
        this.name = name;
        this.expression = expression;
        
        try {
            parseExpression();
        }
        catch (UncheckedParseException x) {
            throw new IllegalArgumentException(x.getMessage(), x);
        }
        
        table.columns.put(name,  this);
    }

    
    /**
     * Returns the <code>DataTable</code> that this column belongs to.
     */
    public DataTable getTable() {
        return table;
    }

    /**
     * Returns the name of the DataColumn.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the type of the values for this DataColumn as a Java Class.
     */
    public Class getType() {
        return type;
    }

    /**
     * @return true if this DataColumn is read-only.
     */
    public boolean isReadOnly() {
        return readOnly;
    }

    /**
     * Sets whether this column is read-only or not.
     * @param readOnly If true, column is read-only.
     */
    public void setReadOnly(boolean readOnly) {
        if (this.readOnly != readOnly) {
            boolean oldValue = this.readOnly;
            this.readOnly = readOnly;
            pcs.firePropertyChange("readOnly", oldValue, readOnly);
        }
    }

    /**
     * @return true if the fields in this column need to have a value
     * before they can be saved to the data store. The DataColumn is required
     * if the required flag is set by the {@link #setRequired(boolean)} method, 
     * or if the DataColumn is a keyColumn. <br>
     * 
     * TODO need to decide if that is true, or if it is always required!
     */
    public boolean isRequired() {
        return required || keyColumn;
    }

    /**
     * Specifies whether the fields in this column must have a value (cannot
     * be null).
     * @param required
     */
    public void setRequired(boolean required) {
        if (this.required != required) {
            boolean oldValue = this.required;
            this.required = required;
            pcs.firePropertyChange("required", oldValue, required);
        }
    }

    /**
     * @return the value to use as a default value when a new field for
     * this column is created, such as when a new row is created.
     */
    public Object getDefaultValue() {
        return defaultValue;
    }
    
    /**
     * Set the value to use as a default when a new field for
     * this column is created, such as when a new row is created.
     * @param defaultValue
     * @throws java.lang.IllegalArgumentException if the value is not of the correct type to
     * be stored in the column
     */
    public void setDefaultValue(Object defaultValue) {
        if (defaultValue != null && !type.isInstance(defaultValue)) {
            String msg = "Column \"{0}\" type {1}: cannot set {2} to {3}";
            Object[] args = new Object[]{ name, type.getName(), "DefaultValue", defaultValue};
            throw new IllegalArgumentException(MessageFormat.format(msg, args));
        }
            
        if (this.defaultValue != defaultValue) {
            Object oldVal = this.defaultValue;
            this.defaultValue = defaultValue;
            pcs.firePropertyChange("defaultValue", oldVal, defaultValue);
        }
    }
    
    /**
     * Returns whether the column is a key column or not
     */
    public boolean isKeyColumn() {
        return keyColumn;
    }
    
    /**
     * Sets this column to be a key column. This implicitly places a unique
     * constraint on the column. When this flag is set, no checks are made to
     * ensure correctness. However, the column will automatically be marked as
     * being required.
     *
     * @param value
     */
    public void setKeyColumn(boolean value) {
        if (value != keyColumn) {
            boolean oldVal = keyColumn;
            keyColumn = value;
            pcs.firePropertyChange("keyColumn", oldVal, value);
            // If the column is a key column, then it is also required
            // by virtue of its key-column-ness. Hence, if the column was NOT
            // required before and keyColumn WAS false but is now true, then
            // everybody needs to be notified that required is now true.
            // Conversly, if the keyColumn WAS true but is now false and
            // required WAS (still is, technically) false, then everybody
            // needs to be notified that required is now false.
            if (!oldVal && value && !required) {
                pcs.firePropertyChange("required", required, true);
            } else if (oldVal && !value && !required) {
                pcs.firePropertyChange("required", true, required);
            }
        }
    }

    /**
     * @return the expression for calculating values in this column
     */
    public String getExpression() {
        return expression;
    }

    /**
     * Returns the value in this column for the given row.  If this column is
     * a computed column, then the expression is evaluated using the contents
     * of the row, and the value returned.  If this column is not a computed
     * column, then the row's getValueByColumn callback method is invoked.
     */
    
    protected Object getValueForRow(DataRow row) {
        if (exprImpl != null)
            return exprImpl.fn(row);

        return row.getValueByColumn(this);
    }

    public String toString() {
        return "Column "+getTable().getName()+"."+getName();
    }

    // A parser used to compile expressions. 
    private Parser getParser() { return getTable().getDataSet().getParser(); }

    /**
     * @throw net.sf.jga.parser.UncheckedParseExpression
     */
    private void parseExpression() {
        LOG.log(Level.FINE, "Attempting to create the expression implementation");
        if (expression == null || expression.equals("")) {
            exprImpl = new ConstantUnary<DataRow,Object>(null);
            type = Object.class;
            return;
        } 

        //UnaryFunctorRef<DataRow,?> ref = getParser().parseComputedColumn(this, expression);
        //type = ref.getReturnType();
        //exprImpl = ref.getFunctor();
        //sources = Parser.register(exprImpl, this, listener);
    }

    /**
     * Adds a PropertyChangeListener to this class for any changes to bean 
     * properties.
     *
     * @param listener The PropertyChangeListener to notify of changes to this 
     * instance.
     */    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }
    
    /**
     * Adds a PropertyChangeListener to this class for specific property changes.
     *
     * @param listener The PropertyChangeListener to notify of changes to this 
     * instance.
     * @param property The name of the property to listen to changes for.
     */
    public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(property,  listener);
    }
    
    /**
     * Stops notifying a specific listener of any changes to bean properties.
     *
     * @param listener The listener to stop receiving notifications.
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }
    
    /**
     * Stops notifying a specific listener of changes to a specific property.
     *
     * @param propertyName The name of the property to ignore from now on.
     * @param listener The listener to stop receiving notifications.
     */
    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(propertyName,  listener);
    }

    // A set of columns and values that are mentioned in the expression.  It is assumed
    // that any change to any of these columns may result in a new value for the
    // corresponding row for this column.  The parser populates this set for us, but
    // since it is specific to each computed column, the parser returns it as a memento
    // from the registration
    
    private Set sources = new HashSet();

    // Listens to dataset elements upon which this column's expression depends.  Registration
    // adds this listener to the DataTable(s) containing column(s) referenced by the expression,
    // and to any DataValue(s) referenced.

    // DataTables fire cellChanged when a value in a particular (row,col) is changed: if this
    // formula references the value in the given column, then the cell (row,this) should fire
    // cellChanged as well, if the row and column are from the same table as this column.
    // Otherwise (as will be the case with lookup functions, for example), any number of cells
    // in the column may be affected by the change, so a columnChanged will be fired.

    // DataValues fire propertyChange when the 'value' property is updated. In response to a
    // value change, every cell in this column is to be considered updated, but firing one
    // event per row would be wasteful.

    DataTableEventAdapter listener = new DataTableEventAdapter() {
            public void propertyChange(PropertyChangeEvent evt) {
                super.propertyChange(evt);
                getTable().fireDataTableChanged(
                        TableChangeEvent.newColumnChangedEvent(getTable(),DataColumn.this));
            }

            public void cellChanged(RowChangeEvent evt) {
                DataRow row = (DataRow) evt.getSource();
                DataColumn col = evt.getColumnAffected();
                if (sources.contains(col)) {
                    if (row.getTable() == getTable()) {
                        // we don't have a good way to determine the 'old' value of the cell in
                        // this column for the given row -- if we call getValue, it'll recompute
                        // based on the new contents of the cell that has changed.
                        row.fireDataRowChanged(RowChangeEvent.newCellChangedEvent(row,
                                                  DataColumn.this, null, evt.getPriorRowStatus()));
                    }
                    // Columns containing a lookup fn can end up here
                    else {
                        getTable().fireDataTableChanged(
                            TableChangeEvent.newColumnChangedEvent(getTable(),DataColumn.this));
                    }
                }
            }
        };
}
