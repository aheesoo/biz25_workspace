package com.kth.common.secure;

import java.io.IOException;

import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;

public class Base64Utils {

   public static String encoder(String key) {
      return new BASE64Encoder().encode(key.getBytes());
   }

   public static String decoder(String cipher) throws IOException {
      return new String(new BASE64Decoder().decodeBuffer(cipher));
   } 
}
