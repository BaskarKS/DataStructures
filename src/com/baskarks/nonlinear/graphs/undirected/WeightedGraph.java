package com.baskarks.nonlinear.graphs.undirected;

import java.util.*;

public class WeightedGraph {
    private class Node {
        private String value;
        private List<Edge> edges;
        public Node(String value) {
            this.value = value;
            this.edges = new ArrayList<>();
        }
        @Override
        public String toString() {
            return value;
        }
        public void addEdge(Node to, int weight) {
            edges.add(new Edge(this, to, weight));
        }
        public List<Edge> getEdges() {
            return edges;
        }
    }

    private class Edge {
        private Node from;
        private Node to;
        private int weight;
        public Edge(Node fromNode, Node toNode, int weight) {
            this.from = fromNode;
            this.to = toNode;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return from + "->" + to;
        }
    }

    private class NodeEntry {
        private Node node;
        private int priority;
        public NodeEntry(Node node, int priority) {
            this.node = node;
            this.priority = priority;
        }
    }
    private class EdgeEntry {
        private Edge edge;
        private int priority;
        public EdgeEntry(Edge edge, int priority) {
            this.edge = edge;
            this.priority = priority;
        }
    }

    private Map<String,Node> nodes = new HashMap<>();
    //private Map<Node, List<Edge>> adjacencyList = new HashMap<>();

    public void addNode(String label) {
        if (label == null || label.isEmpty())
            return;
        //var node = new Node(label);
        nodes.putIfAbsent(label, new Node(label));
        //adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(String from, String to, int weight) {
        var fromNode = nodes.get(from);
        if (fromNode == null)
            throw new IllegalArgumentException();

        var toNode = nodes.get(to);
        if (toNode == null)
            throw new IllegalArgumentException();
/*
        adjacencyList.get(fromNode).add(new Edge(fromNode,
                                                toNode, weight));
        adjacencyList.get(toNode).add(new Edge(toNode,
                                                fromNode, weight));
        */
        fromNode.addEdge(toNode, weight);
        toNode.addEdge(fromNode, weight);
    }

    public void print() {
        for (Node node : nodes.values()) {
            var edgeList = node.getEdges();
            if (!edgeList.isEmpty())
                System.out.println(node + " is connected to " +
                        edgeList);
        }
    }

