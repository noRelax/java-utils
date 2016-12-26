package jct;


import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.EncodedKeySpec;
import java.security.Security;
import java.security.*;
import java.util.Random;
import java.util.Vector;

/**
 * <p>��װͬBlowfish�ԳƼ����㷨�йصķ�����������ʹ�ô���Blowfish���룬ʹ��Blowfish���ܡ�����, ʹ��PBE(���ڿ���ļ���)��ȡblowfiwsh���� </p>
 * @Copyright:WDSsoft
 * @ad:WDSsoft ����ҵ�༶����ǩ��ϵͳ��- ��ѵ���ҵ�����ĵ��༶����ǩ������
 * @URL:www.wdssoft.com
 * @���� �ⶫ�� mdss@wdssoft.com bluesunday@sohu.com
 */



public class BlowfishTool {

  public BlowfishTool() {
  }
/**
 * ʹ��Blowfish����
 * @param Key key:����
 * @param byte[] text:����
 * @return byte[]:����
 */
  public static byte[] encryptReturnByte(Key key,byte[] text){
     try{
         Cipher cipher=Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
         cipher.init(Cipher.ENCRYPT_MODE,key);
         return cipher.doFinal(text);
     }catch(Exception e){  return null;    }
  }
/**
 * Blowfish����
 * @param Key key������
 * @param byte[] text������
 * @return byte[] : ����
 */
   public static byte[] decryptReturnByte(Key key,byte[] text){
     try{
         Cipher cipher=Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
         cipher.init(Cipher.DECRYPT_MODE,key);
         return cipher.doFinal(text);
     }catch(Exception e){  return null;}
   }
/**
 * ʹ��PBE����Blowfish����
 * @param Key targetKey: Blowfish����
 * @param char[] password: ����
 * @return Vector��element1:byte[]:����-element2:byte[]:��
 */

    public static Vector wrapKey(Key targetKey,char[] password){
       try{
          PBEKeySpec keySpec=new PBEKeySpec(password);
          SecretKeyFactory keyFactory=SecretKeyFactory.getInstance("PBEWithMD5AndDES");
          SecretKey key=keyFactory.generateSecret(keySpec);
          Cipher cipher=Cipher.getInstance("PBEWithMD5AndDES");
          byte[] salt=new byte[8];
          Random random=new Random();
          random.nextBytes(salt);
          PBEParameterSpec paramSpec=new PBEParameterSpec(salt,100);
          cipher.init(cipher.WRAP_MODE,key,paramSpec);
          Vector encryptedKey=new Vector();
          encryptedKey.addElement(cipher.wrap(targetKey));
          encryptedKey.addElement(salt);
          return encryptedKey;
       }catch(Exception e){e.printStackTrace();
          return null;
        }
    }
/**
 * ȡ��PBE���ܵ���Կ
 * @param Vector encryptedKey��element1:byte[]:����-element2:byte[]:��
 * @param char[] password:����
 * @return Key:Blowfish����
 */
  public static Key unwrapKey(Vector encryptedKey,char[] password){
      try{

          byte[] wrapedKey=(byte[])encryptedKey.elementAt(0);
          byte[] salt=(byte[])encryptedKey.elementAt(1);
          PBEKeySpec keySpec=new PBEKeySpec(password);
          SecretKeyFactory keyFactory=SecretKeyFactory.getInstance("PBEWithMD5AndDES");
          SecretKey key=keyFactory.generateSecret(keySpec);
          Cipher cipher=Cipher.getInstance("PBEWithMD5AndDES");
          PBEParameterSpec paramSpec=new PBEParameterSpec(salt,100);
          cipher.init(cipher.UNWRAP_MODE,key,paramSpec);
          return cipher.unwrap(wrapedKey,"Blowfish",cipher.SECRET_KEY);
    }catch(Exception e){   return null;    }
  }
  /**
   * ����һ��Blowfish����
   * @return Key Blowfish ����
   */
  public static Key creatKey(){
    try{
        KeyGenerator keyGenerator=KeyGenerator.getInstance("Blowfish");
        keyGenerator.init(128);
        Key key=keyGenerator.generateKey();
        return key;
    }catch(Exception e){return null;}
  }

/**
 * ��Blowfish����Key ����תΪbyte[] ��ʽ
 * @param Key Blowfish����Key ����
 * @return byte[]��ʽBlowfish����
 */
  public static byte[] encodeKey(Key key){
      return key.getEncoded();
    }

/**
* ��byte[] ��ʽBlowfish����תΪKey ����
* @param byte[] ��ʽBlowfish����
* @return Key Blowfish����Key ����
 */
  public static Key decodeKey(byte[] keyByteCode){
       SecretKeySpec myKey=new SecretKeySpec(keyByteCode,"Blowfish");
       return myKey;
    }

}