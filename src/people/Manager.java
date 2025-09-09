package people;

public class Manager extends Employee {

    private String department;
    private double bonus;

    public Manager(Integer id, String name, Double salary, String department, double bonus) {
        super(id, name, salary);
        this.department = department;
        this.bonus = bonus;
    }

    @Override
    public String getRoleDescription() {
        return "Manager of department " + department;
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
        return getSalary() + bonus;
    }

    @Override
    public String toString() {
        return "Manager: " + getName() + ", Department: " + department + ", Bonus: " + bonus;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Manager other = (Manager) obj;
        return this.getId() == other.getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }


}
