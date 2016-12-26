package com.javaeedev.lightweight.mvc;

import javax.servlet.ServletContext;

/**
 * Module implements this interface allowing to get a ServletContext object.
 * 
 * @author Xuefeng
 */
public interface ServletContextAware {

    /**
     * Set ServletContext instance to allow object get information of 
     * current web application.
     * 
     * @param context ServletContext object.
     */
    void setServletContext(ServletContext context);

}
