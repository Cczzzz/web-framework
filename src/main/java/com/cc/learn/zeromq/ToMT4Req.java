package com.cc.learn.zeromq;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class ToMT4Req {


    public static void main(String[] args) {

        try (ZContext context = new ZContext(1)) {
            // Socket to talk to clients
            ZMQ.Socket socket = context.createSocket(ZMQ.REQ);
            socket.connect("tcp://127.0.0.1:5555");
            // Send a response

            while (!Thread.currentThread().isInterrupted()) {

                socket.send( "hello");
                // Block until a message is received
                byte[] reply = socket.recv(0);
                // Print the message
                System.out.println(
                        "c Received: [" + new String(reply, ZMQ.CHARSET) + "]"
                );

            }
        }
    }
}
