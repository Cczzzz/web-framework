package com.cc.mapper.customize;

import com.cc.mapper.provider.MyMapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * Created by chenchang on 2018/3/5.
 */
public interface SelectMapper<T> {

    @SelectProvider(
            type = MyMapper.class,
            method = "dynamicSQL"
    )
    List<T> selectBy(Object... var1);

    @SelectProvider(
            type = MyMapper.class,
            method = "dynamicSQL"
    )
    T selectOneBy(Object... var1);

    @SelectProvider(
            type = MyMapper.class,
            method = "dynamicSQL"
    )
    int selectCountBy(Object... var1);
}
