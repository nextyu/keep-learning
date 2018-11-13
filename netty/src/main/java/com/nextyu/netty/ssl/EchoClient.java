package com.nextyu.netty.ssl;

import cn.hutool.core.lang.Console;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class EchoClient {
    public static void main(String[] args) {

        String host = "127.0.0.1";
        int port = 8080;

        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();

        b.group(group)
                .channel(NioSocketChannel.class)
                .remoteAddress(new InetSocketAddress(host, port))
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new EchoClientHandler());
                    }
                });

        try {
            // 连接到远程节点，阻塞等待直到连接完成
            ChannelFuture f = b.connect().sync();
            f.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        Console.log("连接成功");
                    } else {
                        Console.log("链接失败");
                        future.cause().printStackTrace();
                    }
                }
            });

            // 阻塞，直到Channel 关闭
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭线程池并且释放所有的资源
                group.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
