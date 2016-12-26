import java.awt.*;
import javax.swing.*;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class Welcome extends JFrame implements Runnable{
	Thread t;
	private LoginGraphics loginGraphics_IL;

	public static void main(String[] args) {
		Welcome inst = new Welcome();
		inst.setVisible(true);
	}
	
	public Welcome() {
		super();
		t=new Thread(this);
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			setUndecorated(true);
//			������Ļ��С
			Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
			//���ͼ��
			setIconImage(Toolkit.getDefaultToolkit().getImage("image/f.gif"));
			setTitle("������ͨv2.1");
			//setLayout(new FlowLayout());
			//���ͼ��
			setSize(443, 155);
			//�����ھ���
			setLocation((screen.width-getWidth())/2,(screen.height-getHeight())/2);
			{
				loginGraphics_IL = new LoginGraphics();
				add(loginGraphics_IL);
				loginGraphics_IL.setBorder(BorderFactory.createCompoundBorder(
					null,
					null));
				loginGraphics_IL.setBackground(new java.awt.Color(192,192,192));
			}
			t.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void run(){
		try{
			Thread.sleep(3000);
			new MainFrame();
			this.setVisible(false);
			t.interrupt();
		}catch(InterruptedException e){}
	}
}
