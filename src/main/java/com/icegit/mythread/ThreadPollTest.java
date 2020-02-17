package com.icegit.mythread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建指定线程数的线程池
 * 参考连接：https://www.jianshu.com/p/7ab4ae9443b9
 */
public class ThreadPollTest {

    public static void main(String[] args) {
        ExecutorService executer = Executors.newFixedThreadPool(5);
        for(int i =0;i <10;i++){
            executer.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("thread id is: " + Thread.currentThread().getId());
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        executer.shutdown();
    }


}
