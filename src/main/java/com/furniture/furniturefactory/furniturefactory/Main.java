package com.furniture.furniturefactory.furniturefactory;

import com.furniture.furniturefactory.annotations.Auditable;
import com.furniture.furniturefactory.enums.EmployeeRank;
import com.furniture.furniturefactory.enums.MaterialQuality;
import com.furniture.furniturefactory.enums.OrderPriority;
import com.furniture.furniturefactory.enums.ProductCategory;
import com.furniture.furniturefactory.exceptions.InvalidOrderException;
import com.furniture.furniturefactory.factory.Factory;
import com.furniture.furniturefactory.interfaces.Buildable;
import com.furniture.furniturefactory.interfaces.Discountable;
import com.furniture.furniturefactory.interfaces.Payable;
import com.furniture.furniturefactory.interfaces.WorkAssignable;
import com.furniture.furniturefactory.interfaces.custom.PriceRule;
import com.furniture.furniturefactory.interfaces.custom.TriFunction;
import com.furniture.furniturefactory.interfaces.custom.WorkScheduler;
import com.furniture.furniturefactory.material.Material;
import com.furniture.furniturefactory.order.Order;
import com.furniture.furniturefactory.order.OrderSummary;
import com.furniture.furniturefactory.people.Employee;
import com.furniture.furniturefactory.people.Manager;
import com.furniture.furniturefactory.people.Worker;
import com.furniture.furniturefactory.product.Chair;
import com.furniture.furniturefactory.product.Furniture;
import com.furniture.furniturefactory.product.Table;
import com.furniture.furniturefactory.service.EmployeeService;
import com.furniture.furniturefactory.service.OrderService;
import com.furniture.furniturefactory.util.Box;
import com.furniture.furniturefactory.util.Pair;
import com.furniture.furniturefactory.util.ReflectionHelper;
import com.furniture.furniturefactory.workload.Workload;

