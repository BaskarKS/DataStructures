package com.baskarks.nonlinear.graphs.undirected;

/*
* UnDirected  and Weighted Graphs:
*
* If our edges of our graph has weights , its weighted graph
* these weight represents factors like cost, distance, complexity
* or anything.
*
* Application of Weighted Graph: find shortest path between 2 nodes
* Eg: if nodes represent diff areas in city and weights represent the
* distance / traffic conditions
*
* */
public class Main {
    public static void main(String[] args) {
        WeightedGraph graph = new WeightedGraph();
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");
        graph.addEdge("A", "B", 3);
        graph.addEdge("B", "D", 6);
        graph.addEdge("A", "D", 2);
        graph.addEdge("A", "C", 4);
        graph.addEdge("C", "D", 1);
        graph.addEdge("B", "E", 1);
        graph.addEdge("D", "E", 5);
        //graph.print();
        System.out.println(graph.getShortestPath("A", "E"));

        var cycleGraph = new WeightedGraph();
        cycleGraph.addNode("A");
        cycleGraph.addNode("B");
        cycleGraph.addNode("C");
        cycleGraph.addNode("D");
        cycleGraph.addEdge("A", "B", 2);
        cycleGraph.addEdge("B", "C", 1);
        cycleGraph.addEdge("B", "D", 4);
        cycleGraph.addEdge("C", "D", 3);
        System.out.println(cycleGraph.hasCycle());

        var minimalGraph = new WeightedGraph();
        minimalGraph.addNode("A");
        minimalGraph.addNode("B");
        minimalGraph.addNode("C");
        minimalGraph.addNode("D");
        minimalGraph.addEdge("A", "B", 2);
        minimalGraph.addEdge("A", "C", 3);
        minimalGraph.addEdge("B", "C", 1);
        minimalGraph.addEdge("B", "D", 5);
        minimalGraph.addEdge("C", "D", 4);
        var minTree = minimalGraph.getMinimumSpanningTree();
        minTree.print();
    }
}
