package com.furniture.furniturefactory.people;

import com.furniture.furniturefactory.enums.EmployeeRank;
import com.furniture.furniturefactory.interfaces.WorkAssignable;
import com.furniture.furniturefactory.workload.Workload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Worker extends Employee implements WorkAssignable {
    private static final Logger logger = LogManager.getLogger(Worker.class);

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
        logger.info("{} assigned workload: {} hours", getName(), workload.getHoursAssigned());
    }
}
