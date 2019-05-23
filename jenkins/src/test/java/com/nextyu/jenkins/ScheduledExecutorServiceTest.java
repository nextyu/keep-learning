package com.nextyu.jenkins;

import cn.hutool.core.lang.Console;
import org.junit.Test;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// see https://stackoverflow.com/questions/24649842/scheduleatfixedrate-vs-schedulewithfixeddelay
public class ScheduledExecutorServiceTest {
    private static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4);

    @Test
    public void scheduleAtFixedRate() throws Exception {

        /*
        sleep 1 秒
        00:00:00 Start making coffee
        00:00:01 Finish making coffee
        00:00:05 Start making coffee
        00:00:06 Finish making coffee
        00:00:10 Start making coffee
        00:00:11 Finish making coffee
         */

        /*
        sleep 10 秒
        00:00:00 Start making coffee
        00:00:10 Finish making coffee
        00:00:10 Start making coffee
        00:00:20 Finish making coffee
        00:00:20 Start making coffee
        00:00:30 Finish making coffee
         */

        // 该方法第三个参数表示从上一个任务开始之后延时多少时间再执行
        // 根据上一次任务开始时间计算
        // 经过测试，如果上一次任务执行时间很久，就算到了执行时间，还是会等上一个任务执行完之后，下一个任务才开始执行
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            Console.log("{} Start making coffee", LocalTime.now());
            try {
//                TimeUnit.SECONDS.sleep(1);
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Console.log("{} Finish making coffee", LocalTime.now());
        }, 1, 5, TimeUnit.SECONDS);

        scheduledExecutorService.awaitTermination(1, TimeUnit.MINUTES);
    }

    @Test
    public void scheduleWithFixedDelay() throws Exception {

        /*
        00:00:00 Start making coffee
        00:00:10 Finish making coffee
        00:00:15 Start making coffee
        00:00:25 Finish making coffee
        00:00:30 Start making coffee
        00:00:40 Finish making coffee
         */

        // 该方法第三个参数表示从上一个任务结束之后延时多少时间再执行
        // 根据上一次任务结束时间计算
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            Console.log("{} Start making coffee", LocalTime.now());
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Console.log("{} Finish making coffee", LocalTime.now());
        }, 1, 5, TimeUnit.SECONDS);


        scheduledExecutorService.awaitTermination(1, TimeUnit.MINUTES);
    }
}
