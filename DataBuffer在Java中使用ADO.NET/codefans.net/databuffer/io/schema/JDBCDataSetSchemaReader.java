/*
 * $Id: JDBCDataSetSchemaReader.java,v 1.1.2.2 2006/01/21 19:49:45 david_hall Exp $
 *
 * Copyright 2004 Sun Microsystems, Inc., 4150 Network Circle,
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
package org.jdesktop.databuffer.io.schema;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jdesktop.databuffer.DataColumn;
import org.jdesktop.databuffer.DataRelation;
import org.jdesktop.databuffer.DataSet;
import org.jdesktop.databuffer.DataTable;
import org.jdesktop.databuffer.provider.sql.JDBCDataConnection;
import org.jdesktop.databuffer.provider.sql.SQLDataProvider;


/**
 * An <CODE>JDBCDataSetSchemaReader</CODE> instantiates a {@link org.jdesktop.databuffer.DataSet} from
 * a schema stored in an XML file; the schema is read from the JDBC metadata classes (see
 * {@link java.sql.DatabaseMetaData} and {@link java.sql.ResultSetMetaData}), and the accuracy
 * of the schema depends on the quality of the JDBC driver used.
 * <P>Each instance of <CODE>JDBCDataSetSchemaReader</CODE> works against a single JDBCDataConnection
 * connected to through a JDBC driver to a JDBC datasource (presumably a relational database).
 * Once instantiated against that JDBCConnection, the reader
 * methods (e.g. {@link #readDataSet()}) may be used to load the <CODE>DataSet</CODE>.
 * <CODE>DataSetSchemaReader</CODE> classes do not load data, only the <CODE>DataSet</CODE> schema.
 * Typical usage:
 *
 * <CODE>
 * JDBCDataConnection jdbcConn =  new JDBCDataConnection("org.apache.derby.jdbc.EmbeddedDriver", "jdbc:derby:testDB", "sa", "");
 * JDBCDataSetSchemaReader reader = new JDBCDataSetSchemaReader(jdbcConn);
 * DataSet dataSet = reader.readDataSet();
 * </CODE>
 *
 * <P>You can also use the one-line methods in DataSetUtility, such as 
 * {@link org.jdesktop.databuffer.util.DataSetUtility#readDataSetFromJDBC(JDBCDataConnection)}.
 *
 * @author Adam Barclay
 * @author Patrick Wright
 */
public class JDBCDataSetSchemaReader implements DataSetSchemaReader {
    /** The JDBCDataConnection from which we read schema metadata. */
    private JDBCDataConnection jdbcConn;
    
    /** The catalog name from which we read schema data, if supplied, may be null. */
    private String catalog;
    
    /** The schema name from which we read schema data, if supplied, may be null. */
    private String schema;
    
    /**
     * Instantiates a new <CODE>JDBCDataSetSchemaReader</CODE> for a JDBCDataConnection to a JDBC data source,
     * presumably a relational database, using the default catalog and schema as understood by the JDBC driver (e.g. null).
     * The data connection cannot be changed once the reader is instantiated. The connection
     * will be opened as necessary to read the metadata, and returned to its orignal connection state after reading. As the JDBC
     * connections are not necessarily thread-safe, calling classes should synchronize around the schema loading process.
     * See class documentation for more details.
     *
     * @param jdbcConn A {@link org.jdesktop.databuffer.provider.sql.JDBCDataConnection}.
     */
    public JDBCDataSetSchemaReader(JDBCDataConnection jdbcConn) {
        this(jdbcConn, null, null);
    }
    
