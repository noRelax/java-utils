package server;
import javax.swing.*;
import java.sql.*;
import java.util.StringTokenizer;
public class database {	
	Connection cn=null;
	Statement st=null;
//	Statement st1=null;
	public ResultSet rs=null;
  public void connect(){
	  try{
    String dbUrl = "jdbc:odbc:qq2";
    String user = "";
    String password = "";
    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    cn = DriverManager.getConnection(dbUrl, user, password);
    st = cn.createStatement();
 //   st1 = c
	  }
      catch(Exception e){
    	  JOptionPane.showMessageDialog(null, "数据库链接失败");
      }
	  }

     public void select(String s){
    	 try{
    		 rs=st.executeQuery(s);
    	 }
    	 catch(Exception e)
  		{
    		 JOptionPane.showMessageDialog(null,e.getMessage());
  		}
     }
     public void update(String s)
 	{
 		try
 		{
 			st.executeUpdate(s);
 		}
 		catch(Exception e)
 		{
 			JOptionPane.showMessageDialog(null,e.getMessage());
 		}
 	}
     public void insert(String table,String what){
    	 try
 		{
 		    st.executeUpdate("Insert into "+table+" values "+what);
 	    }
    	 catch(Exception e){
    		 JOptionPane.showMessageDialog(null,e.getMessage());
    	 }
     }
     public void delete(String table,String condition)
 	{
 		try
 		{
 			st.executeUpdate("Delete from "+table+" where "+condition);
 		}
 		catch(Exception e)
 		{
 			JOptionPane.showMessageDialog(null,e.getMessage());
 		}
 	}
     public void close(){
    	 try{

    		 st.close();
        	 cn.close();
    	 }
    	 catch(Exception e){
    		 JOptionPane.showMessageDialog(null, "关闭数据库失败");
    	 }
     }
     public static void main(String args[])
     {
	       	 database d=new database();
	    	 d.connect();
    		String sql="select * from user1";
    		d.select(sql);
    	 try{
    		 while(d.rs.next())
    		 {
    			 System.out.println(d.rs.getString("id"));
    		 }
    		 d.close();
    	 }catch(Exception e){System.out.println("连接出错");}
     }
}