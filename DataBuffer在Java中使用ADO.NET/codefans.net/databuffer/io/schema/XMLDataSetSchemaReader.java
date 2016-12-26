/*
 * $Id: XMLDataSetSchemaReader.java,v 1.1.2.3 2006/01/21 19:49:45 david_hall Exp $
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


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.jdesktop.databuffer.DataColumn;
import org.jdesktop.databuffer.DataProvider;
import org.jdesktop.databuffer.DataRelation;
import org.jdesktop.databuffer.DataSet;
import org.jdesktop.databuffer.DataTable;
import org.jdesktop.databuffer.DataValue;
import org.jdesktop.databuffer.provider.sql.SQLCommand;
import org.jdesktop.databuffer.provider.sql.SQLDataProvider;
import org.jdesktop.databuffer.provider.sql.TableCommand;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 * An <CODE>XMLDataSetSchemaReader</CODE> instantiates a {@link org.jdesktop.databuffer.DataSet} from
 * an XML Schema. Each instance of <CODE>XMLDataSetSchemaReader</CODE> works against a single XML datasource,
 * e.g. an <CODE>InputStream</CODE>; once instantiated against that InputStream, the reader
 * methods (e.g. {@link #readDataSet()}) may be used to load the <CODE>DataSet</CODE>.
 * <CODE>DataSetSchemaReader</CODE> classes do not load data, only the <CODE>DataSet</CODE> schema.
 * Typical usage:
 * <CODE>
 * InputStream xmlIS = getClass().getResourceAsStream("resources/products.xsd");
 * XMLDataSetSchemaReader reader = new XMLDataSetSchemaReader(xmlIS);
 * DataSet dataSet = reader.readDataSet();
 * xmlIS.close();
 * </CODE>
 * You can also use the single-line utility method 
 * {@link org.jdesktop.databuffer.util.DataSetUtility#readDataSetFromXml(InputStream, String[])}.
 *
 * @author Richard Bair
 * @author Patrick Wright
 */
public class XMLDataSetSchemaReader implements DataSetSchemaReader {
    /**
     * The Logger
     */
    private static final Logger LOG = Logger.getLogger(XMLDataSetSchemaReader.class.getName());
    
   /** The unparsed contents of the schema, as XML, as read from the data source. */
   private String xmlContents;
   
   /** The parser we're using during processing. */
   private DataSetParser lastParserUsed;
   
   /**
    * Instantiates a new <CODE>XMLDataSetSchemaReader</CODE> for an input stream holding a <CODE>DataSet</CODE>
    * schema in XML format. The input stream cannot be changed once the reader in instantiated,
    * and the caller is responsible for closing the stream once the schema is loaded. See class documentation for more details.
    *
    * @param xmlInputStream A {@link java.io.InputStream} holding <CODE>DataSet</CODE> schema contents in XML format.
    * The stream must be readable, and the caller is responsible for closing the stream once
    * done.
    */
   public XMLDataSetSchemaReader(InputStream xmlInputStream) throws SchemaReaderException {
      this(new InputStreamReader(xmlInputStream));
   }
   
   /**
    * Instantiates a new <CODE>XMLDataSetSchemaReader</CODE> for an input stream holding a <CODE>DataSet</CODE>
    * schema in XML format. The input stream cannot be changed once the reader in instantiated,
    * and the caller is responsible for closing the stream once the schema is loaded. See class documentation for more details.
    *
    * @param xmlReader A {@link java.io.InputStream} holding <CODE>DataSet</CODE> schema contents in XML format.
    * The stream must be readable, and the caller is responsible for closing the stream once
    * done.
    */
   public XMLDataSetSchemaReader(Reader xmlReader) throws SchemaReaderException {
      String xml = "";
      if ( ! ( xmlReader instanceof BufferedReader )) {
         xmlReader = new BufferedReader(xmlReader);
      }
      try {
         StringBuilder builder = new StringBuilder();
         char[] bytes = new char[4096];
         int length = -1;
         while ((length = xmlReader.read(bytes)) != -1) {
            builder.append(new String(bytes, 0, length));
         }
         this.xmlContents = builder.toString();
      } catch (Exception e) {
         throw new SchemaReaderException("Can't read input stream to parse schema from.", e);
      }
   }
   
