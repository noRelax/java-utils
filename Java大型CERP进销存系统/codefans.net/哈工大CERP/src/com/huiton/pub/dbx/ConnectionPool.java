package com.huiton.pub.dbx;

import java.sql.*;
import java.util.*;

public class ConnectionPool {
	private String driver = null;
	private String url = null;
	private int size = 0;
	private String username = new String("");
	private String password = new String("");
	private Vector pool = null;

	public ConnectionPool() {}

	public void setDriver(String value) {
		if(value!=null) {
			driver = value;
		}
	}

	public String getDriver() {
		return driver;
	}

	public void setURL(String value) {
		if(value!=null) {
			url = value;
		}
	}

	public String getURL() {
		return url;
	}

	public void setSize(int value) {
		if(value>1) {
			size = value;
		}
	}

	public int getSize() {
		return size;
	}

	public void setUsername(String value) {
		if(value!=null) {
			username = value;
		}
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String value) {
		if(value!=null) {
			password = value;
		}
	}

	public String getPassword() {
		return password;
	}

	private Connection createConnection() throws Exception {
		Connection con = null;
		con = DriverManager.getConnection(url, username, password);

		return con;
	}

	public synchronized void initializePool() throws Exception {
		if(driver==null) {
			throw new Exception("No Driver Name Specified!");
		}

		if(url==null) {
			throw new Exception("No URL Specified!");
		}

		if(size<1) {
			throw new Exception("Pool size is less than 1!");
		}

		try {
			Class.forName(driver);

			for(int x=0;x<size;x++) {
				Connection con = createConnection();
				if(con!=null) {
					PooledConnection pcon = new PooledConnection(con);
					addConnection(pcon);
				}
			}
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	private void addConnection(PooledConnection value) {
		if(pool==null) {
			pool = new Vector(size);
		}
		pool.addElement(value);
	}

	public synchronized void releaseConnection(Connection con) {
		for(int x=0;x<pool.size();x++) {
			PooledConnection pcon = (PooledConnection)pool.elementAt(x);
			if(pcon.getConnection()==con) {
				System.err.println("Releasing Connection " + x);
				pcon.setInUse(false);
				break;
			}
		}
	}

	public synchronized Connection getConnection() throws Exception {
		PooledConnection pcon = null;

		for(int x=0;x<pool.size();x++) {
			pcon = (PooledConnection)pool.elementAt(x);
			if(pcon.inUse()==false) {
				pcon.setInUse(true);
				return pcon.getConnection();
			}
		}

		try {
			Connection con = createConnection();
			pcon = new PooledConnection(con);
			pcon.setInUse(true);
			pool.addElement(pcon);
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
			throw new Exception(e.getMessage());
		}

		return pcon.getConnection();
	}

	public synchronized void emptyPool() {
		for(int x=0;x<pool.size();x++) {
			System.err.println("Closing JDBC Connection " + x);
			PooledConnection pcon =
				(PooledConnection)pool.elementAt(x);

			if(pcon.inUse()==false) {
				pcon.close();
			}
			else {
				try {
					java.lang.Thread.sleep(30000);
					pcon.close();
				}
				catch(InterruptedException ie) {
					System.err.println(ie.getMessage());
				}
			}
		}
	}
}