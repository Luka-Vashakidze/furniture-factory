package furniturefactory;

import factory.Factory;
import people.*;
import material.*;
import product.*;
import order.*;
import workload.*;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        Factory factory = new Factory();

        System.out.println("Factory initialized. Total factories: " + Factory.getFactoryCounter());
        System.out.println();

        Material[] materials = factory.getMaterials();
        System.out.println("Materials:");
        for (int i = 0; i < materials.length; i++) {
            Material m = materials[i];
            System.out.println(m.getName() + " total cost: $" + m.calculateTotalCost());
        }
        System.out.println();


        Furniture[] furnitureItems = factory.getFurnitureItems();
        System.out.println("Furniture:");
        for (int i = 0; i < furnitureItems.length; i++) {
            Furniture f = furnitureItems[i];
            System.out.println(f.getName() + " total cost: $" + f.calculateTotalCost());
        }
        System.out.println();

        Chair[] chairs = factory.getChairs();
        System.out.println("Chairs:");
        for (int i = 0; i < chairs.length; i++) {
            Chair c = chairs[i];
            System.out.println(c.getName() + " legs: " + c.getLegs() + ", armrest: " + c.hasArmrest() +
                    ", total price: $" + c.calculateTotalCost());
        }
        System.out.println();

        Table[] tables = factory.getTables();
        System.out.println("Tables:");
        for (int i = 0; i < tables.length; i++) {
            Table t = tables[i];
            System.out.println(t.getName() + " size: " + t.getLength() + "x" + t.getWidth() + "x" + t.getHeight() +
                    ", drawers: " + t.hasDrawers() + ", total price: $" + t.calculateTotalCost());
        }
        System.out.println();

        Employee[] employees = factory.getEmployees();
        System.out.println("Employees:");
        for (int i = 0; i < employees.length; i++) {
            Employee e = employees[i];
            System.out.println(e.getName() + " (ID: " + e.getId() + "), salary: $" + e.getSalary());
        }
        System.out.println();

        Worker[] workers = factory.getWorkers();
        System.out.println("Workers:");
        for (int i = 0; i < workers.length; i++) {
            Worker w = workers[i];
            System.out.println(w.getName() + " (skill: " + w.getSkillLevel() + ", workload: " + w.getCurrentWorkload() + ")");
        }
        System.out.println();

        Manager[] managers = factory.getManagers();
        System.out.println("Managers:");
        for (int i = 0; i < managers.length; i++) {
            Manager m = managers[i];
            System.out.println(m.getName() + " (department: " + m.getDepartment() + "), salary with bonus: $" + m.calculateSalaryWithBonus());
        }
        System.out.println();

        Order[] orders = factory.getOrders();
        System.out.println("Orders:");
        for (int i = 0; i < orders.length; i++) {
            Order o = orders[i];
            System.out.println("Order #" + o.getOrderId() + " by " + o.getCustomerName() +
                    ", total: $" + o.calculateTotalPrice());
        }
        System.out.println();

        Workload[] workloads = factory.getWorkloads();
        System.out.println("Workloads:");
        for (int i = 0; i < workloads.length; i++) {
            Workload wl = workloads[i];
            System.out.println("Worker " + wl.getWorker().getName() +
                    " assigned hours: " + wl.getHoursAssigned() +
                    ", overloaded? " + wl.isOverloaded());
        }
    }
}
