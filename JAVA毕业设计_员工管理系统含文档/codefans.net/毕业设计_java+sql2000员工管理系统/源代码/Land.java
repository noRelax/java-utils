//�û���½��


package classsource;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Land extends JFrame{
	JFrame jf ;

	JTextField textName=new JTextField();
	JPasswordField textage=new JPasswordField();

	JLabel label = new JLabel("Ա������ϵͳ");
	JLabel labelName=new JLabel("�û�����");
	JLabel labelage=new JLabel("���룺");


	JButton buttonEnter=new JButton("��¼");
	JButton buttoncancel=new JButton("���");

	public Land(){
		jf=this;
		setTitle("��¼");
		Font f = new Font("������",Font.PLAIN,12);
		Container con = getContentPane();
		con.setLayout(null);
		label.setBounds(95,10,110,20);
		label.setFont(new Font("������",Font.PLAIN,14));
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
		
		//��½��������
		
		buttonEnter.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent me){
				if(textName.getText().equals("")){
					new JOptionPane().showMessageDialog(null,"�û�������Ϊ��!");
					}
				else if(textage.getText().equals("")){
					new JOptionPane().showMessageDialog(null,"���벻��Ϊ��!");
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

		//��հ�ť������������
		buttoncancel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent me){
				textName.setText("");
				textage.setText("");
				}
			});

	    setResizable(false);
	        //����ͼ��
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
             System.out.println("������ȷ");
             jf.setVisible(false);
             //�ر����ݿ�����
             Database.cn.close();
             new Main();
           }
           else {
             System.out.println("����");
             new JOptionPane().showMessageDialog(null,"�û������������!","",JOptionPane.ERROR_MESSAGE);//!!!!!!!!!!!!!!
            }
          }catch(Exception ex) {
             System.out.println(ex.getMessage());
          }
    }
    else{
   	System.out.println("�������ݿⲻ�ɹ�!!!");
   	}
  }

	public static void main(String args[]){
		new Land();
		}
	}
