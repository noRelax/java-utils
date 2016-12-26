/*
 * RecordSet.java v1.0 2007-09-24 
 * Copyright 2007  齐智行科技
 */

package org.jdesktop.databuffer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.util.Date;
import org.jdesktop.databuffer.provider.sql.ResultSetDataProvider;
import org.jdesktop.databuffer.provider.sql.SQLCommand;
import org.jdesktop.databuffer.provider.sql.SQLDataProvider;

/**
 * <code>RecordSet<code>类向你提供了一个行列结构的记录集对象，它类似于ADO时代的RecordSet对象。
 * RecordSet是一个内存中的表，使用时要注意尽量不要存放较大的记录集对象，这会占用很多计算机内存资源，
 * 使用完毕后要及时将该对象释放。
 * 
 * @author zlz
 */
public class RecordSet {

	private DataSet dataSet;
	private DataTable dataTable;
	private String tableName;
	private int curRow;
	private boolean eof;
	private boolean bof;

	public RecordSet(String pTableName) throws SQLException {
		dataSet = new DataSet("DataSet");
		tableName = pTableName;
		dataTable = new DataTable(dataSet, tableName);
		this.first();
	}
	
	public RecordSet() throws SQLException {
		dataSet = new DataSet("DataSet");
		tableName = "table1";
		dataTable = new DataTable(dataSet, tableName);
		this.first();
	}

	/**
	 * 增加一个新列,并返回这个新列
	 * @param pColName - 列的名称，pColType - 列的类型
	 */
	public void addCol(String pColName, Class pColType) {
		dataTable.createColumn(pColType, pColName);
	}

	/**
	 * 增加一个String类型的新列,并返回这个新列
	 * @param pColName - 列的名称
	 */
	public void addStrCol(String pColName) {
		dataTable.createColumn(String.class, pColName);
	}

	/**
	 * 增加一个int类型的新列，并返回这个新列
	 * @param pColName - 列的名称
	 */
	public void addIntCol(String pColName) {
		dataTable.createColumn(int.class, pColName);
	}

	/**
	 * 增加一个float类型的新列,并返回这个新列
	 * @param pColName - 列的名称
	 */
	public DataColumn addFltCol(String pColName) {
		return dataTable.createColumn(float.class, pColName);
	}

	/**
	 * 在RecordSet中增加一个新行,并返回这个新加的行
	 * @return null if !appendRowSupported, else the newly created row
	 */
	public DataRow addRow() throws SQLException {
		DataRow dr = dataTable.appendRow();
		this.setCurRow(this.getRowCount());
		return dr;
	}

	/**
	 * 删除当前行
	 */
	public void deleteRow() {
		dataTable.deleteRow(this.curRow);
		this.setCurRow(this.getCurRow() - 1);
	}

	/**
	 * 删除指定行
	 * @param rowIndex 待删除行的索引.
	 */
	public void deleteRow(int pRowIndex) {
		this.setCurRow(pRowIndex);
		this.deleteRow();
	}

	/**
	 *复制ResultSet对象的结构和数据，填充到RecordSet对象中
	 *@param pRs - 待复制的ResultSet对象
	 */
	public void fillByResultSet(ResultSet pRs) {

		ResultSetDataProvider rsdp = new ResultSetDataProvider(pRs);
		rsdp.loadAndWait(dataTable);
	}

	/**
	 * 执行指定的SQL查询语句，并将执行后的结果级填充到ResultSet对象中。此方法不支持返回多个结果集的查询语句。
	 * @param pSQL - 指定的查询语句
	 */
	public void fillBySQL(String pSQL, DataConnection pConn) {
		SQLCommand cmd = new SQLCommand();
		cmd.setSelectSQL(pSQL);
		SQLDataProvider dp = new SQLDataProvider();
		dp.setConnection(pConn);
		dp.setCommand(cmd);
		dataTable.setDataProvider(dp);
		dataTable.loadAndWait();
	}

	/**
	 * 移动RecordSet的当前行指针到第一行
	 */
	public void first() {
		this.curRow = 1;
	}

	/**
	 *将ResultSet对象中的数据以XML字符串形式返回
	 */
	public String getXml() {
		String xmldoc = dataSet.writeXml();
		//转换文件编码格式为GBK
		xmldoc = "<?xml version=\"1.0\" encoding=\"GBK\" ?>"  + xmldoc.substring(22);
	    return xmldoc; 
	}

	/**
	 * 移动RecordSet的当前行指针到最后一行
	 */
	public void last() {
		this.curRow = dataTable.getRowCount();
	}

	/**
	 * 从指定的文件中获取数据，并填充当前RecordSet对象。
	 * 执行该方法前需要事先设置RecordSet的列，使其与指定文件中的数据集结构保持一致，否则会抛出一个错误
	 * @param pFileName - 指定文件完整路径名称
	 */
	public void loadFromFile(String pFileName)  throws Exception{
      
		java.io.FileInputStream fis = new java.io.FileInputStream(pFileName);
		dataSet.readXml(fis);
	}

