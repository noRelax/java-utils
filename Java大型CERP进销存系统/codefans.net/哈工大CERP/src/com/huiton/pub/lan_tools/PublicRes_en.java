package com.huiton.pub.lan_tools;

import java.util.*;


/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class PublicRes_en extends ListResourceBundle {
  static final Object[][] contents = new String[][]{
        {"20220","Delete"},
        {
            "10011", "Insert failure!"
        }, {
            "10012", "Update failure!"
        }, {
            "10013", "Delete failure!"
        }, {
            "10021", "Do you confirm insert? "
        }, {
            "10022", "Do you confirm update?"
        }, {
            "10023", "Do you confirm delete?"
        }, {
            "10031", "Do you give up insert? "
        }, {
            "10032", "Do you give up update?"
        }, {
            "10033", "Do you give up delete?"
        }, {
            "10041", "This record is exist!"
        }, {
            "10042", "Correspondence data is not found!"
        }, {
            "10043", "The record is used,Can not delete!"
        }, {
            "20010", "The field signed * must be entered"
        }, {
            "20020", "Please enter correct date!"
        }, {
            "20030", "Please enter the specific record!"
        }, {
            "20040", "Please enter the query condition!"
        }, {
            "20050", "Please enter the number symbol!"
        }, {
            "20060", "Please enter the letter from A to Z!"
        }, {
            "20100.", "No."
        }, {
            "20101","Page"
        }, {
           "20102","Total"
        }, {
            "20107","Receive"
        }, {
            "20104","Back"
        }, {
            "20105","Query"
        }, {
            "20106","The page that found is not exist："
        }, {
            "20108","No item selected!"
        }, {
            "20103","Selected"
        }, {
            "20110","Next Page"
        }, {
            "20111","Previous Page"
        }, {
            "20112","First Page"
        }, {
            "20113","Last Page"
        }, {
            "20114","save"
        }, {
            "20115","addnew"
        }, {
            "20116","delete"
        }, {
            "20117","update"
         }, {
            "20118","query"
         }, {
            "20119","reset"
        },
        //pubDept.jsp & pubPosition.jsp
        {"position_code","Position Code"},
        {"position_name","Position Name"},
        {"dept_code","Dept Code"},
        {"dept_name","Dept Name"},
        {"choose_dept","Select Deparment"},
        {"choose_position","Select Position"},
        {"select","Select"},
        //通用操作键
       {
            "新增失败", "Insert failure!"
        }, {
            "修改失败", "Update failure!"
        }, {
            "删除失败", "Delete failure!"
        }, {
            "确认新增", "Do you confirm insert? "
        }, {
            "确认修改", "Do you confirm update?"
        }, {
            "确认删除", "Do you confirm delete?"
        }, {
            "放弃新增", "Do you give up insert? "
        }, {
            "放弃修改", "Do you give up update?"
        }, {
            "放弃删除", "Do you give up delete?"
        }, {
            "记录存在", "This record has existed!"
        }, {
            "无数据", "Correspondence data is not found!"
        }, {
            "不能删除", "The record is used,Can not delete!"
        }, {
            "必须输入", "The field signed * must be entered"
        }, {
            "正确日期", "Please enter correct date!"
        }, {
            "符合数据", "Please enter the specific record!"
        }, {
            "正确条件", "Please enter the query condition!"
        }, {
            "输入数字", "Please enter the number symbol!"
        }, {
            "输入字符", "Please enter the letter from A to Z!"
        }, {
            "第", "No."
        }, {
            "页","Page"
        }, {
           "共","Total"
        }, {
            "提交","Submit"
        }, {
            "返回","Back"
        }, {
            "查询","Query"
        }, {
            "没有该页","The page that found is not exist!"
        }, {
            "无选中项目","No item selected!"
        }, {
            "选用","Selected"
        }, {
            "下一页","Next Page"
        }, {
            "上一页","Previous Page"
        }, {
            "首页","First Page"
        }, {
            "尾页","Last Page"
        }, {
            "保存","save"
        }, {
            "新增","addnew"
        }, {
            "删除","delete"
        }, {
            "修改","update"
         }, {
            "重置","reset"
        },
        {"保存返回","Save&Return"},
        {"保存继续","Save&Continue"},
        {"增加","Add"},
        {"选择","Select"},
        {"修改确认","Confirm Update"},
        //公用js文件所用
        {"note1","The length of input field more than the maxlength!"},
        {"<1","The entered value must less than 1!"},
        //country.jsp
        {"country_code","Country Code"},
        {"country_name","Country Name"},
        {"selCountry","Select Country"},
        {"city_code","City Code"},
        {"city_name","City Name"},
        {"selCity","Select City"},
        {"province_code","Province Code"},
        {"province_name","Province Name"},
        {"selPro","Select Province"},
        {"code","Code"},
        {"name","Name"},
        {"selAll","Select Country-Province-City"},
        //saleRegion.jsp
        {"region_code","Region Code"},
        {"region_name","Region Name"},
        {"sel_region","Select Region"},
        {"",""},
        {"",""},
        {"",""},
        {"",""},
        {"",""},
        }
        ;

  public PublicRes_en() {
  }
  protected Object[][] getContents() {
    /**@todo: implement this java.util.ListResourceBundle abstract method*/
    return contents;
  }

}