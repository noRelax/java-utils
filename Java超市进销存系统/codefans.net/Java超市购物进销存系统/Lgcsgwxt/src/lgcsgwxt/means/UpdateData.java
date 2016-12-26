package lgcsgwxt.means;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import lgcsgwxt.DBAccess;
import java.sql.*;

/**
 * <p>Title: 鲁广超市进销存系统</p>
 *
 * <p>Description: 北大青鸟鲁广校区S1毕业设计</p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: ST-117班</p>
 *
 * @author ST-117班第二小组
 * @version 1.0
 */
public class UpdateData {
    public UpdateData() {
    }

    private static final String Update1 =
            "Update  UserManage set UserName=?,IDcard=?,password=?,CardDate=?,UserGrade=?,term=?,integral=?,agio=?,remark=? where cardId=?";

    private static final String Update2 ="update StockPile set number=? where MerchandiseNumber=?";

    public static int UpdateCard(String cardId, String name, String IDcard,
                                 String password, String CardDate,
                                 String UserGrade, String term,
                                 String integral, String agio, String remark) {
        int i = 0;
        Connection conn = null;
        PreparedStatement pmst = null;
        conn = DBAccess.getConnection();
        try {
            pmst = conn.prepareStatement(Update1);
            pmst.setString(10, cardId);
            pmst.setString(1, name);
            pmst.setString(2, IDcard);
            pmst.setString(3, password);
            pmst.setString(4, CardDate);
            pmst.setString(5, UserGrade);
            pmst.setString(6, term);
            pmst.setString(7, integral);
            pmst.setString(8, agio);
            pmst.setString(9, remark);
            i = pmst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (pmst != null) {
                    pmst.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }

        return i;
    }
    public static int out_StockPile(int number, String MerchandiseNumber) {
        int i = 0;
        int updatenumber=0;
       updatenumber= Select_Means.Select_StockPile(MerchandiseNumber);
       updatenumber-=number;
        Connection conn = null;
        PreparedStatement pmst = null;
        conn = DBAccess.getConnection();
        try {
            pmst = conn.prepareStatement(Update2);
            pmst.setInt(1, updatenumber);
            pmst.setString(2, MerchandiseNumber);
            i = pmst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (pmst != null) {
                    pmst.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }
        return i;
    }

    public static int in_StockPile(int number, String MerchandiseNumber) {
           int i = 0;
           int updatenumber=0;
          updatenumber= Select_Means.Select_StockPile(MerchandiseNumber);
          updatenumber+=number;
           Connection conn = null;
           PreparedStatement pmst = null;
           conn = DBAccess.getConnection();
           try {
               pmst = conn.prepareStatement(Update2);
               pmst.setInt(1, updatenumber);
               pmst.setString(2, MerchandiseNumber);
               i = pmst.executeUpdate();
           } catch (SQLException ex) {
               ex.printStackTrace();
           } finally {
               try {
                   if (pmst != null) {
                       pmst.close();
                   }
                   if (conn != null) {
                       conn.close();
                   }
               } catch (Exception ex1) {
                   ex1.printStackTrace();
               }
           }
           return i;
    }

}
