package com.itstar.info.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import com.itstar.info.bean.GuestBean;
import com.itstar.info.bean.SeversBean;
import com.itstar.info.dao.GuestInfoDao;
import com.itstar.info.dao.SeverInfoDao;

public class DoSeverInfo {
	
	SeverInfoDao severdao=new SeverInfoDao(); 
	
	public void doseverinsert(SeversBean sever){
		if("".equals(sever.getSname())){
			JOptionPane.showMessageDialog(null, "客户名不能为空！");
		}else{
			String str=sever.getSname();
			ResultSet rs;
			DoSeverInfo dosever=new DoSeverInfo();
			try {
				rs = severdao.getrs(str);
				if(rs.next()){
					if(rs.getString(2).equals("0")){
						if(dosever.reg(sever)==0){
							severdao.updateFlag("1",str);
							JOptionPane.showMessageDialog(null, "添加成功！");
						}
					}else{
						JOptionPane.showMessageDialog(null, "该客户已经存在!添加失败！");
					}
				}else{
					if(dosever.reg(sever)==0){
						severdao.insert(sever);
						JOptionPane.showMessageDialog(null, "添加成功！");
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}	
	}
	
	
	public void doseveralter(SeversBean sever){
		if("".equals(sever.getSname())){
			JOptionPane.showMessageDialog(null, "请选择客户!");
		}else{
			try {
				DoSeverInfo dosever=new DoSeverInfo();
				if(dosever.reg(sever)==0){
					severdao.altersever(sever);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void doseverdelete(SeversBean sever){
		if("".equals(sever.getSname())){
			JOptionPane.showMessageDialog(null, "请选择客户!");
		}else{
			try {
				severdao.deletesever(sever);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public static List docom(){
		List<String> list=new SeverInfoDao().getsevercom();
		return list;
	}
	
	public SeversBean setcom(String value){
		SeversBean sever=new SeversBean();
		try {
			sever=severdao.setsevercom(value);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sever;
	}
	
	public int reg(SeversBean sever ){
		int re=0;
		String s1="\\d{1,12}";
		String s2="\\w{1,}@\\w{1,}\56\\w{1,}";
		for(int i=0;i<1;i++){
			if(sever.getSnumber().matches(s1)||"".equals(sever.getSnumber())){
				
			}else{
				JOptionPane.showMessageDialog(null, "联系号码格式不正确！");
				re++;
				break;
			}
			if(sever.getSphone().matches(s1)||"".equals(sever.getSnumber())){
				
			}else{
				JOptionPane.showMessageDialog(null, "手机号码格式不正确！");
				re++;
				break;
			}
			if(sever.getSemail().matches(s2)||"".equals(sever.getSnumber())){
				
			}else{
				JOptionPane.showMessageDialog(null, "邮箱格式不正确！");
				re++;
				break;
			}
		}
		return re;
	}

}