   /**
    * Instantiates a new {@link org.jdesktop.databuffer.DataSet} from the <CODE>InputStream</CODE>
    * specified in the constructor. If the schema contains multiple <CODE>DataTables</CODE>
    * or <CODE>DataRelations</CODE>, all of them are loaded
    * from the schema into the new <CODE>DataSet</CODE>.
    *
    * @throws ashktheman.databuffer.io.SchemaReaderException If an error occurred while loading the schema.
    *
    * @return A new <CODE>DataSet</CODE> instantiated from the <CODE>InputStream</CODE>
    * specified for the implementing class, including all tables and relations in the schema.
    */
   public DataSet readDataSet() throws SchemaReaderException {
      return readXml(new DataSet());
   }
   
   /**
    * Instantiates a new {@link org.jdesktop.databuffer.DataSet} from the <CODE>InputStream</CODE> specified in the
    * implementing class, with the <CODE>DataSet</CODE> schema loaded but without data, for the tables
    * and columns given in the <CODE>tableNames</CODE> parameter.
    *
    * @param tableNames One or more table names to load from the schema. Each name can be either a table, or a
    * table and column, dot-separated. If no columns are specified, all columns in the table are
    * read from the schema; if columns are listed, only those columns are loaded. <CODE>DataRelations</CODE>
    * for those tables and columns loaded from the schema are also instantiated automatically through
    * {@link #addRelations(org.jdesktop.databuffer.DataSet, String[])}.
    * @throws ashktheman.databuffer.io.SchemaReaderException If an error occurred while loading the schema.
    *
    * @return A new <CODE>DataSet</CODE> instantiated from the <CODE>InputStream</CODE>
    * specified for the implementing class, including only the tables and columns listed in the
    * <CODE>tableNames</CODE> parameter.
    */
   public DataSet readDataSet(String... tableNames) throws SchemaReaderException {
      return readXml(new DataSet(), tableNames);
   }
   
   /**
    * Adds all {@link org.jdesktop.databuffer.DataRelation} for the named {@link org.jdesktop.databuffer.DataTable}
    * to the specified {@link org.jdesktop.databuffer.DataSet}, as read from the schema which this reader
    * is reading from. All <CODE>DataRelations</CODE> for the table are dropped from the <CODE>DataSet</CODE>
    * and re-added.
    *
    * @return A <CODE>List</CODE> of the <CODE>DataRelation</CODE> names loaded from the schema.
    *
    * @param dataSet The <CODE>DataSet</CODE> to which to add the relations.
    *
    * @param tableNames One or more table names for which to read relations from the schema. Only those relations
    * for columns in the DataTable (as currently loaded) are added.
    *
    * @throws org.jdesktop.databuffer.io.SchemaReaderException If an error occurred while reading the schema.
    */
   public List<String> addRelations(DataSet dataSet, String... tableNames) throws SchemaReaderException {
      readXml(dataSet, tableNames);
      return lastParserUsed.getRelationsAdded();
      
   }
   
   /**
    * Appends one or more {@link org.jdesktop.databuffer.DataTable} to a {@link org.jdesktop.databuffer.DataSet}
    * from the schema represented by this reader. If the table, by name, is found in the <CODE>DataSet</CODE>,
    * the table is skipped; if you want to re-add a table (for example, to pick up changes in table structure),
    * drop the table from the <CODE>DataSet</CODE> before calling this method.
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
    * @throws org.jdesktop.databuffer.io.SchemaReaderException If an error occurred while reading the schema.
    */
   public List<String> addTables(DataSet dataSet, String... tableNames) throws SchemaReaderException {
      readXml(dataSet, tableNames);
      return lastParserUsed.getTablesAdded();
   }
   
   /**
    *
    * @param dataSet
    */
   private DataSet readXml(DataSet dataSet, String... tableNames) throws SchemaReaderException {
      //set up an XML parser to parse the schema
      try {
         SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
         InputSource is = new InputSource(new StringReader(xmlContents));
         lastParserUsed = new DataSetParser(dataSet, tableNames);
         parser.parse(is, lastParserUsed);
      } catch (Exception e) {
         throw new SchemaReaderException("Failed to setup schema XML parser.", e);
      }
      return dataSet;
   }
   
