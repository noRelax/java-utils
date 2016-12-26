/*
 * RecordSet.java v1.0 2007-09-24 
 * Copyright 2007  �����пƼ�
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
 * <code>RecordSet<code>�������ṩ��һ�����нṹ�ļ�¼��������������ADOʱ����RecordSet����
 * RecordSet��һ���ڴ��еı�ʹ��ʱҪע�⾡����Ҫ��Žϴ�ļ�¼���������ռ�úܶ������ڴ���Դ��
 * ʹ����Ϻ�Ҫ��ʱ���ö����ͷš�
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
	 * ����һ������,�������������
	 * @param pColName - �е����ƣ�pColType - �е�����
	 */
	public void addCol(String pColName, Class pColType) {
		dataTable.createColumn(pColType, pColName);
	}

	/**
	 * ����һ��String���͵�����,�������������
	 * @param pColName - �е�����
	 */
	public void addStrCol(String pColName) {
		dataTable.createColumn(String.class, pColName);
	}

	/**
	 * ����һ��int���͵����У��������������
	 * @param pColName - �е�����
	 */
	public void addIntCol(String pColName) {
		dataTable.createColumn(int.class, pColName);
	}

	/**
	 * ����һ��float���͵�����,�������������
	 * @param pColName - �е�����
	 */
	public DataColumn addFltCol(String pColName) {
		return dataTable.createColumn(float.class, pColName);
	}

	/**
	 * ��RecordSet������һ������,����������¼ӵ���
	 * @return null if !appendRowSupported, else the newly created row
	 */
	public DataRow addRow() throws SQLException {
		DataRow dr = dataTable.appendRow();
		this.setCurRow(this.getRowCount());
		return dr;
	}

	/**
	 * ɾ����ǰ��
	 */
	public void deleteRow() {
		dataTable.deleteRow(this.curRow);
		this.setCurRow(this.getCurRow() - 1);
	}

	/**
	 * ɾ��ָ����
	 * @param rowIndex ��ɾ���е�����.
	 */
	public void deleteRow(int pRowIndex) {
		this.setCurRow(pRowIndex);
		this.deleteRow();
	}

	/**
	 *����ResultSet����Ľṹ�����ݣ���䵽RecordSet������
	 *@param pRs - �����Ƶ�ResultSet����
	 */
	public void fillByResultSet(ResultSet pRs) {

		ResultSetDataProvider rsdp = new ResultSetDataProvider(pRs);
		rsdp.loadAndWait(dataTable);
	}

	/**
	 * ִ��ָ����SQL��ѯ��䣬����ִ�к�Ľ������䵽ResultSet�����С��˷�����֧�ַ��ض��������Ĳ�ѯ��䡣
	 * @param pSQL - ָ���Ĳ�ѯ���
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
	 * �ƶ�RecordSet�ĵ�ǰ��ָ�뵽��һ��
	 */
	public void first() {
		this.curRow = 1;
	}

	/**
	 *��ResultSet�����е�������XML�ַ�����ʽ����
	 */
	public String getXml() {
		String xmldoc = dataSet.writeXml();
		//ת���ļ������ʽΪGBK
		xmldoc = "<?xml version=\"1.0\" encoding=\"GBK\" ?>"  + xmldoc.substring(22);
	    return xmldoc; 
	}

	/**
	 * �ƶ�RecordSet�ĵ�ǰ��ָ�뵽���һ��
	 */
	public void last() {
		this.curRow = dataTable.getRowCount();
	}

	/**
	 * ��ָ�����ļ��л�ȡ���ݣ�����䵱ǰRecordSet����
	 * ִ�и÷���ǰ��Ҫ��������RecordSet���У�ʹ����ָ���ļ��е����ݼ��ṹ����һ�£�������׳�һ������
	 * @param pFileName - ָ���ļ�����·������
	 */
	public void loadFromFile(String pFileName)  throws Exception{
      
		java.io.FileInputStream fis = new java.io.FileInputStream(pFileName);
		dataSet.readXml(fis);
	}

	/**
	 * �ƶ�RecordSet�ĵ�ǰ��ָ�뵽��һ�С�
	 */
	public void next() {
		if (this.isEof()) {
			throw new IllegalArgumentException("Error of CDataTable:Eof");
		} else {
			this.setCurRow(this.getCurRow() + 1);
		}
	}

	/**
	 * �ƶ�RecordSet�ĵ�ǰ��ָ�뵽��һ�С�
	 */
	public void previous() {
		if (this.isBof()) {
			throw new IllegalArgumentException("Error of RecordSet:Bof");
		} else {
			this.setCurRow(this.getCurRow() - 1);
		}
	}

	/**
	 * ��ȡ�洢��XML�ַ����еļ�¼���ṹ�����ݣ�����䵱ǰRecordSet����
	 * @param pXmlDoc - �洢��¼���ṹ�����ݵ�XML�ַ�����
	 */
	public void readXml(String pXmlDoc) throws Exception{
		dataSet.readXml(pXmlDoc);
	}

	/**
	 * ˢ��ResultSet;
	 */
	public void refresh() {
		dataTable.refreshAndWait();
	}

	/**
	 * ����RecordSet��¼���Ľṹ�����ݣ��洢��ָ���Ĵ����ļ���
	 * @param pFileName - ָ���Ĵ����ļ�����
	 */
	public void saveToFile(String pFileName)  throws Exception{
        
        java.io.FileOutputStream file = new java.io.FileOutputStream(pFileName); 
        java.io.OutputStreamWriter osw = new java.io.OutputStreamWriter(file,"GBK");        
        java.io.BufferedWriter bw = new java.io.BufferedWriter(osw);
        bw.write(this.getXml());
        bw.close();        
	}

	/** 
	 * ָʾ��ǰ��ָ���Ƿ�λ�ڵ�һ��֮ǰ
	 */
	public boolean isEof() {
		return eof;
	}

	/** 
	 * ָʾ��ǰ��ָ���Ƿ�λ�����һ��֮��
	 */
	public boolean isBof() {
		return bof;
	}

	/** 
	 * ����RecordSet��¼�����еĸ���
	 */
	public int getRowCount() {
		return dataTable.getRowCount();
	}

	/** 
	 *  ����RecordSet��¼�����еĸ�����
	 */
	public int getColCount() {
		return dataTable.getColumns().size();
	}

	/** 
	 *  ���ص�ǰ��ָ���λ��
	 */
	public int getCurRow() {

		return curRow;
	}

	/** 
	 *  ���õ�ǰ��ָ���λ��
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
	 *  ����RecordSet�ı�����
	 */
	public String getTableName() {
		return tableName;
	}

	
	/** 
	 *  ����RecordSet�ı�����
	 */
	public void setTableName(String pTableName) {
		dataTable.setName(pTableName);
		tableName = pTableName;
	}
	
	
	/** 
	 *  ����ResultSet����ǰ��ָ���е�ֵ����JAVA��������Ϊһ��java.math.BigDecimal����;
	 *  @param pColumnIndex - ��һ����1, ��һ����2, ...    
	 */
	public BigDecimal getBigDecimal(int pColumnIndex) {
		Object colValue = this.getObject(pColumnIndex);
		return new BigDecimal((String) colValue);
	}

	/** 
	 *  ����ResultSet����ǰ��ָ���е�ֵ����JAVA��������Ϊһ��java.math.BigDecimal����;
	 *  @param pColumnName - ָ���е�����   
	 */
	public BigDecimal getBigDecimal(String pColumnName) {
		Object colValue = this.getObject(pColumnName);
		return new BigDecimal((String) colValue);
	}

	/** 
	 *  ����ResultSet����ǰ��ָ���е�ֵ����JAVA��������Ϊһ��boolean���͵���ֵ;
	 *  @param pColumnIndex - ��һ����1, ��һ����2, ...  
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
	 *  ����ResultSet����ǰ��ָ���е�ֵ����JAVA��������Ϊһ��boolean���͵���ֵ;
	 *  @param pColumnName - ָ���е�����   
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
	 *  ����ResultSet����ǰ��ָ���е�ֵ����JAVA��������Ϊһ��double���͵���ֵ;
	 *  @param pColumnIndex - ��һ����1, ��һ����2, ...  
	 */
	public double getDouble(int pColumnIndex) {
		return Double.parseDouble(this.getObject(pColumnIndex).toString());
	}

	/** 
	 *  ����ResultSet����ǰ��ָ���е�ֵ����JAVA��������Ϊһ��double���͵���ֵ;
	 *  @param pColumnName - ָ���е�����   
	 */
	public double getDouble(String pColumnName) {
		return Double.parseDouble(this.getObject(pColumnName).toString());
	}

	/** 
	 *  ����ResultSet����ǰ��ָ���е�ֵ����JAVA��������Ϊһ��float���͵���ֵ;
	 *  @param pColumnIndex - ��һ����1, ��һ����2, ...  
	 */
	public float getFloat(int pColumnIndex) {
		return Float.parseFloat(this.getObject(pColumnIndex).toString());
	}

	/** 
	 *  ����ResultSet����ǰ��ָ���е�ֵ����JAVA��������Ϊһ��float���͵���ֵ;
	 *  @param pColumnName - ָ���е�����   
	 */
	public float getFloat(String pColumnName) {
		return Float.parseFloat(this.getObject(pColumnName).toString());
	}

	/** 
	 *  ����ResultSet����ǰ��ָ���е�ֵ����JAVA��������Ϊһ��int���͵���ֵ;
	 *  @param pColumnIndex - ��һ����1, ��һ����2, ...  
	 */
	public int getInt(int pColumnIndex) {
		return Integer.parseInt(this.getObject(pColumnIndex).toString());
	}

	/** 
	 *  ����ResultSet����ǰ��ָ���е�ֵ����JAVA��������Ϊһ��int���͵���ֵ;
	 *  @param pColumnName - ָ���е�����   
	 */
	public int getInt(String pColumnName) {
		return Integer.parseInt(this.getObject(pColumnName).toString());
	}

	/** 
	 *  ����ResultSet����ǰ��ָ���е�ֵ����JAVA��������Ϊһ��Long���͵���ֵ;
	 *  @param pColumnIndex - ��һ����1, ��һ����2, ...  
	 */
	public long getLong(int pColumnIndex) {
		return Long.parseLong(this.getObject(pColumnIndex).toString());
	}

	/** 
	 *  ����ResultSet��ǰ��ָ���е�ֵ����JAVA��������ΪLong���͵���ֵ;;
	 *  @param pColumnName - ָ���е�����  
	 */
	public long getLong(String pColumnName) {
		return Long.parseLong(this.getObject(pColumnName).toString());
	}

	/** 
	 *  ����ResultSet����ǰ��ָ���е�ֵ����JAVA��������Ϊһ��java.util.Date����;
	 *  @param pColumnIndex - ��һ����1, ��һ����2, ...  
	 */
	public Date getDate(int pColumnIndex) {
		return new Date("this.getObject(pColumnIndex).toString()");

	}

	/** 
	 *  ����ResultSet��ǰ��ָ���е�ֵ����JAVA��������Ϊһ��java.util.Date����;
	 *  @param pColumnName - ָ���е�����  
	 */
	public Date getDate(String pColumnName) {
		return new Date("this.getObject(pColumnName).toString()");
	}

	/** 
	 *  ����ResultSet��ǰ��ָ���е�ֵ����JAVA��������Ϊһ��Object����
	 *  @param pColumnIndex - ��һ����1, ��һ����2, ...  
	 */
	public Object getObject(int pColumnIndex) {
		String colName = dataTable.getColumns().get(pColumnIndex - 1).getName();
		return this.getObject(colName);
	}

	/** 
	 *  ����ResultSet��ǰ��ָ���е�ֵ����JAVA��������Ϊһ��Object����
	 *  @param pColumnName - ָ���е�����  
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
	 *  ΪResultSet�ĵ�ǰ�У�ָ���и�ֵ
	 *  @param pColumnIndex - ��һ����1, ��һ����2, ...
	 *  @param pValue - ֵ    
	 */
	public void setObject(int pColumnIndex, Object pValue) {
		String colName = dataTable.getColumns().get(pColumnIndex - 1).getName();
		dataTable.setValue(this.curRow, colName, pValue);
	}

	/** 
	 *  ΪResultSet�ĵ�ǰ�У�ָ���и�ֵ
	 *   @param pColumnName - ָ���е�����  
	 *  @param pValue - ֵ    
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
