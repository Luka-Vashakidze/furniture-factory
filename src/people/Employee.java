package people;

public class Employee {

    private int id;
    private String name;
    private double salary;

    private static int employeeCounter;

    static {
        employeeCounter = 0;

    }

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        employeeCounter++;
    }

    public Employee() {
        employeeCounter++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void giveRaise(double percent) {
        if (percent > 0) {
            salary += salary * percent / 100;
        }
    }

    public static int getEmployeeCounter() {
        return employeeCounter;
    }
}
