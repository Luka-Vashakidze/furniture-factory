package com.furniture.furniturefactory.furniturefactory;

public class Connection {
    private String id;
    
    public Connection(String id) {
        this.id = id;
    }
    
    public String create(String data) {
        System.out.println("creating: " + data);
        return "Created: " + data;
    }
    
    public String get(String id) {
        System.out.println("getting:" + id);
        return "Data for: " + id;
    }
    
    public String update(String id, String data) {
        System.out.println("updating: " + id);
        return "updated: " + id;
    }
    
    public void delete(String id) {
        System.out.println("Deleting: " + id);
    }
    
    public String toString() {
        return "connection-" + id;
    }
}

