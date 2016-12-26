package com.javaeedev.lightweight.mvc.view;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaeedev.lightweight.mvc.ViewResolver;

/**
 * Let JSP render the model returned by Action.
 * 
 * @author Xuefeng
 */
public class JspViewResolver implements ViewResolver {

    /**
     * Init JspViewResolver.
     */
    public void init(ServletContext context) throws ServletException {
    }

    /**
     * Render view using JSP.
     */
    public void resolveView(String view, Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(model!=null) {
            Set<String> keys = model.keySet();
            for(String key : keys) {
                request.setAttribute(key, model.get(key));
            }
        }
        request.getRequestDispatcher(view).forward(request, response);
    }

}
