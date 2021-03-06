package card;
//download by http://www.codefans.net
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

public class eventFrame extends JFrame implements TreeSelectionListener
{
  BorderLayout borderLayout1 = new BorderLayout();
  JSplitPane jSplitPane1 = new JSplitPane();
  JScrollPane jScrollPane1 = new JScrollPane();
  JTree jTree1;
  public eventFrame()
  {
    try
    {
      Toolkit kit = Toolkit.getDefaultToolkit();
      Image img=kit.getImage("img/img.gif");
      this.setIconImage(img);
      Dimension screenSize=kit.getScreenSize();
      Dimension size=new Dimension(520,360);
      this.setSize(size);
      setLocation((screenSize.width-size.width)/2,(screenSize.height-size.height)/2);
      jbInit();
    } catch( Exception exception )
    {
      exception.printStackTrace();
    }
  }

  private void jbInit() throws Exception
  {
    getContentPane().setLayout( borderLayout1 );
    this.setTitle( "信用卡管理系统" );
    this.getContentPane().add( jSplitPane1, java.awt.BorderLayout.CENTER );
    //JTree１的模型
    DefaultMutableTreeNode root=new DefaultMutableTreeNode("信用卡系统");//定义根节点为“信用卡系统"
    DefaultMutableTreeNode bitchThing=new DefaultMutableTreeNode("查询");
    root.add(bitchThing);//根节点下添加bitchThing
    bitchThing=new DefaultMutableTreeNode("存款");
    root.add(bitchThing);
    bitchThing=new DefaultMutableTreeNode("取款");
    root.add(bitchThing);
    jTree1=new JTree(root);

    jSplitPane1.add( jScrollPane1, JSplitPane.LEFT );
    jSplitPane1.add(new welcomePanel(),JSplitPane.RIGHT);
    jScrollPane1.getViewport().add( jTree1 );
    jSplitPane1.setDividerLocation( 130 );

    jTree1.addTreeSelectionListener(this);
    int mode=TreeSelectionModel.SINGLE_TREE_SELECTION;
    jTree1.getSelectionModel().setSelectionMode(mode);//选择模式
  }
  public void valueChanged(TreeSelectionEvent event)
  {

    String node=jTree1.getLastSelectedPathComponent().toString();//得到用户选择项的字符

    if(node.equals("查询"))
    {
      jSplitPane1.remove(jSplitPane1.getRightComponent());//移去右边的页面
      jSplitPane1.setDividerLocation( 130 );
      jSplitPane1.add(new queryPanel(CardID),JSplitPane.RIGHT);
    }else if(node.equals("存款"))
    {
      jSplitPane1.remove(jSplitPane1.getRightComponent());//移去右边的页面
      jSplitPane1.setDividerLocation( 130 );
      jSplitPane1.add(new storePanel(CardID,this),JSplitPane.RIGHT);
    }else if(node.equals("取款"))
    {
      jSplitPane1.remove(jSplitPane1.getRightComponent());//移去右边的页面
      jSplitPane1.setDividerLocation( 130 );
      jSplitPane1.add(new getPanel(CardID,this),JSplitPane.RIGHT);
    }else if(node.equals("信用卡系统"))
    {
      jSplitPane1.remove(jSplitPane1.getRightComponent());//移去右边的页面
      jSplitPane1.setDividerLocation( 130 );
      jSplitPane1.add(new welcomePanel(),JSplitPane.RIGHT);
    }
  }
  public void setQueryState()
  {
    jSplitPane1.remove(jSplitPane1.getRightComponent());//移去右边的页面
    jSplitPane1.setDividerLocation( 130 );
    jSplitPane1.add(new queryPanel(CardID),JSplitPane.RIGHT);
  }
  public void setCardID(String id)
  {
    this.CardID=id;
  }
  public void setUserName(String un)
  {
    this.userName=un;
  }
  public String getUserName()
  {
    return userName;
  }
  String userName;
  String CardID=null;
}
