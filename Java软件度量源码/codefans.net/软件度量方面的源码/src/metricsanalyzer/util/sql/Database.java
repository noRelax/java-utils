/* MetricsAnalyzer
 * Copyright (C) 2002  TIKE (tike.mmm.fi)
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc., 59
 * Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package metricsanalyzer.util.sql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import metricsanalyzer.util.fun.VoidToVoid;

/**
 * Base for simple Database abstraction classes.
 */
public class Database {
  protected Database(String driver,
                     String url,
                     String user,
                     String password) throws Exception {
    this.driver = driver;
    this.url = url;
    this.user = user;
    this.password = password;

    connect();
  }

  protected void connect() throws Exception {
    if (null != connection)
      return;

    if (null != driver && !driver.equals(""))
      Class.forName(driver);

    connection = DriverManager.getConnection(url, user, password);
    statement = connection.createStatement();
  }

  public void close() throws Exception {
    if (null == connection)
      return;

    statement = null;

    connection.close();
    connection = null;
  }

  protected ResultSet query(String sql) throws Exception {
    log(sql + "\n");

    return statement.executeQuery(sql);
  }

  protected int queryInt(String sql) throws Exception {
    ResultSet rs = query(sql);

    if (rs.next())
      return rs.getInt(1);
    else
      throw new RuntimeException("Result set was empty!");
  }

  protected void executeTransaction(VoidToVoid transaction) throws Exception {
    JDBC.executeTransaction(connection, transaction);
  }

  protected void update(String sql) throws Exception {
    log(sql + "\n");

    long start = System.currentTimeMillis();
    silentUpdate(sql);
    
    log("TOOK: " + (System.currentTimeMillis() - start) / 1000.0 + " seconds\n");
  }

  protected void silentUpdate(String sql) throws Exception {
    statement.executeUpdate(sql);
  }

  protected Statement createStatement() throws Exception {
    return connection.createStatement();
  }

  protected Connection getConnection() throws Exception {
    return connection;
  }

  protected DatabaseMetaData getMetaData() throws Exception {
    return connection.getMetaData();
  }

  protected void log(String message) {
    if (logging)
      System.out.print(message);
  }

  public boolean setLogging(boolean on) {
    boolean result = logging;
    logging = on;
    return logging;
  }

  private boolean logging = false;

  public final String driver;
  public final String url;
  public final String user;
  public final String password;

  private Connection connection = null;
  private Statement statement = null;
}
