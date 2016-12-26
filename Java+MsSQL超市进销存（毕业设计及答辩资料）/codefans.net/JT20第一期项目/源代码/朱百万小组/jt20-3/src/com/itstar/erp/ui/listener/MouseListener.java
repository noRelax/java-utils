package com.itstar.erp.ui.listener;

import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

public class MouseListener implements java.awt.event.MouseListener {

	public void mouseReleased(MouseEvent e) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int result = JOptionPane.showConfirmDialog(null, "您真的要退出系统吗?", "请确认",
				JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			System.exit(0);
		}

	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	public void mousePressed(MouseEvent e) {

	}
}
