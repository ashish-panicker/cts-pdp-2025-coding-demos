package com.example.collections;

public class CustomHashMap<K, V> {

    // Data object
    static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    private static final int DEFAULT_CAPACITY = 10; // 10 gives you more collision scenarios
    private transient Node<K, V>[] buckets;

    public CustomHashMap() {
        buckets = new Node[DEFAULT_CAPACITY];
    }

    public int getBucketIndex(K key) {
        // Algorithm to get the index
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public void put(K newKey, V value) {
        // Get the index location
        int index = getBucketIndex(newKey);
        // Get the reference of the element at the index
        Node<K, V> head = buckets[index];
        while (head != null) {
            // If the key of the element at the location is equal to the supplied
            // key, they update the value
            if (head.key.equals(newKey)) {
                head.value = value;
                return;
            }
            head = head.next;
        }
        // Insert the new node
        Node<K, V> newNode = new Node<>(newKey, value);
        newNode.next = buckets[index];
        buckets[index] = newNode;
    }

    public void print() {
        for (int i = 0; i < buckets.length; i++) {
            System.out.print("Bucket[" + i + "]: ");
            Node<K, V> head = buckets[i];
            while (head != null) {
                System.out.print(head + " -> ");
                head = head.next;
            }
            System.out.println("null");
        }
    }

    public static void main(String[] args) {
        CustomHashMap<Integer, String> map = new CustomHashMap<>();
        map.put(1, "Apple");
        map.put(2, "Banana");
        map.put(12, "Orange");
        map.put(22, "Mango");

        map.print();
    }
}
