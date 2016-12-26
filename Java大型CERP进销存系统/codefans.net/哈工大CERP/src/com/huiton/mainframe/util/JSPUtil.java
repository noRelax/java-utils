/*
 * $Id: JSPUtil.java,v 1.1.1.1 2002/02/02 05:20:24 Administrator Exp $
 * Copyright 2001 Sun Microsystems, Inc. All rights reserved.
 * Copyright 2001 Sun Microsystems, Inc. Tous droits r?erv?.
 */

package com.huiton.mainframe.util;

import java.util.Locale;
import java.util.Vector;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.text.BreakIterator;
import java.util.Locale;
import java.io.ByteArrayOutputStream;

import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

import com.huiton.mainframe.util.WebKeys;
import com.huiton.mainframe.util.tracer.Debug;

/**
 * This utility class for web tier components (namely Java
 * Server Pages and JavaBeans). This class provides a
 * central location to do specialized formatting in both
 * a default and a locale specfic manner.
 */
public final class JSPUtil extends Object {

    //access to eventCounter is only through the
    //accessor method getEventId()
    private static int eventCounter;

    /**
     * Converts a String SJIS or JIS URL encoded hex encoding to a Unicode String
     *
    */
    public static String convertJISEncoding(String target) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        if (target == null) return null;
        String paramString = target.trim();

        for (int loop =0; loop < paramString.length(); loop++) {
            int i = (int)paramString.charAt(loop);
            bos.write(i);
        }
        String convertedString = null;
        try {
            convertedString =  new String(bos.toByteArray(), "JISAutoDetect");
        } catch (java.io.UnsupportedEncodingException uex) {}
        return convertedString;
    }

    public static String formatCurrency(String amountString) {
        try {
            double amount = Double.parseDouble(amountString);
            return formatCurrency(amount);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    public static String formatCurrency(String amountString, Locale locale) {
        try {
            double amount = Double.parseDouble(amountString);
            return formatCurrency(amount, locale);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    public static String formatCurrency(double amount){
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        DecimalFormat df = (DecimalFormat)nf;
        df.setMinimumFractionDigits(2);
        df.setMaximumFractionDigits(2);
        df.setDecimalSeparatorAlwaysShown(true);
        String pattern = "$###,###.00";
        df.applyPattern(pattern);
        return df.format(amount);
    }

    public static String formatPlainCurrency(double amount){
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        DecimalFormat df = (DecimalFormat)nf;
        df.setMinimumFractionDigits(2);
        df.setMaximumFractionDigits(2);
        df.setDecimalSeparatorAlwaysShown(true);
        String pattern = "###,###";
        df.applyPattern(pattern);
        return df.format(amount);
    }

    public static String formatCurrency(double amount, Locale locale){
        NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
        return nf.format(amount);
    }

    public static Vector parseKeywords(String keywordString){
        if (keywordString != null){
            Vector keywords = new Vector();
            BreakIterator breakIt = BreakIterator.getWordInstance();
            int index=0;
            int previousIndex =0;
            breakIt.setText(keywordString);
            try{
                while(index < keywordString.length()){
                    previousIndex = index;
                    index = breakIt.next();
                    String word = keywordString.substring(previousIndex, index);
                    if (!word.trim().equals("")) keywords.addElement(word);
                }
                return keywords;
            } catch (Throwable e){
                Debug.print(e, "Error while parsing search string");
            }

        }
        return null;
    }

    public static Vector parseKeywords(String keywordString, Locale locale){
        if (keywordString != null){
            Vector keywords = new Vector();
            BreakIterator breakIt = BreakIterator.getWordInstance(locale);
            int index=0;
            int previousIndex =0;
            breakIt.setText(keywordString);
            try{
                while(index < keywordString.length()){
                    previousIndex = index;
                    index = breakIt.next();
                    String word = keywordString.substring(previousIndex, index);
                    if (!word.trim().equals("")) keywords.addElement(word);
                }
                return keywords;
            } catch (Throwable e){
                Debug.print(e, "Error while parsing search string" );
            }

        }
        return null;
    }

    public static int getEventId(){
        return eventCounter++;
    }

    /**
     * Get the Locale specified in the session or return a default locale.
     */
    public static Locale getLocale(HttpSession session) {
       // Locale locale = (Locale)session.getAttribute(WebKeys.LanguageKey);
       Locale locale = null;
       if (locale == null) locale = Locale.CHINA;
        return locale;
    }

    /**
     * Get the Locale specified in the session or return a default locale.
     */
    public static Locale getLocaleFromLanguage(String language) {
        Locale locale = Locale.US;
        if (language.equals("English")) locale = Locale.US;
        else if (language.equals("Japanese")) locale = Locale.JAPAN;
        return locale;
    }

    /**
     *  Added by Baresi.根据request中传递的参数获取SQL查询条件字串
     */
    public static String getCriSQLString(String fieldName,
                                          String sParamName,
                                          String eParamName,
                                          ServletRequest request)
    {
        String sParamValue = request.getParameter(sParamName);
        String eParamValue = request.getParameter(eParamName);
        sParamValue = (sParamValue==null) ? "":sParamValue;
        eParamValue = (eParamValue==null) ? "":eParamValue;
        sParamValue = sParamValue.trim();
        eParamValue = eParamValue.trim();

        String result = "";

        if(sParamValue.equals("") && eParamValue.equals(""))
            result = " 1=1 ";
        else if(sParamValue.equals(""))
            result = fieldName + "<='" + eParamValue + "' ";
        else if(eParamValue.equals(""))
            result = fieldName + ">='" + sParamValue + "' ";
        else
            result = " " +
                fieldName + ">='" + sParamValue + "' AND " +
                fieldName + "<='" + eParamValue + "'" +
                " ";

        return  result;
    }
}











