package com.itstar.erp.login;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.itstar.dao.Connect;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class OneStatic extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OneStatic frame = new OneStatic();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public OneStatic() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 518, 366);
		this.setSize(520,400);
		Toolkit tl = Toolkit.getDefaultToolkit();
		Dimension d = tl.getScreenSize();
		int width = (int)d.getWidth();
		int height = (int)d.getHeight();
		this.setLocation((width-520)/2,(height-400)/2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		{
			JLabel label = new JLabel("\u5546\u54C1\u540D");
			label.setBounds(30, 24, 54, 15);
			contentPane.add(label);
		}
		{
			textField = new JTextField();
			textField.setBounds(100, 21, 148, 21);
			contentPane.add(textField);
			textField.setColumns(10);
		}
		{
			JLabel label = new JLabel("\u5E93  \u5B58");
			label.setBounds(30, 65, 54, 15);
			contentPane.add(label);
		}
		{
			textField_1 = new JTextField();
			textField_1.setBounds(100, 62, 148, 21);
			contentPane.add(textField_1);
			textField_1.setColumns(10);
		}
		{
			JLabel label = new JLabel("\u9500  \u91CF");
			label.setBounds(30, 105, 54, 15);
			contentPane.add(label);
		}
		{
			textField_2 = new JTextField();
			textField_2.setBounds(100, 102, 148, 21);
			contentPane.add(textField_2);
			textField_2.setColumns(10);
		}
		{
			textField_3 = new JTextField();
			textField_3.setBounds(100, 147, 148, 21);
			contentPane.add(textField_3);
			textField_3.setColumns(10);
		}
		{
			JLabel label = new JLabel("\u8FDB  \u4EF7");
			label.setBounds(30, 150, 54, 15);
			contentPane.add(label);
		}
		{
			JLabel label = new JLabel("\u9500\u552E\u4EF7");
			label.setBounds(30, 189, 54, 15);
			contentPane.add(label);
		}
		{
			textField_4 = new JTextField();
			textField_4.setBounds(100, 186, 148, 21);
			contentPane.add(textField_4);
			textField_4.setColumns(10);
		}
		{
			JLabel label = new JLabel("\u5546\u54C1\u7F16\u53F7");
			label.setBounds(272, 24, 54, 15);
			contentPane.add(label);
		}
		{
			textField_5 = new JTextField();
			textField_5.setBounds(336, 21, 148, 21);
			contentPane.add(textField_5);
			textField_5.setColumns(10);
		}
		{
			JLabel label = new JLabel("\u4F9B \u5E94 \u5546");
			label.setBounds(30, 231, 54, 15);
			contentPane.add(label);
		}
		{
			textField_6 = new JTextField();
			textField_6.setBounds(100, 228, 148, 21);
			contentPane.add(textField_6);
			textField_6.setColumns(10);
		}
		{
			JButton button = new JButton("\u67E5 \u8BE2");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String productName = textField.getText().trim();
					String productId = textField_8.getText().trim();
					if(productName.equals("") && productId.equals("")){
						JOptionPane.showMessageDialog(OneStatic.this,"商品名或商品编号不能都为空","Error",JOptionPane.ERROR_MESSAGE);
					}else{
							ResultSet rs = null;
							String sql = "";
							if(!productName.equals(""))
								sql = ""+productName;
							else
								sql = ""+productId;
							try{
								rs = Connect.stmt.executeQuery(sql);
								while(rs.next()){
									int stock = rs.getInt(1);
									textField_1.setText(stock+"");
									int sellAmount = rs.getInt(2);
									textField_2.setText(sellAmount+"");
									Double jinPrice = rs.getDouble(3);
									textField_3.setText(jinPrice+"");
									Double sellPrice = rs.getDouble(4);
									textField_4.setText(sellPrice+"");
									String supplierName = rs.getString(5);
									textField_5.setText(supplierName);
									String productNumber = rs.getString(6);
									textField_6.setText(productNumber);
									Date date1 = rs.getDate(7);
									textField_7.setText(date1.toString());
									Date date2 = rs.getDate(8);
									textField_8.setText(date2.toString());
									String productName1 = rs.getString(9);
									textField.setText(productName1);
									
								}
								Connect.conn.commit();
							}catch(SQLException e1){
								e1.printStackTrace();
							}
						
					}
					/*try{
						String sql = 
					}*/
				}
			});
			button.setBounds(366, 252, 93, 23);
			contentPane.add(button);
		}
		{
			JButton button = new JButton("\u53D6 \u6D88");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					textField_4.setText("");
					textField_5.setText("");
					textField_6.setText("");
					textField_7.setText("");
					textField_8.setText("");
					textField.requestFocus();
				}
			});
			button.setBounds(366, 285, 93, 23);
			contentPane.add(button);
		}
		{
			JLabel label = new JLabel("");
			label.setBounds(30, 307, 264, 15);
			contentPane.add(label);
		}
		{
			JLabel label = new JLabel("\u8FDB\u8D27\u65E5\u671F");
			label.setBounds(272, 105, 54, 15);
			contentPane.add(label);
		}
		{
			JLabel label = new JLabel("\u751F\u4EA7\u65E5\u671F");
			label.setBounds(272, 65, 54, 15);
			contentPane.add(label);
		}
		{
			textField_7 = new JTextField();
			textField_7.setBounds(336, 62, 148, 21);
			contentPane.add(textField_7);
			textField_7.setColumns(10);
		}
		{
			textField_8 = new JTextField();
			textField_8.setBounds(336, 102, 148, 21);
			contentPane.add(textField_8);
			textField_8.setColumns(10);
		}
		init();
	}
	public void init(){
		this.setVisible(true);
		this.setTitle("单一物品信息查询");
		
	}
}
