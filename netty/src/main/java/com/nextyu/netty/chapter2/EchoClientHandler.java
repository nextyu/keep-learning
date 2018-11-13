package com.nextyu.netty.chapter2;

import cn.hutool.core.lang.Console;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.StandardCharsets;

public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 在到服务器的连接已经建立之后将被调用
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocksrocksrocksrocksrocksrocksrocksrocks!",
                StandardCharsets.UTF_8));

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        // 当从服务器接收到一条消息时被调用；
        // 服务端返回的消息
        Console.log("response {}", byteBuf.toString(StandardCharsets.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
