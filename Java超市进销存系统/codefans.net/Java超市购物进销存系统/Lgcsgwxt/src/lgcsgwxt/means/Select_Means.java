package lgcsgwxt.means;

import java.util.Vector;
import java.sql.*;
import lgcsgwxt.*;

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
public class Select_Means {

    private static final String select1 =
            "select * from Merchandise where SortNumber=?";
    private static final String select2 =
            "select * from Merchandise where MerchandiseNumber=?";
    private static final String select3 =
            "select * from Merchandise where MerchandiseName=?";
    private static final String select4 =
            "select * from Merchandise where TreatyCode=?";
    private static final String select5 =
            "select * from UserManage where CardId=? ";
    private static final String select6 =
            "select * from UserManage where UserName=? and Password=? and UserGrade=?";
    private static final String select7 =
            "select * from BackSale where NextBack=?";
    private static final String select8 =
            "select * from BackSale where Product_ID=? and NextBack=?";
    private static final String select9 =
            "select number from StockPile where MerchandiseNumber=?";
    private static final String select10 =
            "select * from StockPile where MerchandiseNumber=?";
    private static final String select11 =
            "select * from StockPile";
    public Select_Means() {
    }

    public static Vector tablehead() {
        Vector dqname = new Vector();
        dqname.add("商品编码");
        dqname.add("条型码");
        dqname.add("类别编号");
        dqname.add("商品名称");
        dqname.add("零售价(元)");
        dqname.add("商品规格");
        dqname.add("计量单位");
        dqname.add("进货价");
        dqname.add("备注");
        return dqname;
    }

