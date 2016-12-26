//奖惩管理类



package classsource;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class EncouragementPunish extends JInternalFrame {

    public EncouragementPunish() {
        //连接数据库--------
        Database.joinDB();
    	setTitle("员工奖罚管理!");
    	Font f = new Font("新宋体", 0, 14);
        lb1 = new JLabel("员 工 奖 罚 信 息");
        lb2 = new JLabel("员工编号:");
        lb3 = new JLabel("地点:");
        lb4 = new JLabel("奖罚时间:");
        lb5 = new JLabel("奖罚原因:");
        lb6 = new JLabel("备注:");
        lb7 = new JLabel("员工姓名:");
        tnumber = new JTextField();
        tname = new JTextField();
        tremarks = new JTextArea();
        tadress = new JTextField();
        treason = new JTextField();
        ttime = new JTextField();
        btright = new JButton("<<");
        btleft = new JButton(">>");
        btdelet = new JButton("删除");
        btamend = new JButton("修改");
        btsave = new JButton("保存");
        btadd = new JButton("添加");

        getContentPane().setLayout(null);

        lb1.setBackground(new java.awt.Color(204, 204, 204));
        lb1.setFont(new java.awt.Font("新宋体", 1, 18));
        lb1.setForeground(new java.awt.Color(0, 0, 255));
        getContentPane().add(lb1);
        lb1.setBounds(120, 10, 190, 30);

        lb2.setFont(f);
        getContentPane().add(lb2);
        lb2.setBounds(20, 60, 70, 20);

        lb7.setFont(f);
        getContentPane().add(lb7);
        lb7.setBounds(215, 60, 70, 20);

        getContentPane().add(tname);
        tname.setBounds(280,60,90,20);
        lb3.setFont(f);
        getContentPane().add(lb3);
        lb3.setBounds(20, 100, 70, 20);

        lb4.setFont(f);
        getContentPane().add(lb4);
        lb4.setBounds(20, 140, 70, 20);

        lb5.setFont(f);

        getContentPane().add(lb5);
        lb5.setBounds(20, 180, 70, 20);

        lb6.setFont(f);

        getContentPane().add(lb6);
        lb6.setBounds(260, 100, 70, 20);

        getContentPane().add(tnumber);
        tnumber.setBounds(100, 60, 90, 20);

        getContentPane().add(tremarks);
        tremarks.setBounds(260, 130, 130, 70);

        getContentPane().add(tadress);
        tadress.setBounds(100, 100, 150, 20);

        getContentPane().add(treason);
        ttime.setBounds(100, 140, 150, 20);

        getContentPane().add(ttime);
        treason.setBounds(100, 180, 150, 21);

        btright.setFont(f);
        getContentPane().add(btright);
        btright.setBounds(330, 250, 50, 20);

        btleft.setFont(f);
        getContentPane().add(btleft);
        btleft.setBounds(330, 230, 50, 20);

        btdelet.setFont(f);
        getContentPane().add(btdelet);
        btdelet.setBounds(250, 240, 70, 25);

        btamend.setFont(f);
        getContentPane().add(btamend);
        btamend.setBounds(170, 240, 70, 25);

        btsave.setFont(f);
        getContentPane().add(btsave);
        btsave.setBounds(10, 240, 70, 25);

        btadd.setFont(f);
        getContentPane().add(btadd);
        btadd.setBounds(90, 240, 70, 25);
//初始化窗体数据-------------------------------------------------------
        String sqlc="select * from EncouragementPunishInformation";
        try{
        if(Database.query(sqlc)){

        	Database.rs.next();
            tnumber.setText(""+Database.rs.getInt("EP_Number"));
            tname.setText(Database.rs.getString("EP_Name"));
            ttime.setText(Database.rs.getString("EP_Date"));
            tadress.setText(Database.rs.getString("EP_Address"));
            treason.setText(Database.rs.getString("EP_Causation"));
            tremarks.setText(Database.rs.getString("EP_Remark"));

        	}
          }
         catch(Exception e){
         	System.out.println(e);
         	}

//-------------------------------------------------------------------

//为左右按钮加事件--------------------------------------------------
     btright.addActionListener(new ActionListener(){
     	public void actionPerformed(ActionEvent e){
     		try{
     		if(Database.rs.previous()){
     	    tnumber.setEditable(true);
            tnumber.setText(""+Database.rs.getInt("EP_Number"));
            tname.setText(Database.rs.getString("EP_Name"));
            ttime.setText(Database.rs.getString("EP_Date"));
            tadress.setText(Database.rs.getString("EP_Address"));
            treason.setText(Database.rs.getString("EP_Causation"));
            tremarks.setText(Database.rs.getString("EP_Remark"));
     			}
     		  }
     		 catch(Exception el){
     		 	System.out.println(el);
     		 	}
     		}
     	});


     btleft.addActionListener(new ActionListener(){
     	public void actionPerformed(ActionEvent e){
     		try{
     		if(Database.rs.next()){

     		tnumber.setEditable(true);
            tnumber.setText(""+Database.rs.getInt("EP_Number"));
            tname.setText(Database.rs.getString("EP_Name"));
            ttime.setText(Database.rs.getString("EP_Date"));
            tadress.setText(Database.rs.getString("EP_Address"));
            treason.setText(Database.rs.getString("EP_Causation"));
            tremarks.setText(Database.rs.getString("EP_Remark"));
     			}
     		 }
     		 catch(Exception er){
     		 	System.out.println(er);
     		 	}
     		}
     	});




//-添加添加按扭事件---------------------------------------
        btadd.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		tnumber.setText("");
        		tnumber.setEditable(false);
        		btsave.setEnabled(true);
        		tname.setText("");
        		ttime.setText("");
        		treason.setText("");
        		tadress.setText("");
        		tremarks.setText("");
        	}
        });

