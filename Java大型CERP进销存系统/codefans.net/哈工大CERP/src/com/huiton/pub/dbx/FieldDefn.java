package com.huiton.pub.dbx;

import java.io.Serializable;

/**
 * Title: ������
 * Description: ���ඨ�����ֶα��ĳ���������ȡ�����Ϣʱʹ�ñ���
 * Copyright:    Copyright (c) 2001
 * Company: huiton
 * @author ����
 * @version 1.0
 */
public class FieldDefn implements Serializable  {
  /**
   * @const: colName �ֶ���
   * @const: colType  �ֶ�����
   * @const: colDft �ֶε�ȱʡֵ
   * @const: nullable �Ƿ�����Ϊ��
   * @const: isPriKey �Ƿ�Ϊ����
   * @const: colLen �еĳ���
   */
  public String colName = "";

  public String colName_cn = "";    //���ı���
  public String colName_en = "";    //Ӣ�ı���
  public String colName_tw = "";    //�������

  public int colType;
  public String colDft = "";
  public boolean nullable = false;
  public boolean isPriKey = false;
  public int colLen = 0;
  public String colWidth = "";
  public Boolean isFix;     //�̶���ʾ�ֶ�

  public FieldDefn () {
  }
}