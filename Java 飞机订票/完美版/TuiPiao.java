package flight.tuipiao;

import flight.assist.*;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.io.*;
import java.util.*;
import java.sql.*;

public class TuiPiao extends JFrame implements ActionListener
{
	private JTextField jtfDingDan = new JTextField(12),jtfID = new JTextField(15),
	                   jtfName = new JTextField(7),jtfFlightInfo = new JTextField(20),
	                   jtfChildNum = new JTextField(5),jtfAdultNum = new JTextField(5),
	                   jtfOriginCost = new JTextField(9),jtfTuiPiaoCost = new JTextField(9),
	                   jtfTime1 = new JTextField(7),
	                   jtfTime2 = new JTextField(7),
	                   jtfChildTuiPiaoShu = new JTextField("0",5),
	                   jtfAdultTuiPiaoShu = new JTextField("0",5);
	                   
	private JButton jbQuery = new JButton("查询"),jbOK = new JButton("退票"),
	                jbCancel = new JButton("退出"),jbRewrite = new JButton("重填");
	                
	private Object[] items = {"15位身份证","18位身份证"	};
	private JComboBox jcb = new JComboBox(items);
	
	private JLabel jlTime1 = new JLabel("出发时间"),jlTime2 = new JLabel("            ");
	
	private String dingdanNum,name,idNum,flight1,flight2,ticketType,leaveTime1,leaveTime2,
	               childNum,adultNum,cost,year,month,day,hour;
	               
	private File file ;
	
	private long locationOfRecord;
	
	private SeatInfo seatInfo = new SeatInfo();
	private SqlBean sqlBean = new SqlBean();
	
