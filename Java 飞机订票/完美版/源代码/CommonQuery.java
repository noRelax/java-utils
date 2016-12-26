package flight.query;

import flight.assist.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
import java.sql.*;

public class CommonQuery extends JPanel implements ActionListener,ItemListener
{
    static SqlBean sqlBean = new SqlBean();
    
	private JTextField flightField = new JTextField(8);	
	
	private static DefaultComboBoxModel modelFlight = new DefaultComboBoxModel();
	private static JComboBox jcbFlight = new JComboBox(modelFlight);
	
	private JButton jbFlightQuery = new JButton("��ѯ");
	
	
	   //********************************************************************
	private static DefaultComboBoxModel modelAirFirm = new DefaultComboBoxModel();
	private static JComboBox jcbAirFirm = new JComboBox(modelAirFirm);
	
	private JButton jbAirFirmQuery = new JButton("��ѯ");
	
	
       //********************************************************************	
	private static DefaultComboBoxModel model_1 = new DefaultComboBoxModel();
	private static DefaultComboBoxModel modelStart = new DefaultComboBoxModel();
	private static DefaultComboBoxModel modelArrive = new DefaultComboBoxModel();
	private static JComboBox jcb1 = new JComboBox(model_1),
	                         jcbStart = new JComboBox(modelStart),
	                         jcbArrive = new JComboBox(modelArrive);
	                  
    private JButton jbPlaceQuery1 = new JButton("��ѯ"),
                    jbPlaceQuery2 = new JButton("��ѯ"); 
         
     
       //Used to store the flightNumber you have choosed
	private String flightNumber;
	   //Used to store the item you choosed inth combobox
	private String airfirm;
	   //Used to the items choosed from each combobox
    private String destination,start,arrive;
                      
    public CommonQuery()
    {
    	JPanel jpFlight = new JPanel();
    	jpFlight.setBorder(new TitledBorder("������Ų�ѯ"));
    	jpFlight.add(new JLabel("�����뺽��Ż���б�ѡ��:"));
    	jpFlight.add(flightField);
    	jpFlight.add(jcbFlight);
    	jpFlight.add(jbFlightQuery);
    	
    	JPanel jpAirFirm = new JPanel();
    	jpAirFirm.setBorder(new TitledBorder("�����չ�˾��ѯ"));
    	jpAirFirm.add(new JLabel("��ѡ������Ҫ��ѯ�ĺ��չ�˾����:"));
    	jpAirFirm.add(jcbAirFirm);
    	jpAirFirm.add(jbAirFirmQuery);
    	
    	    	
    	JPanel jp1 = new JPanel();
    	jp1.add(new JLabel("��ѡ������Ҫ�����Ŀ�ĵ�:"));
    	jp1.add(jcb1);
    	jp1.add(new JLabel("    "));
    	jp1.add(jbPlaceQuery1);
    	
    	JPanel jpPlace1 = new JPanel();
    	jpPlace1.setLayout(new BorderLayout());
    	jpPlace1.add(new JLabel("��ѯ����һ:"),BorderLayout.NORTH);
    	jpPlace1.add(jp1,BorderLayout.CENTER);
    	
    	JPanel jp2 = new JPanel(); 
    	jp2.add(new JLabel("��������:"));
    	jp2.add(jcbStart);
    	jp2.add(new JLabel("�ִ����:"));
    	jp2.add(jcbArrive);
    	jp2.add(jbPlaceQuery2);
    	
    	JPanel jpPlace2 = new JPanel();
    	jpPlace2.setLayout(new BorderLayout());
    	jpPlace2.add(new JLabel("��ѯ������:"),BorderLayout.NORTH);
    	jpPlace2.add(new JLabel("��ѡ����ʼ���к͵ִ���н��в�ѯ:"),BorderLayout.CENTER);
    	jpPlace2.add(jp2,BorderLayout.SOUTH);
    	
    	
    	JPanel jpDestin = new JPanel();
    	jpDestin.setBorder(new TitledBorder("��Ŀ�ĵز�ѯ"));
    	jpDestin.setLayout(new BorderLayout());
    	jpDestin.add(jpPlace1,BorderLayout.NORTH);
    	jpDestin.add(jpPlace2,BorderLayout.CENTER);
    	
    	this.setLayout(new BorderLayout());
    	this.add(jpFlight,BorderLayout.NORTH);
    	this.add(jpAirFirm,BorderLayout.CENTER);
    	this.add(jpDestin,BorderLayout.SOUTH);
    	
    	jcbFlight.addItemListener(this);
    	
    	jbFlightQuery.addActionListener(this);
    	jbAirFirmQuery.addActionListener(this);
    	jbPlaceQuery1.addActionListener(this);
    	jbPlaceQuery2.addActionListener(this);
    	
    }
    
