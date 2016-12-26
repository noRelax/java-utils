Readme.txt
说明文档

opml.xml
RSS频道组定义文件


一、使用说明

	程序入口命令
		cd F:\java\2003012142
		set classpath=classes
		javac -d classes src/RssParser.java
		java RssParser
	操作
		互动部分在左侧的树
		左键选择---状态栏显示RSS的URL
		右键弹出---该对象的方法
		
二、功能
	简单快速查看网上的RSS资源，支持RSS2.0版本。
	可以添加、删除RSS，可以所有频道一起更新。
	
三、特点
	涉及：异常处理、Swing图形界面、多线程、本地IO操作、网络编程、XMLDOM操作
	
四、有待改进
	支持RSS1.0等版本、支持Atom版本。
	UI进阶：HTML解析查看摘要、菜单功能完善。
	本地化数据。（支持离线浏览）

五、RSS概说
	·目前互联网上提供RSS订阅服务的大多是Blog站点，新闻门户站点。
	 近期Google、百度等搜索引擎也提供了各种形式的RSS服务。
	 一周前，在本人与学生清华门户网站、学生网络电视台的共同努力下，终于在学生清华门户网站后台做好了RSS源，使其成为清华校园网第一家提供RSS订阅服务的网站。计划下学期做完其市场调研后联系网络中心，向全校各网站推广。
	·目前网上流行的RSS阅读器不少，国产的不多，多数都比较垃圾。
	 网上能下载到的开源的这类程序几乎没有，仅有的RSSBandit等开源程序还都是要求.NET FrameWorks运行环境的。
	 我打算利用Java跨平台优势和网络编成优势，填补这一空缺。
	·RSS优势不用我讲。
	 上个月的报道，支持RSS订阅的手机已经上市。
	 这半年来，许多Blog精英都在讨论Feed://的网络协议的建设的技术规范问题。

六、感想
	XML与张量
	XML的基本属性是“良构”Well Structed
	张量的基本属性是“多线性”Multilinear
	XML的魅力在于“扩展”Extensible
	张量的魅力在于“万有性”Universal
	推测：发明XML的人一定具有良好的代数素养。
	
	W3C与WTO
	W3C是互联网技术的规范化组织。
	WTO是世界贸易的规范化组织。
	全球化浪潮“甚嚣尘上”的今天，做技术的人不能不关注W3C，就像做生意的人不能不关注WTO一样。
	
	脚本不是程序
	本人长期写惯了脚本，从Flash的ActionScript到DHTML的Javascript，很不注意代码的规范。
	习惯了快速解决的思路，做一个稍大点的程序，比如这次Java大作业，就发现自己的很多陋习非常影响程序的书写效率。
	面向解决的思想先入为主，面向对象的思想尚未在我的大脑中扎根。仅凭这几次作业的训练，是远远不够的。
	
七、测试报告
	本地URL以file:///开头的路径成功解析
	网络上的URL以http://开头的路径解析失败（Permission denied），关闭防火墙（金山网镖6）后，联网成功，RSS解析成功。
	多线程（20）并行，联网效率大幅度提高，返回结果顺序为先连接成功者先显示。
	正在连接的RSSNode其名称用()标记，解析成功后去掉()标记。
	全程状态栏提示，包括所有异常捕获。
	大多数RSS2.0都能成功解析，百度的出错，提示说字符集方面有错，尚未解决。
	强壮性漏洞：
	1.若频道定义文件opml.xml不存在则程序无法运行。
	2.477行更新tree的外观时会随机抛出空指针异常，而且不能捕获。
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
		搞不懂，没解决，但是不影响程序正常运行。只是运行到477行updateUI()时Tree偶尔会变形，很诡异。
		将该行注释掉也可以。
		
	合理性欠缺：添加新RSS站点，若无效仍然会留下。
	细节问题：	弹出对话框中的汉字太丑陋，故改用英文。
				弹出对话框会搞丢，弹出对话框后切换到其他应用程序，然后再通过鼠标单击Windows任务栏则回到Java程序主界面见不到对话框在它上面，但是通过Alt+Tab切换回Java程序就能看到对话框。
				
				
				
				
				
				
				
结束语
作业提交后我将继续改进此程序，希望您能继续关注我的程序并给与指点。

数32
王元涛
2003012142
wangyc03@mails.tsinghua.edu.cn
网名：civ3				