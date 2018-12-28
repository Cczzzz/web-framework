package com.cc.learn.zeromq;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZMsg;

/**
 * Created by chenchang on 2018/12/26.
 */
public class ServerPush {
    public static void main(String[] args) {

        ZContext zContext = new ZContext(1);
        ZMQ.Socket socket = zContext.createSocket(ZMQ.PUSH);
        socket.bind("tcp://*:5555");
        ZMsg zFrames = new ZMsg();
        while (true) {
            String msg = "aaaa";
            boolean send = socket.send(msg, ZMQ.DONTWAIT);
            System.out.println("sned:" + msg);
        }
    }
}
