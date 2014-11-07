package com.kth.common.secure;
import sun.misc.*;

public class Base64 {

    /**
     * BASE64 Encoder
     * @param str
     * @return
     * @throws java.io.IOException
     */
    public static String base64Encode(String str)  throws java.io.IOException {
            sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
            byte[] b1 = str.getBytes();
            //byte[] b1 = str.getBytes("UTF-8");
            String result = encoder.encode(b1);
            return result ;
    }

    /**
     * BASE64 Encoder
     * @param str
     * @return
     * @throws java.io.IOException
     */
    public static String base64EncodeByte(byte[] b)  throws java.io.IOException {
            sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
            String result = encoder.encode(b);
            return result ;
    }
    
    /**
     * BASE64 Decoder
     * @param str
     * @return
     * @throws java.io.IOException
     */
    public static String base64Decode(String str)  throws java.io.IOException {
            sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
            byte[] b1 = decoder.decodeBuffer(str);
            String result = new String(b1);
            return result ;
    }  

} 