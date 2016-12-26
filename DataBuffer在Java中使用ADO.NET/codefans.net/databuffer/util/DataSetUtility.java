/*
 * $Id: DataSetUtility.java,v 1.2.2.3 2006/01/21 19:45:19 david_hall Exp $
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
package org.jdesktop.databuffer.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jdesktop.databuffer.DataColumn;
import org.jdesktop.databuffer.DataRelation;
import org.jdesktop.databuffer.DataSet;
import org.jdesktop.databuffer.DataTable;
import org.jdesktop.databuffer.io.schema.DataSetIOUtility;
import org.jdesktop.databuffer.io.schema.DataSetSchemaReader;
import org.jdesktop.databuffer.io.schema.DataSetSchemaWriter;
import org.jdesktop.databuffer.io.schema.JDBCDataSetSchemaReader;
import org.jdesktop.databuffer.io.schema.SchemaReaderException;
import org.jdesktop.databuffer.io.schema.SchemaWriterException;
import org.jdesktop.databuffer.io.schema.XMLDataSetSchemaReader;
import org.jdesktop.databuffer.io.schema.XMLDataSetSchemaWriter;
import org.jdesktop.databuffer.provider.sql.JDBCDataConnection;
import org.jdesktop.databuffer.provider.sql.SQLDataProvider;


/**
 * <CODE>DataSetUtility</CODE> holds static methods for working with the {@link org.jdesktop.dataset} package. Currently there are methods
 * for importing or exporting a {@link DataSet} schema.
 * @author Richard Bair
 * @author Adam Barclay
 * @author Patrick Wright
 */
public class DataSetUtility {

    /**
     * Utility method that facilitates using a <code>JDBCDataConnection</code>
     * to set the connection for each <code>DataProvider</code> in each
     * <code>DataTable</code> contained in the <code>DataSet</code>.
     * @param ds The <CODE>DataSet</CODE> to add providers to.
     * @param conn The {@link JDBCDataConnection} to set as a provider for the tables in the <CODE>DataSet</CODE>.
     * @return The <CODE>DataSet</CODE> provided as a parameter.
     */
    public static DataSet setProviderConnection(DataSet ds,
            JDBCDataConnection conn) {
        for (DataTable table : ds.getTables()) {
            if (table.getDataProvider() != null) {
                table.getDataProvider().setConnection(conn);
            }
        }
        return ds;
    }
    
    /**
     * Loads a {@link DataSet} schema using a schema reader, for all tables and relations declared in the schema.
     * It is recommended that the <CODE>DataSet</CODE> be empty before calling this method. TODO: document what happens if tables
     * or relations already exist in the DataSet.
     * @param reader The schema instance from which to read the schema.
     * @throws org.jdesktop.databuffer.io.schema.SchemaReaderException If an error occurs while reading the schema.
     * @return The DataSet, with the schema loaded from XML.
     */
    public static DataSet readDataSet(DataSetSchemaReader reader) throws SchemaReaderException {
       return reader.readDataSet();
    }
    
    /**
     * Loads a {@link DataSet} schema using a schema reader, for the tables listed in the tableNames argument, and their
     * relations. It is recommended that the <CODE>DataSet</CODE> be empty before calling this method. TODO: document what happens if tables
     * or relations already exist in the DataSet.
     * @param reader The schema reader to use.
     * @param tableNames Array of table names to read from the schema. Each element can be an unqualified table name, or a table.columnname
     * entry, where only the columns listed for the table are read from the schema. Only tables listed in this argument
     * are read from the schema, along with their relations.
     * @return The <CODE>DataSet</CODE> with its schema configured.
     * @throws org.jdesktop.databuffer.io.schema.SchemaReaderException If an error occurs while reading the schema.
     */
    public static DataSet readDataSet(DataSetSchemaReader reader, String... tableNames) throws SchemaReaderException {
       return reader.readDataSet(tableNames);
    }

