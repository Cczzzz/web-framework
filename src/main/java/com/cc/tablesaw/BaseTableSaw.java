package com.cc.tablesaw;

import com.cc.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import tech.tablesaw.api.Table;

import java.util.List;

/**
 * Created by chenchang on 2018/10/17.
 */
public abstract class BaseTableSaw<T> implements BaseMapper<T> {

    Table table;
    List<EntityColumn> entityColumnList;

    public BaseTableSaw(Table table, List<EntityColumn> entityColumnList) {
        this.table = table;
        this.entityColumnList = entityColumnList;
    }

    public Table getTable() {
        return table;
    }

    @Override
    public List<T> selectBy(Object... var1) {
        //Table result = table.w
        return null;
    }

    @Override
    public T selectOneBy(Object... var1) {
        return null;
    }

    @Override
    public int selectCountBy(Object... var1) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Object key) {
        return 0;
    }

    @Override
    public int delete(T record) {
        return 0;
    }

    @Override
    public int insert(T record) {
        return 0;
    }

    @Override
    public int insertSelective(T record) {
        return 0;
    }

    @Override
    public boolean existsWithPrimaryKey(Object key) {
        return false;
    }

    @Override
    public List<T> selectAll() {
        return null;
    }

    @Override
    public T selectByPrimaryKey(Object key) {
        return null;
    }

    @Override
    public int selectCount(T record) {
        return 0;
    }

    @Override
    public List<T> select(T record) {
        return null;
    }

    @Override
    public T selectOne(T record) {
        return null;
    }

    @Override
    public int updateByPrimaryKey(T record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(T record) {
        return 0;
    }

    @Override
    public int deleteByExample(Object example) {
        return 0;
    }

    @Override
    public List<T> selectByExample(Object example) {
        return null;
    }

    @Override
    public int selectCountByExample(Object example) {
        return 0;
    }

    @Override
    public T selectOneByExample(Object example) {
        return null;
    }

    @Override
    public int updateByExample(@Param("record") T record, @Param("example") Object example) {
        return 0;
    }

    @Override
    public int updateByExampleSelective(@Param("record") T record, @Param("example") Object example) {
        return 0;
    }

    @Override
    public List<T> selectByExampleAndRowBounds(Object example, RowBounds rowBounds) {
        return null;
    }

    @Override
    public List<T> selectByRowBounds(T record, RowBounds rowBounds) {
        return null;
    }
}
