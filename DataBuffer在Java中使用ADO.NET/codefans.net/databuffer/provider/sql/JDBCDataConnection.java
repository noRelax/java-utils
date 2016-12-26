/*
 * $Id: JDBCDataConnection.java,v 1.11 2006/01/13 00:55:05 rbair Exp $
 *
 * Copyright 2005 Sun Microsystems, Inc., 4150 Network Circle,
 * Santa Clara, California 95054, U.S.A. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */

package org.jdesktop.databuffer.provider.sql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.InitialContext;

import org.jdesktop.databuffer.DataConnection;

/**
 * An implementation of the DataConnection for interacting with
 * a local database. This implementation is "local", meaning that it is
 * written and optimized for low latency database access, such as for an in
 * memory database, or one on a local network.
 * <p/>
 * @author rbair
 */
public class JDBCDataConnection extends DataConnection {
    /** The Logger */
    private static final Logger LOG = Logger.getLogger(JDBCDataConnection.class.getName());
    
    /**
     * The connection to the database
     */
    private Connection conn;
    /**
     * This is a mutex used to control access to the conn object.
     * Note that I create a new String object explicitly. This is done
     * so that a single mutex is unique for each JDBCDataConnection
     * instance.
     */
    private final Object connMutex = new String("Connection_Mutex");
    /**
     * If used, defines the JNDI context from which to get a connection to
     * the data base
     */
    private String jndiContext;
    /**
     * When using the DriverManager to connect to the database, this specifies
     * the URL to use to make that connection. URL's are specific to the
     * JDBC vendor.
     */
    private String url;
    /**
     * When using the DriverManager to connect to the database, this specifies
     * the user name to log in with.
     */
    private String userName;
    /**
     * When using the DriverManager to connect to the database, this specifies
     * the password to log in with.
     */
    private String password;
    /**
     * When using the DriverManager to connect to the database, this specifies
     * any additional properties to use when connecting.
     */
    private Properties properties;
    
    /**
     * Create a new DatabaseDataStoreConnection. Be sure to set the JDBC connection
     * properties (user name, password, connection method, etc)
     * prior to connecting this object.
     */
    public JDBCDataConnection() {
    }
    
    /**
     * Create a new JDBCDataConnection and initializes it to connect to a
     * database using the given params.
     * @param driver
     * @param url
     * @param user
     * @param passwd
     */
    public JDBCDataConnection(String driver, String url, String user,
            String passwd) {
        try {
            Class.forName(driver);
        } catch (Exception e) {
            LOG.log(Level.WARNING, "The driver passed to the " +
                    "JDBCDataConnection constructor could not be loaded. " +
                    "This may be due to the driver not being on the classpath", e);
        }
        this.setUrl(url);
        this.setUserName(user);
        this.setPassword(passwd);
    }
    
    /**
     * Create a new JDBCDataConnection and initializes it to connect to a
     * database using the given params.
     * @param driver
     * @param url
     * @param props
     */
    public JDBCDataConnection(String driver, String url, Properties props) {
        try {
            Class.forName(driver);
        } catch (Exception e) {
            LOG.log(Level.WARNING, "The driver passed to the " +
                    "JDBCDataConnection constructor could not be loaded. " +
                    "This may be due to the driver not being on the classpath", e);
        }
        this.setUrl(url);
        this.setProperties(props);
    }
    
    /**
     * Create a new JDBCDataConnection and initializes it to connect to a
     * database using the given params.
     * @param jndiContext
     * @param user
     * @param passwd
     */
    public JDBCDataConnection(String jndiContext, String user, String passwd) {
        this.jndiContext = jndiContext;
        this.setUserName(user);
        this.setPassword(passwd);
    }
    
    
    /**
     * @return the JDBC connection url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url set the JDBC connection url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the user name used to connect to the database
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName used to connect to the database
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password used to connect to the database
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password used to connect to the database
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return JDBC connection properties
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * @param properties miscellaneous JDBC properties to use when connecting
     *        to the database via the JDBC driver
     */
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    /**
     * Connect to the database. This method attempts to connect via jndiContext
     * first, if possible. If not, then it tries to connect by using the
     * DriverManager.
     */
    protected void connect() throws Exception {
        //if the jndiContext is not null, then try to get the DataSource to use
        //from jndi
        if (jndiContext != null) {
            try {
                connectByJNDI();
            } catch (Exception e) {
                try {
                    connectByDriverManager();
                } catch (Exception ex) {
                    throw new Exception("Failed to connect to the database", e);
                }
            }
        } else {
            try {
                connectByDriverManager();
            } catch (Exception ex) {
                throw new Exception("Failed to connect to the database", ex);
            }
        }
    }
    
