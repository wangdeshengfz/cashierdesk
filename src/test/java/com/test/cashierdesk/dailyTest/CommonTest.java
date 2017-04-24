package com.test.cashierdesk.dailyTest;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bosssoft.itfinance.epay.v2.cashierdesk.common.util.JedisUtil;
import com.bosssoft.itfinance.epay.v2.cashierdesk.service.dao.SysConfigTestMapper;
import com.bosssoft.itfinance.epay.v2.cashierdesk.service.entity.SysConfigTest;
import com.test.cashierdesk.base.BaseTest;

/**
 * 其他组件连通性测试
 * @author Administrator
 *
 */
public class CommonTest extends BaseTest {

/*    @Autowired
    SysConfigTestMapper sysConfigTestMapper;*/
    
    
    /**
     * redis连通测试
     */
    @Test
    public void redisTest() {
/*    	String key = "TEST_PNAME";
    	String value = "zhangsan";
        JedisUtil.set(key, value,100);
        System.out.println("设置redis值成功，值="+value);
        String redisValue = String.valueOf(JedisUtil.get(key));
        System.out.println("获取值成功="+redisValue);*/
        
        
        String v = JedisUtil.get("PAYORDERID_" + "09a62dcbf66d9e8a05d30002");
        System.out.println("v=============="+v);
    }
    
    /**
     * 数据库连通测试
     * @throws UnsupportedEncodingException 
     */
    //@Test
  /*  public void dbTest() throws UnsupportedEncodingException {
        Map<String,Object> columnMap = new HashMap<String,Object>();
        columnMap.put("id", "15880084461");
        //List<SysConfigTest> scts = sysConfigTestMapper.queryTestList("ddd");
        SysConfigTest sct = sysConfigTestMapper.selectById(4L);
        System.out.println(Obj2String(sct));

          
        //新增      
        SysConfigTest sct = new SysConfigTest();
        sct.setId("12");
        sct.setCode("XB");
        sct.setValue("不明");
        sct.setNote("性别");
        sct.setCreatetime("20170419");
        int rst = sysConfigTestMapper.insertSelective(sct);
        //System.out.println("新增==============="+rst);
        
    }*/
    
    
}
