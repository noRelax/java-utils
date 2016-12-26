package flight.query;

import flight.assist.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.sql.*;

public class FlightQuery extends JPanel implements ActionListener,ListSelectionListener
{   
       //A bean used to connect to the database and execute SQL operation
	static SqlBean sqlBean = new SqlBean();
	
	private static DefaultListModel model = new DefaultListModel();
    private JList list = new JList(model);	  
	
	private JTextField flightField = new JTextField(15);
	private JButton jbQuery = new JButton("查询");	
	
	   //Used to store the flightNumber you have choosed
	private String flightNumber;
	
	   //Setup GUI in the Constructor method
	public FlightQuery()
	{		 
		   //Enabel only single selection
	    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);			    
	   
	       //****************************************************
	    JLabel jl = new JLabel("航班查询");
	    jl.setFont(new Font("Times",Font.BOLD,23));
	    JPanel jpTop = new JPanel();
	    jpTop.add(jl);    
	   
	    JLabel jl2 = new JLabel("                   请输入航班名称或者");
	    jl2.setFont(new Font("Times",Font.PLAIN,12));
	    
	    JLabel jl3 = new JLabel("           选择右边的航班列表:");
	    jl3.setFont(new Font("Times",Font.PLAIN,12));
	    JPanel jpLabel = new JPanel(new BorderLayout());
	    jpLabel.add(jl2,BorderLayout.NORTH);
	    jpLabel.add(jl3,BorderLayout.SOUTH);
	    
	       //*******************************************************
	    
	    JPanel jpField = new JPanel();
	    jpField.add(flightField);
	    
	    JPanel jpButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    jpButton.add(jbQuery);
	    
	    JPanel jp1 = new JPanel();
	    jp1.setLayout(new BorderLayout());
	    jp1.add(jpField,BorderLayout.NORTH);
	    jp1.add(jpButton,BorderLayout.CENTER);
	    
	    JPanel jp = new JPanel();
	    jp.setLayout(new BorderLayout());
	    jp.add(jpLabel,BorderLayout.NORTH);
	    jp.add(jp1);	   
	    
	       //*******************************************************
	    
	    JPanel jpList = new JPanel();
	    jpList.add(new JScrollPane(list));
	    
	    JPanel jpCenter = new JPanel();
	    jpCenter.setLayout(new BorderLayout());
	    jpCenter.add(jp,BorderLayout.CENTER);
	    jpCenter.add(jpList,BorderLayout.EAST);
	    
	    JPanel jpQuery = new JPanel();
	    jpQuery.setLayout(new BorderLayout());
	    jpQuery.add(jpTop,BorderLayout.NORTH);
	    jpQuery.add(jpCenter,BorderLayout.CENTER);
	    
	    this.setLayout(new BorderLayout());
	    this.add(new JLabel("      "),BorderLayout.NORTH);
	    this.add(jpQuery,BorderLayout.CENTER);
	    
	       //Add listener to the list
	    list.addListSelectionListener(this);
	       //Add listener to the query button
	    jbQuery.addActionListener(this);	
	}
	
	public static void updateFlightList(String newFlightNum,int insertOrDelete)
	{
		if (insertOrDelete == 1)
		   model.addElement(newFlightNum);
		else if (insertOrDelete == 2)
		{
			if (model.contains(newFlightNum))
			   model.removeElement(newFlightNum);			
		}
		   
	}
	
	   //Monitor the action to the list
	public void valueChanged(ListSelectionEvent e)
	{
		String s = (String)list.getSelectedValue();
		
		if (s != null)
		   flightField.setText(s.trim());
		else 
		   flightField.setText("");
	}
	
	public void actionPerformed(ActionEvent e)
	{
		this.flightNumber = flightField.getText().trim();	
		
		   //Haven't enter anything
		if (flightNumber.length() == 0)
		{
			JOptionPane.showMessageDialog(null,"请输入航班号或者从列表中选择",
			                              "错误信息",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		   //Do the query work
		executeFlightQuery();
	}
	
	public void executeFlightQuery()
	{
		   //Form the sqlString 
		String sqlString = "SELECT DISTINCT * FROM " +
		                   "flight " +
		                   "WHERE flight=" + "\'" + flightNumber + "\'";
	
	    ResultSet rs = sqlBean.executeQuery(sqlString);		        
	
	    if (rs != null)
	       showResult(rs);
	    else 
	       JOptionPane.showMessageDialog(null,"没有连接上数据库!",
	                                    "错误信息",JOptionPane.ERROR_MESSAGE);
	}
	
	   //Get the result string from the result set,
       //and then display the result in a dialog
	public void showResult(ResultSet rs)
	{
		String result = "                                                    " + 
		                "航班查询" + "\n";
		
		result += "查询的航班号:" + flightNumber + "\n";
		result += "航班号    航空公司            起飞地点  抵达地点  起飞时间  抵达时间  " + 
		          "儿童票价   成人票价   折扣1   折扣2   班期 " + "\n";
		   
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
				while(discount2.length() != 8)
				   discount2 += " ";			
				   
				result += childFare + adultFare + discount1 + discount2 +
				          rs.getString("week");
				result += "\n";
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		   //Means there are no records found,so show error message
		   //Give user hints that you enter a wrong flightnumber
		if (result.length() == originLength)
		{
			JOptionPane.showMessageDialog(null,"航班号不存在，请确认输入了正确的航班号",
	    	                              "错误信息",JOptionPane.ERROR_MESSAGE);
	    	return;
		}		
		
		   //Display the result in a messageDialog
		JOptionPane.showMessageDialog(null,result,"查询结果",JOptionPane.PLAIN_MESSAGE);		
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
}///:~