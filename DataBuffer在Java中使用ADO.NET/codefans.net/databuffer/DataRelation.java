/*
 * $Id: DataRelation.java,v 1.7.2.4 2006/01/21 04:18:57 david_hall Exp $
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

package org.jdesktop.databuffer;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * 
 * @author rbair
 */
public class DataRelation {
    /**
     * The Logger
     */
    private static final Logger LOG = Logger.getLogger(DataRelation.class.getName());
    
    /**
     * The DataSet that created this DataRelation. This is a readonly property
     */
    private DataSet dataSet;
    /**
     * The name of the DataRelation.
     */
    private String name;
    /**
     * The DataColumn from a "parent" DataTable to use in establishing this
     * relationship. If you need multiple columns in the parent, create a
     * single calculated column in the parent and use that instead
     */
    private DataColumn parentColumn;
    /**
     * The DataColumn from a "child" DataTable to use in establishing this
     * relationship. childColumn cannot equal DataColumn, but it can come
     * from the same table.
     */
    private DataColumn childColumn;
    
    public DataRelation(DataSet ds, String name, DataColumn parentColumn, DataColumn childColumn) {
        if (ds == null) {
            throw new IllegalArgumentException(
                    "DataSet cannot be null for a DataRelation");
        }
        if (name == null) {
            throw new IllegalArgumentException(
                    "name cannot be null for a DataRelation");
        } else if (!DataSetUtils.isValidName(name)) {
            throw new IllegalArgumentException(
                    "'" + name + "' is not a valid name for a DataRelation");
        } else if (ds.hasElement(name)) {
            throw new IllegalArgumentException(
                    "An element named '" + name + "' is already part of this " +
                    "DataSet. You cannot have two elements with the same name");
        }
        if (parentColumn == null) {
            throw new IllegalArgumentException("parentColumn cannot be null");
        }
        if (childColumn == null) {
            throw new IllegalArgumentException("childColumn cannot be null");
        }
        if (parentColumn == childColumn) {
            throw new IllegalArgumentException("Cannot have circular references. parentColumn != childColumn");
        }
        
        this.name = name;
        this.dataSet = ds;
        this.parentColumn = parentColumn;
        this.childColumn = childColumn;

        ds.relations.put(name, this);
    }
    
    /**
     * @return the DataSet that this DataRelation belongs to
     */
    public DataSet getDataSet() {
        return dataSet;
    }
    
    /**
     * @return The name of this DataRelation
     */
    public String getName() {
        return name;
    }

    /**
     * @return the DataColumn that is the parent in this parent/child relation
     */
    public DataColumn getParentColumn() {
        return parentColumn;
    }

    /**
     * @return The child DataColumn in this parent/child relation
     */
    public DataColumn getChildColumn() {
        return childColumn;
    }

    /**
     * Given a DataRow from the parent DataTable, return a list of
     * related DataRows from the child DataTable.
     * @param parentRow
     */
    public List<DataRow> getRows(DataRow parentRow) {
        //return an empty list if I don't have enough info to produce any
        //child rows. Short circuits this method
        if (parentColumn == null || childColumn == null || parentRow == null) {
            return Collections.unmodifiableList(Collections.EMPTY_LIST);
        }
        
        //make sure that this DataRow is from the parent DataTable!
        if (! parentRow.getTable().equals(parentColumn.getTable())) {
            String msg = "Given row is not from parent table \"{0}\"";
            Object args = new Object[]{ parentColumn.getTable().getName() };
            throw new IllegalArgumentException(MessageFormat.format(msg, args));
        }
        
        DataTable childTable = childColumn.getTable();
        Object parentKey = parentColumn.getTable().getValue(parentRow, parentColumn);
        List<DataRow> rows = new ArrayList<DataRow>();
        for (DataRow childRow : childTable.getRows()) {
            Object childKey = childTable.getValue(childRow,  childColumn);
            if (parentKey != null && childKey != null && parentKey.equals(childKey)) {
                rows.add(childRow);
            }
        }
        return Collections.unmodifiableList(rows);
    }

    /**
     * Given the index of a row in the parent DataTable, produce a corrosponding
     * list of related rows from the child DataTable
     *
     * @param parentRowIndex
     */
    public List<DataRow> getRows(int parentRowIndex) {
        if (parentColumn == null || childColumn == null || parentRowIndex < 0) {
            return Collections.unmodifiableList(Collections.EMPTY_LIST);
        }
        //NOTE: No need to check the parentRowIndex for an upper out of bounds
        //condition because the table will do so in the parentTable.getRow()
        //method call.
        DataTable parentTable = parentColumn.getTable();
        return getRows(parentTable.getRow(parentRowIndex));
    }
    
    /**
     * Given an array of DataRows, produce the union of the results for each
     * DataRow from the child DataTable
     *
     * @param parentRows
     */
    public List<DataRow> getRows(DataRow[] parentRows) {
        List<DataRow> rows = new ArrayList<DataRow>();
        for (DataRow parentRow : parentRows) {
            rows.addAll(getRows(parentRow));
        }
        return Collections.unmodifiableList(rows);
    }
    
    /**
     * Given an array if parent row indices, produce the union of the results
     * for each index from the child DataTable
     * 
     * @param parentRowIndices
     */
    public List<DataRow> getRows(int[] parentRowIndices) {
        //short circuit the method if I don't have enough info to produce proper
        //results
        if (parentColumn == null || childColumn == null || parentRowIndices == null) {
            return Collections.unmodifiableList(Collections.EMPTY_LIST);
        }
        
        DataTable parentTable = parentColumn.getTable();
        DataRow[] parentRows = new DataRow[parentRowIndices.length];
        for (int i=0; i<parentRows.length; i++) {
            parentRows[i] = parentTable.getRow(parentRowIndices[i]);
        }
        return getRows(parentRows);
    }
    
    /**
     * Given a List of indices, produce the union of the results for each index
     * from the child DataTable
     *
     * @param parentRowIndices
     */
    public List<DataRow> getRows(List<Integer> parentRowIndices) {
        //short circuit this method if I don't have enough info
        if (parentColumn == null || childColumn == null || parentRowIndices == null) {
            return Collections.unmodifiableList(Collections.EMPTY_LIST);
        }
        
        DataTable parentTable = parentColumn.getTable();
        DataRow[] parentRows = new DataRow[parentRowIndices.size()];
        for (int i=0; i<parentRows.length; i++) {
            parentRows[i] = parentTable.getRow(parentRowIndices.get(i));
        }
        return getRows(parentRows);
    }
}
