package dboperate;
/**
 * <p>Title:勿忘软件,lzquan </p>
 *
 * <p>Description:勿忘软件 </p>
 *
 * <p>Copyright: 泉水依然 Copyright (c) 2007-03-20</p>
 *
 * <p>Company: 泉水依然</p>
 *
 * @author :权哥,湖南农业大学科学技术师范学院04计算机教育班.
 *
 * QQ:25241418
 */
import javax.xml.parsers.*;
import javax.swing.JOptionPane;
public class XmlUpdate{
    /**
     * 删除记录.
     * @param allData Object[][]
     * @param id int
     * @return boolean
     */
    public boolean delData(Object[][] allData, String id) {
        try {
            String delId = id.toString().trim();
            int rowCount = allData.length;
            int delRowCount = 0;
            Object[][] delData = new Object[rowCount-1][9];
            /**
             * 将id相同的行删除.
             */
            for (int i = 0; i < allData.length; i++) {
                String thisID = allData[i][0].toString().trim();
                if (!thisID.equals(delId)) {
                    for (int j = 0; j < 9; j++) {
                        //JOptionPane.showMessageDialog(null,"i:"+i+"\n j:"+j+"\n delRowCount:"+delRowCount);
                        delData[delRowCount][j] = allData[i][j];
                    }
                    delRowCount++;
                }
            }
            /**
             * 写入xml
             */
            if(this.writXml(delData)){
                return true;
            }else{
                return false;
            }
        } catch (Exception e1) {
            return false;
        }
    }

    /**
     * 更新xml
     * @param replaceData Object[],要替换的数据.
     * @param allData Object[][]当前所有数据.
     * @param id String,要替换的id.
     * @return boolean
     */
    public boolean updateData(Object[] replaceData,Object[][] allData,String id){
        try{
            int rowCount = allData.length;
            Object[][] UpdateData = new Object[rowCount][9];
            String updateID = id.toString().trim();
            //将所有的记录赋给要返回的数组.
            for (int m = 0; m < rowCount; m++)
                for (int n = 0; n < allData[m].length; n++) {
                    UpdateData[m][n] = allData[m][n];
                }

            //改变数组.
            for (int i = 0; i < rowCount; i++) {
                String thisID = allData[i][0].toString().trim();
                if (thisID.equals(updateID)) {
                    for (int j = 0; j < 9; j++) {
                        UpdateData[i][j] = replaceData[j];
                    }
                }
            }
            /**
             * 写入xml
             */
            if(this.writXml(UpdateData)){
                return true;
            }else{
                return false;
            }
        }catch(Exception e3){
                return false;
        }
    }

    public boolean filtrateMemoire(Object[][] allData, String id){
        try {
            Object wuxiao = 0;
            String filtrateId = id.toString().trim();
            int rowCount = allData.length;
            Object[][] filtrateData = new Object[rowCount][9];
            String updateID = id.toString().trim();
            //将所有的记录赋给要返回的数组.
            for (int m = 0; m < rowCount; m++)
                for (int n = 0; n < allData[m].length; n++) {
                    filtrateData[m][n] = allData[m][n];
                }
            /**
             * 将id相同的行更改.
             */
            for (int i = 0; i < rowCount; i++) {
                String thisID = allData[i][0].toString().trim();
                if (thisID.equals(updateID)) {
                        filtrateData[i][7] = wuxiao;
                }
            }
            /**
             * 写入xml
             */
            if(this.writXml(filtrateData)){
                return true;
            }else{
                return false;
            }
        } catch (Exception e1) {
            return false;
        }
    }

    /**
     * 写入xml
     * @param updateData Object[][]
     * @return boolean
     */
    public boolean writXml(Object[][] updateData) {
        try {
            writexml updateXml = new writexml();
            updateXml.toWrite(updateData);
            updateXml.toSave();
            return true;
        } catch (ParserConfigurationException ex) {
            return false;
        }
    }

    public static void main(String args[]){
        XmlUpdate update = new XmlUpdate();
        xmlConn conn = new xmlConn("Not_Forget",9);
        Object[][] allData = conn.getData();
        String ID = "2";
        if(update.delData(allData,ID)){
            JOptionPane.showMessageDialog(null,"删除成功!");
        }else{
            JOptionPane.showMessageDialog(null,"删除失败!");
        }
    }
}





