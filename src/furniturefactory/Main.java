package furniturefactory;

import enums.EmployeeRank;
import enums.OrderPriority;
import enums.ProductCategory;
import exceptions.InvalidOrderException;
import factory.Factory;
import interfaces.Buildable;
import interfaces.Discountable;
import interfaces.Payable;
import interfaces.WorkAssignable;
import interfaces.custom.PriceRule;
import interfaces.custom.TriFunction;
import interfaces.custom.WorkScheduler;
import material.Material;
import order.Order;
import order.OrderSummary;
import people.Employee;
import people.Manager;
import people.Worker;
import product.Chair;
import product.Furniture;
import product.Table;
import service.EmployeeService;
import service.OrderService;
import util.Box;
import util.Pair;
import workload.Workload;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.*;

public class Main {

    public static void main(String[] args) {

        Factory factory = new Factory();

        Material wood = new Material("Wood", BigDecimal.valueOf(50), 100);
        Material metal = new Material("Metal", BigDecimal.valueOf(30), 50);
        Material fabric = new Material("Fabric", BigDecimal.valueOf(20), 200);

        List<Material> materials = new ArrayList<>();
        materials.add(wood);
        materials.add(metal);
        materials.add(fabric);
        System.out.println("Materials list size: " + materials.size());
        System.out.println("Materials isEmpty? " + materials.isEmpty());
        factory.setMaterials(materials);

        Chair chair = new Chair("Chair Model A", BigDecimal.valueOf(100),
                List.of(wood, fabric), 4, true, 120);

        Table table = new Table("Table Model B", BigDecimal.valueOf(200),
                List.of(wood, metal), 150, 80, 75, true);

        ProductCategory chairCategory = ProductCategory.CHAIR;
        System.out.println(chairCategory.packingHint());

        List<Furniture> furnitureList = new ArrayList<>();
        furnitureList.add(chair);
        furnitureList.add(table);
        factory.setFurnitureItems(furnitureList);

        Worker worker1 = new Worker(1, "Luka", 2000.0, 5);
        Worker worker2 = new Worker(2, "Taia", 1800.0, 4);
        Manager manager = new Manager(3, "Levan", 2500.0, "Production", 500);

        double overtime = EmployeeRank.MID.overtimePay(20.0, 3);
        System.out.println("Overtime example: " + overtime);

        Set<Employee> employeeSet = new HashSet<>();
        employeeSet.add(worker1);
        employeeSet.add(worker2);
        employeeSet.add(manager);
        System.out.println("Employee set size: " + employeeSet.size());
        System.out.println("Employee set isEmpty? " + employeeSet.isEmpty());

        Employee firstFromSet = employeeSet.iterator().hasNext() ? employeeSet.iterator().next() : null;
        if (firstFromSet != null) {
            System.out.println("First employee from set (by iteration): " + firstFromSet.getName());
        }
        factory.setEmployees(new ArrayList<>(employeeSet));

        worker1.setWorkload(new Workload(worker1, 10, LocalDateTime.now().plusDays(2)));
        worker2.setWorkload(new Workload(worker2, 15, LocalDateTime.now().plusDays(3)));

        EmployeeService service = new EmployeeService();
        service.giveRaise(worker1, 10.0);
        service.printEmployeeRole(manager);

        Order order1 = new Order(101, "Customer XYZ", factory.getFurnitureItems(), LocalDate.now());
        order1.setPriority(OrderPriority.HIGH);

        Map<Order, List<Furniture>> orderItemsMap = new HashMap<>();
        orderItemsMap.put(order1, factory.getFurnitureItems());
        System.out.println("OrderItemsMap size: " + orderItemsMap.size());

        Map.Entry<Order, List<Furniture>> firstEntry = orderItemsMap.entrySet().iterator().next();
        System.out.println("First map key (orderId): " + firstEntry.getKey().getOrderId() +
                ", value size: " + (firstEntry.getValue() != null ? firstEntry.getValue().size() : 0));

        for (Map.Entry<Order, List<Furniture>> entry : orderItemsMap.entrySet()) {
            Order entryOrder = entry.getKey();
            List<Furniture> entryItems = entry.getValue();
            System.out.println("OrderId: " + entryOrder.getOrderId() +
                    ", items count: " + (entryItems == null ? 0 : entryItems.size()));
        }

        List<Furniture> fetched = orderItemsMap.get(order1);
        System.out.println("Fetched list equals factory list? " + (fetched == factory.getFurnitureItems()));
        orderItemsMap.remove(new Order(11, "Nobody", Collections.emptyList(), LocalDate.now()));
        System.out.println("Contains order1? " + orderItemsMap.containsKey(order1));

        List<Order> orders = new ArrayList<>();
        orders.add(order1);
        factory.setOrders(orders);

        System.out.println("Factory Furniture:");
        for (Furniture furniture : factory.getFurnitureItems()) {
            System.out.println(furniture);
        }
        Furniture firstFromList = factory.getFurnitureItems().isEmpty() ? null : factory.getFurnitureItems().get(0);
        if (firstFromList != null) {
            System.out.println("First furniture from list: " + firstFromList.getName());
        }

        System.out.println("\nEmployees:");
        for (Employee employee : factory.getEmployees()) {
            System.out.println(employee);
        }

        OrderService.InterfaceService interfaceService = new OrderService.InterfaceService();

        for (Furniture furniture : factory.getFurnitureItems()) {
            if (furniture instanceof Buildable buildable) {
                interfaceService.assembleFurniture(buildable);
            }
        }

        List<Discountable> discountables = new ArrayList<>();
        discountables.add((Discountable) factory.getFurnitureItems().get(0));
        discountables.add(order1);
        for (Discountable discountable : discountables) {
            discountable.applyDiscount(BigDecimal.valueOf(10));
        }

        for (Employee employee : factory.getEmployees()) {
            if (employee instanceof WorkAssignable workAssignable) {
                Workload wl = new Workload((Worker) employee, 5, LocalDateTime.now().plusDays(1));
                workAssignable.assignWork(wl);
            }
        }

        OrderService orderService = new OrderService();
        try {
            orderService.placeOrder(order1);
        } catch (InvalidOrderException e) {
            System.err.println("failed to place order " + e.getMessage());
        } finally {
            System.out.println("Order finished ");
        }

        ((Payable) order1).pay(order1.calculateTotalPrice());

        Pair<String, Integer> summary = new Pair<>("TotalEmployees", factory.getEmployees().size());
        System.out.println("Pair -> " + summary.getLeft() + ": " + summary.getRight());

        Box<Employee> employeeBox = new Box<>(firstFromSet != null ? firstFromSet : manager);
        System.out.println("Box contains employee: " + employeeBox.get().getName());

        TriFunction<Integer, Integer, Integer, Integer> triAdder = (a, b, c) -> a + b + c;
        System.out.println("TriFunction sum: " + triAdder.apply(1, 2, 3));

        PriceRule addSmallFee = (item, curr) -> curr.add(BigDecimal.valueOf(5));
        WorkScheduler scheduler = (w, wl) -> System.out.println("Scheduling " + w.getName() + " for " + wl.getHoursAssigned() + "h");
        scheduler.schedule(worker1, worker1.getWorkload());

        Predicate<Order> nonEmptyOrder = o -> o.getItems() != null && !o.getItems().isEmpty();
        Function<Order, BigDecimal> totalCalc = Order::calculateTotalPrice;
        Supplier<BigDecimal> seasonalDiscount = () -> BigDecimal.valueOf(5);
        UnaryOperator<List<Furniture>> sortByName = list -> {
            if (list == null) return List.of();
            List<Furniture> copy = new ArrayList<>(list);
            copy.sort(Comparator.comparing(Furniture::getName));
            return copy;
        };
        BiFunction<Order, BigDecimal, Order> adjuster = (o, total) -> {
            if (o.getItems() != null) {
                for (Furniture f : o.getItems()) {
                    f.setBasePrice(addSmallFee.apply(f, f.getBasePrice()));
                }
            }
            return o;
        };
        Consumer<OrderSummary> afterEach = os -> System.out.println("Processed: " + os.pretty());
        BiConsumer<Order, Exception> onError = (o, ex) -> System.err.println("Error for order " +
                (o != null ? o.getOrderId() : "null") + ": " + ex.getMessage());

        List<OrderSummary> processed = orderService.processOrders(
                factory.getOrders(),
                nonEmptyOrder,
                totalCalc,
                seasonalDiscount,
                sortByName,
                adjuster,
                afterEach,
                onError
        );

        System.out.println("Processed summaries count: " + processed.size());

        ToIntFunction<Worker> skillFn = Worker::getSkillLevel;
        BinaryOperator<BigDecimal> adder = BigDecimal::add;
        BigDecimal totalBase = factory.getFurnitureItems().stream()
                .map(Furniture::getBasePrice)
                .reduce(BigDecimal.ZERO, adder);
        System.out.println("Total base prices: " + totalBase + ", skill worker1: " + skillFn.applyAsInt(worker1));
    }
}