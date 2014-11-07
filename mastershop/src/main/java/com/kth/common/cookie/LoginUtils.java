package com.kth.common.cookie;

import java.security.MessageDigest;

public class LoginUtils {

	// Encoding Method
	public byte[] digest(String alg, byte[] input) throws Exception {
		if (input == null) {
			throw new Exception("error");
		}
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance(alg);
		} catch (Exception e) {
			throw e;
		}

		return md.digest(input);
	}

	// Decoding Method
	public String getCryptoMD5String(String input) throws Exception {
		if (input == null) {
			throw new Exception(
					"Can't conver to Message Digest 5 String value!!");
		}
		String result = null;
		try {
			byte[] ret = digest("MD5", input.getBytes());
			result = getHexString(ret);
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	public String getHexString(byte[] input) throws Exception {
		StringBuffer rt = null;
		String tmp = "";
		try {
			rt = new StringBuffer("");
			for (int i = 0; i < input.length; i++) {
				tmp = Integer.toHexString(0xFF & input[i]);
				if (tmp.length() == 1) {
					rt.append("0");
					rt.append(tmp);
				} else {
					rt.append(tmp.toString());
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return rt.toString();
	}


	public boolean isValidCheckSum(String srcString, String checkSum)
			throws Exception {

		String srcMD5 = getCryptoMD5String(srcString);

		if (srcMD5.equals(checkSum)) {
			return true;
		}
		return false;
	}
}