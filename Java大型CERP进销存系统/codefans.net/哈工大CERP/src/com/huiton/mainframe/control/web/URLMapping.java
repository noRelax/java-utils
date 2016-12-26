/*
 * $Id: URLMapping.java,v 1.1.1.1 2002/02/02 05:20:25 Administrator Exp $
 * Copyright 2001 Sun Microsystems, Inc. All rights reserved.
 * Copyright 2001 Sun Microsystems, Inc. Tous droits r�serv�s.
 */

package com.huiton.mainframe.control.web;

/**�Ű���
 * ����ע�ͣ���������˵����
 * �˶�����Ҫ����Ϊ������URL����Ҫʹ�õ���Դ��ϵ����������������࣬ҳ���ļ��ȵ�
 * �Թ����̿��Ƴ�������Դ
 * */
import java.util.HashMap;

public class URLMapping implements java.io.Serializable {

    private String url;
    private boolean useRequestHandler = false;
    private boolean useFlowHandler = false;
    private String flowHandler = null;
    private String requestHandler = null;
    private HashMap resultMappings;
    private String screen;
    private boolean requiresSignin = false;

    public URLMapping(String url, String screen) {
        this.url = url;
        this.screen = screen;
    }

    public URLMapping(String url,
                                    String screen,
                                    boolean useRequestHandler,
                                    boolean useFlowHandler,
                                    String requestHandler,
                                    String flowHandler,
                                    HashMap resultMappings,
                                    boolean requiresSignin) {
        this.url = url;
        this.flowHandler = flowHandler;
        this.requestHandler = requestHandler;
        this.useRequestHandler = useRequestHandler;
        this.useFlowHandler = useFlowHandler ;
        this.resultMappings = resultMappings;
        this.screen = screen;
        this.requiresSignin = requiresSignin;
    }

    public boolean requiresSignin() {
        return requiresSignin;
    }

    public boolean useFlowHandler() {
        return useFlowHandler;
    }

    public boolean useRequestHandler() {
        return useRequestHandler;
    }

    public String getRequestHandler() {
        return requestHandler;
    }

    public String getFlowHandler() {
        return flowHandler;
    }

    public String getScreen() {
        return screen;
    }

    public String getResultScreen(String key) {
        if (resultMappings != null) {
            return (String)resultMappings.get(key);
        } else {
            return null;
        }
    }

    public HashMap getResultMappings() {
        return resultMappings;
    }

    public String toString() {
        return "[URLMapping: url=" + url +
                ", useRequestHandler=" + useRequestHandler +
                ", useFlowHandler=" + useFlowHandler +
                ", requestHandler=" + requestHandler +
                ", flowHandler=" + flowHandler +
                ", resultMappings=" + resultMappings +
                "]";
    }
}

