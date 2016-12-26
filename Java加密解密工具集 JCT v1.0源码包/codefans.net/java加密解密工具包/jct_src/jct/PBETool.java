package jct;


import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.EncodedKeySpec;
import java.security.Security;
import java.util.Random;
import java.util.Vector;

/**
 * <p>��װͬPBE�����ڿ���ļ��ܣ��㷨�йصķ���������ʹ��PBE�㷨���ܺͽ���</p>
 * @Copyright:WDSsoft
 * @ad:WDSsoft ����ҵ�༶����ǩ��ϵͳ��- ��ѵ���ҵ�����ĵ��༶����ǩ������
 * @URL:www.wdssoft.com
 * @���� ������  mdss@wdssoft.com bluesunday@sohu.com
 */


public class PBETool
{

  public PBETool() {  }
  /**
   *����
   */
  public static void main(String[] args)
  {
    String tt=new String("java");
        char[] password=tt.toCharArray();
    byte[] pp;
        PBETool pbe=new PBETool();
        try{

            pp=pbe.PBEunwrap(pbe.PBEwrap(tt.getBytes("UTF8"),password),password);
            System.out.print(new String(pp));

    }
        catch(Exception e){}
  }
  /**
 * ʹ��PBE����byte[]���漴���Σ���unwrap��������ʹ��
 * @param byte[] target��PBE���ܶ���
 * @param char[] password: PBE���ܿ���
 * @return Object[] object[0]1:byte[]:����; object[1]:byte[]:��
 */
   public  Object[] PBEwrap(byte[] target,char[] password){
   try{

          PBEKeySpec keySpec=new PBEKeySpec(password);
          SecretKeyFactory keyFactory=SecretKeyFactory.getInstance("PBEWithMD5AndDES");
          SecretKey key=keyFactory.generateSecret(keySpec);
          Cipher cipher=Cipher.getInstance("PBEWithMD5AndDES");
          byte[] salt=new byte[8];
          Random random=new Random();
          random.nextBytes(salt);
          PBEParameterSpec paramSpec=new PBEParameterSpec(salt,100);
          cipher.init(cipher.ENCRYPT_MODE,key,paramSpec);
          Object[] encryptedByte=new Object[2];
          encryptedByte[0]=cipher.doFinal(target);
          encryptedByte[1]=salt;
          return encryptedByte;

       }catch(Exception e){e.printStackTrace();
        return null;
        }
    }
    /**
   * ʹ��PBE����byte[]�����̶����Σ���unwrape_2����ʹ��
   * @param byte[] target��PBE���ܶ���
   * @param char[] password: PBE���ܿ���
   * @return byte[] :���ܺ������
 */
  public  byte[] PBEwrap_2(byte[] target,char[] password){
   try{
          PBEKeySpec keySpec=new PBEKeySpec(password);
          SecretKeyFactory keyFactory=SecretKeyFactory.getInstance("PBEWithMD5AndDES");
          SecretKey key=keyFactory.generateSecret(keySpec);
          Cipher cipher=Cipher.getInstance("PBEWithMD5AndDES");
          //�̶����Σ�ʵ������ʱ�����и����Σ���Ҫ��unwrape_2һ��
          byte[] salt=new byte[8];
          salt[2]=salt[4]=salt[7]=11;
          salt[1]=salt[3]=salt[5]=36;
          salt[0]=10;
          PBEParameterSpec paramSpec=new PBEParameterSpec(salt,100);
          cipher.init(cipher.ENCRYPT_MODE,key,paramSpec);
          return cipher.doFinal(target);


       }catch(Exception e){e.printStackTrace();
        return null;
        }
    }



/**
 * PBE���ܣ���wrap����ʹ��
 * @param Object[] encrypted: object[0] byte[]:���� object[1]:byte[]:��
 * @param char[] password:����
 * @return byte[] ����
 */
  public  byte[] PBEunwrap(Object[] encrypted,char[] password){
      try{

          byte[] wraped=(byte[])encrypted[0];
          byte[] salt=(byte[])encrypted[1];
          PBEKeySpec keySpec=new PBEKeySpec(password);
          SecretKeyFactory keyFactory=SecretKeyFactory.getInstance("PBEWithMD5AndDES");
          SecretKey key=keyFactory.generateSecret(keySpec);
          Cipher cipher=Cipher.getInstance("PBEWithMD5AndDES");
          PBEParameterSpec paramSpec=new PBEParameterSpec(salt,100);
          cipher.init(cipher.DECRYPT_MODE,key,paramSpec);
          return cipher.doFinal(wraped);


    }catch(Exception e){

      return null;}
  }

  /**
 * PBE���ܣ���wrap_2����ʹ��
 * @param byte[] encrypted:����
 * @param char[] password:����
 * @return byte[] ����
 */

  public  byte[] PBEunwrap_2(byte[] encrypted,char[] password){
      try{

          byte[] wraped=encrypted;

          //�̶����Σ�ʵ������ʱ�����и����Σ���Ҫ��unwrap_2һ��
           byte[] salt=new byte[8];
          salt[2]=salt[4]=salt[7]=11;
          salt[1]=salt[3]=salt[5]=36;
          salt[0]=10;
          PBEKeySpec keySpec=new PBEKeySpec(password);
          SecretKeyFactory keyFactory=SecretKeyFactory.getInstance("PBEWithMD5AndDES");
          SecretKey key=keyFactory.generateSecret(keySpec);
          Cipher cipher=Cipher.getInstance("PBEWithMD5AndDES");
          PBEParameterSpec paramSpec=new PBEParameterSpec(salt,100);
          cipher.init(cipher.DECRYPT_MODE,key,paramSpec);
          return cipher.doFinal(wraped);


    }catch(Exception e){

      return null;}
  }
}