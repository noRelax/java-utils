/* Author : fubin
 * Site  : www.cujava.com
 * -- Ò¬×ÓJava»¶Ó­Äú -- 
µÇÂ½ÓÃ»§Ãû¼°ÃÜÂë£ºcujava ÃÜÂë 123  ºÍ fubin ÃÜÂë123
          
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.*;
public class Login_1 extends javax.swing.JFrame {
	
	private Dimension d = Toolkit.getDefaultToolkit().getScreenSize(); // Determine user's screen size
    
    /** Creates new form NewAccount */
    public Login_1() {
        initComponents();
    }
    
    private void initComponents()
    	{
    	setResizable (false);
    	setLocation (d.width / 2 - getWidth() / 2, d.height / 2 - getHeight() / 2);
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        admin_userid = new javax.swing.JTextField();
        admin_password = new javax.swing.JPasswordField();
        staff_userid = new javax.swing.JTextField();
        staff_password = new javax.swing.JPasswordField();
        btnOk = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        
        Icon login = new ImageIcon("img/login.jpg");
        JLabel computer = new JLabel(login);
        Icon tabbed = new ImageIcon("img/admin.gif");
        JLabel tab = new JLabel(tabbed);
        Icon tabbed2 = new ImageIcon("img/staff.gif");
        JLabel tab2 = new JLabel(tabbed2);

        getContentPane().setLayout(null);
        
        setTitle("SDMS");
        
        getContentPane().add(computer);
        computer.setBounds(40, 0, 273, 216);
        	
        //Admin Username label
        jLabel1.setForeground (Color.black);
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Username :");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(50, 265, 75, 25);
        
        //Admin Password label
        jLabel2.setForeground (Color.black);
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Password :");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(50, 300, 75, 25);

        getContentPane().add(admin_userid);
        getContentPane().add(admin_password);
        admin_userid.setBounds(130, 265, 150, 25);        
        admin_password.setBounds(130, 300, 150, 25);
        
        //Staff Username label
        jLabel3.setForeground (Color.black);
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setText("Username :");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(50, 375, 75, 25);
        
        //Staff Password label
        jLabel4.setForeground (Color.black);
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel4.setText("Password :");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(50, 410, 75, 25);

        getContentPane().add(staff_userid);
        getContentPane().add(staff_password);
        getContentPane().setBackground(Color.white);
        staff_userid.setBounds(130, 375, 150, 25);        
        staff_password.setBounds(130, 410, 150, 25);
        
        getContentPane().add(btnOk);
        
        getContentPane().add(tab);
        tab.setBounds(40, 225, 280,110);
        
        getContentPane().add(tab2);
        tab2.setBounds(40, 335, 280,110);
        
        //Login button
        btnOk.setText("LOGIN");
        btnOk.setFont(new java.awt.Font("Tahoma", 1, 9));
        btnOk.setBackground(Color.white);
        btnOk.setBounds (236, 442, 70, 25);
        btnOk.setToolTipText("Click to login");
		btnOk.addActionListener(new ActionListener(){
  			public void actionPerformed(ActionEvent evt)
			{
				if (!staff_userid.getText().equals("") && !staff_password.getText().equals(""))
					{
						verifystaffLogin();
					}
				else if (!admin_userid.getText().equals("") && !admin_password.getText().equals(""))
					{
						verifyadminLogin();
					}
			}
        });
        pack();
        setIconImage (getToolkit().getImage ("img/logo_1.png"));
		setSize (370, 500);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
		}
    
   
    public static void main(String args[]) {
        Login_1 ad=new Login_1();
    }
    
    private javax.swing.JButton btnCancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField admin_password;
    private javax.swing.JButton btnOk;
    private javax.swing.JTextField admin_userid;
    private javax.swing.Icon login;
    private javax.swing.JLabel computer;
    private javax.swing.Icon tabbed;
    private javax.swing.JLabel tab;
    private javax.swing.Icon tabbed2;
    private javax.swing.JLabel tab2;
    private javax.swing.JTextField staff_userid;
    private javax.swing.JPasswordField staff_password;
    
	void verifyadminLogin() 
		{
				Connection con=null;
				String url="jdbc:odbc:DobiTest";
				Statement st=null;
				
			  try
			  {
					
						 String val1=admin_userid.getText();
						 val1=val1.trim();
						 String val2 =  (String)admin_password.getText();
						 val2 =  val2.trim();					
					
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					
			   		con=DriverManager.getConnection(url);
					
			   		st = con.createStatement();
					
	
				ResultSet rs=st.executeQuery("Select password from admin where username='"+val1+"'");
				
				while(rs.next()){
					String user = rs.getString(1);
					
					boolean b=user.equals(val2);				
				
					if(b)
					{
					setVisible(false);
					JOptionPane.showMessageDialog((Component) null, "Wow! U got it! Hihi =B", "Mmuahaha", JOptionPane.PLAIN_MESSAGE);
				//	MainMenu menu=new MainMenu();  << To link to other page, replace it with yours
					}
					 else
					{
						JOptionPane.showMessageDialog((Component) null, "ÃÜÂë´íÎó ", "Login Error", JOptionPane.INFORMATION_MESSAGE);
						admin_password.setText("");
						admin_password.requestFocus();
					}
					}
			  }
			  catch(SQLException ex)
			   {
			    System.out.println("Unable to access the database");
			   }
			  catch(ClassNotFoundException ex)
			   {
			    System.out.println("Class not found");
			   }
			  catch(Exception ex)
			  {
               System.out.println("Exception raised is:"+ex);
			  }
			  finally {
			  con=null;
			  }
		}
		
	void verifystaffLogin() 
		{
				Connection con=null;
				String url="jdbc:odbc:DobiTest";
				Statement st=null;
				
			  try
			  {
					
						 String val1=staff_userid.getText();
						 val1 = val1.trim();
						 String val2 =  (String)staff_password.getText();
						 val2 = val2.trim();					
					
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					
			   		con=DriverManager.getConnection(url);
					
			   		st = con.createStatement();
					
	
				ResultSet rs=st.executeQuery("Select password from staff where username='"+val1+"'");
				
				while(rs.next()){
					String user = rs.getString(1);
					
					boolean b=user.equals(val2);				
				
					if(b)
					{
					setVisible(false);
					JOptionPane.showMessageDialog((Component) null, "Wow! U got it here too! Hihi =B", "Mmuahaha", JOptionPane.PLAIN_MESSAGE);
				//	MainMenu menu=new MainMenu(); << To link to other page, replace it with yours
					}
					 else
					{
						JOptionPane.showMessageDialog((Component) null, "Invalid password. Please try again. ", "Login Error", JOptionPane.INFORMATION_MESSAGE);
						staff_password.setText("");
						staff_password.requestFocus();
					}
					}
			  }
			  catch(SQLException ex)
			   {
			    System.out.println("Unable to access the database");
			   }
			  catch(ClassNotFoundException ex)
			   {
			    System.out.println("Class not found");
			   }
			  catch(Exception ex)
			  {
               System.out.println("Exception raised is:"+ex);
			  }
			  finally {
			  con=null;
			  }
		}	
}