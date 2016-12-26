<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="style/oa.css" rel="stylesheet" type="text/css">

<title>添加信息</title>
</head>
<body>
<center>
	<s:form action="insert.action" theme="simple">
	
<TABLE class="tableEdit" border="0" cellspacing="1" cellpadding="0" style="width:300px;">
	<TBODY>
		<TR>
	<td align="center" class="tdEditTitle">添加商品名称</TD>
		</TR>
		<TR>
			<td>	


<table class="tableEdit" style="width:300px;" cellspacing="0" border="0" cellpadding="0">
	<tr>	
	<td class="tdEditLabel" >商品名称</td>				
		<td class="tdEditContent"><s:textfield name="product.name" label="名称" /></td>
	</tr>
	
	<tr>	
	<td class="tdEditLabel" >商品品牌</td>				
		<td class="tdEditContent"><s:textfield name="product.brand" label="品牌" /></td>
	</tr>
	
	<tr>	
	<td class="tdEditLabel" >商品价格</td>				
		<td class="tdEditContent"><s:textfield name="product.price" label="价格" /></td>
	</tr>
	
	<tr>
				<td class="tdEditLabel" >商品描述</td>	
		<td class="tdEditContent"><s:textarea name="product.description" label="描述" />
		</td>
	</tr>
	<tr>
		<td>&nbsp;
		</td>
		<td><s:submit/>
		<br></td>
	</tr>
</table>
			</td>
		</TR>
	</TBODY>
</TABLE>
</s:form>
</center>
</body>
</html>