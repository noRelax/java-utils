//�޸�������


package classsource;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;


public class AmendPassword extends JInternalFrame
{
  JLabel lbe1=new JLabel("�޸�����");
  JPanel p=new JPanel();
  public AmendPassword()
  {
  	setTitle("�޸�����");
    p.add(lbe1);
    AmendPanel panel=new AmendPanel();
    Container contentPane=getContentPane();
    contentPane.add(p,"North");
    contentPane.add(panel,"Center");

    setBounds(100, 100, 280, 260);
    this.setClosable(true);
    setVisible(true);
    //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

//  public static void main(String[] args){
//  	new AmendPassword();
//  	}
}

class AmendPanel extends JPanel
{
  JButton b1,b2;
  JLabel lbe2,lbe3,lbe4,lbe5;
  JPasswordField pas1,pas2,pas3;
  JComboBox tf;
  public AmendPanel()
  {
          lbe2=new JLabel("�û���:");
          lbe3=new JLabel("���������:");
          lbe4=new JLabel("����������:");
          lbe5=new JLabel("ȷ��������:");
          tf=new JComboBox();
          pas1=new JPasswordField();
          pas2=new JPasswordField();
          pas3=new JPasswordField();
          b1=new JButton("ȷ��");
          b2=new JButton("���");

         //������������ݴ�����
          add(lbe2);
          lbe2.setBounds(16,10,90,25);
          this.add(tf);
          tf.setBounds(100,10,120,25);
          add(lbe3);
          lbe3.setBounds(16,45,90,25);
          add(pas1);
          pas1.setBounds(100,45,120,25);
          add(lbe4);
          lbe4.setBounds(16,80,80,25);
          add(pas2);
          pas2.setBounds(100,80,120,25);
          add(lbe5);
          lbe5.setBounds(16,115,80,25);
          add(pas3);
          pas3.setBounds(100,115,120,25);
          add(b1);
          b1.setBounds(100,160,60,30);
          add(b2);
          b2.setBounds(160,160,60,30);
          setLayout(null);

//���������û���������
         Database.joinDB();
         String sql="select * from UserInformation";
         try{
         	if(Database.query(sql)){
         		while(Database.rs.next()){
         			String name=Database.rs.getString("User_Name");
         			tf.addItem(name);
         			}
         		}
         	}
         catch(Exception e){}

//Ϊȷ��ȡ����ť���¼�
         b1.addActionListener(new ActionListener(){
         	public void actionPerformed(ActionEvent e){

         		String name="" + tf.getSelectedItem();
         		System.out.println(name);
         		String sql="select * from UserInformation where User_Name='"+ name +"'";
         		System.out.println(sql);
         		try{
         		   if(Database.query(sql)){
         		   	    Database.rs.next();
	         			String ps1=pas1.getText();
	         			String password=Database.rs.getString("Password");
	         			if(ps1.equals(password)){
	         				if(pas2.getText().equals(pas3.getText())){
	         					String supdate="update UserInformation set Password='"+ pas3.getText()+"' where User_Name='"+ name +"'";
	         					Database.executeSQL(supdate);
	         					new JOptionPane().showMessageDialog(null,"������ĳɹ���");
	         					}
	         				else{
	         					new JOptionPane().showMessageDialog(null,"�������벻ͬ��");
	         					}
	         				}
	         			else{
	         				new JOptionPane().showMessageDialog(null,"�����벻��ȷ��");
	         				}
	         			}
	         	    }
	         	 catch(Exception el){
	         	 	System.out.println(el);
	         	 	}
	         	}
         	});
         b2.addActionListener(new ActionListener(){
         	public void actionPerformed(ActionEvent e){
         		pas1.setText("");
         		pas2.setText("");
         		pas3.setText("");
         		}
         	});
  }
}