    /**
     * Instantiates a new <CODE>JDBCDataSetSchemaReader</CODE>, for a JDBCDataConnection to a JDBC data source,
     * presumably a relational database, for a given catalog and schema in that database.
     * The data connection cannot be changed once the reader is instantiated. The connection
     * will be opened as necessary to read the metadata, and returned to its orignal connection state after reading. As the JDBC
     * connections are not necessarily thread-safe, calling classes should synchronize around the schema loading process.
     * See class documentation for more details.
     *
     * @param catalog Name of the catalog to read the schema from. The meaning of "catalog" is RDBMS-dependent;
     * consult the documentation for your data source and JDBC driver.
     *
     * @param schema Name of the schema to read the DataSet schema from. The meaning of "schema" is RDBMS-dependent;
     * consult the documentation for your data source and JDBC driver.
     *
     * @param jdbcConn A {@link org.jdesktop.databuffer.provider.sql.JDBCDataConnection}.
     */
    public JDBCDataSetSchemaReader(JDBCDataConnection jdbcConn, String catalog, String schema)  {
        this.jdbcConn = jdbcConn;
        this.catalog = catalog;
        this.schema = schema;
    }
    
    
    /**
     * Instantiates a new {@link org.jdesktop.databuffer.DataSet} from the <CODE>JDBCDataConnection</CODE>
     * specified in the constructor. If the schema contains multiple <CODE>DataTables</CODE>
     * or <CODE>DataRelations</CODE>, all of them are loaded
     * from the schema into the new <CODE>DataSet</CODE>. Relations are read for a table using the
     * {@link java.sql.DatabaseMetaData#getImportedKeys(String catalog, String schema, String tableName)}. For a
     * relational database, this would mean a foreign key constraint on the table. If such metadata does not exist
     * for table relations, you will need to create them yourself after instantiating the <CODE>DataSet</CODE>.
     * This method is probably not thread-safe for multiple threads accessing the same <CODE>JDBCDataConnection</CODE>
     * and underlying <CODE>java.sql.Connection</CODE>.
     *
     * @return A new <CODE>DataSet</CODE> instantiated from the <CODE>JDBCDataConnection</CODE>
     * specified for the implementing class, including all tables and relations in the schema.
     *
     * @throws org.jdesktop.databuffer.io.schema.SchemaReaderException If an error occurred while loading the schema.
     */
    public DataSet readDataSet() throws SchemaReaderException {
        boolean connectState = jdbcConn.isConnected();
        if ( !connectState ) jdbcConn.setConnected(true);
        
        DataSet dataSet = null;
        try {
            Connection conn = jdbcConn.getConnection();
            DatabaseMetaData dbMetaData = conn.getMetaData();
            
            String[] typeArray = {"TABLE"};
            ResultSet tableResultSet = dbMetaData.getTables(null, null, "%", typeArray);
            List<String> tables = DataSetIOUtility.extractColumn(tableResultSet, "TABLE_NAME");
            tableResultSet.close();
            String[] array = (String[])tables.toArray(new String[tables.size()]);
            
            dataSet = readDataSet(array);
        } catch (SchemaReaderException e) {
            throw e;
        } catch (Exception e) {
            throw new SchemaReaderException("Could not read schemas to create DataSet", e);
        } finally {
            jdbcConn.setConnected(connectState);
        }
        return dataSet;
    }
    
