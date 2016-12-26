package card;

import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class queryPanel extends JPanel
{
   public queryPanel(String id)
  {
    try
    {
      this.CardID=id;
      jbInit();
    } catch( Exception exception )
    {
      exception.printStackTrace();
    }
  }

  private void jbInit() throws Exception
  {
    this.setLayout( null );
    jLabel1.setFont( new java.awt.Font( "Dialog", Font.PLAIN, 18 ) );
    jLabel1.setHorizontalAlignment( SwingConstants.CENTER );
    jLabel1.setText( "用户名：" );
    jLabel1.setBounds( new Rectangle( 64, 46, 76, 18 ) );
    jLabel2.setFont( new java.awt.Font( "Dialog", Font.BOLD, 18 ) );
    jLabel2.setForeground( Color.red );
    jLabel2.setBounds( new Rectangle( 207, 41, 159, 23 ) );
    jLabel3.setFont( new java.awt.Font( "Dialog", Font.PLAIN, 18 ) );
    jLabel3.setHorizontalAlignment( SwingConstants.CENTER );
    jLabel3.setText( "卡　号：" );
    jLabel3.setBounds( new Rectangle( 63, 117, 75, 30 ) );
    jLabel4.setFont( new java.awt.Font( "Dialog", Font.BOLD, 18 ) );
    jLabel4.setForeground( Color.red );
    jLabel4.setBounds( new Rectangle( 209, 118, 151, 26 ) );
    jLabel5.setFont( new java.awt.Font( "Dialog", Font.PLAIN, 18 ) );
    jLabel5.setHorizontalAlignment( SwingConstants.CENTER );
    jLabel5.setText( "余　额：" );
    jLabel5.setBounds( new Rectangle( 63, 193, 81, 19 ) );
    jLabel6.setFont( new java.awt.Font( "Dialog", Font.BOLD, 18 ) );
    jLabel6.setForeground( Color.red );
    jLabel6.setBounds( new Rectangle( 208, 191, 173, 25 ) );
    this.add( jLabel1 );
    this.add( jLabel3 );
    this.add( jLabel6 );
    this.add( jLabel4 );
    this.add( jLabel2 );
    this.add( jLabel5 );

    staticMessage sm=new staticMessage(CardID);
    jLabel2.setText(staticMessage.getUserName());
    jLabel4.setText(CardID);
    jLabel6.setText(staticMessage.getLevel());
  }

  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel6 = new JLabel();
  //自定义变量
  String CardID;
}
