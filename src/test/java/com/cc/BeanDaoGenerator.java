package com.cc;

import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @auther: jialong  by 2018/7/12 13:40
 */
public class BeanDaoGenerator {
    /**
     * 生成bean dao 工具
     * @throws Exception
     */
    @Test
    public void operation() throws Exception{
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        URL url = ClassLoader.getSystemResource("mybatis.xml");
        File configFile = new File(url.getPath());
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
        warnings.stream().forEach(System.out::println);
    }
}
