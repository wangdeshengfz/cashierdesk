package com.test.cashierdesk.generator;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.ConfigBaseEntity;
import com.baomidou.mybatisplus.generator.ConfigGenerator;

/**
 * @ClassName: GenerateCodeTest
 * @Description: TODO(mybatisplus 代码生成器工具类)
 * @author wangml
 * @date 2016-12-12 下午 04:11
 */
public class GenerateCodeTest {

    /* 生成代码包名 */
    private static final String PACKAGE_NAME = "com.bosssoft.itfinance.epay.v2.cashierdesk.service";

    public static void main(String[] args) {
        /* 生成代码 */
        AutoGenerator.run(getConfigGenerator());
    }

    /**
     * 获取配置文件
     *
     * @return 配置Props
     */
    private static Properties getProperties() {
        // 读取配置文件
        Resource resource = new ClassPathResource("jdbc.properties");
        Properties props = new Properties();
        try {
            props = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    private static ConfigGenerator getConfigGenerator() {
        ConfigGenerator cg;
        /* 获取 JDBC 配置文件 */
        Properties props = getProperties();
        if (props != null) {
        /* 配置 Mybatis-Plus 代码生成器 */
            cg = new ConfigGenerator();

        /* Mysql 数据库相关配置 */
            cg.setDbDriverName("com.mysql.jdbc.Driver");
            cg.setDbUrl(props.getProperty("db.url"));
            cg.setDbUser(props.getProperty("db.user"));
            cg.setDbPassword(props.getProperty("db.password"));
        /* 实体基类设置  */
            ConfigBaseEntity configBaseEntity = new ConfigBaseEntity();
            configBaseEntity.setPackageName("com.bosssoft.itfinance.epay.v2.cashierdesk.common.mybatis.entity");
            configBaseEntity.setClassName("BaseEntity");
            //公共字段设置 不设置则无公共字段
            configBaseEntity.setColumns(new String[]{""});
            cg.setConfigBaseEntity(configBaseEntity);

        /* 设置数据库前缀（例如`mp_user`生成实体类，无头部 为 MpUser.java , 头部=imall_ 为 User.java） 无头部设置成"" */
            //cg.setDbPrefix("wf_");
        /* 设置数据库是否使用mycat连接 仅仅是mysql才有的属性 */
            //cg.setIs_mycat(true);
            //cg.setBuliderModel(true);
        /* 是否覆盖当前已有文件（true为覆盖，false为不覆盖）*/
            //cg.setFileOverrideEntity(true);//生成beanName.java实体文件是否覆盖
            //cg.setFileOverrideMapper(true);//生成beanNameMapper.java文件文件是否覆盖
            //cg.setFileOverrideXml(true);//生成Mapper.xml文件文件是否覆盖
            
            cg.setFileOverride(true);

        /* 要生成代码的表名称（若为空就直接指定所有表，格式为逗号分割）*/
/*            cg.setTableNames(Arrays.asList(
                    "wf_interface_config"
            ));*/
            cg.setTableNames(new String[] {"sys_config_test"});
         /*
         * true 表示数据库设置全局下划线命名规则，默认 false
         * ------------------------------------------------------------------------------------
         * 【 开启该设置实体可无 @TableId(value = "test_id") 字段映射，启动配置对应也要开启 true 设置 】
         */
            cg.setDbColumnUnderline(false);
         /* 实体命名方式设置  0 为默认值
         *  0:跟数据库字段保持一致   1:驼峰式命名   2:下划线方式命名
         * */
            //cg.setNameType(2);

        /*
         * 表主键 ID 生成类型, 自增该设置无效。
         * <p>
         * IdType.AUTO             数据库ID自增
         * IdType.INPUT            外部输入ID（默认配置）【等于我们自己使用IdWorkService写入】不进入mybatis-plugs内部逻辑写ID
         * IdType.ID_WORKER        全局唯一ID，内容为空自动填充
         * IdType.UUID             全局唯一ID，内容为空自动填充
         * </p>
         */
            cg.setIdType(IdType.INPUT);
            String projectPath = System.getProperty("user.dir") + "\\src\\main\\java";

        /* 生成文件保存位置 */
            cg.setSaveDir(projectPath);

        /* 生成代码包路径 */
            cg.setEntityPackage(PACKAGE_NAME + ".entity"); //entity 实体包路径
            cg.setMapperPackage(PACKAGE_NAME + ".dao");    //dao 映射文件路径
            cg.setXmlPackage(PACKAGE_NAME + ".entity.xml"); //xml层路径
            cg.setControllerPackage("com.test.cashierdesk.generator.bak.controller");
            cg.setServiceImplPackage("com.test.cashierdesk.generator.bak.serviceImpl");
            cg.setServicePackage("com.test.cashierdesk.generator.bak.service");
        } else {
            System.out.println("配置文件读取失败");
            throw new RuntimeException("配置文件读取失败");
        }
        return cg;
    }

}