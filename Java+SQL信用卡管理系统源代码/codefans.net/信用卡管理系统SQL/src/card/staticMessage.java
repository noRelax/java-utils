package card;
//download by http://www.codefans.net
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;

public class staticMessage
{
  public staticMessage(String id)
  {
    this.CardID=id;
    try
    {
      con = cardConnect.getconn();
      query = con.createStatement();
      queryString = "Select LeaveMoney,Name From queryview Where CardID=" + CardID;
      rs = query.executeQuery( queryString ); //�õ���ѯ���
      if( rs.next() )
      {
        yue=rs.getString(1);
        userName=rs.getString(2);
        exist=true;
      }
    }catch(Exception ee)
    {}
    finally
    {
      cardConnect.close(rs);
      cardConnect.close(query);
      cardConnect.close(con);
    }
  }

  public boolean isExist()
  {
    return exist;
  }
  public static String getUserName()
  {
    return userName;
  }
  public static String getLevel()
  {
    return yue;
  }

  String queryString;
  Connection con;
  Statement query;
  ResultSet rs;
  static String CardID;
  public static String userName;
  public static String yue;
  static boolean exist=false;
}
