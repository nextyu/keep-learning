package com.nextyu.netty.udp;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;

public class LogEventBroadcaster {
    private final EventLoopGroup group;
    private final Bootstrap bootstrap;
    private final File file;

    public LogEventBroadcaster(InetSocketAddress address, File file) {
        group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioDatagramChannel.class)
                .option(ChannelOption.SO_BROADCAST, true)
//                .option(ChannelOption.SO_BACKLOG, 1024)
                .handler(new LogEventEncoder(address));
        this.file = file;
    }

    public void run() throws Exception {
        Channel ch = bootstrap.bind(0).sync().channel();
        for (; ; ) {
            long pointer = 0;
            long length = file.length();
            if (length < pointer) {
                // file was reset
                // If necessary, sets the file pointer to the last byte of the file
                pointer = length;
            } else if (length > pointer) {
                // Content was added
                RandomAccessFile raf = new RandomAccessFile(file, "r");
                // Sets the current file pointer so nothing old is sent
                raf.seek(pointer);
                String line;
                while ((line = raf.readLine()) != null) {
                    // For each log entry,  writes a LogEvent } to the channel
                    Console.log("{}", line);
                    ch.writeAndFlush(new LogEvent(null, -1, file.getAbsolutePath(), StrUtil.trim(line)));
                }
                // Stores the current position within the file
                pointer = raf.getFilePointer();
                raf.close();
            }

            try {
                // Sleeps for 1 second. If interrupted, exits the loop; else restarts it.
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.interrupted();
                break;
            }
        }
    }

    public void stop() {
        group.shutdownGracefully();
    }

    public static void main(String[] args) throws Exception {
        int port = 9999;
        String path = "/Users/zaobai/Downloads/111.txt";
//        String path = "/Users/zaobai/IdeaProjects/keep-learning/netty/src/main/resources/index.html";
        LogEventBroadcaster broadcaster = new LogEventBroadcaster(new InetSocketAddress("255.255.255.255", port), new File(path));

        try {
            broadcaster.run();
        } finally {
            broadcaster.stop();
        }
    }

}
