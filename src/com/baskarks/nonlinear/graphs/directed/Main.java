package com.baskarks.nonlinear.graphs.directed;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addEdge("A", "C");
        graph.addEdge("A", "B");
        graph.addEdge("B", "D");
        graph.addEdge("C", "D");

        //graph.print();
        //graph.removeEdge("A", "C");
        //graph.removeNode("B");
        //graph.print();
        //graph.traverseDepthFirstIterative("A");
        graph.breadthFirstTraversal("A");
        List<String> topologicalSort = graph.topologicalSort();
        System.out.println(topologicalSort);

        Graph cycleGraph = new Graph();
        cycleGraph.addNode("A");
        cycleGraph.addNode("B");
        cycleGraph.addNode("C");
        cycleGraph.addEdge("A", "B");
        cycleGraph.addEdge("B", "C");
        cycleGraph.addEdge("A", "C");
        System.out.println("Has Cycle : " + cycleGraph.hasCycle());

    }
}
