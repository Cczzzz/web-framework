package com.cc.mapper;

import com.cc.mapper.customize.SelectMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by chenchang on 2018/3/9.
 */
public interface BaseMapper<T> extends SelectMapper<T>,Mapper<T>{

}
