package com.itstar.erp.sale;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JTable;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JScrollPane;

import com.itstar.erp.dao.Init;

public class Mingci extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel message = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	/**
	 * This is the default constructor
	 */
	public Mingci() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(474, 300);
		this.setContentPane(getJContentPane());
		this.setTitle("销售表排行榜");
		this.setResizable(false);
		this.setVisible(true);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			message = new JLabel();
			message.setBounds(new Rectangle(154, 4, 136, 25));
			message.setFont(new Font("Dialog", Font.BOLD, 24));
			message.setText("目前排行榜");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(message, null);
			jContentPane.add(getJScrollPane(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(7, 36, 453, 220));
			jScrollPane.setViewportView(getJTable());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTable() {
		if (jTable == null) {
			try{
				Connection conn=Init.getConnection();
				Statement st=null;
				ResultSet rs=null;
				st=conn.createStatement();
				String sql="select a.ousername as name,sum((b.goutprice-b.ginprice)*a.ognumber) as pig  from outstock as a join goods as b on a.ogid=b.gid group by a.ousername order by pig desc";
			    rs=st.executeQuery(sql);
			    while(rs.next()){
			    	System.out.println(rs.getString("name"));
			    	System.out.println(rs.getFloat("pig"));
			    }
				Object data[][]={
			};
	        String colum[]={"名次","销售总额","对应销售员"};
			jTable = new JTable(data,colum);
			jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return jTable;
	}

}  //  @jve:decl-index=0:visual-constraint="179,2"
