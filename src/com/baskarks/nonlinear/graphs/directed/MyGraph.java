package com.baskarks.nonlinear.graphs.directed;

import java.util.*;

public class MyGraph {
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

    public void depthFirstTraversal() {
        Set<Node> visited = new HashSet<>();
        for (Node eachNode : adjacencyList.keySet())
            depthFirstTraversal(eachNode, visited);
    }

    private void depthFirstTraversal(Node root, Set<Node> visited) {
        if (root == null)
            return;

        if (visited.contains(root))
            return;

        System.out.println(root.label);
        visited.add(root);

        for (Node neighbours : adjacencyList.get(root))
            depthFirstTraversal(neighbours, visited);
    }

    public void traverseDepthFirstIterative(String fromNode) {
        var node = nodes.get(fromNode);
        if (node == null)
            return;
        Stack<Node> traverse = new Stack<>();
        Set<Node> visited = new HashSet<>();
        traverse.push(node);
        while (!traverse.isEmpty()) {
            var current = traverse.pop();
            System.out.println(current);
            visited.add(current);
            for (var neighbour : adjacencyList.get(current))
                if (!visited.contains(neighbour))
                    traverse.push(neighbour);
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

            System.out.println(current);
            visited.add(current);

            for (var neighbour : adjacencyList.get(current))
                if (!visited.contains(neighbour))
                    queue.add(neighbour);
        }
    }

    public List<String> topologicalSort() {
        List<String> jobOrder = new ArrayList<>();
        Stack<Node> jobs = new Stack<>();
        Set<Node> visited = new HashSet<>();
        for (var eachNode : adjacencyList.keySet())
            if (!visited.contains(eachNode))
                topologicalSort(eachNode, jobs, visited);
        while(!jobs.isEmpty()) {
            var node = jobs.pop();
            jobOrder.add(node.label);
        }
        return jobOrder;
    }
    private void topologicalSort(Node root, Stack<Node> jobs, Set<Node> visited) {

        visited.add(root);

        if (adjacencyList.get(root) == null) {
            jobs.push(root);
            return;
        }

        for (var neighbour : adjacencyList.get(root)) {
            if (!visited.contains(neighbour))
                topologicalSort(neighbour, jobs, visited);
        }

        jobs.push(root);
    }

    public boolean hasCycle() {
        Set<Node> all = new HashSet<>();
        Set<Node> visiting = new HashSet<>();
        Set<Node> visited = new HashSet<>();
        for (Node each : nodes.values())
            all.add(each);
        while (!all.isEmpty()) {
            Node explore = all.iterator().next();
            boolean status = detectCycle(explore, all, visiting, visited);
            if (status == true)
                return true;
            all.remove(explore);
        }
        return false;
    }
    private boolean detectCycle(Node node, Set<Node> all,
                                Set<Node> visiting, Set<Node> visited) {
        all.remove(node);
        if (visiting.contains(node))
            return true;
        visiting.add(node);
        for (var neighbour : adjacencyList.get(node)) {
            if(detectCycle(neighbour, all, visiting, visited))
                return true;
        }
        visiting.remove(node);
        visited.add(node);
        return false;
    }
}
