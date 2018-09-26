package com.cc.dbBeanToRedis;

import com.iquantex.orm.redis.tool.RedisBean;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by chenchang on 2018/9/26.
 */
public class CallQunatexGenerator {
    public static void call(List<RedisBean> redisBeanList, String javaesPath, String BEAN_PAHT, String DAO_PATH) throws IOException {
        for (RedisBean bean : redisBeanList) {
            HashMap<String, Object> redisBeanMap = new HashMap<>();
            redisBeanMap.put("bean", bean);
            redisBeanMap.put("package_bean", BEAN_PAHT);
            redisBeanMap.put("package_dao", DAO_PATH);
            redisBeanMap.put("dict", getDict());
            generateSrcBean(bean.getBean_name(), redisBeanMap, javaesPath, BEAN_PAHT);
            generateSrcDao(bean.getBean_name(), redisBeanMap, javaesPath, DAO_PATH);
        }
    }

    private static Map<String, String> getDict() {
        HashMap<String, String> dict = new HashMap<>();
        dict.put("Integer", "int");
        dict.put("Double", "double");
        dict.put("Long", "long");
        dict.put("String", "String");
        return dict;
    }

    private static void generateSrcDao(String bean_name, Map<String, Object> root, String javaesPath, String DAO_PATH) throws IOException {
        String packagePath = javaesPath + DAO_PATH.replaceAll("\\.", "/") + "/";
        File packageDir = new File(packagePath);
        if (!packageDir.exists()) {
            packageDir.mkdirs();
        }

        File daoFile = new File(packagePath + bean_name + "Dao.java");
        if (!daoFile.exists()) {
            daoFile.createNewFile();
        }

        FileWriter out = new FileWriter(daoFile);
        String content = TemplateUtil.getContent("dao4redis.ftl", root);
        out.write(content);
        out.close();
    }

    private static void generateSrcBean(String bean_name, Map<String, Object> root, String javaesPath, String BEAN_PAHT) throws IOException {
        String packagePath = javaesPath + BEAN_PAHT.replaceAll("\\.", "/") + "/";
        File packageDir = new File(packagePath);
        if (!packageDir.exists()) {
            packageDir.mkdirs();
        }

        File beanFile = new File(packagePath + bean_name + ".java");
        if (!beanFile.exists()) {
            beanFile.createNewFile();
        }

        FileWriter out = new FileWriter(beanFile);
        String content = TemplateUtil.getContent("bean4redis.ftl", root);
        out.write(content);
        out.close();
    }

}
