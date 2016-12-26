package com.javaeedev.lightweight.mvc;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * Used for holding parameter type.
 * 
 * @author Xuefeng
 */
class ParameterType {

    private Method method;
    private Class<?> parameterClass;

    public ParameterType(Method method, Class<?> parameterClass) {
        this.method = method;
        this.parameterClass = parameterClass;
    }

    public Object convert(String value) {
        if(parameterClass.equals(String.class))
            return value;
        if(parameterClass.equals(int.class) || parameterClass.equals(Integer.class))
            return Integer.valueOf(value);
        if(parameterClass.equals(boolean.class) || parameterClass.equals(Boolean.class))
            return Boolean.valueOf(value);
        if(parameterClass.equals(long.class) || parameterClass.equals(Long.class))
            return Long.valueOf(value);
        if(parameterClass.equals(float.class) || parameterClass.equals(Float.class))
            return Float.valueOf(value);
        if(parameterClass.equals(double.class) || parameterClass.equals(Double.class))
            return Double.valueOf(value);
        if(parameterClass.equals(short.class) || parameterClass.equals(Short.class))
            return Short.valueOf(value);
        if(parameterClass.equals(byte.class) || parameterClass.equals(Byte.class))
            return Byte.valueOf(value);
        if(value.length()>0 && parameterClass.equals(char.class) || parameterClass.equals(Character.class))
            return value.charAt(0);
        throw new IllegalArgumentException("Cannot convert to type: " + parameterClass.getName());
    }

    public Method getMethod() {
        return method;
    }

    public Class<?> getParameterClass() {
        return parameterClass;
    }

    public static boolean isSupport(Class<?> clazz) {
        return supportedClasses.contains(clazz);
    }

    private static Set<Class<?>> supportedClasses = new HashSet<Class<?>>();

    static {
        supportedClasses.add(boolean.class);
        supportedClasses.add(char.class);
        supportedClasses.add(byte.class);
        supportedClasses.add(short.class);
        supportedClasses.add(int.class);
        supportedClasses.add(long.class);
        supportedClasses.add(float.class);
        supportedClasses.add(double.class);
        supportedClasses.add(Boolean.class);
        supportedClasses.add(Character.class);
        supportedClasses.add(Byte.class);
        supportedClasses.add(Short.class);
        supportedClasses.add(Integer.class);
        supportedClasses.add(Long.class);
        supportedClasses.add(Float.class);
        supportedClasses.add(Double.class);
        supportedClasses.add(String.class);
    }

}
