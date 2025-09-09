package factory;

import people.*;
import material.*;
import product.*;
import order.*;
import workload.*;
import service.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Factory {

    private static int factoryCounter;

    static {
        factoryCounter = 0;
    }

    //material
    private Material[] materials;

    //furniture
    private Furniture[] furnitureItems;
    private Chair[] chairs;
    private Table[] tables;

    //employees
    private Employee[] employees;
    private Worker[] workers;
    private Manager[] managers;

    //orders
    private Order[] orders;

    //workload
    private Workload[] workloads;

    public Factory() {
        factoryCounter++;

        materials = new Material[]{
                new Material("Wood", BigDecimal.valueOf(50), 100),
                new Material("Metal", BigDecimal.valueOf(30), 50),
                new Material("Fabric", BigDecimal.valueOf(20), 200)
        };

        furnitureItems = new Furniture[]{
                new Furniture("Chair Model A", BigDecimal.valueOf(100),
                        new Material[]{materials[0], materials[2]}),
                new Furniture("Table Model B", BigDecimal.valueOf(200),
                        new Material[]{materials[0], materials[1]})
        };

        chairs = new Chair[]{
                new Chair("Chair Model A", BigDecimal.valueOf(100),
                        new Material[]{materials[0], materials[2]}, 4, true, 120)
        };

        tables = new Table[]{
                new Table("Table Model B", BigDecimal.valueOf(200),
                        new Material[]{materials[0], materials[1]}, 150, 80, 75, true)
        };

        employees = new Employee[]{
                new Worker(1, "Luka", 2000.0, 5),
                new Worker(2, "Taia", 1800.0, 4),
                new Manager(3, "Levan", 2500.0, "Production", 500)
        };


        workers = new Worker[]{
                new Worker(1, "Luka", 2000, 5),
                new Worker(2, "Taia", 1800, 4)
        };
        workers[0].setCurrentWorkload(10);
        workers[1].setCurrentWorkload(15);

        managers = new Manager[]{
                new Manager(3, "Levan", 2500.0, "Production", 500)
        };

        orders = new Order[]{
                new Order(101, "Customer XYZ", furnitureItems, LocalDate.now())
        };

        workloads = new Workload[]{
                new Workload(workers[0], 20, LocalDateTime.now().plusDays(2)),
                new Workload(workers[1], 15, LocalDateTime.now().plusDays(3))
        };
    }

    public Material[] getMaterials() {
        return materials;
    }

    public Furniture[] getFurnitureItems() {
        return furnitureItems;
    }

    public Chair[] getChairs() {
        return chairs;
    }

    public Table[] getTables() {
        return tables;
    }

    public Employee[] getEmployees() {
        return employees;
    }

    public Worker[] getWorkers() {
        return workers;
    }

    public Manager[] getManagers() {
        return managers;
    }

    public Order[] getOrders() {
        return orders;
    }

    public Workload[] getWorkloads() {
        return workloads;
    }

    public static int getFactoryCounter() {
        return factoryCounter;
    }
}

