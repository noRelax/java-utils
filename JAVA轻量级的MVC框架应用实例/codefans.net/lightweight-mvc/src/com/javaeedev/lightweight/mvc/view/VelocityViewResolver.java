package com.javaeedev.lightweight.mvc.view;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.Context;
import org.apache.velocity.io.VelocityWriter;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.RuntimeInstance;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.javaeedev.lightweight.mvc.ViewResolver;

/**
 * Using velocity to render html pages.
 * @finishing www.codefans.net 
 * @author Xuefeng
 */
public class VelocityViewResolver implements ViewResolver {

    private Log log = LogFactory.getLog(getClass());

    private RuntimeInstance rtInstance;
    private String configFile;

    private String encoding = "UTF-8";
    private String contentType = "text/html;charset=UTF-8";

    @Inject
    public void setConfigFile(@Named("VelocityProperties") String configFile) {
        this.configFile = configFile;
        log.info("Set velocity configuration file to: " + configFile);
    }

    @Inject(optional=true)
    public void setContentType(String contentType) {
        this.contentType = contentType;
        log.info("Set contentType = " + contentType);
        if(contentType!=null) {
            int n = contentType.indexOf("charset=");
            if(n!=(-1)) {
                encoding = contentType.substring(n + "charset=".length()).trim();
                log.info("Set encoding = " + encoding);
            }
        }
    }

    /**
     * Init Velocity Runtime.
     */
    public void init(ServletContext context) throws ServletException {
        rtInstance = new RuntimeInstance();
        // read property file:
        String path = context.getRealPath("/");
        Properties props = readProperties(path==null ? configFile : path + configFile);
        Set<?> keys = props.keySet();
        for(Object key : keys) {
            if(key instanceof String) {
                Object value = props.get(key);
                rtInstance.addProperty((String)key, value);
            }
        }
        try {
            if(rtInstance.getProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH)==null) {
                if(path!=null) {
                    rtInstance.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, path);
                    log.info("Using web application context path as velocity template root path: " + path);
                }
                else {
                    log.warn("Web application context path is unavailable (properly you deployed a .war file) and velocity property \"" + RuntimeConstants.FILE_RESOURCE_LOADER_PATH + "\" is not set.");
                }
            }
            else {
                log.info("Velocity property \"" + RuntimeConstants.FILE_RESOURCE_LOADER_PATH + "\" has been specified in \"" + configFile + "\".");
                log.info("To using web application context path, remove property \"" + RuntimeConstants.FILE_RESOURCE_LOADER_PATH + "\" from configuration file.");
            }
            rtInstance.init();
        }
        catch(Exception e) {
            log.error("VelocityViewResolver init failed.", e);
            throw new ServletException(e);
        }
    }

    /**
     * Read velocity properties from file.
     * 
     * @param file Velocity configuration file.
     * @return Velocity Properties.
     */
    protected Properties readProperties(String file) {
        Properties props = new Properties();
        InputStream input = null;
        try {
            input = new BufferedInputStream(new FileInputStream(file));
            props.load(input);
        }
        catch(IOException ioe) {
            log.warn("Read properties file failed.", ioe);
        }
        finally {
            if(input!=null) {
                try {
                    input.close();
                }
                catch(IOException e) {}
            }
        }
        return props;
    }

    /**
     * Using Velocity to render model.
     */
    public void resolveView(String view, Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // init context:
        Context context = new VelocityContext();
        if(model!=null) {
            Set<String> keys = model.keySet();
            for(String key : keys) {
                context.put(key, model.get(key));
            }
        }
        // init template:
        Template template = null;
        try {
            template = rtInstance.getTemplate(view, encoding);
        }
        catch(Exception e) {
            throw new ServletException(e);
        }
        // render:
        response.setContentType(contentType);
        OutputStream output = response.getOutputStream();
        VelocityWriter vw = new VelocityWriter(new OutputStreamWriter(output, encoding));
        try {
            template.merge(context, vw);
        }
        catch(Exception e) {
            throw new ServletException(e);
        }
        finally {
            vw.flush();
            vw.recycle(null);
        }
    }

}
