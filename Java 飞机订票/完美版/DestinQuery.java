package flight.query;

import flight.assist.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;

public class DestinQuery extends JPanel implements ActionListener
{
	   //A bean used to connect to the database and execute SQL operation
	static SqlBean sqlBean = new SqlBean();
	
	private static DefaultComboBoxModel model_1 = new DefaultComboBoxModel();
	private static DefaultComboBoxModel modelStart = new DefaultComboBoxModel();
	private static DefaultComboBoxModel modelArrive = new DefaultComboBoxModel();
	private static JComboBox jcb1 = new JComboBox(model_1),
	                         jcbStart = new JComboBox(modelStart),
	                         jcbArrive = new JComboBox(modelArrive);
	                  
    private JButton jbQuery1 = new JButton("查询"),
                    jbQuery2 = new JButton("查询"); 
    
       //Used to the items choosed from each combobox
    private String destination,start,arrive;
    
       //Setup GUI in the Constructor method
    public DestinQuery()
    {    
           //*****************************************************
           
    	JLabel jl = new JLabel("目的地查询");
	    jl.setFont(new Font("Times",Font.BOLD,23));
	    JPanel jpTop = new JPanel();
	    jpTop.add(jl);    
	   
	    JLabel jl2 = new JLabel("查询方法一:");
	    jl2.setFont(new Font("Times",Font.PLAIN,12));
	    
	    JLabel jl3 = new JLabel("        请选择要到达的城市名称:");
	    jl3.setFont(new Font("Times",Font.PLAIN,12));
	    JPanel jpLabel1 = new JPanel(new BorderLayout());
	    jpLabel1.add(jl2,BorderLayout.NORTH);
	    jpLabel1.add(jl3,BorderLayout.CENTER);
	    
	    JPanel jpQuery1 = new JPanel();	   
	    jpQuery1.add(jcb1);
	    jpQuery1.add(jbQuery1);  
	    
	    JPanel jp1 = new JPanel();
	    jp1.setLayout(new BorderLayout());
	    jp1.add(jpLabel1,BorderLayout.NORTH);
	    jp1.add(jpQuery1,BorderLayout.CENTER); 
	    
	       //*****************************************************	    
	    
	    JLabel jl4 = new JLabel("查询方法二:");
	    jl4.setFont(new Font("Times",Font.PLAIN,12));
	    
	    JLabel jl5 = new JLabel("        请选择起始城市和到达城市进行查询:");
	    jl5.setFont(new Font("Times",Font.PLAIN,12));
	    JPanel jpLabel2 = new JPanel(new BorderLayout());
	    jpLabel2.add(jl4,BorderLayout.NORTH);
	    jpLabel2.add(jl5,BorderLayout.CENTER);
	    
	    JPanel jpQuery2 = new JPanel();
	    jpQuery2.add(new JLabel("出发城市:"));
	    jpQuery2.add(jcbStart);
	    
	    JPanel jpQuery3 = new JPanel();
	    jpQuery3.add(new JLabel("抵达城市:"));
	    jpQuery3.add(jcbArrive);
	    
	    JPanel jpButton  = new JPanel();
	    jpButton.add(jbQuery2);
	    
	    JPanel jp2 = new JPanel();
	    jp2.add(jpQuery2);
	    jp2.add(jpQuery3);
	    
	    JPanel jp3 = new JPanel();
	    jp3.setLayout(new BorderLayout());
	    jp3.add(jpLabel2,BorderLayout.NORTH);
	    jp3.add(jp2,BorderLayout.CENTER);
	    jp3.add(jpButton,BorderLayout.SOUTH);
	    
	       //*****************************************************	    
	    
	    JPanel jp4 = new JPanel();
	    jp4.setLayout(new BorderLayout());
	    jp4.add(jp1,BorderLayout.NORTH);
	    jp4.add(jp3,BorderLayout.CENTER);
	    
	    this.setLayout(new BorderLayout());
	    this.add(jpTop,BorderLayout.NORTH);
	    this.add(jp4,BorderLayout.CENTER);
	    this.add(new JLabel("             "),BorderLayout.SOUTH);
	    
	       //Add listener to the query buttons
	    jbQuery1.addActionListener(this);
	    jbQuery2.addActionListener(this);	    
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
    
    public void actionPerformed(ActionEvent e)
    {
    	if (e.getSource() == jbQuery1)
    	{
    		   //Get the destination you want to query
    		destination = (String)jcb1.getSelectedItem();
    		   //Trim the space at the side of the string
    		destination = destination.trim();
    		
    		   //Do the query work,and diliver the flag=1,means that 
    		   //it is for the first kind of query
    		executeDestinQuery(1);
    	}
    	else if (e.getSource() == jbQuery2)
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
	       showResult(rs,flag);
	    else 
	       JOptionPane.showMessageDialog(null,"没有连接上数据库!",
	                                    "错误信息",JOptionPane.ERROR_MESSAGE);  
    }
    
       //Get the result string from the result set,
       //and then display the result in a dialog
    public void showResult(ResultSet rs,int flag)
    {
        String result = "                                                    " + 
		                "目的地查询 " + "\n";
		
		   //The result string is different for the two kinds of query
		if (flag == 1)
		   result += "到达 " + destination + " 的所有航班:" + "\n";
		else
		   result += "始发地: " + start + "------" + "目的地: " + arrive + "\n";
		   
		result += "航班号    航空公司            起飞地点  抵达地点  起飞时间  抵达时间  " + 
		          "儿童票价   成人票价   折扣   班期 " + "\n";
		   
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
		
		   //Means there are no records found,so give user the message that 
		   //couldn't find correlate infomation for the designate kind of query
		if (result.length() == originLength && flag == 1)
		{
			JOptionPane.showMessageDialog(null,"没有到达 " + destination + "的航班",
	    	                              "查询结果",JOptionPane.PLAIN_MESSAGE);
	    	return;
		}
		if (result.length() == originLength && flag == 2)
		{
			JOptionPane.showMessageDialog(null,"没有从 " + start +" 到 " + arrive +" 的航班",
	    	                              "查询结果",JOptionPane.PLAIN_MESSAGE);
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