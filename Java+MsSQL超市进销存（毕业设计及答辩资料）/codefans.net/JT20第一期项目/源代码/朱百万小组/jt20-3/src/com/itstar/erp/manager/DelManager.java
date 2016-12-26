package com.itstar.erp.manager;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

import com.itstar.erp.dao.Admin;
import com.itstar.erp.dao.AdminImpl;


@SuppressWarnings("serial")
public class DelManager extends JFrame {

//	private JFrame delManager = null; 
	private JPanel full = null;
	private JLabel condition1 = null;
	private JTextField name = null;
	private JButton btOk = null;
	private JLabel back=null;
	public DelManager() {
		super();
		initialize();
		this.setResizable(false);
		this.setVisible(true);
	}
	private void initialize() {
			this.setTitle("删除管理员");
			this.setSize(new Dimension(369, 169));
			this.setLocation(377, 200);
			this.setContentPane(getJContentPane());
			full.setLayout(null);
			this.setResizable(false);
			
			addControls();
			}

	private void addControls() {
			condition1 = new JLabel();
			condition1.setText("请输入要删除的管理员的名字");
			condition1.setFont(new Font("Dialog", Font.BOLD, 18));
			condition1.setBounds(new Rectangle(53, 17, 257, 26));
			full.add(condition1, null);
			

			name = new JTextField();
			name.setBounds(new Rectangle(106, 58, 134, 22));
			full.add(name, null);
			
			btOk = new JButton();
			btOk.setBounds(new Rectangle(137, 96, 67, 23));
			btOk.setText("提交");
			btOk.addActionListener(new ActionListener(){
              public void actionPerformed(ActionEvent e) {
            	  Admin admin=new Admin();
            	  String str=name.getText();
            	  if("".equals(str)){ JOptionPane.showMessageDialog(full, "不能输入为空!"); }
            	  else if("zhubaiwan".equals(str)) { JOptionPane.showMessageDialog(full, "超级管理员不能删除!");
            	  name.setText("");name.requestFocus();}
            	  else if("zhangli".equals(str)){JOptionPane.showMessageDialog(full, "高级管理员不能删除!");
            	  name.setText("");name.requestFocus();}
            	  else {
            		  admin.setUserid(str);
				       if(new AdminImpl().findAdmin(admin)){
					  new AdminImpl().delAdmin(admin);
					  JOptionPane.showMessageDialog(full, "管理员删除成功!");}
				       else{
					  JOptionPane.showMessageDialog(full, "所查询的管理员不存在!");
				  }
            	  }
				}
				
			});
			full.add(btOk,null);
			
			ImageIcon img = new ImageIcon("src/LoginFrame/zhu.jpg");
			back=new JLabel(img);
			back.setBounds(0, 0, 369, 169);
			
			full.add(back,null);
		}

	private JPanel getJContentPane() {
		if (full == null) {
          full = new JPanel();
		}
		return full;
   }
   }  
