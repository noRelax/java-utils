package greed_snake;
import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 


public class Snake extends JFrame implements ActionListener, KeyListener, Runnable { 
JMenuBar bar; 
JMenu menu1, menu2,menu3,menu4; 
JMenuItem menuitem1,menuitem2; 
JCheckBoxMenuItem menuitem3,menuitem4,menuitem5; 
JMenuItem menuitem6; 
Toolkit toolkit; 
int i, x, y, z, objectX, objectY, object = 0, growth = 0, time;// bojectX,YΪʳ������,zΪ��ǰ������ 
int difficult = 2; 
int length = 6; 
int m[] = new int[50]; 
int n[] = new int[50]; 
Thread snake = null; 
public Snake(String s) { 
	super(s); 
	setVisible(true); 
	setBounds(100, 100, 400, 400); 
	setResizable(false); 
	Container con = this.getContentPane(); 
	toolkit = getToolkit(); 
	bar = new JMenuBar(); 
	menu1 = new JMenu("��Ϸ"); 
	menu2 = new JMenu("�Ѷ�ѡ��"); 
	menu3 = new JMenu("��������"); 
	menu4 = new JMenu("��Ȩ"); 
	menuitem1 = new JMenuItem("���¿�ʼ"); 
	menuitem2 = new JMenuItem("�˳���Ϸ"); 
	menuitem3 = new JCheckBoxMenuItem("��"); 
	menuitem4 = new JCheckBoxMenuItem("��ͨ"); 
	menuitem5 = new JCheckBoxMenuItem("����"); 
	menuitem6 = new JMenuItem("����"); 
	menu1.add(menuitem1); 
	menu1.add(menuitem2); 
	menu2.add(menuitem3); 
	menu2.add(menuitem4); 
	menu2.add(menuitem5); 
	menu4.add(menuitem6); 
	bar.add(menu1); 
	bar.add(menu2); 
	bar.add(menu3); 
	bar.add(menu4); 
	setJMenuBar(bar); 
	menuitem1.addActionListener(this); 
	addKeyListener(this); 
	menuitem2.addActionListener(this); 
	menuitem3.addActionListener(this); 
	menuitem4.addActionListener(this); 
	menuitem5.addActionListener(this); 
	menuitem6.addActionListener(this); 
	validate(); 
} 

public void actionPerformed(ActionEvent e) { 
	if (e.getSource() == menuitem1) 
	{ 
		length = 6; 
		if (snake == null) { 
		snake = new Thread(this); 
		snake.start(); 
		} 
		else if (snake != null) 
		{ 
			snake = null; 
			snake = new Thread(this); 
			snake.start(); 
		} 
	} 

	if (e.getSource() == menuitem2) 
	{ 
		System.exit(0); 
	} 
	
	if (e.getSource() == menuitem6)
	{ 
		JOptionPane.showMessageDialog(this, 
		"�ӱ߿Ƽ���ѧ\nͨ�Ź���רҵ �Ӽ���\n2005��8��\nQQ:183148696", "������Ϣ", 
		JOptionPane.WARNING_MESSAGE); 
	} 
} 

void isDead() // �ж���Ϸ�Ƿ�����ķ��� 
{ 
	if (z == 4) 
	x = x + 10; 
	else if (z == 3) 
	x = x - 10; 
	else if (z == 2) 
	y = y + 10; 
	else if (z == 1) 
	y = y - 10; 
	if (x < 0 || x > 390 || y < 50 || y > 390) 
	snake = null; 
	for (i = 1; i < length; i++) 
	if (m[i] == x && n[i] == y) 
	snake = null; 
} 

public void upgrowth() // ���߳Ե�����ʱ�ķ��� 
{ 
	if (length < 50) 
	length++; 
	growth--; 
	time = time - 10; 
	reform(); 
} 

public void run() // ʵ���߳�Runnable�ӿ� 
{ 
	time = 500; 
	for (i = 0; i <= length - 1; i++)
	{ 
		m[i] = 90 - i * 10; 
		n[i] = 60; 
	} 
	x = m[0]; 
	y = n[0]; 
	z = 4; 
	while (snake != null) 
	{ 
		check(); 
		try
		{ 
			snake.sleep(time); 
		} catch (Exception ee) { 		} 
	} 
} 

public void reform()
{ 
	for (i = length - 1; i > 0; i--) 
	{ 
		m[i] = m[i - 1]; 
		n[i] = n[i - 1]; 
	} 
	if (z == 4) 
	m[0] = m[0] + 10; 
	if (z == 3) 
	m[0] = m[0] - 10; 
	if (z == 2) 
	n[0] = n[0] + 10; 
	if (z == 1) 
	n[0] = n[0] - 10; 
} 

public void check() 
{ 
	isDead(); 
	if (snake != null) 
	{ 
		if (growth == 0) 
			reform(); 
		else 
			upgrowth(); 
		if (x == objectX && y == objectY)
		{ 
			object = 0; 
			growth = 1; 
			toolkit.beep(); 
		}// toolkit.beep()�������� 
		if (object == 0)
		{ 
			object = 1; 
			objectX = (int) Math.floor(Math.random() * 39) * 10; 
			objectY = (int) Math.floor(Math.random() * 29) * 10 + 50; 
		} 
		repaint(); 
	} 
} 

public void paint(Graphics g) 
{ 
	g.setColor(Color.DARK_GRAY); 
	g.fillRect(0, 50, 400, 400); 
	g.setColor(Color.pink); 
	for (i = 0; i <= length - 1; i++)
	{ 
		g.fillRect(m[i], n[i], 10, 10); 
	} 
	g.setColor(Color.green); 
	g.fillRect(objectX, objectY, 10, 10); 
} 

public void keyPressed(KeyEvent e) 
{ 
	if (snake != null) 
	{ 
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{ 
			if (z != 2) 
			{ 
				z = 1; 
				check(); 
			} 
		} 
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) 
		{ 
			if (z != 1)
			{ 
				z = 2; 
				check(); 
			} 
		} 
		else if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{ 
			if (z != 4)
			{ 
				z = 3; 
				check(); 
			} 
		}
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) 
		{ 
			if (z != 3) 
			{ 
				z = 4; 
				check(); 
			} 
		} 
	} 
} 

public void keyTyped(KeyEvent e) { 
} 

public void keyReleased(KeyEvent e) { 
} 

public static void main(String args[]) { 
new Snake("̰����"); 
} 
} 