    /**
     * Instantiates a new {@link org.jdesktop.databuffer.DataSet} from the <CODE>JDBCDataConnection</CODE>
     * specified in the constructor, for the tables and columns given in the <CODE>tableNames</CODE> parameter.
     * from the schema into the new <CODE>DataSet</CODE>. Relations are read for a table using the
     * {@link java.sql.DatabaseMetaData#getImportedKeys(String catalog, String schema, String tableName)}. For a
     * relational database, this would mean a foreign key constraint on the table. If such metadata does not exist
     * for table relations, you will need to create them yourself after instantiating the <CODE>DataSet</CODE>.
     * This method is probably not thread-safe for multiple threads accessing the same <CODE>JDBCDataConnection</CODE>
     * and underlying <CODE>java.sql.Connection</CODE>.
     *
     * @param tableNames One or more table names to load from the schema. Each name can be either a table, or a
     * table and column, dot-separated. If no columns are specified, all columns in the table are
     * read from the schema; if columns are listed, only those columns are loaded. <CODE>DataRelations</CODE>
     * for those tables and columns loaded from the schema are also instantiated automatically through
     * {@link #addRelations(org.jdesktop.databuffer.DataSet, String...)}.
     *
     * @return A new <CODE>DataSet</CODE> instantiated from the <CODE>JDBCDataConnection</CODE>
     * specified for the implementing class, including only the tables and columns listed in the
     * <CODE>tableNames</CODE> parameter.
     *
     * @throws org.jdesktop.databuffer.io.schema.SchemaReaderException If an error occurred while loading the schema.
     */
    public DataSet readDataSet(String... tableNames) throws SchemaReaderException{
        boolean connectState = jdbcConn.isConnected();
        if ( !connectState ) jdbcConn.setConnected(true);
        
        DataSet dataSet = new DataSet();
        try {
            addTables(dataSet, tableNames);
            
            addRelations(dataSet, tableNames);
        } catch (SchemaReaderException e) {
            throw e;
        } catch (Exception e) {
            throw new SchemaReaderException("Could not read schemas to create DataTables", e);
        } finally {
            jdbcConn.setConnected(connectState);
        }
        return dataSet;
    }
    
    
    /**
     * Appends one or more {@link org.jdesktop.databuffer.DataTable} to a {@link org.jdesktop.databuffer.DataSet}
     * from the schema represented by this reader. If the table, by name, is found in the <CODE>DataSet</CODE>,
     * the table is skipped; if you want to re-add a table (for example, to pick up changes in table structure),
     * drop the table from the <CODE>DataSet</CODE> before calling this method.
     *
     * @return A <CODE>List</CODE> of the table names that were actually found. You can check this to
     * verify that all tables requested were loaded.
     *
     * @param dataSet The <CODE>DataSet</CODE> to which to add the tables.
     *
     * @param tableNames One or more table names to load from the schema and add to the <CODE>DataSet</CODE>.
     * Each name can be either a table, or a table and column, dot-separated.
     * If no columns are specified, all columns in the table are read from the schema;
     * if columns are listed, only those columns are loaded. <CODE>DataRelations</CODE>
     * for those tables and columns are not loaded from the schema; to that end, use
     * {@link #addRelations(DataSet, String...)}.
     *
     * @throws org.jdesktop.databuffer.io.schema.SchemaReaderException If an error occurred while loading the schema.
     */
    public List<String> addTables(DataSet dataSet, String... tableNames) throws SchemaReaderException {
        List<String> loadedTables = new ArrayList<String>(tableNames.length);
        boolean connectState = jdbcConn.isConnected();
        if ( !connectState ) jdbcConn.setConnected(true);
        
        try {
            Connection conn = jdbcConn.getConnection();
            DatabaseMetaData dbMetaData = conn.getMetaData();
            
            // Used to store the list of tables that are to be retrieved, along
            // with the list of columns (or explictly null if all columns should
            // be included)
            Map<String, Set<String>> tables = new HashMap<String, Set<String>>(5);
            
            for (String name : tableNames) {
                int dotIdx = name.indexOf(".");
                
                if (dotIdx != -1) {
                    String table = name.substring(0, dotIdx);
                    String col = name.substring(dotIdx + 1);
                    
                    // If nothing has been set before (never seen this table)
                    // then setup the column set
                    if (!tables.containsKey(table)) {
                        tables.put(table, new HashSet<String>());
                    }
                    
                    // If the value has been set before it may have been
                    // explictly null to indicate all columns to be included.
                    if (tables.get(table) != null) {
                        tables.get(table).add(col);
                    }
                } else {
                    tables.put(name, null);
                }
            }
            
            // Create all of the DataTables by building simple SELECT statements
            // to retrieve information for one table
            for (String tableName : tables.keySet()) {
                if ( dataSet.getTable(tableName) != null ) {
                    System.out.println("SKIPPING TABLE " + tableName + " ALREADY IN DATASET");
                    continue;
                }
                Set<String> cols = tables.get(tableName);
                
                // check to see whether the entire table is to be included, or
                // only some of the table
                List colNames = ( cols == null ? null : new ArrayList(cols));
                String tableSelectCmd = getTableSelectNoRowsCommand(tableName, colNames); //"SELECT * FROM " + tableName + " WHERE 1 = 0";
                DataTable dataTable = readDataTable(dataSet, tableName, tableSelectCmd);
                
                SQLDataProvider dataProvider = new SQLDataProvider(tableName);
                dataProvider.setConnection(jdbcConn);
                dataTable.setDataProvider(dataProvider);
                
                loadedTables.add(tableName);
            }
        } catch (SchemaReaderException e) {
            throw e;
        } catch (Exception e) {
            throw new SchemaReaderException("Could not read schemas to create DataTables", e);
        } finally {
            jdbcConn.setConnected(connectState);
        }
        return loadedTables;
    }
    
