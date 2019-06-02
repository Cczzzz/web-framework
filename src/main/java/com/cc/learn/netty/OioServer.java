package com.cc.learn.netty;

import org.apache.commons.lang3.CharUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class OioServer {


    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(1234);
        new Thread(() -> {
            while (true) {
                Socket socket = null;
                try {
                    socket = serverSocket.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                System.out.println("建立连接");
                SocketConnect connect = new SocketConnect(socket);
                connect.accept();
            }
        }).start();
//
        Socket socket = new Socket("127.0.0.1", 1234);
        OutputStream outputStream = socket.getOutputStream();
        InputStream inputStream = socket.getInputStream();
        outputStream.write("hollo word".getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        socket.shutdownOutput();

        byte[] bytes = new byte[1024];
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                int len;
                if ((len = inputStream.read(bytes)) == -1) break;
                sb.append(new String(bytes, 0, len, StandardCharsets.UTF_8));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("收到回复 " + sb);

    }

    public static class SocketConnect implements Runnable {
        OutputStream output;
        InputStreamReader streamReader;
        InputStream intput;
        Socket socket;

        public SocketConnect(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            while (true) {
                byte[] bytes = new byte[1024];
                StringBuilder sb = new StringBuilder();
                while (true) {
                    try {
                        int len;
                        if ((len = intput.read(bytes)) == -1) break;
                        sb.append(new String(bytes, 0, len, StandardCharsets.UTF_8));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (sb.length() > 0) {
                    System.out.println("收到消息" + sb);
                    try {
                        output.write(sb.toString().getBytes(StandardCharsets.UTF_8));
                        output.flush();
                        socket.shutdownOutput();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public void accept() {
            try {
                streamReader = new InputStreamReader(socket.getInputStream());
                output = socket.getOutputStream();
                intput = socket.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            new Thread(this).start();
        }
    }

}
