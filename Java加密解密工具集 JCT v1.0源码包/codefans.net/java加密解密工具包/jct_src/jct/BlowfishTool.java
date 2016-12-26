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
 * <p>封装同Blowfish对称加密算法有关的方法，包括了使用创建Blowfish密码，使用Blowfish加密、解密, 使用PBE(基于口令的加密)存取blowfiwsh密码 </p>
 * @Copyright:WDSsoft
 * @ad:WDSsoft “企业多级数字签名系统”- 最佳的企业电子文档多级数字签名方案
 * @URL:www.wdssoft.com
 * @作者 吴东升 mdss@wdssoft.com bluesunday@sohu.com
 */



public class BlowfishTool {

  public BlowfishTool() {
  }
/**
 * 使用Blowfish加密
 * @param Key key:密码
 * @param byte[] text:明文
 * @return byte[]:密文
 */
  public static byte[] encryptReturnByte(Key key,byte[] text){
     try{
         Cipher cipher=Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
         cipher.init(Cipher.ENCRYPT_MODE,key);
         return cipher.doFinal(text);
     }catch(Exception e){  return null;    }
  }
/**
 * Blowfish解密
 * @param Key key：密码
 * @param byte[] text：密文
 * @return byte[] : 明文
 */
   public static byte[] decryptReturnByte(Key key,byte[] text){
     try{
         Cipher cipher=Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
         cipher.init(Cipher.DECRYPT_MODE,key);
         return cipher.doFinal(text);
     }catch(Exception e){  return null;}
   }
/**
 * 使用PBE保管Blowfish密码
 * @param Key targetKey: Blowfish密码
 * @param char[] password: 口令
 * @return Vector－element1:byte[]:密文-element2:byte[]:盐
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
 * 取出PBE加密的密钥
 * @param Vector encryptedKey－element1:byte[]:密文-element2:byte[]:盐
 * @param char[] password:口令
 * @return Key:Blowfish密码
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
   * 产生一个Blowfish密码
   * @return Key Blowfish 密码
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
 * 将Blowfish密码Key 对象转为byte[] 形式
 * @param Key Blowfish密码Key 对象
 * @return byte[]形式Blowfish密码
 */
  public static byte[] encodeKey(Key key){
      return key.getEncoded();
    }

/**
* 将byte[] 形式Blowfish密码转为Key 对象
* @param byte[] 形式Blowfish密码
* @return Key Blowfish密码Key 对象
 */
  public static Key decodeKey(byte[] keyByteCode){
       SecretKeySpec myKey=new SecretKeySpec(keyByteCode,"Blowfish");
       return myKey;
    }

}