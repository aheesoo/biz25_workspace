package com.includesys.sm.util;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class LinkGenerator {
	static final String HEXES = "0123456789abcdef";
	
	String encodeHexString(byte[] bytes) {
		if (bytes == null) {
			return null;
		}
		final StringBuilder hex = new StringBuilder( 2 * bytes.length );
		for (final byte b : bytes) {
			hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(HEXES.charAt((b & 0x0F)));
		}
		return hex.toString();
	}

	byte[] decodeHexString(String hexStr) {
		int length = hexStr.length();
		byte[] bytes = new byte[length / 2];
		for (int i = 0; i < length; i += 2) {
			bytes[i / 2] = (byte)((Character.digit(hexStr.charAt(i), 16) << 4)
								  + Character.digit(hexStr.charAt(i+1), 16));
		}
		return bytes;
	}

	String generateMD5Hash(String value) {
		return encodeHexString(generateMD5HashBytes(value));
	}

	byte[] generateMD5HashBytes(String value) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return md.digest(value.getBytes("UTF-8"));
		}
		catch (Exception e) {
			return null;
		}
	}

	public String getCurrentTime() {
		return Integer.toHexString((int)(System.currentTimeMillis() / 1000)).toLowerCase();
	}

	public String getExpireTime(long timeout) {
		return Integer.toHexString((int)(System.currentTimeMillis() / 1000 + timeout)).toLowerCase();
	}

	public String createToken(String fileName, String expireTime, String secretKey) {
		return generateMD5Hash(secretKey + fileName + expireTime);
	}

	public String createTokenEx(String fileName, String expireTime, String secretKey, String userStr) {
		return generateMD5Hash(secretKey + fileName + expireTime + userStr);
	}

	public String generateSecureToken(String fileName, String expireTime, String secretKey) {
		return generateMD5Hash(secretKey + fileName + expireTime);
	}

	public String encryptFileName(String fileName, String expireTime, String secretKey) {
		try {
			SecretKeySpec key = new SecretKeySpec(generateMD5HashBytes(secretKey), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key);

			String plainStr = expireTime + fileName;
			byte[] plainBytes = plainStr.getBytes("UTF-8");
			byte[] cipherBytes = new byte[cipher.getOutputSize(plainBytes.length)];
			int cipherBytesLength = cipher.update(plainBytes, 0, plainBytes.length, cipherBytes, 0);
			cipher.doFinal(cipherBytes, cipherBytesLength);

			return encodeHexString(cipherBytes);
		}
		catch (Exception e) {
			return null;
		}
	}

	public String decryptFileName(String cipherStr, String expireTime, String secretKey) {
		try {
			SecretKeySpec key = new SecretKeySpec(generateMD5HashBytes(secretKey), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key);

			byte[] cipherBytes = decodeHexString(cipherStr);
			byte[] plainBytes = new byte[cipher.getOutputSize(cipherBytes.length)];
			int plainBytesLength = cipher.update(cipherBytes, 0, cipherBytes.length, plainBytes, 0);
			plainBytesLength += cipher.doFinal(plainBytes, plainBytesLength);

			String plainStr = new String(plainBytes, 0, plainBytesLength, "UTF-8");
			if (plainStr.startsWith(expireTime))
				return plainStr.substring(expireTime.length());
			else
				return null;
		}
		catch (Exception e) {
			return null;
		}
	}
}
