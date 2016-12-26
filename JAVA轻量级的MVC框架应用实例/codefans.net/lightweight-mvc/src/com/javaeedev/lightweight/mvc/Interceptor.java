package com.javaeedev.lightweight.mvc;

/**
 * Intercept action's execution like servlet Filter, but interceptors are 
 * configured and managed by IoC container. Another difference from Filter 
 * is that Interceptor is executed around Action's execution, but before 
 * rendering view.
 * @finishing www.codefans.net 
 * @author Xuefeng
 */
public interface Interceptor {

    /**
     * Do intercept and invoke chain.doInterceptor() to process next interceptor. 
     * NOTE that process will not continue if chain.doInterceptor() method is not 
     * invoked.
     * 
     * @param action Action instance to handle http request.
     * @param chain Interceptor chain.
     * @throws Exception If any exception is thrown, process will not continued.
     */
    void intercept(Action action, InterceptorChain chain) throws Exception;

}
