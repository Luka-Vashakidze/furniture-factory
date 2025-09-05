package people;

public class Worker {

    private Employee employee;
    private int skillLevel;
    private int currentWorkload;

    public Worker(Employee employee, int skillLevel) {
        this.employee = employee;
        this.skillLevel = skillLevel;
        this.currentWorkload = 0;
    }

    public Worker() {
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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


}
