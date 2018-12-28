package com.cc.learn.MT4;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class MarketConsume {

    public static void main(String[] args) {

        ZContext context = new ZContext(1);
        ZMQ.Socket socket = context.createSocket(ZMQ.PULL);
        socket.bind("tcp://*:5558");
        while (!Thread.currentThread().isInterrupted()) {
            byte[] recv = socket.recv(1);
            if (recv != null) {
                System.out.println(
                        "c Received: [" + new String(recv, ZMQ.CHARSET) + "]"
                );
            }
        }
    }
}
