package service;

import annotations.Auditable;
import exceptions.WorkAssignmentException;
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

    @Auditable("task-assignment")
    public void assignTask(Employee employee, String task) {
        if (employee == null) {
            throw new WorkAssignmentException("employee must not be null.");
        }
        if (task == null || task.isBlank()) {
            throw new WorkAssignmentException("Task shouldn not be empty.");
        }
        if (employee instanceof Manager) {
            throw new WorkAssignmentException("Managers cannot be assigned manual tasks: " + task);
        }
        System.out.println("Assigned task '" + task + "' to " + employee.getName());
    }
}