//用户登陆类


package classsource;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Land extends JFrame{
	JFrame jf ;

	JTextField textName=new JTextField();
	JPasswordField textage=new JPasswordField();

	JLabel label = new JLabel("员工管理系统");
	JLabel labelName=new JLabel("用户名：");
	JLabel labelage=new JLabel("密码：");


	JButton buttonEnter=new JButton("登录");
	JButton buttoncancel=new JButton("清空");

	public Land(){
		jf=this;
		setTitle("登录");
		Font f = new Font("新宋体",Font.PLAIN,12);
		Container con = getContentPane();
		con.setLayout(null);
		label.setBounds(95,10,110,20);
		label.setFont(new Font("新宋体",Font.PLAIN,14));
		con.add(label);
		labelName.setBounds(45,40,55,20);
		labelName.setFont(f);
		con.add(labelName);
		textName.setBounds(95,40,120,20);
		con.add(textName);

        labelage.setBounds(45,70,45,20);
		con.add(labelage);
		labelage.setFont(f);
     	textage.setBounds(95,70,120,20);
		con.add(textage);



		buttonEnter.setBounds(90,110,60,20);
		buttonEnter.setFont(f);
		con.add(buttonEnter);
		
		//登陆的鼠标监听
		
		buttonEnter.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent me){
				if(textName.getText().equals("")){
					new JOptionPane().showMessageDialog(null,"用户名不能为空!");
					}
				else if(textage.getText().equals("")){
					new JOptionPane().showMessageDialog(null,"密码不能为空!");
					}
				else{
					String sql="select * from UserInformation where User_Name = '" + textName.getText() + "' and Password = '" + textage.getText()+ "'";
					System.out.println(sql);
					Judge(sql);
					}
				}
			});
			
		buttoncancel.setBounds(155,110,60,20);
		buttoncancel.setFont(f);
		con.add(buttoncancel);

		//清空按钮的鼠标监听方法
		buttoncancel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent me){
				textName.setText("");
				textage.setText("");
				}
			});

	    setResizable(false);
	        //窗口图标
	    Image img=Toolkit.getDefaultToolkit().getImage("image\\main.gif");
	    setIconImage(img);
	    Toolkit t = Toolkit.getDefaultToolkit();
	    int w = t.getScreenSize().width;
	    int h = t.getScreenSize().height;
	    setBounds(w/2-150,h/2-90,300,180);
	    setVisible(true);
		}

    private void Judge(String sqlString) {

    if (Database.joinDB()) {
      if (Database.query(sqlString))
         try{
           if(Database.rs.isBeforeFirst()) {
             System.out.println("密码正确");
             jf.setVisible(false);
             //关闭数据库连接
             Database.cn.close();
             new Main();
           }
           else {
             System.out.println("错误");
             new JOptionPane().showMessageDialog(null,"用户名或密码错误!","",JOptionPane.ERROR_MESSAGE);//!!!!!!!!!!!!!!
            }
          }catch(Exception ex) {
             System.out.println(ex.getMessage());
          }
    }
    else{
   	System.out.println("连接数据库不成功!!!");
   	}
  }

	public static void main(String args[]){
		new Land();
		}
	}