	/**
	 * 移动RecordSet的当前行指针到下一行。
	 */
	public void next() {
		if (this.isEof()) {
			throw new IllegalArgumentException("Error of CDataTable:Eof");
		} else {
			this.setCurRow(this.getCurRow() + 1);
		}
	}

	/**
	 * 移动RecordSet的当前行指针到上一行。
	 */
	public void previous() {
		if (this.isBof()) {
			throw new IllegalArgumentException("Error of RecordSet:Bof");
		} else {
			this.setCurRow(this.getCurRow() - 1);
		}
	}

	/**
	 * 读取存储在XML字符串中的记录集结构与数据，并填充当前RecordSet对象。
	 * @param pXmlDoc - 存储记录集结构与数据的XML字符串。
	 */
	public void readXml(String pXmlDoc) throws Exception{
		dataSet.readXml(pXmlDoc);
	}

	/**
	 * 刷新ResultSet;
	 */
	public void refresh() {
		dataTable.refreshAndWait();
	}

	/**
	 * 保存RecordSet记录集的结构与数据，存储到指定的磁盘文件中
	 * @param pFileName - 指定的磁盘文件名称
	 */
	public void saveToFile(String pFileName)  throws Exception{
        
        java.io.FileOutputStream file = new java.io.FileOutputStream(pFileName); 
        java.io.OutputStreamWriter osw = new java.io.OutputStreamWriter(file,"GBK");        
        java.io.BufferedWriter bw = new java.io.BufferedWriter(osw);
        bw.write(this.getXml());
        bw.close();        
	}

	/** 
	 * 指示当前行指针是否位于第一行之前
	 */
	public boolean isEof() {
		return eof;
	}

	/** 
	 * 指示当前行指针是否位于最后一行之后
	 */
	public boolean isBof() {
		return bof;
	}

	/** 
	 * 返回RecordSet记录集中行的个数
	 */
	public int getRowCount() {
		return dataTable.getRowCount();
	}

	/** 
	 *  返回RecordSet记录集中列的个数。
	 */
	public int getColCount() {
		return dataTable.getColumns().size();
	}

	/** 
	 *  返回当前行指针的位置
	 */
	public int getCurRow() {

		return curRow;
	}

	/** 
	 *  设置当前行指针的位置
	 */
	public void setCurRow(int pCurRow) {
		if (pCurRow < 1) {
			bof = true;
		} else if (pCurRow > this.getRowCount()) {
			eof = true;
		} else {
			curRow = pCurRow;
			eof = false;
			bof = false;
		}
	}

	/** 
	 *  返回RecordSet的表名称
	 */
	public String getTableName() {
		return tableName;
	}

	
	/** 
	 *  设置RecordSet的表名称
	 */
	public void setTableName(String pTableName) {
		dataTable.setName(pTableName);
		tableName = pTableName;
	}
	
	
	/** 
	 *  返回ResultSet对象当前行指定列的值，在JAVA语言中作为一个java.math.BigDecimal对象;
	 *  @param pColumnIndex - 第一列是1, 第一列是2, ...    
	 */
	public BigDecimal getBigDecimal(int pColumnIndex) {
		Object colValue = this.getObject(pColumnIndex);
		return new BigDecimal((String) colValue);
	}

	/** 
	 *  返回ResultSet对象当前行指定列的值，在JAVA语言中作为一个java.math.BigDecimal对象;
	 *  @param pColumnName - 指定列的名称   
	 */
	public BigDecimal getBigDecimal(String pColumnName) {
		Object colValue = this.getObject(pColumnName);
		return new BigDecimal((String) colValue);
	}

	/** 
	 *  返回ResultSet对象当前行指定列的值，在JAVA语言中作为一个boolean类型的数值;
	 *  @param pColumnIndex - 第一列是1, 第一列是2, ...  
	 */
	public boolean getBoolean(int pColumnIndex) {
		Object colValue = this.getObject(pColumnIndex);
		if (colValue.toString() == "true") {
			return true;
		} else {
			return false;
		}
	}

	/** 
	 *  返回ResultSet对象当前行指定列的值，在JAVA语言中作为一个boolean类型的数值;
	 *  @param pColumnName - 指定列的名称   
	 */
	public boolean getBoolean(String pColumnName) {
		Object colValue = this.getObject(pColumnName);
		if (colValue.toString() == "true") {
			return true;
		} else {
			return false;
		}
	}

	/** 
	 *  返回ResultSet对象当前行指定列的值，在JAVA语言中作为一个double类型的数值;
	 *  @param pColumnIndex - 第一列是1, 第一列是2, ...  
	 */
	public double getDouble(int pColumnIndex) {
		return Double.parseDouble(this.getObject(pColumnIndex).toString());
	}

