import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PracticeMode implements ActionListener,MouseListener {
	int time;
	Card[][] card;
	boolean[][] isSelected;
	Timer t;
	int cardSelected=0;
	JButton b1=new JButton(new ImageIcon("image/Find.png"));
	JButton b2=new JButton(new ImageIcon("image/Reset.png"));
	JButton b3=new JButton(new ImageIcon("image/Return.png"));
	FindExpression fe=new FindExpression();
	JFrame f=new JFrame("24点游戏-练习模式");
	Test mainMenu;

	public PracticeMode(Test mainMenu) {
		this.mainMenu=mainMenu;
		JLayeredPane l=f.getLayeredPane();
		t=new Timer(2, this);
		JLabel bg=new JLabel(new ImageIcon("image/Practice.jpg"));
		bg.setBounds(0,0,800,600);
		card=new Card[26][2];
		isSelected=new boolean[26][2];
		time=0;

		l.add(bg, new Integer(0));
		l.add(b1, new Integer(1));
		l.add(b2, new Integer(1));
		l.add(b3, new Integer(1));
		b1.setBorderPainted(false);
		b1.setContentAreaFilled(false);
		b1.setRolloverIcon(new ImageIcon("image/FindS.png"));
		b1.setBounds(450,300,160,40);
		b1.addMouseListener(this);
		b2.setBorderPainted(false);
		b2.setContentAreaFilled(false);
		b2.setRolloverIcon(new ImageIcon("image/ResetS.png"));
		b2.setBounds(500,350,160,40);
		b2.addMouseListener(this);
		b3.setBorderPainted(false);
		b3.setContentAreaFilled(false);
		b3.setRolloverIcon(new ImageIcon("image/ReturnS.png"));
		b3.setBounds(550,400,160,40);
		b3.addMouseListener(this);
		for (int i=0; i<card.length; i++) {
			for (int j=0; j<2; j++) {
				isSelected[i][j]=false;
				card[i][j]=new Card(i+1-(i/13)*13,j*2+i/13);
				l.add(card[i][j], new Integer(200+i+j*13));
				card[i][j].setBounds(new Rectangle(100+i, 100+j*100, 71, 96));
				card[i][j].setVisible(true);
			}
		}

		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800,600);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		t.start();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==t) {
			t.stop();
			if (time==15*(card.length-1)) {
				for (int i=0; i<card.length; i++) {
					for (int j=0; j<2; j++) {
						card[i][j].addMouseListener(this);
					}
				}
				return;
			}
			time++;
			for (int i=time/15+1; i<card.length; i++) {
				for (int j=0; j<2; j++) {
					card[i][j].setBounds(new Rectangle(100+time, 100+j*100, 71, 96));
				}
			}
			t.setInitialDelay((int)Math.pow((time+1)/100,2)+2);
			t.restart();
		}
	}

	public void mouseClicked(MouseEvent e) {
		/** 寻找答案 */
		if (e.getSource()==b1 && cardSelected==4) {
			int[] number=new int[4];
			int count=0;
			for (int i=0; i<card.length; i++) {
				for (int j=0; j<2; j++) {
					if (isSelected[i][j]) {
						number[count++]=card[i][j].getNumber();
					}
				}
			}
			fe.setNumbers(number);
			showMessage(fe.getExpression());
			return;
		}
		/** 重置 */
		if (e.getSource()==b2) {
			for (int i=0; i<card.length; i++) {
				for (int j=0; j<2; j++) {
					card[i][j].setBounds(new Rectangle(99+i*15, 100+j*100, 71, 96));
					isSelected[i][j]=false;
				}
			}
			cardSelected=0;
			return;
		}
		/** 返回主菜单 */
		if (e.getSource()==b3) {
			mainMenu.setVisible(true);
			f.dispose();
			//f.setVisible(false);
		}
		/** 牌的选择 */
		for (int i=0; i<card.length; i++) {
			for (int j=0; j<2; j++) {
				if (e.getSource()==card[i][j]) {
					if (isSelected[i][j]) {
						card[i][j].setBounds(new Rectangle(99+i*15, 100+j*100, 71, 96));
						isSelected[i][j]=false;
						cardSelected--;
					} else if (cardSelected<4) {
						card[i][j].setBounds(new Rectangle(99+i*15, 75+j*100, 71, 96));
						isSelected[i][j]=true;
						cardSelected++;
					}
					break;
				}
			}
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

	public void showMessage(String[] output) {
		for (int i=0; i<output.length; i++) {
					JOptionPane.showMessageDialog(f,output[i],"24点游戏-练习模式-结果",JOptionPane.PLAIN_MESSAGE,new ImageIcon("image\\message.gif"));
		}
	}

}
