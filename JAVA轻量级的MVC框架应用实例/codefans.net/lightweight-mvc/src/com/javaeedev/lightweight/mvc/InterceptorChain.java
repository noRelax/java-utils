package com.javaeedev.lightweight.mvc;

/**
 * Holds all interceptors as a chain.
 * 
 * @author Xuefeng
 */
public interface InterceptorChain {

    /**
     * Apply next interceptor around the execution of Action.
     * 
     * @param action Target Action to execute.
     * @throws Exception Any exception if error occured.
     */
    void doInterceptor(Action action) throws Exception;

}
