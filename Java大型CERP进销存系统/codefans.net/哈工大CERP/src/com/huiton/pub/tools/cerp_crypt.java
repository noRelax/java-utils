package com.huiton.pub.tools;

public class cerp_crypt{
   // used to encode the input string
   private static final int CRY_KEY = 15;

   // used to limit the length of the final encoded_pass
   private static final int STR_LEN = 32;

   // used to change the cry_key to every byte
   private static final int MUL_NUM = 3;

   // encode pass
   public static String get_encoded_pass(String s){
        char [] buff1 = new char[STR_LEN];
   	buff1 = s.toCharArray();
   	int b_len = buff1.length;
   	for (int i=0;i<b_len && i<STR_LEN;i++)
   		buff1[i] = (char) ((int)buff1[i]^ (CRY_KEY+MUL_NUM*i));
   	return new String(buff1);
   }

   // decode pass
   public static String get_decoded_pass(String s){
        char [] buff1 = new char[STR_LEN];
   	buff1 = s.toCharArray();
   	int b_len = buff1.length;
   	for (int i=0;i<b_len && i<STR_LEN;i++)
   		buff1[i] = (char) ((int)buff1[i]^ (CRY_KEY+MUL_NUM*i));
   	return new String(buff1);
   }

   // test program
   public static void main(String [] s){

   	for(int len=s.length, i=0; i<len; i++)
   	{
   		System.out.println("input is="+s[i]);
   		String t = get_encoded_pass(s[i]);
   		System.out.println("encoded_pass="+t);
   		System.out.println("decoded_pass="+get_decoded_pass(t));
   	}
   }
}
