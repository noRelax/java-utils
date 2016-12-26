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
public class Dialog_enroll_MySQL {

    public Dialog_enroll_MySQL() {
    }

    private int CardId;
    private String UserName;
    private String IDcard;
    private String Password;
    private String CardDate;
    private String UserGrade;
    private String term;
    private String integral;
    private String agio;
    private String remark;
    public int getCardId() {
        return this.CardId;
    }

    public void setCardId(int value) {
        this.CardId = value;
    }

    public String getUserName() {
        return this.UserName;
    }

    public void setUserName(String value) {
        this.UserName = value;
    }

    public String getIDcard() {
        return this.IDcard;
    }

    public void setIDcard(String value) {
        this.IDcard = value;
    }

    public String getPassword() {
        return this.Password;
    }

    public void setPassword(String value) {
        this.Password = value;
    }

    public String getCardDate() {
        return CardDate;
    }

    public void setCardDate(String value) {
        this.IDcard = value;
    }

    public String getUserGrade() {
        return UserGrade;
    }

    public void setUserGrade(String value) {
        this.UserGrade = value;
    }

    public String getterm() {
        return term;
    }

    public void setterm(String value) {
        this.UserGrade = value;
    }

    public String getintegral() {
        return integral;
    }

    public void setintegral(String value) {
        this.integral = value;
    }

    public String getagio() {
        return agio;
    }

    public void setagio(String value) {
        this.agio = value;
    }

    public String getremark() {
        return remark;
    }

    public void setremark(String value) {
        this.remark = value;
    }

}
