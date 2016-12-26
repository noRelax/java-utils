package com.message.pachong;

import java.net.URL;

import org.htmlparser.beans.LinkBean;
import org.htmlparser.beans.StringBean;

import com.message.dao.*;

import com.message.service.*;
public class XingTai123 {
	private StringBean strbean;
	private LinkBean linbean;
	/**
	 * 向数据库内输入数据
	 * @param url
	 */
	public void PageDataToDatabase(String url,DataDAOImp obj,ServiceImp ser,boolean num){

		strbean=new StringBean();
		strbean.setURL(url);
		strbean.setLinks(false);
		strbean.setCollapse(true);
		String content=strbean.getStrings();
		String hah[] = content.split("\n");
		for (int i = 5; i < hah.length - 4; i++) {
			//标题
			String title="";
			//内容
			StringBuffer TableContent=new StringBuffer();
			if(num){
			if (!(hah[i].length()==6)) {
				if(i%2==0){
				if (hah[i].length() > 15) {
					title=hah[i].substring(0, 15)+ "......";
				} else {
					title=hah[i];
				}
				TableContent.append(hah[i]);
				ser.addContent(title, TableContent.toString(), 1,obj);
			}
			}
			}else{
				if (!(hah[i].length()==6)) {
					if(i%2==1){
					if (hah[i].length() > 15) {
						title=hah[i].substring(0, 15)+ "......";
					} else {
						title=hah[i];
					}
					TableContent.append(hah[i]);
					ser.addContent(title, TableContent.toString(), 1,obj);
					}
				}
			}
		}
	}
	/**
	 * 招聘
	 */
	public void ZhaoPin(){
		this.PageDataToDatabase("http://www.xingtai123.com/zhaopin/so.asp?content=招聘&page=1#fb",new ContentDAO(),new ContentService(),true);
	}
	/**
	 * 应聘
	 */
	public void yingpin(){
		this.PageDataToDatabase("http://www.xingtai123.com/zhaopin/so.asp?content=求职&page=1#fb",new ZhaoGongZuoDAO(),new ZhaoGongZuoService(),false);
	}
	/**
	 * 求购
	 */
	public void qiougou(){
		this.PageDataToDatabase("http://www.xingtai123.com/ershou/so.asp?content=求购&page=1#fb",new ErShouDAO(),new ErShouService(),true);
	}
	/**
	 * 出售
	 */
	public void chushou(){
		this.PageDataToDatabase("http://www.xingtai123.com/ershou/so.asp?content=出售&page=1#fb",new ErShouMaiDAO(),new ErShouMaiService(),true);
	}
	/**
	 * 获取此页面全部链接
	 * @param url2
	 * @return
	 */
	public URL[] getLinks(String url2)
	{
		linbean=new LinkBean();
		linbean.setURL(url2);
		return linbean.getLinks();
	}
	public static void main(String args[]){
		XingTai123 x=new XingTai123();
		x.yingpin();
	}
}