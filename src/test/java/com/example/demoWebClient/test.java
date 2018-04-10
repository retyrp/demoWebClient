package com.example.demoWebClient;


import java.security.KeyFactory;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

import sun.misc.BASE64Decoder;


public class test {
    public static final String KEY_ALGORITHM = "RSA";
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
    private static final int KEY_SIZE = 1024;
    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "RSAPrivateKey";
    public static String str_pubK = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIPpddo7PKZHDTlX2LjLittHvtTnfwMWMKzMwUNZlBtwBkGdrsK8PvtTnb+Zc5jlHM6Fepv38oqqSYsSBVflob7WEK5rPk4faKA5DGTJ3ypxiYw8+BYBCzoLeJkA2b8ao15SgDwkLgeAko9gHomTKD3SR9ozFVBKAUJROkIGoLHQIDAQAB";
    public static String str_priK = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMg+l12js8pkcNOVfYuMuK20e+1Od/AxYwrMzBQ1mUG3AGQZ2uwrw++1Odv5lzmOUczoV6m/fyiqpJixIFV+WhvtYQrms+Th9ooDkMZMnfKnGJjDz4FgELOgt4mQDZvxqjXlKAPCQuB4CSj2AeiZMoPdJH2jMVUEoBQlE6QgagsdAgMBAAECgYBSl3ZpOfcTGIBs1LDWmTEm/FVNGNthZ41ZTAU0pLLUpv1zTDK+zIJhYVc/AuA77haRNJfLvN9ez5taG1yhxp61QQ9EGorNzSB6d4l+z245GaDu7M5NCub4yBLPhpbR8SXjgL5Xgp7WsiV9QBJkOzcaywCoEjWiytWfqKnuvV8M5QJBAO+BvXBZEPIP9I0sA6ctEK2p3co+kMHIYI9YSui7Z8lKoiYbGR8/3RblHLc3t5aAY+3X8/KQE/B/jZpU2LYFtoMCQQDWCLJzAMmdY9/Vw1IxZbPOeTpgPo548wG73aStOjzyXTQMnb+G4+Y1ysYtKK+xb089S23Y/xRanOOPP2k9ioXfAkBHVwzXZyA+CfSdDEw2Qp0iLWPL9yDlJ8tjRzhg5FmAwsNTuGSeQ0aWqD4KcQU1Rh793IJkcPgPfd7u5nD6nu8DAkBjUi3dCCSHEO2x+K2Plq5VxhO8zcq3faz0TOo6KKhXn8CjiiByrBcMJ0jHc/Xz1L9kpjgXVmdPlTZUJwzjLgDpAkEA5p2ibHpFNYwCR2cnj6rWahqe4MMm9PWH3FATPsx2SnGiGLGB7xL/lf/toxz9HzOGODL0exKZWfB+PHElgbaQBw==";

    /**
     * 使用getPublicKey得到公钥,返回类型为PublicKey
     *
     * @param key String to PublicKey
     * @throws Exception
     */
    public static PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = (new BASE64Decoder()).decodeBuffer(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    /**
     * 转换私钥
     *
     * @param key String to PrivateKey
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = (new BASE64Decoder()).decodeBuffer(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    //***************************签名和验证*******************************
    public static byte[] sign(byte[] data) throws Exception {
        PrivateKey priK = getPrivateKey(str_priK);
        Signature sig = Signature.getInstance(SIGNATURE_ALGORITHM);
        sig.initSign(priK);
        sig.update(data);
        return sig.sign();
    }

    public static boolean verify(byte[] data, byte[] sign) throws Exception {
        PublicKey pubK = getPublicKey(str_pubK);
        Signature sig = Signature.getInstance(SIGNATURE_ALGORITHM);
        sig.initVerify(pubK);
        sig.update(data);
        return sig.verify(sign);
    }

    //************************加密解密**************************
    public static byte[] encrypt(byte[] bt_plaintext) throws Exception {
        PublicKey publicKey = getPublicKey(str_pubK);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bt_encrypted = cipher.doFinal(bt_plaintext);
        return bt_encrypted;
    }

    public static byte[] decrypt(byte[] bt_encrypted) throws Exception {
        PrivateKey privateKey = getPrivateKey(str_priK);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] bt_original = cipher.doFinal(bt_encrypted);
        return bt_original;
    }

    //********************main函数：加密解密和签名验证*********************
    public static void main(String[] args) throws Exception {
//        String str_plaintext = "这是一段用来测试密钥转换的明文";
//        System.err.println("明文：" + str_plaintext);
//        byte[] bt_cipher = encrypt(str_plaintext.getBytes());
//        System.out.println("加密后：" + Base64.encodeBase64String(bt_cipher));
//
//        byte[] bt_original = decrypt(bt_cipher);
//        String str_original = new String(bt_original);
//        System.out.println("解密结果:" + str_original);

        String str = "[{RemoteUser:null,RemoteHost:0:0:0:0:0:0:0:1,RemotePort:61950,yourUri:/login/ooooooo/kjk/jihi,RemoteAddr:0:0:0:0:0:0:0:1,key:value}]";
        System.err.println("\n原文:" + str);
        byte[] signature = (new BASE64Decoder()).decodeBuffer("MTsvHqiVue3L5jCVOlim/uFCTNFl62gm6EZl6NAavU6k2xipHhdAwPlhKMv7+c51QczLW0kMYeSuD0RJBzISWF06jlIkFpqawVoTMeWm6slyQhtFTeO458fXQqBJStxiSxnkw/xGHG/SblpN+8iX5A0B4HcdjiA6o0qTFgU48zA=");

        System.out.println("产生签名：" + Base64.encodeBase64String(signature));
        boolean status = verify(str.getBytes(), signature);
        System.out.println("验证情况：" + status);
    }

}
