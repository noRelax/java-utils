package com.message.pachong;

import java.net.URL;

import org.htmlparser.beans.LinkBean;
import org.htmlparser.beans.StringBean;

import com.message.dao.ContentDAO;
import com.message.dao.DataDAOImp;
import com.message.dao.ErShouMaiDAO;
import com.message.dao.ZuFangDAO;
import com.message.service.ContentService;
import com.message.service.ErShouMaiService;
import com.message.service.ServiceImp;
import com.message.service.ZuFangService;

public class NiouChengQuanZi {
	private StringBean str;
	private LinkBean link;
 public void pagetodatabase(String url,ServiceImp con,DataDAOImp dao){
	 str=new StringBean();
	 str.setURL(url);
	 str.setLinks(false);
	 str.setCollapse(false);
	 StringBuffer content=new StringBuffer();
	 String str2[]=str.getStrings().split("\n");
	 String title="";
	 for(int i=6;i<str2.length;i++){
		 if(i==6){
			 title=str2[i].substring(str2[i].indexOf("|")+1,str2[i].lastIndexOf("|"));
			 if(title.length()>=15){
				 title=title.substring(0,15);
			 }
			 
		 }else{
			 if(i>=100){
				 if(str2[i].indexOf("邢台热线（www.520319.cn）版权所有")!=-1){
					 String str3=str2[i].substring(0,str2[i].indexOf("邢台热线（www.520319.cn）版权所有"));
						 content.append(str3);
				 }else{
					 content.append(str2[i]);
				 }
				 if(str2[i].indexOf("热度：")!=-1){
					 i=str2.length;
				 }
			 }
		 }
	 }
	 con.addContent(title, content.toString(), 1, dao);
 }
 public void setlinkgetstr(){
for(int ii=3;ii>=1;ii--){
	String url="http://bbs.520319.cn/Class_2_"+ii+".shtml";
	 link=new LinkBean();
	 link.setURL(url);
	 URL ur[]=link.getLinks();
	 for(int i=0;i<ur.length;i++){
		 if(ur[i].toString().indexOf("http://bbs.520319.cn/Details_")!=-1){
			 this.pagetodatabase(ur[i].toString(),new ContentService(),new ContentDAO());
		 }
	 }
}
 }
 public void ershouMai(){
	 for(int ii=3;ii>=1;ii--){
			String url="http://bbs.520319.cn/Class_5_"+ii+".shtml";
			 link=new LinkBean();
			 link.setURL(url);
			 URL ur[]=link.getLinks();
			 for(int i=0;i<ur.length;i++){
				 if(ur[i].toString().indexOf("http://bbs.520319.cn/Details_")!=-1){
					 this.pagetodatabase(ur[i].toString(),new ErShouMaiService(),new ErShouMaiDAO());
				 }
			 }
		}
 } 
 public void zufang(){
	 for(int ii=1;ii>=1;ii--){
			String url="http://bbs.520319.cn/Class_3_"+ii+".shtml";
			 link=new LinkBean();
			 link.setURL(url);
			 URL ur[]=link.getLinks();
			 for(int i=0;i<ur.length;i++){
				 if(ur[i].toString().indexOf("http://bbs.520319.cn/Details_")!=-1){
					 this.pagetodatabase(ur[i].toString(),new ZuFangService(),new ZuFangDAO());
				 }
			 }
		}
 }
 public static void main(String args[]){
	 NiouChengQuanZi n=new NiouChengQuanZi();
	 n.ershouMai();
 }
}
