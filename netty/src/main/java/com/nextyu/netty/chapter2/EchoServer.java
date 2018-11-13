package com.nextyu.netty.chapter2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class EchoServer {
    public static void main(String[] args) {
        int port = 8080;

        EchoServerHandler echoServerHandler = new EchoServerHandler();

        EventLoopGroup bossGroup = new NioEventLoopGroup(); // EpollEventLoopGroup Epoll—用于 Linux 的本地非阻塞传输
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
//                .option(ChannelOption.SO_BACKLOG, 1024)
                .localAddress(new InetSocketAddress(port))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    /*
                    “you make use of a special class, ChannelInitializer. This is key. When a new connection is accepted
                    , a new child Channel will be created, and the ChannelInitializer will add an instance
                    of your EchoServerHandler to the Channel’s ChannelPipeline. As we explained earlier,
                    this handler will receive notifications about inbound messages.”

                    摘录来自: Norman Maurer Marvin Allen Wolfthal. “Netty in Action。” Apple Books.
                     */
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(echoServerHandler);
                    }
                });

        try {
            // 绑定端口，同步等待成功
            ChannelFuture f = serverBootstrap.bind().sync();

            // 等待服务端监听端口关闭
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 优雅退出，释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