   /**
    * parses the document.
    *
    * The xs:element tag is used repeatedly. With a depth of 0, it is the
    * DataSet element. With a depth of 1, it is a DataTable, and with a depth of
    * 2 it is a DataColumn
    */
   private static final class DataSetParser extends DefaultHandler {
      public int elementDepth = 0;
      private Attributes attrs;
      private DataSet ds;
      private DataTable table;
      private DataColumn column;
      private DataProvider dataProvider;
      private List<String> tableNames;
      private List<String> tablesAdded;
      private List<String> relationsAdded;
      
      public DataSetParser(DataSet ds, String... tableNames) {
         this.ds = ds == null ? new DataSet() : ds;
         this.tableNames = new ArrayList(tableNames.length);
         Collections.addAll(this.tableNames, tableNames);
      }
      
      public DataSet getDataSet() {
         return ds;
      }
      
      
      public void startDocument() throws SAXException {
         super.startDocument();
         this.tablesAdded = new ArrayList<String>();
         this.relationsAdded = new ArrayList<String>();
      }
      
      public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
         this.attrs = atts;
         if (qName.equals("xs:element")) {
            elementDepth++;
            switch(elementDepth) {
               case 1:
                  //this is a DataSet
                  ds.setName(attrs.getValue("name"));
                  break;
               case 2:
                  //this is a table tag
                  String tableName = attrs.getValue("name");
                  
                  // skip table if already in DataSet
                  if ( ds.getTable(tableName) != null ) {
                     System.out.println("TABLE " + tableName + " ALREADY IN DATASET; SKIPPING.");
                     table = null;
                     break;
                  }
                  
                  if ( tableNames.size() > 0 && !tableNames.contains(tableName)) {
                     table = null;
                     break;
                  }
                  
                  table = ds.createTable(tableName);
                  
                  String val = attrs.getValue("appendRowSupported");
                  table.setAppendRowSupported(val == null || val.equalsIgnoreCase("true"));
                  val = attrs.getValue("deleteRowSupported");
                  table.setDeleteRowSupported(val == null || val.equalsIgnoreCase("true"));
                  tablesAdded.add(tableName);
                  break;
               case 3:
                  if ( table == null ) break;
                  
                  //this is a column tag: get the immutable attributes
                  String colName = attrs.getValue("name");
                  String colExpression = attrs.getValue("expression");
                  String colType = attrs.getValue("type");
                        
                  if (colExpression != null)
                      column = table.createExpression(colName, colExpression);
                  else
                      column = table.createColumn(decodeColumnType(colType), colName);
                  
                  //set the required flag
                  val = attrs.getValue("minOccurs");
                  if (val != null && val.equals("")) {
                      column.setRequired(true);
                  }
                  
                  //find out if this is a keycolumn
                  val = attrs.getValue("keyColumn");
                  column.setKeyColumn(val == null ? false : val.equalsIgnoreCase("true"));
                  
                  //find out if this column is readOnly
                  val = attrs.getValue("readOnly");
                  column.setReadOnly(val == null ? false : val.equalsIgnoreCase("true"));
                  
                  //grab the default, if one is supplied
                  String defaultValue = attrs.getValue("default"); //TODO This will require some kind of type conversion
                  if (defaultValue != null && !defaultValue.equals("")) 
                      column.setDefaultValue(getDefaultValue(defaultValue));
                  
                  break;
               default:
                  //error condition
                  System.out.println("Error in DataSetParser");
            }
         } else if (qName.equals("dataProvider")) {
            String classType = attrs.getValue("class");
            if (classType != null) {
               try {
                  dataProvider = (DataProvider)Class.forName(classType).newInstance();
                  table.setDataProvider(dataProvider);
                  //TODO There needs to be a more general configuration solution
                  if (dataProvider instanceof SQLDataProvider) {
                     String tableName = attrs.getValue("tableName");
                     if (tableName != null && !tableName.equals("")) {
                        TableCommand cmd = new TableCommand(tableName);
                        cmd.setWhereClause(attrs.getValue("whereClause"));
                        cmd.setOrderByClause(attrs.getValue("orderByClause"));
                        cmd.setHavingClause(attrs.getValue("havingClause"));
                        dataProvider.setCommand(cmd);
                     } else {
                        SQLCommand command = new SQLCommand();
                        // CLEAN: setCustom() is no longer available (PWW 11/01/2005)
                        // command.setCustom(true);
                        command.setSelectSQL(attrs.getValue("select"));
                        command.setInsertSQL(attrs.getValue("insert"));
                        command.setUpdateSQL(attrs.getValue("update"));
                        command.setDeleteSQL(attrs.getValue("delete"));
                        dataProvider.setCommand(command);
                     }
                  }
               } catch (Exception e) {
                  //hmmm
                  e.printStackTrace();
               }
            }
         } else if (qName.equals("dataRelation")) {
            String relationName = attrs.getValue("name");
            
            String parentColumnName = attrs.getValue("parentColumn");
            String childColumnName = attrs.getValue("childColumn");
            String parentTableName = parentColumnName.substring(0, parentColumnName.indexOf("."));
            String childTableName = childColumnName.substring(0, childColumnName.indexOf("."));
            try {
               if ( tableNames.size() > 0 &&
                     tableNames.contains(parentTableName) &&
                     tableNames.contains(childTableName)) {
                  
                  DataColumn parentColumn = (DataColumn)ds.getElement(parentColumnName);
                  DataColumn childColumn = (DataColumn)ds.getElement(childColumnName);
                  if ( parentColumn != null && childColumn != null ) {
                     DataRelation relation = ds.createRelation(relationName, parentColumn, childColumn);
                     relationsAdded.add(relationName);
                  }
               } else {
                  System.err.println("DataRelation: Either parent " + parentColumnName + " or child " + childColumnName +
                        " is missing in DataSet. Tables might not be loaded, or the respective columns might not be. " +
                        "Skipping.");
               }
            } catch ( Exception e ) {
               System.out.println("failed on Parent: " + parentColumnName);
               
            }
         } else if (qName.equals("dataValue")) {
             try {
                 DataValue v = ds.createValue(attrs.getValue("name"), attrs.getValue("expression"));
             } catch (IllegalArgumentException x) {
                 throw new org.xml.sax.SAXException(x.getMessage(), x);
             }
         }
      }
      
