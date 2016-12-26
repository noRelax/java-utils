package com.itstar.erp.ui.searchswing;

import javax.swing.SwingUtilities;
import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import java.awt.CheckboxGroup;
import java.awt.Rectangle;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import com.itstar.erp.ui.searchswing.rukusearch.RuKuAcountSearch;
import com.itstar.erp.ui.searchswing.rukusearch.RuKuESearch;
import com.itstar.erp.ui.searchswing.rukusearch.RuKuEphSearch;
import com.itstar.erp.ui.searchswing.rukusearch.RuKuLiangphSearch;
import com.itstar.erp.ui.searchswing.rukusearch.RuKuTimeSearch;
import com.itstar.erp.ui.searchswing.rukusearch.RuKuZiRanSearch;
import com.itstar.erp.ui.searchswing.sellsearch.SellAcountSearch;
import com.itstar.erp.ui.searchswing.sellsearch.SellESearch;
import com.itstar.erp.ui.searchswing.sellsearch.SellEdxSearch;
import com.itstar.erp.ui.searchswing.sellsearch.SellLiangdxSearch;
import com.itstar.erp.ui.searchswing.sellsearch.SellTimeSearch;
import com.itstar.erp.ui.searchswing.sellsearch.SellZiRanSearch;
import com.itstar.erp.ui.searchswing.tuihuosearch.TuiHuoESearch;
import com.itstar.erp.ui.searchswing.tuihuosearch.TuiHuoEdxSearch;
import com.itstar.erp.ui.searchswing.tuihuosearch.TuiHuoLiangSearch;
import com.itstar.erp.ui.searchswing.tuihuosearch.TuiHuoLiangdxSearch;
import com.itstar.erp.ui.searchswing.tuihuosearch.TuiHuoTimeSearch;
import com.itstar.erp.ui.searchswing.tuihuosearch.TuiHuoZiRanSearch;
import com.itstar.erp.ui.searchswing.tuikusearch.TuiKuESearch;
import com.itstar.erp.ui.searchswing.tuikusearch.TuiKuEdxSearch;
import com.itstar.erp.ui.searchswing.tuikusearch.TuiKuLiangSearch;
import com.itstar.erp.ui.searchswing.tuikusearch.TuiKuLiangdxSearch;
import com.itstar.erp.ui.searchswing.tuikusearch.TuiKuTimeSearch;
import com.itstar.erp.ui.searchswing.tuikusearch.TuiKuZiRanSearch;
import com.itstar.erp.util.GetRS;
import com.itstar.erp.util.GetTime;

