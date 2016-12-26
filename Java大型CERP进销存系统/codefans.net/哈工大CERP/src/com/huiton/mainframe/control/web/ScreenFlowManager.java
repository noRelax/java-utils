package com.huiton.mainframe.control.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

import com.huiton.mainframe.util.JSPUtil;
import com.huiton.mainframe.util.WebKeys;

import com.huiton.mainframe.control.web.handlers.FlowHandler;

import com.huiton.mainframe.util.tracer.Debug;

/**
 * This file looks at the Request URL and maps the request
 * to the page for the web-templating mechanism.
 */
public class ScreenFlowManager implements java.io.Serializable {

    private HashMap screens;
    private HashMap urlMappings;
    private HashMap exceptionMappings;
    private HashMap screenDefinitionMappings;
    private String defaultScreen = "MAIN";
    private String signinScreen = "SIGN_IN";
    private String signinErrorScreen = "SIGN_IN_ERROR";

    //�Ű��� ���ӵ����ڴ���ʱ����Ȩ�޷���
    private static String noRightScreen = "NO_RIGHT";
    private static String sessionTimeoutScreen = "SESSION_TIMEOUT";
    //�������

    public ScreenFlowManager() {
        screens = new HashMap();
    }

    public void init(ServletContext context) {
        String screenDefinitionsURL = null;
        String requestMappingsURL = null;
        try {
            requestMappingsURL = context.getResource("/WEB-INF/xml/requestmappings.xml").toString();
        } catch (java.net.MalformedURLException ex) {
            Debug.println("ScreenFlowManager: initializing ScreenFlowManager malformed URL exception: " + ex);
        }

        //��requestmappings.xml��װ�������������һ����䣬������ScreenFlowData����
        //    <default-screen>MAIN</default-screen>
        //    <signin-screen>SIGN_IN</signin-screen>
        //    <signin-error-screen>SIGN_IN</signin-error-screen>
        //    <session-timeout-screen>SESSION_TIMEOUT</session-timeout-screen>
        //    <no-right-screen>NO_RIGHT</no-right-screen>
        ScreenFlowData screenFlowData = ScreenFlowXmlDAO.loadScreenFlowData(requestMappingsURL);
        defaultScreen = screenFlowData.getDefaultScreen();
        signinScreen = screenFlowData.getSigninScreen();
        signinErrorScreen = screenFlowData.getSigninErrorScreen();
        Debug.println("ScreenFlowManager: **** ScreenFlowData = " + screenFlowData);
        Debug.println("Loading Exception mappings");
        exceptionMappings = screenFlowData.getExceptionMappings();
        Debug.println("Exception mappings=" + exceptionMappings);

        //��requestmappings.xml��װ��������Ļ���
        //    <screen-definition url="/WEB-INF/xml/screendefinitions.xml" language="zh_CN"/>
        //    <screen-definition url="/WEB-INF/xml/ja/screendefinitions.xml" language="ja_JP"/>
        screenDefinitionMappings = screenFlowData.getScreenDefinitionMappings();
        //������Ϣ��ӡ����
        Iterator it = screenDefinitionMappings.keySet().iterator();
        while (it.hasNext()) {
            String language = (String)it.next();
            Debug.println("ScreenFlowManager loading screen definitions for language " + language);
            try {
                String mappings = (String)screenDefinitionMappings.get(language);
                Debug.println("ScreenFlowManager: mappings are: " + mappings);
                screenDefinitionsURL = context.getResource(mappings).toString();
            } catch (java.net.MalformedURLException ex) {
                Debug.println("ScreenFlowManager: initializing ScreenFlowManager malformed URL exception: " + ex);
            }
        //��ûһ��screendefinition.xmlװ�ؽ���
            HashMap screenDefinitions = ScreenFlowXmlDAO.loadScreenDefinitions(screenDefinitionsURL);
            screens.put(language, screenDefinitions);
        }
        // looad url mappings from the context
        urlMappings = (HashMap)context.getAttribute(WebKeys.URLMappingsKey);
        Debug.println("ScreenFlowManager:  initialized Screens and mappings");
    }

    /**
     * The UrlMapping object contains information that will match
     * a url to a mapping object that contains information about
     * the current screen, the RequestHandler that is needed to
     * process a request, and the RequestHandler that is needed
     * to insure that the propper screen is displayed.
    */

    private URLMapping getURLMapping(String urlPattern) {
        if ((urlMappings != null) && urlMappings.containsKey(urlPattern)) {
            return (URLMapping)urlMappings.get(urlPattern);
        } else {
            return null;
        }
    }

    public String getExceptionScreen(String exceptionClassName) {
        return (String)exceptionMappings.get(exceptionClassName);
    }

