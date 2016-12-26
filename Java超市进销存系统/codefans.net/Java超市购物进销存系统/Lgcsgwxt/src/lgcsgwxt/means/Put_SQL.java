package lgcsgwxt.means;

import java.sql.Connection;
import lgcsgwxt.DBAccess;
import java.sql.PreparedStatement;
/**
 * <p>Title: ³�㳬�н�����ϵͳ</p>
 *
 * <p>Description: ��������³��У��S1��ҵ���</p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: ST-117��</p>
 *
 * @author ST-117��ڶ�С��
 * @version 1.0
 */
public class Put_SQL {
 public static final String SQL_INSERT = "INSERT INTO EnterStock(MerNumber, Number, PurchasePrice,StoreHouse_ID,dealWithHuman,downName) VALUES(?, ?, ?,?,?,?)";
    public static boolean  insert(String MerNumber, String Number, String PurchasePrice, String StoreHouse_ID,String dealWithHuman,String downName){
        boolean isSuccess = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBAccess.getConnection();
            pstmt = conn.prepareStatement(SQL_INSERT);
            pstmt.setString(1, MerNumber);
            pstmt.setString(2, Number);
            pstmt.setString(3, PurchasePrice);
            pstmt.setString(4, StoreHouse_ID);
            pstmt.setString(5, dealWithHuman);
            pstmt.setString(6, downName);
            int temp = pstmt.executeUpdate();
            if(temp != 0){
                isSuccess = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally{
            try {
                if(pstmt != null){
                    pstmt.close();
                }
                if(conn != null){
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return isSuccess;
    }
}

