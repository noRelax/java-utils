package dboperate;
/**
 * 该文件将所有的操作集合到一起.
 * 分别使用了,XmlUpdate.java,xmlConn.java,writexml.java
 */
import javax.xml.parsers.*;
public class xmlOperate{
    public Object[][] mimoireData;
    public int MaxID;
    public int rowCount;
    public Object[][] tableData;

    /**
     * 建立连接.初始化.
     * @param fileName String
     * @param columnNum int
     */
    public xmlOperate(String fileName,int columnNum){
        xmlConn conn = new xmlConn(fileName,columnNum);
        this.mimoireData = conn.getData();
        this.MaxID = conn.getMaxID();
        this.rowCount = conn.getRowCount();
        this.tableData = conn.getAllList(this.mimoireData);
    }

    /**
     * 获取数据.
     */
    public Object[][] getMemoireData(){
        return this.mimoireData;
    }

    public int getMaxID(){
        return this.MaxID;
    }

    public int getRowCount(){
        return this.rowCount;
    }

    public Object[][] getTableData(){
        return this.tableData;
    }

    /**
     * 插入xml 将所有的数据与要插入的数据合并传给它.
     * @param insertAllData Object[][]
     * @return boolean
     */
    public boolean insertXml(Object[][] insertAllData) {
        try {
            writexml insert = new writexml();
            insert.toWrite(insertAllData);
            insert.toSave();
            return true;
        } catch (ParserConfigurationException ex) {
            return false;
        }
    }

    /**
     * 删除xml,将当前所有的数据给它,并告知要删除的行对应的id.
     * @param allData Object[][] //所有数据.
     * @param id String ,要删除的id.
     * @return boolean
     */
    public boolean delXml(Object[][] allData, String id){
        XmlUpdate deldate = new XmlUpdate();
        if(deldate.delData(allData,id)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 更新xml数据,
     * @param replaceData Object[], 要更新的数据.
     * @param allData Object[][] ,所有的数据.
     * @param id String ,要更新的数据对应的id.
     * @return boolean
     */
    public boolean updateXml(Object[] replaceData,Object[][] allData,String id){
        XmlUpdate update = new XmlUpdate();
        if(update.updateData(replaceData,allData,id)){
            return true;
        }else{
            return false;
        }
    }


    public static void main(String args[]){
        xmlOperate xml = new xmlOperate("Not_Forget",9);
    }

}



