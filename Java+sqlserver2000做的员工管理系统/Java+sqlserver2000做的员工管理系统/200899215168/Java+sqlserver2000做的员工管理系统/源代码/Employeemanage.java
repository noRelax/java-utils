//Ա����Ϣ������


package classsource;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Employeemanage extends JInternalFrame{
	JInternalFrame jif;
    public Employeemanage() {
    	jif=this;
        initComponents();
    }

    private void initComponents() {

    	setTitle("Ա��������Ϣ");
//    	String schoolage[]={"��ר","����","�о���"};
//    	String department[]={"��ѧ��","�г���","���²�","�ƻᲿ"};
//    	String Headship[]={"��ʦ","����","��ѧ����"};
    	String politicsVisage[]={"��Ա","Ⱥ��"};
    	String Estate[]={"��ְ","ͣн��ְ","��ְ"};
    	String Sex[]={"��","Ů"};
    	String Marriage[]={"δ��","�ѻ�","���"};
    	Font f = new Font("������", 0, 14);

        lb1 = new JLabel("Ա��������Ϣ����");
        lb2 = new JLabel("���:");
        lb3 = new JLabel("������ò:");
        lb4 = new JLabel("����״̬:");
        lb5 = new JLabel("�Ա�:");
        lb6 = new JLabel("״̬:");
        lb7 = new JLabel("ְ��:");
        lb8 = new JLabel("��ע:");
        lb9 = new JLabel("����:");
        lb10 = new JLabel("ת��ʱ��:");
        lb11 = new JLabel("���빫˾����:");
        lb12 = new JLabel("��������:");
        lb13 = new JLabel("ѧ��:");
        lb14 = new JLabel("����:");
        txt_number = new JTextField();
        txt_name = new JTextField();
        sex_cb = new JComboBox(Sex);
        theadship = new JTextField();
        tschoolage= new JTextField();
        tdepartment = new JTextField();
        txt_borndate = new JTextField();
        marriage_cb = new JComboBox(Marriage);
        politicsVisage_cb = new JComboBox(politicsVisage);
        estate_cb = new JComboBox(Estate);
        txt_enterdate = new JTextField();
        txt_InDueFormDate = new JTextField();
        remark_ta = new JTextArea();
        save_bt = new JButton("����");
        rm_bt = new JButton(">>");
        lm_bt = new JButton("<<");
        right_bt = new JButton(">|");
        left_bt = new JButton("|<");
        exit_bt = new JButton("�˳�");
        append_bt = new JButton("���");
        delet_bt= new JButton("ɾ��");
        amend_bt = new JButton("�޸�");

        getContentPane().setLayout(null);

        setFont(new Font("����", 1, 24));
        lb1.setBackground(new Color(0, 0, 0));
        lb1.setFont(new Font("������", 1, 24));
        lb1.setForeground(new Color(0, 0, 255));
        getContentPane().add(lb1);
        lb1.setBounds(140, 10, 210, 40);


        lb2.setFont(f);

        getContentPane().add(lb2);
        lb2.setBounds(20, 80, 60, 20);

        lb3.setFont(f);

        getContentPane().add(lb3);
        lb3.setBounds(10, 240, 80, 20);

        lb4.setFont(f);

        getContentPane().add(lb4);
        lb4.setBounds(10, 190, 80, 20);

        lb5.setFont(f);

        getContentPane().add(lb5);
        lb5.setBounds(360, 140, 60, 20);

        lb6.setFont(f);

        getContentPane().add(lb6);
        lb6.setBounds(200, 290, 60, 20);

        lb7.setFont(f);

        getContentPane().add(lb7);
        lb7.setBounds(200, 190, 60, 20);

        lb8.setFont(f);

        getContentPane().add(lb8);
        lb8.setBounds(350, 240, 60, 20);

        lb9.setFont(f);

        getContentPane().add(lb9);
        lb9.setBounds(200, 140, 60, 20);

        lb10.setFont(f);

        getContentPane().add(lb10);
        lb10.setBounds(350, 190, 70, 20);

        lb11.setFont(f);

        getContentPane().add(lb11);
        lb11.setBounds(10, 290, 110, 20);

        lb12.setFont(f);

        getContentPane().add(lb12);
        lb12.setBounds(10, 140, 80, 20);

        lb13.setFont(f);

        getContentPane().add(lb13);
        lb13.setBounds(200, 240, 60, 20);

        lb14.setFont(f);

        getContentPane().add(lb14);
        lb14.setBounds(190, 80, 60, 20);

        getContentPane().add(txt_number);
        txt_number.setBounds(80, 80, 80, 21);

        getContentPane().add(txt_name);
        txt_name.setBounds(250, 80, 80, 20);

        getContentPane().add(sex_cb);
        sex_cb.setFont(f);
        sex_cb.setSelectedIndex(1);
        sex_cb.setBounds(430, 140, 90, 23);

        getContentPane().add(txt_borndate);
        txt_borndate.setBounds(80, 140, 100, 20);  //��������

        getContentPane().add(marriage_cb);
        marriage_cb.setFont(f);
        marriage_cb.setBounds(80, 190, 100, 23);

        getContentPane().add(politicsVisage_cb);
        politicsVisage_cb.setFont(f);
        politicsVisage_cb.setBounds(80, 240, 100, 23);//������ò

        getContentPane().add(estate_cb);
        estate_cb.setFont(f);
        estate_cb.setBounds(250, 290, 90, 23); //״̬

        getContentPane().add(tdepartment);
        tdepartment.setFont(f);
        tdepartment.setBounds(250, 140, 90, 20); //����

        getContentPane().add(theadship);
        theadship.setFont(f);
        theadship.setBounds(250, 190, 90, 20);  //ְ��

        getContentPane().add(tschoolage);
        tschoolage.setFont(f);
        tschoolage.setBounds(250, 240, 90, 20); //ѧ��

        getContentPane().add(txt_enterdate);
        txt_enterdate.setBounds(100, 290, 90, 20); //���빫˾ʱ��

        getContentPane().add(txt_InDueFormDate);
        txt_InDueFormDate.setBounds(430, 190, 100, 20); //ת��ʱ��

        getContentPane().add(remark_ta);         //��ע
        remark_ta.setBounds(350, 260, 190, 53);

        save_bt.setFont(f);

        getContentPane().add(save_bt);
        save_bt.setBounds(20, 390, 70, 25);
        save_bt.setEnabled(false);

        rm_bt.setFont(f);

        getContentPane().add(rm_bt);
        rm_bt.setBounds(260, 350, 70, 25);

        lm_bt.setFont(f);

        getContentPane().add(lm_bt);
        lm_bt.setBounds(150, 350, 70, 25);

        right_bt.setFont(f);

        getContentPane().add(right_bt);
        right_bt.setBounds(370, 350, 70, 25);

        left_bt.setFont(f);
        getContentPane().add(left_bt);
        left_bt.setBounds(50, 350, 70, 25);

        exit_bt.setFont(f);

        getContentPane().add(exit_bt);
        exit_bt.setBounds(410, 390, 70,25);

        append_bt.setFont(f);

        getContentPane().add(append_bt);
        append_bt.setBounds(110, 390, 70, 25);

        delet_bt.setFont(f);

        getContentPane().add(delet_bt);
        delet_bt.setBounds(310, 390, 70, 25);

        amend_bt.setFont(f);
        getContentPane().add(amend_bt);
        amend_bt.setBounds(210, 390, 70, 25);

//�������ݿ�---------------------------------------
        Database.joinDB();

//��ʼ����������----------------------------------------------------------------------------
        String csql="select * from EmployeeInformation";
        try{
            if(Database.query(csql)){
            	Database.rs.next();


            	txt_number.setText("" + Database.rs.getInt("E_Number"));
            	txt_name.setText(Database.rs.getString("E_Name"));
            	if(Database.rs.getString("E_Sex").equals("��")){
            		sex_cb.setSelectedIndex(0);
            		}
            	else{
            		sex_cb.setSelectedIndex(1);
            		}
            		//System.out.println(""+sex_cb.getSelectedItem());
            	txt_borndate.setText(Database.rs.getString("E_BornDate"));
            	tdepartment.setText(Database.rs.getString("E_Department"));

           	if(Database.rs.getString("E_Marriage").equals("δ��")){
            		marriage_cb.setSelectedIndex(0);
            		}
            	else if(Database.rs.getString("E_Marriage").equals("�ѻ�")){
            		marriage_cb.setSelectedIndex(1);
            		}
            	else{
            		marriage_cb.setSelectedIndex(2);
            		}
            		//System.out.println(""+marriage_cb.getSelectedItem());
            	theadship.setText(Database.rs.getString("E_Headship"));
            	txt_InDueFormDate.setText(Database.rs.getString("E_InDueFormDate"));
            	if(Database.rs.getString("E_PoliticsVisage").equals("��Ա")){
            		politicsVisage_cb.setSelectedIndex(0);
            		}
            	else{
            		politicsVisage_cb.setSelectedIndex(1);
            		}

            	tschoolage.setText(Database.rs.getString("E_SchoolAge"));
            	txt_enterdate.setText(Database.rs.getString("E_EnterDate"));
            	if(Database.rs.getString("E_Estate").equals("��ְ")){
            		estate_cb.setSelectedIndex(0);
            		}
            	else if(Database.rs.getString("E_Estate").equals("ͣн��ְ")){
            		estate_cb.setSelectedIndex(1);
            		}
            	else{
            		estate_cb.setSelectedIndex(2);
            		}
            	remark_ta.setText(Database.rs.getString("E_Remark"));
            	}
            	//System.out.println("ok");
        	}
        catch(Exception e){System.out.println(e);};

//--------------------------------------------------------------------------------------------


//Ϊ��һ������һ����ť����¼�----------------------------------------
         //��һ����ť�¼�
         rm_bt.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	try{
            		if(Database.rs.next()){
		            	txt_number.setText("" + Database.rs.getInt("E_Number"));
		            	txt_name.setText(Database.rs.getString("E_Name"));
		            	if(Database.rs.getString("E_Sex").equals("��")){
		            		sex_cb.setSelectedIndex(0);
		            		}
		            	else{
		            		sex_cb.setSelectedIndex(1);
		            		}
		            		//System.out.println(""+sex_cb.getSelectedItem());
		            	txt_borndate.setText(Database.rs.getString("E_BornDate"));
		            	tdepartment.setText(Database.rs.getString("E_Department"));

		           	if(Database.rs.getString("E_Marriage").equals("δ��")){
		            		marriage_cb.setSelectedIndex(0);
		            		}
		            	else if(Database.rs.getString("E_Marriage").equals("�ѻ�")){
		            		marriage_cb.setSelectedIndex(1);
		            		}
		            	else{
		            		marriage_cb.setSelectedIndex(2);
		            		}
		            		//System.out.println(""+marriage_cb.getSelectedItem());
		            	theadship.setText(Database.rs.getString("E_Headship"));
		            	txt_InDueFormDate.setText(Database.rs.getString("E_InDueFormDate"));
		            	if(Database.rs.getString("E_PoliticsVisage").equals("��Ա")){
		            		politicsVisage_cb.setSelectedIndex(0);
		            		}
		            	else{
		            		politicsVisage_cb.setSelectedIndex(1);
		            		}

		            	tschoolage.setText(Database.rs.getString("E_SchoolAge"));
		            	txt_enterdate.setText(Database.rs.getString("E_EnterDate"));
		            	if(Database.rs.getString("E_Estate").equals("��ְ")){
		            		estate_cb.setSelectedIndex(0);
		            		}
		            	else if(Database.rs.getString("E_Estate").equals("ͣн��ְ")){
		            		estate_cb.setSelectedIndex(1);
		            		}
		            	else{
		            		estate_cb.setSelectedIndex(2);
		            		}
		            	remark_ta.setText(Database.rs.getString("E_Remark"));
            			}
            		}
            	catch(Exception erm){
            		System.out.println(erm);
            		}
            	}
         	});

         //��һ����ť�¼�
         lm_bt.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	try{
            		if(Database.rs.previous()){
		            	txt_number.setText("" + Database.rs.getInt("E_Number"));
		            	txt_name.setText(Database.rs.getString("E_Name"));
		            	if(Database.rs.getString("E_Sex").equals("��")){
		            		sex_cb.setSelectedIndex(0);
		            		}
		            	else{
		            		sex_cb.setSelectedIndex(1);
		            		}
		            		//System.out.println(""+sex_cb.getSelectedItem());
		            	txt_borndate.setText(Database.rs.getString("E_BornDate"));
		            	tdepartment.setText(Database.rs.getString("E_Department"));

		           	if(Database.rs.getString("E_Marriage").equals("δ��")){
		            		marriage_cb.setSelectedIndex(0);
		            		}
		            	else if(Database.rs.getString("E_Marriage").equals("�ѻ�")){
		            		marriage_cb.setSelectedIndex(1);
		            		}
		            	else{
		            		marriage_cb.setSelectedIndex(2);
		            		}
		            		//System.out.println(""+marriage_cb.getSelectedItem());
		            	theadship.setText(Database.rs.getString("E_Headship"));
		            	txt_InDueFormDate.setText(Database.rs.getString("E_InDueFormDate"));
		            	if(Database.rs.getString("E_PoliticsVisage").equals("��Ա")){
		            		politicsVisage_cb.setSelectedIndex(0);
		            		}
		            	else{
		            		politicsVisage_cb.setSelectedIndex(1);
		            		}

		            	tschoolage.setText(Database.rs.getString("E_SchoolAge"));
		            	txt_enterdate.setText(Database.rs.getString("E_EnterDate"));
		            	if(Database.rs.getString("E_Estate").equals("��ְ")){
		            		estate_cb.setSelectedIndex(0);
		            		}
		            	else if(Database.rs.getString("E_Estate").equals("ͣн��ְ")){
		            		estate_cb.setSelectedIndex(1);
		            		}
		            	else{
		            		estate_cb.setSelectedIndex(2);
		            		}
		            	remark_ta.setText(Database.rs.getString("E_Remark"));
            			}
            		}
            	catch(Exception erm){
            		System.out.println(erm);
            		}
            	}
         	});

         //��ǰһ����ť�¼�
         left_bt.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	try{
            		if(Database.rs.first()){
		            	txt_number.setText("" + Database.rs.getInt("E_Number"));
		            	txt_name.setText(Database.rs.getString("E_Name"));
		            	if(Database.rs.getString("E_Sex").equals("��")){
		            		sex_cb.setSelectedIndex(0);
		            		}
		            	else{
		            		sex_cb.setSelectedIndex(1);
		            		}
		            		//System.out.println(""+sex_cb.getSelectedItem());
		            	txt_borndate.setText(Database.rs.getString("E_BornDate"));
		            	tdepartment.setText(Database.rs.getString("E_Department"));

		           	if(Database.rs.getString("E_Marriage").equals("δ��")){
		            		marriage_cb.setSelectedIndex(0);
		            		}
		            	else if(Database.rs.getString("E_Marriage").equals("�ѻ�")){
		            		marriage_cb.setSelectedIndex(1);
		            		}
		            	else{
		            		marriage_cb.setSelectedIndex(2);
		            		}
		            		//System.out.println(""+marriage_cb.getSelectedItem());
		            	theadship.setText(Database.rs.getString("E_Headship"));
		            	txt_InDueFormDate.setText(Database.rs.getString("E_InDueFormDate"));
		            	if(Database.rs.getString("E_PoliticsVisage").equals("��Ա")){
		            		politicsVisage_cb.setSelectedIndex(0);
		            		}
		            	else{
		            		politicsVisage_cb.setSelectedIndex(1);
		            		}

		            	tschoolage.setText(Database.rs.getString("E_SchoolAge"));
		            	txt_enterdate.setText(Database.rs.getString("E_EnterDate"));
		            	if(Database.rs.getString("E_Estate").equals("��ְ")){
		            		estate_cb.setSelectedIndex(0);
		            		}
		            	else if(Database.rs.getString("E_Estate").equals("ͣн��ְ")){
		            		estate_cb.setSelectedIndex(1);
		            		}
		            	else{
		            		estate_cb.setSelectedIndex(2);
		            		}
		            	remark_ta.setText(Database.rs.getString("E_Remark"));
            			}
            		}
            	catch(Exception erm){
            		System.out.println(erm);
            		}
            	}
         	});

         right_bt.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	try{
            		if(Database.rs.last()){
		            	txt_number.setText("" + Database.rs.getInt("E_Number"));
		            	txt_name.setText(Database.rs.getString("E_Name"));
		            	if(Database.rs.getString("E_Sex").equals("��")){
		            		sex_cb.setSelectedIndex(0);
		            		}
		            	else{
		            		sex_cb.setSelectedIndex(1);
		            		}
		            		//System.out.println(""+sex_cb.getSelectedItem());
		            	txt_borndate.setText(Database.rs.getString("E_BornDate"));
		            	tdepartment.setText(Database.rs.getString("E_Department"));

		           	if(Database.rs.getString("E_Marriage").equals("δ��")){
		            		marriage_cb.setSelectedIndex(0);
		            		}
		            	else if(Database.rs.getString("E_Marriage").equals("�ѻ�")){
		            		marriage_cb.setSelectedIndex(1);
		            		}
		            	else{
		            		marriage_cb.setSelectedIndex(2);
		            		}
		            		//System.out.println(""+marriage_cb.getSelectedItem());
		            	theadship.setText(Database.rs.getString("E_Headship"));
		            	txt_InDueFormDate.setText(Database.rs.getString("E_InDueFormDate"));
		            	if(Database.rs.getString("E_PoliticsVisage").equals("��Ա")){
		            		politicsVisage_cb.setSelectedIndex(0);
		            		}
		            	else{
		            		politicsVisage_cb.setSelectedIndex(1);
		            		}

		            	tschoolage.setText(Database.rs.getString("E_SchoolAge"));
		            	txt_enterdate.setText(Database.rs.getString("E_EnterDate"));
		            	if(Database.rs.getString("E_Estate").equals("��ְ")){
		            		estate_cb.setSelectedIndex(0);
		            		}
		            	else if(Database.rs.getString("E_Estate").equals("ͣн��ְ")){
		            		estate_cb.setSelectedIndex(1);
		            		}
		            	else{
		            		estate_cb.setSelectedIndex(2);
		            		}
		            	remark_ta.setText(Database.rs.getString("E_Remark"));
            			}
            		}
            	catch(Exception erm){
            		System.out.println(erm);
            		}
            	}
         	});

