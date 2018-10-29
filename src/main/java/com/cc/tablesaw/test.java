package com.cc.tablesaw;

import com.cc.demo.dao.java.PmsEquityPositionMapper;
import com.cc.mapper.BaseMapper;
import com.esotericsoftware.reflectasm.MethodAccess;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.tablesaw.api.*;

import javax.persistence.Column;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chenchang on 2018/10/11.
 */
@Component
public class test implements InitializingBean {
    @Autowired
    private PmsEquityPositionMapper mapper;
    private static final String GET = "get";

    private static final String SET = "set";


    public static void main(String[] args) {
        double[] numbers = {1, 2, 3, 4};
        DoubleColumn nc = DoubleColumn.create("Test", numbers);
        System.out.println(nc);
        nc.append(10D);
        Table table = Table.create("dadad");
        table.structure();
        table.addColumns(nc);
        System.out.println(table);


    }


    private <T> Table loadTable(BaseMapper<T> mapper) throws Exception {
        Class modelClass = parseModelClass(mapper);
        if (modelClass == null) {
            throw new Exception("映射实体未找到");
        }

        Field[] fieldAccess = modelClass.getDeclaredFields();
        MethodAccess methodAccess = MethodAccess.get(modelClass);
        List<EntityColumn> entityColumns = new ArrayList<>();

        //筛选列
        for (Field field : fieldAccess) {
            Column column = field.getAnnotation(Column.class);
            if (column != null) {
                EntityColumn entityColumn = new EntityColumn();
                entityColumn.setMethodAccess(methodAccess);
                entityColumn.setField(field);
                entityColumns.add(entityColumn);
                entityColumn.setName(field.getName());
                entityColumn.setType(field.getType());
            }
        }
        //创建列
        for (int i = 0; i < entityColumns.size(); i++) {
            EntityColumn entityColumn = entityColumns.get(i);
            Field field = entityColumn.getField();
            tech.tablesaw.columns.Column column = createColumn(field);
            entityColumn.setColumn(column);

            String getMethodName = convertGet(field.getName());
            String setMethodName = convertSet(field.getName());
            int getMethodIndex = methodAccess.getIndex(getMethodName);
            int setMethodIndex = methodAccess.getIndex(setMethodName);

            entityColumn.setGetMethodName(getMethodName);
            entityColumn.setSetMethodName(setMethodName);
            entityColumn.setSetMethodIndex(setMethodIndex);
            entityColumn.setGetMethodIndex(getMethodIndex);
        }
        Table table = Table.create(modelClass.getSimpleName());
        List<T> list = mapper.selectAll();
        for (T t : list) {
            for (EntityColumn entityColumn : entityColumns) {
                Object res = methodAccess.invoke(t, entityColumn.getGetMethodIndex());
                tech.tablesaw.columns.Column column = entityColumn.getColumn();
                append(res, column);
            }
        }

        for (EntityColumn entityColumn : entityColumns) {
            table.addColumns(entityColumn.getColumn());
        }
        System.out.println(table);
        return table;
    }

    private String convertGet(String fieldName) {
        return toUpperCaseFirstOne(fieldName, GET);
    }

    private String convertSet(String fieldName) {
        return toUpperCaseFirstOne(fieldName, SET);
    }

    //首字母转大写
    public static String toUpperCaseFirstOne(String s, String prefix) {

        if (Character.isUpperCase(s.charAt(0)))
            return prefix + s;
        else
            return prefix + Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

    private tech.tablesaw.columns.Column append(Object value, tech.tablesaw.columns.Column column) throws Exception {
        Class fieldClass = value.getClass();
        if (fieldClass.equals(Date.class)) {
            value = ((Date) value).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        } else if (Number.class.isAssignableFrom(fieldClass)
                || fieldClass.equals(int.class)
                || fieldClass.equals(short.class)
                || fieldClass.equals(long.class)
                || fieldClass.equals(double.class)) {
            value = ((Number) value).doubleValue();
        }
        return column.appendObj(value);
    }

    private tech.tablesaw.columns.Column createColumn(Field field) throws Exception {
        Class<?> fieldClass = field.getType();
        tech.tablesaw.columns.Column column;
        if (Number.class.isAssignableFrom(fieldClass)
                || fieldClass.equals(int.class)
                || fieldClass.equals(short.class)
                || fieldClass.equals(long.class)
                || fieldClass.equals(double.class)) {
            column = DoubleColumn.create(field.getName());
        } else if (fieldClass.equals(String.class)) {
            column = StringColumn.create(field.getName());
        } else if (fieldClass.equals(Date.class)) {
            column = DateTimeColumn.create(field.getName());
        } else if (fieldClass.equals(boolean.class) || fieldClass.equals(Boolean.class)) {
            column = BooleanColumn.create(field.getName());
        } else {
            throw new Exception("无法解析的类型：" + field.getName() + "-" + fieldClass.getName());
        }
        return column;
    }

    private <T> Class parseModelClass(BaseMapper<T> mapper) {
        Class mapperClass = mapper.getClass();
        Class modelClass = null;
        //取得接口
        Type[] interfaces = mapperClass.getGenericInterfaces();
        for (Type anInterface : interfaces) {
            Class interfaceClass;
            //接口是baseMapper的子类
            if (anInterface instanceof Class && BaseMapper.class.isAssignableFrom(interfaceClass = (Class) anInterface)) {
                for (Type type : interfaceClass.getGenericInterfaces()) {
                    ParameterizedType parameterizedType;
                    //接口的接口是 BaseMapper
                    if (type instanceof ParameterizedType && ((parameterizedType = (ParameterizedType) type).getRawType()).equals(BaseMapper.class)) {
                        for (Type typeArgument : parameterizedType.getActualTypeArguments()) {
                            if (typeArgument instanceof Class) {
                                javax.persistence.Table table = (javax.persistence.Table) ((Class) typeArgument).getAnnotation(javax.persistence.Table.class);
                                if (table != null) {
                                    modelClass = (Class) parameterizedType.getActualTypeArguments()[0];
                                }
                            }
                        }
                    }
                }
            }
        }
        return modelClass;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        loadTable(mapper);
    }
}