	public TuiPiao()
	{
		//arrange the panel
		File f = new File(".","data");
    	f.mkdir();
    	file = new File(f,"ClientInfo.txt");
    	    	
    	//set the textfields for querying uneditable 
    	jtfName.setEditable(false);
		jtfFlightInfo.setEditable(false);
		jtfChildNum.setEditable(false);
		jtfAdultNum.setEditable(false);
		jtfOriginCost.setEditable(false);
		jtfTuiPiaoCost.setEditable(false);
		jtfTime1.setEditable(false);
		jtfTime2.setEditable(false);
    	        
        //******************************************************************
        JLabel jlDingdan = new JLabel("订 单 号 ");
       // jlDingdan.setFont(new Font("Times",Font.PLAIN,12));
        JLabel jlID = new JLabel("   身份证号");
       // jlID.setFont(new Font("Times",Font.PLAIN,12));
        
        JPanel jpInput = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jpInput.setBorder(new TitledBorder("输入信息"));
        
        jpInput.add(jlDingdan);
        jpInput.add(jtfDingDan);
        jpInput.add(jlID);
        jpInput.add(jtfID);      
                
        //****************************************************************
        
        JLabel jlName = new JLabel("客户姓名");
        JLabel jlFlight = new JLabel("  航班信息");
        JLabel jlChild = new JLabel("儿童票数");
        JLabel jlAdult = new JLabel("成人票数");
        JLabel jlOriCost = new JLabel("原  票  价");
        JLabel jlTuiCost = new JLabel("退  票  价");     
        
        JPanel jpTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jpTop.add(jlName);
        jpTop.add(jtfName);
        jpTop.add(jlFlight);
        jpTop.add(jtfFlightInfo);
        
        JPanel jpNum = new JPanel();
        jpNum.setLayout(new GridLayout(2,1));
	        JPanel jp1 = new JPanel();
	        jp1.add(jlChild);
	        jp1.add(jtfChildNum);
	        JPanel jp2 = new JPanel();
	        jp2.add(jlAdult);
	        jp2.add(jtfAdultNum);    
        jpNum.add(jp1);
        jpNum.add(jp2);        
        
        JPanel jpCost = new JPanel();
        jpCost.setLayout(new GridLayout(2,1));
	        JPanel jp3 = new JPanel();
	        jp3.add(jlOriCost);
	        jp3.add(jtfOriginCost);
	        JPanel jp4 = new JPanel();
	        jp4.add(jlTuiCost);
	        jp4.add(jtfTuiPiaoCost);
        jpCost.add(jp3);
        jpCost.add(jp4);
      
        
        JPanel jpTime = new JPanel();
        jpTime.setLayout(new GridLayout(2,1));
	        JPanel jp5 = new JPanel();
	        jp5.add(jlTime1);
	        jp5.add(jtfTime1);
	        JPanel jp6 = new JPanel();
	        jp6.add(jlTime2);
	        jp6.add(jtfTime2);
        jpTime.add(jp5);
        jpTime.add(jp6);
       
        
        JPanel jpCenter = new JPanel();
        jpCenter.setLayout(new BorderLayout());
        jpCenter.add(jpNum,BorderLayout.WEST);
        jpCenter.add(jpCost,BorderLayout.CENTER);
        jpCenter.add(jpTime,BorderLayout.EAST);   
        
        JPanel jpInfo = new JPanel();
        jpInfo.setBorder(new TitledBorder("基本信息"));
        jpInfo.setLayout(new BorderLayout()); 
        jpInfo.add(jpTop,BorderLayout.NORTH);
        jpInfo.add(jpCenter,BorderLayout.CENTER);       
        
        jlTime2.setVisible(false);
		jtfTime2.setVisible(false);
		
		
		JPanel jpTuiPiao = new JPanel(new FlowLayout(FlowLayout.CENTER));
		jpTuiPiao.setBorder(new TitledBorder("退票"));
		jpTuiPiao.add(new JLabel("退票数:"));
		jpTuiPiao.add(new JLabel("儿童票"));
		jpTuiPiao.add(jtfChildTuiPiaoShu);
		jpTuiPiao.add(new JLabel("成人票:"));
		jpTuiPiao.add(jtfAdultTuiPiaoShu);
			
		
		
		JPanel jpButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
		jpButtons.setBorder(new TitledBorder("操作"));
		jpButtons.add(jbQuery);
		jpButtons.add(jbRewrite);
		jpButtons.add(jbOK);
		jpButtons.add(jbCancel);
		
		JPanel jp = new JPanel();
		jp.setLayout(new BorderLayout());
		jp.add(jpInput,BorderLayout.NORTH);
		jp.add(jpInfo,BorderLayout.CENTER);
		jp.add(jpTuiPiao,BorderLayout.SOUTH);
		
		JPanel jpTotal = new JPanel();
		jpTotal.setBorder(new MatteBorder(new ImageIcon("f.gif")));
		jpTotal.setLayout(new BorderLayout());
		jpTotal.add(jp,BorderLayout.CENTER);
		jpTotal.add(jpButtons,BorderLayout.SOUTH);
       
        //******************************************************************
               
		this.getContentPane().add(jpTotal);		
		
		//add actionListener for the buttons
		jbQuery.addActionListener(this);
		jbRewrite.addActionListener(this);
		jbOK.addActionListener(this);
		jbCancel.addActionListener(this);
		
		//add actionListener for the window
		this.addWindowListener(new WindowAdapter()
	                          {
	                          	public void windowClosing(WindowEvent e)
	                          	{
	                          		TuiPiao.this.setVisible(false);
	                          		TuiPiao.this.dispose();
	                          	}
	                          }
	                      );		
	}
	//********************************************************************
	
