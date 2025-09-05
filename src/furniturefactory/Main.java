package furniturefactory;

import people.*;
import material.*;
import product.*;
import order.*;
import workload.*;
import service.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        Material wood = new Material("Wood", BigDecimal.valueOf(50), 100);
        Material metal = new Material("Metal", BigDecimal.valueOf(30), 50);
        Material fabric = new Material("Fabric", BigDecimal.valueOf(20), 200);

        System.out.println("Material stock costs:");
        System.out.println("Wood total cost: $" + wood.calculateTotalCost());
        System.out.println("Metal total cost: $" + metal.calculateTotalCost());
        System.out.println("Fabric total cost: $" + fabric.calculateTotalCost());
        System.out.println();

        Material[] chairMaterials = {wood, fabric};
        Material[] tableMaterials = {wood, metal};

        Furniture chairFurniture = new Furniture("Chair Model A", BigDecimal.valueOf(100), chairMaterials);
        Furniture tableFurniture = new Furniture("Table Model B", BigDecimal.valueOf(200), tableMaterials);

        Chair chair1 = new Chair(chairFurniture, 4, true, 120);
        Table table1 = new Table(tableFurniture, 150, 80, 75, true);

        Furniture[] orderItems = {chairFurniture, tableFurniture};

        Employee emp1 = new Employee(1, "Luka", 2000);
        Employee emp2 = new Employee(2, "Taia", 1800);

        Worker worker1 = new Worker(emp1, 5);
        worker1.setCurrentWorkload(10);

        Worker worker2 = new Worker(emp2, 4);
        worker2.setCurrentWorkload(15);

        Manager manager = new Manager(emp1, "Production", 500);

        Order order1 = new Order(101, "Customer XYZ", orderItems, LocalDate.now());

        Workload workload1 = new Workload(worker1, 20, LocalDateTime.now().plusDays(2));
        Workload workload2 = new Workload(worker2, 15, LocalDateTime.now().plusDays(3));

        OrderService orderService = new OrderService();
        orderService.placeOrder(order1);

        System.out.println("Total orders placed: " + OrderService.getOrderCounter());
        System.out.println();

        System.out.println("Manager " + manager.getEmployee().getName() +
                " total salary with bonus: $" + manager.calculateSalaryWithBonus());
        System.out.println();

        System.out.println("Worker " + worker1.getEmployee().getName() +
                " overloaded? " + workload1.isOverloaded());

        System.out.println("Worker " + worker2.getEmployee().getName() +
                " overloaded? " + workload2.isOverloaded());
        System.out.println();

        System.out.println("Chair total price: $" + chair1.calculateTotalPrice());
        System.out.println("Table total price: $" + table1.calculateTotalPrice());
        System.out.println();

        worker1.assignTask(5);
        System.out.println(worker1.getEmployee().getName() + " new workload after assigning 5 hours: " + worker1.getCurrentWorkload());

        worker1.finishTask(3);
        System.out.println(worker1.getEmployee().getName() + " workload after finishing 3 hours: " + worker1.getCurrentWorkload());
    }
}
