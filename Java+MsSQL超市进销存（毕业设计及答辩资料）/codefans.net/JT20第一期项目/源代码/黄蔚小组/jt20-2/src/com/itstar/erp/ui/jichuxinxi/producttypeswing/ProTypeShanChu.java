package com.itstar.erp.ui.jichuxinxi.producttypeswing;

import javax.swing.SwingUtilities;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.itstar.erp.dao.baiscinfo.impl.ProTypeDaoImpl;
import com.itstar.erp.util.GetRS;
import com.itstar.erp.util.GetTime;

public class ProTypeShanChu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JComboBox jComboBox = null;
	private JButton okButton = null;
	String value="";  //  @jve:decl-index=0:
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JTextField dw = null;
	private JTextField bh = null;
	private JTextField t = null;
	/**
	 * This method initializes jComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox() {
		if (jComboBox == null) {
			Vector v=new Vector();
			v.add("");
			ResultSet rs=new GetRS().getResultSet("select * from tb_productType_info ");
			try {
				while(rs.next()){
					rs.getInt(1);
					String name=rs.getString(2).trim();
					v.add(name);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				rs.beforeFirst();                                        //游标移动
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			DefaultComboBoxModel model=new DefaultComboBoxModel(v);
			jComboBox = new JComboBox(model);
			jComboBox.setBounds(new Rectangle(300, 24, 147, 47));
			jComboBox.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(e.getStateChange() == ItemEvent.SELECTED){ 
						value=jComboBox.getSelectedItem().toString();
						System.out.println(value);
						if(!value.equals("")){
							System.out.println(value);
							try{
							ResultSet yz=new GetRS().getResultSet("select proTypeID,proTypeDanWei,proTypeCreateTime from tb_productType_info where proTypeName='"+value+"'");
							if(yz.next()){
							System.out.println(value+"3");
			bh.setText("type"+(1000+yz.getInt(1)));
							System.out.println(value+"4");
	   dw.setText(yz.getString(2));
							System.out.println(value+"5");
		String ntime=yz.getString(3);
							String time=new GetTime().format(ntime);
							t.setText(time);
							
						}}catch(Exception e2){
							e2.printStackTrace();
						}
					}
						
						if(value.equals("")){
							bh.setText("");
							dw.setText("");
							t.setText("");
						}
				}
			}});
		}
		return jComboBox;
	}

	/**
	 * This method initializes okButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton();
			okButton.setBounds(new Rectangle(372, 173, 90, 31));
			okButton.setText("确定删除");
			okButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(value.equals("")){
						JOptionPane.showMessageDialog(okButton, "请选择要删除的商品类别");
					}else{
						int confirm=JOptionPane.showConfirmDialog(okButton, "确认要删除此商品类别吗？");
						if(confirm==JOptionPane.YES_OPTION)
						{
					new ProTypeDaoImpl().delete(value);
					JOptionPane.showMessageDialog(okButton, "删除成功");
					dispose();
					
					ProTypeShanChu protypeshanchu=new ProTypeShanChu();
					protypeshanchu.setVisible(true);
					protypeshanchu.setSize(500,300);
					protypeshanchu.setLocationRelativeTo(null);
					}}}
				
			});
		}
		return okButton;
	}

	/**
	 * This method initializes dw	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getDw() {
		if (dw == null) {
			dw = new JTextField();
			dw.setEditable(false);
			dw.setBounds(new Rectangle(171, 136, 198, 22));
		}
		return dw;
	}

	/**
	 * This method initializes bh	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getBh() {
		if (bh == null) {
			bh = new JTextField();
			bh.setEditable(false);
			bh.setBounds(new Rectangle(171, 86, 196, 22));
		}
		return bh;
	}

	/**
	 * This method initializes t	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getT() {
		if (t == null) {
			t = new JTextField();
			t.setEditable(false);
			t.setBounds(new Rectangle(168, 180, 195, 22));
		}
		return t;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ProTypeShanChu thisClass = new ProTypeShanChu();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public ProTypeShanChu() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(500, 300);
		this.setContentPane(getJContentPane());
		this.setTitle("商品类别资料删除");
		this.setLocationRelativeTo(null);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(50, 179, 115, 25));
			jLabel3.setText("商品类别创建时间");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(51, 134, 118, 24));
			jLabel2.setText("商品类别单位");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(51, 84, 120, 25));
			jLabel1.setText("商品类别编号");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(113, 28, 180, 42));
			jLabel.setText("请选择要删除的商品类别");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJComboBox(), null);
			jContentPane.add(getOkButton(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(getDw(), null);
			jContentPane.add(getBh(), null);
			jContentPane.add(getT(), null);
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="219,29"
