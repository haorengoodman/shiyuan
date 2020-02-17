package com.icegit.mythread;

import static java.lang.Thread.sleep;

/**
 * 多线程模拟售票
 */
public class TicketSeller implements Runnable {
    public int ticket = 100;
    String lock = "ooxx";

    @Override
    public void run() {
        try {

            while (ticket > 0) {
                synchronized (lock) {
                    if (ticket > 0) {
                        System.out.println(Thread.currentThread().getName() + "  ->  " + ticket);
                        ticket--;
                        sleep(100L);
                    } else {
                        System.out.println(Thread.currentThread().getName() + "  ->  票售完了。");
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TicketSeller seller = new TicketSeller();
        new Thread(seller).start();
        new Thread(seller).start();
        new Thread(seller).start();
    }

}
