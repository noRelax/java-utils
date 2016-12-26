import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameMode implements ActionListener,MouseListener {
	Test mainMenu;
	String name="";
	JFrame f=new JFrame("24点游戏-游戏模式");
	JLayeredPane l=f.getLayeredPane();
	JButton b1=new JButton(new ImageIcon("image/Pass.png"));
	JButton b2=new JButton(new ImageIcon("image/Return.png"));
	JButton bO1=new JButton(new ImageIcon("image/+.png"));
	JButton bO2=new JButton(new ImageIcon("image/-.png"));
	JButton bO3=new JButton(new ImageIcon("image/×.png"));
	JButton bO4=new JButton(new ImageIcon("image/÷.png"));
	JButton bO5=new JButton(new ImageIcon("image/(.png"));
	JButton bO6=new JButton(new ImageIcon("image/).png"));
	JButton bO7=new JButton(new ImageIcon("image/=.png"));
	JButton bO8=new JButton("Clear");
	Card[] card=new Card[] {new Card(), new Card(), new Card(), new Card()};
	FindExpression fe=new FindExpression();
	CalculateExpression ce=new CalculateExpression();
	JLabel JLbExpression=new JLabel("");
	String expression;
	JLabel JLbTime=new JLabel("Time : ");
	static Timer timer;
	int time;
	boolean canEnterNumber;

	public GameMode(Test mainMenu) {
		this.mainMenu=mainMenu;
		JLabel bg=new JLabel(new ImageIcon("image/Game.jpg"));
		bg.setBounds(0,0,800,600);

		b1.setBorderPainted(false);
		b1.setContentAreaFilled(false);
		b1.setRolloverIcon(new ImageIcon("image/PassS.png"));
		b1.setBounds(20,530,160,40);
		b1.addMouseListener(this);
		b2.setBorderPainted(false);
		b2.setContentAreaFilled(false);
		b2.setRolloverIcon(new ImageIcon("image/ReturnS.png"));
		b2.setBounds(200,530,160,40);
		b2.addMouseListener(this);
		bO1.setBorderPainted(false);
		bO1.setContentAreaFilled(false);
		bO1.setRolloverIcon(new ImageIcon("image/+S.png"));
		bO1.setBounds(20,490,40,40);
		bO1.addMouseListener(this);
		bO2.setBorderPainted(false);
		bO2.setContentAreaFilled(false);
		bO2.setRolloverIcon(new ImageIcon("image/-S.png"));
		bO2.setBounds(60,490,40,40);
		bO2.addMouseListener(this);
		bO3.setBorderPainted(false);
		bO3.setContentAreaFilled(false);
		bO3.setRolloverIcon(new ImageIcon("image/×S.png"));
		bO3.setBounds(100,490,40,40);
		bO3.addMouseListener(this);
		bO4.setBorderPainted(false);
		bO4.setContentAreaFilled(false);
		bO4.setRolloverIcon(new ImageIcon("image/÷S.png"));
		bO4.setBounds(140,490,40,40);
		bO4.addMouseListener(this);
		bO5.setBorderPainted(false);
		bO5.setContentAreaFilled(false);
		bO5.setRolloverIcon(new ImageIcon("image/(S.png"));
		bO5.setBounds(180,490,40,40);
		bO5.addMouseListener(this);
		bO6.setBorderPainted(false);
		bO6.setContentAreaFilled(false);
		bO6.setRolloverIcon(new ImageIcon("image/)S.png"));
		bO6.setBounds(220,490,40,40);
		bO6.addMouseListener(this);
		bO7.setBorderPainted(false);
		bO7.setContentAreaFilled(false);
		bO7.setRolloverIcon(new ImageIcon("image/=S.png"));
		bO7.setBounds(260,490,40,40);
		bO7.addMouseListener(this);
		bO8.setBorderPainted(false);
		bO8.setContentAreaFilled(false);
		bO8.setBounds(300,490,80,40);
		bO8.addMouseListener(this);
		JLbExpression.setForeground(Color.WHITE);
		JLbExpression.setBounds(40,200,400,50);
		JLbExpression.setFont(new Font("Arial", Font.BOLD, 30));
		JLbTime.setForeground(Color.WHITE);
		JLbTime.setBounds(20,20,400,50);
		JLbTime.setFont(new Font("Arial", Font.BOLD, 30));

		l.add(bg, new Integer(0));
		l.add(b1, new Integer(1));
		l.add(b2, new Integer(1));
		l.add(bO1, new Integer(1));
		l.add(bO2, new Integer(1));
		l.add(bO3, new Integer(1));
		l.add(bO4, new Integer(1));
		l.add(bO5, new Integer(1));
		l.add(bO6, new Integer(1));
		l.add(bO7, new Integer(1));
		l.add(bO8, new Integer(1));
		l.add(JLbExpression, new Integer(1));
		l.add(JLbTime, new Integer(1));
		
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800,600);
		f.setLocationRelativeTo(null);
		while (name.equals("")) {
			name=JOptionPane.showInputDialog(null, "请输入您的昵称", "24点游戏-游戏模式", JOptionPane.QUESTION_MESSAGE);
			if (name==null) {
				mainMenu.setVisible(true);
				return;
			}
		}
		f.setVisible(true);
		start();
	}

	public void start() {
		fe.setNumbers(new int[] {0,0,0,0});
		canEnterNumber=true;
		l.remove(card[0]);
		l.remove(card[1]);
		l.remove(card[2]);
		l.remove(card[3]);
		while (fe.getExpression()[0].equals("无解\n                       第1页 / 共1页")) {
			JLbExpression.setText("");
			expression="";
			for (int i=0; i<4; i++) {
				card[i]=new Card((int)(Math.random()*13+1), (int)(Math.random()*4));
				for (int j=0; j<i; j++) {
					if (card[i].getNumber()==card[j].getNumber() && card[i].getVariety().equals(card[j].getVariety())) {
						continue;
					}
				}
			}
			fe.setNumbers(new int[] {card[0].getNumber(), card[1].getNumber(), card[2].getNumber(), card[3].getNumber()});
		}
		card[0].setBounds(new Rectangle(60, 300, 71, 96));
		card[0].setVisible(true);
		card[1].setBounds(new Rectangle(140, 300, 71, 96));
		card[1].setVisible(true);
		card[2].setBounds(new Rectangle(220, 300, 71, 96));
		card[2].setVisible(true);
		card[3].setBounds(new Rectangle(300, 300, 71, 96));
		card[3].setVisible(true);
		card[0].addMouseListener(this);
		card[1].addMouseListener(this);
		card[2].addMouseListener(this);
		card[3].addMouseListener(this);
		card[0].setEnabled(true);
		card[1].setEnabled(true);
		card[2].setEnabled(true);
		card[3].setEnabled(true);
		l.add(card[0], new Integer(1));
		l.add(card[1], new Integer(1));
		l.add(card[2], new Integer(1));
		l.add(card[3], new Integer(1));
		timer=new Timer(1000, this);
		time=card[0].getTime()+card[1].getTime()+card[2].getTime()+card[3].getTime();
		JLbTime.setForeground(Color.WHITE);
		JLbTime.setText("Time : "+time);
		timer.start();
	}

	public void actionPerformed(ActionEvent e) {
		time-=1;
		JLbTime.setText("Time : "+time);
		if (time==15) {
			JLbTime.setForeground(Color.RED);
		}
		if (time<=0) {
			timeIsUp();
		}
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource()==b1) {
			new RankList().addPlayer(name, -1);
			timer.stop();
			start();
		}
		if (e.getSource()==b2) {
			mainMenu.setVisible(true);
			timer.stop();
			f.dispose();
		}
		if (canEnterNumber) {
			for (int i=0; i<4; i++) {
				if (e.getSource()==card[i]) {
					JLbExpression.setText(JLbExpression.getText()+card[i].getNumber());
					expression+=card[i].getNumber();
					card[i].setEnabled(false);
					canEnterNumber=false;
				}
			}
		}
		if (e.getSource()==bO1) {
			JLbExpression.setText(JLbExpression.getText()+"+");
			expression+="+";
			canEnterNumber=true;
		}
		if (e.getSource()==bO2) {
			JLbExpression.setText(JLbExpression.getText()+"-");
			expression+="-";
			canEnterNumber=true;
		}
		if (e.getSource()==bO3) {
			JLbExpression.setText(JLbExpression.getText()+"×");
			expression+="*";
			canEnterNumber=true;
		}
		if (e.getSource()==bO4) {
			JLbExpression.setText(JLbExpression.getText()+"÷");
			expression+="/";
			canEnterNumber=true;
		}
		if (e.getSource()==bO5) {
			JLbExpression.setText(JLbExpression.getText()+"(");
			expression+="(";
			canEnterNumber=true;
		}
		if (e.getSource()==bO6) {
			JLbExpression.setText(JLbExpression.getText()+")");
			expression+=")";
			canEnterNumber=true;
		}
		if (e.getSource()==bO7 && !card[0].isEnabled() && !card[1].isEnabled() && !card[2].isEnabled() && !card[3].isEnabled()) {
			ce.setExpression(expression);
			canEnterNumber=true;
			if (!ce.isCorrect()) {
				JOptionPane.showMessageDialog(null,"表达式输入有误，请重新输入",
											"24点游戏-游戏模式",JOptionPane.INFORMATION_MESSAGE);
				JLbExpression.setText("");
				expression="";
				card[0].setEnabled(true);
				card[1].setEnabled(true);
				card[2].setEnabled(true);
				card[3].setEnabled(true);
				return;
			}
			timer.stop();
			if (Math.pow(ce.getDoubleResult()-24,2)<0.000001){
				right();
			} else {
				fault();
			}
		}
		if (e.getSource()==bO8) {
			canEnterNumber=true;
			JLbExpression.setText("");
			expression="";
			card[0].setEnabled(true);
			card[1].setEnabled(true);
			card[2].setEnabled(true);
			card[3].setEnabled(true);
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	void right() {
		int score=card[0].getScore()+card[1].getScore()+card[2].getScore()+card[3].getScore();
		JOptionPane.showMessageDialog(null,"回答正确,加上"+score+"分",
											"24点游戏-游戏模式",JOptionPane.PLAIN_MESSAGE, new ImageIcon("image\\right.gif"));
		new RankList().addPlayer(name, score);
		start();
	}

	void fault() {
		JOptionPane.showMessageDialog(null,"回答错误,扣除1分",
											"24点游戏-游戏模式",JOptionPane.PLAIN_MESSAGE, new ImageIcon("image\\fault.gif"));
		new RankList().addPlayer(name, -1);
		start();
	}

	void timeIsUp() {
		timer.stop();
		JOptionPane.showMessageDialog(null,"时间到,扣除1分",
											"24点游戏-游戏模式",JOptionPane.PLAIN_MESSAGE, new ImageIcon("image\\TimeIsUp.gif"));
		new RankList().addPlayer(name, -1);
		start();
	}

}
