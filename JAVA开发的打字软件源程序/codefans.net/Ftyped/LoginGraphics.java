import java.awt.*;
import javax.swing.*;

public class LoginGraphics extends JPanel{
	public void  paint(Graphics g){
		paintComponent(g);
		
		g.setFont(new Font("x",Font.BOLD+Font.ITALIC,20));
		g.setColor(new Color(255,255,255));
		g.drawString("FTYPED",22,32);
		g.setColor(new Color(0,0,0));
		g.drawString("FTYPED",20,30);
		
        g.setFont(new Font("Dialog",Font.BOLD+Font.ITALIC,45));
        for(int i=1;i<=5;i++){
        	g.setColor(new Color(255-(i*50),255-(i*50),255-(i*50)));
        	g.drawLine(0,36+i,293,36+i);
        }
        
        g.setColor(new Color(255,255,255));
        g.drawString("疯狂打字通",62,85);
        g.setColor(new Color(0,0,0));
        g.drawString("疯狂打字通",60,85);
        
        g.setFont(new Font("x",Font.BOLD,14));
        g.drawString("v2.1",300,90);
        
        g.setFont(new Font("x",Font.PLAIN,12));
        g.setColor(new Color(255,255,255));
        g.drawString("激发你手指的灵感",341,91);
        g.setColor(new Color(0,0,0));
        g.drawString("激发你手指的灵感",340,90);
        
        
        for(int i=1;i<=5;i++){
        	g.setColor(new Color(255-(i*50),255-(i*50),255-(i*50)));
        	g.drawLine(60,100-i,443,100-i);
        }
        
        g.setFont(new Font("x",Font.PLAIN,12));
        g.setColor(new Color(255,255,255));
        g.drawString("FTYPED工作组版权所有",311,151);
		g.drawString("FTYPED工作组成员:熊占洲、廖阳、张浩佳、杨智、李文彪、张佳伟",61,115);
		g.drawString("(2007.3.15-2007.4.26)",321,133);
		g.drawString("开发工具 Java 编辑工具 Eclipse",61,151);
        g.setColor(new Color(0,0,0));
        g.drawString("FTYPED工作组版权所有",310,150);
		g.drawString("FTYPED工作组成员:熊占洲、廖阳、张浩佳、杨智、李文彪、张佳伟",60,114);
		g.drawString("(2007.3.15-2007.4.26)",320,132);
		g.drawString("开发工具 Java 编辑工具 Eclipse",60,150);
	}
}
