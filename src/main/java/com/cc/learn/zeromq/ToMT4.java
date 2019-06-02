package com.cc.learn.zeromq;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class ToMT4 {

    public static void main(String[] args) {

        try (ZContext context = new ZContext(1)) {
            // Socket to talk to clients
            ZMQ.Socket socket = context.createSocket(ZMQ.PUSH);
            socket.bind("tcp://*:5555");
            // Send a response
            while (!Thread.currentThread().isInterrupted()) {
                // Block until a message is received
                // Print the message
                socket.send("nnnnn",ZMQ.DONTWAIT);
                System.out.println("aaaaaaaaaa");
            }
        }
    }
}
