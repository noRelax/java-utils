package com.itstar.query.gysinfo;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class xianshi {
	public void filetable(JTable jtable) {
		Object[][] cells = null;
		String[] tableforname = { "��Ӧ�̱��", "��Ӧ������", "��Ӧ�̵�ַ", "�ʼ�", "��ϵ���ֻ�",
				"��ϵ������", "�ʱ�", "�����ʺ�", "��Ӧ�̵绰", "���" };
		List<GysBean> list = new ArrayList<GysBean>();
		list = new suoyou().queryall();
		int length = list.size();
		cells = new Object[length][9];
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
	}
	
//	public void filetableQuery(JTable jtable) {}
}
