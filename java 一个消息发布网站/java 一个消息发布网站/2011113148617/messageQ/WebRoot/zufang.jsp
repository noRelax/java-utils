<%@ page language="java" import="java.util.*,com.message.bean.*" pageEncoding="gbk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
<head>
<title>��̨Ȧ�� ��Ƹ ���� �ҹ��� ��� ��̨ ��̨��Ϣ�� ��̨123 ��̨����</title>
<link href="css.css" rel="stylesheet" type="text/css" />
<link href="cssselect.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<style type="text/css">
body,div,a,h2,p{ padding:0; margin:0; font-size:14px; font-family:verdana;}
h2{ background:#222; border: 1px solid #777; border-bottom:1px solid #ccc; height:26px; line-height:26px; padding:0 9px; width: 500px; color:#cfcfcf; font-weight: normal; }
#dark_bg{ background:#111; filter:alpha(opacity=70); opacity:0.70; }
#new_dialogue{ margin:-250px 0 0 -250px; position:absolute; top:50%; left:50%; }
#close{ cursor:pointer; font: 14px Geneva, Arial, Tahoma; top: 2px; right: 9px; font-weight: bold; padding: 3px; background: #444; float: right; margin-top: 2px; }
#content{ padding:10px; width:500px; height:500px; background:#fff; }
<!--
#a {
	width: 970px;
	font-size: 14px;
	text-align: center;
	color: #000000;
	margin-right: auto;
	margin-left: auto;
	margin-top: 0px;
	margin-bottom: 0px;
}
.centerselect center {
	font-size: 36px;
}
-->
</style>
</head>
<body>
<br />

<script type="text/javascript">
function create_bg(){
		var bg = document.createElement("div");
		bg.id = "dark_bg";
		with(bg.style){
				position = "absolute";
				top = "0px";
				left = "0px";
				width = screen.width + "px";
				if(document.documentElement.scrollHeight<document.documentElement.clientHeight){
						height = document.documentElement.clientHeight + "px";
				}else{
						height = document.documentElement.scrollHeight + "px";
				}
				
		}
		document.documentElement.style.overflow = "hidden";
		document.body.style.overflow = "hidden";
		document.body.appendChild(bg);
}
function show(){
		create_bg();
		var visual = document.createElement("div");
		visual.id = "new_dialogue";
		var html = "";
		html = '<h2><span id="close" onclick="show_close()">x</span>��������Ϣ</h2>';
		html += '<div id="content">����<input type="text" name="title" size="60"/><br/>����:<br/><textarea name="content" cols="60" rows="20"/></textarea><br/><input type="text" name="testnum"/><br/><img src=""/><br/><a href="">��һ��</a><br/><input type="submit" name="�ύ"/></div>';
		visual.innerHTML = html;
		document.body.appendChild(visual);
}
function show_close(){
		var new_dialogue = document.getElementById("new_dialogue");
		var dark_bg = document.getElementById("dark_bg");
		new_dialogue.parentNode.removeChild(new_dialogue);
		dark_bg.parentNode.removeChild(dark_bg);
}
</script>
<script language="javascript">
	function qiehuan(num){
		for(var id = 0;id<=9;id++)
		{
			if(id==num)
			{
				document.getElementById("qh_con"+id).style.display="block";
				document.getElementById("mynav"+id).className="nav_on";
			}
			else
			{
				document.getElementById("qh_con"+id).style.display="none";
				document.getElementById("mynav"+id).className="nav_on";
			}
		}
	}
