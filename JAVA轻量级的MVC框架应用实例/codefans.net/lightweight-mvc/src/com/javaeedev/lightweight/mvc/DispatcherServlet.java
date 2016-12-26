package com.javaeedev.lightweight.mvc;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.inject.Binding;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Module;
import com.google.inject.Stage;
import com.google.inject.name.Named;
import com.javaeedev.lightweight.common.Disposable;

/**
 * Core dispatcher servlet.
 * 
 * @author Xuefeng
 */
public class DispatcherServlet extends HttpServlet {

    private Log log = LogFactory.getLog(getClass());

    private Map<String, ActionAndMethod> actionMap;
    private Interceptor[] interceptors = null;
    private ExceptionResolver exceptionResolver = null;
    private ViewResolver viewResolver = null;

    private Injector injector = null;

    /**
     * Don't forget to call super.init(config) if subclass override this method.
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        long startTime = System.currentTimeMillis();
        String moduleClass = config.getInitParameter("module");
        if(moduleClass==null || moduleClass.trim().equals(""))
            throw new ServletException("Cannot find init parameter in web.xml: <servlet>"
                    + "<servlet-name>?</servlet-name><servlet-class>"
                    + getClass().getName()
                    + "</servlet-class><init-param><param-name>module</param-name><param-value>"
                    + "put-your-config-module-full-class-name-here</param-value></init-param></servlet>");
        ServletContext context = config.getServletContext();
        // init guice:
        injector = Guice.createInjector(Stage.PRODUCTION, getConfigModule(moduleClass.trim(), context));
        // find out all actions:
        log.info("Register all Action classes...");
        actionMap = getUrlMapping(findKeysByType(injector, Action.class));
        log.info("Register all Interceptor classes...");
        interceptors = findInterceptors(findKeysByType(injector, Interceptor.class));
        log.info("Register ViewResolver...");
        viewResolver = findViewResolver(findKeysByType(injector, ViewResolver.class));
        if(viewResolver!=null)
            viewResolver.init(context);
        // init exception resolver:
        exceptionResolver = findExceptionResolver(findKeysByType(injector, ExceptionResolver.class));
        if(exceptionResolver!=null)
            exceptionResolver.init(context);
        long time = System.currentTimeMillis() - startTime;
        log.info("DispatcherServlet init completed in " + time + " ms.");
    }

    private ExceptionResolver findExceptionResolver(List<Key<?>> exceptionResolverKeys) {
        if(exceptionResolverKeys.size()==0) {
            log.info("No ExceptionResolver found.");
            return null;
        }
        if(exceptionResolverKeys.size()>0) {
            log.warn("Found " + exceptionResolverKeys.size() + " ExceptionResolvers, only the first valid ExceptionResolver is available.");
        }
        for(Key<?> key : exceptionResolverKeys) {
            ExceptionResolver resolver = (ExceptionResolver) safeInstantiate(key);
            if(resolver!=null)
                return resolver;
        }
        log.warn("Cannot instantiate any ExceptionResolver. ExceptionResolver will be unavailable.");
        return null;
    }

    /**
     * Don't forget to call super.destroy if subclass override this method.
     */
    @Override
    public void destroy() {
        // destroy guice: Run all singleton instances' destroy() method:
        List<Key<?>> keys = findKeysByType(injector, null);
        for(Key<?> key : keys) {
            Object obj = injector.getInstance(key);
            if(obj instanceof Disposable) {
                try {
                    ((Disposable)obj).dispose();
                }
                catch(Throwable t) {
                    log.error("Call dispose() failed.", t);
                }
            }
        }
        log.info("Servlet destroyed: " + getClass().getName());
    }

    private ViewResolver findViewResolver(List<Key<?>> viewResolverKeys) {
        if(viewResolverKeys.size()==0) {
            log.info("No ViewResolver found.");
            return null;
        }
        if(viewResolverKeys.size()>1) {
            log.warn("Found " + viewResolverKeys.size() + " ViewResolvers, only the first valid ViewResolver is available.");
        }
        for(Key<?> key : viewResolverKeys) {
            ViewResolver resolver = (ViewResolver) safeInstantiate(key);
            if(resolver!=null) {
                log.info("Found ViewResolver: " + resolver);
                return resolver;
            }
        }
        log.warn("Cannot instantiate any ViewResolver. ViewResolver will be unavailable.");
        return null;
    }

