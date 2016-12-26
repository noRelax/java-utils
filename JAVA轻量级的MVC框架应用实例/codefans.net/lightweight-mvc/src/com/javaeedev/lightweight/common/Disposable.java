package com.javaeedev.lightweight.common;

/**
 * Object which implements this interface will be invoked of its 
 * <code>dispose</code> method to release any resources it holds 
 * when object is destroyed.
 * 
 * @author Xuefeng
 */
public interface Disposable {

    /**
     * Dispose this object and release all resources it holds.
     */
    void dispose();

}
