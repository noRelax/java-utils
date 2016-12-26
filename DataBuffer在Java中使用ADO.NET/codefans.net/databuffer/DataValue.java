/*
 * $Id: DataValue.java,v 1.12.2.3 2006/01/21 04:22:08 david_hall Exp $
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
import java.util.logging.Level;
import java.util.logging.Logger;

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

abstract public class DataValue {
    /** The logger */
    private static final Logger LOG = Logger.getLogger(DataValue.class.getName());

    //used for communicating changes to this JavaBean, especially necessary for
    //IDE tools, but also handy for any other component listening to this data value
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    /** The DataSet instance that owns this DataValue. */
    private DataSet dataSet;
    
    /** The name of this DataValue. */
    private String name;
   
    /** The type of this DataValue. */
    private Class type = Void.TYPE;
    
    /** 
     * Creates a new instance of DataValue, 
     * for a given DataSet.
     *
     * @param ds The DataSet that owns this DataValue
     * @param name The DataValue's name.
     *
     * @throw java.lang.IllegalArgumentException if any of the three arguments is missing
     * or invalid
     */
    public DataValue(DataSet ds, String name) {
        if (ds == null) 
            throw new IllegalArgumentException("DataSet is required");

        if (name == null)
            throw new IllegalArgumentException("DataValue Name is required");

        if (!DataSetUtils.isValidName(name))
            throw new IllegalArgumentException("Invalid DataValue Name");

        if (ds.hasElement(name)) 
            throw new IllegalArgumentException(
                    "An element named '" + name + "' is already part of this " +
                    "DataSet. You cannot have two elements with the same name");

        this.name = name;
        this.dataSet = ds;
        
        ds.values.put(name, this);
    }
        
    /** 
     * Returns the current name for this DataValue.
     *
     * @return the DataValue's name.
     */
    public String getName() {
        return name;
    }

    /** 
     * Returns the DataSet this DataValue belongs to.
     *
     * @return the DataSet this DataValue belongs to.
     */
    public final DataSet getDataSet() {
        return dataSet;
    }
    
    /**
     * Returns the actual value resulting from the DataValue's expression being
     * evaluated in the context of a DataSet. This value may be constant or may
     * change on each invocation--that's up to the expression.
     *
     * @return the actual value of this DataValue, once evaluated.
     */
    // TODO: should value be cached??
    
    abstract public Object getValue();

    /**
     * Returns the type of the value.
     */
    abstract public Class getType();


    public String toString() {
        return getType().getSimpleName()+" " +getName()+" = " +getValue();
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
     * @param property The name of the property to listen to changes for.
     * @param listener The PropertyChangeListener to notify of changes to this 
     * instance.
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

    /**
     * Notifies listeners of a change in a specific property
     */
    public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        pcs.firePropertyChange(propertyName, oldValue, newValue);
    }
}
