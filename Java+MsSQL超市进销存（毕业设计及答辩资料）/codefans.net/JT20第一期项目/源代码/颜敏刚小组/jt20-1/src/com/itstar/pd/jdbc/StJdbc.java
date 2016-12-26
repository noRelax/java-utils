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
		System.out.println("���ӳɹ� ");
		List<StType> list=new ArrayList<StType>();
		// �������
		try {
			conn = DriverManager.getConnection(url, "sa", "sa");
			
			
			
//			
//			//����в�������
//			ps=conn.prepareStatement("insert into Storage(Sgname,Ssname,Sstotal,Stype,Snumber) values(?,?,?,?,?)");
//			ps.setString(1, "ơ��");
//			ps.setString(2, "�ൺơ��");
//			ps.setString(3, "2555");
//			ps.setString(4, "��");
//			ps.setString(5, "500");
//			ps.executeUpdate();
			

			/************ �����ݿ������ز��� ************/
			String query = "select * from Storage order by Sid";
			
			ps=conn.prepareStatement(query);
			rs=ps.executeQuery();
			System.out.println("account:");


			System.out.println("------------------------");
			System.out.println("Sid" + " " + "Sgname" + " " + "Ssname "+ " " + "Sstotal" + " " + "Stype "+""+"Snumber");
			System.out.println("------------------------");

			// �Ի�õĲ�ѯ������д�����Result��Ķ�����в���
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
				// ȡ�����ݿ��е�����				
//				System.out.println(" " + Sid + " " + Sgname + " " + Ssname+""+Sstotal+""+Stype+""+Snumber);
				list.add(st);
			}

			// �ر����Ӻ�����
			ps.close();
			conn.close();

			System.out.println("��ѯ���! ");
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


