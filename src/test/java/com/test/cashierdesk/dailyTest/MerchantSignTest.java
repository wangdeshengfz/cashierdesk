package com.test.cashierdesk.dailyTest;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import com.bosssoft.itfinance.epay.v2.cashierdesk.common.util.SignUtils;

/**
 * 基础工具校验
 */
public class MerchantSignTest{

    /** md5密钥*/
    public static final String MD5_KEY = "testMD5Key";
    /** rsa公钥*/
    public static final String RSA_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC8rPqGGsar+BWI7vAtaaDOqphy" +
            "41j5186hCU9DcchV4HWiv0HvQ3KXAEqHfZiAHZSyMSRMmDZVnqJwCVWFvKUPqU1R" +
            "sCPZ9Imk+9ZXVkM3DDdw74v/s6YMNx8cTuxybRCJUfOKbyC79cnHgmQqqkODv+En" +
            "prBtNKE4k8g90jNmbwIDAQAB";

    /** rsa私钥*/
    public static final String RSA_PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALys+oYaxqv4FYju" +
            "8C1poM6qmHLjWPnXzqEJT0NxyFXgdaK/Qe9DcpcASod9mIAdlLIxJEyYNlWeonAJ" +
            "VYW8pQ+pTVGwI9n0iaT71ldWQzcMN3Dvi/+zpgw3HxxO7HJtEIlR84pvILv1yceC" +
            "ZCqqQ4O/4SemsG00oTiTyD3SM2ZvAgMBAAECgYBLToeX6ywNC7Icu7Hljll+45yB" +
            "jri+0CJLKFoYw1uA21xYnxoEE9my54zX04uA502oafDhGYfmWLDhIvidrpP6oalu" +
            "URb/gbV5Bdcm98gGGVgm6lpK+G5N/eawXDjP0ZjxXb114Y/Hn/oVFVM9OqcujFSV" +
            "+Wg4JgJ4Mmtdr35gYQJBAPbhx030xPcep8/dL5QQMc7ddoOrfxXewKcpDmZJi2ey" +
            "381X+DhuphQ5gSVBbbunRiDCEcuXFY+R7xrgnP+viWcCQQDDpN8DfqRRl+cUhc0z" +
            "/TbnSPJkMT/IQoFeFOE7wMBcDIBoQePEDsr56mtc/trIUh/L6evP9bkjLzWJs/kb" +
            "/i25AkEAtoOf1k/4NUEiipdYjzuRtv8emKT2ZPKytmGx1YjVWKpyrdo1FXMnsJf6" +
            "k9JVD3/QZnNSuJJPTD506AfZyWS6TQJANdeF2Hxd1GatnaRFGO2y0mvs6U30c7R5" +
            "zd6JLdyaE7sNC6Q2fppjmeu9qFYq975CKegykYTacqhnX4I8KEwHYQJAby60iHMA" +
            "YfSUpu//f5LMMRFK2sVif9aqlYbepJcAzJ6zbiSG5E+0xg/MjEj/Blg9rNsqDG4R" +
            "ECGJG2nPR72O8g==";

    /**
     * 签名校验
     */
    @Test
    public void testSign(){
        Map<String,String> params = new HashMap<String, String>();
        params.put("param1","key1");
        params.put("param2","key2");

        String md5Sign = SignUtils.signByMD5(params,MD5_KEY);
        System.out.println(SignUtils.verifyByMD5(params,md5Sign,MD5_KEY)+"-"+md5Sign);

        String rsaSign = SignUtils.signByRSA(params,RSA_PRIVATE_KEY);
        System.out.println(SignUtils.verifyByRSA(params,rsaSign,RSA_PUBLIC_KEY)+"-"+rsaSign);
    }

}
