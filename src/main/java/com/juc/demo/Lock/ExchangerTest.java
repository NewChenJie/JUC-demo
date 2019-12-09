package com.juc.demo.Lock;

import java.util.concurrent.Exchanger;

/**
 * @author : chenjie
 * @date : 2019-12-09 17:37
 * @describe :
 */
public class ExchangerTest {


    public static void main(String[] args) throws InterruptedException {
        Exchanger<String> exchanger = new Exchanger<>();
        new Thread(() -> {
            try {
                String result = exchanger.exchange("before");
                System.out.println(result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                 exchanger.exchange("change");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(9000);
    }
}
