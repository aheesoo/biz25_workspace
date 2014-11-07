package com.includesys.sm.util;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

public class OpenapiAesSecure {

	final String password = "09042014";
	/**
	 * 암호화
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public String aesEncode(String str) throws Exception {       
   
		  byte[] seedB = password.getBytes();   
		  SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");   
		  sr.setSeed(seedB);     
		  KeyGenerator kgen = KeyGenerator.getInstance("AES");   
		  kgen.init(128, sr); 
		  
		  SecretKey skey = kgen.generateKey();   
		  String keyString = Hex.encodeHexString(skey.getEncoded());   
		  SecretKeySpec skeySpec = new SecretKeySpec(skey.getEncoded(), "AES");      
		  Cipher cipher = Cipher.getInstance("AES");   
		  cipher.init(Cipher.ENCRYPT_MODE, skeySpec);      
		  byte[] encrypted =  cipher.doFinal(str.getBytes());             
		  String encString =  Hex.encodeHexString(encrypted);             
		  System.out.println("encrypted string: [" + keyString + "] " + encString ); 
		  return encString;
		 }

		/**
		 * 복호화
		 * @param u
		 * @throws Exception
		 */
		public String aesDecode(String str) throws Exception{

		  byte[] seedB = password.getBytes();   
		  SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");   
		  sr.setSeed(seedB);       
		  KeyGenerator kgen = KeyGenerator.getInstance("AES");   
		  kgen.init(128, sr); 
		   
		  SecretKey skey = kgen.generateKey();   
		  String keyString = Hex.encodeHexString(skey.getEncoded());   
		  SecretKeySpec skeySpec = new SecretKeySpec(skey.getEncoded(), "AES");        
		  Cipher cipher = Cipher.getInstance("AES");   
		  cipher.init(Cipher.DECRYPT_MODE, skeySpec);      
		  byte[] decrypted =  cipher.doFinal(Hex.decodeHex(str.toCharArray()));             
		  System.out.println("decrypted string: [" + keyString + "] " + new String(decrypted));
		  return new String(decrypted);
		} 
		
		public static void main(String[] args) {
			String test1 = "test";
			String test2 = "";
			OpenapiAesSecure a = new OpenapiAesSecure();
			try {
				test2 = a.aesEncode(test1);
				System.out.println("aesEncode="+test2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				System.out.println("aesEncode="+a.aesDecode(test2));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

}
