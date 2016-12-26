package com.javaeedev.lightweight.mvc;

/**
 * Prototype Action that execute for handle http request. NOTE that subclass 
 * should designed as "Use and throw".
 * @finishing www.codefans.net
 * @author Xuefeng
 */
public interface Action {

    /**
     * Return a ModelAndView object for render, or null if no need to render.
     * 
     * @return A ModelAndView object, or null.
     * @throws Exception If any exception is thrown.
     */
    ModelAndView execute() throws Exception;

}