import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    public static void main(String[] args) {
        Logger logger = LogManager.getLogger(Main.class);

        Factory factory = new Factory();

        Material wood = new Material("Wood", BigDecimal.valueOf(50), 100);
        Material metal = new Material("Metal", BigDecimal.valueOf(30), 50);
        Material fabric = new Material("Fabric", BigDecimal.valueOf(20), 200);

        wood.setQuality(MaterialQuality.PREMIUM);
        metal.setQuality(MaterialQuality.BASIC);
        fabric.setQuality(MaterialQuality.LUXURY);

        List<Material> materials = new ArrayList<>();
        materials.add(wood);
        materials.add(metal);
        materials.add(fabric);
        logger.info("Materials list size: {}", materials.size());
        logger.info("Materials isEmpty? {}", materials.isEmpty());
        factory.setMaterials(materials);

        long matCount = materials.stream().map(Material::getName).count();
        logger.info("Materials count via stream: {}", matCount);

        Chair chair = new Chair("Chair Model A", BigDecimal.valueOf(100),
                List.of(wood, fabric), 4, true, 120);

        Table table = new Table("Table Model B", BigDecimal.valueOf(200),
                List.of(wood, metal), 150, 80, 75, true);

        ProductCategory chairCategory = ProductCategory.CHAIR;
        logger.info(chairCategory.packingHint());

        chair.setCategory(ProductCategory.CHAIR);
        table.setCategory(ProductCategory.TABLE);


        List<Furniture> furnitureList = new ArrayList<>();
        furnitureList.add(chair);
        furnitureList.add(table);
        factory.setFurnitureItems(furnitureList);

        Worker worker1 = new Worker(1, "Luka", 2000.0, 5);
        Worker worker2 = new Worker(2, "Taia", 1800.0, 4);
        Manager manager = new Manager(3, "Levan", 2500.0, "Production", 500);

        double overtime = EmployeeRank.MID.overtimePay(20.0, 3);
        logger.info("Overtime example: {}", overtime);

        Set<Employee> employeeSet = new HashSet<>();
        employeeSet.add(worker1);
        employeeSet.add(worker2);
        employeeSet.add(manager);
        logger.info("Employee set size: {}", employeeSet.size());
        logger.info("Employee set isEmpty? {}", employeeSet.isEmpty());

        Employee firstFromSet = employeeSet.iterator().hasNext() ? employeeSet.iterator().next() : null;
        if (firstFromSet != null) {
            logger.info("First employee from set (by iteration): {}", firstFromSet.getName());
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
        logger.info("OrderItemsMap size: {}", orderItemsMap.size());

        Map.Entry<Order, List<Furniture>> firstEntry = orderItemsMap.entrySet().iterator().next();
        logger.info("First map key (orderId): {}, value size: {}", firstEntry.getKey().getOrderId(), (firstEntry.getValue() != null ? firstEntry.getValue().size() : 0));

        //replaced for lop with mentries
        orderItemsMap.entrySet().stream()
                .forEach(entry -> logger.info("OrderId: {}, items count: {}", entry.getKey().getOrderId(), (entry.getValue() == null ? 0 : entry.getValue().size())));


        List<Furniture> fetched = orderItemsMap.get(order1);
        logger.info("Fetched list equals factory list? {}", (fetched == factory.getFurnitureItems()));
        orderItemsMap.remove(new Order(11, "Nobody", Collections.emptyList(), LocalDate.now()));
        logger.info("Contains order1? {}", orderItemsMap.containsKey(order1));

        List<Order> orders = new ArrayList<>();
        orders.add(order1);
        factory.setOrders(orders);

        logger.info("Factory Furniture:");
        // relpaced here for loop here
        factory.getFurnitureItems().stream()
                .map(Object::toString)
                .forEach(item -> logger.info(item));

        Furniture firstFromList = factory.getFurnitureItems().isEmpty() ? null : factory.getFurnitureItems().get(0);
        if (firstFromList != null) {
            logger.info("First furniture from list: {}", firstFromList.getName());
        }

        logger.info("Employees:");
        // replace here too
        factory.getEmployees().stream()
                .sorted(Comparator.comparing(Employee::getName))
                .forEach(emp -> logger.info(emp));


        OrderService.InterfaceService interfaceService = new OrderService.InterfaceService();
        //replaced for loop here too
        factory.getFurnitureItems().stream()
                .filter(f -> f instanceof Buildable)
                .map(f -> (Buildable) f)
                .forEach(interfaceService::assembleFurniture);


        List<Discountable> discountables = new ArrayList<>();
        discountables.add((Discountable) factory.getFurnitureItems().get(0));
        discountables.add(order1);

        discountables.stream()
                .forEach(d -> d.applyDiscount(BigDecimal.valueOf(10)));


        factory.getEmployees().stream()
                .filter(e -> e instanceof WorkAssignable)
                .map(e -> (WorkAssignable) e)
                .forEach(wa -> {
                    Workload wl = new Workload((Worker) worker1, 5, LocalDateTime.now().plusDays(1));
                    wa.assignWork(wl);
                });


        OrderService orderService = new OrderService();
        try {
            orderService.placeOrder(order1);
        } catch (InvalidOrderException e) {
            logger.error("failed to place order {}", e.getMessage());
        } finally {
            logger.info("Order finished ");
        }

        ((Payable) order1).pay(order1.calculateTotalPrice());

        Pair<String, Integer> summary = new Pair<>("TotalEmployees", factory.getEmployees().size());
        logger.info("Pair -> {}: {}", summary.getLeft(), summary.getRight());

        Box<Employee> employeeBox = new Box<>(firstFromSet != null ? firstFromSet : manager);
        logger.info("Box contains employee: {}", employeeBox.get().getName());

        TriFunction<Integer, Integer, Integer, Integer> triAdder = (a, b, c) -> a + b + c;
        logger.info("TriFunction sum: {}", triAdder.apply(1, 2, 3));

        PriceRule addSmallFee = (item, curr) -> curr.add(BigDecimal.valueOf(5));
        WorkScheduler scheduler = (w, wl) -> logger.info("Scheduling {} for {}h", w.getName(), wl.getHoursAssigned());
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
        Consumer<OrderSummary> afterEach = os -> logger.info("Processed: {}", os.pretty());
        BiConsumer<Order, Exception> onError = (o, ex) -> logger.error("Error for order {}: {}", (o != null ? o.getOrderId() : "null"), ex.getMessage());

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

        logger.info("Processed summaries count: {}", processed.size());

        ToIntFunction<Worker> skillFn = Worker::getSkillLevel;
        BinaryOperator<BigDecimal> adder = BigDecimal::add;
        BigDecimal totalBase = factory.getFurnitureItems().stream()
                .map(Furniture::getBasePrice)
                .reduce(BigDecimal.ZERO, adder);
        logger.info("Total base prices: {}, skill worker1: {}", totalBase, skillFn.applyAsInt(worker1));

        ReflectionHelper.printClassInfo(Order.class);
        ReflectionHelper.printClassInfo(EmployeeService.class);

        try {
            Object w3 = ReflectionHelper.createInstance(
                    Worker.class,
                    new Class<?>[]{Integer.class, String.class, double.class, int.class},
                    new Object[]{10, "Reflected", 1500.0, 3}
            );
            ReflectionHelper.callMethod(w3, "setSkillLevel", new Class<?>[]{int.class}, new Object[]{6});
            Object role = ReflectionHelper.callMethod(w3, "getRoleDescription", new Class<?>[]{}, new Object[]{});
            logger.info("Reflected worker role: {}", role);
        } catch (Exception ex) {
            logger.error("Reflection error: {}", ex.getMessage());
        }


        Arrays.stream(EmployeeService.class.getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(Auditable.class))
                .forEach(m -> logger.info("Auditable method: {}, modifiers={}, returnType={}, params={}", m.getName(),
                        Modifier.toString(m.getModifiers()), m.getReturnType().getSimpleName(),
                        Arrays.stream(m.getParameterTypes()).map(Class::getSimpleName).collect(Collectors.joining(","))));

        System.out.println("hello from conflict-2");

    }




}