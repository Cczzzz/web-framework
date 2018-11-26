package com.cc.learn.disruptor;

import lombok.Data;

/**
 * Created by chenchang on 2018/11/5.
 */
@Data
public class Position {
    String secuCode;
    Double market;
    Long qty;
    Double price;
}
