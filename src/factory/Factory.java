package factory;

import material.Material;
import order.Order;
import people.Employee;
import product.Furniture;

import java.util.List;

public class Factory {

    private List<Material> materials;
    private List<Furniture> furnitureItems;
    private List<Employee> employees;
    private List<Order> orders;

    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

    public List<Furniture> getFurnitureItems() {
        return furnitureItems;
    }

    public void setFurnitureItems(List<Furniture> furnitureItems) {
        this.furnitureItems = furnitureItems;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
