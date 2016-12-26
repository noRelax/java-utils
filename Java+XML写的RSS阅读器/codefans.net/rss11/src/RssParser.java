/*
Application运行入口
java RssParser

classname RssParser

名称：RSS解析器
Version 1.0

Date 2006-06-03
Down www.codefans.net

CopyRight
清华大学数学科学系
数32班
王元涛
2003012142
*/
import java.awt.event.*;
import java.awt.*;
import java.applet.*;
import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.net.URL;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.util.*;
import javax.swing.table.*;
import org.w3c.dom.*;
import org.apache.crimson.tree.XmlDocument;
import java.io.*; 
import javax.xml.parsers.*;
import javax.swing.plaf.multi.MultiTreeUI;

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////表现层
public class RssParser extends Applet{//主类
	//全局对象
	static RssParser rssParser;
	static Frame frame;
	static PanelTree pTree;
	static PanelTable pTable;
	static Label status;
	
	//初始化布局
	public void init(){
		setLayout(new BorderLayout());
		pTable = new PanelTable();
		pTree = new PanelTree(pTable);
		status = new Label("Status:");
		add("West", pTree);
		add("Center",pTable);
		add("South",status);
		status.setText("inited");
	}
	
	//程序入口
	public static void main(String[] args){
		frame = new Frame("RssParser");
		rssParser = new RssParser();
		rssParser.init();
		
		frame.addWindowListener(new WindowAdapter()//匿名内部类
		{
			public void windowClosing(WindowEvent e) {//关闭窗口
				System.exit(0);
			}
		}
		);

		frame.add("Center", rssParser);
		frame.setSize(1024, 700);
		frame.show();
	}
}

class PanelTree extends Panel implements MouseListener{//左侧的树

	//全局变量
	public JTree tree;
	public MenuActions popp;
	public RSSNode top;
	PanelTable targetTable;
	
	//构造方法初始化布局
	public PanelTree(PanelTable targetTable){
		this.targetTable=targetTable;
		//添加菜单对象
		popp=new MenuActions(this.tree);
		add(popp);
		//添加树
		JScrollPane jsp=creatTree();
	//	jsp.setBounds(new Rectangle(0,0,300,650));
		add(jsp);
	}
	
