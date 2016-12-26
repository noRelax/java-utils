package com.huiton.cerp.pub.taglib;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.util.ResourceBundle;
import java.util.Locale;
import javax.servlet.ServletRequest;

import com.huiton.cerp.pub.util.WebKeys;
import com.huiton.mainframe.util.tracer.Debug;
/**
 * Title:        导航条标签
 * Description:
 * Copyright:    Copyright (c) 2000
 * Company:      BRITC
 * @author 王涛
 * @version 1.0
 */

public class FootNavTag extends TagSupport {
  boolean isFirst = true;    //第一次插入时要插入脚本文件
  String align = "left";
  String width = "100%";            //缺省的宽度
  String submitForm = "FormQuery";    //缺省提交的表名
  String textCSS = "" ;
  JspWriter out;
  ResourceBundle res;
  ServletRequest request;
  int currentPage;      //当前页，应位于request中
  int pageCount;        //当前页数，应位于request中

  public FootNavTag() {
  }


  //嵌入导航条
  public int doStartTag() throws javax.servlet.jsp.JspException {
    /**@todo: Override this javax.servlet.jsp.tagext.TagSupport method*/
  try {
    //绑定语言资源
    request = pageContext.getRequest();
    if (request == null)
      Debug.println("Null request...");
    String lang = (String)request.getAttribute("lang");
    if (lang == null)
      lang = "zh";

    res = ResourceBundle.getBundle("com.huiton.pub.lan_tools.PublicRes",new Locale(lang,""));
    //res = ResourceBundle.getBundle("com.huiton.cerp.login.LoginRes",new Locale(lang,""));

    // 当前页码，翻页时使用
    String tmpCurPage = (String)request.getAttribute(WebKeys.CurrentPageKey);
    tmpCurPage = (tmpCurPage==null)||(tmpCurPage.equals("")) ? "1":tmpCurPage;
    try
    {
        currentPage = Integer.parseInt(tmpCurPage);
    }
    catch(Exception e)
    {
        currentPage = 1;
    }

    // 页数
    String tmpPageCount = (String)request.getAttribute(WebKeys.PageCountKey);
    tmpPageCount = (tmpPageCount==null)||(tmpPageCount.equals("")) ? "1":tmpPageCount;
    try
    {
        pageCount = Integer.parseInt(tmpPageCount);
    }
    catch(Exception e)
    {
        pageCount = 1;
    }

    if (currentPage > pageCount)
      currentPage = pageCount;

    out = pageContext.getOut();
    out.println("<table width='" + width + "' border='0' cellspacing='0' cellpadding='0' align='center' height='30'>");
    out.println("<form><tr>");
    out.print(" <td width='" + width + "' align='" + align + "'> ");
    out.print("<a href='javascript: firstPage()'><img src='/com/huiton/cerp/pub/images/first.gif' width='20' border='0' height='15'></a>");
    out.print("<a href='javascript: prevPage()'><img src='/com/huiton/cerp/pub/images/first1.gif' width='20' border='0' height='15'></a>");
    out.print(" <input class='" + textCSS + "' size='3' value='" + currentPage + "' onkeypress='return locate(this);'> ");
    out.print("<a href='javascript: nextPage()'><img src='/com/huiton/cerp/pub/images/last1.gif' width='20' border='0' height='15'></a>");
    out.println("<a href='javascript: lastPage()'><img src='/com/huiton/cerp/pub/images/last.gif' width='20' border='0' height='15'></a>&nbsp;");

    if (lang.equalsIgnoreCase("zh"))  {
      out.print(" "+res.getString("20100")+currentPage+res.getString("20101"));
      out.println("/"+res.getString("20102")+pageCount+res.getString("20101"));
    }
    else  {
      //英文
      out.print(" "+res.getString("20101")+":<u>"+currentPage+"</u>");
      out.println("/"+res.getString("20102")+" "+res.getString("20101")+":<u>"+pageCount+"</u>");
    }

    //out.println("   "+res.getString("20102")+pageCount+res.getString("20101"));
    out.println(" </td>");
    Debug.println("end of start");
  }catch (Exception e)  {
    System.out.println("FootNavTag Tag error:" + e);
  }

    return EVAL_BODY_INCLUDE;
  }

