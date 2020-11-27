package com.baskarks.nonlinear.graphs.directed;
/*
 * Graphs are used anywhere to model a bunch of objects
 *
 * We use graphs to represent connected objects such as
 * Routers in network
 * people on social media platform
 * (how they are connected and how strong the connection)
 *
 * Like trees, graphs also contains Nodes and edges
 * A tree is a kind of graph without any cycles
 *
 * In graphs we don't have root nodes
 * Node in graph is also called vertices - (Vertex plural)
 * Nodes can be connected to any other nodes,
 * no limitation of having edges connected to a Node
 *
 * If 2 nodes are directly connected - they are adjacent / neighbours
 *
 *  If edges have direction - they are directed graph (eg : Twitter)
 * Undirected graphs - (eg: Facebook and LinkedIn)
 *
 * The edges can have weight - weights are used to represent
 * how strong the connection is!
 *
 * Another application of Graph is => finding the shortest path
 * weights represent the distance / travelling cost/ traffic conditions
 *
 * Ways to Implement a Graph
 * 1. Adjacency List.
 * 2. Adjacency Matrix.
 *
 * 1. Adjacency Matrix:
 * Two dimensional array used to represent
 * row and column represent the nodes,
 * intersection values represent the connection(edges) between them
 *
 * Problem : lot of memory needed to maintain
 * for n nodes => space complexity is O(N^2)
 *
 * Add Node : need to add a row and column by allocating
 * new matrix and copy all existing items in new matrix -
 * Time Complexity - O(V ^ 2)
 *
 * Removing a node - need to delete a row and column by allocating
 * new matrix and copy all existing items in new matrix -
 * Time Complexity - O(V ^ 2)
 *
 * Adding a edge - find index of Node (map node to index in hashmap)
 * get index from hashtable O(1) - access matrix is O(1)
 *
 * Removing a edge - find index of Node (map node to index in hashmap)
 * get index from hashtable O(1) - access matrix is O(1)
 *
 * Check 2 nodes are connected - get index from hashmap O(1) and access
 * matrix is O(1)
 *
 * Finding all adjacent Nodes (neighbouring nodes) :
 * Get the node from hashmap for which the adjacent nodes need to be found
 * Read the entire row of the matrix for the respective node is equal to number
 * of vertices in graph
 * O(V)
 *
 * Space - O(V^2)
 * Adding a node / vertex - O(V^2)
 * Removing a node / vertex - O(V^2)
 * Add Edge - O(1)
 * Remove Edge - O(1)
 * Query Edge - O(1)
 * Finding neighbours - O(V)
 *
 * 2. Adjacency List:
 * Array of linked list - every element in array has linked list
 * this linked list contains adjacent/ neighbouring nodes
 *
 * HashMap maintained to find index of Node
 * for Node we get index from hashMap
 * Using the index, we go to respective array index and get the
 * linked list of neighbouring nodes
 *
 * We only store the edges that exist in linked list, more
 * space efficient than adjacency matrix.
 *
 * Space Complexity : one entry for each node in array O(V)
 * for each node we have 0 or more edges - Total edges O(E)
 * total - O(V + E)
 *
 * worst case scenario : every node is connected to every other node
 * which is DENSE GRAPH => total edges is O(V * (V - 1))
 * normalised to O(V^2)
 *
 * Time Complexity -
 *
 * Add a Node - we just add a new element in list O(1)
 *
 * Removing a Node -
 * Remove corresponding element from list and
 * have to remove its entries in other vertices/node Linked list.
 * O(V + E)
 * worst case => dense graphs => O(V^2)
 *
 * Adding a Edge -
 * 1. Find the node index from hashmap is O(1)
 * 2. Add a node in the list by looking the entire list, to
 * ensure the edge doesn't exist already - O(K)
 * (k is no of edges that node has)
 * worst case => O(V) (can have V nodes in the list)
 *
 * Removing a Edge -
 * Find the node index from hashmap is O(1)
 * and iterate the entire list in respective node index in array
 * and find the element to remove O(K)
 * worst Case => O(V)
 *
 * Query Edge (Check 2 nodes are connected) - O(K)
 * worst Case : O(V)
 *
 * Find adjacent Nodes - find the node index from hashmap is O(1)
 * and iterate entire list of the node location in list.
 * O(K) and worst case : O(V)
 *
 * Params                            Matrix            List             Dense Graph
 *                                                           Using List
 * space                            O(V^2)        O(V + E)         O(V^2)
 * Add Edge                       O(1)              O(K)               O(V)
 * Remove Edge                O(1)              O(K)               O(V)
 * Query Edge                   O(1)              O(K)               O(V)
 * Finding Neighbours      O(V)              O(K)               O(V)
 * Add Node                    O(V^2)           O(1)             O(V^2)
 * Remove Node             O(V^2)         O(V^2)          O(V^2)
 *
 * for Dense graph / too much of edges better use matrix
 * otherwise List is better
 * */
