package lgcsgwxt.means;

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
public class Put_Out_Depot_SQL {
    private String MerNumber; //��Ʒ����
    private String Number; //����
    private String PurchasePrice; //������
    private String StoreHouse_ID; //�ֿ��
    private String dealWithHuman; //������
    private String downName; //�µ���
    public String getMerNumber() {
        return MerNumber;
    }

    public void setMerNumber(String MerNumber) {
        this.MerNumber = MerNumber;
    }

    public void setNumber(String Number) {
        this.Number = Number;
    }

    public String getNumber() {
        return Number;
    }


    public void setPurchasePrice(String PurchasePrice) {
        this.PurchasePrice = PurchasePrice;
    }

    public String getPurchasePrice() {
        return PurchasePrice;
    }

    public void setStoreHouse_ID(String StoreHouse_ID) {
        this.StoreHouse_ID = StoreHouse_ID;
    }

    public String StoreHouse_ID() {
        return StoreHouse_ID;
    }

    public void dealWithHuman(String dealWithHuman) {
        this.dealWithHuman = dealWithHuman;
    }

    public String dealWithHuman() {
        return dealWithHuman;
    }

    public void downName(String downName) {
        this.downName = downName;
    }

    public String downName() {
        return downName;
    }
}
