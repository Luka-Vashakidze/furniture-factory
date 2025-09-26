package people;

import enums.EmployeeRank;
import interfaces.WorkAssignable;
import workload.Workload;

public class Worker extends Employee implements WorkAssignable {

    private int skillLevel;
    private EmployeeRank rank = EmployeeRank.MID;

    public Worker(Integer id, String name, double salary, int skillLevel) {
        super(id, name, salary);
        this.skillLevel = skillLevel;
    }

    public Worker() {
        super();
    }

    @Override
    public String getRoleDescription() {
        return "Worker with skill level " + skillLevel;
    }

    public int getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(int skillLevel) {
        this.skillLevel = skillLevel;
    }

    public EmployeeRank getRank() {
        return rank;
    }

    public void setRank(EmployeeRank rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Worker{" + "id=" + getId() + ", name=" + getName() + ", skillLevel=" + skillLevel + ", rank=" + rank.label() + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Worker)) return false;
        Worker w = (Worker) o;
        return getId() == w.getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }

    @Override
    public void assignWork(Workload workload) {
        System.out.println(getName() + " assigned workload: " + workload.getHoursAssigned() + " hours");
    }
}