    /**
        Get the screens for the specified language.

    */

    public HashMap getScreens(Locale locale) {
        String languageKey = locale.getLanguage() + "_" + locale.getCountry();
        if (screens.containsKey(languageKey)) {
            return (HashMap)screens.get(languageKey);
        }
        return null;
    }

    /**
        Get the template for the specified language.

    */
    public String getTemplate(Locale locale) {
        HashMap localeScreens = getScreens(locale);
        if (localeScreens == null) return null;
        else return (String)localeScreens.get(ScreenFlowXmlDAO.TEMPLATE);
    }

    /**
        ��ȡ��ģ��.
    */
    public HashMap getTemplates(Locale locale) {
        HashMap localeScreens = getScreens(locale);
        Debug.println("ScreenFlowManager.getTemplates(): locale = " + locale);
        Debug.println("ScreenFlowManager.getTemplates(): localeScreens = " +
                      String.valueOf(localeScreens!=null));
        if (localeScreens == null)
            return null;
        else
            return (HashMap)localeScreens.get(ScreenFlowXmlDAO.TEMPLATE);
    }


    /**
     * Using the information we have in the request along with
     * The url map for the current url we will insure that the
     * propper current screen is selected based on the settings
     * in both the screendefinitions.xml file and requestmappings.xml
     * files.
    */
    public void getNextScreen(HttpServletRequest request) {
        // set the presious screen
        String previousScreen = (String)request.getSession().getAttribute(WebKeys.CurrentScreen);
        if (previousScreen != null) request.getSession().setAttribute(WebKeys.PreviousScreen, previousScreen);
        String selectedURL = request.getPathInfo();
        String currentScreen = defaultScreen;
        URLMapping urlMapping = getURLMapping(selectedURL);
        if (urlMapping != null) {
            if (!urlMapping.useFlowHandler()) {
                currentScreen = urlMapping.getScreen();
            } else {
                Debug.println("ScreenFlowManager: using flow handler for:" + selectedURL);
                // load the flow handler
                FlowHandler handler = null;
                String flowHandlerString = urlMapping.getFlowHandler();
                try {
                    handler = (FlowHandler)getClass().getClassLoader().loadClass(flowHandlerString).newInstance();
                    // invoke the processFlow(HttpServletRequest)
                    handler.doStart(request);
                    String flowResult = handler.processFlow(request);
                    Debug.println("ScreenFlowManager: flow handler processing result=" + flowResult);
                    handler.doEnd(request);
                    // get the matching screen from the URLMapping object
                    if (flowResult.equals("TARGET_URL")) {
                        String urlPattern = (String)request.getSession().getAttribute(WebKeys.SigninTargetURL);
                        currentScreen = getURLMapping(urlPattern).getScreen();
                        Debug.println("ScreenFlowManager: using SigninTargetURL=" + currentScreen);
                    } else {
                        currentScreen = urlMapping.getResultScreen(flowResult);
                    }
               } catch (Exception ex) {
                   Debug.println("ScreenFlowManager caught loading handler: " + ex);
               }
            }
        }
        if (currentScreen != null) {
            request.getSession().setAttribute(WebKeys.CurrentScreen, currentScreen);
        } else {
            Debug.println("ScreenFlowManager: Screen not found for " + selectedURL);
            throw new RuntimeException("Screen not found for " + selectedURL);
        }

    }

    public void setDefaultScreen(String defaultScreen) {
        this.defaultScreen = defaultScreen;
    }

    /**
    * Gets the required parameter for the current screen
    *
    * This method is used by the insert tag to get the parameters
    * needed to build a page.
    *
    * If a language is not set then the default properties will be loaded.
    */
    public Parameter getParameter(String key, HttpSession session) {
        String currentScreen = (String)session.getAttribute(WebKeys.CurrentScreen);
        Locale locale = JSPUtil.getLocale(session);
        if (currentScreen == null) currentScreen = defaultScreen;
        if (screens == null || currentScreen == null) return null;
        Screen screen = (Screen)getScreens(locale).get(currentScreen);
        if (screen == null) {
            return null;
        }
        return screen.getParameter(key);
    }

    /**
     * Returs the current screen
     */

    public String getCurrentScreen(HttpSession session)  {
        return (String)session.getAttribute(WebKeys.CurrentScreen);
    }

    public String getSigninScreen() {
        return signinScreen;
    }

    public String getSigninErrorScreen() {
        return signinErrorScreen;
    }

    public String getSessionTimeoutScreen() {
        return sessionTimeoutScreen;
    }

    public String getNoRightScreen() {
        return noRightScreen;
    }

}

