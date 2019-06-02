package com.cc.learn.zeromq;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 * Created by chenchang on 2018/12/26.
 */
public class ClientPull {

    public static void main(String[] args) {
        ZContext context = new ZContext();
        ZMQ.Socket socket = context.createSocket(ZMQ.PULL);
        socket.connect("tcp://127.0.0.1:5555");
        while (true) {
            byte[] recv = socket.recv();
            String msg = new String(recv, ZMQ.CHARSET);
            System.out.println("收到消息");
        }
    }
}
