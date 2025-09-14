package service;

import people.Employee;
import people.Manager;
import people.Worker;

public final class EmployeeService {

    public void giveRaise(Employee employee, double percent) {
        employee.giveRaise(percent);
        System.out.println(employee.getName() + " new salary: " + employee.getSalary());
    }

    public void printEmployeeRole(Employee employee) {
        if (employee instanceof Worker) {
            System.out.println(employee.getName() + " is a Worker.");
        } else if (employee instanceof Manager) {
            System.out.println(employee.getName() + " is a Manager.");
        }
    }
}
