package com.cc.mapper.provider;


import com.cc.mapper.SqlHelper;
import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;

public class MyMapper extends MapperTemplate {

    public MyMapper(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    public String selectBy(MappedStatement ms) {
        Class entityClass = this.getEntityClass(ms);
        this.setResultType(ms, entityClass);
        StringBuilder sql = new StringBuilder();
        this.addWhereParams(sql, entityClass, "_params");
        return sql.toString();
    }


    public String selectOneBy(MappedStatement ms) {
        Class entityClass = this.getEntityClass(ms);
        this.setResultType(ms, entityClass);
        StringBuilder sql = new StringBuilder();
        this.addWhereParams(sql, entityClass, "_params");
        return sql.toString();
    }


    public String selectCountBy(MappedStatement ms) {
        Class entityClass = this.getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.addParams(entityClass, "_params"));
        sql.append(SqlHelper.selectCount(entityClass));
        sql.append(SqlHelper.fromTable(entityClass, this.tableName(entityClass)));
        sql.append(SqlHelper.whereAllIfColumns("_params", entityClass, this.isNotEmpty()));
        return sql.toString();
    }


    public void addWhereParams(StringBuilder sql, Class entityClass, String entityName) {
        sql.append(SqlHelper.addParams(entityClass, entityName));
        sql.append(SqlHelper.selectAllColumns(entityClass));
        sql.append(SqlHelper.fromTable(entityClass, this.tableName(entityClass)));
        sql.append(SqlHelper.whereAllIfColumns(entityName, entityClass, this.isNotEmpty()));
        sql.append(SqlHelper.orderByClause(entityName));
    }
}
