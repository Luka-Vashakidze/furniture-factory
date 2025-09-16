package service;

public class AssemblySession implements AutoCloseable {
    private final String itemName;

    public AssemblySession(String itemName) {
        this.itemName = itemName;
        System.out.println("Opening assembly session for: " + itemName);
    }

    public String getItemName() {
        return itemName;
    }

    @Override
    public void close() {
        System.out.println("Closing assembly session for: " + itemName);
    }
}