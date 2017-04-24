package com.test.cashierdesk.base;

import java.io.UnsupportedEncodingException;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml"})
@Transactional
public abstract class BaseTest{
    
    public String Obj2String(Object object) throws UnsupportedEncodingException{
        SerializerFeature feature = SerializerFeature.DisableCircularReferenceDetect;
        byte[] bytes = JSON.toJSONBytes(object, feature);
        return new String(bytes, "UTF-8");
    }
    
}