    /**
     * Adds all {@link org.jdesktop.databuffer.DataRelation} for the named {@link org.jdesktop.databuffer.DataTable}
     * to the specified {@link org.jdesktop.databuffer.DataSet}, as read from the schema which this reader
     * is reading from. All <CODE>DataRelations</CODE> for each table are dropped from the <CODE>DataSet</CODE>
     * and re-added.
     *
     * @return A <CODE>List</CODE> of the <CODE>DataRelation</CODE> names loaded from the schema.
     *
     * @param dataSet The <CODE>DataSet</CODE> to which to add the relations.
     *
     * @param tableNames One or more table names for which to read relations from the schema. Only those relations
     * for columns in the DataTable (as currently loaded) are added.
     *
     * @throws org.jdesktop.databuffer.io.schema.SchemaReaderException If an error occurred while loading the schema.
     */
    public List<String> addRelations(DataSet dataSet, String... tableNames) throws SchemaReaderException {
        List<String> loadedRelations = new ArrayList<String>();
        
        boolean connectState = jdbcConn.isConnected();
        if ( !connectState ) jdbcConn.setConnected(true);
        Connection conn = jdbcConn.getConnection();
        
        // relations
        // This list is used to search for datatables using a case-insensitive
        // comparison to avoid problems of databases changing the casing when
        // returning relation metadata (i.e. MySQL)
        try {
            List<DataTable> dataTables = dataSet.getTables();
            
            //for (DataTable dataTable : dataTables) {
            for ( String tableName : tableNames) {
                DataTable dataTable = dataSet.getTable(tableName);
                
                DatabaseMetaData md = conn.getMetaData();
                
                // Uses the current catalog, as noted in the javadoc
                ResultSet rs = md.getImportedKeys(conn.getCatalog(), null, dataTable.getName());
                
                while (rs.next()) {
                    // TODO for now, DataRelations only support a single column,
                    // so if the sequence is > 1, skip it
                    if (rs.getInt("KEY_SEQ") > 1) {
                        continue;
                    }
                    
                    // search for table in DataSet, case-insensitive
                    DataTable parentTable = null;
                    String pkTableName = rs.getString("PKTABLE_NAME");
                    
                    for (DataTable t : dataTables) {
                        if (t.getName().equalsIgnoreCase(pkTableName)) {
                            parentTable = t;
                            break;
                        }
                    }
                    
                    // May occur if tableName was not initially selected
                    if (parentTable == null) {
                        continue;
                    }
                    
                    DataColumn parentColumn = parentTable.getColumn(rs.getString("PKCOLUMN_NAME"));
                    DataColumn childColumn = dataTable.getColumn(rs.getString("FKCOLUMN_NAME"));
                    if (parentColumn != null && childColumn != null && parentColumn != childColumn) {
                        String name = rs.getString("FK_NAME");
                        DataRelation rel = dataSet.createRelation(rs.getString("FK_NAME"), parentColumn, childColumn);
                        loadedRelations.add(rel.getName());
                    }
                }
            }
            
        } catch (Exception e) {
            throw new SchemaReaderException("Could not create data relations", e);
        } finally {
            jdbcConn.setConnected(connectState);
        }
        return loadedRelations;
    }
    