import java.util.*;

public class Graph {
    private class Node {
        private String label;
        public Node(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    private Map<String, Node> nodes = new HashMap<>();
    private Map<Node, List<Node>> adjacencyList= new HashMap<>();

    public void addNode(String label) {
        Node node = new Node(label);
        nodes.putIfAbsent(label, node);
        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(String from, String to) {
        var fromNode = nodes.get(from);
        if (fromNode == null)
            throw new IllegalArgumentException("From Node is Invalid");

        var toNode = nodes.get(to);
        if (toNode == null)
            throw new IllegalArgumentException("To Node is Invalid");

        adjacencyList.get(fromNode).add(toNode);
        // for undirected graphs the reverse edge also be added
        //adjacencyList.get(toNode).add(fromNode);
    }

    public void print() {
        for (var source : adjacencyList.keySet()) {
            List<Node> targets = adjacencyList.get(source);
            if (!targets.isEmpty())
                System.out.println(source + " is connected to " +
                                        targets);
        }

    }

    public void removeNode(String label) {
        var node = nodes.get(label);
        if (node == null)
            return;
        //remove entry in all lists
        for (var n : adjacencyList.keySet())
            adjacencyList.get(n).remove(node);

        adjacencyList.remove(node);
        nodes.remove(node);
    }

    public void removeEdge(String from, String to) {
        var fromNode = nodes.get(from);
        var toNode = nodes.get(to);

        if (fromNode == null || toNode == null)
            return;

        adjacencyList.get(fromNode).remove(toNode);
    }

    /*
    * In Tree traversal we always start from root node, all nodes
    * are accessible from root node.
    *
    * In graphs we don't have particular root node, we start traversing
    * from any node, but we can access only nodes access from
    * that node not all nodes in graph.*/

    public void traverseDepthFirst(String root) {
        Node node = nodes.get(root);
        if(node == null)
            return;
        traverseDepthFirst(node, new HashSet<>());
    }

    private void traverseDepthFirst(Node root, Set<Node> visited) {
        System.out.println(root);
        visited.add(root);

        for (Node neighbour : adjacencyList.get(root))
            if (!visited.contains(neighbour))
                traverseDepthFirst(neighbour, visited);
    }

    public void traverseDepthFirstIterative(String fromNode) {
        var node = nodes.get(fromNode);
        if (node == null)
            return;
        Set<Node> visited = new HashSet<>();
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            var current = stack.pop();

            if (visited.contains(current))
                continue;

            System.out.println(current);
            visited.add(current);

            for (var neighbour : adjacencyList.get(current))
                    stack.push(neighbour);
        }
    }

    public void breadthFirstTraversal(String node) {
        if (node == null || node.isEmpty())
            return;

        var fromNode = nodes.get(node);
        if (fromNode == null)
            return;

        Set<Node> visited = new HashSet<>();

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(fromNode);

        while (!queue.isEmpty()) {
            var current = queue.remove();
            if (visited.contains(current))
                continue;

            System.out.println(current);
            visited.add(current);

            for (var neighbour : adjacencyList.get(current))
                if (!visited.contains(neighbour))
                    queue.add(neighbour);
        }

    }

    // With Topological sorting, we can figure out the
    // right order to process the nodes of the graph.
    //
    // Eg: nodes represent the projects, another application
    // is in the process of scheduling the jobs, if we have jobs that
    // are dependent on each other. we can use topological sorting
    // to come up with right order to perform the job

    //Doesn't provide unique results

    //Works on graph without a cycle.
    //our graph should be DIRECTED ACYCLIC GRAPH

    //Do a DFS on graph, we will be able to find a node which doesn't
    //have any outgoing edges.(means no other node is depended on this end node)
    //nodes having outgoing edges are on top of hierarchy should come first

    //we use Stack to implement this algorithm.
    //Do DFS and go deep in the graph. and add the end node to the stack,
    //while coming back, the nodes which we passed will be encountered,
    //If we have visited all of its children and the node is also not
    //in the visited set, we add it into the stack

    //at last we pop out the stack to find the right order.

    /*
                        /------------> B----------->\
                    A                                         D
                       \-------------->C ----------->/

                       in Stack =>   ACBD => D is pushed first, A is pushed last
                       output List => popped from stack and list formed => ACBD
    * */
    public List<String> topologicalSort() {

        Stack<Node> stack = new Stack<>();
        Set<Node> visited = new HashSet<>(); // visited in passed in
        // common for visiting all nodes, passing common visited is the turning point of the solution

        for (var eachNode : nodes.values()) // explore all the node to find the topological order
            topologicalSort(eachNode, stack, visited);

        //stack contains the total order,
        List<String> sorted = new ArrayList<>();
        while(!stack.isEmpty())
            sorted.add(stack.pop().label);

        return sorted;
    }
    private void topologicalSort(Node root, Stack<Node> stack, Set<Node> visited) {

        if (visited.contains(root)) // if the node is already visited, just return // this check is the turning point of the solution
            return;

        visited.add(root); // if the node is not visited, make it visited

        for (var neighbour : adjacencyList.get(root)) // explore each neighbour in depth-first
            topologicalSort(neighbour, stack, visited);

        stack.push(root); // if no more neighbours are left to explore, push it to stack
    }

    /*
    * Cycle Detection - in Directed Graphs
    *
    * Topological Sorting doesn't work if graph has a cycle
    *
    * maintain 3 sets of nodes
    * 1st set (all) - to track all nodes in graph
    * 2nt set (visiting)- to track the current visiting node (still
    *           some children yet to visit)
    * 3rd set (visited)- to track all visited nodes (all of its
    *           children are visited)
    *
    * add all nodes to 1st set,
    * pick any node from 1st set (remove it in 1st set) and put it in visiting set
    * make a DFS on the picked node, explore its children by moving it
    * from 'all set' to 'visiting set' and repeat until a children
    * doesn't have a neighbours, when all of the children is explored
    * move those nodes from 'visiting set' to 'visited set'
    *
    * while exploring the neighbours, if we encounter a node which is still in
    * 'visiting set' then we conclude that we have a cycle in graph
    *
    * to find the nodes which are part of a cycle
    * by maintaining a hashMap of a 'node - parent' relationship
    * the starting node's parent is marked as 'null', when we find a cycle
    * based on above algorithm we can used this relation hashMap to
    * find the nodes part of cycle.
    *
    * */

    public boolean hasCycle() {
        Set<Node> all = new HashSet<>();
        for (Node each : nodes.values())
            all.add(each);
        Set<Node> visiting = new HashSet<>();
        Set<Node> visited = new HashSet<>();

        while (!all.isEmpty()) { // iterate till all list become empty
            Node current = all.iterator().next(); // get a node from all list
            if (hasCycle(current, all, visiting, visited)) // check the fetched node has a cycle
                return true; // return immediately if the node forms a cycle, no need to check others
        }
        return false; // means all nodes don't form a cycle
    }

    private boolean hasCycle(Node node, Set<Node> all,
                             Set<Node> visiting, Set<Node> visited) {
        all.remove(node); // remove the node from all list
        visiting.add(node); // add the node to visiting list as its been explored further

        for (var neighbour : adjacencyList.get(node)) { // explore all the neighbours of node
            if (visited.contains(neighbour)) // skip if the node is already visited
                continue;

            if (visiting.contains(neighbour)) // if the neighbour is still in visiting, theres a cycle
                return true;

            if(hasCycle(neighbour, all, visiting, visited)) // do a depth-first on the neighbour node
               return true; // return immediately, exploring neighbour forms a cycle
        }
        visiting.remove(node); // all neighbours are explored, hence its removed in visiting list
        visited.add(node); // all neighbours are explored, hence its added to visited list
        return false; // control comes here when the node doesn't form a cycle
    }
    private boolean hasCycleInGraph() {
        Set<Node> all = new HashSet<>(nodes.values());
        Set<Node> visiting = new HashSet<>();
        Set<Node> visited = new HashSet<>();

        while(!all.isEmpty()) {
            Node toVisit = all.iterator().next();
            if (checkCycle(toVisit, all, visiting, visited))
                return true;
        }
        return false;
    }
    private boolean checkCycle(Node node, Set<Node> all,
                               Set<Node> visiting, Set<Node> visited) {

        all.remove(node);
        visiting.add(node);

        for (Node neighbour : adjacencyList.get(node)) {
            if (visited.contains(neighbour))
                continue;
            if (visiting.contains(neighbour))
                return true;
            if (checkCycle(neighbour, all, visiting, visited))
                return true;
        }
        visiting.remove(node);
        visited.add(node);
        return false;
    }
}
