package com.cc.dbBeanToRedis;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;

/**
 * Created by chenchang on 2018/9/25.
 */
public class TemplateUtil {
    private static final Logger LOG = LoggerFactory.getLogger(TemplateUtil.class);
    private static final Configuration cfg;

    public static String getContent(String tpFile, Object root) {
        try {
            Template e = cfg.getTemplate(tpFile);
            StringWriter sw = new StringWriter();
            e.process(root, sw);
            return sw.toString();
        } catch (Exception var4) {
            LOG.error("freemarket模板渲染失败, tpFile<{}>, errorMsg<{}>", tpFile, var4.getMessage());
            throw new RuntimeException("freemarket模板渲染失败", var4);
        }
    }

    static {
        cfg = new Configuration(Configuration.VERSION_2_3_22);

        try {
            cfg.setClassForTemplateLoading(TemplateUtil.class, "/");
        } catch (Exception var1) {
            var1.printStackTrace();
            System.exit(-1);
        }

    }
}
