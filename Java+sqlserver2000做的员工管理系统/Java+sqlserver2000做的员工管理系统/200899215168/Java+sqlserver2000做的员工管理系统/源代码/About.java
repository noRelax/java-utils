

//关于软件类

package classsource;

import java.awt.*;//倒包
import java.awt.event.*;//倒包
import javax.swing.*;//倒包

public class About extends JInternalFrame {


 		JLabel label = new JLabel("运行环境：Windows");//创建标签并实例化
		JLabel labe2 = new JLabel("开发语言：JAVA");//创建标签并实例化
		JLabel labe3 = new JLabel("数据库类型：SqlServer2000");//创建标签并实例化
		JLabel labe4 = new JLabel("开发人员：张扬、王丽霞、高旭、李爱全");//创建标签并实例化
		public About(){//构造方法
	        setTitle("关于");//设置标题
	 	    Container con=getContentPane();
            con.setLayout(new GridLayout(4,1));//设置网格布局
            con.add(label);//添加标签
            con.add(labe2);//添加标签
            con.add(labe3);//添加标签
            con.add(labe4);//添加标签
            con.setBackground(Color.green);//添加背景颜色


	 	    setResizable(false);//不可更改大小
	 	    setSize(380,220);//设置大小
	 	    setVisible(true);//可见
	 	    setClosable(true);//设置可以关闭
			}

	}
