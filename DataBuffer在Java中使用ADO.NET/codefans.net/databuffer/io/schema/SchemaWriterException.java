/*
 * $Id: SchemaWriterException.java,v 1.1 2005/11/01 13:35:34 pdoubleya Exp $
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

/**
 * Thrown when a {@link DataSetSchemaReader} cannot write a {@link org.jdesktop.databuffer.DataSet} schema.
 * @author Patrick Wright
 */
public class SchemaWriterException extends Exception {
    /** 
      * Exception with just a message. 
      * @param msg Describes the exception.
     */
    public SchemaWriterException(String msg) {
        super(msg);
    }
    
    /** 
      * Exception with message and cause. 
      * @param msg Describes the exception.
      * @param cause A Throwable that caused this exception to be thrown.
     */   
    public SchemaWriterException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
