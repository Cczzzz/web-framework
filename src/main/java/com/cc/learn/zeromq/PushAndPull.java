package com.cc.learn.zeromq;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class PushAndPull {
    public static void main(String[] args) {

        Runnable serve = () -> {
            try (ZContext context = new ZContext(1)) {
                // Socket to talk to clients
                ZMQ.Socket socket = context.createSocket(ZMQ.PUSH);
                socket.bind("tcp://*:5555");
                while (!Thread.currentThread().isInterrupted()) {
                    // Send a response
                    String msg = "Hello , world!";
                    socket.send(msg.getBytes(ZMQ.CHARSET), 0);
                }
            }
        };
        Thread serviceTh = new Thread(serve);
        serviceTh.start();

        try (ZContext context = new ZContext(1)) {
            // Socket to talk to clients
            ZMQ.Socket socket = context.createSocket(ZMQ.PULL);
            socket.connect("tcp://127.0.0.1:5555");
           // socket.subscribe("");

            while (!Thread.currentThread().isInterrupted()) {
                // Block until a message is received
                byte[] reply = socket.recv(0);
                System.out.println(
                        "c Received: [" + new String(reply, ZMQ.CHARSET) + "]"
                );
            }
        }


    }
}
