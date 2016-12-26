/*
 * $Id: SQLCommand.java,v 1.12 2005/10/25 22:44:29 rbair Exp $
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

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jdesktop.databuffer.DataColumn;
import org.jdesktop.databuffer.DataRow;

/**
 * <p>A fully customizeable DataCommand for use with a {@link SQLDataProvider}. This
 * DataCommand requires the user to specify their select, insert, update and
 * delete sql statements manually using {@link #setSelectSQL(String)}, 
 * {@link #setInsertSQL(String)}, {@link #setUpdateSQL(String)}, 
 * {@link #setDeleteSQL(String)}. 
 * 
 * <p>The SQL for these statements can
 * contain named parameters. As a DataCommand, the SQLCommand can track values for named
 * parameters ({@link org.jdesktop.databuffer.DataCommand#setParameter(String, Object)}, so that when the
 * SQLDataProvider requests the PreparedStatement to execute, the parameters in the SQL
 * statement are assigned the values stored in the DataCommand. For example, suppose your
 * SQLCommand instance was only to work with a row in the Employee table for "Jones". You'd
 * do the following:
 * <pre>
 * SQLCommand cmd = new SQLCommand();
 * cmd.setSelectSql("Select * from Employee where name = :emp-name");
 * cmd.setParameter("emp-name", "Jones");
 * </pre>
 * The INSERT, UPDATE, and DELETE statements can access any of the parameters defined in this
 * DataCommand as well.
 * 
 * <p>A subclass, {@link TableCommand} allows you to specify a simplified set of criteria to base your query on. You may,
 * for instance, simply specify the name of the table and the select, insert, update and delete queries
 * will be automatically generated for you. You may override the delete, insert, select and
 * update SQL statements in the TableCommand.</p>
 *
 * @author rbair
 */
public class SQLCommand extends AbstractSqlCommand {
    /** The Logger */
    private static final Logger LOG = Logger.getLogger(SQLCommand.class.getName());
    
    private String deleteSql;
    private String insertSql;
    private String selectSql;
    private String updateSql;
    
    /** 
     * Creates a new instance of TableCommand 
     */
    public SQLCommand() {
    }

    /**
     */
    public void setSelectSQL(String sql) {
        // TODO: this is a string, why use != (PWW 04/25/05)
        if (this.selectSql != sql) {
            String oldValue = this.selectSql;
            this.selectSql = sql;
            firePropertyChange("selectSql", oldValue, sql);
        }
    }
    
    public String getSelectSQL() {
        return selectSql;
    }
    
    /**
     */
    public void setUpdateSQL(String sql) {
        if (this.updateSql != sql) {
            String oldValue = this.updateSql;
            this.updateSql = sql;
            firePropertyChange("updateSql", oldValue, sql);
        }
    }
    
    public String getUpdateSQL() {
        return updateSql;
    }
    
    /**
     */
    public void setInsertSQL(String sql) {
        if (this.insertSql != sql) {
            String oldValue = this.insertSql;
            this.insertSql = sql;
            firePropertyChange("insertSql", oldValue, sql);
        }
    }
    
    public String getInsertSQL() {
        return insertSql;
    }
    
    /**
     */
    public void setDeleteSQL(String sql) {
        if (this.deleteSql != sql) {
            String oldValue = this.deleteSql;
            this.deleteSql = sql;
            firePropertyChange("deleteSql", oldValue, sql);
        }
    }
    
    public String getDeleteSQL() {
        return deleteSql;
    }
    
    public String[] getParameterNames() {
	return super.getParameterNames(new String[]{selectSql, updateSql, insertSql, deleteSql});
    }

