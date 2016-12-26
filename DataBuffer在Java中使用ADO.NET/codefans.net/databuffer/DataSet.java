/*
 * $Id: DataSet.java,v 1.26.2.5 2006/01/21 05:19:44 david_hall Exp $
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

import java.beans.PropertyChangeSupport;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.jdesktop.databuffer.event.TableChangeEvent;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * <p>A DataSet is the top-level class for managing multiple {@link DataTable}s as a 
 * group, allowing explicit support for master-detail relationships. A DataSet
 * contains one or more DataTables, one or more {@link DataRelation}s, and one or more 
 * {@link DataValue}s. Through the DataSet API one has a sort of unified access to all
 * these elements in a single group. As a canonical example, a single DataSet could
 * have DataTables for Customers, Orders, and OrderItems, and the relationships
 * between them.
 *
 * <p>The DataSet internal structure read from a DataSet XML 
 * schema using the {@link #createFromSchema(String)} method, which also supports
 * reading from an {@link InputStream} or {@link File}. The internal structure
 * can then be persisted back as an XML schema by retrieving its String form
 * using {@link #getSchema()} and then saving that.
 *
 * <p>Separately, all the data in a DataSet (e.g. rows in the internal DataTables)
 * can be read ({@link #readXml(String)}) or written ({@link #writeXml()}).
 * 
 * <p>DataSet has standard methods for adding or removing single DataTables,
 * DataRelationTables, and DataValues. Note that these instances are automatically
 * tracked, so if their names are changed through their own API, that name change
 * is reflected automatically in the DataSet.
 * 
 * @see DataTable
 * @see DataRelationTable
 * @see DataValue
 * @see DataRelation
 *
 * @author rbair
 */
public class DataSet {
    /** 
     * Flag used for {@link #writeXml(OutputControl)} to indicate whether
     * all rows, or just modified rows should be spit out.
     */
    // TODO: replace with some kind of filter; maybe a functor (PWW 04/29/05)
    public enum OutputControl { ALL_ROWS, MODIFIED_ONLY };
    
    /**
     * The Logger
     */
    private static final Logger LOG = Logger.getLogger(DataSet.class.getName());
    
    //used for communicating changes to this JavaBean, especially necessary for
    //IDE tools, but also handy for any other component listening to this dataset
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    /** The name of this DataSet; should be unique among all instantiated DataSets. */
    private String name;
    
    /** DataTables in this set, keyed by table name. */
    protected Map<String,DataTable> tables = new HashMap<String,DataTable>();
    
    /** DataRelations in this set, keyed by relation name. */
    protected Map<String,DataRelation> relations = new HashMap<String,DataRelation>();

    /** DataValues in this set, keyed by value name. */
    protected Map<String,DataValue> values = new HashMap<String,DataValue>();
    
    /** An extended JFXGParser for parsing calculated columns and data values. */
    private Parser parser;
    
    public DataSet() {
        this("dataset");        
    }
    
    /**
     * Instantiates a DataSet with a specific name; the name can be useful
     * when persisting the DataSet or when working with multiple DataSets.
     * See {@link #setName(String)}
     * for comments about DataSet naming.
     *
     * @param name The name for the DataSet.
     */
    public DataSet(String name) {
        setName(name);
    }

    /**
     * Assigns a new name to this DataSet. Names are not controlled for
     * uniqueness, so the caller must take care to not use
     * the same name for more than one DataSet. Any String that is a valid
     * Java identifier can be used; see comments on {@link DataSetUtils#isValidName(String)}..
     *
     * @param name The new name for the DataSet.
     */
    public void setName(String name) {
        if (this.name != name) {
            if (!DataSetUtils.isValidName(name))
                throw new IllegalArgumentException("Invalid DataSet Name");
            
            String oldName = this.name;
            this.name = name;
            pcs.firePropertyChange("name", oldName, name);
        }
    }
    
    /**
     * Returns the current name for the DataSet.
     *
     * @return the current name for the DataSet.
     */
    // TODO: should we check for (static) uniqueness of names? (PWW 04/28/04)
    //       is there actually any use for the dataset name? (DH 10/3/06)
    public String getName() {
        return name;
    }
    
