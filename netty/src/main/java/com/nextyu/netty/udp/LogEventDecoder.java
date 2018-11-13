package com.nextyu.netty.udp;

import cn.hutool.core.util.StrUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

public class LogEventDecoder extends MessageToMessageDecoder<DatagramPacket> {

    @Override
    protected void decode(ChannelHandlerContext ctx, DatagramPacket datagramPacket, List<Object> out) throws Exception {
        ByteBuf data = datagramPacket.content();
        int index = data.indexOf(0, data.readableBytes(), LogEvent.SEPARATOR);
        String fileName = data.slice(0, index).toString(CharsetUtil.UTF_8);
        String logMsg = StrUtil.trim(data.slice(index + 1, data.readableBytes()).toString(CharsetUtil.UTF_8));

        LogEvent logEvent = new LogEvent(datagramPacket.sender(), System.currentTimeMillis(), fileName, logMsg);
        out.add(logEvent);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

