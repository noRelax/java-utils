//计算器类



package classsource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;

public class Calculator extends JInternalFrame implements ActionListener
{
	private JTextField displayText=new JTextField();
	private JButton buttonTag=new JButton();
	private JButton buttonBackspace=new JButton("Backspace");
	private JButton buttonCe=new JButton("CE");
	private JButton buttonc=new JButton("C");
	private JButton[] button=new JButton[24];
	private String[] keys={"MC","7","8","9","/","sqrt","MR","4","5","6","*","%",
				"MS","1","2","3","-","1/x","M+","0","+/-",".","+","="};


	private String numStr1="";
	private String numStr2="";
	private String numsave="";

	private char op;
	private boolean firstInput=true;
	private boolean operatorTag1=true;
	private boolean operatorTag2=true;

//	private JMenuBar mb=new JMenuBar();
//	private JMenu editMenu=new JMenu("编辑");
//	private JMenu seeMenu=new JMenu("查看");
//	private JMenu helpMenu=new JMenu("帮助");


	public Calculator(){
		setTitle("计算器");
		setSize(345,265);
		Container pane=getContentPane();
		//displayText.setBackground(Color.blue);
//		editMenu.add("复制");
//		editMenu.add("粘贴");
//		seeMenu.add("标准型");
//		seeMenu.add("科学型");
//		seeMenu.addSeparator();
//		seeMenu.add("数字型");
//		helpMenu.add("帮助主题");
//		helpMenu.addSeparator();
//		helpMenu.add("关于计算器");
//		mb.add(editMenu);
//		mb.add(seeMenu);
//		mb.add(helpMenu);
//		setJMenuBar(mb);
		pane.setLayout(null);

        displayText.setHorizontalAlignment(JTextField.RIGHT);
		displayText.setSize(320,30);
		displayText.setLocation(10,10);
		pane.add(displayText);

		buttonTag.setSize(60,30);
		buttonTag.setLocation(10,50);
		pane.add(buttonTag);

		buttonBackspace.setSize(110,30);
		buttonBackspace.setLocation(90,50);
		buttonBackspace.setForeground(Color.red);
		buttonBackspace.addActionListener(this);
		pane.add(buttonBackspace);

		buttonCe.setSize(65,30);
		buttonCe.setLocation(200,50);
		buttonCe.setForeground(Color.red);
		pane.add(buttonCe);

		buttonc.setSize(65,30);
		buttonc.setLocation(265,50);
		buttonc.setForeground(Color.red);
		buttonc.addActionListener(this);
		pane.add(buttonc);

		int x,y;

		x=10;
		y=85;
		for(int ind=0;ind<24;ind++){
			//button[ind].setFont(new Font("Times New Roman",Font.PLAIN,14));
			button[ind]=new JButton(keys[ind]);
			button[ind].addActionListener(this);
			button[ind].setLocation(x,y);
			if(ind%6==0 || ind%6==5)
				{button[ind].setSize(60,30);
				x=x+60;}
			else
				{button[ind].setSize(50,30);
				x=x+50;}

			if(ind%6==0 || ind%6==4 ||ind%24==23)
				button[ind].setForeground(Color.red);
			else
				button[ind].setForeground(Color.blue);

			pane.add(button[ind]);

			if((ind+1)%6==0)
				{
				x=10;
				y=y+30;
				}
			}
//		this.addWindowListener(new WindowAdapter(){
//			public void windowClosing(WindowEvent e)
//				{
//				System.exit(0);
//				}
//			});
		setVisible(true);
		this.setClosable(true);
		}

	public void actionPerformed(ActionEvent e){
			String resultStr="";

			String str=String.valueOf(e.getActionCommand());
			char ch=str.charAt(0);

			if(str.equals("0")||str.equals("1")||str.equals("2")||str.equals("3")||str.equals("4")||
				str.equals("5")||str.equals("6")||str.equals("7")||str.equals("8")||
				str.equals("9")||str.equals(".")){
						if(firstInput){
							numStr1=numStr1+ch;
							displayText.setText(numStr1);
							}
						else{
							numStr2=numStr2+ch;
							displayText.setText(numStr2);
							}
						}
			else if(str.equals("+")||str.equals("-")||str.equals("*")
						||str.equals("/") & operatorTag2){
						if(numStr2.equals(""))
				        op=ch;
				        else{
				        	resultStr=evaluate();
				        	displayText.setText(resultStr);
				        	numStr1=resultStr;
						    numStr2="";
						    op=ch;
				        	}
						firstInput=false;
						//operatorTag1=false;
						}
			else if(str.equals("=")){
						resultStr=evaluate();
						displayText.setText(resultStr);
						numStr1="";
						numStr2="";
						firstInput=true;
					}
			else if(str.equals("C")){
						displayText.setText("");
						numStr1="";
						numStr2="";
						firstInput=true;
				}
			else if((str.equals("sqrt")|str.equals("1/x")) & !numStr1.equals("")& operatorTag1){
				float A=Float.parseFloat(numStr1);
				float result=0;
				if(str.equals("sqrt"))
					result=(float)Math.sqrt(A);
				if(str.equals("1/x"))
					result=1/A;
				numStr1=Float.toString(result);
				displayText.setText(numStr1);
				firstInput=false;
				//operatorTag2=false;
				}
			else if (str.equals("Backspace")){
				String str1=displayText.getText();
				if(!str1.equals("")){
					displayText.setText(str1.substring(0,str1.length()-1));
				if(!numStr1.equals(""))
					numStr1=displayText.getText();
				else
					numStr2=displayText.getText();
					}
				}
			else if(str.equals("MS")){
				numsave=displayText.getText();
				buttonTag.setText("M");
			    numStr1="";
				}
			else if(str.equals("MC")){
				numsave="";
				buttonTag.setText("");
				}
			else if (str.equals("MR")){
				displayText.setText(numsave);
				}
			else if(str.equals("M+")){

				}
			}

	private String evaluate(){
		//final char beep='\n0007';
		if(!numStr1.equals("") & !numStr2.equals("")){
			float A=Float.parseFloat(numStr1);
			float B=Float.parseFloat(numStr2);
			float result=0;
			switch(op){
					case '+':result=A+B;break;
					case '-':result=A-B;break;
					case '*':result=A*B;break;
					case '/':result=A/B;break;
					}
			return String.valueOf(result);
			}
		else
			return numStr1;
		}

//	public static void main(String []args){
//		Calculator C=new Calculator();
//		C.setResizable(false);
//		}
}
