package com.juc.demo.Voliate;

/**
 * @author : chenjie
 * @date : 2019-12-07 15:44
 * @describe : 验证可见性: 线程变更状态就立即刷新值
 */
public class VoliateTest1 {
     boolean  flag;
//   Boolean  flag = false;
    public  void change(){
        flag = true;
        System.out.println("change:"+flag);
    }
    public  void work() {
        while (!flag) { }
        System.out.println("变了才能看见我");
    }

    public static void main(String[] args) throws InterruptedException {
        VoliateTest1 test = new VoliateTest1();
        new Thread(() -> test.work()).start();
         Thread.sleep(1000);
        new Thread(() -> test.change()).start();
        Thread.sleep(5000);
        System.out.println("结束");
    }

}
