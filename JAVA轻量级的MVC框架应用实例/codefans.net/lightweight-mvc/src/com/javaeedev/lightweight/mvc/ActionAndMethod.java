package com.javaeedev.lightweight.mvc;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.inject.Key;

/**
 * Holds Action and Method.
 * 
 * @author Xuefeng
 */
class ActionAndMethod {

    private Log log = LogFactory.getLog(getClass());

    private Key<?> key;
    private Class<Action> actionClass;
    private List<String> propertyList = new ArrayList<String>();
    private Map<String, ParameterType> parameterMap = new HashMap<String, ParameterType>();

    public ActionAndMethod(Key<?> key, Class<Action> actionClass) {
        this.key = key;
        this.actionClass = actionClass;
        Method[] methods = actionClass.getMethods();
        for(Method method : methods) {
            String setterName = getSetterName(method);
            if(setterName!=null) {
                // a valid setter:
                if(log.isDebugEnabled()) {
                    StringBuilder sb = new StringBuilder("Setter found in ");
                    sb.append(actionClass.getName())
                        .append(": void ")
                        .append(setterName)
                        .append('(')
                        .append(method.getParameterTypes()[0].getName())
                        .append(')');
                }
                propertyList.add(setterName);
                parameterMap.put(setterName, new ParameterType(method, method.getParameterTypes()[0]));
            }
        }
    }

    public Key<?> getKey() {
        return key;
    }

    public Class<Action> getActionClass() {
        return actionClass;
    }

    public List<String> getProperties() {
        return propertyList;
    }

    public void invokeSetter(Action action, String propertyName, String value) {
        ParameterType p = parameterMap.get(propertyName);
        if(p!=null) {
            Method m = p.getMethod();
            try {
                m.invoke(action, p.convert(value));
            }
            catch(Exception e) {
                if(log.isDebugEnabled())
                    log.debug("Invoke setter failed: " + m.getName(), e);
            }
        }
    }

    private String getSetterName(Method method) {
        String name = method.getName();
        if(!name.startsWith("set"))
            return null;
        if(name.length()<4)
            return null;
        if(!method.getReturnType().equals(void.class))
            return null;
        Class<?>[] params = method.getParameterTypes();
        if(params.length!=1)
            return null;
        if(!ParameterType.isSupport(params[0]))
            return null;
        return Character.toLowerCase(name.charAt(3)) + name.substring(4);
    }

}
