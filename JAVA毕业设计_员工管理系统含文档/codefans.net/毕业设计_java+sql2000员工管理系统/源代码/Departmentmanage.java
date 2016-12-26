//���Ź�����



package classsource;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Departmentmanage extends JInternalFrame{

	JLabel lb1 = new JLabel("�� �� �� Ϣ");
	JLabel lb2 = new JLabel("���ű�ţ�");
	JLabel lb3 = new JLabel("�������ƣ�");
	JLabel lb4 = new JLabel("����������");
	JTextField tnumber = new JTextField();
	JTextField tname = new JTextField();
	JTextField tcount = new JTextField();
	JButton btright = new JButton("<<");
    JButton btleft = new JButton(">>");
    JButton btdelet = new JButton("ɾ��");
    JButton btamend = new JButton("�޸�");
    JButton btsave = new JButton("����");
    JButton btadd = new JButton("���");
	public Departmentmanage(){

		setTitle("������Ϣ����!");
        btsave.setEnabled(false);

		getContentPane().setLayout(null);
		Font f = new Font("������",0,14);
		btleft.setFont(f);

		lb1.setFont(new java.awt.Font("������", 1, 18));
        lb1.setForeground(new Color(0, 51, 255));
        getContentPane().add(lb1);
        lb1.setBounds(110, 20, 160, 30);

        lb2.setFont(f);
        getContentPane().add(lb2);
        lb2.setBounds(40, 85, 70, 20);
        lb3.setFont(f);
        getContentPane().add(lb3);
        lb3.setBounds(40, 125, 70, 20);
        lb4.setFont(f);
        getContentPane().add(lb4);
        lb4.setBounds(40,165,70,20);

        getContentPane().add(tnumber);
        tnumber.setBounds(155,85,100,23);
        getContentPane().add(tname);
        tname.setBounds(155,125,120,23);
        getContentPane().add(tcount);
        tcount.setBounds(155,165,120,23);

        btright.setFont(f);
		getContentPane().add(btright);
        btright.setBounds(330, 250, 50, 20);

        btleft.setFont(f);
        getContentPane().add(btleft);
        btleft.setBounds(330, 230, 50, 20);


        btdelet.setFont(f);
        getContentPane().add(btdelet);
        btdelet.setBounds(250, 240, 70, 25);

        btamend.setFont(f);
        getContentPane().add(btamend);
        btamend.setBounds(170, 240, 70, 25);

        btsave.setFont(f);
        getContentPane().add(btsave);
        btsave.setBounds(10, 240, 70, 25);

        btadd.setFont(f);
        getContentPane().add(btadd);
        btadd.setBounds(90, 240, 70, 25);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-403)/2, (screenSize.height-329)/2, 403, 329);

        //�������ݿ�-------
        Database.joinDB();

//��ʼ����������-------------------------------------------------------
        String sqlc="select * from DepartmentInformation";
        try{
        if(Database.query(sqlc)){
        	//System.out.println("ok");
        	Database.rs.next();
        	String number=("" + Database.rs.getInt("D_Number"));
        	//System.out.println(number);
        	String name=Database.rs.getString("D_Name");
        	String count=Database.rs.getString("D_Count");
        	tnumber.setText(number);
        	tname.setText(name);
        	tcount.setText(count);
        	}
          }
         catch(Exception e){
         	System.out.println(e);
         	}

//-------------------------------------------------------------------

//Ϊ���Ұ�ť���¼�--------------------------------------------------
     btright.addActionListener(new ActionListener(){
     	public void actionPerformed(ActionEvent e){
     		try{
     		if(Database.rs.previous()){
     		   String number=("" + Database.rs.getInt("D_Number"));
        	   //System.out.println(number);
        	   String name=Database.rs.getString("D_Name");
        	   String count=Database.rs.getString("D_Count");
        	   tnumber.setEditable(true);
        	   tnumber.setText(number);
        	   tname.setText(name);
        	   tcount.setText(count);
     			}
     		  }
     		 catch(Exception el){
     		 	System.out.println(el);
     		 	}
     		}
     	});


     btleft.addActionListener(new ActionListener(){
     	public void actionPerformed(ActionEvent e){
     		try{
     		if(Database.rs.next()){
     		   String number=("" + Database.rs.getInt("D_Number"));
        	   //System.out.println(number);
        	   String name=Database.rs.getString("D_Name");
        	   String count=Database.rs.getString("D_Count");
        	   tnumber.setEditable(true);
        	   tnumber.setText(number);
        	   tname.setText(name);
        	   tcount.setText(count);
     			}
     		  }
     		 catch(Exception er){
     		 	System.out.println(er);
     		 	}
     		}
     	});


