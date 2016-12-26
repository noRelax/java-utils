package lgcsgwxt.means;

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
public class Put_Out_Depot_SQL {
    private String MerNumber; //商品编码
    private String Number; //数量
    private String PurchasePrice; //进货价
    private String StoreHouse_ID; //仓库号
    private String dealWithHuman; //经手人
    private String downName; //下单人
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
