package com.baskarks.nonlinear.binarytrees.avltrees;

public class AVLTree {
    private class AVLNode {
        private AVLNode leftChild;
        private AVLNode rightChild;
        int value;
        int height;
        public AVLNode(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "AVLNode{" +
                    "value=" + value +
                    '}';
        }
    }
    private AVLNode root;
    public AVLNode getRoot() {
        return this.root;
    }
    public void printTree() {
        printTree(getRoot());
        System.out.println();
    }
    private void printTree(AVLNode root) {
        if (root == null)
            return;

        printTree(root.leftChild);
        System.out.print(root.value + "..");
        printTree(root.rightChild);
    }
    public void insert(int value) {
        root = insert(getRoot(), value);
    }
    private AVLNode insert(AVLNode root, int value) {
        if (root == null)
            return new AVLNode(value);

        if (value < root.value)
            root.leftChild = insert(root.leftChild, value);
        else if (value > root.value )
            root.rightChild = insert(root.rightChild, value);

        //After insert, calculate height of node
  /*      root.height = 1 + Math.max(height(root.leftChild),
                                    height(root.rightChild));*/
        setHeight(root);

        //After calculating height, check balanced factor
        //int balanceFactor = height(root.leftChild) - height(root.rightChild);
        //balance(root);
        //return root;
        return balance(root);
    }
    private AVLNode balance(AVLNode root) {
        if (isLeftHeavy(root)) {
            //do left rotate on left child if balance factor on
            //left child is < 0 and do right rotate
            System.out.println(root.value + " is Left Heavy");
            if (balanceFactor(root.leftChild) < 0)
                root.leftChild = leftRotate(root.leftChild);
            return rightRotate(root);
        } else if (isRightHeavy(root)) {
            // do right rotate on right child if balance factor
            // on right child is > 0 and the left rotate
            System.out.println(root.value + " is Right Heavy");
            if (balanceFactor(root.rightChild) > 0)
                root.rightChild = rightRotate(root.rightChild);
            return leftRotate(root);
        }
        return root;
    }
    private AVLNode leftRotate(AVLNode node) {
        AVLNode newRoot = node.rightChild;
        node.rightChild = newRoot.leftChild;
        setHeight(node);
        newRoot.leftChild = node;
        setHeight(newRoot);
        return newRoot;
    }
    private AVLNode rightRotate(AVLNode root) {
        AVLNode newRoot = root.leftChild;
        root.leftChild = newRoot.rightChild;
        newRoot.rightChild = root;
        setHeight(root);
        setHeight(newRoot);
        return newRoot;
    }
    private void setHeight(AVLNode node) {
        node.height = 1 + Math.max(height(node.leftChild),
                height(node.rightChild));
    }
    private int height(AVLNode node) {
        return (node != null) ? node.height : -1;
    }
    private boolean isLeftHeavy(AVLNode node) {
        return balanceFactor(node) > 1;
    }
    private boolean isRightHeavy(AVLNode node) {
        return balanceFactor(node) < -1;
    }
    private int balanceFactor(AVLNode node) {
        return (node == null) ? 0 : height(node.leftChild) - height(node.rightChild);
    }

    public boolean isBalanced() {
        return isBalanced(getRoot());
    }
    private boolean isBalanced(AVLNode root) {
        if (root == null)
            return true;
        int balanceFactor = balanceFactor(root);
        if (balanceFactor > -2 && balanceFactor < 2)
            return isBalanced(root.leftChild)
                    && isBalanced(root.rightChild);
        return false;
    }

    /*
    A Perfect Binary Tree of height h (where height is number of nodes
    on path from root to leaf) has 2h – 1 nodes.

    Below is an idea to check whether a given Binary Tree is perfect or not.

    Find depth of any node (in below tree we find depth of leftmost node).
    Let this depth be d.Now recursively traverse the tree and check for
    following two conditions. Every internal node should have both children
    non-empty

    All leaves are at depth ‘d’
    */
    private int getTreeHeight(AVLNode root) {
        if (root == null)
            return 0;
        return 1 + Math.max(getTreeHeight(root.leftChild),
                                getTreeHeight(root.rightChild));
    }
    private int sizeOfTree(AVLNode root) {
        if (root == null)
            return 0;
        return 1 + sizeOfTree(root.leftChild) + sizeOfTree(root.rightChild);
    }
    public boolean isPerfectTree() {
        int treeHeight = getTreeHeight(getRoot());
        int noOfNodes = sizeOfTree(getRoot());
        return noOfNodes == (Math.pow(2, treeHeight) - 1);
    }
}

