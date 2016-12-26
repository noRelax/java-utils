package lgcsgwxt.means;

import java.util.Vector;
import java.sql.*;
import lgcsgwxt.DBAccess;

public class SelectEarning {
    public static String SQLFind1 = "select ProductID,ProductName,Number,Price,SaleDate,Number*Price from Sale where ProductID=?";
    public static String SQLFind2 =
            "select ProductID,ProductName,Number,Price,SaleDate,Number*Price from Sale";
    public static Vector findAll(String ProductID) {

        Vector ve1 = new Vector();
        DBAccess dba = new DBAccess();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        con = dba.getConnection();
        try {
            if (ProductID.length() == 0) {
                ps = con.prepareStatement(SQLFind2);
            }

            else {
                ps = con.prepareStatement(SQLFind1);
                ps.setString(1,ProductID);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                Vector ve = new Vector();
                ve.add(rs.getString(1));
                ve.add(rs.getString(2));
                ve.add(rs.getString(3));
                ve.add(rs.getString(4));
                ve.add(rs.getString(5));
                Double money=new Double(rs.getString(6));
                double money1=((int)(money*100))/100.0;//将金额处理成只含两位小数
                Double money2=new Double(money1);
                ve.add(money2.toString());
                ve1.add(ve);
            }
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }

        } catch (SQLException ex) {
        }
        return ve1;
    }

    public static boolean charbj(String n1, String n2) {
        boolean b1 = false;
        int i = 0;
        while (i < n1.length() - 1) {
            i++;
            if (n1.charAt(i) == n2.charAt(i)) {
                b1 = true;
                continue;
            }
            if (n1.charAt(i) > n2.charAt(i)) {
                b1 = true;
                break;
            }
            if (n1.charAt(i) < n2.charAt(i)) {
                b1 = false;
                break;
            }
        }
        return b1;
    }

//    public static void main(String args[]) {
////        int a = 0;
////        Vector va = new Vector();
////        Vector va2 = new Vector();
//        String n1 = "2007-09-24";
//        String n2 = "2007-09-25";
//        System.out.println(charbj(n2,n1));
////
////        va = SelectEarning.findAll(n1, n2, "RY000014");
////        while (a < va.size()) {
////            va2 = (Vector) va.get(a);
////            int b = 0;
////            while (b < va2.size()) {
////                va2.get(b);
////                System.out.println(va2);
////                b++;
////            }
////            a++;
////        }
//    }
}
