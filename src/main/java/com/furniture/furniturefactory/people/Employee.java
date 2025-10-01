package com.furniture.furniturefactory.people;

import com.furniture.furniturefactory.workload.Workload;

public abstract class Employee {

    private static int employeeCounter;

    static {
        employeeCounter = 0;
    }

    protected String name;
    protected Double salary;
    private Integer id;
    private Workload workload;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        employeeCounter++;
    }

    public Employee() {
        employeeCounter++;
    }

    public static int getEmployeeCounter() {
        return employeeCounter;
    }

    public abstract String getRoleDescription();

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

    public Workload getWorkload() {
        return workload;
    }

    public void setWorkload(Workload workload) {
        this.workload = workload;
    }

    public void giveRaise(double percent) {
        if (percent > 0) {
            salary += salary * percent / 100;
        }
    }
}