    /**
     * Loads a {@link DataSet} schema from a JDBC data connection, using JDBC metadata, for all tables and relations declared in the schema.
     * The accuracy of the schema loading depends on the quality of the metadata returned by the JDBC driver; test carefully to make
     * sure you get the results you want. It is recommended that the <CODE>DataSet</CODE> be empty before calling this method. TODO: document what happens if tables
     * or relations already exist in the DataSet.
     * @param jdbcConn The connection from which to read the JDBC metadata.
     * @return The <CODE>DataSet</CODE>, with its schema configured.
     * @throws org.jdesktop.databuffer.io.schema.SchemaReaderException If an error occurs while reading the schema.
     */
    public static DataSet readDataSetFromJDBC(JDBCDataConnection jdbcConn) throws SchemaReaderException {
         return readDataSet(new JDBCDataSetSchemaReader(jdbcConn));
    }

    /**
     * Loads a {@link DataSet} schema from a JDBC data connection, using JDBC metadata, for the list of tables passed as an argument,
     * and their relations. The accuracy of the schema loading depends on the quality of the metadata returned by the JDBC driver; 
     * test carefully to make sure you get the results you want. It is recommended that the <CODE>DataSet</CODE> be empty before 
     * calling this method. TODO: document what happens if tables or relations already exist in the DataSet.
     * @param jdbcConn The connection from which to read the schema.
     * @param tableNames Array of table names to read from the schema. Each element can be an unqualified table name, or a table.columnname
     * entry, where only the columns listed for the table are read from the schema. Only tables listed in this argument
     * are read from the schema, along with their relations.
     * @return The <CODE>DataSet</CODE> with its schema configured.
     * @throws org.jdesktop.databuffer.io.schema.SchemaReaderException If an error occurs while reading the schema.
     */
    public static DataSet readDataSetFromJDBC(JDBCDataConnection jdbcConn, String... tableNames) throws SchemaReaderException {
         return readDataSet(new JDBCDataSetSchemaReader(jdbcConn), tableNames);       
    }

    /**
     * Loads a {@link DataSet} schema from a XML Schema, for all tables and relations declared in the schema.
     * It is recommended that the <CODE>DataSet</CODE> be empty before calling this method. TODO: document what happens if tables
     * or relations already exist in the DataSet.
     * @param is An InputStream from which to read the schema; this should be open before calling this method, and closed by the 
     * caller.
     * @param tableNames Array of table names to read from the schema. Each element can be an unqualified table name, or a table.columnname
     * entry, where only the columns listed for the table are read from the schema. Only tables listed in this argument
     * are read from the schema, along with their relations.
     * @throws org.jdesktop.databuffer.io.schema.SchemaReaderException If an error occurs while reading the schema.
     * @return The <CODE>DataSet</CODE> with its schema configured.
     */
    public static DataSet readDataSetFromXml(InputStream is, String... tableNames) throws SchemaReaderException {
       if ( tableNames.length == 0 ) {
         return readDataSet(new XMLDataSetSchemaReader(is));       
       } else {
         return readDataSet(new XMLDataSetSchemaReader(is), tableNames);       
       }
    }

    /**
     * Loads a {@link DataSet} schema from a XML Schema, for all tables and relations declared in the schema.
     * It is recommended that the <CODE>DataSet</CODE> be empty before calling this method. TODO: document what happens if tables
     * or relations already exist in the DataSet.
     * 
     * @param reader A Reader from which to read the schema; this should be open before calling this method, and closed by the 
     * caller.
     * @param tableNames Array of table names to read from the schema. Each element can be an unqualified table name, or a table.columnname
     * entry, where only the columns listed for the table are read from the schema. Only tables listed in this argument
     * are read from the schema, along with their relations.
     * @return The <CODE>DataSet</CODE> with its schema configured.
     * @throws org.jdesktop.databuffer.io.schema.SchemaReaderException If an error occurs while reading the schema.
     */
    public static DataSet readDataSetFromXml(Reader reader, String... tableNames) throws SchemaReaderException {
       if ( tableNames.length == 0 ) {
         return readDataSet(new XMLDataSetSchemaReader(reader));       
       } else {
         return readDataSet(new XMLDataSetSchemaReader(reader), tableNames);       
       }
    }

    /**
     * Exports a {@link DataSet DataSet's} schema using a schema writer, for all tables in the <CODE>DataSet</CODE>, and their relations.
     * @param writer The schema writer to use.
     * @param ds The <CODE>DataSet</CODE> to export a schema for.
     * @throws org.jdesktop.databuffer.io.schema.SchemaWriterException If an error occurs while writing the schema.
     */
    public static void writeDataSet(DataSetSchemaWriter writer, DataSet ds) throws SchemaWriterException {
      writer.writeDataSet(ds);
    }

