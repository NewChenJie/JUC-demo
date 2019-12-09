package com.juc.demo.Lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author : chenjie
 * @date : 2019-12-09 16:29
 * @describe :
 */
public class CyclicBarrierTest {
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()-> System.out.println("出来吧！神龙！"));
        for (int i = 0; i < 14; i++) {
            new Thread(() -> {
                try {
                    System.out.println("GET一颗龙珠！");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
     Thread.sleep(5000);

    }
}