	/** 
	 *  返回ResultSet对象当前行指定列的值，在JAVA语言中作为一个double类型的数值;
	 *  @param pColumnName - 指定列的名称   
	 */
	public double getDouble(String pColumnName) {
		return Double.parseDouble(this.getObject(pColumnName).toString());
	}

	/** 
	 *  返回ResultSet对象当前行指定列的值，在JAVA语言中作为一个float类型的数值;
	 *  @param pColumnIndex - 第一列是1, 第一列是2, ...  
	 */
	public float getFloat(int pColumnIndex) {
		return Float.parseFloat(this.getObject(pColumnIndex).toString());
	}

	/** 
	 *  返回ResultSet对象当前行指定列的值，在JAVA语言中作为一个float类型的数值;
	 *  @param pColumnName - 指定列的名称   
	 */
	public float getFloat(String pColumnName) {
		return Float.parseFloat(this.getObject(pColumnName).toString());
	}

	/** 
	 *  返回ResultSet对象当前行指定列的值，在JAVA语言中作为一个int类型的数值;
	 *  @param pColumnIndex - 第一列是1, 第一列是2, ...  
	 */
	public int getInt(int pColumnIndex) {
		return Integer.parseInt(this.getObject(pColumnIndex).toString());
	}

	/** 
	 *  返回ResultSet对象当前行指定列的值，在JAVA语言中作为一个int类型的数值;
	 *  @param pColumnName - 指定列的名称   
	 */
	public int getInt(String pColumnName) {
		return Integer.parseInt(this.getObject(pColumnName).toString());
	}

	/** 
	 *  返回ResultSet对象当前行指定列的值，在JAVA语言中作为一个Long类型的数值;
	 *  @param pColumnIndex - 第一列是1, 第一列是2, ...  
	 */
	public long getLong(int pColumnIndex) {
		return Long.parseLong(this.getObject(pColumnIndex).toString());
	}

	/** 
	 *  返回ResultSet当前行指定列的值，在JAVA语言中作为Long类型的数值;;
	 *  @param pColumnName - 指定列的名称  
	 */
	public long getLong(String pColumnName) {
		return Long.parseLong(this.getObject(pColumnName).toString());
	}

	/** 
	 *  返回ResultSet对象当前行指定列的值，在JAVA语言中作为一个java.util.Date对象;
	 *  @param pColumnIndex - 第一列是1, 第一列是2, ...  
	 */
	public Date getDate(int pColumnIndex) {
		return new Date("this.getObject(pColumnIndex).toString()");

	}

	/** 
	 *  返回ResultSet当前行指定列的值，在JAVA语言中作为一个java.util.Date对象;
	 *  @param pColumnName - 指定列的名称  
	 */
	public Date getDate(String pColumnName) {
		return new Date("this.getObject(pColumnName).toString()");
	}

	/** 
	 *  返回ResultSet当前行指定列的值，在JAVA语言中作为一个Object对象
	 *  @param pColumnIndex - 第一列是1, 第一列是2, ...  
	 */
	public Object getObject(int pColumnIndex) {
		String colName = dataTable.getColumns().get(pColumnIndex - 1).getName();
		return this.getObject(colName);
	}

	/** 
	 *  返回ResultSet当前行指定列的值，在JAVA语言中作为一个Object对象
	 *  @param pColumnName - 指定列的名称  
	 */
	public Object getObject(String pColumnName) {
		if (this.isBof()) {
			throw new IllegalArgumentException("Error of RecordSet:Bof");
		} else if (this.isEof()) {
			throw new IllegalArgumentException("Error of RecordSet:Eof");
		} else {
			return dataTable.getValue(this.getCurRow() - 1 , pColumnName);
		}
	}

	/** 
	 *  为ResultSet的当前行，指定列赋值
	 *  @param pColumnIndex - 第一列是1, 第一列是2, ...
	 *  @param pValue - 值    
	 */
	public void setObject(int pColumnIndex, Object pValue) {
		String colName = dataTable.getColumns().get(pColumnIndex - 1).getName();
		dataTable.setValue(this.curRow, colName, pValue);
	}

	/** 
	 *  为ResultSet的当前行，指定列赋值
	 *   @param pColumnName - 指定列的名称  
	 *  @param pValue - 值    
	 */
	public void setObject(String pColumnName, Object pValue) {
		if (this.isBof()) {
			throw new IllegalArgumentException("Error of RecordSet:Bof");
		} else if (this.isEof()) {
			throw new IllegalArgumentException("Error of RecordSet:Eof");
		} else {
			dataTable.setValue(this.curRow - 1, pColumnName, pValue);
		}
	}

}
