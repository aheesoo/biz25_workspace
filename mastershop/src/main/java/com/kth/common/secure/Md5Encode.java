package com.kth.common.secure;

public class Md5Encode {
	
	/**
     * MD5 형태로 암호화
     */
    public static String makeMD5(String param) {
        StringBuffer md5 = new StringBuffer();
        try { 
            byte[] digest = java.security.MessageDigest.getInstance("MD5").digest(param.getBytes());
            for (int i = 0; i < digest.length; i++) {
                md5.append(Integer.toString((digest[i] & 0xf0) >> 4, 16));
                md5.append(Integer.toString(digest[i] & 0x0f, 16));
            }
        } catch(java.security.NoSuchAlgorithmException ne) {
            ne.printStackTrace();
        }
        //return md5.toString().toUpperCase();
        return md5.toString();
    } 

}