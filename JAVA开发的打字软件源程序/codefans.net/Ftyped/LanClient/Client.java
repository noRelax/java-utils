/*
 * 创建日期 2007-4-19
 *
 * TODO 要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package LanClient;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;
/**
 * @author Administrator
 *
 * TODO 要更改此生成的类型注释的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class Client extends JFrame{
	public Client(){
		try {
			setUndecorated(true);
			setResizable(false);
			
			//计算屏幕大小
			Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
			//添加图标
			setIconImage(Toolkit.getDefaultToolkit().getImage("image/f.gif"));
			setTitle("疯狂打字通v2.1--网络测试客户端");
			setSize(screen.width,screen.height);
			setLocation((screen.width-getWidth())/2,(screen.height-getHeight())/2);
			setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