    /**
     * Creates a new, named DataTable, and adds it to this DataSet; the 
     * table will belong to this DataSet. If the DataSet already has a table
     * with that name, the new, empty DataTable will take its place.
     *
     * @param name The new DataTable's name.
     * @return a new unnamed DataTable assigned to this DataSet.
     */
    public DataTable createTable(String name) {
        return new DataTable(this, name);
    }
    
    /**
     * Creates a new, named DataRelation, and adds it to this DataSet; the 
     * table will belong to this DataSet. If the DataSet already has a table
     * with that name, the new, empty DataRelation will take its place.
     *
     * @param name The new DataRelation's name.
     * @return a new unnamed DataRelation assigned to this DataSet.
     */
    public DataRelation createRelation(String name, DataColumn parentColumn, DataColumn childColumn){
        return new DataRelation(this, name, parentColumn, childColumn);
    }


    /**
     * Creates a new DataValue with the given name and expression, and adds it
     * to this DataSet;
     *
     * @param name The new DataValue's name.  Must be unique within a given DataSet.
     * @param expression The new DataValue's formula
     * @return the new DataValue.
     * @throw java.lang.IllegalArgumentException if a value with the given name already exists.
     */
    public DataValue createValue(String name, String expression) {
        return new SummaryValue(this, name, expression);
    }

    
    /** 
     * Checks whether this DataSet has a relation, table or value by the given 
     * name.
     *
     * @param name The name to lookup.
     * @return true if this DataSet has a table, relation or value with that name.
     */
    protected boolean hasElement(String name) {
        boolean b = relations.containsKey(name);
        if (!b) {
            b = tables.containsKey(name);
        }
        if (!b) {
            b = values.containsKey(name);
        }
        return b;
    }
    
    /**
     * Given some path, return the proper element. Paths are:
     * <ul>
     * <li>tableName</li>
     * <li>tableName.colName</li>
     * <li>dataRelationTableName</li>
     * <li>dataRelationTableName.colName</li>
     * <li>relationName</li>
     * <li>valueName</li>
     * </ul>
     * @param path The identifier for the element in question; see desc.
     * @return the element in question as an Object, or null if it doesn't
     * identify anything in this DataSet.
     */
    // TODO: this was made public to be usable from the XML schema reader, need to review (PWW 11/01/2005)
    public Object getElement(String path) {
        if (path == null) {
            return null;
        }
        if (path.contains(".")) {
            //must be a table
            String[] steps = path.split("\\.");
            assert steps.length == 2;
            DataTable table = tables.get(steps[0]);
            DataColumn col = table.getColumn(steps[1]);
            if (col != null) {
                return col;
            }
        } else {
            if (relations.containsKey(path)) {
                return relations.get(path);
            } else if (tables.containsKey(path)) {
                return tables.get(path);
            } else if (values.containsKey(path)) {
                return values.get(path);
            }
        }
        return null;
    }
    
    /** TODO */
    /*
     * TODO: this is a method of retrieving a subset of rows from a DataSet, by 
     * naming a table or relation and optional selectors in a path expression--
     * need to document!
     * if not found, will be an empty list, and if found, list is unmodifiable
     */
    public List<DataRow> getRows(String path) {
        if (path == null || path.trim().equals("")) {
            return Collections.EMPTY_LIST;
        }
        
        path = path.trim();
        
        //first, split on "."
        String[] steps = path.split("\\.");
        
        //each step is either a specific name ("myTable"), or is a composite
        //of a name and an index ("myTable[mySelector]")
        
        //maintain a collection of results
        List<DataRow> workingSet = null;
        
        for (String step : steps) {
            String name = null;
            String selectorName = null;
            if (step.contains("[")) {
                name = step.substring(0, step.indexOf('['));
                selectorName = step.substring(step.indexOf('[')+1,  step.indexOf(']'));
            }
            
            if (workingSet == null) {
                //get all of the results from the named object (better be a
                //table, not a relation!)
                DataTable table = tables.get(name);
                assert table != null;
                
                workingSet = table.getRows();
                if (selectorName != null) {
                    // TODO: why is the reassignment for workingSet commented out (PWW 04/27/05)
//                    workingSet = filterRows(workingSet, selectors.get(selectorName));
                }
            } else {
                //better be a relation...
                DataRelation relation = relations.get(name);
                assert relation != null;
                    
                workingSet = relation.getRows((DataRow[])workingSet.toArray(new DataRow[workingSet.size()]));
                if (selectorName != null) {
                    // TODO: why is the reassignment for workingSet commented out (PWW 04/27/05)
//                    workingSet = filterRows(workingSet, selectors.get(selectorName));
                }
            }
        }
        return Collections.unmodifiableList(workingSet);
    }
    
