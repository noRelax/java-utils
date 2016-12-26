import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.io.*;
import javax.swing.*;

public class QueryCoding extends JInternalFrame{
	private JTextField txtWord;
	private JButton btnExit;
	private JButton btnHelp;
	private JPanel p2;
	private JTextField txtCode2;
	private JLabel lbl2;
	private JTextField txtCode1;
	private JLabel lbl1;
	private JPanel p1;
	private JButton btnQuery;
	private JLabel lblQword;
	public QueryCoding(){
		setLayer(5);
	    ///setBounds(142,326,0,0); 
	    this.setPreferredSize(new java.awt.Dimension(231, 203));
	    //((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
	    this.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
	    this.setVisible(true);
		getContentPane().setLayout(null);
	    this.setBounds((800-231)/2+140/2, (550-203)/2, 231, 203);
		{
			txtWord = new JTextField();
			getContentPane().add(txtWord);
			txtWord.setBounds(85, 5, 28, 21);
			txtWord.addKeyListener(new KeyListener() {
				public void keyPressed(KeyEvent e) {}
				public void keyReleased(KeyEvent e){}
				public void keyTyped(KeyEvent e){
					if(txtWord.getText().length()!=0 && e.getKeyChar()==java.awt.event.KeyEvent.VK_ENTER){
						queryWord();
					}
				}
			});
		}
		{
			lblQword = new JLabel();
			getContentPane().add(lblQword);
			lblQword.setText("查询的汉字:");
			lblQword.setBounds(11, 10, 77, 14);
			lblQword.setFont(new java.awt.Font("新宋体",0,12));
		}
		{
			btnQuery = new JButton();
			getContentPane().add(btnQuery);
			btnQuery.setText("查询=回车");
			btnQuery.setBounds(122, 5, 98, 21);
			btnQuery.setMnemonic(java.awt.event.KeyEvent.VK_ENTER);
			btnQuery.setFont(new java.awt.Font("新宋体",0,12));
			btnQuery.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					if(txtWord.getText().length()!=0){
						queryWord();
						txtWord.setFocusable(true);
					}
				}
			});
		}
		{
			p1 = new JPanel();
			getContentPane().add(p1);
			p1.setBounds(10, 33, 210, 42);
			p1.setBorder(BorderFactory.createTitledBorder(""));
			{
				lbl1 = new JLabel();
				p1.add(lbl1);
				lbl1.setText("\u4e94\u7b14:");
				lbl1.setFont(new java.awt.Font("新宋体",0,12));
			}
			{
				txtCode1 = new JTextField();
				p1.add(txtCode1);
				txtCode1.setPreferredSize(new java.awt.Dimension(57, 20));
				txtCode1.setEditable(false);
			}
			{
				lbl2 = new JLabel();
				p1.add(lbl2);
				lbl2.setText("\u62fc\u97f3:");
				lbl2.setFont(new java.awt.Font("新宋体",0,12));
			}
			{
				txtCode2 = new JTextField();
				p1.add(txtCode2);
				txtCode2.setPreferredSize(new java.awt.Dimension(61, 20));
				txtCode2.setEditable(false);
			}
		}
		{
			p2 = new SplitMap();
			getContentPane().add(p2);
			p2.setBounds(6, 82, 217, 70);
			p2.setFont(new java.awt.Font("新宋体",0,12));
		}
		{
			btnHelp = new JButton();
			getContentPane().add(btnHelp);
			btnHelp.setText("\u5e2e\u52a9");
			btnHelp.setBounds(8, 159, 91, 21);
			btnHelp.setFont(new java.awt.Font("新宋体",0,12));
		}
		{
			btnExit = new JButton();
			getContentPane().add(btnExit);
			btnExit.setText("\u9000\u51fa");
			btnExit.setBounds(122, 159, 98, 21);
			btnExit.setFont(new java.awt.Font("新宋体",0,12));
			btnExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					Exit();
				}
			});
		}
	    this.pack();
	}
	
	private void queryWord(){
		System.gc();
		PublicData.SplitMapName=txtWord.getText().substring(0,1)+".gif";
		this.p2.repaint();
		System.gc();
		if((("code/wb.db"==null) || ("code/wb.db".length()==0)) || (("code/py.db"==null) || ("code/py.db".length()==0)))
			return;
		File f;
		FileReader in=null;
		int len;
		String s="";
		String string=txtWord.getText().substring(0,1);
		try{
			f=new File("code/wb.db");
			in=new FileReader(f);
			char[] buffer=new char[1100];
			while((len=in.read(buffer))!=-1){
				s=new String(buffer,0,len);
				if(s.indexOf(string)!=-1){
					String str=s.substring(s.indexOf(string),s.indexOf(string)+string.length()+4);
					txtCode1.setText(str.substring(1,str.length()));
					break;
				}
			}
		}
		catch(IOException e){
			System.out.println(e);
		}
		finally{
			try{
				if(in!=null)
					in.close();
			}
			catch(IOException e){}
		}
		in=null;
		len=0;
		s="";
		try{
			f=new File("code/py.db");
			in=new FileReader(f);
			char[] buffer=new char[7000];
			while((len=in.read(buffer))!=-1){
				s=new String(buffer,0,len);
				if(s.indexOf(string)!=-1){
					String str=s.substring(s.indexOf(string),s.indexOf(string)+string.length()+6);
					txtCode2.setText(str.substring(1,str.length()));
					break;
				}
			}
		}
		catch(IOException e){
			System.out.println(e);
		}
		finally{
			try{
				if(in!=null)
					in.close();
			}
			catch(IOException e){}
		}
		System.gc();
	}
	
	private void Exit(){
		PublicData.onChoice=true;
		PublicData.SplitMapName="null.gif";
		this.doDefaultCloseAction();
		System.gc();
	}
}
