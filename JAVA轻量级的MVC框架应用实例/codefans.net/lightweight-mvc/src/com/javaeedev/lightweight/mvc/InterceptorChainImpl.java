package com.javaeedev.lightweight.mvc;

/**
 * Used for holds an interceptor chain.
 * 
 * @author Xuefeng
 */
class InterceptorChainImpl implements InterceptorChain {

    private final Interceptor[] interceptors;
    private int index = 0;
    private ModelAndView mv = null;

    InterceptorChainImpl(Interceptor[] interceptors) {
        this.interceptors = interceptors;
    }

    ModelAndView getModelAndView() {
        return mv;
    }

    public void doInterceptor(Action action) throws Exception {
        if(index==interceptors.length)
            mv = action.execute();
        else {
            // must update index first, otherwise will cause stack overflow:
            index++;
            interceptors[index-1].intercept(action, this);
        }

    }

}
