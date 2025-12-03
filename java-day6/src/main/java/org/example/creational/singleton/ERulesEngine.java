package org.example.creational.singleton;

public enum ERulesEngine {
    INSTANCE; // singleton instance

    ERulesEngine() {
        System.out.println("Initiated ERulesEngine...");
    }

    public boolean validate(Order order) {
        System.out.println("Validating order....");
        return true;
    }
}

// When does a static inner class based singleton fails
// but a enum based one does not fail
// Serialization breaks inner static classes
// Reflection
// Multiple class loaders, enums based ones are less likely to be
// duplicated