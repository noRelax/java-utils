package QQ_test1;
import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;
public class table extends JPanel{
	JTable t;
	public table(Vector v,Vector row){
	 t=new JTable(new DefaultTableModel(v,row));
	 JScrollPane panel;  
     panel=new JScrollPane(t);
     t.setEditingColumn(4);
     this.setBackground(Color.black);
     t.setBackground(Color.black);
     t.setForeground(Color.green);
     panel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);    
	 panel.getViewport().add(t);
	 add(panel);
}
	public void setmodel(Vector v,Vector row)
	{
		t.setModel((new DefaultTableModel(v,row)));
	}
	public int getselectedrow()
	{
		return t.getSelectedRow();
	}
	public String getValueAt(int a,int b)
	{
		return (String)t.getValueAt(a,b);
	}
	public void printtable()
	{
		int colummCount = t.getModel().getColumnCount();// ÁÐÊý 
		int rowcount=t.getModel().getRowCount();
		for(int j=0;j<rowcount;j++)
		{
			for (int i = 0; i < colummCount; i++) 
				System.out.print(t.getModel().getValueAt(j, i).toString()+ "  "); 
			System.out.println(); 
		}
	}
	public void setEnable(boolean tf)
	{
		t.setEnabled(tf);
	}
}
