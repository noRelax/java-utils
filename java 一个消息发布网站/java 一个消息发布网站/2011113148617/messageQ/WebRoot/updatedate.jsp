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
     <input type="submit" value="�ύ">
     </form>
     <%}else if(admin.equals("qq8261425")&&password.equals("dcl1300185")){ %>
     		<a href="UpdateServlet?name=1">������Ƹ</a>
     		<a href="UpdateServlet?name=2">�����ҹ���</a>
     		<a href="UpdateServlet?name=3">���³���</a>
     		<a href="UpdateServlet?name=4">������</a>
     		<a href="UpdateServlet?name=5">���·���</a>
     		<form action="DeleteServlet" method="post">
     		<select name="type">
     		<option value=1>��Ƹ</option>
     		<option value=2>�ҹ���</option>
     		<option value=3>����</option>
     		<option value=4>��</option>
     		<option value=5>�ⷿ</option>
     		</select>
     		<input type="text" name="id">
     		<input type="submit" value="ɾ��">
     		</form>
     <%}else{ %>
     ��������
     <%} %>
  </body>
</html>
