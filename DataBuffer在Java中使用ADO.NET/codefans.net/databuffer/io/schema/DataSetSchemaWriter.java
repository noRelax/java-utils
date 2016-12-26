/*
 * $Id: DataSetSchemaWriter.java,v 1.1 2005/11/01 13:35:35 pdoubleya Exp $
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

import org.jdesktop.databuffer.DataSet;

/**
 * A <CODE>DataSetSchemaWriter</CODE> "exports" or "write" a {@link DataSet DataSet's} schema
 * to some external format, such as XML or SQL DDL. Implementing classes must be able to write the entire
 * <CODE>DataSet</CODE>, or a subset of it, given a list of tables. By agreement, the exported
 * schema should be re-loadable (using a {@link DataSetSchemaReader}) into a working <CODE>DataSet</CODE>,
 * but this may not be possible, depending on the exported format. For example,
 * a writer may output Data Definition Language (SQL) scripts corresponding to a <CODE>DataSet</CODE>,
 * but there may not be a parser available for that DDL, or the DDL may not correspond
 * exactly to the DDL returned from a database administration tool, due to information
 * that is simply lacking from the DataSet. This limitation should be noted by implementing
 * classes.
 * 
 * <P>The output format, and output sink, is undefined in the interface, and would typically be
 * specified in the constructor of the implementing class.
 *
 * @author Patrick Wright
 */
public interface DataSetSchemaWriter {
    /**
     * Writes an entire {@link DataSet} out to some export format, defined by the implementing class. 
     * All tables and relations are written out on calling this command. Note only the <CODE>DataSet</CODE>
     * schema, not any data, is exported.
     * @param ds The <CODE>DataSet</CODE> to write out.
     * @throws org.jdesktop.databuffer.io.schema.SchemaWriterException If any error occurs while writing the schema.
     */
   void writeDataSet(DataSet ds) throws SchemaWriterException;
    /**
     * Writes a subset of a {@link DataSet DataSet's} schema out to some export format, 
     * for the tables given as an argument, in a format defined by the implementing class. 
     * The named tables and their relations are written out on calling this command. Note only the <CODE>DataSet</CODE>
     * schema, not any data, is exported.
     * @param ds The <CODE>DataSet</CODE> to write out.
     * @param tableNames List of <CODE>DataTable</CODE> names to write out; the schemas for these tables, and their relations, will be written.
     * @throws org.jdesktop.databuffer.io.schema.SchemaWriterException If any error occurs while exporting the schema.
     */
   void writeDataSet(DataSet ds, String... tableNames) throws SchemaWriterException;
}
