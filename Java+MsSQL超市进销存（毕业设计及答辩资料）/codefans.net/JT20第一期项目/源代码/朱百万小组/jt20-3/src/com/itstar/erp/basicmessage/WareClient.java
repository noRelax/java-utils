package com.itstar.erp.basicmessage;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.itstar.erp.bean.Client;
import java.awt.event.KeyEvent;
import java.awt.Point;
import java.awt.Dimension;
import javax.swing.SwingConstants;

public class WareClient extends JFrame {

	private static final long serialVersionUID = 1L;

	private Object[][] cells = null; // @jve:decl-index=0:

	private JPanel jContentPane = null;

	private int width = Toolkit.getDefaultToolkit().getScreenSize().width;

	private int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	private JButton jButton = null;

	private JButton jButton1 = null;

	private JButton jButton2 = null;
	
	private JScrollPane jScrollPane = null;

	public JTable jTable = null;

	private JButton jButton31 = null;
	/**
	 * This method initializes jButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("���ӿͻ�");
			jButton.setSize(new Dimension(97, 27));
			jButton.setLocation(new Point(30, 18));
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					AddClient ad = new AddClient();
					ad.setVisible(true);
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes jButton1
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("ɾ���ͻ�");
			jButton1.setSize(new Dimension(97, 27));
			jButton1.setLocation(new Point(330, 18));
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int row = jTable.getSelectedRow();
					if (row == -1) {
						JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ���ļ�¼");
					}
					if (row != -1) {
						Client gd = new Client();
						
						JOptionPane jop = new JOptionPane();
						int i = jop.showConfirmDialog(jop, "��ȷ��Ҫɾ����", "ȷ�϶Ի���",jop.YES_NO_OPTION);
						if (i == JOptionPane.YES_OPTION) {
							int rowcount = getJTable().getSelectedRow();
						
							gd.setCName(jTable.getValueAt(jTable.getSelectedRow(), 1).toString());
							gd.setCId(Integer.parseInt(getJTable().getValueAt(rowcount, 0).toString()));
							gd.delet();
						} else {
							return;
						}
						jTable.setModel(getTableModel());
					}
				}
			});
		}
		return jButton1;
	}

	/**
	 * This method initializes jButton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setMnemonic(KeyEvent.VK_UNDEFINED);
			jButton2.setLocation(new Point(180, 18));
			jButton2.setSize(new Dimension(97, 27));
			jButton2.setHorizontalAlignment(SwingConstants.CENTER);
			jButton2.setText("�޸���Ϣ");
			jButton2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int rowcount = getJTable().getSelectedRow();

					if (rowcount == -1) {
						JOptionPane j = new JOptionPane();
						j.showMessageDialog(j, "��ѡ��һ�м�¼");
					}
					if (!(rowcount == -1)) {
						UpdClient ads = new UpdClient();
						getJTable().getValueAt(rowcount, 1);
						ads.setVisible(true);
						ads.getJTextField3().setText(getJTable().getValueAt(rowcount, 0)+"");
						ads.getJTextField2().setText((String) getJTable().getValueAt(rowcount, 1));
						ads.getJTextField6().setText((String) getJTable().getValueAt(rowcount, 2));
						ads.getJTextField4().setText((String) getJTable().getValueAt(rowcount, 3));
						ads.getJTextField5().setText((String) getJTable().getValueAt(rowcount, 4));
						ads.getJTextField7().setText((String) getJTable().getValueAt(rowcount, 5));
					}
				}
			});

		}
		return jButton2;
	}
	

		
	/**
	 * This method initializes jScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(3, 63, 590, 300));
			jScrollPane.setViewportView(getJTable());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTable
	 * 
	 * @return javax.swing.JTable
	 */
	private JTable getJTable() {
		if (jTable == null) {
			jTable = new JTable();
			jTable.setBorder(null);
			jTable.setModel(getTableModel());
		}
		return jTable;
	}

	public  TableModel getTableModel() {
		Client gd = new Client();
		List result = gd.queryAll();

		String[] columns = { "�ͻ����", "�ͻ�����","�ͻ���ַ","�ͻ��绰","�����ʺ�","��������" };
		
		cells=new Object[result.size()][6];
		
		for(int i=0;i<result.size();i++){
			cells[i]=(Object[]) result.get(i);
		}
		return new DefaultTableModel(cells, columns);
	}

	public WareClient() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(600, 400);
		this.setContentPane(getJContentPane());
		
		this.setTitle("�ͻ�����");
		this.setResizable(false);
		this.setLocation((width - 600) / 2, (height - 400) / 2);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(getJButton1(), null);
			jContentPane.add(getJButton2(), null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(getJButton31(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jButton31	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton31() {
		if (jButton31 == null) {
			jButton31 = new JButton();
			jButton31.setText("ˢ  ��");
			jButton31.setSize(new Dimension(97, 27));
			jButton31.setLocation(new Point(480, 18));
			jButton31.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					jTable.setModel(getTableModel());
				}
			});
		}
		return jButton31;
	}
	
	

}
