package com.baskarks.nonlinear.binarytrees.avltrees;

public class main {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        tree.insert(7);
        tree.insert(4);
        tree.insert(9);
        tree.insert(1);
        tree.insert(6);
        tree.insert(8);
        tree.insert(10);
        //tree.insert(12);
        //tree.insert(14);
        tree.printTree();
        System.out.println("is Tree Balanced : " + tree.isBalanced());
        System.out.println("is Perfect Tree : " + tree.isPerfectTree());
    }
}
