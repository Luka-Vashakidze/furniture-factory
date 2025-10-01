package com.furniture.furniturefactory.interfaces.custom;

import com.furniture.furniturefactory.people.Worker;
import com.furniture.furniturefactory.workload.Workload;

@FunctionalInterface
public interface WorkScheduler {
    void schedule(Worker worker, Workload workload);
}