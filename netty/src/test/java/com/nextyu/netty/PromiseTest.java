package com.nextyu.netty;

import io.netty.util.concurrent.*;

import java.util.concurrent.TimeUnit;

public class PromiseTest {
    public static void main(String[] args) {
        // 构造线程池
        EventExecutor executor = new DefaultEventExecutor();

        final Promise promise = new DefaultPromise<String>(executor);

        promise.addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("任务结束，结果：" + future.get());
            } else {
                System.out.println("任务失败，异常：" + future.cause());
            }
        }).addListener(future -> {
            System.out.println("任务结束，balabala...");
        });


        executor.submit(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 设置 promise 的结果
//            promise.setSuccess("呵呵哒");
            promise.setFailure(new RuntimeException());
        });

        // main 线程阻塞等待执行结果
        try {
//            promise.sync();
            promise.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
