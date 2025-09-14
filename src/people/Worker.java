package people;

import interfaces.WorkAssignable;
import workload.Workload;

public class Worker extends Employee implements WorkAssignable {

    private int skillLevel;

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

    @Override
    public String toString() {
        return "Worker{" + "id=" + getId() + ", name=" + getName() + ", skillLevel=" + skillLevel + '}';
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