//-----------------------------------------------------------------


//Ϊ���ɾ�������޸İ�ť���¼�----------------------------------------
    btadd.addActionListener(new ActionListener(){
    	public void actionPerformed(ActionEvent e){
    		btsave.setEnabled(true);
    		tnumber.setText("");
    		tnumber.setEditable(false);
        	tname.setText("");
        	tcount.setText("");
    		}
    	});
   btsave.addActionListener(new ActionListener(){
   	  public void actionPerformed(ActionEvent e){
   	  	if(tname.getText().equals("") || tcount.getText().equals("")){
   	  		new JOptionPane().showMessageDialog(null,"�������Ͳ�������������Ϊ�գ�");
   	  		}
   	  	else{
   	  		String name=tname.getText();
   	  		String count=tcount.getText();
   	  		String sInsert = "insert DepartmentInformation values('"+ name +"','"+ count +"')";
   	  		try{
   	  			if(Database.executeSQL(sInsert)){
   	  				tnumber.setEditable(true);
   	  				btsave.setEnabled(false);
   	  				new JOptionPane().showMessageDialog(null,"������ݳɹ���");
   	  				Database.joinDB();
   	  				String sql="select * from DepartmentInformation";
   	  				Database.query(sql);
   	  				Database.rs.last();
   	  				    String number1=("" + Database.rs.getInt("D_Number"));
        	           //System.out.println(number);
        	            String name1=Database.rs.getString("D_Name");
        	            String count1=Database.rs.getString("D_Count");
        	            tnumber.setText(number1);
        	            tname.setText(name1);
        	            tcount.setText(count1);
   	  				}

   	  			else{
   	  				new JOptionPane().showMessageDialog(null,"������ݲ��ɹ���");
   	  				}
   	  			}
   	  		catch(Exception ei){
   	  			new JOptionPane().showMessageDialog(null,"������ݲ��ɹ���");
   	  			}
   	  		}
   	  	}
   	});

   	btdelet.addActionListener(new ActionListener(){
   		public void actionPerformed(ActionEvent e){
   			try{
   				String sql="delete from DepartmentInformation where D_Number ='"+ tnumber.getText()+"'";
   				System.out.println(sql);
   				if(Database.executeSQL(sql)){
   					new JOptionPane().showMessageDialog(null,"����ɾ���ɹ���");
   					Database.joinDB();
   	  				String sqll="select * from DepartmentInformation";
   	  				Database.query(sqll);
   	  				Database.rs.last();
   	  				    String number1=("" + Database.rs.getInt("D_Number"));
        	           //System.out.println(number);
        	            String name1=Database.rs.getString("D_Name");
        	            String count1=Database.rs.getString("D_Count");
        	            tnumber.setText(number1);
        	            tname.setText(name1);
        	            tcount.setText(count1);
   					}
   				}
   			catch(Exception el){}
   			}
   		});

   	btamend.addActionListener(new ActionListener(){
   		public void actionPerformed(ActionEvent e){
   			try{
   				String supdate="update DepartmentInformation set D_Name='"+ tname.getText() +"',D_count='"+ tcount.getText()+"' where D_Number='"+ tnumber.getText()+"'";

   				if(Database.executeSQL(supdate)){
   					new JOptionPane().showMessageDialog(null,"�����޸ĳɹ���");
   					Database.joinDB();
   	  				String sqll="select * from DepartmentInformation";
   	  				Database.query(sqll);
   	  				Database.rs.last();
   	  				    String number1=("" + Database.rs.getInt("D_Number"));
        	           //System.out.println(number);
        	            String name1=Database.rs.getString("D_Name");
        	            String count1=Database.rs.getString("D_Count");
        	            tnumber.setText(number1);
        	            tname.setText(name1);
        	            tcount.setText(count1);
   					}
   				}
   				catch(Exception es){}
   			}
   		});
//-----------------------------------------------------------------
        this.setClosable(true);
        setVisible(true);

	}
//	public static void main(String args[]){

//		new Departmentmanage();
//	}
}
