package com.itstar.erp.login;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.itstar.dao.Connect;
import com.itstar.erp.util.ExportExcel;

public class SellRank extends JFrame{
	private Map countMap = new HashMap<String,Integer>();
	private JButton button1 = null;
	private JLabel label1 = null;
	ResultSet rs = null;
	
	public SellRank(){
		initComponent();
		getCount();
	}
	
	public void initComponent(){
		this.setSize(500,450);
		this.setTitle("销售排行");
		this.setResizable(false);
		this.setLayout(null);
		
		Toolkit tl = Toolkit.getDefaultToolkit();
		Dimension d = tl.getScreenSize();
		int height = (int)d.getHeight();
		int width = (int)d.getWidth();
		this.setLocation((width-500)/2,(height-400)/2);
		
		button1 = new JButton("导出Excel");
		button1.setBounds(340, 60, 100, 25);
		this.add(button1);
		button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String[] str2 = {"sname","sellcount"};
				System.out.println("Excel已经导出 ");			
				ExportExcel.showExcel(countMap,str2,"ERP_sell");
				label1.setText("Excel已经导出");
				
			}
		});
		
		label1 = new JLabel();
		label1.setBounds(20,390,100,25);
		this.add(label1);
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				free();
				//System.out.println("资源释放");
			}
		});
		
		this.setVisible(true);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void paint(Graphics g){
		super.paintComponents(g);
		g.setColor(Color.RED);
		g.drawString("销售排行统计", 20, 50);
		g.drawString("销", 15, 70);
		g.drawString("售", 15, 85);
		g.drawString("量", 15, 100);
		g.drawLine(30, 350, 450, 350);
		g.drawLine(30, 350, 30, 70);
		g.drawString("商品名",430, 370);
		g.setColor(Color.BLUE);
		Set<String> entry = countMap.keySet();
		int dia = 60;
		for(String str:entry){
			int count = (Integer)countMap.get(str);
			g.drawString(str, 20+dia, 365);
			g.fillRect(20+dia, 350-count, 10, count);
			g.drawString(count+"", 20+dia, 350-count-5);
			dia+=30;
		}
		
	}

	
	public void getCount(){
			
		try{
			String sql = "select spname,sum(spcount) from ERP_sell group by spname";
			rs = Connect.stmt.executeQuery(sql);
			while(rs.next()){
				String str1 = rs.getString(1);
				int amount = rs.getInt(2);
				//System.out.println(str1 +  "  " +amount );
				countMap.put(str1,amount);
			}
			Connect.conn.commit();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public void free(){
		try{
			if(rs != null){
				rs.close();
				rs = null;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

}
