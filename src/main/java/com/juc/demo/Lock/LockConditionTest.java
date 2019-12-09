package com.juc.demo.Lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : chenjie
 * @date : 2019-12-09 11:14
 * @describe : await,signal 在lock中实现，并且会释放掉锁，支持多个condition
 */
public class LockConditionTest {
    private int total = 0;
    private Lock lock = new ReentrantLock();
    private Condition product = lock.newCondition();
    private Condition consume = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        final LockConditionTest ad = new LockConditionTest();

        new Thread(() -> {
            try {
                ad.use();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "B").start();

        new Thread(() -> {
            try {
                ad.add();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "A").start();
        Thread.sleep(10000);
    }

    private void use() throws InterruptedException {
        while (true) {
            lock.lock();
            if (total == 0) {
                System.out.println("======================");
                product.signal();
                consume.await();
            }
            if (total > 0) {
                total--;
                System.out.println(total);
            }
            lock.unlock();
        }

    }

    private void add() throws InterruptedException {
        while (true) {
            lock.lock();
            if (total == 10) {
                System.out.println("======================");
                consume.signal();
                product.await();
            }
            if (total < 10) {
                total++;
                System.out.println(total);
            }
            lock.unlock();
        }
    }
}
