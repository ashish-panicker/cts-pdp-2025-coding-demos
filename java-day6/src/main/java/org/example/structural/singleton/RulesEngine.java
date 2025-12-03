package org.example.structural.singleton;

public class RulesEngine {

    private static RulesEngine instance;

    private RulesEngine() {
    }

//    public static RulesEngine getThreadSafeInstance() {
//        if (instance == null) {
//            synchronized (RulesEngine.class) {
//                if (instance == null) {
//                    instance = new RulesEngine();
//                }
//            }
//        }
//        return instance;
//    }

    public static RulesEngine getInstance() {
        return RulesEngineHolder.INSTANCE;
    }

    // Bill Pugh Singleton
    // This class is loaded in a lazy manner
    // Class loading is synchronised by JVM
    // only one instance of this class will be present in JVM
    private static class RulesEngineHolder {
        private static final RulesEngine INSTANCE = new RulesEngine();
    }

    public boolean validate(Order order) {
        return true;
    }
}
