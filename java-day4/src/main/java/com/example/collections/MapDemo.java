package com.example.collections;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MapDemo {
    public static void main(String[] args) {

        // Map is  Key-Value data structure
        // equals() and hashCode(), determines identity in case of hash based maps
        // HashMap is the most common implementation
        // HashMap support Treeification, when the number of nodes in a bucket
        // reaches 8, the linked list will be converted into a Red Black Tree
        // Order of growth: On Average O(1)
        // table default size is 16, each location is termed as a bucket
        //
        // https://miro.medium.com/v2/resize%3Afit%3A1400/1%2A5tZ5_V3l0atE3rBGmN2PWA.png

        // HashMap Algorithm:
        // Node<K,V> [] table;
        // Key              -> hash(keyValue.hashCode())
        // Compute Index    -> index = hash & (table.length - 1)
        //
        // if bucket is empty       -> insert the node
        // if bucket is not empty   -> Collision occurred, insert data into the linked list
        // if threshold is reached then convert the list to tree

        Map<Integer, String> map = new HashMap<>();

        map.put(1, "a");
        map.put(2, "b");
        map.put(3, "c");
        map.put(null, "d"); // One null key is allowed in HashMap

        System.out.println(map);
        System.out.println(map.keySet());
        System.out.println(map.values());
        Set<Map.Entry<Integer, String>> entries = map.entrySet();
        entries.forEach(e -> System.out.println(e.getKey() + " : " + e.getValue()));

        // Redblack tree implementation
        TreeMap<Data, String> treeMap = new TreeMap<>(Comparator.comparing(Data::getRefId));
        treeMap.put(new Data(10, "#001"), "EFDDEE");
        treeMap.put(new Data(12, "#023"), "FEDDSSR");

        System.out.println(treeMap);

        // Mutating keys
        // key = {id: 10, name: '' }
        // map.put(key, data)
        // key = {id: 12, name: ''} -> changing the key value after inserting it into the map

        // ConcurrentHashMap
        // Offers O(1) performance
        // Thread Safe without global locks
        // Safe concurrent iteration
        // No null keys/values
        // Fine grained locking with no locks for reading

    }


}

class Data {

    int key;
    String refId;

    Data(int key, String refId) {
        this.key = key;
        this.refId = refId;
    }

    public int getKey() {
        return key;
    }

    public String getRefId() {
        return refId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return key == data.key && Objects.equals(refId, data.refId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, refId);
    }

    @Override
    public String toString() {
        return "Data{" +
                "key=" + key +
                ", refId='" + refId + '\'' +
                '}';
    }
}