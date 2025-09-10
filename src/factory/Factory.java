package factory;

import material.Material;
import order.Order;
import people.Employee;
import product.Furniture;

public class Factory {

    private Material[] materials;
    private Furniture[] furnitureItems;
    private Employee[] employees;
    private Order[] orders;

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
}
