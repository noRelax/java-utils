package com.itstar.erp.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JLabel;

public class  TimeListener  implements ActionListener {
	private JLabel clock;
	    public TimeListener(JLabel clock) {
	    	this.clock=clock;
	}
		public void actionPerformed(ActionEvent e) {
        Calendar c = new GregorianCalendar();
		String time = c.get(Calendar.YEAR) + "年" +
					  (c.
							  get(Calendar.MONTH) + 1) + "月" +
					  c.get(Calendar.DATE)  + " 日 " ;
		int h = c.get(Calendar.HOUR_OF_DAY);
		int m = c.get(Calendar.MINUTE);
		int s = c.get(Calendar.SECOND);
		String ph = h<10 ? "0":"";
		String pm = m<10 ? "0":"";
		String ps = s<10 ? "0":"";		
		time ="温馨提示：  今天是"+time+"   "+"现在时刻" +"  "+ph + h + ":" + pm + m + ":" + ps + s; 
		clock.setText(time);
		clock.repaint();
		}
   }



