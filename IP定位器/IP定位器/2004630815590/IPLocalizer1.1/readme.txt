............................................................
                      IP��λ�� V1.0
                 http://www.xdevelop.net
............................................................

һ��˵��
    IPLocalizer��һ������JSP������IP��λ���ߣ����Ը��ݷ�����IP�ж������ڵ����������

����ʹ��
    ���ӣ�
    <%@ page import="net.xdevelop.ip.*"%>

    <%
	String ip=request.getRemoteAddr();
	String[] addr=IPLocalizer.search(ip);
	out.println("IP:"+ip+"�����ڵ���Ϊ:"+addr[0]+addr[1]);
    %>
    ͨ������IPLocalizer.search(ip)�ɷ���һ������Ϊ2������(������addrΪ��)��addr[0]����ϢΪ���Һ�ʡ�У�
���й��㶫��addr[1]Ϊ�����ַ�������������163��
    ͨ���������¹���
    1.���Ϊ����������addr[0]һ����"�й�"��addr[1]Ϊ����ԺУ�����廪��
    2.���Ϊ�й���½(�����۰�̨)����addr[0]һ��������"�й��㶫"��addr[1]һ������Ϊ"����"��"����163�û�"�ȣ�
    3.���Ϊ���⣬�۰�̨��δȷ����ַ����addr[0]û��"�й�"��ǰ׺;
    4.���Ϊ���⣬addr[0]Ϊ�������ƣ�����addr[1]�п���û�о����ַ����addr[1]����Ϊ"";

������װ
    ��ip.jar����WEB-INF\lib���ɣ��ο����������еĵ��÷�����
    ���µĲ��ضི�˰�^^

�ġ�����
    �������������BUG����EMAIL��starboy@xdevelop.net
    лл��

�塢�޶���¼
    1. V1.1 ������IPLocalizer.search(HttpServletRequest request)��������JSPҳ����ֱ��ʹ�ã����ƣ�String[] addr=IPLocalizer.search(request)
    2. V1.1 �������˶ԷǷ�IP����Ĵ����Ƿ�IP���뽫����""�մ�

                                                   Starboy
                                          EMAIL:webmaster@xdevelop.net
                                           WEB: www.xdevelop.net


