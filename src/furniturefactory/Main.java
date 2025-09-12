package furniturefactory;

import factory.Factory;
import interfaces.Buildable;
import interfaces.Discountable;
import interfaces.Payable;
import interfaces.WorkAssignable;
import people.*;
import material.*;
import product.*;
import order.*;
import service.EmployeeService;
import service.OrderService;
import workload.Workload;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        Factory factory = new Factory();

        Material wood = new Material("Wood", BigDecimal.valueOf(50), 100);
        Material metal = new Material("Metal", BigDecimal.valueOf(30), 50);
        Material fabric = new Material("Fabric", BigDecimal.valueOf(20), 200);
        factory.setMaterials(new Material[]{wood, metal, fabric});

        Chair chair = new Chair("Chair Model A", BigDecimal.valueOf(100),
                new Material[]{wood, fabric}, 4, true, 120);

        Table table = new Table("Table Model B", BigDecimal.valueOf(200),
                new Material[]{wood, metal}, 150, 80, 75, true);

        factory.setFurnitureItems(new Furniture[]{chair, table});

        Worker worker1 = new Worker(1, "Luka", 2000.0, 5);
        Worker worker2 = new Worker(2, "Taia", 1800.0, 4);
        Manager manager = new Manager(3, "Levan", 2500.0, "Production", 500);
        factory.setEmployees(new Employee[]{worker1, worker2, manager});

        worker1.setWorkload(new Workload(worker1, 10, LocalDateTime.now().plusDays(2)));
        worker2.setWorkload(new Workload(worker2, 15, LocalDateTime.now().plusDays(3)));

        EmployeeService service = new EmployeeService();
        service.giveRaise(worker1, 10.0);
        service.printEmployeeRole(manager);

        Order order1 = new Order(101, "Customer XYZ", factory.getFurnitureItems(), LocalDate.now());
        factory.setOrders(new Order[]{order1});

        System.out.println("Factory Furniture:");
        for (Furniture furniture : factory.getFurnitureItems()) {
            System.out.println(furniture);
        }

        System.out.println("\nEmployees:");
        for (Employee employee : factory.getEmployees()) {
            System.out.println(employee);
        }

        for (Furniture furniture : factory.getFurnitureItems()) {
            if (furniture instanceof Buildable buildable) {
                buildable.assemble();
            }
        }

        Discountable[] discountables = { (Discountable) factory.getFurnitureItems()[0], order1 };
        for (Discountable d : discountables) {
            d.applyDiscount(BigDecimal.valueOf(10));
        }

        for (Employee employee : factory.getEmployees()) {
            if (employee instanceof WorkAssignable) {
                WorkAssignable workAssignable = (WorkAssignable) employee;
                Workload wl = new Workload((Worker) employee, 5, LocalDateTime.now().plusDays(1));
                workAssignable.assignWork(wl);
            }
        }

        ((Payable) order1).pay(order1.calculateTotalPrice());

        OrderService.InterfaceService interfaceService = new OrderService.InterfaceService();
        interfaceService.assembleAllFurniture(factory.getFurnitureItems());
    }
}
