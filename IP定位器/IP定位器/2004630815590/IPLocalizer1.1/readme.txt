............................................................
                      IP定位器 V1.0
                 http://www.xdevelop.net
............................................................

一、说明
    IPLocalizer是一个用于JSP开发的IP定位工具，可以根据访问者IP判断其所在地区或机构。

二、使用
    例子：
    <%@ page import="net.xdevelop.ip.*"%>

    <%
	String ip=request.getRemoteAddr();
	String[] addr=IPLocalizer.search(ip);
	out.println("IP:"+ip+"其所在地区为:"+addr[0]+addr[1]);
    %>
    通过调用IPLocalizer.search(ip)可返回一个长度为2的数组(这里以addr为例)，addr[0]的信息为国家和省市，
如中国广东，addr[1]为具体地址或机构，如深圳163。
    通常符合以下规则：
    1.如果为教育网，则addr[0]一般是"中国"，addr[1]为具体院校，如清华；
    2.如果为中国大陆(不含港澳台)，则addr[0]一般类似于"中国广东"，addr[1]一般类似为"深圳"、"深圳163用户"等；
    3.如果为国外，港澳台或未确定地址，则addr[0]没有"中国"的前缀;
    4.如果为国外，addr[0]为国家名称，不过addr[1]中可能没有具体地址，即addr[1]可能为"";

三、安装
    将ip.jar拷入WEB-INF\lib即可，参考上面例子中的调用方法。
    余下的不必多讲了吧^^

四、其它
    共享软件，如有BUG，请EMAIL：starboy@xdevelop.net
    谢谢！

五、修订记录
    1. V1.1 版增加IPLocalizer.search(HttpServletRequest request)，可以在JSP页面内直接使用，类似：String[] addr=IPLocalizer.search(request)
    2. V1.1 版增加了对非法IP输入的处理，非法IP输入将返回""空串

                                                   Starboy
                                          EMAIL:webmaster@xdevelop.net
                                           WEB: www.xdevelop.net


