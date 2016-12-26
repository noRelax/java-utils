package com.itstar.query.ingood;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



public class xianshi {
	public void filetable(JTable jtable) {
		Object[][] cells = null;
		String[] tableforname = { "入库编号", "商品编号", "商品名称", "商品类型", "进货单价",
				"数量", "计量单位", "总计金额", "供应商名称", "入库时间" ,"结算方式"};
		List<Gysbean> list = new ArrayList<Gysbean>();
		list = new chaxun().queryall();
		int length = list.size();
		cells = new Object[length][11];
		for (int i = 0; i < list.size(); i++) {
			cells[i][0] = list.get(i).getDid();
			cells[i][1] = list.get(i).getPgid();
			cells[i][2] = list.get(i).getGname();
			cells[i][3] = list.get(i).getGtype();
			cells[i][4] = list.get(i).getPprice();
			cells[i][5] = list.get(i).getPnumber();
			cells[i][6] = list.get(i).getGunit();
			cells[i][7] = list.get(i).getDtotal();
			cells[i][8] = list.get(i).getDsname();
			cells[i][9] = list.get(i).getDdate();
			cells[i][10]= list.get(i).getDsquare();
		}
		DefaultTableModel model = (DefaultTableModel) jtable.getModel();
		model.setColumnIdentifiers(tableforname);
		model.setDataVector(cells, tableforname);
	}
	
//	public void filetableQuery(JTable jtable) {}
}
