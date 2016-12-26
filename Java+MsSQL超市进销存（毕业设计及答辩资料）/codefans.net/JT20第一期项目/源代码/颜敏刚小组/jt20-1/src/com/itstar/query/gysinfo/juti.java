package com.itstar.query.gysinfo;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class juti {
//	public static void main(String[] aaaaa) {
//		chaxun test=new chaxun();
//		String name="%x%";
//		List li=test.queryLike(name);
//		
//		for(int i=0;i<li.size();i++){
//			GysBean row=(GysBean) li.get(i);
//			System.out.println(row.getSid());
//			System.out.println(row.getSname());
//			System.out.println(row.getSaddress());
//			System.out.println(row.getSemail());
//			System.out.println(row.getSphone());
//			System.out.println(row.getSperson());
//			System.out.println(row.getSpostCode());
//			System.out.println(row.getSbank());
//			System.out.println(row.getSnumber());
//			System.out.println(row.getSflag());
//		}
//	}

	public List  textfiled(JTable jtable,String name) {
		Object[][] cells = null;
		String[] tableforname = { "供应商编号", "供应商名称", "供应商地址", "邮件", "联系人手机",
				"联系人姓名", "邮编", "银行帐号", "供应商电话", "标记" };
		chaxun text=new chaxun();
//		javabean java=new javabean();
//		name=java.getJTextField().getText();
		List<GysBean> list =text.queryLike(name);	
		cells = new Object[list.size()][9];
		for (int i = 0; i < list.size(); i++) {
			cells[i][0] = list.get(i).getSid();
			cells[i][1] = list.get(i).getSname();
			cells[i][2] = list.get(i).getSaddress();
			cells[i][3] = list.get(i).getSemail();
			cells[i][4] = list.get(i).getSphone();
			cells[i][5] = list.get(i).getSperson();
			cells[i][6] = list.get(i).getSpostCode();
			cells[i][7] = list.get(i).getSbank();
			cells[i][8] = list.get(i).getSnumber();
		}
		DefaultTableModel model = (DefaultTableModel) jtable.getModel();
		model.setColumnIdentifiers(tableforname);
		model.setDataVector(cells, tableforname);
		
		return list;
		}
}
