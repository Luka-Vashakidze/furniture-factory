package com.furniture.furniturefactory.furniturefactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
    private static ConnectionPool instance;
    private BlockingQueue<Connection> connections;
    
    private ConnectionPool(int size) {
        connections = new LinkedBlockingQueue<>();
        for (int i = 1; i <= size; i++) {
            connections.add(new Connection(String.valueOf(i)));
        }
    }
    
    public static synchronized ConnectionPool getInstance(int size) {
        if (instance == null) {
            instance = new ConnectionPool(size);
        }
        return instance;
    }
    
    public Connection getConnection() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Connection conn = connections.take();
        System.out.println(Thread.currentThread().getName() + " got " + conn);
        return conn;
    }
    
    public void releaseConnection(Connection connection) throws InterruptedException {
        connections.put(connection);
        System.out.println(Thread.currentThread().getName() + " released " + connection);
    }
}

