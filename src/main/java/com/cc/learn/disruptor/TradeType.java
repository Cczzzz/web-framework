package com.cc.learn.disruptor;

/**
 * Created by chenchang on 2018/11/5.
 */
public enum TradeType {
    buy("buy"), sell("sell");
    String name;

    TradeType(String name) {
        this.name = name;
    }


}
