package com.cc.learn.zeromq;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class REQAndREP {

    public static void main(String[] args) {

        Runnable serve = () -> {
            try (ZContext context = new ZContext(1)) {
                // Socket to talk to clients
                ZMQ.Socket socket = context.createSocket(ZMQ.REP);
                socket.bind("tcp://127.0.0.1:5555");
                while (!Thread.currentThread().isInterrupted()) {
                    // Block until a message is received
                    byte[] reply = socket.recv(0);
                    // Send a response
                    String response = "Hello, world!";
                    socket.send(response.getBytes(ZMQ.CHARSET), 0);

                    // Print the message
                    System.out.println(
                            "serve Received: [" + new String(reply, ZMQ.CHARSET) + "]"
                    );

                }
            }
        };
        Thread serviceTh = new Thread(serve);
        serviceTh.start();


        try (ZContext context = new ZContext(1)) {
            // Socket to talk to clients
            ZMQ.Socket socket = context.createSocket(ZMQ.REQ);
            socket.connect("tcp://127.0.0.1:5555");

            while (!Thread.currentThread().isInterrupted()) {

                String response = "Hello, world!";
                socket.send(response.getBytes(ZMQ.CHARSET), 0);
                // Block until a message is received
                byte[] reply = socket.recv(0);
                Thread.sleep(1000);
                // Print the message
                System.out.println(
                        "c Received: [" + new String(reply, ZMQ.CHARSET) + "]"
                );
            }
        } catch (InterruptedException e) {

        }


    }
}
