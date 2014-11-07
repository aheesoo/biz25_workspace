package com.kth.common.secure;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

public class StringEncrypter {

	private static final long serialVersionUID = 1L;


	/**
	* <P>�־��� �����ͷ�, �ش� �˰?�� ����� ���Ű(SecretKey)�� ���Ѵ�.</P>
	* 
	* @param algorithm TripleDES
	* @param keyData
	* @return
	*/
	public static Key generateKey(byte[] keyData) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException {
		KeySpec keySpec = new DESedeKeySpec(keyData);
		SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("TripleDES");
		SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);
		return secretKey;
	}
	
	public static String Encode(Key key, String inStr) throws Exception {
		StringBuffer sb = null;
		try {
			Cipher cipher = Cipher.getInstance("TripleDES/ECB/PKCS5Padding");
			//Cipher cipher = Cipher.getInstance("TripleDES/ECB/NoPadding");
			
			cipher.init(Cipher.ENCRYPT_MODE, key);
			
			byte[] plaintext = inStr.getBytes("UTF8");
			byte[] ciphertext = cipher.doFinal(plaintext);
			
			sb = new StringBuffer(ciphertext.length * 2);
			
			for(int i = 0; i < ciphertext.length; i++) {				
				String hex = "0" + Integer.toHexString(0xff & ciphertext[i]); 				
				sb.append(hex.substring(hex.length()-2));
			}
		}catch(Exception e) {
			System.out.println("Encode" + e.getMessage());
		}
		return sb.toString();
	}

	public static String Decode(Key key, String inStr) throws Exception {
		String text = null;
		
		try {
			byte[] b = new byte[inStr.length()/2];
			
			Cipher cipher = Cipher.getInstance("TripleDES/ECB/PKCS5Padding");			
			//Cipher cipher = Cipher.getInstance("TripleDES/ECB/NoPadding");
			
			cipher.init(Cipher.DECRYPT_MODE, key);
			
			for(int i = 0; i < b.length; i++) {
				b[i] = (byte)Integer.parseInt(inStr.substring(2*i, 2*i+2), 16);
			}
			
			byte[] decryptedText = cipher.doFinal(b);
			text = new String(decryptedText,"UTF8");
		} catch(Exception e) {
			System.out.println("Decode" + e.getMessage());
		}
		
		return text;
	}
	
	
	/*
	 * pw : ��ȣȭ�� ��ȣ(Ǯ���� �ϴ� ��ȣ)
	 * id : key �� id
	 */
	public static String PwDecode(String id, String pwd) throws Exception {
		String idKey = "";

		try {
			idKey = id;
			if(idKey.length() < 8) {
				int j = 8-idKey.length();
				
				for(int i = 0; i < j; i++) {
					idKey = idKey + "0";
				}				
			}

			/*
			 * ��ȣȭ
			 * ��ȣȭ Key : Password 8�ڸ� + ID 8�ڸ� + Password 8�ڸ�
			 */
			byte[] keyByte = new String(idKey.substring(0, 8) + "12345678" + idKey.substring(0, 8)).getBytes();				
			Key key2 = StringEncrypter.generateKey(keyByte);				
			pwd = StringEncrypter.Decode(key2, pwd); // ��ȣȭ
		}catch(Exception e) {
			System.out.println("PwDecode" + e.getMessage());
		}
		return pwd;
	}
	
	public static String PwEncode(String id, String pwd) throws Exception {
		String idKey = "";

		try {
			idKey = id;
			if(idKey.length() < 8) {
				int j = 8-idKey.length();
				
				for(int i = 0; i < j; i++) {
					idKey = idKey + "0";
				}				
			}

			/*
			 * ��ȣȭ
			 * ��ȣȭ Key : Password 8�ڸ� + ID 8�ڸ� + Password 8�ڸ�
			 */
			byte[] keyByte = new String(idKey.substring(0, 8) + "12345678" + idKey.substring(0, 8)).getBytes();	
			
			Key key2 = StringEncrypter.generateKey(keyByte);			
			pwd = StringEncrypter.Encode(key2, pwd);			
		}catch(Exception e) {
			System.out.println("PwEncode" + e.getMessage());
		}
		return pwd;
	}
}
