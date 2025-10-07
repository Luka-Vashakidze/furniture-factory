package com.furniture.furniturefactory.service;

public class AssemblySession implements AutoCloseable {
    private final String itemName;
    private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger(AssemblySession.class);

    public AssemblySession(String itemName) {
        this.itemName = itemName;
        logger.info("Opening assembly session for: {}", itemName);
    }

    public String getItemName() {
        return itemName;
    }

    @Override
    public void close() {
        logger.info("Closing assembly session for: {}", itemName);
    }
}