    public static void updateFlightComboBox(String newFlightNum,int insertOrDelete)
	{
		if (insertOrDelete == 1)
		{
			if (modelFlight.getIndexOf(newFlightNum) == -1)
			   modelFlight.addElement(newFlightNum);
		}  
		else if (insertOrDelete == 2)
		{
			if (modelFlight.getIndexOf(newFlightNum) != -1)
			   modelFlight.removeElement(newFlightNum);			
		}		   
	}
	
	public static void updateAirFirmComboBox(String newPlace,int insertOrDelete)
	{
		if (insertOrDelete == 1)
		{
			if (modelAirFirm.getIndexOf(newPlace) == -1)
			   jcbAirFirm.addItem(newPlace);
		}
		   
		else if (insertOrDelete == 2)
		{
			if (modelAirFirm.getIndexOf(newPlace) != -1)
			   jcbAirFirm.removeItem(newPlace);
		}		   
	}
	
	public static void updatePlaceComboBox(String newPlace,int insertOrDelete)
    {
    	if (insertOrDelete == 1)
    	{
    		if (model_1.getIndexOf(newPlace) == -1)
	    	   jcb1.addItem(newPlace);
	    	 
	    	if (modelStart.getIndexOf(newPlace) == -1)
	    	   jcbStart.addItem(newPlace);
	    	   
	    	if (modelArrive.getIndexOf(newPlace) == -1)
	    	   jcbArrive.addItem(newPlace);	
    	}
    	else if (insertOrDelete == 2)
    	{
    		if (model_1.getIndexOf(newPlace) != -1)
    		   jcb1.removeItem(newPlace);
    		
    		if (modelStart.getIndexOf(newPlace) != -1)
    	       jcbStart.removeItem(newPlace);
    		
    		if (modelArrive.getIndexOf(newPlace) != -1)
    		   jcbArrive.removeItem(newPlace);
    	}    	
    }    
    
    public void itemStateChanged(ItemEvent e)
    {
    	if (e.getSource() == jcbFlight)
    	{
    		flightField.setText( (String)jcbFlight.getSelectedItem() );
    	}
    }
    
