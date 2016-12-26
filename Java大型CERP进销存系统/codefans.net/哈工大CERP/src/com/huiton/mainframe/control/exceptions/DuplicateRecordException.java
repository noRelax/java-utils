package com.huiton.mainframe.control.exceptions;

/**
 * Title:        CERP测试框架
 * Description:
 * Copyright:    Copyright (c) 2000
 * Company:      BRITC
 * @author 吴剑
 * @version 1.0
 */

public class DuplicateRecordException extends Exception {
    public DuplicateRecordException() {
        super("此记录已经存在！");
    }
}