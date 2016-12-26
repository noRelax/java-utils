package com.itstar.erp.util;
//download by http://www.codefans.net
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class StringTransformDate {
	
	public final static java.sql.Time string2Date(String dateString)
	throws java.lang.Exception {
	DateFormat dateFormat;
	dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	dateFormat.setLenient(false);
	java.util.Date timeDate = dateFormat.parse(dateString);// util类型
	java.sql.Time dateTime = new java.sql.Time(timeDate.getTime());// sql类型
	return dateTime;
}
}