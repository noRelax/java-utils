/*
 * �������� 2007-4-19
 *
 * TODO Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
package LanClient;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;
/**
 * @author Administrator
 *
 * TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class Client extends JFrame{
	public Client(){
		try {
			setUndecorated(true);
			setResizable(false);
			
			//������Ļ��С
			Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
			//���ͼ��
			setIconImage(Toolkit.getDefaultToolkit().getImage("image/f.gif"));
			setTitle("������ͨv2.1--������Կͻ���");
			setSize(screen.width,screen.height);
			setLocation((screen.width-getWidth())/2,(screen.height-getHeight())/2);
			setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
