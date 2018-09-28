package com.cc.dbBeanToRedis;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.FieldDoc;
import com.sun.javadoc.RootDoc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Created by chenchang on 2018/9/25.
 */
public class DbBeanToRedisGenerator {
    private static RootDoc rootDoc;
    public final static Logger LOGGER = LoggerFactory.getLogger(DbBeanToRedisGenerator.class);

    public static class Doclet {
        public static boolean start(RootDoc rootDoc) {
            DbBeanToRedisGenerator.rootDoc = rootDoc;
            return true;
        }
    }

    /**
     * 显示DocRoot中的基本信息
     */
    public static Map<String, Map<String, String>> show() {
        ClassDoc[] classes = rootDoc.classes();
        Map<String, Map<String, String>> commentMap = new HashMap<>();
        for (ClassDoc classDoc : classes) {
            FieldDoc[] fieldDoc = classDoc.fields(false);
            Map<String, String> map = new HashMap<>(fieldDoc.length);
            for (FieldDoc doc : fieldDoc) {
                map.put(doc.name(), doc.commentText());
            }
            commentMap.put(classDoc.qualifiedName(), map);
        }
        return commentMap;
    }

    private final static String CLASS_SUFFIX = ".class";
    private final static int CLASS_SUFFIX_LENGTH = CLASS_SUFFIX.length();

    private final static Map<String, Class> STRING_CLASS_MAP = new HashMap<>();
    private final static List<String> STRING_JAVA_FILE_List = new ArrayList<>();
    //  private final static Map<String, Map<String, String>> comment_MAP = new HashMap<>();
    private static String projectPath;
    private static String classesPath;
    private static String javaesPath;
    private static String packagePath;
    private final static char PACK_SPLIT = '.';

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, IOException {
        URL url = Thread.currentThread().getContextClassLoader().getResource("");
        classesPath = url.getPath().substring(1, url.getPath().length() - 1);
        projectPath = url.getPath().substring(1, url.getPath().indexOf("target"));
        javaesPath = projectPath + "src/main/java/";
        packagePath = "com/iquantex/calculate/model";
        File root = new File(classesPath + File.separator + packagePath);
        scan(root);
        LOGGER.info("扫描完成。。。");
        List<String> docArgs = new ArrayList<>();

        docArgs.addAll(Arrays.asList("-doclet",
                Doclet.class.getName(),
                "-encoding", "utf-8", "-classpath",
                classesPath));

        for (String s : STRING_JAVA_FILE_List) {
            docArgs.add(s);
        }
        com.sun.tools.javadoc.Main.execute(docArgs.toArray(new String[docArgs.size()]));
        Map<String, Map<String, String>> comment_MAP = show();
//        List<RedisBean> redisBeanList = transformation(
//                c -> c.getAnnotation(Table.class) != null,
//                f -> f.getAnnotation(Column.class) != null,
//                f -> f.getAnnotation(Id.class) != null, null);
        //  CallQunatexGenerator.call(redisBeanList, javaesPath, BEAN_PAHT, DAO_PATH);
    }


    static String BEAN_PAHT = "com.iquantex.calculate.redis.bean";
    static String DAO_PATH = "com.iquantex.calculate.redis.dao";

//
//    private static List<RedisBean> transformation(Filter<Class> calssFilter, Filter<Field> redisColumnFiler, Filter<Field> FKeyFiler, Function<Class, List<List<String>>> getIndex) throws NoSuchFieldException, IllegalAccessException {
//        List<RedisBean> list = new ArrayList<>();
//        for (Map.Entry<String, Class> entry : STRING_CLASS_MAP.entrySet()) {
//            String name = entry.getKey();
//            Class c = entry.getValue();
//            RedisBean redisBean = new RedisBean();
//            redisBean.setType("A");
//            redisBean.setBase_bean_name("BaseHashBean");
//            redisBean.setBase_dao_name("BaseHashRedis");
//            redisBean.setBean_name(c.getSimpleName());
//
//            if (!calssFilter.accepts(c)) {
//                continue;
//            }
//            Field[] fields = c.getDeclaredFields();
//            Map<String, String> comment = comment_MAP.get(name);
//
//            List<RedisField> redisFields = new ArrayList<>();
//            Map<String, String> field2Type = new HashMap<>();
//            List<String> pkeys = new ArrayList<>();
//            List<List<String>> index = getIndex.apply(c);
//
//            for (Field field : fields) {
//                if (redisColumnFiler.accepts(field)) {
//                    RedisField redisField = new RedisField();
//                    redisField.setField_name(field.getName());
//
//                    String fieldType = field.getType().getSimpleName();
//                    if (field.getType().isAssignableFrom(Date.class)) {
//                        fieldType = Long.class.getSimpleName();
//                    }
//
//                    Field field_type = RedisField.class.getDeclaredField("field_type");
//                    field_type.setAccessible(true);
//                    field_type.set(redisField, fieldType);
//                    field2Type.put(field.getName(), fieldType);
//
//                    Field field_is_key = RedisField.class.getDeclaredField("field_is_key");
//                    field_is_key.setAccessible(true);
//                    if (FKeyFiler.accepts(field)) {
//                        field_is_key.set(redisField, 1);
//                        pkeys.add(field.getName());
//                    } else {
//                        field_is_key.set(redisField, 0);
//                    }
//                    redisField.setComment(comment.get(field.getName()));
//                    redisFields.add(redisField);
//                }
//            }
//            redisBean.setField_list(redisFields);
//            redisBean.setPkeys(pkeys);
//            redisBean.setIndex_list(index);
//            redisBean.setField2Type(field2Type);
//            list.add(redisBean);
//        }
//        return list;
//    }

    private static void scan(File file) throws ClassNotFoundException {
        if (file.isDirectory()) {
            File[] subfile = file.listFiles(f -> f.isDirectory() || f.getName().endsWith(CLASS_SUFFIX));
            if (subfile != null && subfile.length > 0) {
                for (File child : subfile) {
                    scan(child);
                }
            }
        } else {
            if (file.getName().endsWith(CLASS_SUFFIX)) {
                String filePath = file.getPath();
                String calssPath = file.getPath().substring(classesPath.length() + 1, filePath.length() - CLASS_SUFFIX_LENGTH).replace(File.separatorChar, PACK_SPLIT);
                Class c = Thread.currentThread().getContextClassLoader().loadClass(calssPath);
                LOGGER.info("扫描到类，{}", c.getName());
                STRING_CLASS_MAP.put(c.getName(), c);
                STRING_JAVA_FILE_List.add(javaesPath + calssPath.replace(PACK_SPLIT, File.separatorChar) + ".java");
            }
        }
    }
}
