package com.cc.learn.netty;

import org.checkerframework.checker.units.qual.K;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioServer {
    public static void main(String[] args) throws IOException {

        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.configureBlocking(false);
        ServerSocket serverSocket = channel.socket();
        InetSocketAddress address = new InetSocketAddress(8888);
        serverSocket.bind(address);
        Selector selector = Selector.open();
        channel.register(selector, SelectionKey.OP_ACCEPT);
        ByteBuffer byteBuffer = ByteBuffer.wrap("HI!".getBytes());
        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey next = iterator.next();
                iterator.remove();

                if (next.isAcceptable()) {
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) next.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ, byteBuffer.duplicate());
                    System.out.println("连接建立");
                }
                if (next.isWritable()){
                    SocketChannel socketChannel = (SocketChannel) next.channel();
                    ByteBuffer  byteBuffer1 = (ByteBuffer) next.attachment();
                    while (byteBuffer1.hasRemaining()){
                        if (socketChannel.write(byteBuffer) == 0){
                            break;
                        }
                    }

                }

            }
        }


    }
}
