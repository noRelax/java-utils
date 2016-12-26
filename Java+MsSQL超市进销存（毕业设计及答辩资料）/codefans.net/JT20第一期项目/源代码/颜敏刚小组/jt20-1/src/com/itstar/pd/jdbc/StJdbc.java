package com.itstar.pd.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class StJdbc {

	
	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */

	public List getRs() throws InstantiationException, IllegalAccessException, ClassNotFoundException{

		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		String url = "jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=jxc;SelectMethod=Cursor";
		Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver").newInstance();
		System.out.println("连接成功 ");
		List<StType> list=new ArrayList<StType>();
		// 输出正常
		try {
			conn = DriverManager.getConnection(url, "sa", "sa");
			
			
			
//			
//			//向表中插入数据
//			ps=conn.prepareStatement("insert into Storage(Sgname,Ssname,Sstotal,Stype,Snumber) values(?,?,?,?,?)");
//			ps.setString(1, "啤酒");
//			ps.setString(2, "青岛啤酒");
//			ps.setString(3, "2555");
//			ps.setString(4, "酒");
//			ps.setString(5, "500");
//			ps.executeUpdate();
			

			/************ 对数据库进行相关操作 ************/
			String query = "select * from Storage order by Sid";
			
			ps=conn.prepareStatement(query);
			rs=ps.executeQuery();
			System.out.println("account:");


			System.out.println("------------------------");
			System.out.println("Sid" + " " + "Sgname" + " " + "Ssname "+ " " + "Sstotal" + " " + "Stype "+""+"Snumber");
			System.out.println("------------------------");

			// 对获得的查询结果进行处理，对Result类的对象进行操作
			while (rs.next()) {
				StType st=new StType();
				st.setSid(rs.getInt("Sid")) ;
				st.setSgname(rs.getString("Sgname"));
				st.setSsname(rs.getString("Ssname"));
				st.setSstotal(rs.getString("Sstotal"));
				st.setStype(rs.getString("Stype"));
				st.setSnumber(rs.getString("Snumber"));
//				String Sgname = rs.getString("Sgname");
//				String Ssname = rs.getString("Ssname");
//				String Sstotal = rs.getString("Sstotal");
//				String Stype = rs.getString("Stype");
//				String Snumber = rs.getString("Snumber");
				// 取得数据库中的数据				
//				System.out.println(" " + Sid + " " + Sgname + " " + Ssname+""+Sstotal+""+Stype+""+Snumber);
				list.add(st);
			}

			// 关闭连接和声明
			ps.close();
			conn.close();

			System.out.println("查询完毕! ");
			conn.close();
		} catch (SQLException e) {
			System.out.println("---------------------");
			e.printStackTrace();
		}finally{
			try{
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			}catch(Exception e){}
		}

	
		return list;
		}

	}


