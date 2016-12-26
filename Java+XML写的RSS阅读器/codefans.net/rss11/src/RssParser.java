/*
Application�������
java RssParser

classname RssParser

���ƣ�RSS������
Version 1.0

Date 2006-06-03
Down www.codefans.net

CopyRight
�廪��ѧ��ѧ��ѧϵ
��32��
��Ԫ��
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

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////���ֲ�
public class RssParser extends Applet{//����
	//ȫ�ֶ���
	static RssParser rssParser;
	static Frame frame;
	static PanelTree pTree;
	static PanelTable pTable;
	static Label status;
	
	//��ʼ������
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
	
	//�������
	public static void main(String[] args){
		frame = new Frame("RssParser");
		rssParser = new RssParser();
		rssParser.init();
		
		frame.addWindowListener(new WindowAdapter()//�����ڲ���
		{
			public void windowClosing(WindowEvent e) {//�رմ���
				System.exit(0);
			}
		}
		);

		frame.add("Center", rssParser);
		frame.setSize(1024, 700);
		frame.show();
	}
}

class PanelTree extends Panel implements MouseListener{//������

	//ȫ�ֱ���
	public JTree tree;
	public MenuActions popp;
	public RSSNode top;
	PanelTable targetTable;
	
	//���췽����ʼ������
	public PanelTree(PanelTable targetTable){
		this.targetTable=targetTable;
		//��Ӳ˵�����
		popp=new MenuActions(this.tree);
		add(popp);
		//�����
		JScrollPane jsp=creatTree();
	//	jsp.setBounds(new Rectangle(0,0,300,650));
		add(jsp);
	}
	
	//������
	public JScrollPane creatTree(){
		//���ڵ�ΪRSSNode�Ķ���
		top = new RSSNode("MyRSS_root");
		top.type=0;
		try{
			//����Ƶ�������ļ�����������
			XmlProcessor.opmlRead(top,"opml.xml");
		}catch(Exception e){
			RssParser.status.setText(e.getMessage());
		}
		tree = new JTree(top);
		//������������ϵĶ���
		tree.addMouseListener(this);

		//���ش��Զ�����������
		return new JScrollPane(tree,
		ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
		ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	}
	public void mouseClicked(MouseEvent e){
			TreePath selPath = tree.getPathForLocation(e.getX(),e.getY());
				if(selPath!=null){
					//���ص��еĶ���
					Object o=selPath.getLastPathComponent();
					//�����������
					if(e.getButton()==e.BUTTON1){
						//״̬�����RSS��URL
						if(((RSSNode)o).url!=null)
							RssParser.status.setText(((RSSNode)o).url.toString());
						else{
							RssParser.status.setText("feed://about:_root");
						}
					}
					//������Ҽ�
					if(e.getButton()==e.BUTTON3){
						//�����˵�
						popp.curNode=(RSSNode)o;
						popp.add(popp.popupMenu1);
						popp.popupMenu1.removeAll();
						//��Ӳ˵���
						if(popp.curNode.type==0){
							popp.popupMenu1.add(popp.menuItem1);
							popp.popupMenu1.add(popp.menuItem4);
						}else{
							popp.popupMenu1.add(popp.menuItem2);
							popp.popupMenu1.add(popp.menuItem3);
						}
						//�˵�λ�����¼�����λ��ƫ��������������ʾ
						popp.popupMenu1.show(popp,e.getX(),e.getY()-200);
						//�ػ�
						popp.repaint();
					}
				}
	}	
	public void mouseReleased(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
}

class PanelTable extends Panel{//�Ҳ�ı�

	//ȫ�ֱ���
	public JTable table;
	public Vector row;
	public DefaultTableModel tableModel;
	String[] tableHeads = {"����","ʱ��","ժҪ","��Դ"};
	
	//�����
	public PanelTable(){
		tableModel = new DefaultTableModel();
		Vector tableHeadName = new Vector();
		//д��ͷ
		for (int i = 0; i < tableHeads.length; i++) {
			tableHeadName.add(tableHeads[i]);
		}
		tableModel.setDataVector(new Vector(),tableHeadName);
		//������
		table = new JTable(tableModel);
		//���ñ����
		table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		table.setPreferredScrollableViewportSize(new Dimension(700,600));
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.setSurrendersFocusOnKeystroke(false);
	//	table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setRowHeight(20);
		//Ϊ����������
		JScrollPane jsp2=new JScrollPane(table,
		ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
		ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(jsp2);
	}
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////������

class MenuActions extends JPanel implements ActionListener{//�˵�

	//ȫ�ֱ���
	public String title;
	public RSSNode curNode;//�ؼ�����
	JTree tree;
	public PopupMenu popupMenu1 = new PopupMenu();
	public MenuItem menuItem1 = new MenuItem();
	public MenuItem menuItem2 = new MenuItem();
	public MenuItem menuItem3 = new MenuItem();
	public MenuItem menuItem4 = new MenuItem();

	//����˵��Ͳ˵���
	public MenuActions(JTree tree) {
		this.tree=tree;
		this.setLayout(null);

		menuItem1.setLabel("���RSS");
		menuItem2.setLabel("ɾ��RSS");
		menuItem3.setLabel("��ʾRSS");
		menuItem4.setLabel("��ʾȫ��RSS");
		menuItem1.addActionListener(this);
		menuItem2.addActionListener(this);
		menuItem3.addActionListener(this);
		menuItem4.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("���RSS"))addRss(e);
		if(e.getActionCommand().equals("ɾ��RSS"))delRss(e);
		if(e.getActionCommand().equals("��ʾRSS"))showRss(e);
		if(e.getActionCommand().equals("��ʾȫ��RSS"))showAll(e);
	}


	void addRss(ActionEvent e){//���RSS
		//��ձ�
		RssParser.pTable.tableModel.setRowCount(0);
		//�����Ի���Ҫ������RSS��URL�������Զ���ȡ������
		String url=JOptionPane.showInputDialog("Please input RSS Url:(and then the name will be given)");
		if(url!=null){
			String name="New rssSite";
			RSSNode tmp=new RSSNode(name);
			try{
				//�ַ�����URL�����ת��
				tmp.url=new URL(url);
				//����Ϊ��ʱ����
				tmp.type=2;
				curNode.add(tmp);
				//������Զ�����
				XmlProcessor.rssRead(tmp,tree);
			}
			catch(MalformedURLException ex){
				RssParser.status.setText(ex.getMessage());
			}catch(Exception ex2){
				RssParser.status.setText(ex2.getMessage());
			}
		}
	}

	void delRss(ActionEvent e){//ɾ��RSS
		RssParser.pTable.tableModel.setRowCount(0);
		int i=JOptionPane.NO_OPTION;
		try{//ȷ��ɾ��
			i=JOptionPane.showConfirmDialog(RssParser.frame,
			"Are you sure to delete "+curNode.name+"?",
			"Confirmer",
			JOptionPane.YES_NO_OPTION);
		}catch(Exception ex){}
		if(i==JOptionPane.YES_OPTION){
			RssParser.status.setText(curNode.name+" �Ѿ�ɾ��");
			curNode.removeFromParent();
			try{
				RssParser.pTree.tree.updateUI();
			}catch(Exception ec){}
			try{
				//���µ�Ƶ�������ļ�
				XmlProcessor.opmlWrite(RssParser.pTree.tree,"opml.xml");
			}catch(Exception ex2){
				RssParser.status.setText(ex2.getMessage());
			}
		}
	}
	void showRss(ActionEvent e){//��ʾRSS
		RssParser.pTable.tableModel.setRowCount(0);
		XmlProcessor.rssRead(curNode,RssParser.pTree.tree);
	}
	void showAll(ActionEvent e){//��ʾȫ��RSS
		RssParser.pTable.tableModel.setRowCount(0);
		XmlProcessor.rssReadAll(RssParser.pTree.tree);
	}
	
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////��Ϊ��

class XmlProcessor{//�ۺϲ���XML�ķ���������ͳһ����
	//opml������֤����Ƶ�������ļ�ͬ��
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

class RSSNode extends DefaultMutableTreeNode{//��չ���ڵ���ΪRSSƵ������ڵ�
	public int type;//0=root;1=rss;2=temp
	public String name;
	public URL url;
	public RSSNode(String s){
		super(s);
		this.name=s;
	}
}

class RssThread extends Thread{//��չ�߳���ΪRSS�����߳�
	RSSNode curNode;
	JTree tree;
	public RssThread(RSSNode curNode,JTree tree){
		super();
		this.curNode=curNode;
		this.tree=tree;
	}
	public void run(){
		URLConnection hpCon=null;
		try{//ʹ��URL��������
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
			
			
			//ͨ������RSS�������xml�ļ�
			readXMLFile(inputStream);
		}catch (IOException ex){
			RssParser.status.setText(ex.getMessage());
		}catch(Exception ex2){
			RssParser.status.setText(ex2.getMessage());
		}

		RssParser.pTable.table.setModel(RssParser.pTable.tableModel);

		//��������������Ӧ
		if(RssParser.pTable.tableModel.getRowCount()>1)
			RssParser.status.setText(curNode.name+" done!");
		else
			RssParser.status.setText(curNode.url+" is not ready!");
	}
	
	//Rss�����ĺ��ķ���������xml�ĵ�����ģ��XML DOM
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
		//�������нڵ�����ΪƵ������
		curNode.setUserObject(curNode.name);
		try{
		//	Thread.currentThread().yield();
		/////////////////////
		/////////////////////
		RssParser.pTree.tree.updateUI();
		//	tree.updateUI();////�˴��׳����쳣��Ȼ���ܲ���!
		/////////////////////
		/////////////////////
		}catch(NullPointerException ep){
		}catch(InternalError eu){
		}catch(Exception ec){}

		//��ȡƵ����Ϣ�б�
		NodeList items = ch.getElementsByTagName("item");

		//�����б�
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

			//��ȡ���� ���⡢ʱ�䡢ժҪ��������
			cell.add(curNode.name);
			//�����ѹջ
			RssParser.pTable.tableModel.insertRow(0,cell);
		}
		//����Ƶ�������ļ�
		XmlProcessor.opmlWrite(RssParser.pTree.tree,"opml.xml");
	}
}