//-添加保存按扭事件----------------------------------------------------------------
        btsave.addActionListener(new ActionListener(){

        	public void actionPerformed(ActionEvent e){


        	 if(tname.getText().equals("") || ttime.getText().equals("") || tadress.getText().equals("") || treason.getText().equals("")){
        	    new JOptionPane().showMessageDialog(null,"姓名,地址,时间,奖励原因!填写不能为空！");
        	   }
        	else{
        	String name=tname.getText();
        	String time=ttime.getText();
        	String reason=treason.getText();
        	String address=tadress.getText();
        	String remarks=tremarks.getText();
        	String sInsert="insert EncouragementPunishInformation values ('"+name+"','"+time+"','"+ address +"','"+remarks+"','"+reason+"')";

        	System.out.println(sInsert);
        	try{
        	if(Database.executeSQL(sInsert)){
        	tnumber.setEditable(true);
        	btsave.setEnabled(false);
        	new JOptionPane().showMessageDialog(null,"成功添加数据！");
        	String sql="select * from EncouragementPunishInformation ";
        	Database.joinDB();
        	Database.query(sql);

        	Database.rs.last();
        	tnumber.setText(""+Database.rs.getInt("EP_Number"));
            }
             }catch(Exception el){
             	new JOptionPane().showMessageDialog(null,"添加数据不成功！");
             }
         }
       	}
        });
//---添加修改和删除事件按扭-------------------------------------------------------
   	btdelet.addActionListener(new ActionListener(){
   		public void actionPerformed(ActionEvent e){
   			try{
   				String sql="delete from EncouragementPunishInformation where EP_Number ='"+ tnumber.getText()+"'";
   				System.out.println(sql);
   				if(Database.executeSQL(sql)){
   					new JOptionPane().showMessageDialog(null,"数据删除成功！");
   					Database.joinDB();
   	  				String sqll="select * from EncouragementPunishInformation";
   	  				Database.query(sqll);
   	  				Database.rs.last();
                    tnumber.setText(""+Database.rs.getInt("EP_Number"));
                    tname.setText(Database.rs.getString("EP_Name"));
                    ttime.setText(Database.rs.getString("EP_Date"));
                    tadress.setText(Database.rs.getString("EP_Address"));
                    treason.setText(Database.rs.getString("EP_Causation"));
                    tremarks.setText(Database.rs.getString("EP_Remark"));
   					}
   				}
   			catch(Exception el){}
   			}
   		});

   	btamend.addActionListener(new ActionListener(){
   		public void actionPerformed(ActionEvent e){
   			try{
   				String supdate="update EncouragementPunishInformation set EP_Name='"+ tname.getText()+ "',EP_Date='"+ ttime.getText() +"',EP_Address='"+tadress.getText()+"',EP_Causation='"+treason.getText()+"',EP_Remark='"+tremarks.getText()+"'where EP_Number='"+tnumber.getText()+"'";

   				if(Database.executeSQL(supdate)){
   					new JOptionPane().showMessageDialog(null,"数据修改成功！");
   					Database.joinDB();
   	  				String sqll="select * from EncouragementPunishInformation";
   	  				Database.query(sqll);
   	  				Database.rs.last();
                    tnumber.setText(""+Database.rs.getInt("EP_Number"));
                    tname.setText(Database.rs.getString("EP_Name"));
                    ttime.setText(Database.rs.getString("EP_Date"));
                    tadress.setText(Database.rs.getString("EP_Address"));
                    treason.setText(Database.rs.getString("EP_Causation"));
                    tremarks.setText(Database.rs.getString("EP_Remark"));
   					}
   				}
   				catch(Exception es){new JOptionPane().showMessageDialog(null,"修改数据不成功！");}
   			}
   		});
//-----------------------------------------------------------------


        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-403)/2, (screenSize.height-329)/2, 403, 329);
        this.setClosable(true);
        setVisible(true);

    }

 //   public static void main(String args[]) {
//                new EncouragementPunish();
//   }

     JButton btadd;
     JButton btamend;
     JButton btdelet;
     JButton btleft;
     JButton btright;
     JButton btsave;
     JLabel lb1;
     JLabel lb2;
     JLabel lb3;
     JLabel lb4;
     JLabel lb5;
     JLabel lb6;
     JLabel lb7;
     JTextField tadress;
     JTextField tname;
     JTextField tnumber;
     JTextField treason;
     JTextArea tremarks;
     JTextField ttime;


}