    /**
     *
     * Returns the SELECT statement used to query the given table, without rows being returned; this may vary
     * between databases. The default is to select "where 1 = 0". You may override this in a subclass to provide
     * a database-specific SELECT command, e.g. "SELECT * FROM <table> LIMIT 0".
     *
     * @param tableName The table from which to select.
     *
     * @param columnNames The columns to select for that table, null means all columns.
     *
     * @return A SELECT statement with no parameters, selecting from the table for the columns specified,
     * and returning no rows.
     */
    protected String getTableSelectNoRowsCommand(String tableName, List<String> columnNames) {
        String comma = ", \n";
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT ");
        
        if ( columnNames == null ) {
            builder.append("*\n");
        } else {
            for ( String col : columnNames ) {
                builder.append(col + comma);
            }
            builder.delete(builder.length() - comma.length(), builder.length() - 1);
        }
        
        builder.append("FROM " + tableName + "\n");
        builder.append("WHERE 1 = 0");
        
        return builder.toString();
    }
    
    /**
     * Adds a single named <CODE>DataTable</CODE> to the <CODE>DataSet</CODE>.
     *
     * @return The new <CODE>DataTable</CODE> added to the <CODE>DataSet</CODE>.
     *
     * @param dataSet The <CODE>DataSet</CODE> to add to.
     *
     * @param tableName The table name to read from the schema.
     *
     * @param tableSelectCmd The SELECT command to retrieve from the table, with no rows.
     *
     * @throws org.jdesktop.databuffer.io.schema.SchemaReaderException If an error occurred while loading the schema.
     */
    private DataTable readDataTable(DataSet dataSet, String tableName, String tableSelectCmd) throws SchemaReaderException {
        boolean connectState = jdbcConn.isConnected();
        if ( !connectState ) jdbcConn.setConnected(true);
        Connection conn = jdbcConn.getConnection();
        DataTable dataTable = null;
        try {
            DatabaseMetaData dbMetaData = conn.getMetaData();
            Statement stmt = conn.createStatement();
            ResultSetMetaData colRSMD = stmt.executeQuery(tableSelectCmd).getMetaData();
            dataTable = dataSet.createTable(tableName);
            
            int colCnt = colRSMD.getColumnCount();
            String catalog = colRSMD.getCatalogName(1);
            String schema = colRSMD.getSchemaName(1);;
            for ( int i=1; i <= colCnt; i++ ) {
                String colName = colRSMD.getColumnName(i);
                DataColumn newColumn =
                    dataTable.createColumn(DataSetIOUtility.getType(colRSMD.getColumnType(i)),
                                           colName);
                
                newColumn.setRequired(colRSMD.isNullable(i) == ResultSetMetaData.columnNullable);
                newColumn.setReadOnly(colRSMD.isReadOnly(i));
                
                String columnPattern = "%";
                ResultSet colRS = dbMetaData.getColumns(catalog, schema, tableName, columnPattern );
                if ( !colRS.next()) continue;
                
                newColumn.setDefaultValue(colRS.getObject("COLUMN_DEF"));
                colRS.close();
            }
            
            ResultSet rs = dbMetaData.getPrimaryKeys(catalog, schema, tableName);
            while (rs.next()) {
                DataColumn col = dataTable.getColumn(rs.getString("COLUMN_NAME"));
                
                // Happens if a key column is not SELECTed
                if (col != null) {
                    col.setKeyColumn(true);
                }
            }
            SQLDataProvider dataProvider = new SQLDataProvider(tableName);
            dataProvider.setConnection(jdbcConn);
            dataTable.setDataProvider(dataProvider);
        } catch (Exception e) {
            throw new SchemaReaderException("Could not create DataTable for table named " + tableName, e);
        } finally {
            jdbcConn.setConnected(connectState);
        }
        return dataTable;
    }    
}