	//创建树
	public JScrollPane creatTree(){
		//根节点为RSSNode的对象
		top = new RSSNode("MyRSS_root");
		top.type=0;
		try{
			//根据频道定义文件创建树内容
			XmlProcessor.opmlRead(top,"opml.xml");
		}catch(Exception e){
			RssParser.status.setText(e.getMessage());
		}
		tree = new JTree(top);
		//监听鼠标在树上的动作
		tree.addMouseListener(this);

		//返回带自动滚动条的树
		return new JScrollPane(tree,
		ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
		ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	}
	public void mouseClicked(MouseEvent e){
			TreePath selPath = tree.getPathForLocation(e.getX(),e.getY());
				if(selPath!=null){
					//返回点中的对象
					Object o=selPath.getLastPathComponent();
					//如果是左键点击
					if(e.getButton()==e.BUTTON1){
						//状态栏输出RSS的URL
						if(((RSSNode)o).url!=null)
							RssParser.status.setText(((RSSNode)o).url.toString());
						else{
							RssParser.status.setText("feed://about:_root");
						}
					}
					//如果是右键
					if(e.getButton()==e.BUTTON3){
						//弹出菜单
						popp.curNode=(RSSNode)o;
						popp.add(popp.popupMenu1);
						popp.popupMenu1.removeAll();
						//添加菜单项
						if(popp.curNode.type==0){
							popp.popupMenu1.add(popp.menuItem1);
							popp.popupMenu1.add(popp.menuItem4);
						}else{
							popp.popupMenu1.add(popp.menuItem2);
							popp.popupMenu1.add(popp.menuItem3);
						}
						//菜单位置与事件发生位置偏差修正，真正显示
						popp.popupMenu1.show(popp,e.getX(),e.getY()-200);
						//重绘
						popp.repaint();
					}
				}
	}	
	public void mouseReleased(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
}

class PanelTable extends Panel{//右侧的表

	//全局变量
	public JTable table;
	public Vector row;
	public DefaultTableModel tableModel;
	String[] tableHeads = {"标题","时间","摘要","来源"};
	
	//构造表
	public PanelTable(){
		tableModel = new DefaultTableModel();
		Vector tableHeadName = new Vector();
		//写表头
		for (int i = 0; i < tableHeads.length; i++) {
			tableHeadName.add(tableHeads[i]);
		}
		tableModel.setDataVector(new Vector(),tableHeadName);
		//创建表
		table = new JTable(tableModel);
		//设置表外观
		table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		table.setPreferredScrollableViewportSize(new Dimension(700,600));
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.setSurrendersFocusOnKeystroke(false);
	//	table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setRowHeight(20);
		//为表加入滚动条
		JScrollPane jsp2=new JScrollPane(table,
		ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
		ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(jsp2);
	}
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////交互层

class MenuActions extends JPanel implements ActionListener{//菜单

	//全局变量
	public String title;
	public RSSNode curNode;//关键变量
	JTree tree;
	public PopupMenu popupMenu1 = new PopupMenu();
	public MenuItem menuItem1 = new MenuItem();
	public MenuItem menuItem2 = new MenuItem();
	public MenuItem menuItem3 = new MenuItem();
	public MenuItem menuItem4 = new MenuItem();

	//构造菜单和菜单项
	public MenuActions(JTree tree) {
		this.tree=tree;
		this.setLayout(null);

		menuItem1.setLabel("添加RSS");
		menuItem2.setLabel("删除RSS");
		menuItem3.setLabel("显示RSS");
		menuItem4.setLabel("显示全部RSS");
		menuItem1.addActionListener(this);
		menuItem2.addActionListener(this);
		menuItem3.addActionListener(this);
		menuItem4.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("添加RSS"))addRss(e);
		if(e.getActionCommand().equals("删除RSS"))delRss(e);
		if(e.getActionCommand().equals("显示RSS"))showRss(e);
		if(e.getActionCommand().equals("显示全部RSS"))showAll(e);
	}


	void addRss(ActionEvent e){//添加RSS
		//清空表
		RssParser.pTable.tableModel.setRowCount(0);
		//弹出对话框，要求输入RSS的URL，程序自动获取其名称
		String url=JOptionPane.showInputDialog("Please input RSS Url:(and then the name will be given)");
		if(url!=null){
			String name="New rssSite";
			RSSNode tmp=new RSSNode(name);
			try{
				//字符串到URL对象的转换
				tmp.url=new URL(url);
				//设置为临时类型
				tmp.type=2;
				curNode.add(tmp);
				//加入后自动更新
				XmlProcessor.rssRead(tmp,tree);
			}
			catch(MalformedURLException ex){
				RssParser.status.setText(ex.getMessage());
			}catch(Exception ex2){
				RssParser.status.setText(ex2.getMessage());
			}
		}
	}

	void delRss(ActionEvent e){//删除RSS
		RssParser.pTable.tableModel.setRowCount(0);
		int i=JOptionPane.NO_OPTION;
		try{//确认删除
			i=JOptionPane.showConfirmDialog(RssParser.frame,
			"Are you sure to delete "+curNode.name+"?",
			"Confirmer",
			JOptionPane.YES_NO_OPTION);
		}catch(Exception ex){}
		if(i==JOptionPane.YES_OPTION){
			RssParser.status.setText(curNode.name+" 已经删除");
			curNode.removeFromParent();
			try{
				RssParser.pTree.tree.updateUI();
			}catch(Exception ec){}
			try{
				//更新到频道定义文件
				XmlProcessor.opmlWrite(RssParser.pTree.tree,"opml.xml");
			}catch(Exception ex2){
				RssParser.status.setText(ex2.getMessage());
			}
		}
	}
	void showRss(ActionEvent e){//显示RSS
		RssParser.pTable.tableModel.setRowCount(0);
		XmlProcessor.rssRead(curNode,RssParser.pTree.tree);
	}
	void showAll(ActionEvent e){//显示全部RSS
		RssParser.pTable.tableModel.setRowCount(0);
		XmlProcessor.rssReadAll(RssParser.pTree.tree);
	}
	
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////行为层

class XmlProcessor{//聚合操作XML的方法，方便统一调用
	//opml方法保证树和频道定义文件同构
	static void opmlWrite(JTree tree,String filename){
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;

		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException pce) {
			RssParser.status.setText(pce.getMessage());
		}

		Document doc =  db.newDocument();
		Element root = doc.createElement("opml");
		Element head = doc.createElement("head");
		Element body = doc.createElement("body");
		
		root.setAttribute("version","1.1");
		doc.appendChild(root);
		root.appendChild(head);
		root.appendChild(body);

		DefaultTreeModel treeModel=(DefaultTreeModel)tree.getModel();
		RSSNode treeModelRoot=(RSSNode)treeModel.getRoot();
		int len=treeModel.getChildCount(treeModelRoot);
		for(int i=0;i<len;i++){
			RSSNode t=(RSSNode)treeModel.getChild(treeModelRoot,i);
			Element outline=doc.createElement("outline");
			outline.setAttribute("text" , t.name);
			outline.setAttribute("xmlUrl" , t.url.toString());
			body.appendChild(outline);
		}

		try{
			FileOutputStream outStream = new FileOutputStream(filename);
			OutputStreamWriter outWriter = new OutputStreamWriter(outStream);
			((XmlDocument) doc).write(outWriter, "GB2312");
			outWriter.close();
			outStream.close();
		}catch(Exception ex){
			RssParser.status.setText(ex.getMessage());
		}

	}
	static void opmlRead(RSSNode top,String filename){
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException pce) {
			RssParser.status.setText(pce.getMessage());
		}
		Document doc = null;
		try {
			doc = db.parse(filename);
			Element root = doc.getDocumentElement();
			NodeList chs = root.getElementsByTagName("body");
			Element body = (Element) chs.item(0);
			NodeList items = body.getElementsByTagName("outline");
			for (int i = 0; i < items.getLength(); i++) {
				Element R=(Element) items.item(i);
				RSSNode	rss= new RSSNode(R.getAttribute("text"));
				try{
					rss.url=new URL(R.getAttribute("xmlUrl"));
				}catch(Exception e){}
				rss.type=1;
				top.add(rss);
			}
		} catch (DOMException dom) {
			RssParser.status.setText(dom.getMessage());
		} catch (Exception ioe) {
			RssParser.status.setText(ioe.getMessage());
		}
	}
	static void rssRead(RSSNode curNode,JTree tree){
		curNode.setUserObject("("+curNode.name+")");
		try{
			RssParser.pTree.tree.updateUI();
		}catch(Exception ec){}
		RssParser.status.setText(curNode.name+" updating......");
		RssThread rt = new RssThread(curNode,tree);
		rt.start();
	//	RssParser.pTable.table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	}
	static void rssReadAll(JTree tree){
		DefaultTreeModel treeModel=(DefaultTreeModel)tree.getModel();
		RSSNode treeModelRoot=(RSSNode)treeModel.getRoot();
		int len=treeModel.getChildCount(treeModelRoot);
		for(int i=0;i<len;i++){
			RSSNode t=(RSSNode)treeModel.getChild(treeModelRoot,i);
			t.setUserObject("("+t.name+")");
			try{
				tree.updateUI();
			}catch(Exception ec){}
			RssThread rt = new RssThread(t,tree);
			rt.start();
		}
	}
}

class RSSNode extends DefaultMutableTreeNode{//扩展树节点类为RSS频道定义节点
	public int type;//0=root;1=rss;2=temp
	public String name;
	public URL url;
	public RSSNode(String s){
		super(s);
		this.name=s;
	}
}

class RssThread extends Thread{//扩展线程类为RSS更新线程
	RSSNode curNode;
	JTree tree;
	public RssThread(RSSNode curNode,JTree tree){
		super();
		this.curNode=curNode;
		this.tree=tree;
	}
	public void run(){
		URLConnection hpCon=null;
		try{//使用URL连接网络
			hpCon=curNode.url.openConnection();
		//	hpCon.connect();
		}catch(IOException ex){
			RssParser.status.setText(ex.getMessage());
		}
		InputStream inputStream=null;
		try{
			hpCon.connect();	
			inputStream=hpCon.getInputStream();//;//curNode.url.openStream()
		/*	BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
			String tmp;
			while((tmp=reader.readLine())!=null)System.out.println(tmp);*/
			
			
			//通过流读RSS所在异地xml文件
			readXMLFile(inputStream);
		}catch (IOException ex){
			RssParser.status.setText(ex.getMessage());
		}catch(Exception ex2){
			RssParser.status.setText(ex2.getMessage());
		}

		RssParser.pTable.table.setModel(RssParser.pTable.tableModel);

		//对输出结果作出响应
		if(RssParser.pTable.tableModel.getRowCount()>1)
			RssParser.status.setText(curNode.name+" done!");
		else
			RssParser.status.setText(curNode.url+" is not ready!");
	}
	
	//Rss解析的核心方法，适用xml文档定义模型XML DOM
	public void readXMLFile(InputStream inputStream) throws Exception {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;

		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException pce) {
			RssParser.status.setText(pce.getMessage());
		}
		Document doc = null;
		try {
			doc = db.parse(inputStream);
		} catch (DOMException dom) {
			RssParser.status.setText(dom.getMessage());
		} catch (IOException ioe) {
			RssParser.status.setText(ioe.getMessage());
		}
		Vector cell;
		Vector row=new Vector();
		
		Element root = doc.getDocumentElement();
		NodeList chs = root.getElementsByTagName("channel");//rss2.0
		Element ch = (Element) chs.item(0);
		Element _title = (Element)ch.getElementsByTagName("title").item(0);
		curNode.name=((Text)_title.getFirstChild()).toString();
		//更新树中节点名称为频道名称
		curNode.setUserObject(curNode.name);
		try{
		//	Thread.currentThread().yield();
		/////////////////////
		/////////////////////
		RssParser.pTree.tree.updateUI();
		//	tree.updateUI();////此处抛出的异常竟然不能捕获!
		/////////////////////
		/////////////////////
		}catch(NullPointerException ep){
		}catch(InternalError eu){
		}catch(Exception ec){}

		//获取频道消息列表
		NodeList items = ch.getElementsByTagName("item");

		//遍历列表
		for (int i = 0; i < items.getLength(); i++) {
			cell=new Vector();
			
			Element R=(Element) items.item(i);
			NodeList title = R.getElementsByTagName("title");
			if (title.getLength() == 1) {
				Element e = (Element) title.item(0);
				Text t = (Text) e.getFirstChild();
				cell.add(t);
			}else{cell.add("");}
			NodeList time = R.getElementsByTagName("pubDate");
			if (time.getLength() == 1) {
				Element e = (Element) time.item(0);
				Text t = (Text) e.getFirstChild();
				cell.add(t);
			}else{
				time = R.getElementsByTagName("dc:date");
				if (time.getLength() == 1) {
					Element e = (Element) time.item(0);
					Text t = (Text) e.getFirstChild();
					cell.add(t);
				}else{cell.add("");}
			}
			NodeList description = R.getElementsByTagName("description");
			if (description.getLength() == 1) {
				Element e = (Element) description.item(0);
				Text t = (Text) e.getFirstChild();
				cell.add(t);
			}else{cell.add("");}

			//将取出的 标题、时间、摘要放入向量
			cell.add(curNode.name);
			//向表中压栈
			RssParser.pTable.tableModel.insertRow(0,cell);
		}
		//更新频道定义文件
		XmlProcessor.opmlWrite(RssParser.pTree.tree,"opml.xml");
	}
}
