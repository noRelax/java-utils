import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.*;
import javax.swing.*;
import java.awt.*;

import javax.swing.event.*;

public class Editor extends JInternalFrame implements DocumentListener,Runnable{

	Runtime r=Runtime.getRuntime();
	Hint parent;
	
	Document doc;
	StyledDocument styledDoc = new DefaultStyledDocument();
	Thread th;
	
	private int hour=0;//时
	private int minute=0;//分
	private int second=0;//秒
	
	private JLabel lblTime;
	private JTextField txtCount;
	private JLabel lblError;
	private JCheckBox on_off_Hint;
	private JButton btnRight;
	private JButton btnError;
	private JCheckBox faze;
	private JCheckBox grade;
	private JCheckBox on_off_Assistant;
	private JPanel pCollocate;
	private JTextPane txtOriginal;
	private JTextPane txtNewText;
	private JPanel pShowData;
	private JButton btnClose;
	private JLabel lblWord;
	private JTextField txtMW;
	private JLabel lblM;
	private JTextField txtError;
	private JTextField txtRight;
	private JLabel lblRight;
	private JLabel lblCount;
	private JTextField txtTime;
	private JPanel pAttrib;
	
	//记录输入的正确和错误的个数据
	private int rightWord=0;
	private int errorWord=0;
	private int rightCount=0;
	private int errorCount=0;
	private int wordCount=0;
	//临时
	private boolean temp=true;
	

