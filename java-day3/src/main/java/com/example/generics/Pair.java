package com.example.generics;

public class Pair<K, v> {

    private K key;
    private v value;

    public Pair(K key, v value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Pair{" + "key=" + key + ", value=" + value + '}';
    }
}