    private <T> T safeInstantiate(Key<T> key) {
        try {
            return injector.getProvider(key).get();
        }
        catch(Exception e) {
            log.warn("Cannot instantiate by key: " + key, e);
            return null;
        }
    }

    private List<Key<?>> findKeysByType(Injector inj, Class<?> type) {
        Map<Key<?>, Binding<?>> map = inj.getBindings();
        List<Key<?>> keyList = new ArrayList<Key<?>>();
        for(Key<?> key : map.keySet()) {
            Type t = key.getTypeLiteral().getType();
            if(t instanceof Class<?>) {
                Class<?> clazz = (Class<?>) t;
                if(type==null || type.isAssignableFrom(clazz)) {
                    keyList.add(key);
                }
            }
        }
        return keyList;
    }

    @SuppressWarnings("unchecked")
    private Interceptor[] findInterceptors(List<Key<?>> interceptorKeys) {
        List<InterceptorOrder> list = new ArrayList<InterceptorOrder>();
        for(Key<?> key : interceptorKeys) {
            Object obj = safeInstantiate(key);
            if(obj==null)
                continue;
            Class<Interceptor> intClass = (Class<Interceptor>) obj.getClass();
            Order orderAnn = intClass.getAnnotation(Order.class);
            int order = 0;
            if(orderAnn!=null) {
                order = orderAnn.value();
                log.info("Found interceptor [" + intClass.getName() + "] with specified order: " + order);
            }
            else {
                log.warn("Found interceptor [" + intClass.getName() + "] but no order specified, set order to default: 0");
            }
            list.add(new InterceptorOrder((Interceptor)injector.getInstance(key), order));
        }
        if(list.size()==0)
            return new Interceptor[0];
        InterceptorOrder[] orderArray = list.toArray(new InterceptorOrder[0]);
        Arrays.sort(orderArray);
        Interceptor[] intArray = new Interceptor[orderArray.length];
        for(int i=0; i<intArray.length; i++) {
            intArray[i] = orderArray[i].interceptor;
        }
        return intArray;
    }

    @SuppressWarnings("unchecked")
    private Map<String, ActionAndMethod> getUrlMapping(List<Key<?>> actionKeys) {
        Map<String, ActionAndMethod> urlMapping = new HashMap<String, ActionAndMethod>();
        for(Key<?> key : actionKeys) {
            Object obj = safeInstantiate(key);
            if(obj==null)
                continue;
            Class<Action> actionClass = (Class<Action>) obj.getClass();
            Annotation ann = key.getAnnotation();
            if(ann instanceof Named) {
                Named named = (Named) ann;
                String url = named.value();
                if(url!=null)
                    url = url.trim();
                if(!"".equals(url)) {
                    log.info("Bind action [" + actionClass.getName() + "] to URL: " + url);
                    // link url with this action:
                    urlMapping.put(url, new ActionAndMethod(key, actionClass));
                }
                else {
                    log.warn("Cannot bind action [" + actionClass.getName() + "] to *EMPTY* URL.");
                }
            }
            else {
                log.warn("Cannot bind action [" + actionClass.getName() + "] because no @Named annotation found in config module. Using: binder.bind(MyAction.class).annotatedWith(Names.named(\"/url\"));");
            }
        }
        return urlMapping;
    }

