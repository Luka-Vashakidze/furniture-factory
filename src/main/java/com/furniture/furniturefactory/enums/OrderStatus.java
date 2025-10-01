package com.furniture.furniturefactory.enums;

public enum OrderStatus {
    NEW(0, false),
    PROCESSING(1, false),
    READY(2, false),
    DELIVERED(3, true),
    CANCELLED(4, true);

    static {
        if (NEW.code >= PROCESSING.code) {
            throw new IllegalStateException("Status codes must be increasing for initial states");
        }
    }

    private final int code;
    private final boolean terminal;

    OrderStatus(int code, boolean terminal) {
        this.code = code;
        this.terminal = terminal;
    }

    public int code() { return code; }
    public boolean isTerminal() { return terminal; }

    public OrderStatus next() {
        switch (this) {
            case NEW: return PROCESSING;
            case PROCESSING: return READY;
            case READY: return DELIVERED;
            default: return this;
        }
    }
}