    /*
    * (Dutch Scientist) Dijkstra's Algorithm - classical algorithm to
    * find the shortest path between 2 nodes
    *
    * need to maintain a table to find the shortest distance
    *  Eg: to find shortest distance between A to E
    * Hence we start from A, the below table will be
    *           A -----(3)-------- B
    *           -  -               - - (1)
    *        (4)-    -             -   -
    *           -       -(2)    (6)-     E
                -          -       -   -
                -              -   - - (5)
    *           C ------(1)------- D
    *           node   distance       previous
    *            A        0
    *            B       MAX,3          A
    *            C       MAX,4,3        A,D
    *            D       MAX,2          A
    *            E       MAX,7,4        D,B
    *
    * we start from startNode which is A
    *    at each step we look at unvisited neighbours of current node
    *
    * unvisited neighbours of current node is ( B, C , D)
    * at start, the currentNode and startNode is same which is A
    * calculate distance from startNode to each of its neighbours
    * if the calculated distance is smaller than table, update it
    * A(startNode)-B(neighbourNode) => distance is 3 is smaller
    * than MAX hence update it with value, we reached B through
    * A, hence 'previous will be A, update all neighbours
    *
    * pick a neighbour of A with shortest distance which is D
    *
    * repeat steps above, now startNode is A, currentNode is D
    * check all unvisited neighbours of currentNode, calculate
    * distance between startNode to neighbours via current node,
    * if distance is smaller than recorded value in table then
    * update the 'distance' and 'previous'
    *
    * currentNode D => unvisited neighbours are B, C, E
    *
    * checking neighbour B
    * distance between A-D(2) and D-B(6) => 8
    * distance in table for B node is 3 which is smaller, we ignore
    *
    * checking neighbour C
    * distance between A-D(2) and (D-C)1 => 3
    * distance for C node in table is 4 > 3(calculated Value)
    * hence we update the table and mark 'previous' entry is  D
    *
    * checking neighbour E
    * distance between A-D(2) and (D-E)5 => 7
    * distance in table for E is MAX which is larger than calculated
    * value, hence we update in distance as 7 and previous as D
    *
    * visited all unvisited neighbours of D which is B(3), C(3), E(7)
    * mark D as visited, pick a smaller value neighbour and go
    * there, in this case B(3)
    *
    * currentNode is B, startNode is A
    * unvisited neighbours of B is E
    *
    * checking neighbour E
    * distance between A-B(3) and (B-E)1 => 3 + 1 => 4
    * distance in table for E in table is 7 which is greater than
    * calculated value, hence we update the distance(4)
    * and 'previous' as B for E node
    *
    * all neighbours of B is visited, mark B as visited
    * moving to closest unvisited neighbour which is E.
    * Moving to E but it doesn't have an unvisited neighbours,
    * mark E as visited
    *
    * Only node left is C, since all of neighbours of C is
    * visited, marking C node as visited.
    *
    * All nodes in graph is visited, algorithm stops here,
    * table contains all nodes short distance when we start from
    * A node
    *
    * using 'previous' column we can build the path from A to
    * any node
    * Eg: A-E, from table we can reach E through B as previous
    * and can reach B with A as previous
    * A-B-E has distance of 4
    *
    * this is Greedy algorithm - tries to find optimal solution
    * to problem by making optimal choices at each step.
    *
    * Implementation:
    *   need to perform BFS on graph - need queue
    *   in BFS the order of visiting neighbours doesnt matter
    *   in this algorithm we have to visit the closest to start node
    *   Hence we have to use priority queue instead of regular queue
    *
    *   for each neighbour
    *     if found shorter path
    *       update distances table
    *       push neighbour to queue with priority as new calculated
    *       distance
    *
    *     2 HashMap to construct above table
    *      Map<Node,Integer> distances
    *       Map<Node, Node> previousNodes
    * */
    public Path getShortestPath(String from, String to) {
        var fromNode = nodes.get(from);
        var toNode = nodes.get(to);
        if (fromNode == null || toNode == null)
            throw new IllegalArgumentException();

        Map<Node, Integer> distances = new HashMap<>();
        for (Node node : nodes.values()) {
            distances.putIfAbsent(node, Integer.MAX_VALUE);
        }
        distances.replace(fromNode, 0);

        Map<Node, Node> previousNodes = new HashMap<>();
        Set<Node> visited = new HashSet<>();

        PriorityQueue<NodeEntry> queue = new PriorityQueue<>(
                Comparator.comparingInt(ne->ne.priority));
        queue.add(new NodeEntry(fromNode, 0));

        while (!queue.isEmpty()) {
            var current = queue.poll().node;
            visited.add(current);

            for (var edge : current.getEdges()) {
                if (visited.contains(edge.to))
                    continue;

                var newDistance = distances.get(current)
                                            + edge.weight;
                if (newDistance < distances.get(edge.to)) {
                    distances.replace(edge.to, newDistance);
                    queue.add(new NodeEntry(edge.to, newDistance));
                    previousNodes.put(edge.to, current);
                }
            }
        }

        return buildPath(toNode, previousNodes);
    }

    private Path buildPath(Node toNode, Map<Node, Node> previousNodes) {
        Stack<Node> stack = new Stack<>();
        stack.push(toNode);
        var previous = previousNodes.get(toNode);
        while (previous != null) {
            stack.push(previous);
            previous = previousNodes.get(previous);
        }

        var path = new Path();
        while (!stack.isEmpty())
            path.add(stack.pop().value);
        return path;
    }

    public boolean hasCycle() {
        Set<Node> visited = new HashSet<>();
        for (var node : nodes.values()) {
            if (!visited.contains(node) &&
                    hasCycle(node, null, visited))
                    return true;
        }
        return false;
    }
    private boolean hasCycle(Node node, Node parent, Set<Node> visited) {
        visited.add(node);
        for (var edge : node.getEdges()) {
            if (edge.to == parent)
                continue;
            if (visited.contains(edge.to) ||
                    hasCycle(edge.to, node, visited))
                return true;
        }
        return false;
    }