    public void actionPerformed(ActionEvent e)
    {
    	if (e.getSource() == jbFlightQuery)
    	{
    		this.flightNumber = flightField.getText().trim();	
		
			   //Haven't enter anything
			if (flightNumber.length() == 0)
			{
				JOptionPane.showMessageDialog(null,"�����뺽��Ż��ߴ��б���ѡ��",
				                              "������Ϣ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			   //Do the query work
			executeFlightQuery();
    	}
    	
    	else if (e.getSource() == jbAirFirmQuery)
    	{
    		this.airfirm = (String)jcbAirFirm.getSelectedItem();
		
			   //Do the query work
			executeAirFirmQuery();
    	}
    	
    	else if (e.getSource() == jbPlaceQuery1)
    	{
    		   //Get the destination you want to query
    		destination = (String)jcb1.getSelectedItem();
    		   //Trim the space at the side of the string
    		destination = destination.trim();
    		
    		   //Do the query work,and diliver the flag=1,means that 
    		   //it is for the first kind of query
    		executeDestinQuery(1);
    	}
    	
    	else if (e.getSource() == jbPlaceQuery2)
    	{
    		   //Get the start place
    		start = (String)jcbStart.getSelectedItem();
    		start = start.trim();
    		   //Get the destionation
    		arrive = (String)jcbArrive.getSelectedItem();
    		arrive = arrive.trim();
    		
    		   //Do the query work,and diliver the flag=2,means that 
    		   //it is for the second kind of query
    		executeDestinQuery(2);
    	}    	
    }
    
    public void executeFlightQuery()
	{
		   //Form the sqlString 
		String sqlString = "SELECT DISTINCT * FROM " +
		                   "flight " +
		                   "WHERE flight=" + "\'" + flightNumber + "\'";
	
	    ResultSet rs = sqlBean.executeQuery(sqlString);		        
	
	    if (rs != null)
	    {
	    	   //Form result string
	        String result = "                                                    " + 
			                "����Ų�ѯ"; 
			result += "��ѯ�ĺ����:" + flightNumber + "\n";
			   //Form the specific result string according the message you give       
	        result += formResult(rs);    	
	        
	           //Display result in a dialog
	        showResult(result);
	    }	     
	    else 
	       JOptionPane.showMessageDialog(null,"û�����������ݿ�!",
	                                    "������Ϣ",JOptionPane.ERROR_MESSAGE);
	}
	
	public void executeAirFirmQuery()
	{
		   //Form the sqlString
		String sqlString = "SELECT DISTINCT * FROM " +
		                   "flight " +
		                   "WHERE airfirm=" + "\'" + airfirm + "\'";
	                  
	    ResultSet rs = sqlBean.executeQuery(sqlString);
	     
	    if (rs != null)
        {
        	   //Form result string
	        String result = "                                                    " + 
			                "���չ�˾��ѯ"; 
			result += "��ѯ�ĺ��չ�˾:" + airfirm + "\n";
			   //Form the specific result string according the message you give       
	        result += formResult(rs);    	
	        
	           //Display result in a dialog
	        showResult(result);
        }	       
	    else 
	       JOptionPane.showMessageDialog(null,"û�����������ݿ�!",
	                                    "������Ϣ",JOptionPane.ERROR_MESSAGE);
	}
	
	public void executeDestinQuery(int flag)
    {
    	   //Form the SQL string
    	String sqlString = "SELECT DISTINCT * FROM " + "flight ";
    	
    	   //The SQL string is different for the two kinds of query
    	if (flag == 1)
    	   sqlString += "WHERE destination=" + "\'" + destination + "\'";
    	else 
    	   sqlString += "WHERE start=" + "\'" + start + "\'" + " AND " +
    	                "destination=" + "\'" + arrive + "\'";
    	         
        ResultSet rs = sqlBean.executeQuery(sqlString);
        
        if (rs != null)
        {
        	String result = "                                                    " + 
		                "Ŀ�ĵز�ѯ " + "\n";
		                
        	   //The result string is different for the two kinds of query
			if (flag == 1)
			   result += "���� " + destination + " �����к���:" + "\n";
			else
			   result += "ʼ����: " + start + "------" + "Ŀ�ĵ�: " + arrive + "\n";
			   
			result += formResult(rs);
			   
			showResult(result);
        }
	       
	    else 
	       JOptionPane.showMessageDialog(null,"û�����������ݿ�!",
	                                    "������Ϣ",JOptionPane.ERROR_MESSAGE);  
    }
	
	public String formResult(ResultSet rs)
	{
		String result = "�����    ���չ�˾            ��ɵص�  �ִ�ص�  ���ʱ��  �ִ�ʱ��  " + 
		                "��ͯƱ��   ����Ʊ��   �ۿ�    ���� " + "\n";
		   
		   //Used to determine whether there are no records found
		int originLength = result.length();
		
		String time1,time2;
		String childFare,adultFare,discount1,discount2,seat;	
		
		try
		{
			while(rs.next())
			{
				result += rs.getString("flight") + rs.getString("airfirm") + rs.getString("start") + 
				          rs.getString("destination");
				          
				    //When you get the time from the resultset,it is like "1200".
				    //So we should change it into the form "12:00".
				time1 = rs.getString("leaveTime");
				time2 = rs.getString("arriveTime");
				    //getTime(String time) is used to change the time form into standard one
				time1 = getTime(time1);
				time2 = getTime(time2);
				
				result += time1 + "     " + time2  + "     ";
					
				   //Make sure that the following items have the exactly bits,
				   //so that they can be display in a neat format
				childFare = String.valueOf(rs.getFloat("childFare"));
				adultFare = String.valueOf(rs.getFloat("adultFare"));
				discount1 = String.valueOf(rs.getFloat("discount1"));
				discount2 = String.valueOf(rs.getFloat("discount2"));
				seat = String.valueOf(rs.getInt("seat"));
				
				   //Make every item in a neat format
				while(childFare.length() != 11)
				   childFare += " ";
				while(adultFare.length() != 11)
				   adultFare += " ";
				while(discount1.length() != 8)
				   discount1 += " ";					
				   
				result += childFare + adultFare + discount1 +
				          rs.getString("week");
				result += "\n";
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		   //Means there are no records found
		   //So give user message that couldn't find correlate infomation
		if (result.length() == originLength)
		{
			result += "                                                    " +
			          "�Բ���,�Ҳ�������Ҫ�ĺ�����Ϣ!" + "\n";
		}	
		
		return result;
	}
	
	//Show the result in a dialog
    public void showResult(String result)
    {
    	JOptionPane.showMessageDialog(null,result,"��ѯ���",JOptionPane.PLAIN_MESSAGE);
    }          
	
	//The method used to change the time form 
	private String getTime(String time)
	{
		String time1,time2;
		time1 = time.substring(0,2);
		time2 = time.substring(2,4);
		
		time1 = time1.concat(":");
		time1 = time1.concat(time2);
		
		return time1;
	}            
}