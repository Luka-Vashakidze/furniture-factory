package furniturefactory;

import factory.Factory;
import people.*;
import material.*;
import product.*;
import order.*;
import service.EmployeeService;
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

        Furniture chairFurniture = new Furniture("Chair Model A", BigDecimal.valueOf(100),
                new Material[]{wood, fabric});
        Furniture tableFurniture = new Furniture("Table Model B", BigDecimal.valueOf(200),
                new Material[]{wood, metal});
        factory.setFurnitureItems(new Furniture[]{chairFurniture, tableFurniture});

        Chair chair1 = new Chair("Chair Model A", BigDecimal.valueOf(100),
                new Material[]{wood, fabric}, 4, true, 120);
        Table table1 = new Table("Table Model B", BigDecimal.valueOf(200),
                new Material[]{wood, metal}, 150, 80, 75, true);
        factory.setChairs(new Chair[]{chair1});
        factory.setTables(new Table[]{table1});

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
    }
}