    /**
     * Exports a {@link DataSet DataSet's} schema using a schema writer, for the list of tables passed as an argument,
     * and their relations.
     * @param writer The schema writer to use.
     * @param ds The <CODE>DataSet</CODE> to export a schema for.
     * @param tableNames Array of table names to export the schema for. Each element can be an unqualified table name, or a table.columnname
     * entry, where only the columns listed for the table are written out. Only tables listed in this argument
     * are exported, along with their relations.
     * @throws org.jdesktop.databuffer.io.schema.SchemaWriterException If an error occurs while writing the schema.
     */
    public static void writeDataSet(DataSetSchemaWriter writer, DataSet ds, String... tableNames) throws SchemaWriterException {
      writer.writeDataSet(ds, tableNames);
    }

    /**
     * Exports a {@link DataSet DataSet's} schema as an XML Schema, for all tables in the <CODE>DataSet</CODE>, and their relations.
     * @param out The {@link OutputStream} to write the XML to.
     * @param ds The <CODE>DataSet</CODE> to export.
     * @throws org.jdesktop.databuffer.io.schema.SchemaWriterException If an error occurs while writing the schema.
     */
    public static void writeDataSetAsXml(OutputStream out, DataSet ds) throws SchemaWriterException {
      XMLDataSetSchemaWriter writer = new XMLDataSetSchemaWriter(out);
      writer.writeDataSet(ds);
    }
    

    /**
     * Exports a {@link DataSet DataSet's} schema as an XML Schema, for all tables in the <CODE>DataSet</CODE>, and their relations.
     * @param writer The {@link Writer} to write the XML to.
     * @param ds The <CODE>DataSet</CODE> to export.
     * @throws org.jdesktop.databuffer.io.schema.SchemaWriterException If an error occurs while writing the schema.
     */
    public static void writeDataSetAsXml(Writer writer, DataSet ds) throws SchemaWriterException {
      XMLDataSetSchemaWriter xwriter = new XMLDataSetSchemaWriter(writer);
      xwriter.writeDataSet(ds);
    }    

