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
		//����
		String title="";
		//����
		StringBuffer TableContent=new StringBuffer();
		String Content=strbean.getStrings();
		String st[]=Content.split("\n");
		ContentService con=new ContentService();
		for(int i=0;i<st.length;i++)//28,49
		{
			if(st[i].trim().equals("��Ƹְλ��"))
			{
				title="��Ƹְλ��"+st[i+1];
			}
			if(st[i].trim().equals("����Ҫ��")){
				for(;!st[i].trim().equals("�ο�н�ʣ�");i++)
				{
					TableContent.append(st[i]);
				}
			}
			if(st[i].trim().equals("��Ƹ�绰��")){
				TableContent.append("��Ƹ�绰��"+st[i+1]);
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
