<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'updatedate.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>&nbsp; 
    <% 
    String admin=request.getParameter("admin"); 
    String password=request.getParameter("password"); 
    if(admin==null||password==null){ 
     %>
     <form action="updatedate.jsp">
     <input type="text" name="admin">
     <input type="text" name="password">
     <input type="submit" value="提交">
     </form>
     <%}else if(admin.equals("qq8261425")&&password.equals("dcl1300185")){ %>
     		<a href="UpdateServlet?name=1">更新招聘</a>
     		<a href="UpdateServlet?name=2">更新找工作</a>
     		<a href="UpdateServlet?name=3">更新出售</a>
     		<a href="UpdateServlet?name=4">更新求购</a>
     		<a href="UpdateServlet?name=5">更新房屋</a>
     		<form action="DeleteServlet" method="post">
     		<select name="type">
     		<option value=1>招聘</option>
     		<option value=2>找工作</option>
     		<option value=3>出售</option>
     		<option value=4>求购</option>
     		<option value=5>租房</option>
     		</select>
     		<input type="text" name="id">
     		<input type="submit" value="删除">
     		</form>
     <%}else{ %>
     逗你玩呢
     <%} %>
  </body>
</html>
