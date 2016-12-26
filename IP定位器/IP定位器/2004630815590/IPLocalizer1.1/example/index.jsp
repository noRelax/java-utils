<%@ page import="net.xdevelop.ip.*"%>

<%
	//method 1
	String ip=request.getRemoteAddr();
	String[] addr=IPLocalizer.search(ip);
	
	//or method 2
	//String[] addr=IPLocalizer.search(request);
	
	out.println("IP:"+ip+"  Address:"+addr[0]+addr[1]);

%>


