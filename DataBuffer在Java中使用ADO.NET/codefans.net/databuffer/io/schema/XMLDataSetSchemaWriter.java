/*
 * $Id: XMLDataSetSchemaWriter.java,v 1.1.2.1 2006/01/13 01:23:18 rbair Exp $
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


import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import org.jdesktop.databuffer.DataColumn;
import org.jdesktop.databuffer.DataRelation;
import org.jdesktop.databuffer.DataSet;
import org.jdesktop.databuffer.DataTable;
import org.jdesktop.databuffer.DataValue;
import org.jdesktop.databuffer.MutableValue;
import org.jdesktop.databuffer.SummaryValue;

/**
 * Writes the schema for a {@link org.jdesktop.databuffer.DataSet} out as an XML Schema.
 *
 * <p>Canonical use:
 * <pre>
 * Writer writer = new PrintWriter(System.out);
 * XMLDataSetSchemaWriter xmlWriter = new XMLDataSetSchemaWriter(writer);
 * xmlWriter.writeDataSet(ds);
 * </pre>
 * <P>You can also use the static {@link org.jdesktop.databuffer.util.DataSetUtility} methods, like
 * {@link org.jdesktop.databuffer.util.DataSetUtility#writeDataSetAsXml(OutputStream, DataSet)} for a one-line operation.
 * @author Patrick Wright
 */
public class XMLDataSetSchemaWriter implements DataSetSchemaWriter {
    /**
     * The PrintWriter used internally for the export, instantiated in the constructor.
     */
    private PrintWriter outputPrintWriter;
    