public class SearchJFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JTabbedPane jTabbedPane = null;
	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
	private JPanel jPanel2 = null;
	private JPanel jPanel3 = null;
	private JButton sellButton = null;
	private JRadioButton ziranradio = null;
	private JRadioButton sellpriceradio = null;
	private JRadioButton sellacountradio = null;
	String sellvalue="";  //  @jve:decl-index=0:
	String rukuvalue="";  //  @jve:decl-index=0:
	String tuihuovalue="";  //  @jve:decl-index=0:
	String tuikuvalue="";
	private JRadioButton rukuziran = null;
	private JRadioButton rukue = null;
	private JRadioButton rukusellacount = null;
	private JButton rukuButton = null;
	private JRadioButton selltimeradio = null;
	private JTextField from = null;
	private JLabel jLabel = null;
	private JTextField to = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel3 = null;
	private JTextField jTextField = null;
	private JRadioButton selleph = null;
	private JRadioButton sellliangph = null;
	private JRadioButton rukueduoshao = null;
	private JRadioButton rukuliangdaxiao = null;
	private JRadioButton rukutime = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel4 = null;
	private JTextField from1 = null;
	private JTextField to1 = null;
	private JLabel jLabel5 = null;
	private JTextField jTextField1 = null;
	private JLabel jLabel7 = null;
	private JLabel jLabel8 = null;
	private JRadioButton zirantuihuoR = null;
	private JRadioButton tuihuoeR = null;
	private JLabel jLabel6 = null;
	private JRadioButton tuihuoliang = null;
	private JRadioButton tuihuoedxR = null;
	private JRadioButton tuihuoliangdxR = null;
	private JRadioButton tuohuotimeR = null;
	private JLabel jLabel9 = null;
	private JTextField xzsjT = null;
	private JLabel jLabel10 = null;
	private JTextField from2 = null;
	private JTextField to2 = null;
	private JLabel jLabel11 = null;
	private JLabel jLabel12 = null;
	private JButton tuihuoButton = null;
	private JRadioButton tuikuziranR = null;
	private JRadioButton tuikueR = null;
	private JRadioButton tuikuliangR = null;
	private JRadioButton tuikuedxR = null;
	private JRadioButton tuikuliangdxR = null;
	private JRadioButton tuikutimeR = null;
	private JLabel jLabel13 = null;
	private JTextField jTextField2 = null;
	private JLabel jLabel14 = null;
	private JTextField from3 = null;
	private JLabel jLabel15 = null;
	private JTextField to3 = null;
	private JLabel jLabel16 = null;
	private JButton tuikuButton = null;
	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.setBounds(new Rectangle(1, -1, 693, 468));
			jTabbedPane.addTab("销售查询", null, getJPanel(), null);
			jTabbedPane.addTab("退货查询", null, getJPanel1(), null);
			jTabbedPane.addTab("入库查询", null, getJPanel2(), null);
			jTabbedPane.addTab("退库查询", null, getJPanel3(), null);
		}
		return jTabbedPane;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(446, 188, 240, 26));
			jLabel7.setText("格式为  从2000-01-01到2099-12-30");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(474, 160, 71, 18));
			jLabel3.setText("现在时间");
			
			
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(183, 191, 31, 25));
			jLabel1.setText("  从");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(306, 192, 27, 18));
			jLabel.setText("   到");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.add(getOkButton(), null);
			jPanel.add(getZiranradio(), null);
			jPanel.add(getSellpriceradio(), null);
			jPanel.add(getSellacountradio(), null);
			jPanel.add(getSelltimeradio(), null);
			jPanel.add(getFrom(), null);
			jPanel.add(jLabel, null);
			jPanel.add(getTo(), null);
			jPanel.add(jLabel1, null);
			jPanel.add(jLabel3, null);
			jPanel.add(getJTextField(), null);
			jPanel.add(getSelleph(), null);
			jPanel.add(getSellliangph(), null);
			jPanel.add(jLabel7, null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jLabel12 = new JLabel();
			jLabel12.setBounds(new Rectangle(467, 217, 222, 18));
			jLabel12.setText("格式为  从2000-01-01到2099-12-30");
			jLabel11 = new JLabel();
			jLabel11.setBounds(new Rectangle(335, 216, 23, 18));
			jLabel11.setText("到 ");
			jLabel10 = new JLabel();
			jLabel10.setBounds(new Rectangle(206, 214, 35, 24));
			jLabel10.setText("从");
			jLabel9 = new JLabel();
			jLabel9.setBounds(new Rectangle(475, 187, 72, 25));
			jLabel9.setText("现在时间");
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(364, 62, 162, 18));
			jLabel6.setText("退货数量  乘以  商品进价");
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.add(getZirantuihuoR(), null);
			jPanel1.add(getTuihuoeR(), null);
			jPanel1.add(jLabel6, null);
			jPanel1.add(getTuihuoliang(), null);
			jPanel1.add(getTuihuoedxR(), null);
			jPanel1.add(getTuihuoliangdxR(), null);
			jPanel1.add(getTuohuotimeR(), null);
			jPanel1.add(jLabel9, null);
			jPanel1.add(getXzsjT(), null);
			jPanel1.add(jLabel10, null);
			jPanel1.add(getFrom2(), null);
			jPanel1.add(getTo2(), null);
			jPanel1.add(jLabel11, null);
			jPanel1.add(jLabel12, null);
			jPanel1.add(getTuihuoButton(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jLabel8 = new JLabel();
			jLabel8.setBounds(new Rectangle(473, 201, 204, 18));
			jLabel8.setText("格式为  2009-01-01到2099-12-30");
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(390, 171, 64, 18));
			jLabel5.setText("现在时间");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(323, 200, 38, 18));
			jLabel4.setText("到");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(195, 202, 38, 18));
			jLabel2.setText("从");
			jPanel2 = new JPanel();
			jPanel2.setLayout(null);
			jPanel2.add(getRukuziran(), null);
			jPanel2.add(getRukue(), null);
			jPanel2.add(getRukusellacount(), null);
			jPanel2.add(getRukuButton(), null);
			jPanel2.add(getRukueduoshao(), null);
			jPanel2.add(getRukuliangdaxiao(), null);
			jPanel2.add(getRukutime(), null);
			jPanel2.add(jLabel2, null);
			jPanel2.add(jLabel4, null);
			jPanel2.add(getFrom1(), null);
			jPanel2.add(getTo1(), null);
			jPanel2.add(jLabel5, null);
			jPanel2.add(getJTextField1(), null);
			jPanel2.add(jLabel8, null);
		}
		return jPanel2;
	}

	/**
	 * This method initializes jPanel3	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jLabel16 = new JLabel();
			jLabel16.setBounds(new Rectangle(470, 195, 212, 18));
			jLabel16.setText("格式为  2009-01-01到2099-12-30");
			jLabel15 = new JLabel();
			jLabel15.setBounds(new Rectangle(335, 196, 38, 18));
			jLabel15.setText("到");
			jLabel14 = new JLabel();
			jLabel14.setBounds(new Rectangle(201, 196, 38, 18));
			jLabel14.setText("从");
			jLabel13 = new JLabel();
			jLabel13.setBounds(new Rectangle(439, 168, 71, 18));
			jLabel13.setText("现在时间");
			jPanel3 = new JPanel();
			jPanel3.setLayout(null);
			jPanel3.add(getTuikuziranR(), null);
			jPanel3.add(getTuikueR(), null);
			jPanel3.add(getTuikuliangR(), null);
			jPanel3.add(getTuikuedxR(), null);
			jPanel3.add(getTuikuliangdxR(), null);
			jPanel3.add(getTuikutimeR(), null);
			jPanel3.add(jLabel13, null);
			jPanel3.add(getJTextField2(), null);
			jPanel3.add(jLabel14, null);
			jPanel3.add(getFrom3(), null);
			jPanel3.add(jLabel15, null);
			jPanel3.add(getTo3(), null);
			jPanel3.add(jLabel16, null);
			jPanel3.add(getTuikuButton(), null);
		}
		return jPanel3;
	}

	/**
	 * This method initializes sellacountcheck	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */


	/**
	 * This method initializes okButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getOkButton() {
		if (sellButton == null) {
			sellButton = new JButton();
			sellButton.setBounds(new Rectangle(276, 279, 121, 37));
			sellButton.setText("开始查询");
			sellButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if( sellvalue.equals("")){
						JOptionPane.showMessageDialog(sellButton, "请选择查询方式");
					}else{
						if(sellvalue.equals("销售自然")){
							SellZiRanSearch sellZiRanSearchnew =new SellZiRanSearch();
							sellZiRanSearchnew.init();
							//validate();
//							repaint();
						}
						if(sellvalue.equals("销售额")){
							new SellESearch().init();
							repaint();
						}
						if(sellvalue.equals("销售量")){
							new SellAcountSearch().init();
							repaint();
						}
						
						if(sellvalue.equals("盈利")){
							new SellEdxSearch().init();
							repaint();
						}
						if(sellvalue.equals("销售量大小")){
							new SellLiangdxSearch().init();
							repaint();
						}
						
						if(sellvalue.equals("销售时间")){
							String t1=from.getText().trim();
							String t2=to.getText().trim();
							if(t1.equals("")||t2.equals("")){
								
								JOptionPane.showMessageDialog(sellButton, "时间不能为空，请输入时间  格式为：  从 2000-01-01 到 2099-12-30  ");
								
							}else{
								if(t1.matches( "20\\d{1}\\d{1}-[0,1]{1}\\d{1}-[0,1,2,3]{1}\\d{1}" ) && t2.matches("20\\d{1}\\d{1}-[0,1]{1}\\d{1}-[0,1,2,3]{1}\\d{1}")){
									ResultSet rs=new GetRS().getResultSet("select * from tb_sell_info where sellDateTime between '"+t1+"' and  '"+t2+"'");
									if(rs==null){
										JOptionPane.showMessageDialog(sellButton, "未查到内容，时间格式不对 或者 无此时间段信息 ");
									}else{
										new SellTimeSearch().init(t1,t2);
										repaint();
									}
									
								}else{
									JOptionPane.showMessageDialog(sellButton, "时间格式错误，请输入时间  格式为：  从 2000-01-01 到 2099-12-30");
								}
								}
							}
						}
						
					}
				
			});
		}
		return sellButton;
	}

	/**
	 * This method initializes ziranradio	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getZiranradio() {
		if (ziranradio == null) {
			ziranradio = new JRadioButton();
			ziranradio.addItemListener(new MyListener());
			ziranradio.setBounds(new Rectangle(251, 12, 169, 21));
			ziranradio.setText("自然查询");
		}
		return ziranradio;
	}

	/**
	 * This method initializes sellpriceradio	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getSellpriceradio() {
		if (sellpriceradio == null) {
			sellpriceradio = new JRadioButton();
			sellpriceradio.addItemListener(new MyListener());
			sellpriceradio.setBounds(new Rectangle(254, 67, 201, 21));
			sellpriceradio.setText("销售额统计");
		}
		return sellpriceradio;
	}

	/**
	 * This method initializes sellacountradio	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getSellacountradio() {
		if (sellacountradio == null) {
			sellacountradio = new JRadioButton();
			sellacountradio.addItemListener(new MyListener());
			sellacountradio.setBounds(new Rectangle(252, 38, 196, 21));
			sellacountradio.setText("销售量统计");
			
		}
		return sellacountradio;
	}

	/**
	 * This method initializes rukuziran	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getRukuziran() {
		if (rukuziran == null) {
			rukuziran = new JRadioButton();
			rukuziran.addItemListener(new MyListener());
			rukuziran.setBounds(new Rectangle(251, 17, 119, 21));
			rukuziran.setText("自然查询");
		}
		return rukuziran;
	}

	/**
	 * This method initializes rukue	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getRukue() {
		if (rukue == null) {
			rukue = new JRadioButton();
			rukue.addItemListener(new MyListener());
			rukue.setBounds(new Rectangle(250, 51, 183, 21));
			rukue.setText("入库金额统计");
		}
		return rukue;
	}

	/**
	 * This method initializes rukusellacount	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getRukusellacount() {
		if (rukusellacount == null) {
			rukusellacount = new JRadioButton();
            rukusellacount.addItemListener(new MyListener());
			
			rukusellacount.setBounds(new Rectangle(250, 84, 183, 21));
			rukusellacount.setText("入库数量统计");
		}
		return rukusellacount;
	}

	/**
	 * This method initializes rukuButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getRukuButton() {
		if (rukuButton == null) {
			rukuButton = new JButton();
			rukuButton.setBounds(new Rectangle(285, 294, 111, 42));
			rukuButton.setText("开始查询");
			rukuButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if( rukuvalue.equals("")){
						JOptionPane.showMessageDialog(sellButton, "请选择查询方式");
					}else{
						if(rukuvalue.equals("入库自然")){
							new RuKuZiRanSearch().init();
						}
						if(rukuvalue.equals("入库额")){
							new RuKuESearch().init();
						}
						if(rukuvalue.equals("入库量")){
							new RuKuAcountSearch().init();
						}
						if(rukuvalue.equals("入库额排行")){
							new RuKuEphSearch().init();    //除问题了
						}
						if(rukuvalue.equals("入库量排行")){
							new RuKuLiangphSearch().init();
						}
						if(rukuvalue.equals("入库时间")){

							String t1=from1.getText().trim();
							String t2=to1.getText().trim();
							if(t1.equals("")||t2.equals("")){
								
								JOptionPane.showMessageDialog(sellButton, "时间不能为空，请输入时间  格式为：  从 2000-01-01 到 2099-12-30  ");
								
							}else{
								if(t1.matches( "20\\d{1}\\d{1}-[0,1]{1}\\d{1}-[0,1,2,3]{1}\\d{1}" ) && t2.matches("20\\d{1}\\d{1}-[0,1]{1}\\d{1}-[0,1,2,3]{1}\\d{1}")){
									ResultSet rs=new GetRS().getResultSet("select * from tb_ruku_info where rukuDateTime between '"+t1+"' and  '"+t2+"'");
									if(rs==null){
										JOptionPane.showMessageDialog(sellButton, "未查到内容，时间格式不对 或者 无此时间段信息 ");
									}else{
										new RuKuTimeSearch().init(t1,t2);
									}
									
								}else{
									JOptionPane.showMessageDialog(sellButton, "时间格式错误，请输入时间  格式为：  从 2000-01-01 到 2099-12-30");
								}
								}
								
						}
					}
				}
			});
		}
		return rukuButton;
	}

	/**
	 * This method initializes selltimeradio	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getSelltimeradio() {
		if (selltimeradio == null) {
			selltimeradio = new JRadioButton();
			selltimeradio.setBounds(new Rectangle(266, 158, 155, 21));
			selltimeradio.setText("按时间段查询");
			selltimeradio.addItemListener(new MyListener());
			
		}
		return selltimeradio;
	}

	/**
	 * This method initializes from	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getFrom() {
		if (from == null) {
			from = new JTextField();
			from.setBounds(new Rectangle(220, 191, 81, 22));
		}
		return from;
	}

	/**
	 * This method initializes to	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTo() {
		if (to == null) {
			to = new JTextField();
			to.setBounds(new Rectangle(336, 189, 98, 22));
		}
		return to;
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			String time=new GetTime().getTime();
			jTextField.setText(time);
			jTextField.setEditable(false);
			jTextField.setBounds(new Rectangle(555, 157, 79, 22));
		}
		return jTextField;
	}

	/**
	 * This method initializes selleph	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getSelleph() {
		if (selleph == null) {
			selleph = new JRadioButton();
			selleph.setBounds(new Rectangle(263, 122, 156, 21));
			selleph.setText("按盈利多少排行查询");
			selleph.addItemListener(new MyListener());
		}
		return selleph;
	}

	/**
	 * This method initializes sellliangph	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getSellliangph() {
		if (sellliangph == null) {
			sellliangph = new JRadioButton();
			sellliangph.setBounds(new Rectangle(256, 92, 155, 21));
			sellliangph.setText("按销售量大小排行查询");
			sellliangph.addItemListener(new MyListener());
			ButtonGroup group=new ButtonGroup();
			group.add(ziranradio);
			group.add(sellpriceradio);
			group.add(sellacountradio);
			group.add(selltimeradio);
			group.add(selleph);
			group.add(sellliangph);
		}
		return sellliangph;
	}

	/**
	 * This method initializes rukueduoshao	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getRukueduoshao() {
		if (rukueduoshao == null) {
			rukueduoshao = new JRadioButton();
			rukueduoshao.setBounds(new Rectangle(255, 113, 140, 21));
			rukueduoshao.setText("按入库额多少排行");
			rukueduoshao.addItemListener(new MyListener());
		}
		return rukueduoshao;
	}

	/**
	 * This method initializes rukuliangdaxiao	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getRukuliangdaxiao() {
		if (rukuliangdaxiao == null) {
			rukuliangdaxiao = new JRadioButton();
			rukuliangdaxiao.setBounds(new Rectangle(251, 140, 141, 21));
			rukuliangdaxiao.setText("按入库量多少排行");
			rukuliangdaxiao.addItemListener(new MyListener());
			
			
		}
		return rukuliangdaxiao;
	}

	/**
	 * This method initializes rukutime	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getRukutime() {
		if (rukutime == null) {
			rukutime = new JRadioButton();
			rukutime.setBounds(new Rectangle(254, 170, 139, 21));
			rukutime.setText("按时间段查询");
			rukutime.addItemListener(new MyListener());
			ButtonGroup group=new ButtonGroup();
			group.add(rukuziran);
			group.add(rukue);
			group.add(rukusellacount);
			group.add(rukueduoshao);
			group.add(rukuliangdaxiao);
			group.add(rukutime);
			
		}
		return rukutime;
	}

	/**
	 * This method initializes from1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getFrom1() {
		if (from1 == null) {
			from1 = new JTextField();
			from1.setBounds(new Rectangle(242, 200, 75, 22));
		}
		return from1;
	}

	/**
	 * This method initializes to1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTo1() {
		if (to1 == null) {
			to1 = new JTextField();
			to1.setBounds(new Rectangle(367, 199, 98, 22));
		}
		return to1;
	}

	/**
	 * This method initializes jTextField1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
			String time=new GetTime().getTime();
			jTextField1.setText(time);
			jTextField1.setEditable(false);
			jTextField1.setBounds(new Rectangle(470, 167, 86, 22));
		}
		return jTextField1;
	}

	/**
	 * This method initializes zirantuihuoR	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getZirantuihuoR() {
		if (zirantuihuoR == null) {
			zirantuihuoR = new JRadioButton();
			zirantuihuoR.addItemListener(new MyListener());
			zirantuihuoR.setBounds(new Rectangle(240, 29, 143, 21));
			zirantuihuoR.setText("自然查询");
		}
		return zirantuihuoR;
	}

	/**
	 * This method initializes tuihuoeR	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getTuihuoeR() {
		if (tuihuoeR == null) {
			tuihuoeR = new JRadioButton();
			tuihuoeR.setBounds(new Rectangle(238, 62, 106, 21));
			tuihuoeR.addItemListener(new MyListener());
			tuihuoeR.setText("退货金额统计");
		}
		return tuihuoeR;
	}

	/**
	 * This method initializes tuihuoliang	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getTuihuoliang() {
		if (tuihuoliang == null) {
			tuihuoliang = new JRadioButton();
			tuihuoliang.setBounds(new Rectangle(241, 93, 126, 21));
			tuihuoliang.addItemListener(new MyListener());
			tuihuoliang.setText("退货数量统计");
		}
		return tuihuoliang;
	}

	/**
	 * This method initializes tuihuoedxR	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getTuihuoedxR() {
		if (tuihuoedxR == null) {
			tuihuoedxR = new JRadioButton();
			tuihuoedxR.setBounds(new Rectangle(244, 124, 195, 21));
			tuihuoedxR.addItemListener(new MyListener());
			tuihuoedxR.setText("按退货金额大小排行查询");
		}
		return tuihuoedxR;
	}

	/**
	 * This method initializes tuihuoliangdxR	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getTuihuoliangdxR() {
		if (tuihuoliangdxR == null) {
			tuihuoliangdxR = new JRadioButton();
			tuihuoliangdxR.setBounds(new Rectangle(248, 153, 198, 21));
			tuihuoliangdxR.addItemListener(new MyListener());
			tuihuoliangdxR.setText("按退货数量大小排行查询");
		}
		return tuihuoliangdxR;
	}

	/**
	 * This method initializes tuohuotimeR	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getTuohuotimeR() {
		if (tuohuotimeR == null) {
			tuohuotimeR = new JRadioButton();
			tuohuotimeR.setBounds(new Rectangle(251, 183, 169, 21));
			tuohuotimeR.setText("按时间段查询");
			tuohuotimeR.addItemListener(new MyListener());
			
			ButtonGroup group=new ButtonGroup();
			group.add(zirantuihuoR);
			group.add(tuihuoeR);
			group.add(tuihuoliang);
			group.add(tuihuoedxR);
			group.add(tuihuoliangdxR);
			group.add(tuohuotimeR);
			
		}
		return tuohuotimeR;
	}

	/**
	 * This method initializes xzsjT	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getXzsjT() {
		if (xzsjT == null) {
			xzsjT = new JTextField();
			String time=new GetTime().getTime();
			xzsjT.setText(time);
			xzsjT.setEditable(false);
			xzsjT.setBounds(new Rectangle(558, 189, 120, 22));
		}
		return xzsjT;
	}

	/**
	 * This method initializes from2	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getFrom2() {
		if (from2 == null) {
			from2 = new JTextField();
			from2.setBounds(new Rectangle(242, 215, 83, 22));
		}
		return from2;
	}

	/**
	 * This method initializes to2	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTo2() {
		if (to2 == null) {
			to2 = new JTextField();
			to2.setBounds(new Rectangle(360, 216, 104, 22));
		}
		return to2;
	}

	/**
	 * This method initializes tuihuoButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getTuihuoButton() {
		if (tuihuoButton == null) {
			tuihuoButton = new JButton();
			tuihuoButton.setBounds(new Rectangle(281, 282, 122, 44));
			tuihuoButton.setText("开始查询");
			tuihuoButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(tuihuovalue.equals("")){
						JOptionPane.showMessageDialog(tuihuoButton, "请选择查询方式");
					}else{
						
						if(tuihuovalue=="自然退货"){
							new TuiHuoZiRanSearch().init();
							   }
						   if(tuihuovalue=="退货额"){
							   new TuiHuoESearch().init();
					}
						   if(tuihuovalue=="退货量"){
							   new TuiHuoLiangSearch().init();
						  }
					if(tuihuovalue=="退货额大小"){
						new TuiHuoEdxSearch().init();
					  }
					
					if(tuihuovalue=="退货量大小"){
						new TuiHuoLiangdxSearch().init();
					  }
						if(tuihuovalue=="退货时间"){

							String t1=from2.getText().trim();
							String t2=to2.getText().trim();
							if(t1.equals("")||t2.equals("")){
								
								JOptionPane.showMessageDialog(sellButton, "时间不能为空，请输入时间  格式为：  从 2000-01-01 到 2099-12-30  ");
								
							}else{
								if(t1.matches( "20\\d{1}\\d{1}-[0,1]{1}\\d{1}-[0,1,2,3]{1}\\d{1}" ) && t2.matches("20\\d{1}\\d{1}-[0,1]{1}\\d{1}-[0,1,2,3]{1}\\d{1}")){
									ResultSet rs=new GetRS().getResultSet("select * from tb_sell_info where sellDateTime between '"+t1+"' and  '"+t2+"'");
									if(rs==null){
										JOptionPane.showMessageDialog(sellButton, "未查到内容，时间格式不对 或者 无此时间段信息 ");
									}else{
										new TuiHuoTimeSearch().init(t1,t2);
									}
									
								}else{
									JOptionPane.showMessageDialog(sellButton, "时间格式错误，请输入时间  格式为：  从 2000-01-01 到 2099-12-30");
								}
								}
							
						}
						
						
					}
				}
			});
		}
		return tuihuoButton;
	}

	/**
	 * This method initializes tuikuziranR	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getTuikuziranR() {
		if (tuikuziranR == null) {
			tuikuziranR = new JRadioButton();
			tuikuziranR.addItemListener(new MyListener());
			tuikuziranR.setBounds(new Rectangle(258, 21, 132, 21));
			tuikuziranR.setText("自然查询");
		}
		return tuikuziranR;
	}

	/**
	 * This method initializes tuikueR	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getTuikueR() {
		if (tuikueR == null) {
			tuikueR = new JRadioButton();
			tuikueR.addItemListener(new MyListener());
			tuikueR.setBounds(new Rectangle(259, 48, 126, 21));
			tuikueR.setText("退库金额统计");
		}
		return tuikueR;
	}

	/**
	 * This method initializes tuikuliangR	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getTuikuliangR() {
		if (tuikuliangR == null) {
			tuikuliangR = new JRadioButton();
			tuikuliangR.addItemListener(new MyListener());
			tuikuliangR.setBounds(new Rectangle(266, 78, 119, 21));
			tuikuliangR.setText("退库数量统计");
		}
		return tuikuliangR;
	}

	/**
	 * This method initializes tuikuedxR	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getTuikuedxR() {
		if (tuikuedxR == null) {
			tuikuedxR = new JRadioButton();
			tuikuedxR.addItemListener(new MyListener());
			tuikuedxR.setBounds(new Rectangle(266, 105, 185, 21));
			tuikuedxR.setText("按退库金额大小排行查询");
		}
		return tuikuedxR;
	}

	/**
	 * This method initializes tuikuliangdxR	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getTuikuliangdxR() {
		if (tuikuliangdxR == null) {
			tuikuliangdxR = new JRadioButton();
			tuikuliangdxR.addItemListener(new MyListener());
			tuikuliangdxR.setBounds(new Rectangle(267, 137, 173, 21));
			tuikuliangdxR.setText("按退库数量大小排行查询");
		}
		return tuikuliangdxR;
	}

	/**
	 * This method initializes tuikutimeR	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getTuikutimeR() {
		if (tuikutimeR == null) {
			tuikutimeR = new JRadioButton();
			tuikutimeR.setBounds(new Rectangle(267, 164, 134, 21));
			tuikutimeR.setText("按时间段查询");
			tuikutimeR.addItemListener(new MyListener());
			ButtonGroup group=new ButtonGroup();
			group.add(tuikuziranR);
			group.add(tuikueR);
			group.add(tuikuliangR);
			group.add(tuikuedxR);
			group.add(tuikuliangdxR);
			group.add(tuikutimeR);
		}
		return tuikutimeR;
	}

	/**
	 * This method initializes jTextField2	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField2() {
		if (jTextField2 == null) {
			jTextField2 = new JTextField();
			String time=new GetTime().getTime();
			jTextField2.setText(time);
			jTextField2.setEditable(false);
			jTextField2.setBounds(new Rectangle(524, 165, 122, 22));
		}
		return jTextField2;
	}

	/**
	 * This method initializes from3	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getFrom3() {
		if (from3 == null) {
			from3 = new JTextField();
			from3.setBounds(new Rectangle(239, 194, 95, 22));
		}
		return from3;
	}

	/**
	 * This method initializes to3	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTo3() {
		if (to3 == null) {
			to3 = new JTextField();
			to3.setBounds(new Rectangle(377, 194, 88, 22));
		}
		return to3;
	}

	/**
	 * This method initializes tuikuButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getTuikuButton() {
		if (tuikuButton == null) {
			tuikuButton = new JButton();
			tuikuButton.setBounds(new Rectangle(306, 258, 95, 32));
			tuikuButton.setText("开始查询");
			tuikuButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(tuikuvalue.equals("")){
						JOptionPane.showMessageDialog(tuikuButton, "请选择查询方式！");
					}else{
						if(tuikuvalue=="自然退库"){
							new TuiKuZiRanSearch().init();
							   }
						   if(tuikuvalue=="退库额"){
							   new TuiKuESearch().init();
					}
						   if(tuikuvalue=="退库量"){
							   new TuiKuLiangSearch().init();
						  }
					if(tuikuvalue=="退库额大小"){
						new TuiKuEdxSearch().init();
					  }
					
					if(tuikuvalue=="退库量大小"){
						new TuiKuLiangdxSearch().init();
					  }
						if(tuikuvalue=="退库时间"){

							String t1=from3.getText().trim();
							String t2=to3.getText().trim();
							if(t1.equals("")||t2.equals("")){
								
								JOptionPane.showMessageDialog(sellButton, "时间不能为空，请输入时间  格式为：  从 2000-01-01 到 2099-12-30  ");
								
							}else{
								if(t1.matches( "20\\d{1}\\d{1}-[0,1]{1}\\d{1}-[0,1,2,3]{1}\\d{1}" ) && t2.matches("20\\d{1}\\d{1}-[0,1]{1}\\d{1}-[0,1,2,3]{1}\\d{1}")){
									ResultSet rs=new GetRS().getResultSet("select * from tb_tuiku_info where tkTime between '"+t1+"' and  '"+t2+"'");
									if(rs==null){
										JOptionPane.showMessageDialog(sellButton, "未查到内容，时间格式不对 或者 无此时间段信息 ");
									}else{
										new TuiKuTimeSearch().init(t1,t2);
									}
									
								}else{
									JOptionPane.showMessageDialog(sellButton, "时间格式错误，请输入时间  格式为：  从 2000-01-01 到 2099-12-30");
								}
							}}}
				}
			});
		}
		return tuikuButton;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				SearchJFrame thisClass = new SearchJFrame();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public SearchJFrame() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(700,500);
		this.setContentPane(getJContentPane());
		this.setTitle("信息查询中心");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
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
			jContentPane.add(getJTabbedPane(), null);
		}
		return jContentPane;
	}
	
	private class MyListener implements ItemListener {

		
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange()==e.SELECTED)
			  { 
				//==================================================================================//
			   if(e.getSource()==ziranradio){
				   sellvalue="销售自然";  
				   }
			   if(e.getSource()==sellpriceradio){
				   sellvalue="销售额";  }
			   if(e.getSource()==sellacountradio){
				   sellvalue="销售量";
			  }
			if(e.getSource()==selltimeradio){
				sellvalue="销售时间";
			}
			if(e.getSource()==selleph){
				sellvalue="盈利";
			}
			if(e.getSource()==sellliangph){
				sellvalue="销售量大小";
			}
			//=======================================================================/////
			
			if(e.getSource()==rukuziran){
				   rukuvalue="入库自然";  
				   }
			   if(e.getSource()==rukue){
				   rukuvalue="入库额";  
		}
			   if(e.getSource()==rukusellacount){
				   rukuvalue="入库量";
			  }
		if(e.getSource()==rukueduoshao){
			   rukuvalue="入库额排行";
		  }
		
		if(e.getSource()==rukuliangdaxiao){
			   rukuvalue="入库量排行";
		  }
			if(e.getSource()==rukutime){
				   rukuvalue="入库时间";
			  }
		//======================================================================== ===== = === = =======================/////	
			
			
		if(e.getSource()==zirantuihuoR){
			   tuihuovalue="自然退货";  
			   }
		   if(e.getSource()==tuihuoeR){
			   tuihuovalue="退货额";  
	}
		   if(e.getSource()==tuihuoliang){
			   tuihuovalue="退货量";
		  }
	if(e.getSource()==tuihuoedxR){
		   tuihuovalue="退货额大小";
	  }
	
	if(e.getSource()==tuihuoliangdxR){
		   tuihuovalue="退货量大小";
	  }
		if(e.getSource()==tuohuotimeR){
			   tuihuovalue="退货时间";
		}
		//======================================================================== ===== = === = =======================/////	
		
		
		if(e.getSource()==tuikuziranR){
			   tuikuvalue="自然退库";  
			   }
		   if(e.getSource()==tuikueR){
			   tuikuvalue="退库额";  
	}
		   if(e.getSource()==tuikuliangR){
			   tuikuvalue="退库量";
		  }
	if(e.getSource()==tuikuedxR){
		   tuikuvalue="退库额大小";
	  }
	
	if(e.getSource()==tuikuliangdxR){
		   tuikuvalue="退库量大小";
	  }
		if(e.getSource()==tuikutimeR){
			   tuikuvalue="退库时间";
		}
		
		
		
		}

		
		}
		
	}
	}  //  @jve:decl-index=0:visual-constraint="94,3"
	

