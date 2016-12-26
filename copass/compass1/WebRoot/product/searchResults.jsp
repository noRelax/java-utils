<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.List"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%

if(((List)request.getAttribute("searchresults")).size()==0){
	%>
	no results found.
<%} %>

<table border="1">
<s:iterator value="#request.searchresults">
<tr><td>
<s:property value="name"/>
</td>
<td>

<s:property value="price"/></td>
<td>

<s:property value="brand"/></td>
<td>

<s:property value="description"/></td>
</tr>
</s:iterator>
</table>

</body>
</html>