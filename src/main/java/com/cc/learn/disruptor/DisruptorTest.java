package com.cc.learn.disruptor;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.apache.catalina.util.ParameterMap;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

/**
 * Created by chenchang on 2018/11/5.
 */
public class DisruptorTest {

    static Map<String, Position> positionMap = new ParameterMap<>();

    public static void main(String[] args) {
        List list = Collections.synchronizedList(new ArrayList<>());
        MyFactory myFactory = new MyFactory();
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        Disruptor<DisruptorEvent> disruptor = new Disruptor(myFactory, 1048576, threadFactory, ProducerType.SINGLE, new BusySpinWaitStrategy());
        disruptor.handleEventsWith((event, sequence, endOfBatch) -> {
            Deal deal = (Deal) event.getEvent();
            Processor.calc(deal, positionMap);
        });
        disruptor.start();
        RingBuffer<DisruptorEvent> ringBuffer = disruptor.getRingBuffer();
        LongEventProducerWithTranslator producer = new LongEventProducerWithTranslator(ringBuffer);



        List<Deal> events = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i <= 10000000; i++) {
            Deal deal = new Deal();
            deal.setSecuCode("aaaa");
            deal.setPrice((double) random.nextInt(100));
            deal.setQty((long) i);
            deal.setTradeType(i % 2 == 0 ? TradeType.buy : TradeType.sell);
            events.add(deal);
        }

        for (Deal deal : events) {
            deal.setSendTime(System.currentTimeMillis());
            producer.send(deal);
        }
        Map<Long, List<Deal>> collect = events.stream().collect(Collectors.groupingBy(Deal::getOutTime));
        int i = 1;
        for (List<Deal> dealList : collect.values()) {
            System.out.print("第" + i++ + "毫秒\t");
            System.out.print("每毫秒：" + dealList.size()+"\t");
            Long time = 0L;
            for (Deal deal : dealList) {
                time += deal.getOutTime() - deal.getSendTime();
            }
            System.out.println("平均耗时：" + time / dealList.size());
        }


    }

    public static class LongEventProducerWithTranslator {
        private final RingBuffer<DisruptorEvent> ringBuffer;

        public LongEventProducerWithTranslator(RingBuffer<DisruptorEvent> ringBuffer) {
            this.ringBuffer = ringBuffer;
        }

        public void send(Object obj) {
            long sequence = this.ringBuffer.next();

            try {
                DisruptorEvent event = this.ringBuffer.get(sequence);
                event.setEvent(obj);
            } finally {
                this.ringBuffer.publish(sequence);
            }

        }
    }

}
