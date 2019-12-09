package com.juc.demo.Lock;

import java.util.concurrent.Semaphore;

/**
 * @author : chenjie
 * @date : 2019-12-09 17:29
 * @describe :
 */
public class SemaphoreTest {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("限流控制");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                semaphore.release();
            }).start();
        }
        Thread.sleep(12000);
    }
}
