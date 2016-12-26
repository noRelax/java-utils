import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class Etymon extends JInternalFrame{
	
	private Hint parent;
	private JButton btnCelean;
	private JLabel lblword;
	private JPanel p4;
	private JPanel p3;
	private JPanel p2;
	private JTextField txtMSword;
	private JLabel lblRight;
	private JTextField txtRight;
	private JLabel lblM;
	private JTextField txtError;
	private JLabel lblError;
	private JTextField txtCountWord;
	private JLabel countWord;
	private JTextField txtTime;
	private JLabel lblTime;
	private JPanel p1;
	private JLabel lblt;
	
	private JLabel[] ShowWord;
	public Etymon(Hint parent){
		
		this.parent=parent;
		
		setLayer(7);
        setBounds(142,0,0,0); 
        this.setPreferredSize(new java.awt.Dimension(657, 325));//内部窗体大小
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
		getContentPane().setLayout(null);
        this.setVisible(true);
		{
			lblt = new JLabel();
			getContentPane().add(lblt);
			lblt.setIcon(new ImageIcon(getClass().getClassLoader().getResource(
				"Image/zgt.gif")));
			lblt.setBounds(26, 40, 602, 189);
		}
		{
			p1 = new JPanel();
			getContentPane().add(p1);
			p1.setBounds(-2, -2, 658, 42);
			p1.setBorder(BorderFactory.createTitledBorder(""));
			{
				lblTime = new JLabel();
				p1.add(lblTime);
				lblTime.setText("\u7528\u65f6:");
				lblTime.setPreferredSize(new java.awt.Dimension(30, 14));
				lblTime.setFont(new java.awt.Font("新宋体",0,12));
			}
			{
				txtTime = new JTextField();
				p1.add(txtTime);
				txtTime.setText("00:00:00");
				txtTime.setEditable(false);
				txtTime.setPreferredSize(new java.awt.Dimension(69, 20));
				txtTime.setFont(new java.awt.Font("新宋体",0,12));
				txtTime.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				countWord = new JLabel();
				p1.add(countWord);
				countWord.setText("\u603b\u5b57\u6570:");
				countWord.setFont(new java.awt.Font("新宋体",0,12));
			}
			{
				txtCountWord = new JTextField();
				p1.add(txtCountWord);
				txtCountWord.setText("0");
				txtCountWord.setPreferredSize(new java.awt.Dimension(69, 20));
				txtCountWord.setFont(new java.awt.Font("新宋体",0,12));
				txtCountWord.setHorizontalAlignment(SwingConstants.CENTER);
				txtCountWord.setEditable(false);
			}
			{
				lblRight = new JLabel();
				p1.add(lblRight);
				lblRight.setText("\u6b63\u786e:");
				lblRight.setFont(new java.awt.Font("新宋体",0,12));
			}
			{
				txtRight = new JTextField();
				p1.add(txtRight);
				txtRight.setText("0");
				txtRight.setPreferredSize(new java.awt.Dimension(69, 20));
				txtRight.setFont(new java.awt.Font("新宋体",0,12));
				txtRight.setEditable(false);
				txtRight.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				lblError = new JLabel();
				p1.add(lblError);
				lblError.setText("\u9519\u8bef:");
				lblError.setFont(new java.awt.Font("新宋体",0,12));
			}
			{
				txtError = new JTextField();
				p1.add(txtError);
				txtError.setText("0");
				txtError.setPreferredSize(new java.awt.Dimension(69, 20));
				txtError.setFont(new java.awt.Font("新宋体", 0, 12));
				txtError.setEditable(false);
				txtError.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				lblM = new JLabel();
				p1.add(lblM);
				lblM.setText("\u6bcf\u5206\u949f");
				lblM.setFont(new java.awt.Font("新宋体",0,12));
			}
			{
				txtMSword = new JTextField();
				p1.add(txtMSword);
				txtMSword.setText("0");
				txtMSword.setPreferredSize(new java.awt.Dimension(69, 20));
				txtMSword.setFont(new java.awt.Font("新宋体",0,12));
				txtMSword.setEditable(false);
				txtMSword.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				lblword = new JLabel();
				p1.add(lblword);
				lblword.setText("\u5b57");
				lblword.setFont(new java.awt.Font("新宋体",0,12));
			}
			{
				btnCelean = new JButton();
				p1.add(btnCelean);
				btnCelean.setText("\u79bb\u5f00");
				btnCelean.setFont(new java.awt.Font("新宋体", 0, 12));
				btnCelean.setBounds(579, 236, 63, 21);
				btnCelean.setBackground(new java.awt.Color(255, 255, 255));
				btnCelean.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						exit();
					}
				});
			}
		}
		{
			p2 = new JPanel();
			getContentPane().add(p2);
			p2.setBounds(-2, 229, 658, 28);
			p2.setBorder(BorderFactory.createTitledBorder(""));
		}
		{
			p3 = new JPanel();
			getContentPane().add(p3);
			p3.setBounds(-2, 290, 658, 35);
			p3.setBorder(BorderFactory.createTitledBorder(""));
		}
		{
			//ShowWord=new JLabel[20];
			//for(int i=0;i<20;i++){
				//ShowWord[i].setText("熊");
			//}
			p4 = new JPanel();
			p4.setLayout(null);
			getContentPane().add(p4);
			p4.setBounds(-2, 257, 658, 35);
			p4.setBorder(BorderFactory.createTitledBorder(""));
		}
		this.pack();
	}
	private void exit(){
		this.parent.doDefaultCloseAction();
		this.doDefaultCloseAction();
		PublicData.onChoice=true;
		PublicData.textTag=1;
		System.gc();
	}
}
