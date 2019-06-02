package com.cc.learn.netty.EchoServer;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;


public class Echo {
    public static void main(String[] args) {

    }

    public void start() throws InterruptedException {
        EchoHandler handler = new EchoHandler();
        EventLoopGroup group = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(group)
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(9999))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(handler);
                    }
                });

        ChannelFuture future = serverBootstrap.bind().sync();
        future.channel().closeFuture().sync();
    }
    public void startclient(){
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .remoteAddress(new InetSocketAddress("127.0.0.1",9999))
                .


    }


    public static class EchoHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuffer in = (ByteBuffer) msg;
            System.out.println("收到消息：" + new String(in.array(), CharsetUtil.UTF_8));
            ctx.write(in);
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);

        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            super.exceptionCaught(ctx, cause);
        }
    }

    public static class clientHandler extends SimpleChannelInboundHandler<ByteBuf>{

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
                System.out.printf("client received%s",msg.toString(CharsetUtil.UTF_8));

        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            //super.channelActive(ctx);
            ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!",CharsetUtil.UTF_8));
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
          //  super.exceptionCaught(ctx, cause);
        }
    }

}
