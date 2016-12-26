package com.huiton.pub.dbx;

import java.sql.*;

public class PooledConnection {
	private Connection connection = null;
	private boolean inuse = false;

	public PooledConnection(Connection value) {
		if(value!=null) {
			connection = value;
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public void setInUse(boolean value) {
		inuse = value;
	}

	public boolean inUse() {
		return inuse;
	}

	public void close() {
		try {
			connection.close();
		}
		catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
		}
	}
}