package com.furniture.furniturefactory.furniturefactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectionPoolDemo {
    
    public static void main(String[] args) throws InterruptedException {
        demo1();
        demo2();
    }
    
    private static void demo1() throws InterruptedException {
        ConnectionPool pool = ConnectionPool.getInstance(5);
        
        for (int i = 1; i <= 7; i++) {
            final int num = i;
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Connection conn = pool.getConnection();
                        Thread.sleep(1000);
                        conn.create("data" + num);
                        pool.releaseConnection(conn);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
        }
        
        Thread.sleep(10000);
    }
    
    private static void demo2() throws InterruptedException {
        ConnectionPool pool = ConnectionPool.getInstance(5);
        ExecutorService executor = Executors.newFixedThreadPool(7);
        
        for (int i = 1; i <= 7; i++) {
            final int num = i;
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Connection conn = pool.getConnection();
                        Thread.sleep(1000);
                        conn.get("id" + num);
                        pool.releaseConnection(conn);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        
        executor.shutdown();
        Thread.sleep(10000);
    }
}

