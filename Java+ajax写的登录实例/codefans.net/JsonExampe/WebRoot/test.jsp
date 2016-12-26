<%@ page language="java" import="java.util.*,org.json.*" pageEncoding="utf-8"%>
    <%
    	String result="";
    	//JSONArray _array = new JSONArray();
    	
    	JSONObject _user = new JSONObject();
		_user.append("name","张三1");
		_user.append("gender","男");
		_user.append("age","18");
		//_array.put(_user);
		
		//JSONObject _users = new JSONObject();
		//out.print(_users.put(_array));
		//result = new JSONObject().put("user",_user).toString();
		out.print(_user);
		System.out.println(_user);
     %>
