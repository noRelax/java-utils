package org.jdesktop.databuffer;

import org.jdesktop.databuffer.DataSet;

/**
 * MutableValue.java
 *
 *
 * Created: Sun Aug 27 23:44:17 2006
 *
 * @author <a href="mailto:dave@dolphin.hallsquared.org">David A. Hall</a>
 * @version 1.0
 */
public class MutableValue extends DataValue {
    /**
     */
    private Object value;

    /**
     */
    private Class<?> type;
    
    public MutableValue(DataSet ds, String name, Object value) {
        super(ds, name);
        this.value = value;
        this.type = (value != null) ? value.getClass() : Void.TYPE;
    }

    public MutableValue(DataSet ds, String name, Object value, Class type) {
        super(ds, name);
        this.value = value;
        this.type = (type != null) ? type : Void.TYPE;
    }


    public Object getValue() { return value; }

    public Class getType() { return type; }
    
    public void setValue(Object value) {
        Object old = this.value;
        this.value = type.cast(value);;
        firePropertyChange("value", old, this.value);
    }
    
} // MutableValue
