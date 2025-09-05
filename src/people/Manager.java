package people;

public class Manager {
    private Employee employee;
    private String department;
    private double bonus;

    public Manager(Employee employee, String department, double bonus) {
        this.employee = employee;
        this.department = department;
        this.bonus = bonus;
    }

    public Manager() {

    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double calculateSalaryWithBonus() {
        return employee.getSalary() + bonus;
    }
}