    /**
     * Attempts to get a JDBC Connection from a JNDI javax.sql.DataSource, using
     * that connection for interacting with the database.
     * @throws Exception
     */
    private void connectByJNDI() throws Exception {
        InitialContext ctx = new InitialContext();
        javax.sql.DataSource ds = (javax.sql.DataSource)ctx.lookup(jndiContext);
        synchronized(connMutex) {
            try {
                conn = ds.getConnection();
            } catch (Exception e) {
                conn = ds.getConnection(getUserName(), getPassword());
            }
//            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        }
    }
    
    /**
     * Attempts to get a JDBC Connection from a DriverManager. If properties
     * is not null, it tries to connect with those properties. If that fails,
     * it then attempts to connect with a user name and password. If that fails,
     * it attempts to connect without any credentials at all.
     * <p>
     * If, on the other hand, properties is null, it first attempts to connect
     * with a username and password. Failing that, it tries to connect without
     * any credentials at all.
     * @throws Exception
     */
    private void connectByDriverManager() throws Exception {
        synchronized(connMutex) {
            if (getProperties() != null) {
                try {
                    conn = DriverManager.getConnection(getUrl(), getProperties());
                    conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
                } catch (Exception e) {
                    try {
                        conn = DriverManager.getConnection(getUrl(), getUserName(), getPassword());
                        conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
                    } catch (Exception ex) {
                        conn = DriverManager.getConnection(getUrl());
                        conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
                    }
                }
            } else {
                try {
                    conn = DriverManager.getConnection(getUrl(), getUserName(), getPassword());
                    
                } catch (Exception e) {
	            LOG.log(Level.FINE, "Couldn't connect with the supplied " +
			    "user name and password. Attempting to connect " +
			    "annonymously.", e);
                    //try to connect without using the userName and password
                    conn = DriverManager.getConnection(getUrl());
                    
                }
            }
        }
    }
    
    /**
     * Disconnects from the database and causes all of the attached DataModels
     * to flush their contents.
     */
    protected void disconnect() throws Exception {
        synchronized(connMutex) {
            if (conn != null) {
                conn.close();
            }
        }
    }
    
    public ResultSet executeQuery(PreparedStatement ps) {
        synchronized(connMutex) {
            if (conn != null) {
                try {
                    return ps.executeQuery();
                } catch (Exception e) {
		    LOG.log(Level.WARNING, "Failed to execute query {0}", ps);
		    LOG.log(Level.WARNING, e.getMessage(), e);
                }
            }
        }
        return null;
    }

    public int executeUpdate(PreparedStatement ps) {
        synchronized(connMutex) {
            if (conn != null) {
                try {
                    return ps.executeUpdate();
                } catch (Exception e) {
		    LOG.log(Level.WARNING, "Failed to execute update {0}", ps);
		    LOG.log(Level.WARNING, e.getMessage(), e);
                }
            }
            // TODO: hmm. may want a note somewhere that if connection not established, we will fail silently (PWW 04/25/05)
        }
        return 0;
    }
    
    public PreparedStatement prepareStatement(String sql) throws Exception {
    	synchronized(connMutex) {
            if (conn != null) {
                return conn.prepareStatement(sql);
            }
        }
        return null;
    }
    
    public void commit() {
        try {
            conn.commit();
        } catch (Exception e) {
	    LOG.log(Level.WARNING, "Failed to commit", e);
        }
    }
    
    public Connection getConnection() {
        return conn;
    }
}