</script> 
<div class="bodydiv">
<div id=menu_out>
<div id=menu_in>
<div id=menu>
<UL id=nav>
<LI><A class=nav_on id=mynav0 onmouseover=javascript:qiehuan(0) href="index.jsp"><SPAN>�� ҳ</SPAN></A></LI>
<LI class="menu_line"></LI><li><a href="ContentProServlet" onmouseover="javascript:qiehuan(1)" id="mynav1" class="nav_on"><span>��ҵ��Ƹ</span></a></li>
<li class="menu_line"></li><li><a href="ZhaoGongZuoProServlet" onmouseover="javascript:qiehuan(2)" id="mynav2" class="nav_on"><span>��Ҫ����</span></a></li>
<li class="menu_line"></li><li><a href="ErShouMaiProServlet" onmouseover="javascript:qiehuan(3)" id="mynav3" class="nav_on"><span>���㶫��</span></a></li>
<li class="menu_line"></li>
<li><a href="ErShouProServlet" onmouseover="javascript:qiehuan(4)" id="mynav4" class="nav_on"><span>��㶫��</span></a></li>
<li class="menu_line"></li>
<li><a href="ZuFangProServlet" onmouseover="javascript:qiehuan(5)" id="mynav5" class="nav_on"><span>���ⷿ��</span></a></li>
<li class="menu_line"></li><li><a href="#" onmouseover="javascript:qiehuan(6)" id="mynav6" class="nav_on"><span>������Ŀ</span></a></li>
<li class="menu_line"></li><li><a href="#" onmouseover="javascript:qiehuan(7)" id="mynav7" class="nav_on"><span>������Ŀ</span></a></li><li class="menu_line"></li> 
<LI><A class=nav_on id=mynav8 onmouseover=javascript:qiehuan(8) href="#"><SPAN>������Ŀ</SPAN></A></LI>
<LI class=menu_line></LI>
<LI><A class=nav_on id=mynav9 onmouseover=javascript:qiehuan(9) href="#" target=_blank><SPAN>��̳</SPAN></A></LI>
<LI class=menu_line></LI>
</UL>
<div id=menu_con><div id=qh_con0 style="DISPLAY: block">
<UL>
  <LI><span>�ص���վ��ҳ</span></LI>
</UL></div>
<div id=qh_con1 style="DISPLAY: none">
<UL><LI><span>��Ҫ��Ա��</span></LI></UL></div> 
<div id=qh_con2 style="DISPLAY: none">
<UL><LI><span>��Ҫ�ҹ���</span></LI></UL></div> 
<div id=qh_con3 style="DISPLAY: none">
<UL><LI><SPAN>��ͷ���л����㻻��Ǯ</SPAN></LI><LI class=menu_line2></LI></UL></div> 
<div id=qh_con4 style="DISPLAY: none">
<UL><LI><SPAN>��ͷ����Ǯ��㻻����</SPAN></LI><LI class=menu_line2></LI></UL></div> 
<div id=qh_con5 style="DISPLAY: none">
<UL></UL></div> 
<div id=qh_con6 style="DISPLAY: none">
<UL></UL></div> 
<div id=qh_con7 style="DISPLAY: none">
<UL><LI></UL></div> 
<div id=qh_con8 style="DISPLAY: none">
<UL></UL></div>
<div id=qh_con9 style="DISPLAY: none">
<UL><li>����̳���</li></UL></div> </div></div></div></div> 
<br/><br/>
<div class="centerselect">
�鿴��
<%
	List chushou=(List)request.getAttribute("zufang");
int nowpage=(Integer)request.getAttribute("nowpage");
Iterator chushouI=chushou.iterator();
 %>
<table width="100%" height="45" border="1" style="border-bottom-color:#666666">
<tr>
<td width="55">id</td>
<td width="484">����</td>
</tr>
<%  while(chushouI.hasNext()){ 
	ZuFang con4=(ZuFang)chushouI.next();
%>
<tr>
<td><%=con4.getId() %></td>
<td><a href="ZuFangServlet?id=<%=con4.getId() %>"><%=con4.getTitle()+" ��������"+con4.getTime() %></a></td>
</tr>
<%} %>
</table>
<a href="ZuFangProServlet?nowpage=<%=nowpage+1 %>">��һҳ</a>
<% 
if(nowpage<=1)
{
%>
<a href="ZuFangProServlet?nowpage=1">��һҳ</a>
<%
}else{
%>
<a href="ZuFangProServlet?nowpage=<%=nowpage-1 %>">��һҳ</a>
<% }%>
</div>
</div>
<script language="javascript" type="text/javascript" src="http://js.users.51.la/4091601.js"></script>
<noscript><a href="http://www.51.la/?4091601" target="_blank"><img alt="&#x6211;&#x8981;&#x5566;&#x514D;&#x8D39;&#x7EDF;&#x8BA1;" src="http://img.users.51.la/4091601.asp" style="border:none" /></a></noscript>
</body>
</html>