    public static Vector Select_sort1(String number) { //商品类别查询
        Connection conn = null;
        PreparedStatement pmst = null;
        ResultSet rs = null;
        Vector vector2 = new Vector();
        conn = DBAccess.getConnection();

        try {
            pmst = conn.prepareStatement(select1);
            pmst.setString(1, number);
            rs = pmst.executeQuery();
            while (rs.next()) {
                Vector vector1 = new Vector();
                vector1.add(rs.getString(2));
                vector1.add(rs.getString(3));
                vector1.add(rs.getString(4));
                vector1.add(rs.getString(5));
                vector1.add(rs.getString(6));
                vector1.add(rs.getString(7));
                vector1.add(rs.getString(8));
                vector1.add(rs.getString(9));
                vector1.add(rs.getString(11));
                vector2.add(vector1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
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
        return vector2;
    }

    public static Vector Select_sort2(String number) { //按商品编号查询
        Connection conn = null;
        PreparedStatement pmst = null;
        ResultSet rs = null;
        Vector vector2 = new Vector();
        conn = DBAccess.getConnection();

        try {
            pmst = conn.prepareStatement(select2);
            pmst.setString(1, number);
            rs = pmst.executeQuery();
            while (rs.next()) {
                Vector vector1 = new Vector();
                vector1.add(rs.getString(2));
                vector1.add(rs.getString(3));
                vector1.add(rs.getString(4));
                vector1.add(rs.getString(5));
                vector1.add(rs.getString(6));
                vector1.add(rs.getString(7));
                vector1.add(rs.getString(8));
                vector1.add(rs.getString(9));
                vector1.add(rs.getString(11));
                vector2.add(vector1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
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
        return vector2;
    }

    public static Vector Select_sort3(String number) { //按商品名称查询
        Connection conn = null;
        PreparedStatement pmst = null;
        ResultSet rs = null;
        Vector vector2 = new Vector();
        conn = DBAccess.getConnection();

        try {
            pmst = conn.prepareStatement(select3);
            pmst.setString(1, number);
            rs = pmst.executeQuery();
            while (rs.next()) {
                Vector vector1 = new Vector();
                vector1.add(rs.getString(2));
                vector1.add(rs.getString(3));
                vector1.add(rs.getString(4));
                vector1.add(rs.getString(5));
                vector1.add(rs.getString(6));
                vector1.add(rs.getString(7));
                vector1.add(rs.getString(8));
                vector1.add(rs.getString(9));
                vector1.add(rs.getString(11));
                vector2.add(vector1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
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
        return vector2;
    }

    public static Vector Select_sort4(String number) { //按商品条形码查询
        Connection conn = null;
        PreparedStatement pmst = null;
        ResultSet rs = null;
        Vector vector2 = new Vector();
        conn = DBAccess.getConnection();

        try {
            pmst = conn.prepareStatement(select4);
            pmst.setString(1, number);
            rs = pmst.executeQuery();
            while (rs.next()) {
                Vector vector1 = new Vector();
                vector1.add(rs.getString(2));
                vector1.add(rs.getString(3));
                vector1.add(rs.getString(4));
                vector1.add(rs.getString(5));
                vector1.add(rs.getString(6));
                vector1.add(rs.getString(7));
                vector1.add(rs.getString(8));
                vector1.add(rs.getString(9));
                vector1.add(rs.getString(11));
                vector2.add(vector1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
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
        return vector2;
    }

    public static Vector Select_User(String name, String password) {
        Vector vcUser = new Vector();
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pmst = null;
        try {
            conn = DBAccess.getConnection();
            pmst = conn.prepareStatement(select6);
            pmst.setString(1, name);
            pmst.setString(2, password);
            pmst.setString(3, "管理员");
            rs = pmst.executeQuery();
            rs.next();
            vcUser.add(rs.getString(1));
            vcUser.add(rs.getString(2));
            vcUser.add(rs.getString(3));
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
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
        return vcUser;
    }

    public static Vector Select_UserManager(String CardId) { //查询卡信息
        Vector vcUser = new Vector();
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pmst = null;
        try {
            conn = DBAccess.getConnection();
            pmst = conn.prepareStatement(select5);
            pmst.setString(1, CardId);
            rs = pmst.executeQuery();
            rs.next();
            vcUser.add(rs.getString(1));
            vcUser.add(rs.getString(2));
            vcUser.add(rs.getString(3));
            vcUser.add(rs.getString(4));
            vcUser.add(rs.getString(5));
            vcUser.add(rs.getString(6));
            vcUser.add(rs.getString(7));
            vcUser.add(rs.getString(8));
            vcUser.add(rs.getString(9));
            vcUser.add(rs.getString(10));

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
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
        return vcUser;
    }

    public static Vector Select_inoutSale(String Product_ID, String NextBack) { //查询进退货信息
        Vector vector1 = new Vector();
        Vector vector2 = new Vector();
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pmst = null;
        try {
            conn = DBAccess.getConnection();
            if (Product_ID.length() == 0) {
                pmst = conn.prepareStatement(select7);
                pmst.setString(1, NextBack);
            } else {
                pmst = conn.prepareStatement(select8);
                pmst.setString(1, Product_ID);
                pmst.setString(2, NextBack);
            }
            rs = pmst.executeQuery();
            rs.next();
            vector1.add(rs.getString(2));
            vector1.add(rs.getString(3));
            vector1.add(rs.getString(4));
            vector1.add(rs.getString(5));
            vector1.add(rs.getString(6));
            vector1.add(rs.getString(7));
            vector1.add(rs.getString(8));
            vector1.add(rs.getString(9));
            vector2.add(vector1);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
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
        return vector2;
    }

    public static int Select_StockPile(String Product_ID) { //查询商品库存数量
        int number1 = 0;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pmst = null;
        try {
            conn = DBAccess.getConnection();
            pmst = conn.prepareStatement(select9);
            pmst.setString(1, Product_ID);
            rs = pmst.executeQuery();
            rs.next();
            String num = rs.getString(1);
            Integer number = new Integer(num);
            number1 = number;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
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
        return number1;
    }

    public static Vector Select_repertory(String Product_ID) { //根据商品编码查询库存
        Vector vector2 = new Vector(); //将vector1装入形成一条数据
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pmst = null;
        try {
            conn = DBAccess.getConnection();
            pmst = conn.prepareStatement(select10);
            pmst.setString(1, Product_ID);
            rs = pmst.executeQuery();
            while (rs.next()) {
                Vector vector1 = new Vector(); //记录查询的数据
                Vector vector3 = new Vector(); //接收查询到的商品信息的一条数据
                Vector vector4 = new Vector(); //接收vector3中商品的名称
                vector1.add(rs.getString(1));
                vector1.add(rs.getString(2));
                vector3 = Select_sort2(rs.getString(2)); //根据商品编号查询商品信息
                vector4 = (Vector) vector3.get(0);
                vector1.add(vector4.get(3).toString());
                vector1.add(rs.getString(3));
                vector2.add(vector1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
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
        return vector2;
    }
    public static Vector Select_All_repertory() { //查询所有库存
           Vector vector2 = new Vector(); //将vector1装入形成一条数据
           Connection conn = null;
           ResultSet rs = null;
           PreparedStatement pmst = null;
           try {
               conn = DBAccess.getConnection();
               pmst = conn.prepareStatement(select11);
               rs = pmst.executeQuery();
               while (rs.next()) {
                   Vector vector1 = new Vector(); //记录查询的数据
                   Vector vector3 = new Vector(); //接收查询到的商品信息的一条数据
                   Vector vector4 = new Vector(); //接收vector3中商品的名称
                   vector1.add(rs.getString(1));
                   vector1.add(rs.getString(2));
                   vector3 = Select_sort2(rs.getString(2)); //根据商品编号查询商品信息
                   vector4 = (Vector) vector3.get(0);
                   vector1.add(vector4.get(3).toString());
                   vector1.add(rs.getString(3));
                   vector2.add(vector1);
               }
           } catch (Exception ex) {
               ex.printStackTrace();
           } finally {
               try {
                   if (rs != null) {
                       rs.close();
                   }
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
           return vector2;
    }

}
