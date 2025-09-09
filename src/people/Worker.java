package people;

public class Worker extends Employee {

    protected int skillLevel;
    private int currentWorkload;

    public Worker(Integer id, String name, double salary, int skillLevel) {
        super(id, name, salary);
        this.skillLevel = skillLevel;
        this.currentWorkload = 0;
    }

    public Worker() {
        super();
        this.currentWorkload = 0;
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

    public int getCurrentWorkload() {
        return currentWorkload;
    }

    public void setCurrentWorkload(int currentWorkload) {
        if (currentWorkload >= 0) {
            this.currentWorkload = currentWorkload;
        }
    }

    public void assignTask(int workload) {
        this.currentWorkload += workload;
    }

    public void finishTask(int workload) {
        this.currentWorkload -= workload;
        if (this.currentWorkload < 0) this.currentWorkload = 0;
    }


    @Override
    public String toString() {
        return "Worker{" + "id=" + id + ", name=" + getName() + ", skillLevel=" + skillLevel + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Worker)) return false;
        Worker w = (Worker) o;
        return id.equals(w.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }


}
