package com.nextyu.netty.chapter2;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.lang.Console;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf in = (ByteBuf) msg;

        Console.log("body {}", in.toString(CharsetUtil.UTF_8));

//        ByteBuf response = Unpooled.copiedBuffer(new DateTime().toString("yyyy-MM-dd HH:mm:ss"), StandardCharsets.UTF_8);

        ctx.channel().eventLoop().schedule(new Runnable() {
            @Override
            public void run() {
                Console.log("aaa");
            }
        }, 10, TimeUnit.SECONDS);


        ctx.channel().eventLoop().scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Console.log("bbbb");
            }
        }, 0, 10, TimeUnit.SECONDS);

        ctx.write(in);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