    /**
     * Creates a new DataTable in the specified DataSet that maps directly to
     * one returned from a custom SELECT query.
     * <p>
     * Unlike the {@link #readDataSetFromJDBC(JDBCDataConnection, String[])} method
     * the data relations are not set up, as typically the links would be within
     * the table itself (i.e. due to a JOIN statement on a foreign key)
     * <p>
     * To avoid expensive queries that will have to be executed twice (to create
     * and then load the table) the statement passed in here should use a
     * <code>LIMIT 0</code> statement to avoid returning any real data, as
     * only the metadata is required for analysis.
     * <p>
     * It should be noted that only one catalog is currently supported and
     * therefore the query should only select tables from one catalog. A
     * SchemaCreationException will be thrown if more than one catalog is used.
     * <p>
     * If at any point during the creation of the new table a problem occurs an
     * exception will be thrown to indicate this problem, and no new table will
     * be added to the DataSet.
     * @see #readDataSetFromJDBC(JDBCDataConnection, String[])
     * @param ds The {@link DataSet} to import schema information into.
     * @param jdbcConn a valid connection to a database that is used to generate the
     *            queries and metadata, that <b>must</b> be opened prior to
     *            calling this method
     * @param tableName The new table's name that is to be created
     * @param statement The custom query that is to be used to generate the table
     * @throws SchemaCreationException if a problem occurs at any time during the creation of the
     *             schema
     */
    public static void createDataTableSchema(JDBCDataConnection jdbcConn,
            DataSet ds, String tableName, String statement)
            throws SchemaCreationException {

        assert jdbcConn != null : "Cannot supply a null data connection";
        assert ds != null : "Cannot supply a null DataSet";
        assert tableName != null : "Cannot supply a null table name";
        assert statement != null : "Cannot supply a null statement";
        assert jdbcConn.isConnected() : "The data connection must be open";

        System.out.println(statement);
        DataTable dt = ds.createTable(tableName);

        
        try {
            Connection conn = jdbcConn.getConnection();
            ResultSet set = conn.createStatement().executeQuery(statement);
            ResultSetMetaData rsmd = set.getMetaData();
            DatabaseMetaData md = conn.getMetaData();

            // Short circuit here if no columns are actually returned (this
            // is valid and a table will still be created)
            if (rsmd.getColumnCount() == 0) {
                return;
            }

            String catalogName = rsmd.getCatalogName(1);

            // A list of all tables from which columns have been returned. Used
            // to query them for primary keys.
            Set<String> tableNames = new HashSet<String>(5);

            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                // Only supports a single catalog. If the catalog is an empty
                // string then the column was created / aliased and therefore is
                // allowed
                String colCat = rsmd.getCatalogName(i);
                if (!"".equals(colCat) && !catalogName.equals(colCat)) {
                    throw new SchemaCreationException(
                            "Resulting table has sources from multiple catalogs - "
                                    + catalogName + " & " + colCat);
                }

                String t = rsmd.getTableName(i);

                // Create the new column and fill out all details available
                // from ResultSetMetaData
                DataColumn col = dt.createColumn(DataSetIOUtility.getType(rsmd.getColumnType(i)),
                                                 rsmd.getColumnName(i));
                col.setReadOnly(rsmd.isReadOnly(i));

                ResultSet rs = md.getColumns(catalogName, null, t, col
                        .getName());
                // Happens when columns were created in statement through
                // expressions and are therefore not backed up in database
                if (!rs.next()) {
                    continue;
                }

                col.setDefaultValue(rs.getObject(13));
                col.setRequired(rs.getString(18).equals("NO"));

                tableNames.add(t);
            }

            // For each table that was used above set the primary keys and
            // references (done per table as JDBC supports only retreiving
            // the information in this manner)
            for (String table : tableNames) {
                ResultSet rs = md.getPrimaryKeys(catalogName, null, table);
                while (rs.next()) {
                    DataColumn col = dt.getColumn(rs.getString(4));
                    // Happens if a key column is not SELECTed
                    if (col != null) {
                        col.setKeyColumn(true);
                    }
                }
            }
        } catch (Exception e) {
            // Do not allow a half-built table to be stored in the DataSet
            //TODO! This problem shows up in a couple of places. Hmmm...
//            ds.dropTable(dt);
            e.printStackTrace();
            throw new SchemaCreationException(
                    "Could not create the schema for the table " + tableName, e);
        }
    }

    /**
     * Creates a DataSet that contains a DataTable for each table that has been
     * specified in the given names Set. Each DataTable will be created in turn
     * and then any relations between the created tables will be established.
     * This method takes an array of database entity &quot;names&quot; which are
     * of the form: <code>table</code> or <code>table.column</code>
     * <p>
     * As the definition of each DataTable created refers only to a physical
     * table and is therefore backed up by a physical representation in the
     * database an <code>SQLDataProvider</code> implementation is created for
     * each table that will create the required statements for updating the
     * table. The <code>DataConnection</code> will be set to the given
     * <code>jdbcConn</code> parameter.
     * <p>
     * This method delegates the work of actualy creating the schema to the
     * {@link #createDataTableSchema(JDBCDataConnection, DataSet, String, String)}
     * method, which should be referred to for details of the work actually
     * performed.
     * <p>
     * <b>NB</b>: The catalog used to retrieve the information is the 'current'
     * one as set by the database system, one that is automatically used when a
     * query is sent to the database without explicity stating a catalog.
     * 
     * @param conn
     *            a valid connection to a database that is used to generate the
     *            queries and metadata, that <b>must</b> be opened prior to
     *            calling this method
     * @param names
     *            A list of all tables to be included (plus optional explicit
     *            column names for each table)
     * @return a new DataSet containing newly created DataTable definitions
     * @throws SchemaCreationException
     *             if a problem occurs at any time during the creation of the
     *             schema
     * 
     * @see #createDataTableSchema(JDBCDataConnection, DataSet, String, String)
     * @see #createRelations(JDBCDataConnection, DataSet)
     */
    public static DataSet createDataSetSchema(JDBCDataConnection conn,
            String... names) throws SchemaCreationException {

        assert conn != null : "Cannot supply a null data connection";
        assert names != null : "Cannot supply a null list of names";
        assert conn.isConnected() : "The data connection must be open";

        DataSet ds = new DataSet();

        // Used to store the list of tables that are to be retrieved, along
        // with the list of columns (or explictly null if all columns should
        // be included)
        Map<String, Set<String>> tables = new HashMap<String, Set<String>>(5);

        for (String name : names) {
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
        for (String tbl : tables.keySet()) {
            Set<String> cols = tables.get(tbl);

            // check to see whether the entire table is to be included, or
            // only some of the table
            if (cols == null) {
                createDataTableSchema(conn, ds, tbl, "SELECT * FROM " + tbl);
                        // CLEAN + " LIMIT 0");
            } else {
                // Should not happen as the set should only be created when
                // a column has been specified
                assert cols.size() > 0 : "Created an empty column set";

                StringBuilder cmd = new StringBuilder("SELECT ");

                for (String name : cols) {
                    cmd.append(name);
                    cmd.append(",");
                }

                // deletes the last comma
                cmd.deleteCharAt(cmd.length() - 1);
                cmd.append(" FROM ");
                cmd.append(tbl);
                cmd.append(" LIMIT 0");

                createDataTableSchema(conn, ds, tbl, cmd.toString());
            }

            SQLDataProvider dataProvider = new SQLDataProvider(tbl);
            dataProvider.setConnection(conn);
            ds.getTable(tbl).setDataProvider(dataProvider);
        }

        createRelations(conn, ds);

        return ds;
    }

    /**
     * Creates any relations that exist between the tables in the given DataSet
     * using the database metadata that can be retrieved using the given SQL
     * Connection.
     * <p>
     * <b>NB</b>: The catalog used to retrieve the information is the 'current'
     * one as set by the database system, one that is automatically used when a
     * query is sent to the database without explicity stating a catalog.
     * 
     * @param jdbcConn
     *            a valid connection to a database that is used to generate the
     *            queries and metadata, that <b>must</b> be opened prior to
     *            calling this method
     * @param ds
     *            the DataSet that contains the DataTables which are to be
     *            analysed for relations
     * @throws SchemaCreationException
     *             if a problem occurs at any time during the creation of the
     *             schema
     */
    public static void createRelations(JDBCDataConnection jdbcConn, DataSet ds)
            throws SchemaCreationException {

        assert jdbcConn != null : "Cannot supply a null connection";
        assert ds != null : "Cannot supply a null DataSet";
        assert jdbcConn.isConnected() : "The data connection must be open";

        // This list is used to search for datatables using a case-insensitive
        // comparison to avoid problems of databases changing the casing when
        // returning relation metadata (i.e. MySQL)
        List<DataTable> dataTables = ds.getTables();

        try {
            Connection conn = jdbcConn.getConnection();

            for (DataTable tbl : dataTables) {
                DatabaseMetaData md = conn.getMetaData();
                // Uses the current catalog, as noted in the javadoc
                ResultSet rs = md.getImportedKeys(conn.getCatalog(), null, tbl
                        .getName());

                while (rs.next()) {
                    // TODO for now, DataRelations only support a single column,
                    // so if the sequence is > 1, skip it
                    if (rs.getInt(9) > 1) {
                        continue;
                    }

                    String childTableString = rs.getString(3);
                    DataTable childTable = ds.getTable(rs.getString(3));
                    for (DataTable t : dataTables) {
                        if (t.getName().equalsIgnoreCase(childTableString)) {
                            childTable = t;
                            break;
                        }
                    }

                    // May occur if table was not initially selected
                    if (childTable == null) {
                        continue;
                    }

                    DataColumn childColumn = childTable.getColumn(rs
                            .getString(4));
                    DataColumn parentColumn = tbl.getColumn(rs.getString(8));

                    if (parentColumn != null && childColumn != null
                            && parentColumn != childColumn) {
                        DataRelation rel = ds.createRelation(rs.getString(12), parentColumn, childColumn);
                    }
                }
            }
        } catch (Exception e) {
            throw new SchemaCreationException(
                    "Could not create data relations", e);
        }
    }

    public static void main(String[] args) throws Exception {
        JDBCDataConnection jdbcConn = new JDBCDataConnection();

        jdbcConn.setConnected(true);
        DataSet ds = createDataSetSchema(jdbcConn, "Addresses", "Customers",
                "Bookings", "BookingLocations");
        ds.loadAndWait();
        jdbcConn.setConnected(false);

        writeDataSetAsXml(System.out, ds);
        System.out.println(ds.writeXml());
    }
}