//---------------------------------------------------------------------

//Ϊ��ӱ��水ť���¼�-----------------------------------------------------
    append_bt.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
       	        save_bt.setEnabled(true);
                txt_number.setText("");
                txt_number.setEditable(false);
            	txt_name.setText("");
            	sex_cb.setSelectedIndex(0);
            	txt_borndate.setText("");
            	tdepartment.setText("");
            	marriage_cb.setSelectedIndex(0);
            	theadship.setText("");
            	txt_InDueFormDate.setText("");
            	politicsVisage_cb.setSelectedIndex(0);
            	tschoolage.setText("");
            	txt_enterdate.setText("");
            	estate_cb.setSelectedIndex(0);
            	remark_ta.setText("");
     		}
     	});

     	save_bt.addActionListener(new ActionListener(){
     		public void actionPerformed(ActionEvent e){
     			if(txt_name.getText().equals("")||txt_borndate.getText().equals("")||tdepartment.getText().equals("")
     			    ||theadship.getText().equals("")||txt_InDueFormDate.getText().equals("")||tschoolage.getText().equals("")
                    ||txt_enterdate.getText().equals("")){
                    	new JOptionPane().showMessageDialog(null,"����ע�⣬�������ݾ�����Ϊ�գ�");
                    	}
                else{
                	String name=txt_name.getText();
                	String borndate=txt_borndate.getText();
                	String department=tdepartment.getText();
                	String headship=theadship.getText();
                	String indueformdate=txt_InDueFormDate.getText();
                	String schoolage=tschoolage.getText();
                	String enterdate=txt_enterdate.getText();
                	String remark=remark_ta.getText();

                	String sex=("" + sex_cb.getSelectedItem());
                	String marriage=(""+marriage_cb.getSelectedItem());
                	String estate=("" + estate_cb.getSelectedItem());
                	String politicsVisage=("" + politicsVisage_cb.getSelectedItem());

                	String sInsert="insert EmployeeInformation values('"+ name +"','"+ sex +"','"+  borndate+"',"+
                	               "'"+ marriage +"','"+ politicsVisage +"','"+ schoolage +"','"+ enterdate +"','"+ indueformdate +"',"+
                	               "'"+ department +"','"+ headship +"','"+  estate +"','"+ remark +"')";
                	//System.out.println(sInsert);

                	try{
                		if(Database.executeSQL(sInsert)){
                			txt_number.setEditable(true);
                			save_bt.setEnabled(false);
                			new JOptionPane().showMessageDialog(null,"������ݳɹ���");

                			Database.joinDB();
                			String sql="select * from EmployeeInformation";
                			Database.query(sql);
                			Database.rs.last();
                			txt_number.setText("" + Database.rs.getInt("E_Number"));
                			}
                		}
                	catch(Exception einsert){
                		System.out.println(einsert);
                		}
                	}
     			}
     		});
