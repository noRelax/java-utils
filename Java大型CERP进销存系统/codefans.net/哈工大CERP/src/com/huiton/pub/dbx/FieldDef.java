package com.huiton.pub.dbx;

import java.io.Serializable;

/**
 * Title: �����࣬���ڴ����±�
 * Description: ���ඨ�����ֶα��ĳ���������JdbOp.createTbl������ʱ�������ñ�������鶨�����ֶ�����
 * Copyright:    Copyright (c) 2001
 * Company: huiton
 * @author ����
 * @version 1.0
 */
public class FieldDef implements Serializable  {
  /**
   * @const: colName �ֶ���
   * @const: colType  �ֶ�����
   * @const: colDft �ֶε�ȱʡֵ
   * @const: nullable �Ƿ�����Ϊ��
   * @const: isPriKey �Ƿ�Ϊ����
   */
  public String colName = "";

  public String colName_cn = "";    //���ı���
  public String colName_en = "";    //Ӣ�ı���
  public String colName_tw = "";    //�������

  public String colType="";
  public String colDft = "";
  public boolean nullable;
  public boolean isPriKey;

  public FieldDef () {
  }
}