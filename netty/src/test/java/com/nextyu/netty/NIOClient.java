package com.nextyu.netty;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NIOClient {
    public static void main(String[] args) throws Exception {
        Selector selector = Selector.open();

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8888));

        socketChannel.register(selector, SelectionKey.OP_CONNECT);

        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            selectionKeys.forEach(selectionKey -> {
                try {
                    SocketChannel client;

                    if (selectionKey.isConnectable()) {
                        client = (SocketChannel) selectionKey.channel();
                        if (client.isConnectionPending()) {
                            client.finishConnect();

                            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                            ExecutorService executorService = Executors.newSingleThreadExecutor();
                            executorService.submit(()->{
                                while (true) {
                                    writeBuffer.clear();

                                    InputStreamReader input = new InputStreamReader(System.in);
                                    BufferedReader bufferedReader = new BufferedReader(input);
                                    String msg = bufferedReader.readLine();

                                    writeBuffer.put(msg.getBytes());

                                    writeBuffer.flip();

                                    client.write(writeBuffer);
                                }
                            });


                        }

                        client.register(selector, SelectionKey.OP_READ);
                    } else if (selectionKey.isReadable()) {
                        client = (SocketChannel) selectionKey.channel();
                        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                        int read = client.read(readBuffer);

                        if (read > 0) {
                            String msg = new String(readBuffer.array(), 0, read);
                            System.out.println(msg);
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
