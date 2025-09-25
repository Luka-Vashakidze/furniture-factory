package interfaces.custom;

import people.Worker;
import workload.Workload;

@FunctionalInterface
public interface WorkScheduler {
    void schedule(Worker worker, Workload workload);
}