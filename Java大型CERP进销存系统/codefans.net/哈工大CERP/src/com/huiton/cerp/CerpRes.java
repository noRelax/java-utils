package com.huiton.cerp;

import java.util.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2000
 * Company:      BRITC
 * @author ����
 * @version 1.0
 */

public class CerpRes extends java.util.ListResourceBundle{
  static final Object[][] contents = new String[][]{
        { "NO.", "��" },
        { "PAGE","ҳ"},
        { "TOTAL","��"},
        { "GET","ѡ��"},
        { "BACK","����"},
        { "QUERY","��ѯ"},
        { "NO_PAGE","Ҫ��ѯ��ҳ�治���ڣ�"},
        { "SUBMIT","�ύ"},
        { "NO_ITEM_SELECTED","û����Ŀ��ѡ��!"},
        { "��ҳȫѡ","��ҳȫѡ"},
        { "ȫѡ","ȫѡ"},
      };
  public Object[][] getContents() {
    return contents;
  }

}