    /** 
     * Returns a list of rows that match a given selector; convenience method.
     *
     * @param rows The rows to select from.
     * @param ds The DataSelector with indices to filter with.
     * @return a List of DataRows matching the given DataSelector; empty if
     * none match.
     */
    // TODO: this has nothing to do with DataSet--put in utils? (PWW 04/27/05)
    public List<DataRow> filterRows(List<DataRow> rows, List<Integer> indices) {
        List<DataRow> results = new ArrayList<DataRow>(indices.size());
        for (int index : indices) {
            results.add(rows.get(index));
        }
        return results;
    }
    
    // TODO: document (PWW 04/27/05)
    public List<DataColumn> getColumns(String path) {
        //path will either include a single table name, or a single
        //relation name, followed by a single column name
        String[] parts = path.split("\\.");
        assert parts.length == 1 || parts.length == 2;
        
        DataTable table = tables.get(parts[0]);
        if (table == null) {
            DataRelation relation = relations.get(parts[0]);
            if (relation == null) {
                return new ArrayList<DataColumn>();
            } else {
                table = relation.getChildColumn().getTable();
            }
        }
        
        if (parts.length == 1) {
            return table.getColumns();
        } else {
            List<DataColumn> results = new ArrayList<DataColumn>();
            results.add(table.getColumn(parts[1]));
            return Collections.unmodifiableList(results);
        }
    }
    
    /**
     * Looks up a DataTable in this DataSet, by name.
     *
     * @param tableName The name of the table to retrieve.
     * @return the DataTable, if found, or null, if not.
     */
    public DataTable getTable(String tableName) {
        return tables.get(tableName);
    }
    
    /**
     * Returns a List of the DataTables in this DataSet.
     *
     * @return a List of the DataTables in this DataSet; empty if no tables
     * in this DataSet.
     */
    public List<DataTable> getTables() {
        List<DataTable> tableList = new ArrayList<DataTable>();
        for(DataTable table : tables.values()) {
            tableList.add(table);
        }
        return tableList;
    }
    
    /** 
     * Looks up a DataValue in this set by name.
     *
     * @param valueName The name of the DataValue.
     * @return the named DataValue, or null if no value with that name.
     */
    public DataValue getValue(String valueName) {
        return values.get(valueName);
    }
    
    /**
     * Retrieves a list of all DataValues in this set.
     *
     * @return list of all DataValues in this set, empty if none assigned.
     */
    public List<DataValue> getValues() {
        List<DataValue> values = new ArrayList<DataValue>();
        for (DataValue v : this.values.values()) {
            values.add(v);
        }
        return values;
    }
    
    /** 
     * Looks up a DataRelation in this set by name.
     *
     * @param relationName The name of the DataRelation.
     * @return the named DataRelation, or null if no value with that name.
     */
    public DataRelation getRelation(String relationName) {
        return relations.get(relationName);
    }
    
    /**
     * Retrieves a list of all DataRelation in this set.
     *
     * @return list of all DataRelation in this set, empty if none assigned.
     */
    public List<DataRelation> getRelations() {
        List<DataRelation> relations = new ArrayList<DataRelation>();
        for (DataRelation r : this.relations.values()) {
            relations.add(r);
        }
        return relations;
    }
    
    /** 
     * Requests that each {@link DataTable} in this DataSet load itself; this is 
     * an <em>asynchronous</em> operation. See {@link DataTable#load()}; tables must have
     * been assigned a {@link DataProvider} already.
     */
    public void load() {
        for (DataTable table : tables.values()) {
            table.load();
        }
    }
    
    /** 
     * Requests that each {@link DataTable} in this DataSet load itself; this is 
     * a <em>synchronous</em> operation. See {@link DataTable#loadAndWait()}; tables must have
     * been assigned a {@link DataProvider} already.
     */
    public void loadAndWait() {
        for (DataTable table : tables.values()) {
            table.loadAndWait();
        }
    }
    
