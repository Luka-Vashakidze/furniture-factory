package com.furniture.furniturefactory.furniturefactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectionPoolDemo {
    
    public static void main(String[] args) throws InterruptedException {
        demo1();
        demo2();
        demo3();
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
    
    private static void demo3() throws InterruptedException {
        ConnectionPool pool = ConnectionPool.getInstance(5);
        
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Connection conn = pool.getConnection();
                Thread.sleep(1000);
                conn.get("account1");
                pool.releaseConnection(conn);
                return "Task1 done";
            } catch (InterruptedException e) {
                return "error";
            }
        });
        
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Connection conn = pool.getConnection();
                Thread.sleep(1000);
                conn.update("account2", "data");
                pool.releaseConnection(conn);
                return "Task2 done";
            } catch (InterruptedException e) {
                return "error";
            }
        });
        
        CompletableFuture.allOf(future1, future2).join();
    }
}

