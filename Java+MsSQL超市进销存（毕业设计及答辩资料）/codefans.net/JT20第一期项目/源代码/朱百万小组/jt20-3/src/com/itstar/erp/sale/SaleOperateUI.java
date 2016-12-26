package com.itstar.erp.sale;

import java.awt.BorderLayout;
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

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;

import com.itstar.erp.dao.Init;

public class SaleOperateUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel top = null;
	private JLabel dept = null;
	private JTextField deptField = null;
	private JLabel goodno = null;
	private JTextField goodnoField = null;
	private JLabel mark = null;
	private JLabel goodname = null;
	private JTextField goodnameField = null;
	private JLabel number = null;
	private JTextField numberField = null;
	private JLabel mark1 = null;
	private JLabel stock = null;
	private JTextField stockField = null;
	private JLabel saler = null;
	private JComboBox salername = null;
	private JLabel mark11 = null;
	private JButton sumit = null;
	private JButton canel = null;
	private JLabel java = null;

	/**
	 * This is the default constructor
	 */
	public SaleOperateUI() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(458, 280);
		this.setLocation(380, 220);
		this.setContentPane(getTop());
		this.setTitle("销售员操作");
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
			java = new JLabel();
			java.setBounds(new Rectangle(39, 211, 354, 18));
			java.setForeground(new Color(51, 255, 153));
			java.setText("操作提示：输入一行后按<ENTER>键, 输入完毕后按<提交>键");
			mark11 = new JLabel();
			mark11.setBounds(new Rectangle(389, 121, 10, 24));
			mark11.setForeground(new Color(205, 49, 49));
			mark11.setHorizontalAlignment(SwingConstants.CENTER);
			mark11.setHorizontalTextPosition(SwingConstants.CENTER);
			mark11.setText("*");
			mark11.setFont(new Font("Dialog", Font.BOLD, 18));
			saler = new JLabel();
			saler.setBounds(new Rectangle(211, 120, 63, 24));
			saler.setFont(new Font("Dialog", Font.BOLD, 18));
			saler.setText("销售员");
			stock = new JLabel();
			stock.setBounds(new Rectangle(33, 120, 80, 26));
			stock.setFont(new Font("Dialog", Font.BOLD, 18));
			stock.setText("库存信息");
			mark1 = new JLabel();
			mark1.setBounds(new Rectangle(384, 71, 10, 20));
			mark1.setForeground(new Color(205, 49, 49));
			mark1.setHorizontalAlignment(SwingConstants.CENTER);
			mark1.setHorizontalTextPosition(SwingConstants.CENTER);
			mark1.setText("*");
			mark1.setFont(new Font("Dialog", Font.BOLD, 18));
			number = new JLabel();
			number.setBounds(new Rectangle(211, 73, 51, 25));
			number.setFont(new Font("Dialog", Font.BOLD, 18));
			number.setText("数 量");
			goodname = new JLabel();
			goodname.setBounds(new Rectangle(33, 76, 63, 21));
			goodname.setFont(new Font("Dialog", Font.BOLD, 18));
			goodname.setText("商品名");
			mark = new JLabel();
			mark.setBounds(new Rectangle(396, 29, 38, 18));
			mark.setFont(new Font("Dialog", Font.BOLD, 18));
			mark.setHorizontalAlignment(SwingConstants.CENTER);
			mark.setHorizontalTextPosition(SwingConstants.CENTER);
			mark.setForeground(new Color(205, 49, 49));
			mark.setText("*");
			goodno = new JLabel();
			goodno.setBounds(new Rectangle(203, 26, 83, 27));
			goodno.setFont(new Font("Dialog", Font.BOLD, 18));
			goodno.setText("商品编号");
			dept = new JLabel();
			dept.setBounds(new Rectangle(31, 25, 79, 29));
			dept.setFont(new Font("Dialog", Font.BOLD, 18));
			dept.setText("所属部分");
			top = new JPanel();
			top.setLayout(null);
			top.add(dept, null);
			top.add(getDeptField(), null);
			top.add(goodno, null);
			top.add(getGoodnoField(), null);
			top.add(mark, null);
			top.add(goodname, null);
			top.add(getGoodnameField(), null);
			top.add(number, null);
			top.add(getNumberField(), null);
			top.add(mark1, null);
			top.add(stock, null);
			top.add(getStockField(), null);
			top.add(saler, null);
			top.add(getSalername(), null);
			top.add(mark11, null);
			top.add(getSumit(), null);
			top.add(getCanel(), null);
			top.add(java, null);
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
			deptField.setBounds(new Rectangle(116, 24, 67, 30));
			deptField.setFont(new Font("Dialog", Font.BOLD, 18));
			deptField.setBackground(Color.cyan);
			deptField.setEditable(false);
			deptField.setText("销售部");
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
			goodnoField.setBounds(new Rectangle(295, 28, 84, 22));
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
					    if(!rs.next()){ JOptionPane.showMessageDialog(null, "暂无此商品");
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
			goodnameField.setBounds(new Rectangle(108, 76, 76, 22));
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
			numberField.setBounds(new Rectangle(281, 73, 82, 22));
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
					    	if(rs.getInt("Ssum")==0){
					    		String str=String.valueOf(rs.getInt("Ssum"));
					    		stockField.setText(str);
					    		JOptionPane.showMessageDialog(null, "缺货中.......");
					    		goodnoField.setText("");
					    		goodnameField.setText("");
					    		numberField.setText("");
					    		stockField.setText("");
					    		goodnoField.requestFocus();
					    		return;  }
					    	else if(rs.getInt("Ssum")<num_4) {
					    		String str=String.valueOf(rs.getInt("Ssum"));
					    		stockField.setText(str);
					    		JOptionPane.showMessageDialog(null, "库存不足.......");
					    		goodnoField.setText("");
					    		goodnameField.setText("");
					    		numberField.setText("");
					    		stockField.setText("");
					    		goodnoField.requestFocus();
					    		return;
					    	}
					    	else {
					    		String str=String.valueOf(rs.getInt("Ssum"));
					    		stockField.setText(str);
					    		salername.requestFocus();
					    	}
					    	}
					    }catch(Exception e3){
						e3.printStackTrace();}
				     }
				}
				});
		}
		return numberField;
	}

	/**
	 * This method initializes stockField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getStockField() {
		if (stockField == null) {
			stockField = new JTextField();
			stockField.setBounds(new Rectangle(121, 121, 69, 22));
			stockField.setEditable(false);
		}
		return stockField;
	}

	/**
	 * This method initializes salername	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getSalername() {
		if (salername == null) {
			salername = new JComboBox();
			salername.setBounds(new Rectangle(288, 122, 82, 21));
			salername.addItem("酒进法子");
			salername.addItem("黄容");
			salername.addItem("悟空");
			salername.addItem("猪八戒");
			salername.addItem("科比");
			salername.addItem("王丽丽");
			salername.addItem("吴平平");
			salername.addItem("张敏");
			}
		return salername;
	}

	/**
	 * This method initializes sumit	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSumit() {
		if (sumit == null) {
			sumit = new JButton();
			sumit.setBounds(new Rectangle(101, 168, 72, 27));
			sumit.setFont(new Font("Dialog", Font.BOLD, 18));
			sumit.setText("提交");
			sumit.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try{
						if(!"".equals(goodnoField.getText()) && !"".equals(numberField.getText())) {
					    Connection conn=Init.getConnection();
						PreparedStatement ps=null;
						ResultSet rs=null;
						String  sql="Insert into outstock (OGId,OGName,OGNumber,OUsername) values (?,?,?,?)";
						ps=conn.prepareStatement(sql);
						int num_1=Integer.valueOf(goodnoField.getText());
						int num_2=Integer.valueOf(numberField.getText());
						String value = salername.getSelectedItem().toString(); 
						ps.setInt(1, num_1);
						ps.setString(2,goodnameField.getText());
						ps.setInt(3, num_2);
						ps.setString(4, value);
						int set=ps.executeUpdate();						
					}
				else {JOptionPane.showMessageDialog(null, "有数据项为空!");  return; }
				}catch(Exception e1){e1.printStackTrace();}
				try{
					int num_1=Integer.valueOf(goodnoField.getText());
					System.out.println(num_1);
					int num_2=Integer.valueOf(numberField.getText());
					System.out.println(num_2);
					int num_3=Integer.valueOf(stockField.getText());
					System.out.println(num_3);
					int num_4=num_3-num_2;
					System.out.println(num_4);
					Connection conn=Init.getConnection();
					PreparedStatement ps=null;
					ResultSet rs=null;
					String sql="update stock set Ssum=? where Sgid=?";
					ps=conn.prepareStatement(sql);
					ps.setInt(1,num_4);
					ps.setInt(2, num_1);
					int set=ps.executeUpdate();
					if(set>0) {
						JOptionPane.showMessageDialog(null, "交易成功");
						goodnoField.setText("");
						goodnameField.setText("");
						numberField.setText("");
						stockField.setText("");
						goodnoField.requestFocus();
						return;
					}
				}catch(Exception e2){e2.printStackTrace();}
				}
			});
		}
		return sumit;
	}

	/**
	 * This method initializes canel	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getCanel() {
		if (canel == null) {
			canel = new JButton();
			canel.setBounds(new Rectangle(217, 167, 72, 29));
			canel.setFont(new Font("Dialog", Font.BOLD, 18));
			canel.setText("取消");
			canel.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					goodnoField.setText("");
		    		goodnameField.setText("");
		    		numberField.setText("");
		    		stockField.setText("");
		    		goodnoField.requestFocus();
				}
			});
		}
		return canel;
	}

}  //  @jve:decl-index=0:visual-constraint="147,-16"
