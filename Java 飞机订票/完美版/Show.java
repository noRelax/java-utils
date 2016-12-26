package flight.manage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;

public class Show extends JPanel implements ActionListener
{
	private JTextField jtf=new JTextField(10);
	private JPasswordField password=new JPasswordField(10);
	private JLabel label1=new JLabel("账号");
	private JLabel label2=new JLabel("密码");
	private JButton button=new JButton("查看");
	private JTextArea area=new JTextArea();
	private JScrollPane pan;
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	
	public Show()
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		}
		catch(Exception ex)
		{
		}
		pan=new JScrollPane(area);
		JPanel p1=new JPanel();
		p1.setBorder(new TitledBorder("输入有效认证"));
		p1.add(label1);
		p1.add(jtf);
		p1.add(label2);
		p1.add(password);
		
		JPanel p2=new JPanel();
		p2.setBorder(new TitledBorder("操作"));
		p2.add(button);
		
		this.setLayout(new BorderLayout());
		this.add(p1,BorderLayout.NORTH);
		this.add(pan,BorderLayout.CENTER);
		this.add(p2,BorderLayout.SOUTH);
		
		button.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			
			area.setText("");
			if(!(jtf.getText().equals("0302")&&password.getText().equals("0302")))
			JOptionPane.showMessageDialog(null,"账好或密码不对","错误",JOptionPane.ERROR_MESSAGE);
			else
			{con = DriverManager.getConnection("jdbc:odbc:dsStudent","sa","");
	        stmt = con.createStatement();
	        rs=stmt.executeQuery("select * from flight");
	        while(rs.next())
	        {
	        	area.append(rs.getString(1)+"\t");
	        	area.append(rs.getString(2)+"\t");
	        	area.append(rs.getString(3)+"\t");
	        	area.append(rs.getString(4)+"\t");
	        	area.append(rs.getString(5)+"\t");
	        	area.append(rs.getString(6)+"\t");
	        	area.append(""+rs.getFloat(7)+"\t");
	        	area.append(""+rs.getFloat(8)+"\t");
	        	area.append(""+rs.getFloat(9)+"\t");
	        	area.append(""+rs.getFloat(10)+"\t");
	        	area.append(""+rs.getInt(11)+"\t");
	        	area.append(rs.getString(12)+"\t");
	        	area.append(rs.getString(13)+"\t"+"\n");
	        	
	        }}}
		catch(Exception ex)
		{
		}
		
	}
	public static void main(String[] args)
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception ex)
		{
			
		}
		JFrame frame=new JFrame();
		frame.getContentPane().add(new Show());
		frame.setSize(500,450);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}