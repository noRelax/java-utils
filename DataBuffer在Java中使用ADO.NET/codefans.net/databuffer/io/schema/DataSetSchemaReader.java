/*
 * $Id: DataSetSchemaReader.java,v 1.1 2005/11/01 13:35:33 pdoubleya Exp $
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


import java.util.List;
import org.jdesktop.databuffer.DataSet;


/**
 * A <CODE>DataSetSchemaReader</CODE> instantiates DataSets, DataTables and DataRelations from a
 * schema provider, such as database metadata or XML. The methods for reading schema information
 * <CODE>DataSetSchemaReaders</CODE> create empty DataSets, DataTables, and DataRelations. The methods
 * in <CODE>DataSetSchemaReader</CODE> do not specify a data source; implementing classes must do so,
 * for example in the constructor. For this reason, some <CODE>DataSetSchemaReaders</CODE> may only be
 * usable with one schema source. Typically the data source will be specified in the constructor to a
 * <CODE>DataSetSchemaReader</CODE>.
 *
 * @author Patrick Wright
 */
public interface DataSetSchemaReader {
   /**
    * Instantiates a new {@link org.jdesktop.databuffer.DataSet} from the data source
    * specified in the constructor. If the schema contains multiple <CODE>DataTables</CODE> 
    * or <CODE>DataRelations</CODE>, all of them are loaded 
    * from the schema into the new <CODE>DataSet</CODE>.
    * 
    * @throws ashktheman.databuffer.io.SchemaReaderException If an error occurred while loading the schema.
    * 
    * @return A new <CODE>DataSet</CODE> instantiated from the data source
    * specified for the implementing class, including all tables and relations in the schema.
    */
   DataSet readDataSet() throws SchemaReaderException ;
   
   /**
    * Instantiates a new {@link org.jdesktop.databuffer.DataSet} from the data source specified in the
    * implementing class, with the <CODE>DataSet</CODE> schema loaded but without data, for the tables
    * and columns given in the <CODE>tableNames</CODE> parameter.
    * 
    * @param tableNames One or more table names to load from the schema. Each name can be either a table, or a
    * table and column, dot-separated. If no columns are specified, all columns in the table are
    * read from the schema; if columns are listed, only those columns are loaded. <CODE>DataRelations</CODE>
    * for those tables and columns loaded from the schema are also instantiated automatically through
    * {@link #addRelations(org.jdesktop.databuffer.DataSet, String...)}.
    * 
    * @throws ashktheman.databuffer.io.SchemaReaderException If an error occurred while loading the schema.
    * 
    * @return A new <CODE>DataSet</CODE> instantiated from the data source 
    * specified for the implementing class, including all tables and relations in the schema.
    */
   DataSet readDataSet(String... tableNames) throws SchemaReaderException ;
   
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
   List<String> addTables(DataSet dataSet, String... tableNames) throws SchemaReaderException ;   
   
   /**
    * Adds all {@link org.jdesktop.databuffer.DataRelation} for the named {@link org.jdesktop.databuffer.DataTable}
    * to the specified {@link org.jdesktop.databuffer.DataSet}, as read from the schema which this reader
    * is reading from. All <CODE>DataRelations</CODE> for the table are dropped from the <CODE>DataSet</CODE>
    * and re-added.
    *
    * @return A <CODE>List</CODE> of the <CODE>DataRelation</CODE> names loaded from the schema.#
    *
    * @param dataSet The <CODE>DataSet</CODE> to which to add the relations.
    *
    * @param tableNames One or more table names for which to read relations from the schema. Only those relations
    * for columns in the DataTable (as currently loaded) are added.
    *
    * @throws org.jdesktop.databuffer.io.SchemaReaderException If an error occurred while reading the schema.
    */
   List<String> addRelations(DataSet dataSet, String... tableNames) throws SchemaReaderException ;      
}