      public void endElement(String uri, String localName, String qName) throws SAXException {
         if (qName.equals("xs:element")) {
            switch(elementDepth) {
               case 1:
                  //this is a dataset tag
                  break;
               case 2:
                  //this is a table tag
                  break;
               case 3:
                  //this is a column tag
                  break;
               default:
                  //error condition
                  System.out.println("Error in DataSetParser");
            }
            elementDepth--;
         }
      }
      
      public void endDocument() throws SAXException {
      }
      
      public List<String> getTablesAdded() {
         return tablesAdded;
      }
      
      public List<String> getRelationsAdded() {
         return relationsAdded;
      }

       
       private Class decodeColumnType(String type) {
           if (type.equals("xs:string"))
               return String.class;
           
           if (type.equals("xs:decimal")) 
               return BigDecimal.class;
           
           if (type.equals("xs:integer") || type.equals("xs:int")) 
               return Integer.class;
           
           if (type.equals("xs:boolean"))
               return Boolean.class;
           
           if (type.equals("xs:date") || type.equals("xs:time") || type.equals("xs.dateTime")) 
               return Date.class;
           
           if (type.equals("xs:unsignedByte")) 
               return Byte.class;
           
           LOG.log(Level.WARNING, "unexpected classType: '{0}'", type);
           return Object.class;
       }
       
       private Object getDefaultValue(String value) {
           Class type = column.getType();
           if (type == String.class)
               return value;
           
           if (type == BigDecimal.class)
               return new BigDecimal(value);
           
           if (type == Integer.class)
               return Integer.decode(value);
           
           if (type == Boolean.class)
               return Boolean.valueOf(value);
           
           if (type == Date.class) 
               return new Date(Date.parse(value));
           
           if (type == Byte.class)
               return Byte.valueOf(value);
           
           LOG.log(Level.WARNING, "unexpected classType: '{0}'", type.getName());
           return null;
       }
   }
}
