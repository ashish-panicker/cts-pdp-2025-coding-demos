public class GcDemo {
    public static void main(String[] args) {
        System.out.println("Starting GC Demo");
        for (int i = 0; i < 20_000_000; i++) {
            byte[] data = new byte[10 * 1024]; // Allocate 1 KB
        }
        System.out.println("Completing GC Demo");

        // Thread Local Allocation Buffer
        // Objects are initially allocated in Eden Space
        // Minor GC: When Eden is full, live objects are moved to Survivor Space

        /**
         * Java Heap
         * - Young Generation (short lived objects)
         *  - Eden Space
         *  - Survivor Space S0
         *  - Survivor Space S1
         * - Old Generation (long lived objects)
         * 
         * Non Heap Memory
         * - Metaspace
         * - Code Cache
         * - Thread Stacks
         */

    }
}
