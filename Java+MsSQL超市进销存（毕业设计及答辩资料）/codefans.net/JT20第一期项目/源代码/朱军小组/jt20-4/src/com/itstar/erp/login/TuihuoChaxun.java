package com.itstar.erp.login;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.itstar.dao.Connect;

public class TuihuoChaxun extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private ResultSet rs =null;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TuihuoChaxun frame = new TuihuoChaxun();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the frame.
	 */
	public TuihuoChaxun() {
		setTitle("\u9000\u8D27\u7BA1\u7406\u9875\u9762\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 357);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		{
			JLabel label = new JLabel("\u5546\u54C1\u540D\u79F0");
			label.setBounds(22, 23, 54, 15);
			contentPane.add(label);
		}
		{
			textField = new JTextField();
			textField.setBounds(101, 20, 223, 21);
			contentPane.add(textField);
			textField.setColumns(10);
		}
		{
			JLabel label = new JLabel("\u5546\u54C1\u6570\u91CF");
			label.setBounds(22, 64, 54, 15);
			contentPane.add(label);
		}
		{
			textField_1 = new JTextField();
			textField_1.setBounds(101, 61, 223, 21);
			contentPane.add(textField_1);
			textField_1.setColumns(10);
		}
		{
			JLabel label = new JLabel("\u5546\u54C1\u4EF7\u683C");
			label.setBounds(22, 108, 54, 15);
			contentPane.add(label);
		}
		{
			textField_2 = new JTextField();
			textField_2.setBounds(101, 105, 223, 21);
			contentPane.add(textField_2);
			textField_2.setColumns(10);
		}
		{
			JLabel label = new JLabel("\u4F9B \u5E94 \u5546");
			label.setBounds(22, 154, 54, 15);
			contentPane.add(label);
		}
		{
			textField_3 = new JTextField();
			textField_3.setBounds(101, 151, 223, 21);
			contentPane.add(textField_3);
			textField_3.setColumns(10);
		}
		{
			JButton button = new JButton("\u786E \u5B9A");
			button.setBounds(327, 270, 93, 23);
			button.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					String str1 = textField.getText().trim();
					String str2 = textField_1.getText().trim();
					String str3 = textField_2.getText().trim();
					String str4 = textField_3.getText().trim();
					String str5 = textField_4.getText().trim();;
					
					if(str1.equals("")||str2.equals("")){
						JOptionPane.showMessageDialog(TuihuoChaxun.this,"必须有名称数量","Error",JOptionPane.ERROR_MESSAGE);
					    textField.requestFocus();
					}else{
						try{
							int count = Integer.parseInt(str2);
							int price = Integer.parseInt(str3);
							//Connect.getConnect();
							rs = Connect.stmt.executeQuery("select sname from ERP_stockform where sname ='"+ str1 +"'");
							if(!rs.next()){
								JOptionPane.showMessageDialog(TuihuoChaxun.this,"没有此商品库存信息","Error",JOptionPane.ERROR_MESSAGE);
								textField.setText("");
								textField.requestFocus();
							}
							rs = Connect.stmt.executeQuery("select scount from ERP_stockform where sname = '"+str1+"'");
							rs.next();
							int scount = rs.getInt(1);
							if(scount<count){
								JOptionPane.showMessageDialog(TuihuoChaxun.this,"库存商品不足够","Error",JOptionPane.ERROR_MESSAGE);
								textField_1.setText("");
								textField_1.requestFocus();
							}else{
								Connect.stmt.executeUpdate("update ERP_stockform set scount=scount-"+count+" where sname = '"+str1+"'");
								Connect.conn.commit();
								textField.setText("");
								textField_2.setText("");
								textField_1.setText("");
								textField_3.setText("");
								textField_4.setText("");
								textField.requestFocus();
							}
							
						}catch(SQLException e1){
							e1.printStackTrace();
						}catch(NumberFormatException e2){
							label_1.setText("输入商品数量或价格应为数字");
							textField.requestFocus();
						}
						
							/*int count = Integer.parseInt(textField_1.getText());
							int price = Integer.parseInt(textField_2.getText());
							Date date = new Date(str5);
							String sql = "select allcall from gysgl";
							Connect.getConnect();
							boolean flag1 = false;
							boolean flag2 = false;*/
						
					}
					
				}
			});
			contentPane.add(button);
		}
		{
			JLabel label = new JLabel("\u8FDB\u8D27\u65E5\u671F");
			label.setBounds(22, 198, 54, 15);
			contentPane.add(label);
		}
		{
			textField_4 = new JTextField();
			textField_4.setBounds(101, 195, 223, 21);
			contentPane.add(textField_4);
			textField_4.setColumns(10);
		}
		{
			label_1 = new JLabel("");
			label_1.setBounds(22, 298, 236, 15);
			contentPane.add(label_1);
		}
		this.setVisible(true);
	}
	private JLabel label_1;
}