	//the buttons' action
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == jbQuery)
		   query();
		else if (e.getSource() == jbRewrite)
		   rewrite();
		else if (e.getSource() == jbOK)
		   tuiPiao();
		else if (e.getSource() == jbCancel)
		{
			//set the frame unvisible
			this.setVisible(false);
			//close the window
			this.dispose();
		}   
	}
	//********************************************************************
	
	//operations for query
	private void query()
	{
		String  dingdan = jtfDingDan.getText().trim();
	    //if dingdan is wrong,display the error message
	    if (dingdan.length() == 0)
	    {
	    	JOptionPane.showMessageDialog(null,"订单号不能为空",
	    	                              "错误信息",JOptionPane.ERROR_MESSAGE);
	    	return;
	    }
	    
	    String id = jtfID.getText().trim();
	    //if dingdan is wrong,display the error message
	    if (id.length() == 0)
	    {
	    	JOptionPane.showMessageDialog(null,"身份证号不能为空",
	    	                              "错误信息",JOptionPane.ERROR_MESSAGE);
	    	return;
	    }
	    
	    //find the message of passenger
	    boolean isValid = getClientInfo(dingdan,id);
	    
	    if (isValid == false)
	    {
	    	rewrite();
	    	return;
	    }
	       
	       
	    //display the message of passenger
	    else
	    {
	    	jtfName.setText(name);
	    	jtfChildNum.setText(childNum);
	    	jtfAdultNum.setText(adultNum);
	    	jtfOriginCost.setText("  "+cost+"元");
	    	
	    	int sign=returnTime();
	    	//if the time for return ticket is over,display the error message 
	    	if(sign==0)
			  JOptionPane.showMessageDialog(null,"抱歉，退票时间已过，您现在不能退票!","错误信息",JOptionPane.ERROR_MESSAGE);
	    	//display the returning money across the current time and the flight time 
	    	if(sign==1)
	    	  jtfTuiPiaoCost.setText("退还65%价钱");
	    	if(sign==1)
	    	  jtfTuiPiaoCost.setText("退还70%价钱");
	    	  
	    	
	    	//compute the returning money  
	    	if (Integer.parseInt(childNum) == 0)
		       jtfChildTuiPiaoShu.setEditable(false);
	        if (Integer.parseInt(adultNum) == 0)
	           jtfAdultTuiPiaoShu.setEditable(false);
	           
	        if (ticketType.equals("单程"))
	        {
	        	jlTime1.setText("出发时间");
	        	jtfTime1.setText(leaveTime1);
	        	
	        	jlTime2.setVisible(false);
	        	jtfTime2.setVisible(false);
	        	
	        	jtfFlightInfo.setText("(单程机票)" + "航班号:" + flight1);
	        } 
	        
	        else if (ticketType.equals("往返"))
	        {
	        	jlTime1.setText("出发时间");
	        	jtfTime1.setText(leaveTime1);
	        	
	        	jlTime2.setText("返回时间");
	        	jlTime2.setVisible(true);
	        	
	        	jtfTime2.setText(leaveTime2);
	        	jtfTime2.setVisible(true);
	        	
	        	jtfFlightInfo.setText("(往返机票) " + " 去: " + flight1 +  "; 返: " + flight2);
	        }  
	        
	        else if (ticketType.equals("联程"))
	        {
	        	jlTime1.setText("第一出发时间");
	        	jtfTime1.setText(leaveTime1);
	        	
	        	jlTime2.setText("第二出发时间");
	        	jlTime2.setVisible(true);
	        	jtfTime2.setText(leaveTime2);
	        	jtfTime2.setVisible(true);
	        	
	        	jtfFlightInfo.setText("(联程机票) " + "航班1: " + flight1 +  "; 航班2: " + flight2);
	        }  	    	
	    }		
	}
	//********************************************************************
	
	//clean the textFilds
	private void rewrite()
	{
	    jtfDingDan.setText("");
	    jtfID.setText("");
		jtfName.setText("");
		jtfFlightInfo.setText("");
		
    	jtfChildNum.setText("");
    	jtfAdultNum.setText("");
    	jtfOriginCost.setText("");
    	jtfTuiPiaoCost.setText("");
    	
    	jtfTime1.setText("");
    	jtfTime2.setText("");
    	
    	jtfChildTuiPiaoShu.setText("0");
    	jtfAdultTuiPiaoShu.setText("0"); 
    	
    	jlTime1.setText("出发时间");   
    
    	jlTime2.setVisible(false);
    	jtfTime2.setVisible(false);
	}
	//********************************************************************
	
	//determine wether can return ticket and performs
	private void tuiPiao()
	{
	    String  dingdan = jtfDingDan.getText().trim();
	    if (dingdan.length() == 0)
	    {
	    	JOptionPane.showMessageDialog(null,"订单号不能为空",
	    	                              "错误信息",JOptionPane.ERROR_MESSAGE);
	    	return;
	    }
	    
	    String id = jtfID.getText().trim();
	    if (id.length() == 0)
	    {
	    	JOptionPane.showMessageDialog(null,"身份证号不能为空",
	    	                              "错误信息",JOptionPane.ERROR_MESSAGE);
	    	return;
	    }
	    
	    boolean isValid = getClientInfo(dingdan,id);
	    
	    if (isValid == false)
	    {
	    	rewrite();
	    	return;
	    }	       
	       
	    else
	    {
	    	//when the tuipiaoshu is null,display the error message
	    	String childTuiPiaoShu = jtfChildTuiPiaoShu.getText().trim();
	    	String adultTuiPiaoShu = jtfAdultTuiPiaoShu.getText().trim();
	    	
	    	int cTuiPiaoShu = Integer.parseInt(childTuiPiaoShu);
	    	int aTuiPiaoShu = Integer.parseInt(adultTuiPiaoShu);
	    	
	    	if (cTuiPiaoShu == 0 && aTuiPiaoShu == 0)
	    	{
	    		JOptionPane.showMessageDialog(null,"请输入退票数",
	    		                              "错误信息",JOptionPane.ERROR_MESSAGE);
	    		return;
	    	}
	    	
	    	//tuipiaoshu over the tickets
	    	if (cTuiPiaoShu != 0 && cTuiPiaoShu > Integer.parseInt(childNum))
	    	{
	    		JOptionPane.showMessageDialog(null,"退票数大于已定票数,请按\"查询\"按钮查看信息",
	    		                              "错误信息",JOptionPane.ERROR_MESSAGE);
	    		return;
	    	}
	    	
	    	if (aTuiPiaoShu != 0 && aTuiPiaoShu > Integer.parseInt(adultNum))
	    	{
	    		JOptionPane.showMessageDialog(null,"退票数大于已定票数,请按\"查询\"按钮查看信息",
	    		                              "错误信息",JOptionPane.ERROR_MESSAGE);
	    		return;
	    	}
	    	
	    	operationForTuiPiao(cTuiPiaoShu,aTuiPiaoShu);
	    }
		
	}
	//********************************************************************
	
	//operation after returning
	private void operationForTuiPiao(int childTuiPiaoShu,int adultTuiPiaoShu)
	{
		int newChildNum = Integer.parseInt(childNum) - childTuiPiaoShu;
		int newAdultNum = Integer.parseInt(adultNum) - adultTuiPiaoShu;
		float tuiPiaoCost = caculateTuiPiaoCost(childTuiPiaoShu,adultTuiPiaoShu);
		float newCost = Float.parseFloat(cost) - tuiPiaoCost;
		
		try
		{
			RandomAccessFile raf = new RandomAccessFile(file,"rw");
			
			raf.seek(this.locationOfRecord);
			
			//input the message after modified
			raf.writeUTF(dingdanNum);
			raf.writeUTF(name);
		    raf.writeUTF(idNum);
		    raf.writeUTF(flight1);
		    raf.writeUTF(flight2);
		    raf.writeUTF(ticketType);
		    raf.writeUTF(leaveTime1);
		    raf.writeUTF(leaveTime2);
		    raf.writeUTF(String.valueOf(newChildNum));
		    raf.writeUTF(String.valueOf(newAdultNum));
		    raf.writeUTF(String.valueOf(newCost));
		    
		    int totalTuiPiaoShu = childTuiPiaoShu + adultTuiPiaoShu;
		    int sign=returnTime();
		    
		    seatInfo.tuiPiao(flight1,leaveTime1,totalTuiPiaoShu);
		    
		    //when there's the other flight
		    if (flight2.length() != 0)
		       seatInfo.tuiPiao(flight2,leaveTime2,totalTuiPiaoShu);
		       
		    if (newChildNum == 0 && newAdultNum == 0)
		       JOptionPane.showMessageDialog(null,"恭喜你退票成功!"+"\n"+"该订单号已作废!"+"\n"+"你将获得"+tuiPiaoCost +
		                                     "的退票钱"+"\n"+"欢迎你再次选择我们!",
		                                     "退票成功",JOptionPane.INFORMATION_MESSAGE);	
		    else
		       if(sign!=0)
		         JOptionPane.showMessageDialog(null,"恭喜你退票成功!"+"\n"+"你现在剩余"+newChildNum+
		                                     "张儿童票和"+newAdultNum+"张成人票"+"\n"+"你将获得"+tuiPiaoCost +
		                                     "的退票钱"+"\n"+"欢迎你再次选择我们!","退票成功",JOptionPane.INFORMATION_MESSAGE);
		
		    //clean the textField
		    rewrite();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	//********************************************************************
	
	//caculate the returning money
	private float caculateTuiPiaoCost(int childTuiPiaoShu,int adultTuiPiaoShu)
	{
		float tuiPiaoCost = 0;
		int sign=0;
		
		try
		{
			//get message about the flight
			String sqlString = "select childFare,adultFare from flight where flight='"+flight1+"'";
			ResultSet rs = sqlBean.executeQuery(sqlString);
			
			float childFare1 = 0;
			float adultFare1 = 0;
			while(rs.next())
			{
			    childFare1 = rs.getFloat(1);
			    adultFare1 = rs.getFloat(2);	
			}
			
			float childFare2 = 0;
			float adultFare2 = 0;
			if (flight2.length() != 0)
			{
				String sqlString2 = "select childFare,adultFare from flight where flight='"+flight2+"'";
			    ResultSet rs2 = sqlBean.executeQuery(sqlString2);
			    
			    while(rs2.next())
				{
				    childFare1 = rs2.getFloat(1);
				    adultFare2 = rs2.getFloat(2);	
				}
			}
			
			sign=returnTime();
			
			if (flight2.length() == 0){
				
			  if(sign==0)
			    JOptionPane.showMessageDialog(null,"抱歉，您现在不能退票!","错误信息",JOptionPane.ERROR_MESSAGE);
			  if(sign==1)
			    tuiPiaoCost = (childFare1*childTuiPiaoShu + adultFare1*adultTuiPiaoShu) * (float)0.65;
			  if(sign==2)
				tuiPiaoCost = (childFare1*childTuiPiaoShu + adultFare1*adultTuiPiaoShu) * (float)0.7;
			  
			}
			  
			else
			{
			  if(sign==0)
			    JOptionPane.showMessageDialog(null,"抱歉，退票时间已过，您现在不能退票!","错误信息",JOptionPane.ERROR_MESSAGE);
			  if(sign==1)
			    tuiPiaoCost = ( (childFare1 + childFare2)*childTuiPiaoShu +
				                (adultFare1 + adultFare2)*adultTuiPiaoShu ) * (float)0.65;
			  if(sign==2)
				tuiPiaoCost = ( (childFare1 + childFare2)*childTuiPiaoShu +
				                (adultFare1 + adultFare2)*adultTuiPiaoShu ) * (float)0.7;
			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return tuiPiaoCost;		
	}
	//********************************************************************
	
	public String getCurrentTime(){
		//Get the instance for the class Calendar which used to get the present time
    	Calendar cal = Calendar.getInstance();
    	
    	   //Because there are two Date classes(java.util.Date--java.sql.Date)
    	   //So we should designate the full name for the java.util.Date class 
    	cal.setTime(new java.util.Date());
    	
    	   //Get the present year,month,day
    	String year = String.valueOf(cal.get(Calendar.YEAR));
    	String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
    	String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
    	String hour = String.valueOf(cal.get(Calendar.HOUR_OF_DAY));
    	//String minute = String.valueOf(cal.get(Calendar.MINUTE));
    	//String second = String.valueOf(cal.get(Calendar.SECOND));
    	return year.concat(month.concat(day.concat(hour)));
	}
	
	//determine wether can return ticket right now
	public int returnTime(){
		
	    int y=Integer.parseInt(year);
		int m=Integer.parseInt(month);
		int d=Integer.parseInt(day);
		int h=Integer.parseInt(hour);
				
		String st=getCurrentTime();
		int cy=Integer.parseInt(st.substring(0,4));
		int cm=Integer.parseInt(st.substring(4,6));
		int cd=Integer.parseInt(st.substring(6,8));
		int ch=Integer.parseInt(st.substring(8,10));
		
		//compare flight time and current time
		if(y-cy==1)
		  cm=m-1;
		  
		else if(y!=cy)   
		  return 0;
		  
		if(m-cm==1){
		      		switch(m){
		      			case 1:  
		      			case 3:  
		      			case 5: 
		      			case 7:  
		      			case 8:  
		      			case 10:  
		      			case 12:   cd=cd-31; break;
		      			case 2:    {
		      				         if(isLeapYear(cy))   cd=cd-29;
		      			             else  cd=cd-28;
		      			             break;
		      			           }
		      			case 4:  
		      			case 6:  
		      			case 9:  
		      			case 11:   cd=cd-30;   break;
		      			  
		      		}
		  }
		  else if(m!=cm)  return 0;
		  //if the flight is leave the day after tomorrow day,the return can't perform
		  if(d==cd){
		  	if(h-ch<2)  return 0;
		    else   return 1;
		  }
		    
		  else 
		      {
		         if(d-cd<5&&d-cd>0)   return 1;
		         else if(d-cd>5)   return 2;
		         else return 0;
		      }
		  	
		  	  
	}
	
	//wether the year is leap year
	public boolean isLeapYear(int year){
		if(year%4==0&&year%100!=0&&year%400==0)
		  return true;
		else return false;
	}
	//********************************************************************
	
	//get the clients' information
	private boolean getClientInfo(String dingdan,String id)
	{
		RandomAccessFile raf = null;
		
		try
		{				
		    raf = new RandomAccessFile(file,"rw");
		    
		    boolean isDingDanExist = false;
		    boolean isIDRight = false;
		    
		    long tempLocation = 0;
		    raf.seek(0);
		   
		   //find message in file
		   try{
		   	
		   	 //if can't continue reading,throw exception
		   	 while (raf.getFilePointer() < raf.length())
		    {
			    //record current position
			    tempLocation = raf.getFilePointer();
			    
			    dingdanNum = raf.readUTF();
			    name = raf.readUTF();
			    idNum = raf.readUTF();
			    flight1 = raf.readUTF();
			    flight2 = raf.readUTF();
			    ticketType = raf.readUTF();
			    leaveTime1 = raf.readUTF();
			    leaveTime2 = raf.readUTF();
			    childNum = raf.readUTF();
			    adultNum = raf.readUTF();
			    cost = raf.readUTF();
			    
			    if (dingdanNum.equals(dingdan))
			    {
			    	isDingDanExist = true;
			    	
			    	if (idNum.equals(id))
			    	{
			    		isIDRight = true;
			    		this.locationOfRecord = tempLocation;
			    		break;
			    	}
			    	
			    	else 
			    	{
			    		isIDRight = false;
			    		break;
			    	}
			    }		    
		    }
		   }
		   catch(Exception e){
		   	
		   }
		    
		    //display the fist leave time and the second time
		    if (isDingDanExist == true && isIDRight == true)
		    {
		    	String year2="";
		    	String month2="";
		    	String day2="";
		    	String hour2="";
		    	
		    	if (leaveTime1.length() != 0)
		    	{
		    		year = leaveTime1.substring(0,4);
		    	    month = leaveTime1.substring(4,6);
		    	    day = leaveTime1.substring(6,8);
		    	    hour=leaveTime1.substring(8,10);
		    	    
		    	    leaveTime1 = year.concat("-").concat(month).concat("-").concat(day);
		    	}
		    	
		    	if (leaveTime2.length() != 0)
		    	{
		    		year2 = leaveTime2.substring(0,4);
		    	    month2 = leaveTime2.substring(4,6);
		    	    day2 = leaveTime2.substring(6,8);
		    	    hour2 =leaveTime1.substring(8,10);
		    	    
		    	    leaveTime2 = year2.concat("-").concat(month2).concat("-").concat(day2);
		    	}
		    	
		    	if (Integer.parseInt(childNum) == 0 && Integer.parseInt(adultNum) == 0)
		    	{
		    		JOptionPane.showMessageDialog(null,"该订单号已经无效!",
		    		                              "错误信息",JOptionPane.ERROR_MESSAGE);
		    		return false;
		    	}
		    	
		    	return true;
		    	
		    }
		        
		    
		    if (isDingDanExist == true && isIDRight == false)
		    {
		    	JOptionPane.showMessageDialog(null,"身份证号不正确","错误信息",JOptionPane.ERROR_MESSAGE);
		    	return false;
		    }		      
		       
		    if (isDingDanExist == false)
		    {
		    	JOptionPane.showMessageDialog(null,"订单号不存在","错误信息",JOptionPane.ERROR_MESSAGE);
		    	return false;
		    	
		    }		   	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				//close the file
				raf.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return false;	
	}
    //********************************************************************	

}