package com.itstar.erp.ui.searchswing.rukusearch;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.itstar.erp.dao.kucun.KuCunDaoImpl;
import com.itstar.erp.util.GetRS;
import com.itstar.erp.util.GetResultSet;
import com.itstar.erp.util.GetTime;
import com.itstar.erp.vo.product.ProBean;

public class RuKuEphSearch {

	static Map<Integer, String> ywyidnamemap = new HashMap<Integer, String>(); // 业务员
																				// 编号-----名称
	static {
		String table = "tb_yewuyuan_info";
		ResultSet rs = new GetResultSet().getResultSet(table);
		try {
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				ywyidnamemap.put(id, name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	static Map<Integer, Integer> prosp = new HashMap<Integer, Integer>(); // 商品
																			// 商品编号----供应商编号
	static {
		String table = "tb_product_info";
		ResultSet rs = new GetResultSet().getResultSet(table);
		try {
			while (rs.next()) {
				int proid = rs.getInt(1);
				int spid = rs.getInt(7);
				prosp.put(proid, spid);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	static Map<Integer, String> spidnamemap = new HashMap<Integer, String>(); // 供应商
																				// 编号----名称
	static {
		String table = "tb_supply_info";
		ResultSet rs = new GetResultSet().getResultSet(table);
		try {
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				spidnamemap.put(id, name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new RuKuEphSearch().init();
	}

	public void init() {

		Object[][] cells;
		ResultSet rs = null;

		rs = new GetRS()
				.getResultSet("select t1.* from tb_ruku_info as t1,tb_product_info as t2 order by t1.rukuAcount*t2.proPrice desc");// 怎么回事

		String[] colnames = { "入库金额","商品名称", "商品进价", "入库数量", "供应商名称", "经手人","入库编号", 
				 "入库时间" };

		int i = 0;

		double zongyingli = 0;
		double zongselljine = 0;

		try {
			while (rs.next()) {
				i++;
			}
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		cells = new Object[i][8];
		int j = 0;
		try {
			rs.beforeFirst();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {
			while (rs.next()) {
				System.out.println("Ha Ha Ha");

				int rukuid = rs.getInt(1); // 入库编号
				String rukudatetime = rs.getString(2);
				String stime = new GetTime().format(rukudatetime); // 入库时间
																	// 无----
				String time = new GetTime().toformat(rukudatetime); // 入库时间 有---

				int rukuacount = rs.getInt(3); // 入库数量
				int proid = rs.getInt(4); // 商品编号
				int ywyid = rs.getInt(5); // 业务员编号
				String remark = rs.getString(6); // 入库备注

				int id = prosp.get(proid);

				String spname = spidnamemap.get(proid); // 供应商名称
				String ywyname = ywyidnamemap.get(ywyid); // 经手人

				ProBean bean = new KuCunDaoImpl().getProBean(proid);
				String name = bean.getProName(); // 商品名称
				double price = bean.getProPrice(); // 商品进价

				double rukujine = rukuacount * price; // 入库金额

				// {"入库编号","商品名称","商品进价","入库数量","供应商名称","经手人","入库金额","入库时间"}

				cells[j][0] = String.valueOf(rukujine).trim() + "   元";
				cells[j][1] = name;
				cells[j][2] = String.valueOf(price).trim() + "   元";
				cells[j][3] = String.valueOf(rukuacount).trim();
				cells[j][4] = String.valueOf(spname);
				cells[j][5] = String.valueOf(ywyname);
				cells[j][6] = "RK" + time + "_" + (1000 + rukuid);
			
				cells[j][7] = String.valueOf(stime).trim();

				j++;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		JFrame jf = new JFrame("按入库金额大小排行");

		JTable jt = new JTable(cells, colnames);


		jt.setRowHeight(30);
	
		jt.setEnabled(false);

		JScrollPane js = new JScrollPane(jt);
		jf.add(js);
	
		jf.setVisible(true);
		jf.setSize(800, 600);
		jf.setLocationRelativeTo(null);

	}

}
