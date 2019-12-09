package com.juc.demo.Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : chenjie
 * @date : 2019-12-07 15:33
 * @describe : lockInterruptibly 可以被打断 并且自己释放锁
 */
public class LockTest2 {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Thread t = new Thread(()->{
            try {
                lock.lockInterruptibly();
                Thread.sleep(4000);
            } catch (InterruptedException e) {

            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " finished");
            }
        });
        t.start();
        t.interrupt();
        Thread.sleep(5000);
    }
}

    class RunIt2 implements Runnable{

    private  Lock lock = new ReentrantLock();
    @Override
    public void run() {
        try {
            lock.lock();
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " finished");
        }
    }
}
