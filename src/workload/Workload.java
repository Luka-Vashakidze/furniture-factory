package workload;

import people.Worker;

import java.time.LocalDateTime;

public class Workload {

    private Worker worker;
    private int hoursAssigned;
    private LocalDateTime deadline;

    public Workload(Worker worker, int hoursAssigned, LocalDateTime deadline) {
        this.worker = worker;
        this.hoursAssigned = hoursAssigned;
        this.deadline = deadline;
    }

    public Workload() {
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public int getHoursAssigned() {
        return hoursAssigned;
    }

    public void setHoursAssigned(int hoursAssigned) {
        this.hoursAssigned = hoursAssigned;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public boolean isOverloaded() {
        if (worker == null) {
            return false;
        }
        int totalWork = worker.getCurrentWorkload() + hoursAssigned;
        return totalWork > 40;
    }
}
