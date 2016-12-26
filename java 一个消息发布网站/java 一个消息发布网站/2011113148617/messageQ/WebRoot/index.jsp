<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
<head>
<title>邢台圈子 招聘 二手 找工作 广告 邢台 邢台信息港 邢台123 邢台贴吧</title>
<link href="css.css" rel="stylesheet" type="text/css" />
<link href="cssselect.css" rel="stylesheet" type="text/css" />
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
function myReload(){
	document.getElementById("chineseCheckCode").src=document.getElementById("chineseCheckCode").src+"?nocache="+new Date().getTime();
 }
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
		html = '<h2><span id="close" onclick="show_close()">x</span>发布新消息</h2>';
		html += '<div id="content"><form action="AddMessageServlet" method="post">信息类型:<select name="type"><option value="1">招聘</option><option value="2">找工作</option><option value="3">卖物</option><option value="4">买物</option><option value="5">租房</option></select><br/>标题<input type="text" name="title" size="60"/><br/>内容:<br/><textarea name="content" cols="60" rows="20"/></textarea><br/><input type="text" name="testnum"/><br/><img src="ChineseCheckCode" name="chineseCheckCode" width="166" height="45" border="1" id="chineseCheckCode"></img><a onClick="myReload()">&nbsp;换一个</a><br/><input type="submit" name="提交"/></form></div>';
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
<LI><A class=nav_on id=mynav0 onmouseover=javascript:qiehuan(0) href="index.jsp"><SPAN>首 页</SPAN></A></LI>
<LI class="menu_line"></LI><li><a href="ContentProServlet" onmouseover="javascript:qiehuan(1)" id="mynav1" class="nav_on"><span>企业招聘</span></a></li>
<li class="menu_line"></li><li><a href="ZhaoGongZuoProServlet" onmouseover="javascript:qiehuan(2)" id="mynav2" class="nav_on"><span>我要工作</span></a></li>
<li class="menu_line"></li><li><a href="ErShouMaiProServlet" onmouseover="javascript:qiehuan(3)" id="mynav3" class="nav_on"><span>卖点东西</span></a></li>
<li class="menu_line"></li>
<li><a href="ErShouProServlet" onmouseover="javascript:qiehuan(4)" id="mynav4" class="nav_on"><span>买点东西</span></a></li>
<li class="menu_line"></li>
<li><br /></li>
<li class="menu_line"></li><li><a href="ZuFangProServlet" onmouseover="javascript:qiehuan(5)" id="mynav5" class="nav_on"><span>出租房子</span></a></li>
<li class="menu_line"></li><li><a href="#" onmouseover="javascript:qiehuan(7)" id="mynav7" class="nav_on"><span>待增项目</span></a></li><li class="menu_line"></li> 
<LI><A class=nav_on id=mynav8 onmouseover=javascript:qiehuan(8) href="#"><SPAN>待增项目</SPAN></A></LI>
<LI class=menu_line></LI>
<LI><A class=nav_on id=mynav9 onmouseover=javascript:qiehuan(9) href="#" target=_blank><SPAN>论坛</SPAN></A></LI>
<LI class=menu_line></LI>
</UL>
<div id=menu_con><div id=qh_con0 style="DISPLAY: block">
<UL>
  <LI><span>回到网站首页</span></LI>
</UL></div> 
<div id=qh_con1 style="DISPLAY: none">
<UL><LI><span>我要招员工</span></LI></UL></div> 
<div id=qh_con2 style="DISPLAY: none">
<UL><LI><span>我要找工作</span></LI></UL></div> 
<div id=qh_con3 style="DISPLAY: none">
<UL><LI><SPAN>手头有闲货卖点换点钱</SPAN></LI><LI class=menu_line2></LI></UL></div> 
<div id=qh_con4 style="DISPLAY: none">
<UL><LI><SPAN>手头有闲钱买点换点物</SPAN></LI><LI class=menu_line2></LI></UL></div> 
<div id=qh_con5 style="DISPLAY: none">
<UL></UL></div> 
<div id=qh_con6 style="DISPLAY: none">
<UL></UL></div> 
<div id=qh_con7 style="DISPLAY: none">
<UL><LI></UL></div> 
<div id=qh_con8 style="DISPLAY: none">
<UL></UL></div>
<div id=qh_con9 style="DISPLAY: none">
<UL><li>来论坛逛逛</li></UL></div> </div></div></div></div> 
<br/><br/>
<div class="centerselect">
<center>邢台圈子</center>
<div class="primaryselect">
<form action="SelectServlet" method="post">
<table>
<tr>
<td>邢 台 圈 子
</td>
<td><input type="text" size="25%" name="keyword"/></td>
<td>
<input type="submit" value="查找"/>
</td>
</tr>
</table></form></div>
<div class="baidugoogle">
<div class="baidu">
<form action="http://www.baidu.com/s"><table>
<tr>
<td>
<img src="baidu_logo.gif" width="100" height="45" alt="百度搜索" />
</td>
<td><input type="text" name="wd"/></td>
<td><input type="submit" value="查找"/></td>
</tr>
</table></form></div>
<div class="google">
<form action="http://www.google.com.hk/search">
<table>
<tr>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<img src="logo_cn.png" width="100" height="45" alt="谷歌搜索" />
</td>
<td>
<input type="text" name="q"/>
</td>
<td>
<input type="submit" value="查找"/>
</td>
</tr>
</table>
</form>
</div>
</div>
<div class="bottonsubmit"><a href="#" onclick="show()"><img src="myskin/onebit_20.png"/></a></div>
</div>
<center>冀ICP备10013756号<br/>
版权所有：河北邢台海达化玻总汇<br/>
<script language="javascript" type="text/javascript" src="http://js.users.51.la/4091601.js"></script>
<noscript><a href="http://www.51.la/?4091601" target="_blank"><img alt="&#x6211;&#x8981;&#x5566;&#x514D;&#x8D39;&#x7EDF;&#x8BA1;" src="http://img.users.51.la/4091601.asp" style="border:none" /></a></noscript>
</center>
</div>
 
</body>

</html>