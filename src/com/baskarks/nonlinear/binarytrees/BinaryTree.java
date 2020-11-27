package com.baskarks.nonlinear.binarytrees;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
    private class Node {
        private int value;
        private Node leftChild;
        private Node rightChild;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                     value +
                    '}';
        }
    }

    private Node root;

    public Node getRoot() {
        return root;
    }

    public void insert(int value) {
        Node current = getRoot();
        Node toInsert = new Node(value);
        if (current == null) {
            root = toInsert;
            return;
        }
        while (current != null) {
            if (current.value < value) {
                if(current.rightChild == null) {
                    current.rightChild = toInsert;
                    break;
                }
                current = current.rightChild;
            } else if (current.value > value) {
                if(current.leftChild == null) {
                    current.leftChild = toInsert;
                    break;
                }
                current = current.leftChild;
            } else if(current.value == value) {
                break;
            }
        }
    }
    public boolean find(int value) {
        Node current = getRoot();
        if (current == null)
            return false;
        while (current != null) {
            if (current.value > value)
                current = current.leftChild;
            else if (current.value < value)
                current = current.rightChild;
            else
                return true;
        }
        return false;
    }
    public void traversePreOrder() {
        System.out.println();
        traversePreOrder(getRoot());
    }
    private void traversePreOrder(Node root) {
        if (root == null)
            return;
        System.out.print(root.value + ", ");
        traversePreOrder(root.leftChild);
        traversePreOrder(root.rightChild);
    }
    public void traverseInOrder() {
        System.out.println();
        traverseInOrder(getRoot());
    }
    private void traverseInOrder(Node root) {
        if (root == null)
            return;
        traverseInOrder(root.leftChild);
        System.out.print(root.value + ", ");
        traverseInOrder(root.rightChild);
    }
    public void traversePostOrder() {
        System.out.println();
        traversePostOrder(getRoot());
    }
    private void traversePostOrder(Node root) {
        if (root == null)
            return;
        traversePostOrder(root.leftChild);
        traversePostOrder(root.rightChild);
        System.out.print(root.value + ", ");
    }
    public int heightOfTree() {
        int height = treeHeight(getRoot());
        //System.out.println("Height of Tree : " + height);
        return height;
    }
    private int treeHeight(Node root) {
        if (root == null)
            return -1;
        if (isLeaf(root))
           return 0;
        return 1 + Math.max(
                treeHeight(root.leftChild),
                treeHeight(root.rightChild));
    }
    private boolean isLeaf(Node node) {
        return node.leftChild == null && node.rightChild == null;
    }
    //O(n)
    public int min() {
        if (getRoot() == null)
            return -1;
        return min(getRoot());
    }
    private int min(Node root) {
        if (root == null)
            return Integer.MAX_VALUE;
        if (isLeaf(root))
            return root.value;
        int left = min(root.leftChild);
        int right = min(root.rightChild);
        return Math.min(Math.min(left, right), root.value);
    }
    //O(Log(n))
    public int binarySearchTreeMin() {
        return binarySearchTreeMin(getRoot());
    }

    private int binarySearchTreeMin(Node root) {
        if(root == null) throw new IllegalArgumentException("Invalid Arg");
        while(root.leftChild != null)
            root = root.leftChild;
        return root.value;
    }

    public boolean checkTreeEquality(BinaryTree otherTree) {
        if (otherTree == null)
            return false;
        return checkTreeEquality(getRoot(), otherTree.getRoot());
    }

    // pre order traversal
    private boolean checkTreeEquality(Node root, Node otherRoot) {
        if (root == null && otherRoot == null)
            return true;

        if (root != null && otherRoot != null)
            return root.value == otherRoot.value &&
                    checkTreeEquality(root.leftChild, otherRoot.leftChild) &&
                        checkTreeEquality(root.rightChild, otherRoot.rightChild);

        return false;
    }

    public boolean isBinarySearchTree() {
        return isBinarySearchTree(getRoot(), Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    //Pre order traversal
    private boolean isBinarySearchTree(Node root, int min, int max) {
        if (root == null)
            return true;

        if (root.value < min && root.value > max)
            return false;

        return isBinarySearchTree(root.leftChild, min, root.value - 1) &&
                    isBinarySearchTree(root.rightChild, root.value + 1, max);
    }

    public List<Integer> getNodesAtDistance(int distance) {
        List<Integer> list = new ArrayList<>();
        getNodesAtDistance(getRoot(), distance, list);
        return list;
    }
    private void getNodesAtDistance(Node root, int distance, List<Integer> list) {
        if (root == null)
            return;
        if (distance == 0) {
            list.add(root.value);
            return;
        }
        getNodesAtDistance(root.leftChild, distance - 1, list);
        getNodesAtDistance(root.rightChild, distance - 1, list);
    }

    public void traverseLevelOrder() {
        for (int i = 0; i <= heightOfTree(); i++) {
            for(Integer node : getNodesAtDistance(i))
                System.out.print(node + ", ");
            System.out.println();
        }
    }
    public int size() {
        return size(getRoot());
    }
    private int size(Node root) {
        if (root == null)
            return 0;

        if (root.leftChild == null && root.rightChild == null)
            return 1;

        return 1 + size(root.leftChild) + size(root.rightChild);
    }
    public int getLeafCount() {
        return getLeafCount(getRoot());
    }
    private int getLeafCount(Node root) {
        if (root == null)
            return 0;
        if (isLeaf(root))
            return 1;
        return getLeafCount(root.leftChild) + getLeafCount(root.rightChild);
    }

    public int getIterativeMax() {
        return getIterativeMax(getRoot());
    }
    private int getIterativeMax(Node root) {
        if (root == null)
            return -1;

        while(root.rightChild != null)
            root = root.rightChild;

        return root.value;
    }

    public int getRecursiveMax() {
        return getRecursiveMax(getRoot());
    }
    private int getRecursiveMax(Node root) {
        if (root == null)
            return -1;
        return root.rightChild != null ? getRecursiveMax(root.rightChild) : root.value;
    }

    public boolean contains(int value) {
        return contains(getRoot(), value);
    }
    private boolean contains(Node root, int value) {
        if (root == null)
            return false;
        if (root.value == value)
            return true;

        return (value < root.value) ?
                contains(root.leftChild, value):
                contains(root.rightChild, value);
    }

    public boolean areSiblings(int firstValue, int secondValue) {
        return areSiblings(getRoot(), firstValue, secondValue);
    }
    private boolean areSiblings(Node root, int firstValue, int secondValue) {
        if (root == null || (root.leftChild == null || root.rightChild == null))
            return false;
        if (root.leftChild != null && root.rightChild != null) {
            int leftValue = root.leftChild.value;
            int rightValue = root.rightChild.value;
            return (leftValue == firstValue) && (rightValue == secondValue) ||
                    (leftValue == secondValue) && (rightValue == firstValue) ||
                    areSiblings(root.leftChild, firstValue, secondValue) ||
                    areSiblings(root.rightChild, firstValue, secondValue);
        }
        return false;
    }

    public List<Integer> getAncestors(int value) {
        List<Integer> ancestors = new ArrayList<>();
        getAncestors(getRoot(), value, ancestors);
        return ancestors;
    }
    private boolean getAncestors(Node root, int value, List<Integer> ancestors) {
        if (root == null)
            return false;

        boolean status = false;
        if (root.value == value) {
            ancestors.add(root.value);
            return true;
        }
        else if(value < root.value)
            status = getAncestors(root.leftChild, value, ancestors);
        else
            status = getAncestors(root.rightChild, value, ancestors);

        if (status)
            ancestors.add(root.value);

        return status;
    }
}
