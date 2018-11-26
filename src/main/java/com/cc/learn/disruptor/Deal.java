package com.cc.learn.disruptor;

import lombok.Data;

/**
 * Created by chenchang on 2018/11/5.
 */
@Data
public class Deal  {
    String secuCode;
    Long qty;
    Double price;
    TradeType tradeType;
    long sendTime;
    long outTime;
}
