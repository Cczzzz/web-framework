package com.cc.learn.disruptor;

import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by chenchang on 2018/11/5.
 */
public class Processor {

    static ReentrantLock lock = new ReentrantLock();

    public static void calc(Deal deal, Map<String, Position> positionMap) {
        if (lock.tryLock()){
            Position position = positionMap.get(deal.getSecuCode());
            if (position == null) {
                position = new Position();
                position.setSecuCode(deal.getSecuCode());
                position.setQty(0L);
                position.setMarket(0D);
                position.setPrice(0D);
            }
            if (deal.getTradeType().equals(TradeType.buy)) {
                position.setMarket(deal.getPrice() * deal.getQty());
                position.setPrice(deal.getPrice());
                position.setQty(position.getQty() + deal.getQty());
            } else {
                position.setMarket(position.getMarket() - deal.getPrice() * deal.getQty());
                position.setPrice(deal.getPrice());
                position.setQty(position.getQty() - deal.getQty());
            }
            positionMap.put(position.getSecuCode(), position);
            deal.setOutTime(System.currentTimeMillis());
            lock.unlock();
        }else {
            System.out.print("啊啊啊啊啊啊，我被锁上了");
        }
    }
}
