package com.furniture.furniturefactory.enums;

public enum EmployeeRank {
    JUNIOR("Junior", 1.0),
    MID("Mid", 1.25),
    SENIOR("Senior", 1.5);

    private final String label;
    private final double overtimeMultiplier;

    static {
        if (JUNIOR.overtimeMultiplier > MID.overtimeMultiplier) {
            throw new IllegalStateException("Rank multipliers must increase with rank");
        }
    }

    EmployeeRank(String label, double overtimeMultiplier) {
        this.label = label;
        this.overtimeMultiplier = overtimeMultiplier;
    }

    public String label() { return label; }

    public double overtimePay(double baseHourRate, int hours) {
        return baseHourRate * hours * overtimeMultiplier;
    }
}