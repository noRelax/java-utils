Readme.txt
˵���ĵ�

opml.xml
RSSƵ���鶨���ļ�


һ��ʹ��˵��

	�����������
		cd F:\java\2003012142
		set classpath=classes
		javac -d classes src/RssParser.java
		java RssParser
	����
		����������������
		���ѡ��---״̬����ʾRSS��URL
		�Ҽ�����---�ö���ķ���
		
��������
	�򵥿��ٲ鿴���ϵ�RSS��Դ��֧��RSS2.0�汾��
	������ӡ�ɾ��RSS����������Ƶ��һ����¡�
	
�����ص�
	�漰���쳣����Swingͼ�ν��桢���̡߳�����IO�����������̡�XMLDOM����
	
�ġ��д��Ľ�
	֧��RSS1.0�Ȱ汾��֧��Atom�汾��
	UI���ף�HTML�����鿴ժҪ���˵��������ơ�
	���ػ����ݡ���֧�����������

�塢RSS��˵
	��Ŀǰ���������ṩRSS���ķ���Ĵ����Blogվ�㣬�����Ż�վ�㡣
	 ����Google���ٶȵ���������Ҳ�ṩ�˸�����ʽ��RSS����
	 һ��ǰ���ڱ�����ѧ���廪�Ż���վ��ѧ���������̨�Ĺ�ͬŬ���£�������ѧ���廪�Ż���վ��̨������RSSԴ��ʹ���Ϊ�廪У԰����һ���ṩRSS���ķ������վ���ƻ���ѧ���������г����к���ϵ�������ģ���ȫУ����վ�ƹ㡣
	��Ŀǰ�������е�RSS�Ķ������٣������Ĳ��࣬�������Ƚ�������
	 ���������ص��Ŀ�Դ��������򼸺�û�У����е�RSSBandit�ȿ�Դ���򻹶���Ҫ��.NET FrameWorks���л����ġ�
	 �Ҵ�������Java��ƽ̨���ƺ����������ƣ����һ��ȱ��
	��RSS���Ʋ����ҽ���
	 �ϸ��µı�����֧��RSS���ĵ��ֻ��Ѿ����С�
	 ������������Blog��Ӣ��������Feed://������Э��Ľ���ļ����淶���⡣

��������
	XML������
	XML�Ļ��������ǡ�������Well Structed
	�����Ļ��������ǡ������ԡ�Multilinear
	XML���������ڡ���չ��Extensible
	�������������ڡ������ԡ�Universal
	�Ʋ⣺����XML����һ���������õĴ���������
	
	W3C��WTO
	W3C�ǻ����������Ĺ淶����֯��
	WTO������ó�׵Ĺ淶����֯��
	ȫ���˳����������ϡ��Ľ��죬���������˲��ܲ���עW3C��������������˲��ܲ���עWTOһ����
	
	�ű����ǳ���
	���˳���д���˽ű�����Flash��ActionScript��DHTML��Javascript���ܲ�ע�����Ĺ淶��
	ϰ���˿��ٽ����˼·����һ���Դ��ĳ��򣬱������Java����ҵ���ͷ����Լ��ĺܶ�ªϰ�ǳ�Ӱ��������дЧ�ʡ�
	��������˼������Ϊ������������˼����δ���ҵĴ�������������ƾ�⼸����ҵ��ѵ������ԶԶ�����ġ�
	
�ߡ����Ա���
	����URL��file:///��ͷ��·���ɹ�����
	�����ϵ�URL��http://��ͷ��·������ʧ�ܣ�Permission denied�����رշ���ǽ����ɽ����6���������ɹ���RSS�����ɹ���
	���̣߳�20�����У�����Ч�ʴ������ߣ����ؽ��˳��Ϊ�����ӳɹ�������ʾ��
	�������ӵ�RSSNode��������()��ǣ������ɹ���ȥ��()��ǡ�
	ȫ��״̬����ʾ�����������쳣����
	�����RSS2.0���ܳɹ��������ٶȵĳ�����ʾ˵�ַ��������д���δ�����
	ǿ׳��©����
	1.��Ƶ�������ļ�opml.xml������������޷����С�
	2.477�и���tree�����ʱ������׳���ָ���쳣�����Ҳ��ܲ���
	java.lang.NullPointerException
        at javax.swing.plaf.basic.BasicTreeUI.paintRow(Unknown Source)
        at javax.swing.plaf.basic.BasicTreeUI.paint(Unknown Source)
        at javax.swing.plaf.metal.MetalTreeUI.paint(Unknown Source)
        at javax.swing.plaf.ComponentUI.update(Unknown Source)
        at javax.swing.JComponent.paintComponent(Unknown Source)
        at javax.swing.JComponent.paint(Unknown Source)
        at javax.swing.JComponent._paintImmediately(Unknown Source)
        at javax.swing.JComponent.paintImmediately(Unknown Source)
        at javax.swing.RepaintManager.paintDirtyRegions(Unknown Source)
        at javax.swing.SystemEventQueueUtilities$ComponentWorkRequest.run(Unknown Source)
        at java.awt.event.InvocationEvent.dispatch(Unknown Source)
        at java.awt.EventQueue.dispatchEvent(Unknown Source)
        at java.awt.EventDispatchThread.pumpOneEventForHierarchy(Unknown Source)

        at java.awt.EventDispatchThread.pumpEventsForHierarchy(Unknown Source)
        at java.awt.EventDispatchThread.pumpEvents(Unknown Source)
        at java.awt.EventDispatchThread.pumpEvents(Unknown Source)
        at java.awt.EventDispatchThread.run(Unknown Source)
		�㲻����û��������ǲ�Ӱ������������С�ֻ�����е�477��updateUI()ʱTreeż������Σ��ܹ��졣
		������ע�͵�Ҳ���ԡ�
		
	������Ƿȱ�������RSSվ�㣬����Ч��Ȼ�����¡�
	ϸ�����⣺	�����Ի����еĺ���̫��ª���ʸ���Ӣ�ġ�
				�����Ի����㶪�������Ի�����л�������Ӧ�ó���Ȼ����ͨ����굥��Windows��������ص�Java����������������Ի����������棬����ͨ��Alt+Tab�л���Java������ܿ����Ի���
				
				
				
				
				
				
				
������
��ҵ�ύ���ҽ������Ľ��˳���ϣ�����ܼ�����ע�ҵĳ��򲢸���ָ�㡣

��32
��Ԫ��
2003012142
wangyc03@mails.tsinghua.edu.cn
������civ3				