	public Editor(Hint parent){
		
		this.parent=parent;
		
		th=new Thread(this);
		
		
		//组建Editor用户界面....
		setLayer(4);
        setBounds(142,0,0,0); 
        this.setPreferredSize(new java.awt.Dimension(657, 325));//内部窗体大小
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
		getContentPane().setLayout(null);
        this.setVisible(true);
		{
			pAttrib = new JPanel();
			getContentPane().add(pAttrib, new GridBagConstraints(1, 0, 12, 3, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			FlowLayout pAttribLayout = new FlowLayout();
			pAttrib.setBorder(BorderFactory.createTitledBorder(""));
			pAttrib.setLayout(pAttribLayout);
			pAttrib.setBounds(-2, 33, 658, 42);
			{
				lblTime = new JLabel();
				pAttrib.add(lblTime);
				lblTime.setText("\u7528\u65f6:");
				lblTime.setFont(new java.awt.Font("新宋体", 0, 12));
			}
			{
				txtTime = new JTextField(8);
				pAttrib.add(txtTime);
				txtTime.setText("00:00:00");
				txtTime.setEditable(false);
				txtTime.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				lblCount = new JLabel();
				pAttrib.add(lblCount);
				lblCount.setText("\u603b\u5b57\u6570:");
				lblCount.setFont(new java.awt.Font("新宋体", 0, 12));
			}
			{
				txtCount = new JTextField();
				pAttrib.add(txtCount);
				txtCount.setColumns(6);
				txtCount.setText("0");
				txtCount.setEditable(false);
				txtCount.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				lblRight = new JLabel();
				pAttrib.add(lblRight);
				lblRight.setText("\u6b63\u786e:");
				lblRight.setFont(new java.awt.Font("新宋体", 0, 12));
			}
			{
				txtRight = new JTextField();
				pAttrib.add(txtRight);
				txtRight.setColumns(4);
				txtRight.setText("0");
				txtRight.setEditable(false);
				txtRight.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				lblError = new JLabel();
				pAttrib.add(lblError);
				lblError.setText("\u9519\u8bef:");
				lblError.setFont(new java.awt.Font("新宋体", 0, 12));
			}
			{
				txtError = new JTextField();
				pAttrib.add(txtError);
				txtError.setColumns(4);
				txtError.setText("0");
				txtError.setEditable(false);
				txtError.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				lblM = new JLabel();
				pAttrib.add(lblM);
				lblM.setText("\u6bcf\u5206\u949f");
				lblM.setFont(new java.awt.Font("新宋体", 0, 12));
			}
			{
				txtMW = new JTextField();
				pAttrib.add(txtMW);
				txtMW.setColumns(4);
				txtMW.setText("0");
				txtMW.setEditable(false);
				txtMW.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				lblWord = new JLabel();
				pAttrib.add(lblWord);
				lblWord.setText("\u4e2a\u5b57");
				lblWord.setFont(new java.awt.Font("新宋体", 0, 12));
			}
			{
				btnClose = new JButton();
				pAttrib.add(btnClose);
				btnClose.setText("\u79bb\u5f00");
				btnClose.setIcon(new ImageIcon(getClass().getClassLoader()
					.getResource("Image/leave.gif")));
				btnClose.setFont(new java.awt.Font("新宋体", 0, 12));
				btnClose.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						exitFram();
					}
				});
			}
		}
		{
			pShowData = new JPanel();
			getContentPane().add(
				pShowData,
				new GridBagConstraints(
					1,
					1,
					12,
					10,
					0.0,
					0.0,
					GridBagConstraints.CENTER,
					GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 0),
					0,
					0));
			GridLayout pShowDataLayout = new GridLayout(2, 1);
			pShowDataLayout.setHgap(5);
			pShowDataLayout.setVgap(5);
			pShowDataLayout.setColumns(1);
			pShowDataLayout.setRows(2);
			pShowData.setLayout(pShowDataLayout);
			pShowData.setBounds(5, 82, 644, 231);
			{
				txtOriginal = new JTextPane(styledDoc);
				pShowData.add(txtOriginal);
				txtOriginal.setPreferredSize(new java.awt.Dimension(600, 0));
				txtOriginal.setEditable(false);
			}
			{
				txtNewText = new JTextPane();
				pShowData.add(txtNewText);
				txtNewText.setPreferredSize(new java.awt.Dimension(600, 0));
				Document doc=txtNewText.getDocument();
				doc.addDocumentListener(this);
				
			}
		}
		{
			pCollocate = new JPanel();
			getContentPane().add(
				pCollocate,
				new GridBagConstraints(
					1,
					11,
					12,
					2,
					0.0,
					0.0,
					GridBagConstraints.SOUTH,
					GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 0),
					0,
					0));
			FlowLayout pCollocateLayout = new FlowLayout();
			pCollocate.setBorder(BorderFactory.createTitledBorder(""));
			pCollocate.setLayout(pCollocateLayout);
			pCollocate.setBounds(-2, -2, 658, 35);
			{
				on_off_Hint = new JCheckBox();
				pCollocate.add(on_off_Hint);
				on_off_Hint.setText("\u5173\u95ed\u5b57\u6bcd\u63d0\u793a");
				on_off_Hint.setSelected(true);
				on_off_Hint.setFont(new java.awt.Font("新宋体",0,12));
			}
			{
				on_off_Assistant = new JCheckBox();
				pCollocate.add(on_off_Assistant);
				on_off_Assistant.setText("\u5173\u95ed\u6c49\u5b57\u52a9\u624b");
				on_off_Assistant.setSelected(true);
				on_off_Assistant.setFont(new java.awt.Font("新宋体",0,12));
			}
			{
				grade = new JCheckBox();
				pCollocate.add(grade);
				grade.setText("\u79bb\u5f00\u65f6\u662f\u5426\u663e\u793a\u6210\u7ee9");
				grade.setSelected(true);
				grade.setFont(new java.awt.Font("新宋体",0,12));
			}
			{
				faze = new JCheckBox();
				pCollocate.add(faze);
				faze.setText("\u804a\u5929\u7cfb\u7edf\u5f00\u542f\u65f6\u663e\u793a\u81ea\u5df1\u7684\u72b6\u6001");
				faze.setSelected(true);
				faze.setFont(new java.awt.Font("新宋体",0,12));
			}
			{
				btnError = new JButton();
				pCollocate.add(btnError);
				//btnError.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Image/Error.gif")));
				btnError.setBackground(new java.awt.Color(255,255,255));
				btnError.setToolTipText("\u9519\u8bef\u6587\u5b57\u7528\u7ea2\u8272\u8868\u793a");
				btnError.setText("×");
				btnError.setPreferredSize(new java.awt.Dimension(47, 17));
				btnError.setFont(new java.awt.Font("新宋体",1,12));
				btnError.setForeground(new java.awt.Color(219,32,36));
			}
			{
				btnRight = new JButton();
				pCollocate.add(btnRight);
				//btnRight.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Image/Right.gif")));
				btnRight.setBackground(new java.awt.Color(255,255,255));
				btnRight.setToolTipText("\u6b63\u786e\u6587\u5b57\u7528\u84dd\u8272\u8868\u793a");
				btnRight.setText("\u221a");
				btnRight.setFont(new java.awt.Font("新宋体",1,12));
				btnRight.setPreferredSize(new java.awt.Dimension(47, 17));
				btnRight.setForeground(new java.awt.Color(0,0,255));
			}
			
			txtOriginal.setText(DistillText.getData(PublicData.Text,PublicData.textTag));
			//PublicData.key=txtOriginal.getText().substring(0,1);
			Empty empty=new Empty();
			empty.start();
			if(txtOriginal.getText().length()!=0)
			{
				if(PublicData.Lang.equals("English")){
					PublicData.key=txtOriginal.getText().substring(0,1);
					this.parent.repaint();
				}
				if(PublicData.Lang.equals("Chinese")){
					PublicData.key="";
				}
			}
		}
        this.pack();
        txtNewText.requestFocus();   
        th.start();
	}
	
	 //判断正确和错误的字.并改变它们的颜色
	 public void chDocs(int xLen,int yLen,String xStr,String yStr)
	{
	 	SimpleAttributeSet attrSet=new SimpleAttributeSet(); 
	 	rightWord=0;
	 	errorWord=0;
	 	for(int i=1;i<=xLen;i++)
	 	{
	 		if(i<=yLen){//数据验证
	 			if(xStr.substring(i-1,i).equals(yStr.substring(i-1,i))){
	 				rightWord++;
	 				StyleConstants.setForeground(attrSet,Color.BLUE);
	 			}
	 			else{
	 				errorWord++;
	 				StyleConstants.setForeground(attrSet,Color.RED);
	 			}
	 			StyleConstants.setUnderline(attrSet,true);
	 		}
	 		else{
	 			StyleConstants.setForeground(attrSet,Color.BLACK);
	 			StyleConstants.setUnderline(attrSet,false);
	 		}
	 		styledDoc.setCharacterAttributes(i-1,1,attrSet,true);
   		}
	 	
	 	txtRight.setText((rightWord+rightCount)+"");
	 	txtError.setText((errorWord+errorCount)+"");
	 	int c=0;
	 	for(int i=0;i<minute;i++){
	 		c=c+60;
	 	}
	 	c=c+second;
	 	double s=(double)c/60;
	 	txtMW.setText(""+Math.round(((double)Integer.parseInt(txtRight.getText()))/s));
    }

    //实现DocumentListener所有的方法..用于监听输入文本...
    public void changedUpdate(DocumentEvent e){
    	if(PublicData.Lang.equals("English")){
    		PublicData.key=txtOriginal.getText().substring(txtNewText.getText().length(),
	    			txtNewText.getText().length()+1);
    		this.parent.repaint();
		}
		if(PublicData.Lang.equals("Chinese")){
			PublicData.key="";
		}
    }
    public void insertUpdate(DocumentEvent e){
    	if(PublicData.Lang.equals("English")){
    		try{
	    		PublicData.key=txtOriginal.getText().substring(txtNewText.getText().length(),
		    			txtNewText.getText().length()+1);
    		}catch(StringIndexOutOfBoundsException str){}
    		this.parent.repaint();
		}
		if(PublicData.Lang.equals("Chinese")){
			PublicData.key="";
		}
    }

    public void removeUpdate(DocumentEvent e){
    	if(PublicData.Lang.equals("English")){
    		PublicData.key=txtOriginal.getText().substring(txtNewText.getText().length(),
	    			txtNewText.getText().length()+1);
    		this.parent.repaint();
		}
		if(PublicData.Lang.equals("Chinese")){
			PublicData.key="";
		}
    }
    
    //数据验证
    private void dataValidate(){
	    chDocs(txtOriginal.getText().length(),txtNewText.getText()
	    		.length(),txtOriginal.getText(),txtNewText.getText());
	    if(txtOriginal.getText().length()>txtNewText.getText().length()){
	    	if(PublicData.Lang.equals("English")){
	    		PublicData.key=txtOriginal.getText().substring(txtNewText.getText().length(),
		    			txtNewText.getText().length()+1);
			}
			if(PublicData.Lang.equals("Chinese")){
				PublicData.key="";
			}
	    	txtCount.setText((txtNewText.getText().length()+wordCount)+"");
	    }
	    else{
	    	wordCount=wordCount+txtOriginal.getText().length();
	    	rightCount=rightCount+rightWord;
	    	errorCount=errorCount+errorWord;
	    	txtOriginal.setText(DistillText.getData(PublicData.Text,PublicData.textTag));
	    		
	    	if(PublicData.Lang.equals("English")){
				PublicData.key=txtOriginal.getText().substring(0,1);
				this.parent.repaint();
			}
			if(PublicData.Lang.equals("Chinese")){
				PublicData.key="";
				this.parent.repaint();
			}
	    }
    }
    
    //退出时所要关闭的窗体
	private void exitFram(){
		PublicData.key="";
		PublicData.Text="";
		this.parent.repaint();
		this.parent.doDefaultCloseAction();
		this.doDefaultCloseAction();
		PublicData.onChoice=true;
		PublicData.textTag=1;
		r.gc();
		System.gc();
	}
	
	//计时器
	public  void run(){
		try{
			while(true){
				Thread.sleep(1000);
				second++;
				if(second==60){
					second=0;
					minute++;
					if(minute==60){
						minute=0;
						hour++;
						if(hour==1000){
							second=0;
							minute=0;
							hour=0;
						}
					}
				}
				txtTime.setText(((hour<=9)?"0":"")+hour+":"+((minute<=9)?"0":"")
						+minute+":"+((second<=9)?"0":"")+second);
			}
		}
		catch (InterruptedException e){}
	}
	
	class Empty extends Thread{
		public void run(){
			while(true){
				try{
					Thread.sleep(100);
					dataValidate();
					if(txtNewText.getText().length()>=txtOriginal.getText().length()){
						txtNewText.setText("");
					}
				}
				catch(InterruptedException e){}
			}
		}
	}
}

