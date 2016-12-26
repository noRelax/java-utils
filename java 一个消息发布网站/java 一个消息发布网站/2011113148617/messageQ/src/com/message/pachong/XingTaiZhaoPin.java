package com.message.pachong;

import java.net.URL;

import org.htmlparser.beans.LinkBean;
import org.htmlparser.beans.StringBean;

import com.message.dao.ContentDAO;
import com.message.service.ContentService;

public class XingTaiZhaoPin {
	private StringBean strbean;
	private LinkBean linbean;
	public URL[] getLinks(String url2)
	{
		linbean=new LinkBean();
		linbean.setURL(url2);
		return linbean.getLinks();
	}
	public void PageDataToDatabase(String url){
		strbean=new StringBean();
		strbean.setURL(url);
		strbean.setLinks(false);
		strbean.setCollapse(true);
		//标题
		String title="";
		//内容
		StringBuffer TableContent=new StringBuffer();
		String Content=strbean.getStrings();
		String st[]=Content.split("\n");
		ContentService con=new ContentService();
		for(int i=0;i<st.length;i++)//28,49
		{
			if(st[i].trim().equals("招聘职位："))
			{
				title="招聘职位："+st[i+1];
			}
			if(st[i].trim().equals("其它要求：")){
				for(;!st[i].trim().equals("参考薪资：");i++)
				{
					TableContent.append(st[i]);
				}
			}
			if(st[i].trim().equals("招聘电话：")){
				TableContent.append("招聘电话："+st[i+1]);
			}
		}
		con.addContent(title, TableContent.toString(), 1,new ContentDAO());
	}
	public void ZhaoPin(){
		for(int ii=3;ii>=1;ii--)
		{
			URL u[]=this.getLinks("http://www.xtjob.net/zp/zw.php?gzdd=0&gzxz=&zpdx=&zpsex=&zwlb=&zpzw=&PageNo="+ii);
			for(int i=0;i<u.length;i++)
			{
				if(u[i].toString().indexOf("http://www.xtjob.net/zp/z_")!=-1){
					this.PageDataToDatabase(u[i].toString());
				}
			}
		}
	}
	public static void main(String[] args) {
		XingTaiZhaoPin xingtai=new XingTaiZhaoPin();
		xingtai.ZhaoPin();
	}
}
