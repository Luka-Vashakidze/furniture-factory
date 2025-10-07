package com.furniture.furniturefactory.service;

import com.furniture.furniturefactory.annotations.Auditable;
import com.furniture.furniturefactory.exceptions.WorkAssignmentException;
import com.furniture.furniturefactory.people.Employee;
import com.furniture.furniturefactory.people.Manager;
import com.furniture.furniturefactory.people.Worker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class EmployeeService {

    private static final Logger logger = LogManager.getLogger(EmployeeService.class);

    public void giveRaise(Employee employee, double percent) {
        employee.giveRaise(percent);
        logger.info("{} new salary: {}", employee.getName(), employee.getSalary());
    }

    public void printEmployeeRole(Employee employee) {
        if (employee instanceof Worker) {
            logger.info("{} is a Worker.", employee.getName());
        } else if (employee instanceof Manager) {
            logger.info("{} is a Manager.", employee.getName());
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
        logger.info("Assigned task '{}' to {}", task, employee.getName());
    }
}