    /** 
     * Requests that each {@link DataTable} in this DataSet refresh itself; this is 
     * an <em>asynchronous</em> operation. See {@link DataTable#refresh()}; tables must have
     * been assigned a {@link DataProvider} already.
     */
    public void refresh() {
        for (DataTable table : tables.values()) {
            table.refresh();
        }
    }
    
    /** 
     * Requests that each {@link DataTable} in this DataSet refresh itself; this is 
     * a <em>synchronous</em> operation. See {@link DataTable#refreshAndWait()}; tables must have
     * been assigned a {@link DataProvider} already.
     */
    public void refreshAndWait() {
        for (DataTable table : tables.values()) {
            table.refreshAndWait();
        }
    }
    
    /** 
     * Requests that each {@link DataTable} in this DataSet clear itself; this is 
     * a <em>synchronous</em> operation since the clear operation is not anticipated to
     * take more than a couple of milliseconds at worst.
     * See {@link DataTable#clear()};
     */
    public void clear() {
        for (DataTable table : tables.values()) {
            table.clear();
        }
    }
    
    /** 
     * Requests that each {@link DataTable} in this DataSet save itself; this is 
     * an <em>asynchronous</em> operation. See {@link DataTable#save()}; tables must have
     * been assigned a {@link DataProvider} already.
     */
    public void save() {
        for (DataTable table : tables.values()) {
            table.save();
        }
    }
    
    /** 
     * Requests that each {@link DataTable} in this DataSet save itself; this is 
     * a <em>synchronous</em> operation. See {@link DataTable#saveAndWait()}; tables must have
     * been assigned a {@link DataProvider} already.
     */
    public void saveAndWait() {
        for (DataTable table : tables.values()) {
            table.saveAndWait();
        }
    }

    /**
     * @return the Functor parser
     */
    Parser getParser() {
        if (parser == null) {
            parser = new Parser(this);
        }
            
        return parser;
    }


    /** 
     * Same as {@link #readXml(String)}, but using an InputStream as input source.
     *
     * @param is The XML Document, as an InputStream.
     */
     //* TODO: need the schema documented somewhere; we might want to list the URL for the schema here (PWW 04/27/05)
    public void readXml(InputStream is) {
        String xml = "";
        try {
            StringBuilder builder = new StringBuilder();
            byte[] bytes = new byte[4096];
            int length = -1;
            while ((length = is.read(bytes)) != -1) {
                builder.append(new String(bytes, 0, length));
            }
            xml = builder.toString();
             readXml(xml);
        } catch (Exception e) {
	    LOG.log(Level.SEVERE, "Failed to read xml from input stream.", e);
        }
    }
    
    /** 
     * Loads DataSet data from an XML Document in String format; when complete, the DataSet
     * will have been populated from the Document.
     *
     * @param xml The XML Document, as a String.
     */
     //* TODO: need the schema documented somewhere; we might want to list the URL for the schema here (PWW 04/27/05)
    public void readXml(String xml) {
        //TODO when parsing the xml, validate it against the xml schema
        
        try { 
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document dom = db.parse(new ByteArrayInputStream(xml.getBytes()));
            
            //create the xpath
            XPath xpath = XPathFactory.newInstance().newXPath();
            
            String path = null;
            //for each table, find all of its items
            for (DataTable table : tables.values()) {
                table.fireDataTableChanged(TableChangeEvent.newLoadStartEvent(table));
                //clear out the table
                table.clear();

                if ( !table.isAppendRowSupported()) {
                    LOG.fine("Table '" + table.getName() + "' does " +
                            "not support append row; skipping (regardless of " +
                            "input).");
                    continue;
                }

                LOG.finer("loading table " + table.getName());

                //get the nodes
                path = "/" + name + "/" + table.getName();
                NodeList nodes = (NodeList)xpath.evaluate(path, dom, XPathConstants.NODESET);

                LOG.finer("  found " + nodes.getLength() + " rows for path " + path);

                //for each node, iterate through the columns, loading their values
                for (int i=0; i<nodes.getLength(); i++) {
                    //each rowNode node represents a row
                    Node rowNode = nodes.item(i);
                    DataRow row = table.appendRowNoEvent();
                    NodeList cols = rowNode.getChildNodes();
                    for (int j=0; j<cols.getLength(); j++) {
                        Node colNode = cols.item(j);
                        if (colNode.getNodeType() == Node.ELEMENT_NODE) {
                            //TODO this doesn't take into account type conversion...
                            //could use a default converter...
//                            	System.out.println(colNode.getNodeName() + "=" + colNode.getTextContent());
                            String text = colNode.getTextContent();
                            //convert the text to the appropriate object of the appropriate type
                            Object val = text;
                            Class type = table.getColumn(colNode.getNodeName()).getType();
                            if (type == BigDecimal.class) {
                                val = new BigDecimal(text);
                            }
                            //TODO do the other types
                            row.setValue(colNode.getNodeName(), val);                            }
                    }
                    row.setStatus(DataRow.DataRowStatus.UNCHANGED);
                }
                table.fireDataTableChanged(TableChangeEvent.newLoadCompleteEvent(table));
            }
        } catch (Exception e) {
	    LOG.log(Level.SEVERE, "Failed to parse xml {0}. {1}", new Object[]{xml, e.getStackTrace()});
        }
    }
    
