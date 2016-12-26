package com.itstar.info.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.itstar.info.bean.GuestBean;
import com.itstar.info.bean.SeversBean;
import com.itstar.info.dao.GuestInfoDao;
import com.itstar.info.dao.SeverInfoDao;

public class DoGestInfo {

	GuestInfoDao gudao=new GuestInfoDao();
	
	public void doguestinsert(GuestBean guest){
		if("".equals(guest.getGname())){
			JOptionPane.showMessageDialog(null, "客户名不能为空！");
		}else{
			String str=guest.getGname();
			ResultSet rs;
			DoGestInfo doguest=new DoGestInfo();
			try {
				rs = gudao.getrs(str);
				if(rs.next()){
					if(rs.getString(2).equals("0")){
						if(doguest.reg(guest)==0){
							gudao.updateFlag("1",str);
							JOptionPane.showMessageDialog(null, "添加成功！");
						}
					}else{
						JOptionPane.showMessageDialog(null, "该客户已经存在!添加失败！");
					}
				}else{
					if(doguest.reg(guest)==0){
						gudao.insert(guest);
						JOptionPane.showMessageDialog(null, "添加成功！");
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void doalter(GuestBean guest){
		DoGestInfo doguest=new DoGestInfo();
		if("".equals(guest.getGname())){
			JOptionPane.showMessageDialog(null, "请选择客户!");
		}else{
			try {
				if(doguest.reg(guest)==0){
					gudao.alter(guest);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void dodelete(GuestBean guinfo){
		if("".equals(guinfo.getGname())){
			JOptionPane.showMessageDialog(null, "请选择客户!");
		}else{
			try {
				gudao.delete(guinfo);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public static List docom(){
		List<String> list=new GuestInfoDao().getcom();
		return list;
	}
	
	public GuestBean setcom(String value){
		GuestBean guest=new GuestBean();
		GuestInfoDao guinfo=new GuestInfoDao();
		try {
			guest=guinfo.setcombo(value);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return guest;
	}
	
	public int reg(GuestBean guest ){
		int re=0;
		String s1="\\d{1,12}";
		String s2="\\w{1,}@\\w{1,}\56\\w{1,}";
		for(int i=0;i<1;i++){
			if(guest.getGnumber().matches(s1)||"".equals(guest.getGnumber())){
				
			}else{
				JOptionPane.showMessageDialog(null, "联系号码格式不正确！");
				re++;
				break;
			}
			if(guest.getGphone().matches(s1)||"".equals(guest.getGnumber())){
				
			}else{
				JOptionPane.showMessageDialog(null, "手机号码格式不正确！");
				re++;
				break;
			}
			if(guest.getGemail().matches(s2)||"".equals(guest.getGnumber())){
				
			}else{
				JOptionPane.showMessageDialog(null, "邮箱格式不正确！");
				re++;
				break;
			}
		}
		return re;
	}

}
