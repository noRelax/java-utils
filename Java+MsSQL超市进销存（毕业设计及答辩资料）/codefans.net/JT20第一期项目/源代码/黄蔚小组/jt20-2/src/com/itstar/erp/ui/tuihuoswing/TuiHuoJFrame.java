package com.itstar.erp.ui.tuihuoswing;

import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

import com.itstar.erp.dao.kucun.KuCunDaoImpl;
import com.itstar.erp.dao.sell.SellDaoImpl;
import com.itstar.erp.dao.tuihuo.TuiHuoDaoImpl;
import com.itstar.erp.util.GetRS;
import com.itstar.erp.util.GetTime;
import com.itstar.erp.vo.sell.SellBean;
import com.itstar.erp.vo.tuihuo.TuiHuoBean;

public class TuiHuoJFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private JLabel jLabel5 = null;
	private JLabel jLabel6 = null;
	private JLabel jLabel7 = null;
	private JLabel jLabel8 = null;
	private JLabel jLabel9 = null;
	private JLabel jLabel10 = null;
	private JLabel jLabel11 = null;
	private JButton resetButton = null;
	private JButton okButton = null;
	private JTextField xssjT = null;
	private JTextField pronameT = null;
	private JTextField xsslT = null;
	private JTextField khmcT = null;
	private JTextField thbhT = null;
	private JTextField thsjT = null;
	private JComboBox xsbhC = null;
	private JTextField xsjjT = null;
	private JTextArea thyyT = null;
	private JTextField xsywyT = null;
	private JTextField thslT = null;
	private JComboBox ywyC = null;
	String xsvalue="��ѡ��";  //  @jve:decl-index=0:
	String ywyvalue="��ѡ��";  //  @jve:decl-index=0:
	Map<String,Integer> sellbhidmap=new TreeMap<String,Integer>();  //  @jve:decl-index=0:
	String thdate=new GetTime().getTime("");  //  @jve:decl-index=0:
	String date=new GetTime().getTime();
	/**
	 * This method initializes resetButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getResetButton() {
		if (resetButton == null) {
			resetButton = new JButton();
			resetButton.setBounds(new Rectangle(395, 410, 89, 26));
			resetButton.setText("����");
			resetButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					xsbhC.setSelectedIndex(0);
					thyyT.setText("");
					thslT.setText("");
					ywyC.setSelectedIndex(0);
				}
			});
		}
		return resetButton;
	}

	/**
	 * This method initializes okButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton();
			okButton.setBounds(new Rectangle(514, 410, 110, 28));
			okButton.setText("ȷ���˻�");
			okButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(xsvalue.equals("��ѡ��")||ywyvalue.equals("��ѡ��")){
						JOptionPane.showMessageDialog(okButton, "���������ѡ��");
					}else{
						
						int sellid=sellbhidmap.get(xsvalue);           //���۱��
						SellBean bean=new TuiHuoDaoImpl().getSellBean(sellid);
						xssjT.setText(new GetTime().format(bean.getSellDateTime()));//����ʱ��
						int proid=bean.getProID();                               //��Ʒ���
						String proname=new SellDaoImpl().getproName(proid);       //��Ʒ����
						int sellacount=bean.getSellAcount();                         //��������
						int kehuid=bean.getKehuID();                                    //�ͻ����
						String kehuname=new TuiHuoDaoImpl().getkehuName(kehuid);//�ͻ�����
						double sellprice=bean.getSellPrice();                      //���ۼ۸�
						int ywyid=bean.getYwyID();                                 //ҵ��Ա���
						String ywyname=new TuiHuoDaoImpl().getywyName(ywyid);         //ҵ��Ա����
						
						
						
						String remark=thyyT.getText().trim();
						String sl=thslT.getText().trim();
						if(remark.equals("")||sl.equals("")){
							JOptionPane.showMessageDialog(okButton, "�˻�ԭ�� �� �˻����� ����Ϊ�� ��");
						}else{
							try{
							int tuihuosl=Integer.parseInt(sl);
							if(tuihuosl<=0){
								JOptionPane.showMessageDialog(okButton, "�˻�������������� !");
							}else if(tuihuosl>sellacount){
								JOptionPane.showMessageDialog(okButton, "�˻��������������۵�����������  !");
							}else{
								TuiHuoBean thbean=new TuiHuoBean();
								thbean.setSellID(sellid);
								thbean.setThTime(date);
								thbean.setThRemark(remark);
								thbean.setYwyID(ywyid);
								thbean.setThAcount(tuihuosl);
								int kucun=new TuiHuoDaoImpl().getkucun(proid);
								int left=kucun+tuihuosl;                              //���ʣ��
								int sellleft=sellacount-tuihuosl;                     //��������ʣ��
								
								
								new SellDaoImpl().update(sellid, sellleft);             //�������۱�
								
								new KuCunDaoImpl().update(proid, left);                //���¿��
								
								new TuiHuoDaoImpl().insert(thbean);                    //�����˻���
								
								
								
								JOptionPane.showMessageDialog(okButton, "�˻��ɹ� ����");
								dispose();
								
								TuiHuoJFrame tuihuojframe=new TuiHuoJFrame();
								tuihuojframe.setSize(700,500);
								tuihuojframe.setTitle("�˻�����");
								tuihuojframe.setLocationRelativeTo(null);
								tuihuojframe.setVisible(true);
								
							}}catch(NumberFormatException e1){
								JOptionPane.showMessageDialog(okButton, "�˻���������Ϊ���� !");
							}
						}
					}
				}
			});
		}
		return okButton;
	}

	/**
	 * This method initializes xssjT	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getXssjT() {
		if (xssjT == null) {
			xssjT = new JTextField();
			xssjT.setEditable(false);
			xssjT.setBounds(new Rectangle(456, 80, 199, 22));
		}
		return xssjT;
	}

	/**
	 * This method initializes pronameT	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getPronameT() {
		if (pronameT == null) {
			pronameT = new JTextField();
			pronameT.setEditable(false);
			pronameT.setBounds(new Rectangle(180, 136, 176, 22));
		}
		return pronameT;
	}

	/**
	 * This method initializes xsslT	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getXsslT() {
		if (xsslT == null) {
			xsslT = new JTextField();
			xsslT.setEditable(false);
			xsslT.setBounds(new Rectangle(458, 135, 199, 22));
		}
		return xsslT;
	}

	/**
	 * This method initializes khmcT	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getKhmcT() {
		if (khmcT == null) {
			khmcT = new JTextField();
			khmcT.setEditable(false);
			khmcT.setBounds(new Rectangle(185, 188, 177, 22));
		}
		return khmcT;
	}

	/**
	 * This method initializes thbhT	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getThbhT() {
		if (thbhT == null) {
			thbhT = new JTextField();
			ResultSet rs=new GetRS().getResultSet("select max(thID) from tb_tuihuo_info");
			int thid=0;
			try {
				if(rs.next()){
					thid=rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			thbhT.setText("TH"+thdate+"_"+(1000+thid));
			thbhT.setEditable(false);
			thbhT.setBounds(new Rectangle(178, 21, 181, 22));
		}
		return thbhT;
	}

	/**
	 * This method initializes thsjT	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getThsjT() {
		if (thsjT == null) {
			thsjT = new JTextField();
			thsjT.setText(date);
			thsjT.setEditable(false);
			thsjT.setBounds(new Rectangle(456, 24, 193, 22));
		}
		return thsjT;
	}

	/**
	 * This method initializes xsbhC	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getXsbhC() {
		if (xsbhC == null) {
			
			String field1="sellID";
			String field2="sellDateTime";
			String table="tb_sell_info";
			List<Integer> thidlist=new TuiHuoDaoImpl().getIntList(field1, table);
			List selltimelist=new TuiHuoDaoImpl().getStringList(field2, table);
			Vector v=new Vector();
			v.add("��ѡ��");
			String str;
			
			for(int i=0;i<thidlist.size();i++){
				str="XS"+new GetTime().toformat(selltimelist.get(i).toString())+"_"+(1000+(int)thidlist.get(i));
				v.add(str);
				sellbhidmap.put(str, (int)thidlist.get(i));
			}
			System.out.println(sellbhidmap);
			DefaultComboBoxModel model=new DefaultComboBoxModel(v);
			xsbhC = new JComboBox(model);
			xsbhC.setBounds(new Rectangle(180, 80, 175, 27));
			xsbhC.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(e.getStateChange() == ItemEvent.SELECTED){ 
						xsvalue=xsbhC.getSelectedItem().toString().trim();
						if(!xsvalue.equals("��ѡ��")){
							int sellid=sellbhidmap.get(xsvalue);
							SellBean bean=new TuiHuoDaoImpl().getSellBean(sellid);
							xssjT.setText(new GetTime().format(bean.getSellDateTime()));
							int proid=bean.getProID();
							String proname=new SellDaoImpl().getproName(proid);
							pronameT.setText(proname);
							xsslT.setText(""+bean.getSellAcount());
							int kehuid=bean.getKehuID();
							String kehuname=new TuiHuoDaoImpl().getkehuName(kehuid);
							khmcT.setText(kehuname);
							xsjjT.setText(""+bean.getSellPrice());
							int ywyid=bean.getYwyID();
							String ywyname=new TuiHuoDaoImpl().getywyName(ywyid);
							xsywyT.setText(ywyname);
						}
						if(xsvalue.equals("��ѡ��")){
							xssjT.setText("");
							pronameT.setText("");
							xsslT.setText("");
							khmcT.setText("");
							xsjjT.setText("");
							xsywyT.setText("");
						}
					}
				}
			});
		}
		return xsbhC;
	}

	/**
	 * This method initializes xsjjT	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getXsjjT() {
		if (xsjjT == null) {
			xsjjT = new JTextField();
			xsjjT.setEditable(false);
			xsjjT.setBounds(new Rectangle(455, 192, 204, 22));
		}
		return xsjjT;
	}

	/**
	 * This method initializes thyyT	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getThyyT() {
		if (thyyT == null) {
			thyyT = new JTextArea();
			thyyT.setBounds(new Rectangle(186, 241, 171, 131));
		}
		return thyyT;
	}

	/**
	 * This method initializes xsywyT	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getXsywyT() {
		if (xsywyT == null) {
			xsywyT = new JTextField();
			xsywyT.setEditable(false);
			xsywyT.setBounds(new Rectangle(454, 231, 204, 22));
		}
		return xsywyT;
	}

	/**
	 * This method initializes thslT	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getThslT() {
		if (thslT == null) {
			thslT = new JTextField();
			thslT.setBounds(new Rectangle(459, 287, 202, 22));
		}
		return thslT;
	}

	/**
	 * This method initializes ywyC	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getYwyC() {
		if (ywyC == null) {
			String field="ywyName";
			String table="tb_yewuyuan_info";
			List<String> list=new TuiHuoDaoImpl().getStringList(field, table);
			Vector v=new Vector();
			v.add("��ѡ��");
			for(int i=0;i<list.size();i++){
				v.add(list.get(i));
			}
			DefaultComboBoxModel model=new DefaultComboBoxModel(v);
			ywyC = new JComboBox(model);
			ywyC.setBounds(new Rectangle(464, 345, 197, 27));
			ywyC.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(e.getStateChange() == ItemEvent.SELECTED){ 
						ywyvalue=ywyC.getSelectedItem().toString().trim();
					}
				}
			});
		}
		return ywyC;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				TuiHuoJFrame thisClass = new TuiHuoJFrame();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public TuiHuoJFrame() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(700, 500);
		this.setContentPane(getJContentPane());
		this.setTitle("�˻�����");
		this.setLocationRelativeTo(null);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel11 = new JLabel();
			jLabel11.setBounds(new Rectangle(362, 352, 90, 18));
			jLabel11.setText("������");
			jLabel10 = new JLabel();
			jLabel10.setBounds(new Rectangle(80, 238, 104, 25));
			jLabel10.setText("�˻�ԭ�򣨱��");
			jLabel9 = new JLabel();
			jLabel9.setBounds(new Rectangle(362, 235, 80, 18));
			jLabel9.setText("���۾�����");
			jLabel8 = new JLabel();
			jLabel8.setBounds(new Rectangle(363, 193, 78, 18));
			jLabel8.setText("��Ʒ���ۼ۸�");
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(92, 191, 91, 18));
			jLabel7.setText("�ͻ�����");
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(359, 290, 90, 21));
			jLabel6.setText("�˻�����");
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(369, 134, 81, 27));
			jLabel5.setText("��Ʒ��������");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(361, 75, 85, 31));
			jLabel4.setText("��Ʒ����ʱ��");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(89, 133, 84, 26));
			jLabel3.setText("��Ʒ����");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(362, 22, 84, 23));
			jLabel2.setText("�˻�ʱ��");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(83, 80, 89, 24));
			jLabel1.setText("���۱��");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(83, 20, 88, 26));
			jLabel.setText("�˻����");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(jLabel4, null);
			jContentPane.add(jLabel5, null);
			jContentPane.add(jLabel6, null);
			jContentPane.add(jLabel7, null);
			jContentPane.add(jLabel8, null);
			jContentPane.add(jLabel9, null);
			jContentPane.add(jLabel10, null);
			jContentPane.add(jLabel11, null);
			jContentPane.add(getResetButton(), null);
			jContentPane.add(getOkButton(), null);
			jContentPane.add(getXssjT(), null);
			jContentPane.add(getPronameT(), null);
			jContentPane.add(getXsslT(), null);
			jContentPane.add(getKhmcT(), null);
			jContentPane.add(getThbhT(), null);
			jContentPane.add(getThsjT(), null);
			jContentPane.add(getXsbhC(), null);
			jContentPane.add(getXsjjT(), null);
			jContentPane.add(getThyyT(), null);
			jContentPane.add(getXsywyT(), null);
			jContentPane.add(getThslT(), null);
			jContentPane.add(getYwyC(), null);
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="266,3"
