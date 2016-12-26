package com.itstar.erp.login;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.itstar.Gongyingshang;
import com.itstar.SpxInxi;
import com.itstar.dao.Connect;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Ruku extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private ResultSet rs = null;
	private JLabel label_1;
	private JLabel lblPi;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ruku frame = new Ruku();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Ruku() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 410);
		contentPane = new JPanel();
		this.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				free();
			}
		});
		
		setContentPane(contentPane);
		{
			JLabel label = new JLabel("\u5546\u54C1\u540D\u79F0");
			label.setBounds(29, 21, 54, 15);
			contentPane.add(label);
		}
		{
			textField = new JTextField();
			textField.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					label_1.setText("");
				}
			});
			textField.setBounds(97, 18, 252, 21);
			contentPane.add(textField);
			textField.setColumns(10);
		}
		{
			JLabel label = new JLabel("\u6570    \u91CF");
			label.setBounds(29, 59, 54, 15);
			contentPane.add(label);
		}
		{
			textField_1 = new JTextField();
			textField_1.setBounds(97, 56, 252, 21);
			contentPane.add(textField_1);
			textField_1.setColumns(10);
		}
		{
			JLabel label = new JLabel("\u4EF7    \u683C");
			label.setBounds(29, 102, 54, 15);
			contentPane.add(label);
		}
		{
			JLabel label = new JLabel("\u4F9B \u5E94 \u5546");
			label.setBounds(29, 151, 54, 15);
			contentPane.add(label);
		}
		{
			textField_2 = new JTextField();
			textField_2.setBounds(97, 99, 252, 21);
			contentPane.add(textField_2);
			textField_2.setColumns(10);
		}
		{
			textField_3 = new JTextField();
			textField_3.setBounds(97, 148, 252, 21);
			contentPane.add(textField_3);
			textField_3.setColumns(10);
		}
		{
			JLabel label = new JLabel("\u5165\u5E93\u65E5\u671F");
			label.setBounds(29, 205, 54, 15);
			contentPane.add(label);
		}
		{
			textField_4 = new JTextField();
			textField_4.setBounds(97, 202, 252, 21);
			contentPane.add(textField_4);
			textField_4.setColumns(10);
		}
		{
			JButton button = new JButton("\u63D0  \u4EA4");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String str1 = textField.getText();
					String str4 = textField_3.getText();
					String str5 = textField_4.getText();
					String str6 = textField_5.getText();
					if(str1.equals("")||textField_1.getText().equals("")||textField_2.getText().equals("")||str4.equals("")||str5.equals("")||str6.equals("")){
						JOptionPane.showMessageDialog(Ruku.this,"信息不完全","Error",JOptionPane.ERROR_MESSAGE);
					    textField.requestFocus();
					}else{
						try{
							int count = Integer.parseInt(textField_1.getText());
							int price = Integer.parseInt(textField_2.getText());
							Date date = new Date(str5);
							String sql = "select allcall from gysgl";
							//Connect.getConnect();
							boolean flag1 = false;
							boolean flag2 = false;
							
							rs = Connect.stmt.executeQuery("select spname from spgl");
							while(rs.next()){
								String spname = rs.getString(1);
								if(str1.equals(spname)){
									flag2 = true;
									break;
								}
							}
							int j = 0;
							int i = 0;
							if(!flag2){
								 j= JOptionPane.showConfirmDialog(Ruku.this, "商品信息不存在，要添加吗","",JOptionPane.YES_NO_OPTION);
								if(j == 0)
									new SpxInxi().setVisible(true);
								else{
									rs = Connect.stmt.executeQuery(sql);
									while(rs.next()){
										String gys = rs.getString(1);
										if(str4.equals(gys)){
											flag1 = true;
											break;
										}
									}
							
								
									if(!flag1){
										//JOptionPane.showMessageDialog(Ruku.this, "供应商信息不存在，要添加吗？","",JOptionPane.OK_CANCEL_OPTION);
										 i= JOptionPane.showConfirmDialog(Ruku.this, "供应商信息不存在，要添加吗","",JOptionPane.YES_NO_OPTION);
										if(i == 0)
											new Gongyingshang();
										else{								
										}
									}
								}
							}
							
							
							
							
							
							
							String sql2 = "insert into ruku values('"+str1+"',"+count+","+price+",'"+str4+"','"+str5+"','"+str6+"')";
							//string sql4 = 插入库存表相应信息
							Connect.stmt.executeUpdate(sql2);
							label_1.setText("插入成功！！");
							textField.setText("");
							textField_1.setText("");
							textField_2.setText("");
							textField_3.setText("");
							textField_4.setText("");
							textField_5.setText("");
							if(i!=0)
								textField.requestFocus();
							Connect.conn.commit();
							
						}catch(NumberFormatException e1){
							JOptionPane.showMessageDialog(Ruku.this,"价格与数量都应为数字");
							textField_1.requestFocus();
						}catch(SQLException e2){
							e2.printStackTrace();
						}catch(IllegalArgumentException e3){
							label_1.setText("日期格式不正确(yyyy/mm/dd)");
							textField_4.setText("");
							textField_4.requestFocus();
						}
						
					}
					
				
				}
			});
			button.setBounds(330, 325, 93, 23);
			contentPane.add(button);
		}
		{
			label_1 = new JLabel("");
			label_1.setBounds(10, 290, 238, 15);
			contentPane.add(label_1);
		}
		{
			lblPi = new JLabel("\u6279\u51C6\u6587\u53F7");
			lblPi.setBounds(29, 254, 54, 15);
			contentPane.add(lblPi);
		}
		{
			textField_5 = new JTextField();
			textField_5.setBounds(97, 251, 252, 21);
			contentPane.add(textField_5);
			textField_5.setColumns(10);
		}
		this.setVisible(true);
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
