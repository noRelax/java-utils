/*
 * $Id: SQLDataProvider.java,v 1.13 2005/10/26 15:30:10 rbair Exp $
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
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jdesktop.databuffer.DataColumn;
import org.jdesktop.databuffer.DataProvider;
import org.jdesktop.databuffer.DataRow;
import org.jdesktop.databuffer.DataTable;
import org.jdesktop.databuffer.event.TableChangeEvent;
import org.jdesktop.databuffer.provider.LoadTask;
import org.jdesktop.databuffer.provider.SaveTask;

/**
 * SQL based DataProvider for a JDNC DataSet. This implementation handles
 * retrieving values from a database table, and persisting changes back
 * to the table.
 * 
 * @author rbair
 */
public class SQLDataProvider extends DataProvider {
	/** The Logger */
	private static final Logger LOG = Logger.getLogger(SQLDataProvider.class
			.getName());

	private Map<String, String> columnMappings = new HashMap<String, String>();

	/**
	 * Creates a new instance of SQLDataProvider 
	 */
	public SQLDataProvider() {
	}

	public SQLDataProvider(String tableName) {
		TableCommand tableCommand = new TableCommand(tableName);
		setCommand(tableCommand);
	}

	public SQLDataProvider(String tableName, String whereClause) {
		TableCommand tableCommand = new TableCommand(tableName, whereClause);
		setCommand(tableCommand);
	}

	public void addColumnNameMapping(String pseudonym, String realName) {
		columnMappings.put(pseudonym, realName);
	}

	public void removeColumnNameMapping(String pseudonym) {
		columnMappings.remove(pseudonym);
	}

	/**
	 * @inheritDoc
	 */
	protected LoadTask createLoadTask(DataTable[] tables) {

		return new LoadTask(tables) {
			protected void readData(DataTable[] tables) throws Exception {
				JDBCDataConnection conn = (JDBCDataConnection) getConnection();
				if (conn == null) {
					//no connection, short circuit
					return;
				}
				if (getCommand() == null) {
					//there isn't any command to run, so short circuit the method
					return;
				}
				//TODO when selectCommand exists, add it to the check here

				//set the progess count
				setMinimum(0);
				setMaximum(tables.length);
				//construct and execute a resultset for each table in turn.
				//as each table is finished, call scheduleLoad.

				for (DataTable table : tables) {
					try {

						PreparedStatement stmt = ((AbstractSqlCommand) getCommand())
								.getSelectStatement(conn);
						ResultSet rs = stmt.executeQuery();

						ResultSetMetaData md = rs.getMetaData();                         
						
						//table.getColumns contains all of the columns in the
						//table. columnMappings contains a mapping of pseudonym ->
						//dataTable column name. The set of column names is
						//all of the real column names and all of the pseudonyms.
						Set<String> colNames = new HashSet<String>();
						
						colNames.addAll(columnMappings.keySet());
						
						
						//如果没有为table设置列，那么填充时使用数据集的列结构
						if (table.getColumns().size() ==0){
						   for (int i = 0; i < md.getColumnCount(); i++){
							  table.createColumn(Object.class,md.getColumnName(i + 1));	
						   }
						}
						
						 for (DataColumn col : table.getColumns()) {
								  colNames.add(col.getName());
						 }
																	
						//collect the column names from the result set so that
						//I can retrieve the data from the result set into the
						//column based on matching column names
						
						List<String> names = new ArrayList<String>();
						
						for (String name : colNames) {
							for (int i = 0; i < md.getColumnCount(); i++) {
								if (name.equalsIgnoreCase(md
										.getColumnName(i + 1))) {
									names.add(name);
								}
							}
						}
						
						//iterate over the result set. Every 50 items, schedule a load
						List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>(
								60);
						while (rs.next()) {
							if (rows.size() >= 50) {
								LoadItem item = new LoadItem<List<Map<String, Object>>>(
										table, rows);
								scheduleLoad(item);
								rows = new ArrayList<Map<String, Object>>(60);
							}
							//create a row
							Map<String, Object> row = new HashMap<String, Object>();
							for (String name : names) {
								row.put(name, rs.getObject(name));
							}
							rows.add(row);
						}
						//close the result set
						rs.close();
						//load the remaining items
						LoadItem item = new LoadItem<List<Map<String, Object>>>(
								table, rows);
						scheduleLoad(item);
					} catch (Exception e) {
						LOG.log(Level.WARNING,
								"Failed to read data for table {0}", table);
						LOG.log(Level.WARNING, e.getMessage(), e);
					}
					setProgress(getProgress() + 1);
				}
				setProgress(getMaximum());
			}

			/**
			 * @inheritDoc
			 */
			protected void loadData(LoadItem[] items) {
				int i =1;
				for (LoadItem<List<Map<String, Object>>> item : items) {
					for (Map<String, Object> row : item.data) {
						DataRow r = item.table.appendRowNoEvent();
						for (String col : row.keySet()) {
							if (columnMappings.containsKey(col)) {
								col = columnMappings.get(col);
							}
							r.setValue(col, row.get(col));
						}
						r.setStatus(DataRow.DataRowStatus.UNCHANGED);

						// CLEAN
						item.table.fireDataTableChanged(TableChangeEvent
								.newRowAddedEvent(item.table, r));
					}
					item.table.fireDataTableChanged(TableChangeEvent
							.newLoadCompleteEvent(item.table));
				}
			}
		};
	}

	/**
	 * @inheritDoc
	 */
	protected SaveTask createSaveTask(DataTable[] tables) {
		return new SaveTask(tables) {
			protected void saveData(DataTable[] tables) throws Exception {
				JDBCDataConnection conn = (JDBCDataConnection) getConnection();
				if (conn == null) {
					//no connection, short circuit
					return;
				}
				if (getCommand() == null) {
					//there isn't any command to run, so short circuit the method
					return;
				}
				//TODO when selectCommand exists, add it to the check here

				//set the progess count
				setMinimum(0);
				setMaximum(tables.length);
				for (DataTable table : tables) {
					//fetch the set of rows from the table
					//NOTE: must wrap the returned list in a new ArrayList to
					//avoid a ConcurrentModificationException when a row is
					//deleted from the DataTable
					List<DataRow> rows = new ArrayList<DataRow>(table.getRows());
					//for each row, either insert it, update it, delete it, or
					//ignore it, depending on the row flag
					for (DataRow row : rows) {
						PreparedStatement stmt = null;
						switch (row.getStatus()) {
							case UPDATED :
								stmt = ((AbstractSqlCommand) getCommand())
										.getUpdateStatement(conn, row);
								conn.executeUpdate(stmt);
								row.setStatus(DataRow.DataRowStatus.UNCHANGED);
								break;
							case INSERTED :
								stmt = ((AbstractSqlCommand) getCommand())
										.getInsertStatement(conn, row);
								conn.executeUpdate(stmt);
								row.setStatus(DataRow.DataRowStatus.UNCHANGED);
								break;
							case DELETED :
								stmt = ((AbstractSqlCommand) getCommand())
										.getDeleteStatement(conn, row);
								conn.executeUpdate(stmt);
								table.discardRow(row);
								break;
							default :
								//do nothing
								break;
						}
					}
					table.fireDataTableChanged(TableChangeEvent
							.newSaveCompleteEvent(table));
					setProgress(getProgress() + 1);
				}
				setProgress(getMaximum());
				conn.commit();
			}
		};
	}
}
