package org.example.structural.singleton;

public class Main {
    public static void main(String[] args) {
        ERulesEngine instance = ERulesEngine.INSTANCE;
        instance.validate(null);
    }
}