    /*
    * Minimum Spanning Tree:
    *
    *       A ---------2 ---------B
    *       -                  -  -
    *       -               -     -
    *     3 -         1 -         - 4
    *       -       -             -
    *       -    -                -
    *       - -                   -
    *       C --------5-----------D
    * Nodes represent the offices, and edges represent the connection
    * between the offices using cables. weight represent the cost,
    * need to find the minimal cost to lay cables between all offices
    * not necessarily the offices should be directly connected it
    * can be indirectly connected.
    *
    *      A --------- 2 --------- B
    *                           -
    *                       -
    *                 1 -             COST:8
    *               -
    *           -
    *        -
    *      C --------- 5 --------- D
    *      This is Minimal Spanning tree
    * we have converted a graph into a tree (a tree is a graph
    * without a cycle)
    * Spanning trees are not unique, there are many ways to
    * create a spanning tree for given graph
    *
    *      A                     B
    *      -                     -
    *      -                     -
    *      - 3                 4 -   COST:12
    *      -                     -
    *      -                     -
    *      C -------- 5 -------- D
    *    Another way of spanning tree, all nodes are connected
    *
    * If we have n nodes in our graph, we should have exactly
    * n - 1 edges
    *
    * if we have n edges for n nodes, it will become a graph (
    * will have cycle).
    *
    * if we have lesser than n - 1 edges then all nodes of graph
    * are not connected
    *      A-----------2-----------B
    *
    *
    *
    *
    *
    *      C-----------5-----------D
    *
    *  above is not a graph , also not a tree. it has n - 2 edges
    * not all nodes are connected.
    *
    * Spanning tree should have exactly n - 1 edges.
    *
    * each spanning tree have a COST (sum of weight of all of its
    * edges)
    *
    * One if the algorithm to form a spanning tree with minimal
    * cost (minimal spanning tree) is PRIM'S ALGORITHM
    *
    * PRIM's ALGORITHM:
    *
    * we start of from selecting any node in a graph
    *
    * if we start from A, we look at the edges with minimal weight
    * we could see minimum edge is 1 which we add to our tree and
    * then go to the node C.
    *
    * now our constructed spanning tree has 2 nodes - A and C
    *
    * we have to consider all the edges(A-B(3), C-B(2), C-D(5))
    * in our existing tree, and pick a minimum weight edge.
    * we pick C-B(2) minimal edge from the list and add the
    * edge to our existing spanning tree and go to the node B
    *
    * now our spanning tree A-C(1) C-B(2) has 3 nodes and 2 edges
    *
    * now we pick a minimum edge from all the existing edges from
    * constructed spanning tree.
    *
    * A-B(3) B-D(4) C-D(5) - minimum nodes is A-B(3) but we have
    * already visited A
    * Hence we pick the next minimum node. B-D(4) and add it to our
    * spanning tree
    *
    * now our spanning tree A-C(1) C-B(2) B-D(4) - we have visited
    * all nodes - hence our spanning tree is completely constructed.
    *
    * Prim's Algorithm - extending the tree by adding the smallest
    * connected edge
    * Greedy algorithm
    *
    * Implementation:
    * At each step we have to pick a minimum edge, from all the edges
    * connected to the existing nodes - we use priority queue
    * to pick a minimum edge from all edges in constant time.
    * repeat until our tree has all nodes.
    *
    * represent the spanning tree using our weighted graph, our spanning
    * tree will have all edges of graph with fewer edges.
    * */

    public WeightedGraph getMinimumSpanningTree() {
        var tree = new WeightedGraph();
        if (nodes.isEmpty())
            return tree;

        PriorityQueue<Edge> edges = new PriorityQueue<>(
                Comparator.comparingInt(e -> e.weight));

        var startNode = nodes.values().iterator().next();
        edges.addAll(startNode.getEdges());
        tree.addNode(startNode.value);

        if (edges.isEmpty())
            return tree;

        while (tree.nodes.size() < nodes.size()) {
            var minEdge = edges.poll();
            var nextNode = minEdge.to;
            if (tree.containsNode(nextNode.value))
                continue;

            tree.addNode(nextNode.value);
            tree.addEdge(minEdge.from.value, nextNode.value, minEdge.weight);

            for (var edge : nextNode.getEdges())
                if (!tree.containsNode(edge.to.value))
                    edges.add(edge);
        }
        return tree;
    }
    public boolean containsNode(String value) {
        return nodes.containsKey(value);
    }

}
