package com.itstar.erp.util;
//download by http://www.codefans.net
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetTime {
	
	public String getTime(){
			Date d=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String date=sdf.format(d);
			return date;
	}
	public String getTime(String rk){
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		String date=sdf.format(d);
		return date;
	}
	
	public String toformat(String datetime){
		String date=datetime.substring(0, 10);
		String[] s=date.split("-");
		String d="";
		for(int i=0;i<s.length;i++){
			d+=s[i];
		}
		return d;
	}
	
	public String format(String datetime){
		String date=datetime.substring(0, 10);
		
		return date;
	}
	
	public String getTime(int b){
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("今天是yyyy年,MM月,dd日,是个好天气哦~");
		String date=sdf.format(d);
		return date;
}
}
