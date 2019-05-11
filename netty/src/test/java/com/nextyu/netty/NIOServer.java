package com.nextyu.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class NIOServer {

    //    private static final Map<String, SocketChannel> ID_CHANNEL_MAP = new ConcurrentHashMap<>();
    private static final Map<SocketChannel, String> CHANNEL_ID_MAP = new ConcurrentHashMap<>();
    private static final Charset CHARSET = Charset.forName("utf-8");

    public static void main(String[] args) throws Exception {
        Selector selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(8888));

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            selectionKeys.forEach(selectionKey -> {
                SocketChannel client = null;
                try {
                    if (selectionKey.isAcceptable()) {
                        ServerSocketChannel serverSocketChannel1 = (ServerSocketChannel) selectionKey.channel();
                        client = serverSocketChannel1.accept();
                        client.configureBlocking(false);

                        client.register(selector, SelectionKey.OP_READ);

                        String key = UUID.randomUUID().toString();
//                        ID_CHANNEL_MAP.put(key, client);
                        CHANNEL_ID_MAP.put(client, key);

                        System.out.println(key + " 连接来了");

                    } else if (selectionKey.isReadable()) {
                        client = (SocketChannel) selectionKey.channel();

                        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                        int read = client.read(readBuffer);
                        if (read > 0) {
                            readBuffer.flip();

                            String msg = String.valueOf(CHARSET.decode(readBuffer).array());
                            String from = CHANNEL_ID_MAP.get(client);
                            String message = "from " + from + " msg " + msg;

                            System.out.println(message);

                            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);

                            for (Map.Entry<SocketChannel, String> entry : CHANNEL_ID_MAP.entrySet()) {
                                writeBuffer.clear();

                                String value = entry.getValue();
                                if (from.equalsIgnoreCase(value)) {
                                    continue;
                                }

                                writeBuffer.put(message.getBytes());
                                writeBuffer.flip();

                                entry.getKey().write(writeBuffer);
                            }


                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });

            selectionKeys.clear();

        }
    }
}
