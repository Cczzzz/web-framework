package com.cc.mapper;

import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.entity.EntityTable;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.util.StringUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static tk.mybatis.mapper.mapperhelper.EntityHelper.getEntityTable;

/**
 * Created by chenchang on 2018/3/12.
 */
public class SqlHelper extends tk.mybatis.mapper.mapperhelper.SqlHelper{

    public static String addParams(Class<?> entityClass, String entityName) {

        StringBuilder sql = new StringBuilder();
        sql.append("<bind name=\"");
        sql.append(entityName);
        sql.append("\" value=\"@cc.framework.common.mapper.SqlHelper@parseParams(_parameter, \'");
        sql.append(entityClass.getCanonicalName());
        sql.append("\')\"/>");
        return sql.toString();
    }

    public static String orderByClause(String entityName) {
        StringBuilder sql = new StringBuilder();
        sql.append("<if test=\"");
        if(StringUtil.isNotEmpty(entityName)) {
            sql.append(entityName).append(".");
        }

        sql.append("orderBy != null\"> order by ${");
        if(StringUtil.isNotEmpty(entityName)) {
            sql.append(entityName).append(".");
        }

        sql.append("orderBy");
        sql.append("}</if>");
        return sql.toString();
    }

    public static String whereAllIfColumns(String entityName, Class<?> entityClass, boolean empty) {
        StringBuilder sql = new StringBuilder();
        sql.append("<where>");
        Set columnList = EntityHelper.getColumns(entityClass);
        Iterator var5 = columnList.iterator();

        while(var5.hasNext()) {
            EntityColumn column = (EntityColumn)var5.next();
            sql.append(getIfNotNull(entityName, column, " AND " + column.getColumnEqualsHolder(entityName), empty));
        }

        sql.append("</where>");
        return sql.toString();
    }

    public static Map<String, Object> parseParams(Object parameter, String entityFullName) {
        Map paramsMap;
        try {
            Map e = (Map)parameter;
            Object[] params = (Object[])((Object[])e.get("array"));
            paramsMap = getMap(params);
        } catch (Exception var5) {
            throw new RuntimeException("解析sql参数失败", var5);
        }

        parseOrderBy(paramsMap, entityFullName);
        return paramsMap;
    }

    private static Map<String, Object> getMap(Object... args) {
        if(args.length % 2 != 0) {
            throw new RuntimeException("sql参数个数应是2的倍数");
        } else {
            HashMap map = new HashMap();

            for(int i = 0; i < args.length; i += 2) {
                String key = args[i].toString();
                map.put(key, args[i + 1]);
            }

            return map;
        }
    }


    public static void parseOrderBy(Map<String, Object> pMap, String entityFullName) {
        try {
            Object e = pMap.get("orderBy");
            if(null != e) {
                Class entityClass = Class.forName(entityFullName);
                StringBuilder sql = new StringBuilder();
                String[] var5 = e.toString().split(",");
                int var6 = var5.length;

                for(int var7 = 0; var7 < var6; ++var7) {
                    String orderBy = var5[var7];
                    String property = orderBy;
                    String order = " asc";
                    orderBy = orderBy.toLowerCase();
                    int index = orderBy.contains(" asc")?orderBy.indexOf(" asc"):(orderBy.contains(" desc")?orderBy.indexOf(" desc"):-1);
                    if(index > -1) {
                        order = orderBy.substring(index);
                        property = property.substring(0, index).trim();
                    }

                    String column = property2Column(entityClass, property);
                    column = column == null?property:column;
                    if(StringUtil.isNotEmpty(sql.toString())) {
                        sql.append(",");
                    }

                    sql.append(column + order);
                }

                if(StringUtil.isNotEmpty(sql.toString())) {
                    pMap.put("orderBy", sql.toString());
                }
            }

        } catch (Exception var13) {
            throw new RuntimeException("解析orderBy参数失败");
        }
    }

    public static String property2Column(Class<?> entityClass, String property) {
        EntityTable table = getEntityTable(entityClass);
        Iterator var3 = table.getEntityClassColumns().iterator();

        EntityColumn column;
        do {
            if(!var3.hasNext()) {
                return null;
            }

            column = (EntityColumn)var3.next();
        } while(!column.getProperty().equals(property));

        return column.getColumn();
    }



}