    /**
     * Creates a new instance of <CODE>XMLDataSetSchemaWriter</CODE> for a given {@link OutputStream}.
     * @param os The {@link java.io.OutputStream} to write to; must be open. Will not be closed by this class. The
     * <CODE>OutputStream</CODE> will be wrapped in a {@link java.io.PrintWriter} so line separation
     * will be environment-specific.
     */
    public XMLDataSetSchemaWriter(OutputStream os) {
        if ( ! ( os instanceof BufferedOutputStream )) {
            os = new BufferedOutputStream(os);
        }
        this.outputPrintWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(os)));
    }
    
    /**
     * Creates a new instance of <CODE>XMLDataSetSchemaWriter</CODE> for a given {@link Writer}.
     * @param writer The {@link java.io.Writer} to write to; must be open. Will not be closed by this class. The
     * writer will be wrapped in a {@link java.io.PrintWriter} so line separation will be
     * environment-specific.
     */
    public XMLDataSetSchemaWriter(Writer writer) {
        if ( writer instanceof PrintWriter ) {
            this.outputPrintWriter = (PrintWriter)writer;
        } else {
            if ( writer instanceof BufferedWriter ) {
                writer = new BufferedWriter(writer);
            }
            
            this.outputPrintWriter = new PrintWriter(writer);
        }
    }
    
    /**
     * Writes the complete <CODE>DataSet</CODE> schema out as an XML Schema to the <CODE>OutputStream</CODE>
     * or <CODE>Writer</CODE> this class was constructed with.
     * @param ds The <CODE>DataSet</CODE> to write out as a Schema.
     * @throws org.jdesktop.databuffer.io.SchemaWriterException If an error occurred while writing the schema.
     */
    public void writeDataSet(DataSet ds) throws SchemaWriterException {
        writeXmlSchema(ds, "");
        outputPrintWriter.flush();
    }
    
    /**
     * Writes the <CODE>DataSet</CODE> schema out as an XML Schema to the <CODE>OutputStream</CODE>
     * or <CODE>Writer</CODE> this class was constructed with for the tables listed.
     * @param ds The <CODE>DataSet</CODE> to write as a schema.
     * @param tableNames The <CODE>DataTable</CODE> names to include in the schema; includes their {@link DataRelation relations}.
     * @throws org.jdesktop.databuffer.io.SchemaWriterException If an error occurred while dumping.
     */
    public void writeDataSet(DataSet ds, String... tableNames) throws SchemaWriterException {
        writeXmlSchema(ds, tableNames);
        outputPrintWriter.flush();
    }
    
    /**
     * Writes the entire schema to the current {@link PrintWriter}.
     * @param ds The DataSet to write.
     * @param tableNames List of table names to write, will include their relations as well.
     */
    private void writeXmlSchema(DataSet ds, String... tableNames) {
        PrintWriter pw = this.outputPrintWriter;
        boolean allTables = ( tableNames.length == 1 && tableNames[0].length() == 0 );
        
        pw.println("<?xml version=\"1.0\" standalone=\"yes\" ?>");
        pw.print("<xs:schema id=\"");
        pw.print(ds.getName());
        pw.print("\" targetNamespace=\"http://jdesktop.org/tempuri/");
        pw.print(ds.getName());
        pw.print(".xsd\" xmlns=\"http://javadesktop.org/tempuri/");
        pw.print(ds.getName());
        pw.println(".xsd\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" attributeFormDefault=\"qualified\" elementFormDefault=\"qualified\">");
        pw.print("\t<xs:element name=\"");
        pw.print(ds.getName());
        pw.println("\">");
        pw.println("\t\t<xs:complexType>");
        pw.println("\t\t\t<xs:choice maxOccurs=\"unbounded\">");
        
        Map<String, Set<String>> tables = DataSetIOUtility.extractTableList(tableNames);
        
        for (DataTable table : ds.getTables()) {
            String tableName = table.getName();
            if ( ! allTables && ! tables.containsKey(tableName)) continue;

            pw.print("\t\t\t\t<xs:element name=\"");
            pw.print(tableName);
            pw.print("\" appendRowSupported=\"");
            pw.print(table.isAppendRowSupported());
            pw.print("\" deleteRowSupported=\"");
            pw.print(table.isDeleteRowSupported());
            pw.println("\">");
            pw.println("\t\t\t\t\t<xs:complexType>");
            pw.println("\t\t\t\t\t\t<xs:sequence>");
            for (DataColumn col : table.getColumns()) {
                String columnName = col.getName();
                Set<String> cols = tables.get(tableName);
                if ( ! allTables && ! cols.contains(columnName)) continue;

                pw.print("\t\t\t\t\t\t\t<xs:element name=\"");
                pw.print(col.getName());
                pw.print("\" type=\"");
                if (col.getType() == String.class || col.getType() == Character.class) {
                    pw.print("xs:string");
                } else if (col.getType() == BigDecimal.class) {
                    pw.print("xs:decimal");
                } else if (col.getType() == Integer.class) {
                    pw.print("xs:integer");
                } else if (col.getType() == Boolean.class) {
                    pw.print("xs:boolean");
                } else if (col.getType() == Date.class) {
                    pw.print("xs:dateTime");
                } else if (col.getType() == Byte.class) {
                    pw.print("xs:unsignedByte");
                } else {
                    System.out.println("Couldn't find type for xsd for Class " + col.getType());
                }
                if (col.getDefaultValue() != null) {
                    pw.print("\" default=\"");
                    pw.print(col.getDefaultValue());
                }
                if (!col.isRequired()) {
                    pw.print("\" minOccurs=\"0");
                }
                pw.print("\" keyColumn=\"");
                pw.print(col.isKeyColumn());
                pw.print("\" readOnly=\"");
                pw.print(col.isReadOnly());
                if (col.getExpression() != null && !(col.getExpression().trim().equals(""))) {
                    pw.print("\" expression=\"");
                    pw.print(col.getExpression());
                }
                pw.println("\" />");
            }
            pw.println("\t\t\t\t\t\t</xs:sequence>");
            pw.println("\t\t\t\t\t</xs:complexType>");
            pw.println("\t\t\t\t</xs:element>");
        }
        pw.println("\t\t\t</xs:choice>");
        pw.println("\t\t</xs:complexType>");
        
        pw.println("\t\t<xs:annotation>");
        pw.println("\t\t\t<xs:appinfo>");
        
        //write the relations out
        for (DataRelation r : ds.getRelations()) {
            DataColumn parentCol = r.getParentColumn();
            DataColumn childCol = r.getChildColumn();
            String parentTableName = parentCol.getTable().getName();
            String childTableName = childCol.getTable().getName();
            if ( ! allTables && ! ( tables.containsKey(parentTableName) && tables.containsKey(childTableName)))  continue;
            
            Set<String> cols = null;
            cols = tables.get(parentTableName);
            if ( ! allTables && ! cols.contains(parentCol.getName())) continue;
            cols = tables.get(childTableName);
            if ( ! allTables && ! cols.contains(childCol.getName())) continue;
            
            pw.print("\t\t\t\t<dataRelation name=\"");
            pw.print(r.getName());
            pw.print("\" parentColumn=\"");
            if (parentCol != null) {
                pw.print(parentCol.getTable().getName());
                pw.print(".");
                pw.print(parentCol.getName());
            }
            pw.print("\" childColumn=\"");
            if (childCol != null) {
                pw.print(childCol.getTable().getName());
                pw.print(".");
                pw.print(childCol.getName());
            }
            pw.println("\" />");
        }
        
        //write the data values out
        for (DataValue value : ds.getValues()) {
            pw.print("\t\t\t\t<dataValue name=\"");
            pw.print(value.getName());
            pw.print("\"");

            if(value instanceof SummaryValue) {
                SummaryValue expression = (SummaryValue) value;
                pw.print(" expression=\"");
                if (expression.getExpression() != null) {
                    pw.print(expression.getExpression());
                }
                pw.println("\"");
            }

            if(value instanceof MutableValue) {
                pw.print(" type=\"");
                pw.print(value.getType());
                pw.println("\"");
            }
            
            pw.println("/>");
        }
        
        //close the annotation section
        pw.println("\t\t\t</xs:appinfo>");
        pw.println("\t\t</xs:annotation>");
        
        //close the document down
        pw.println("\t</xs:element>");
        pw.println("</xs:schema>");
    }
}
