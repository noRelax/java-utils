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

public class PublicRes extends ListResourceBundle {
  static final Object[][] contents = new String[][]{
        {"20220","删除"},
        {
            "10011", "新增失败!"
        }, {
            "10012", "修改失败!"
        }, {
            "10013", "删除失败!"
        }, {
            "10021", "确认新增吗?"
        }, {
            "10022", "确认修改吗?"
        }, {
            "10023", "确认删除吗?"
        }, {
            "10031", "放弃新增吗?"
        }, {
            "10032", "放弃修改吗?"
        }, {
            "10033", "放弃删除吗?"
        }, {
            "10041", "该记录已经存在!"
        }, {
            "10042", "符合条件的数据没有找到!"
        }, {
            "10043", "该条记录已经使用，不能删除!"
        }, {
            "20010", "带*的符号必须输入!"
        }, {
            "20020", "请输入正确的日期!"
        }, {
            "20030", "请输入符合条件的数据!"
        }, {
            "20040", "请输入正确的查询条件!"
        }, {
            "20050", "请输入数字!"
        }, {
            "20060", "请输入字母A-Z!"
        }, {
            "20100", "第"
        }, {
            "20101","页"
        }, {
            "20102","共"
        }, {
            "20103","选用"
        }, {
            "20104","返回"
        }, {
            "20105","查询"
        }, {
            "20106","要查询的页面不存在!"
        }, {
            "20107","提交"
        }, {
            "20108","没有项目被选中!"
        }, {
            "20110","下一页"
        }, {
            "20111","上一页"
        }, {
            "20112","首页"
        }, {
            "20113","尾页"
        }, {
            "20114","保存"
        }, {
            "20115","新增"
        }, {
            "20116","删除"
        }, {
            "20117","修改"
         }, {
            "20118","查询"
         }, {
            "20119","重置"
        },

         //pubDept.jsp & pubPosition.jsp
        {"position_code","职位代码"},
        {"position_name","职位名称"},
        {"dept_code","部门代码"},
        {"dept_name","部门名称"},
        {"choose_dept","选择部门"},
        {"choose_position","选择职位"},
        {"select","选择"},
       //通用操作键
       {
            "新增失败", "新增失败!"
        }, {
            "修改失败", "修改失败!"
        }, {
            "删除失败", "删除失败!"
        }, {
            "确认新增", "确认新增吗?"
        }, {
            "确认修改", "确认修改吗?"
        }, {
            "确认删除", "确认删除吗?"
        }, {
            "放弃新增", "放弃新增吗?"
        }, {
            "放弃修改", "放弃修改吗?"
        }, {
            "放弃删除", "放弃删除吗?"
        }, {
            "记录存在", "该记录已经存在!"
        }, {
            "无数据", "符合条件的数据没有找到!"
        }, {
            "不能删除", "该条记录已经使用，不能删除!"
        }, {
            "必须输入", "带*的符号必须输入!"
        }, {
            "正确日期", "请输入正确的日期!"
        }, {
            "符合数据", "请输入符合条件的数据!"
        }, {
            "正确条件", "请输入正确的查询条件!"
        }, {
            "输入数字", "请输入数字!"
        }, {
            "输入字符", "请输入字母A-Z!"
        }, {
            "第", "第"
        }, {
            "页","页"
        }, {
           "共","共"
        }, {
            "提交","提交"
        }, {
            "返回","返回"
        }, {
            "查询","查询"
        }, {
            "没有该页","没有该页!"
        }, {
            "无选中项目","无选中项目!"
        }, {
            "选用","选用"
        }, {
            "下一页","下一页"
        }, {
            "上一页","上一页"
        }, {
            "首页","首页"
        }, {
            "尾页","尾页"
        }, {
            "保存","保存"
        }, {
            "新增","新增"
        }, {
            "删除","删除"
        }, {
            "修改","修改"
         }, {
            "重置","重置"
        },
        {"保存返回","保存返回"},
        {"保存继续","保存继续"},
        {"增加","增加"},
        {"选择","选择"},
        {"修改确认","修改确认"},
        //公用js文件所用
        {"note1","输入的字符超过允许输入的最大长度"},
        {"<1","输入的数值应该小于1!"},
        //country.jsp
        {"country_code","国家代码"},
        {"country_name","国家名称"},
        {"selCountry","选择国家"},
        {"city_code","城市代码"},
        {"city_name","城市名称"},
        {"selCity","选择城市"},
        {"province_code","省份代码"},
        {"province_name","省份名称"},
        {"selPro","选择省份"},
        {"code","代码"},
        {"name","名称"},
        {"selAll","选择国家、省份、城市"},
        //saleRegion.jsp
        {"region_code","销售地区代码"},
        {"region_name","销售地区名称"},
        {"sel_region","选择销售地区"},
        {"",""},
        {"",""},
        }
        ;

  public PublicRes() {
  }
  protected Object[][] getContents() {
    /**@todo: implement this java.util.ListResourceBundle abstract method*/
    return contents;
  }

}