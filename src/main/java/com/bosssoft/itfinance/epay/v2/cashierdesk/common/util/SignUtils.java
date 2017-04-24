package com.bosssoft.itfinance.epay.v2.cashierdesk.common.util;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * <p>签名工具类</p>
 * <p>Created by qrf on 2016/8/12.</p>
 */
public class SignUtils {

    private static final String  SIGN_ALGORITHMS = "SHA1WithRSA";

    public static String signByMD5(Map<String,String> params,String key){
        return DigestUtils.md5Hex(String.format("%s%s", createLinkString(paramsFilter(params)), key));
    }

    public static boolean verifyByMD5(Map<String,String> params,String sign,String key){
        String result = DigestUtils.md5Hex(String.format("%s%s", createLinkString(paramsFilter(params)), key));
        return result.equals(sign);
    }

    public static String signByRSA(Map<String,String> params,String privateKey){
        try
        {
            String content = createLinkString(paramsFilter(params));
            PKCS8EncodedKeySpec priPKCS8 	= new PKCS8EncodedKeySpec( Base64.decodeBase64(privateKey) );
            KeyFactory keyf 				= KeyFactory.getInstance("RSA");
            PrivateKey priKey 				= keyf.generatePrivate(priPKCS8);

            java.security.Signature signature = java.security.Signature
                    .getInstance(SIGN_ALGORITHMS);

            signature.initSign(priKey);
            signature.update( content.getBytes() );

            byte[] signed = signature.sign();

            return Base64.encodeBase64String(signed);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean verifyByRSA(Map<String,String> params,String sign,String publicKey){
        try
        {
            String content = createLinkString(paramsFilter(params));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] encodedKey = Base64.decodeBase64(publicKey);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));


            java.security.Signature signature = java.security.Signature
                    .getInstance(SIGN_ALGORITHMS);

            signature.initVerify(pubKey);
            signature.update( content.getBytes() );

            boolean bverify = signature.verify( Base64.decodeBase64(sign) );
            return bverify;

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    private static Map<String, String> paramsFilter(Map<String, String> params) {
        HashMap result = new HashMap();
        if(params != null && params.size() > 0) {
            Iterator i$ = params.keySet().iterator();

            while(i$.hasNext()) {
                String key = (String)i$.next();
                String value = (String)params.get(key);
                if(value != null && !value.equals("") && !key.equalsIgnoreCase("sign")) {
                    result.put(key, value);
                }
            }

            return result;
        } else {
            return result;
        }
    }

    private static String createLinkString(Map<String, String> params) {
        ArrayList keys = new ArrayList(params.keySet());
        Collections.sort(keys);
        String prestr = "";

        for(int i = 0; i < keys.size(); ++i) {
            String key = (String)keys.get(i);
            String value = (String)params.get(key);
            prestr = prestr + key + "=" + value + "&";
        }

        if(prestr.length() > 1) {
            prestr = prestr.substring(0, prestr.length() - 1);
        }

        return prestr;
    }
}