    protected PreparedStatement createPreparedStatement(String parameterizedSql, JDBCDataConnection conn) throws Exception {
        //replace all of the named parameters in the sql with their
        //corrosponding values. This is done by first converting the sql
        //to proper JDBC sql by inserting '?' for each and every param.
        //As this is done, a record is kept of which parameters go with
        //which indexes. Then, the parameter values are applied.
        //map containing the indexes for each named param
    	
        Map<String,List<Integer>> indexes = new HashMap<String,List<Integer>>();
        String sql = constructSql(parameterizedSql, indexes);
        
        PreparedStatement ps = conn.prepareStatement(sql);

        //now, apply the given set of parameters
        
        for (String paramName : getParameterNames()) {
            List<Integer> list = indexes.get(paramName);
            if (list != null) {
                for (int index : list) {
                    ps.setObject(index + 1, getParameter(paramName));
                }
            }
        }        
        return ps;
    }
    
    protected PreparedStatement getSelectStatement(JDBCDataConnection conn) throws Exception {
	if (selectSql == null) {
	    //this SQLCommand has not been configured, throw an exception
	    throw new Exception("SQLCommand not configured with a select sql statement");
	}
	
  
	
    try {
	    return createPreparedStatement(selectSql, conn);
	} catch (Exception e) {
	    LOG.log(Level.WARNING, "Problem with select SQL statement {0}", deleteSql);
	    LOG.log(Level.WARNING, e.getMessage(), e);
	    return null;
	}
    }
    
    protected PreparedStatement getUpdateStatement(JDBCDataConnection conn, DataRow row) throws Exception {
	if (updateSql == null) {
	    //this SQLCommand has not been configured, throw an exception
	    throw new Exception("SQLCommand not configured with an update sql statement");
	}

	try {
	    Map<String,Object> values = new HashMap<String,Object>();
	    //iterate over all of the columns in the row.
	    List<DataColumn> columns = row.getTable().getColumns();
	    for (int i=0; i<columns.size(); i++) {
		DataColumn col = columns.get(i);
		values.put(col.getName(), row.getValue(col));
	    }

	    //do the where clause
	    int keyColCount = 0;
	    for (int i=0; i<columns.size(); i++) {
		DataColumn col = columns.get(i);
		if (col.isKeyColumn()) {
		    values.put("orig_" + col.getName(), row.getReferenceValue(col));
		    keyColCount++;
		}
	    }

	    return super.prepareStatement(updateSql, values, conn);
	} catch (Exception e) {
	    LOG.log(Level.WARNING, "Problem with update SQL statement {0}", deleteSql);
	    LOG.log(Level.WARNING, e.getMessage(), e);
	    return null;
        }
    }

    protected PreparedStatement getInsertStatement(JDBCDataConnection conn, DataRow row) throws Exception {
	if (insertSql == null) {
	    //this SQLCommand has not been configured, throw an exception
	    throw new Exception("SQLCommand not configured with an insert sql statement");
	}

	try {
	    Map<String,Object> values = new HashMap<String,Object>();
	    for (DataColumn col : row.getTable().getColumns()) {
		values.put(col.getName(), row.getValue(col));
	    }
	    return super.prepareStatement(insertSql, values, conn);
	} catch (Exception e) {
	    LOG.log(Level.WARNING, "Problem with insert SQL statement {0}", deleteSql);
	    LOG.log(Level.WARNING, e.getMessage(), e);
	    return null;
	}
    }

    protected PreparedStatement getDeleteStatement(JDBCDataConnection conn, DataRow row) throws Exception {
	if (deleteSql == null) {
	    //this SQLCommand has not been configured, throw an exception
	    throw new Exception("SQLCommand not configured with a delete sql statement");
	}

	try {
	    Map<String,Object> values = new HashMap<String,Object>();
	    List<DataColumn> columns = row.getTable().getColumns();
	    for (int i=0; i<columns.size(); i++) {
		DataColumn col = columns.get(i);
		if (col.isKeyColumn()) {
		    values.put("orig_" + col.getName(), row.getReferenceValue(col));
		}
	    }
	    //add in the normal params...
	    return super.prepareStatement(deleteSql, values, conn);
	} catch (Exception e) {
	    LOG.log(Level.WARNING, "Problem with delete SQL statement {0}", deleteSql);
	    LOG.log(Level.WARNING, e.getMessage(), e);
	    return null;
	}
    }
}