package jct;

import java.security.*;


/**
 * <p>�����ַ�����byte[]������ժҪ </p>
 * @Copyright:WDSsoft
 * @ad:WDSsoft ����ҵ�༶����ǩ��ϵͳ��- ��ѵ���ҵ�����ĵ��༶����ǩ������
 * @URL:www.wdssoft.com
 * @���� �ⶫ�� mdss@wdssoft.com bluesunday@sohu.com
 */
public class Digest {



 /**
  * �����ַ�����SHA����ժҪ����byte[]��ʽ����
  **/
  public  static byte[] MdigestSHA(String source){
      byte[] nullreturn={0} ;
      try{
            MessageDigest thisMD=MessageDigest.getInstance("SHA");
            byte[]  digest=thisMD.digest(source.getBytes("UTF8"));
            return digest;
            } catch(Exception e){return null;}
  }
  /**
   * ����byte[]��SHA����ժҪ����byte[]��ʽ����
   **/
  public static byte[] MdigestSHA(byte[] source){
      byte[] nullreturn={0} ;
      try{
            MessageDigest thisMD=MessageDigest.getInstance("SHA");
            byte[]  digest=thisMD.digest(source);
            return digest;
            } catch(Exception e){return null;}
  }
  /**
    * �����ַ�����MD5����ժҪ����byte[]��ʽ����
    **/
    public  static byte[] MdigestMD5(String source){
        byte[] nullreturn={0} ;
        try{
              MessageDigest thisMD=MessageDigest.getInstance("MD5");
              byte[]  digest=thisMD.digest(source.getBytes("UTF8"));
              return digest;
              } catch(Exception e){return null;}
    }
    /**
     * ����byte[]����MD5��ժҪ����byte[]��ʽ����
     **/
    public static byte[] MdigestMD5(byte[] source){
        byte[] nullreturn={0} ;
        try{
              MessageDigest thisMD=MessageDigest.getInstance("MD5");
              byte[]  digest=thisMD.digest(source);
              return digest;
              } catch(Exception e){return null;}
    }
    public static void main(String[] args){
          String test="WDSsoft";

          byte[] t1=MdigestSHA(test);
          for(int i=0;i<t1.length;i++){
           System.out.print(Integer.toString(Math.abs(t1[i])));
           }
           System.exit(0);
    }

  }