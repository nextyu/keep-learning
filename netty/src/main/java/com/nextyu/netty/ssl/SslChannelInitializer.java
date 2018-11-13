package com.nextyu.netty.ssl;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;

public class SslChannelInitializer extends ChannelInitializer<SocketChannel> {

    private SslContext context;
    private boolean statTls;

    public SslChannelInitializer(SslContext context, boolean statTls) {
        this.context = context;
        this.statTls = statTls;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        SSLEngine sslEngine = context.newEngine(ch.alloc());
        ch.pipeline().addFirst("ssl", new SslHandler(sslEngine, statTls));
    }
}