    /** 
     * Writes out the data in this DataSet as XML, and returns this as a String;
     * all rows are exported.
     *
     * @return the contents of this DataSet, as XML, in a String.
     */
     //* TODO: need the schema documented somewhere; we might want to list the URL for the schema here (PWW 04/27/05)
    public String writeXml() {
        return writeXml(OutputControl.ALL_ROWS);
    }

    /** 
     * Writes out the data in this DataSet as XML, and returns this as a String.
     *
     * @param flags Value indicating whether all rows (OutputControl.ALL_ROWS)
     * or only new and modified rows should be spit out.
     * @return the contents of this DataSet, as XML, in a String.
     */
     //* TODO: need the schema documented somewhere; we might want to list the URL for the schema here (PWW 04/27/05)
    public String writeXml(OutputControl flags) {
        StringBuilder builder = new StringBuilder();
        builder.append("<?xml version=\"1.0\" ?>\n");
        builder.append("<");
        builder.append(name);
        builder.append(">\n");
        for (DataTable table : tables.values()) {
            for (DataRow row : table.rows) {
                if ( flags == OutputControl.MODIFIED_ONLY && row.getStatus() == DataRow.DataRowStatus.UNCHANGED ) {
                    continue;                        
                }

                builder.append("\t<");
                builder.append(table.getName());
                builder.append(">\n");

                for (DataColumn col : table.columns.values()) {
                    builder.append("\t\t<");
                    builder.append(col.getName());
                    builder.append(">");
                    String val = row.getValue(col) == null ? "" : row.getValue(col).toString();
                    //escape val
                    val = val.replaceAll("&", "&amp;");
                    val = val.replaceAll("<", "&lt;");
                    val = val.replaceAll(">", "&gt;");
                    val = val.replaceAll("'", "&apos;");
                    val = val.replaceAll("\"", "&quot;");
                    builder.append(val);
                    builder.append("</");
                    builder.append(col.getName());
                    builder.append(">\n");
                }

                builder.append("\t</");
                builder.append(table.getName());
                builder.append(">\n");
            }
        }
        builder.append("</");
        builder.append(name);
        builder.append(">");
        
        return builder.toString();
    }
    
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("DataSet: ").append(name).append("\n");
        for (DataTable table : tables.values()) {
            buffer.append("\tDataTable: ").append(table.getName()).append("\n");
            for (DataColumn col : table.getColumns()) {
                buffer.append("\t\tDataColumn: ").append(col.getName()).append("\n");
            }
        }
        for (DataRelation relation : relations.values()) {
            buffer.append("\tRelation: ").append(relation.getName()).append("\n");
            DataColumn parentColumn = relation.getParentColumn();
            buffer.append("\t\tParentColumn: ").append(parentColumn == null ? "<none>" : parentColumn.getTable().getName()).append(".").append(parentColumn == null ? "<none>" : parentColumn.getName()).append("\n");
            DataColumn childColumn = relation.getChildColumn();
            buffer.append("\t\tChildColumn: ").append(childColumn == null ? "<none>" : childColumn.getTable().getName()).append(".").append(childColumn == null ? "<none>" : childColumn.getName()).append("\n");
        }
//         buffer.append(getSchema());
        return buffer.toString();
    }
}
