package factory;

import people.*;
import material.*;
import product.*;
import order.*;
import workload.*;

public class Factory {

    private static int factoryCounter;

    static {
        factoryCounter = 0;
    }

    // Material
    private Material[] materials;

    // Furniture
    private Furniture[] furnitureItems;
    private Chair[] chairs;
    private Table[] tables;

    // Employees
    private Employee[] employees;

    // Orders
    private Order[] orders;

    public Factory() {
        factoryCounter++;
    }

    // Getters and setters
    public Material[] getMaterials() {
        return materials;
    }

    public void setMaterials(Material[] materials) {
        this.materials = materials;
    }

    public Furniture[] getFurnitureItems() {
        return furnitureItems;
    }

    public void setFurnitureItems(Furniture[] furnitureItems) {
        this.furnitureItems = furnitureItems;
    }

    public Chair[] getChairs() {
        return chairs;
    }

    public void setChairs(Chair[] chairs) {
        this.chairs = chairs;
    }

    public Table[] getTables() {
        return tables;
    }

    public void setTables(Table[] tables) {
        this.tables = tables;
    }

    public Employee[] getEmployees() {
        return employees;
    }

    public void setEmployees(Employee[] employees) {
        this.employees = employees;
    }

    public Order[] getOrders() {
        return orders;
    }

    public void setOrders(Order[] orders) {
        this.orders = orders;
    }

    public static int getFactoryCounter() {
        return factoryCounter;
    }
}
