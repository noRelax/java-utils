package com.itstar.erp.ui.searchswing.tuikusearch;



import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import com.itstar.erp.dao.kucun.KuCunDaoImpl;
import com.itstar.erp.dao.tuihuo.TuiHuoDaoImpl;
import com.itstar.erp.dao.tuiku.TuiKuDaoImpl;
import com.itstar.erp.util.GetRS;
import com.itstar.erp.util.GetTime;
import com.itstar.erp.vo.product.ProBean;
import com.itstar.erp.vo.ruku.RuKuBean;

public class TuiKuTimeSearch {
	
	public static void main(String[] args){
//		new TuiKuTimeSearch().init();
	}
	
	public void init(String time1,String time2){
		Object[][] cells;
		
		ResultSet rs=new GetRS().getResultSet("select * from tb_tuiku_info where tkTime between '"+time1 +"' and '"+time2+"'");
		String[] colnames={"退库数量","入库编号","商品名称","商品进价","入库数量","退库金额","退库编号","入库时间","退库时间","退库原因","经手人"}; 
		
		int i=0;
		
		try {
			while(rs.next()){
				i++;
			}
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		
		cells=new Object[i][11];
		
		int j=0;
		try {
			rs.beforeFirst() ;                 //移动游标
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {
			while(rs.next()){
				System.out.println("Ha Ha Ha");
//				{"退库编号","入库编号","商品名称","商品进价","入库数量","退库数量","退库金额","入库时间","退库时间","退库原因","经手人"};
				int tuikuid=rs.getInt(1);                                                     //退库编号
				int rukuid=rs.getInt(2);                                                        //入库编号
				String tuikutime=rs.getString(3);                                               //退库时间
				
				String tktime=new GetTime().format(tuikutime);                                      //退库时间 编号   无-----
				String ttime=new GetTime().toformat(tuikutime);                                       //有-------   退库时间
				
				int tuikuacount=rs.getInt(4);                                                  //退库数量
				String tuikuremark=rs.getString(5);                                                      //退库备注
				int tuikuywyid=rs.getInt(6);                                                //退库业务员编号
				
				
				RuKuBean rukubean=new TuiKuDaoImpl().getRuKuBean(rukuid);
				int proid=rukubean.getProID();                                                  //商品编号
				int rukuacount=rukubean.getRukuAcount();                                          //入库数量
				String rukutime=rukubean.getRukuDateTime();                                        //入库时间
				
				String rktime=new GetTime().format(rukutime);                           //入库时间  编号   无------
				String xtime=new GetTime().toformat(rukutime);                            //入库时间
				
			
				
				
				ProBean probean=new KuCunDaoImpl().getProBean(proid);                     
				String proname=probean.getProName();                                                //商品名称
				double proprice=probean.getProPrice();                                               // 商品进价
				
				String ywyname=new TuiHuoDaoImpl().getywyName(tuikuywyid);                        //退货业务员名称
				
				double tuikujine=tuikuacount*proprice;                                                  //退库金额
				
			
				
				                               
				
//		{"退库编号","入库编号","商品名称","商品进价","入库数量","退库数量","退库金额","入库时间","退库时间","退库原因","经手人"};
				cells[j][0]=String.valueOf(tuikuacount).trim();
				
				cells[j][1]="RK"+xtime+"_"+(1000+rukuid);
				cells[j][2]=String.valueOf(proname).trim();
				cells[j][3]=String.valueOf(proprice).trim();
				cells[j][4]=String.valueOf(rukuacount).trim();
				cells[j][5]=String.valueOf(tuikujine).trim()+"     元";
				cells[j][6]="TK"+ttime+"_"+(1000+tuikuid);
				cells[j][7]=String.valueOf(rktime).trim();
				cells[j][8]=String.valueOf(tktime).trim();
				cells[j][9]=String.valueOf(tuikuremark).trim();
				cells[j][10]=String.valueOf(ywyname).trim();
				
				
				
				j++;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JFrame jf=new JFrame("按退库金额大小排行查询");
		
		JTable jt=new JTable(cells,colnames);
		
		jt.setBackground(Color.white);
		jt.setRowHeight (30);
		jt.setSelectionForeground (Color.red);//
		jt.setEnabled(false);

		JScrollPane js=new JScrollPane(jt);
		jf.add(js);

		jf.setVisible(true);
		jf.setSize(800,600);
		jf.setLocationRelativeTo(null);
		
		
		}
	

}