//------------------------------------------------------------------------

//Ϊ�޸�ɾ����ť����¼�-------------------------------------------------------------------
     amend_bt.addActionListener(new ActionListener(){
     	public void actionPerformed(ActionEvent e){

           	String name=txt_name.getText();
        	String borndate=txt_borndate.getText();
        	String department=tdepartment.getText();
        	String headship=theadship.getText();
        	String indueformdate=txt_InDueFormDate.getText();
        	String schoolage=tschoolage.getText();
        	String enterdate=txt_enterdate.getText();
        	String remark=remark_ta.getText();

        	String sex=("" + sex_cb.getSelectedItem());
        	String marriage=(""+marriage_cb.getSelectedItem());
        	String estate=("" + estate_cb.getSelectedItem());
        	String politicsVisage=("" + politicsVisage_cb.getSelectedItem());

     		String supdate="update EmployeeInformation set E_Name ='"+ name +"',E_Sex='"+ sex +"'," +
     		               "E_BornDate='"+ borndate +"',E_Marriage='"+ marriage +"',E_PoliticsVisage='"+ politicsVisage +"'," +
     		               "E_SchoolAge='"+ schoolage+"',E_EnterDate='"+ enterdate +"',E_InDueFormDate='"+ indueformdate +"',"+
     		               "E_Department='"+ department +"',E_Headship='"+ headship +"',E_Estate='"+ estate +"'," +
     		               "E_Remark='"+ remark +"' where E_Number='"+ txt_number.getText() +"'";

     		System.out.println(supdate);
     		try{
     	     	if(Database.executeSQL(supdate)){
   					new JOptionPane().showMessageDialog(null,"�����޸ĳɹ���");
                        //System.out.println("supdate");
   					Database.joinDB();
   	  				String sqll="select * from EmployeeInformation";
  	  				Database.query(sqll);
   				    }
     			}
     		catch(Exception eupdate){}
     		}
     	});

     delet_bt.addActionListener(new ActionListener(){
     	public void actionPerformed(ActionEvent e){
     		String sdelete = "delete from EmployeeInformation where E_Number ='"+ txt_number.getText()+"'";
     		try{
     			if(Database.executeSQL(sdelete)){
     				new JOptionPane().showMessageDialog(null,"����ɾ���ɹ���");

	     		    String sql="select * from EmployeeInformation";
	     		    Database.query(sql);
	     		    Database.rs.next();


	            	txt_number.setText("" + Database.rs.getInt("E_Number"));
	            	txt_name.setText(Database.rs.getString("E_Name"));
	            	if(Database.rs.getString("E_Sex").equals("��")){
	            		sex_cb.setSelectedIndex(0);
	            		}
	            	else{
	            		sex_cb.setSelectedIndex(1);
	            		}
	            		//System.out.println(""+sex_cb.getSelectedItem());
	            	txt_borndate.setText(Database.rs.getString("E_BornDate"));
	            	tdepartment.setText(Database.rs.getString("E_Department"));

	           	if(Database.rs.getString("E_Marriage").equals("δ��")){
	            		marriage_cb.setSelectedIndex(0);
	            		}
	            	else if(Database.rs.getString("E_Marriage").equals("�ѻ�")){
	            		marriage_cb.setSelectedIndex(1);
	            		}
	            	else{
	            		marriage_cb.setSelectedIndex(2);
	            		}
	            		//System.out.println(""+marriage_cb.getSelectedItem());
	            	theadship.setText(Database.rs.getString("E_Headship"));
	            	txt_InDueFormDate.setText(Database.rs.getString("E_InDueFormDate"));
	            	if(Database.rs.getString("E_PoliticsVisage").equals("��Ա")){
	            		politicsVisage_cb.setSelectedIndex(0);
	            		}
	            	else{
	            		politicsVisage_cb.setSelectedIndex(1);
	            		}

	            	tschoolage.setText(Database.rs.getString("E_SchoolAge"));
	            	txt_enterdate.setText(Database.rs.getString("E_EnterDate"));
	            	if(Database.rs.getString("E_Estate").equals("��ְ")){
	            		estate_cb.setSelectedIndex(0);
	            		}
	            	else if(Database.rs.getString("E_Estate").equals("ͣн��ְ")){
	            		estate_cb.setSelectedIndex(1);
	            		}
	            	else{
	            		estate_cb.setSelectedIndex(2);
	            		}
	            	remark_ta.setText(Database.rs.getString("E_Remark"));
	     				}
     			}
     		catch(Exception er){
     			System.out.println(er);
     			}
     		}
     	});

     exit_bt.addActionListener(new ActionListener(){
     	public void actionPerformed(ActionEvent e){
     		jif.setVisible(false);
     		}
     	});
