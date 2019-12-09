package com.juc.demo.Lock;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author : chenjie
 * @date : 2019-12-09 13:23
 * @describe :
 */
public class ScheduledThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(6);
        pool.scheduleWithFixedDelay(() -> {
            System.out.println("11111");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(LocalDateTime.now());
        }, 0,2, TimeUnit.SECONDS);
        Thread.sleep(30000);
    }
}