  //结束时完成表单的结构
  public int doEndTag() throws javax.servlet.jsp.JspException {
    /**@todo: Override this javax.servlet.jsp.tagext.TagSupport method*/
  try {
    out.println("</tr></form>");
    out.println("</table>");
    if (isFirst)
      genScript();

    Debug.println("end of end");
  }catch (Exception e)  {
    System.out.println("FootNavTag Tag error:" + e);
  }

    return EVAL_PAGE;
  }

  /**
   * 生成脚本代码
   */
  void genScript()  {
  try {
    out.println("<!-- 导航条事件 -->");
    out.println("<Script language=javascript>");

    out.println("function nextPage() {");
    if (currentPage >= pageCount)
    {
        out.println("  return;");
    }
    else
    {
        out.println(" document."+ submitForm + ".currentPage.value=" + (currentPage+1) + ";");
        out.println(" document."+ submitForm + ".submit();");
    }
    out.println("}");

    out.println("function prevPage() {");
    if (currentPage <= 1) {
        out.println("  return;");
    }
    else
    {
        out.println(" document."+ submitForm + ".currentPage.value="+(currentPage-1)+ ";");
        out.println("  document."+ submitForm + ".submit();");
    }
    out.println("}");

    out.println("function firstPage() {");
    if (currentPage <= 1)
        out.println("  return;");
    else
    {
        out.println("  document."+ submitForm + ".currentPage.value=1;");
        out.println("  document."+ submitForm + ".submit();");
    }
    out.println("}");

    out.println("function lastPage() {");
    if (currentPage >= pageCount) {
        out.println("  return;");
    }
    else
    {
        out.println("  document."+ submitForm + ".currentPage.value=" + pageCount+ ";");
        out.println("  document."+ submitForm + ".submit();");
    }
    out.println("}");

    out.println("//直接定位时调用");
    out.println("function locate(obj) ");
    out.println("{");
    out.println("  var tmpChar = window.event.keyCode;");
    out.println("  if (tmpChar == 13) ");
    out.println("  {");
    out.println("    var val = parseInt(obj.value);");
    out.println("    if (val>" + pageCount + " || val<1 || isNaN(val))  ");
    out.println("    {");
    out.println("      alert(\"" + res.getString("20106") + ": \" + obj.value);");
    out.println("      obj.value=" + currentPage + ";");
    out.println("      return false;");
    out.println("    }");
    out.println("    document."+ submitForm + ".currentPage.value= val;");
    out.println("    document."+ submitForm + ".submit();");
    out.println("    return false;");
    out.println("  }");
    out.println("  else");
    out.println("  {");
    out.println("     if (tmpChar>47 && tmpChar<58) ");
    out.println("     {  ");
    out.println("         return true;");
    out.println("     }  ");
    out.println("     return false; ");
    out.println("  }  ");
    out.println("}  ");
    out.println("</script>");
}catch (Exception e)  {
    Debug.println("生成脚本错误：" + e);
}
  }

  public void setIsFirst(String isFirst) {

    this.isFirst = isFirst.equalsIgnoreCase("true");
    Debug.println("isFirst: " + isFirst);
  }

  public void setSubmitForm(String submitForm) {
    this.submitForm = submitForm;
  }

  public void setWidth(String width)
  {
    width = (width==null ? "" : width.trim());
    if (width.length()>1)
        this.width = width;
  }

  public void setAlign(String align)
  {
    align = (align==null ? "" : align.trim());
    if (align.length()>1)
        this.align = align;
  }

  public void setTextCSS(String textCSS)
  {
    textCSS = (textCSS==null ? "" : textCSS.trim());
    if (textCSS.length()>1)
        this.textCSS = textCSS;
  }
}