//--------------------------------------------------------------------------------------------

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-558)/2, (screenSize.height-477)/2, 558, 455);
        this.setClosable(true);
        this.setMaximizable(true);
        setVisible(true);
    }
//   public static void main(String args[]) {
//
//         new Employeemanage();
//
//    }

    private JButton save_bt;
    private JButton rm_bt;
    private JButton lm_bt;
    private JButton right_bt;
    private JButton left_bt;
    private JButton exit_bt;
    private JButton append_bt;
    private JButton delet_bt;
    private JButton amend_bt;
    private JComboBox sex_cb;
    private JComboBox marriage_cb;
    private JComboBox politicsVisage_cb;
    private JComboBox estate_cb;
    private JFrame jFrame1;
    private JLabel lb1;
    private JLabel lb10;
    private JLabel lb11;
    private JLabel lb12;
    private JLabel lb13;
    private JLabel lb14;
    private JLabel lb2;
    private JLabel lb3;
    private JLabel lb4;
    private JLabel lb5;
    private JLabel lb6;
    private JLabel lb7;
    private JLabel lb8;
    private JLabel lb9;
    private JList headship_ls;
    private JList schoolage_ls;
    private JList department_ls;
    private JTextArea remark_ta;
    private JTextField txt_number;
    private JTextField txt_name;
    private JTextField txt_borndate;
    private JTextField txt_enterdate;
    private JTextField txt_InDueFormDate;
    private JTextField theadship;
    private JTextField tschoolage;
    private JTextField tdepartment;


}
