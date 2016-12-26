package com.itstar.erp.instock;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTextArea;
import javax.swing.JButton;

import com.itstar.erp.dao.Init;

public class Reback extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel top = null;
	private JLabel dept = null;
	private JTextField deptField = null;
	private JLabel goodno = null;
	private JTextField goodnoField = null;
	private JLabel goodname = null;
	private JTextField goodnameField = null;
	private JLabel number = null;
	private JTextField numberField = null;
	private JLabel reason = null;
	private JTextArea reasonArea = null;
	private JButton sumit = null;
	private  int left;

	/**
	 * This is the default constructor
	 */
	public Reback() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(444, 273);
		this.setLocation(380, 220);
		this.setContentPane(getTop());
		this.setTitle("退货管理");
		this.setResizable(false);
		this.setVisible(true);
	}

	/**
	 * This method initializes top
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getTop() {
		if (top == null) {
			reason = new JLabel();
			reason.setBounds(new Rectangle(36, 119, 76, 23));
			reason.setFont(new Font("Dialog", Font.BOLD, 18));
			reason.setText("退货理由");
			number = new JLabel();
			number.setBounds(new Rectangle(224, 69, 77, 26));
			number.setFont(new Font("Dialog", Font.BOLD, 18));
			number.setText("退货数量");
			goodname = new JLabel();
			goodname.setBounds(new Rectangle(41, 68, 63, 23));
			goodname.setFont(new Font("Dialog", Font.BOLD, 18));
			goodname.setText("商品名");
			goodno = new JLabel();
			goodno.setBounds(new Rectangle(230, 27, 67, 18));
			goodno.setFont(new Font("Dialog", Font.BOLD, 18));
			goodno.setText("商品号");
			dept = new JLabel();
			dept.setBounds(new Rectangle(39, 25, 79, 24));
			dept.setFont(new Font("Dialog", Font.BOLD, 18));
			dept.setText("所属部门");
			top = new JPanel();
			top.setLayout(null);
			top.setFont(new Font("Dialog", Font.PLAIN, 18));
			top.setBackground(new Color(204, 102, 255));
			top.add(dept, null);
			top.add(getDeptField(), null);
			top.add(goodno, null);
			top.add(getGoodnoField(), null);
			top.add(goodname, null);
			top.add(getGoodnameField(), null);
			top.add(number, null);
			top.add(getNumberField(), null);
			top.add(reason, null);
			top.add(getReasonArea(), null);
			top.add(getSumit(), null);
		}
		return top;
	}

	/**
	 * This method initializes deptField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getDeptField() {
		if (deptField == null) {
			deptField = new JTextField();
			deptField.setBounds(new Rectangle(130, 25, 70, 22));
			deptField.setFont(new Font("Dialog", Font.BOLD, 18));
			deptField.setBackground(Color.pink);
			deptField.setForeground(new Color(0, 204, 204));
			deptField.setEditable(false);
			deptField.setText("采购部");
		}
		return deptField;
	}

	/**
	 * This method initializes goodnoField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getGoodnoField() {
		if (goodnoField == null) {
			goodnoField = new JTextField();
			goodnoField.setBounds(new Rectangle(307, 27, 81, 21));
			goodnoField.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if("".equals(goodnoField.getText())){
						JOptionPane.showMessageDialog(null, "商品编号不能为空!");
						goodnoField.requestFocus();
					}
					else if(!goodnoField.getText().matches("^[0-9_]+$")){
						JOptionPane.showMessageDialog(null, "商品编号不是数字!");
						goodnoField.setText("");
						goodnoField.requestFocus();
					} 
					else {
						try {
						int num_1=Integer.valueOf(goodnoField.getText());
						Connection conn=Init.getConnection();
						Statement st=conn.createStatement();
						ResultSet rs=null;
						String sql="select gname from goods where gid='"+num_1+"'";
					    rs=st.executeQuery(sql);
					    if(!rs.next()){ JOptionPane.showMessageDialog(null, "商品号有误!");
					    goodnoField.setText("");
					    goodnoField.requestFocus();
					    return;
					    }
					}catch(Exception e1){
						e1.printStackTrace();
					}
					
					try{
						int num_2=Integer.valueOf(goodnoField.getText());
						Connection conn=Init.getConnection();
						Statement st=conn.createStatement();
						ResultSet rs=null;
						String sql="select gname from goods where gid='"+num_2+"'";
					    rs=st.executeQuery(sql);
					    while(rs.next()){
					    	System.out.println(rs.getString("gname"));
					    	goodnameField.setText(rs.getString("gname"));
					    	numberField.requestFocus();
					    }
					    }catch(Exception e2){
						e2.printStackTrace();
					}
				}
				}
				});
		}
		return goodnoField;
	}

	/**
	 * This method initializes goodnameField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getGoodnameField() {
		if (goodnameField == null) {
			goodnameField = new JTextField();
			goodnameField.setBounds(new Rectangle(112, 68, 85, 25));
			goodnameField.setEditable(false);
		}
		return goodnameField;
	}

	/**
	 * This method initializes numberField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getNumberField() {
		if (numberField == null) {
			numberField = new JTextField();
			numberField.setBounds(new Rectangle(316, 67, 68, 25));
			numberField.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if("".equals(numberField.getText())){
						JOptionPane.showMessageDialog(null, "数量为必填项,不能为空!");
						numberField.requestFocus();
					}
					else if(!numberField.getText().matches("^[0-9_]+$")){
						JOptionPane.showMessageDialog(null, "商品编号不是数字!");
						numberField.requestFocus();
					} 
					else {
						try{
							int num_3=Integer.valueOf(goodnoField.getText());
							int num_4=Integer.valueOf(numberField.getText());
							Connection conn=Init.getConnection();
							Statement st=conn.createStatement();
							ResultSet rs=null;
							String sql="select Ssum from stock where sgid='"+num_3+"'";
						    rs=st.executeQuery(sql);
						    while(rs.next()){
						    	if(rs.getInt("Ssum")<num_4) {
						    		JOptionPane.showMessageDialog(null, "输入的数量有错误！.......");
						    		numberField.setText("");
						    		goodnoField.requestFocus();
						    		return;
						    	}
						    	else {
						    		left=rs.getInt("Ssum");
						    		reasonArea.requestFocus();
						    	}
						    	
						    }
						    
						}catch(Exception e1){
						    e1.printStackTrace();	
						    }
						    }
				}
			});
		}
		return numberField;
	}

	/**
	 * This method initializes reasonArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getReasonArea() {
		if (reasonArea == null) {
			reasonArea = new JTextArea();
			reasonArea.setBounds(new Rectangle(131, 114, 254, 64));
		}
		return reasonArea;
	}

	/**
	 * This method initializes sumit	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSumit() {
		if (sumit == null) {
			sumit = new JButton();
			sumit.setBounds(new Rectangle(161, 203, 94, 23));
			sumit.setText("确定提交");
			sumit.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try{
						int num_3=Integer.valueOf(goodnoField.getText());
						int num_4=Integer.valueOf(numberField.getText());
						Connection conn=Init.getConnection();
						Statement st=conn.createStatement();
						ResultSet rs=null;
						String sql="select Ssum from stock where sgid='"+num_3+"'";
					    rs=st.executeQuery(sql);
					    while(rs.next()){
					    	if(rs.getInt("Ssum")<num_4) {
					    		JOptionPane.showMessageDialog(null, "输入的数量有错误！.......");
					    		numberField.setText("");
					    		goodnoField.requestFocus();
					    		return;
					    	}
					    	else {
					    		left=rs.getInt("Ssum");
					    		reasonArea.requestFocus();
					    	}
					    }
					    
					}catch(Exception e1){
					    e1.printStackTrace();	
					    }
					try{
						if(!"".equals(goodnoField.getText()) && !"".equals(numberField.getText())) {
					    Connection conn=Init.getConnection();
						PreparedStatement ps=null;
						ResultSet rs=null;
						String  sql="Insert into reback (GId,GName,Number,reason) values (?,?,?,?)";
						ps=conn.prepareStatement(sql);
						int num_1=Integer.valueOf(goodnoField.getText());
						int num_2=Integer.valueOf(numberField.getText());
						ps.setInt(1, num_1);
						ps.setString(2,goodnameField.getText());
						ps.setInt(3, num_2);
						ps.setString(4, reasonArea.getText());
						ps.executeUpdate();						
					}
				else {JOptionPane.showMessageDialog(null, "有数据项为空!");  return; }
				}catch(Exception e1){e1.printStackTrace();}
				try{
					System.out.println(left);
					int num_1=Integer.valueOf(goodnoField.getText());
					int num_2=Integer.valueOf(numberField.getText());
					System.out.println(num_2);
					int num_3=left-num_2;
					System.out.println(num_3);
					Connection conn=Init.getConnection();
					PreparedStatement ps=null;
					ResultSet rs=null;
					String sql="update stock set Ssum=? where Sgid=?";
					ps=conn.prepareStatement(sql);
					ps.setInt(1,num_3);
					ps.setInt(2, num_1);
					int set=ps.executeUpdate();
					if(set>0) {
						JOptionPane.showMessageDialog(null, "退货成功");
						goodnoField.setText("");
						goodnameField.setText("");
						numberField.setText("");
						reasonArea.setText("");
						goodnoField.requestFocus();
						return;
					}
					}catch(Exception e2){e2.printStackTrace();}
			}
			});
		}
		return sumit;
	}
} 
