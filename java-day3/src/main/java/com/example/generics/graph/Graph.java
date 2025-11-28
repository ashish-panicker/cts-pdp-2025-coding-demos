package com.example.generics.graph;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// NODE: (N: W)
// A: {B=5, C=7, D=10}
public class Graph<N extends Comparable<N>, W extends Number> {

    private final Map<N, Map<N, W>> edges = new HashMap<>();

    // Adda a node to the graph
    public void addNode(N node) {
        edges.putIfAbsent(node, new HashMap<>());
    }

    //Add an edge to the graph
    public void addEdge(N from, N to, W weight) {
        // edges.putIfAbsent(from, new HashMap<>());
        edges.get(from).put(to, weight);
    }

    // Get total weight of the graph
    public <T extends Number> double getGraphWeight() {
        System.out.println(edges);
        return edges.values().stream()
                .peek(System.out::println)
                .flatMap(map -> map.values().stream().peek(nestedValue -> System.out.println("\t" + nestedValue)))
                .mapToDouble(Number::doubleValue)
                .sum();
    }

    // get neighbours of a node
    public List<N> getNeighbours(N node) {
        System.out.println(edges);
        return edges.getOrDefault(node, Collections.emptyMap())
                .keySet().stream().peek(System.out::println)
                .toList();
    }

    @Override
    public String toString() {
        return "Graph{" + "edges=" + edges + '}';
    }

    public static void main(String[] args) {
        Graph<String, Integer> graph = new Graph<>();

        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");

        graph.addEdge("A", "B", 5);
        graph.addEdge("A", "C", 7);
        graph.addEdge("A", "D", 10);

        graph.addEdge("B", "C", 1);
        graph.addEdge("B", "D", 2);

        graph.addEdge("C", "D", 3);


//        var weight = graph.getGraphWeight();
//        System.out.println(weight);

        var list = graph.getNeighbours("A");
        System.out.println("Edges of A:" + list + " ");
    }
}
