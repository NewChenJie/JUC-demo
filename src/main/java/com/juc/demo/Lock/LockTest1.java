package com.juc.demo.Lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : chenjie
 * @date : 2019-12-07 15:33
 * @describe : tryLock 尝试获取锁，根据返回值来判断是否获取到锁（可以设置尝试等待的时间）
 */
public class LockTest1 {
    ReentrantLock lock = new ReentrantLock();
    private int num = 0;

    public void add(CountDownLatch countDownLatch) throws InterruptedException {

        if (lock.tryLock(5,TimeUnit.SECONDS)) {
            try {
                num++;
            } finally {
                lock.unlock();
            }

        }
        countDownLatch.countDown();
    }

    public static void main(String[] args) throws InterruptedException {
        LockTest1 lockTest1 = new LockTest1();
        CountDownLatch countDownLatch = new CountDownLatch(10000);

        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                try {
                    lockTest1.add(countDownLatch);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        countDownLatch.await();
        System.out.println(lockTest1.num);
    }
}