    /**
     * Get a config module for IoC config. NOTE that all Action classes must 
     * bind with annotation of @Named("url") with a valid url.
     * 
     * @return A config module.
     */
    protected Module getConfigModule(String className, ServletContext context) throws ServletException {
        Object obj = null;
        try {
            Class<?> clazz = Class.forName(className);
            obj = clazz.newInstance();
        }
        catch(InstantiationException e) {
            throw new ServletException("Cannot instantiat for class \"" + className + "\"", e);
        }
        catch(IllegalAccessException e) {
            throw new ServletException("Cannot instantiat for class \"" + className + "\"", e);
        }
        catch(ClassNotFoundException e) {
            throw new ServletException("Cannot find class \"" + className + "\"", e);
        }
        try {
            Module module = (Module)obj;
            if(module instanceof ServletContextAware) {
                ((ServletContextAware)module).setServletContext(context);
            }
            return module;
        }
        catch(ClassCastException e) {
            throw new ServletException("Cannot cast class \"" + className + "\" to \"" + Module.class.getName() + "\"", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // find mvc:
        String contextPath = request.getContextPath();
        String url = request.getRequestURI().substring(contextPath.length());
        if(log.isDebugEnabled())
            log.debug("Handle for URL: " + url);
        ActionAndMethod am = actionMap.get(url);
        if(am==null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        ModelAndView mv = null;
        try {
            // init ActionContext:
            HttpSession session = request.getSession();
            ServletContext context = session.getServletContext();
            ActionContext.setActionContext(request, response, session, context);
            // try instance action:
            Action action = (Action) injector.getInstance(am.getKey());
            if(log.isDebugEnabled()) {
                log.debug("Found action for URL \"" + url + "\": " + action);
                log.debug("Bind parameters to action: " + action);
            }
            // try to invoke all setters:
            List<String> props = am.getProperties();
            for(String prop : props) {
                String value = request.getParameter(prop);
                if(value!=null) {
                    // only invoke with non-null value:
                    am.invokeSetter(action, prop, value);
                }
            }
            // now apply all interceptors and invoke execute:
            if(log.isDebugEnabled())
                log.debug("Apply Interceptors...");
            InterceptorChainImpl chains = new InterceptorChainImpl(interceptors);
            try {
                chains.doInterceptor(action);
                mv = chains.getModelAndView();
            }
            catch(Exception e) {
                if(log.isDebugEnabled())
                    log.debug("Handle exception: " + e);
                if(exceptionResolver!=null) {
                    try {
                        mv = exceptionResolver.handleException(action, e);
                    }
                    catch(Exception ex) {
                        throw new ServletException("Exception when handle request.", e);
                    }
                }
                else
                    throw new ServletException("Exception when handle request.", e);
            }
            catch(Throwable t) {
                throw new ServletException(t);
            }
        }
        finally {
            ActionContext.remove();
        }
        // render view:
        if(mv!=null)
            render(mv, request, response);
    }

    // render view:
    private void render(ModelAndView mv, HttpServletRequest reqest, HttpServletResponse response) throws ServletException, IOException {
        String view = mv.getView();
        if(view.startsWith("redirect:")) {
            String redirect = view.substring("redirect:".length());
            if(log.isDebugEnabled())
                log.debug("Send a redirect to: " + redirect);
            response.sendRedirect(redirect);
            return;
        }
        Map<String, Object> model = mv.getModel();
        if(log.isDebugEnabled())
            log.debug("Render view: " + view);
        if(viewResolver!=null)
            viewResolver.resolveView(view, model, reqest, response);
    }

    /**
     * Call to doGet(request, response).
     */
    @Override
    protected void doPost(HttpServletRequest reqest, HttpServletResponse response) throws ServletException, IOException {
        doGet(reqest, response);
    }

    //--------------------------------------------------------------------------
    // All method are rejected except GET and POST
    //--------------------------------------------------------------------------

    /**
     * Send an HttpServletResponse.SC_BAD_REQUEST error.
     */
    @Override
    protected void doDelete(HttpServletRequest reqest, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    /**
     * Send an HttpServletResponse.SC_BAD_REQUEST error.
     */
    @Override
    protected void doHead(HttpServletRequest reqest, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    /**
     * Send an HttpServletResponse.SC_BAD_REQUEST error.
     */
    @Override
    protected void doOptions(HttpServletRequest reqest, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    /**
     * Send an HttpServletResponse.SC_BAD_REQUEST error.
     */
    @Override
    protected void doPut(HttpServletRequest reqest, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    /**
     * Send an HttpServletResponse.SC_BAD_REQUEST error.
     */
    @Override
    protected void doTrace(HttpServletRequest reqest, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

}
