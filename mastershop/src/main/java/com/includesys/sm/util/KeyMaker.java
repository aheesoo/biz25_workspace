package com.includesys.sm.util;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
 
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;
 
public class KeyMaker 
{
	/**
     * <p>해당 알고리즘에 사용할 비밀키(SecretKey)를 생성한다.</p>
     * @return 비밀키(SecretKey)
     */
    public static Key generateKey(String algorithm) throws NoSuchAlgorithmException 
    {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        SecretKey key = keyGenerator.generateKey();
        return key;
    }
    
    /**
     * <p>주어진 데이터로, 해당 알고리즘에 사용할 비밀키(SecretKey)를 생성한다.</p>
     * @param algorithm DES/DESede/TripleDES/AES
     * @param keyData
     * @return 비밀키(SecretKey)
     */
    public static Key generateKey(String algorithm, byte[] keyData) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException 
    {
        String upper = algorithm.toUpperCase();
        if ("DES".equals(upper)) 
        {
            KeySpec keySpec = new DESKeySpec(keyData);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(algorithm);
            SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);
            return secretKey;
        } 
        else if ("DESede".equals(upper) || "TripleDES".equals(upper)) 
        {
            KeySpec keySpec = new DESedeKeySpec(keyData);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(algorithm);
            SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);
            return secretKey;
        } 
        else 
        {
            SecretKeySpec keySpec = new SecretKeySpec(keyData, algorithm);
            return keySpec;
        }
    }  
}
