package com.juc.demo.Lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author : chenjie
 * @date : 2019-12-09 10:50
 * @describe :
 */
public class ReadWriteTest {
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
    ReentrantReadWriteLock.ReadLock readLock = lock.readLock();

    public static void main(String[] args) throws InterruptedException {
        ReadWriteTest test = new ReadWriteTest();
        new Thread(() -> {
            try {
                test.read();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(500);
        new Thread(() -> {
            try {
                test.write();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(5000);

    }

    private void read() throws InterruptedException {
        readLock.lock();
        System.out.println("start-read");
        Thread.sleep(3000);
        System.out.println("start-end");
        readLock.unlock();
    }

    private void write() throws InterruptedException {
        writeLock.lock();
        System.out.println("write");
        writeLock.unlock();
    }
}
