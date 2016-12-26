/*
 * $Id: DataSetIOUtility.java,v 1.2 2005/11/01 13:31:57 pdoubleya Exp $
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

import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Static utility class for the io package, meant for use internally
 * within the package.
 *
 * @author Adam Barclay
 * @author Patrick Wright
 */
public class DataSetIOUtility {
    private static final Logger LOG = Logger.getLogger(DataSetIOUtility.class.getName());
    
    /** Creates a new instance of DataSetIOUtility */
    private DataSetIOUtility() {
    }
    
    /**
     * Utility class to convert between a <code>java.sql.Types</code> value
     * and a corresponding Java object that would typically be used to represent
     * the data.
     *
     * @param type
     *            the <code>java.sql.Types</code> value
     * @return a corresponding object typically used to represent data of the
     *         given type
     */
    public static Class getType(int type) {
        switch (type) {
            case Types.ARRAY:
            case Types.DISTINCT:
            case Types.JAVA_OBJECT:
            case Types.NULL:
            case Types.OTHER:
            case Types.REF:
            case Types.STRUCT:
            case Types.DATALINK:
                return Object.class;
                
            case Types.BINARY:
            case Types.BIT:
            case Types.BOOLEAN:
                return Boolean.class;
                
            case Types.BIGINT:
            case Types.SMALLINT:
            case Types.TINYINT:
            case Types.INTEGER:
                return Integer.class;
                
            case Types.BLOB:
            case Types.LONGVARBINARY:
            case Types.VARBINARY:
                return byte[].class;
                
            case Types.CHAR:
                return Character.class;
                
            case Types.CLOB:
                return char[].class;
                
            case Types.DATE:
            case Types.TIME:
            case Types.TIMESTAMP:
                return Date.class;
                
            case Types.DECIMAL:
            case Types.DOUBLE:
            case Types.FLOAT:
            case Types.NUMERIC:
            case Types.REAL:
                return BigDecimal.class;
                
            case Types.VARCHAR:
            case Types.LONGVARCHAR:
                return String.class;
                
            default:
                LOG.warning("Unsupported JDBC type; type is unknown, so mapping to Object.class. Type value was " + type);
                return Object.class;
        }
    }
    
    /**
     * Parses an array of table names, where each table may either be named by itself, or have a column
     * name associated with it, in the format table-name.column-name. Returns a Map keyed by table name, mapped
     * to a Set of the column names parsed out. The Set will be null if no column names were found, meaning
     * all columns should be included from the table.
     * @param tableNames The array of table names to parse; see method docs.
     * @return A Map, keyed by table name, mapped to a Set of column names for the table, or mapped to null if no columns were specified.
     */
    public static Map<String, Set<String>> extractTableList(String... tableNames) {
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
        return tables;
    }

    
    public static String getDefaultSchemaName(DatabaseMetaData databaseMetaData) {
        String defaultSchemaName = null;
        try {
            ResultSet rs = databaseMetaData.getSchemas();
            boolean found = false;
            while ( rs.next() && ! found) {
                found = rs.getBoolean("IS_DEFAULT");
                if ( found ) defaultSchemaName = rs.getString("TABLE_SCHEM");
            }
        } catch (Exception e) {
            defaultSchemaName = null;
        }
        return defaultSchemaName;
    }
    
    public static List<String> extractColumn(ResultSet tableResultSet, String columnName) {
        List<String> tables = new ArrayList<String>();
        
        try {
            while (tableResultSet.next()) {
                tables.add(tableResultSet.getString(columnName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tables;
    }
    
    public static void dumpCatalogDetails(DatabaseMetaData databaseMetaData, PrintWriter pw) {
        try {
            pw.println(databaseMetaData.getCatalogTerm());
            ResultSet rs = databaseMetaData.getCatalogs();
            while ( rs.next()) {
                int cnt = rs.getMetaData().getColumnCount();
                while ( --cnt >= 0 ) {
                    pw.println("Col: " + rs.getMetaData().getColumnName(cnt));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static PrintWriter getPrintWriter(Writer writer) {
        if ( writer instanceof PrintWriter ) {
            return (PrintWriter)writer;
        } else {
            return new PrintWriter(new BufferedWriter(writer));
        }
    }
}
