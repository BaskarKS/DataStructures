package com.baskarks.nonlinear.binarytrees;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        /*BinaryTree tree = new BinaryTree();
        System.out.println(tree.find(10));
        tree.insert(7);
        tree.insert(4);
        tree.insert(9);
        tree.insert(1);
        tree.insert(6);
        tree.insert(8);
        tree.insert(10);
        System.out.println(tree.find(8));
        System.out.println(tree.find(1));
        System.out.println(tree.find(7));
        tree.traversePreOrder();
        tree.traverseInOrder();
        tree.traversePostOrder();
        tree.heightOfTree();
        new BinaryTree().heightOfTree();

        System.out.println("Min Node in Tree : " + tree.min());
        System.out.println("Min Node in Binary Search Tree : " +
                tree.binarySearchTreeMin());
        BinaryTree otherTree = new BinaryTree();
        otherTree.insert(7);
        otherTree.insert(4);
        System.out.println(otherTree.min());
        otherTree.insert(9);
        otherTree.insert(1);
        otherTree.insert(6);
        otherTree.insert(8);
        otherTree.insert(10);
        otherTree.insert(12);
        System.out.println("Check Equality : " + tree.checkTreeEquality(otherTree));
        System.out.println("Is valid BinarySearchTree : " + tree.isBinarySearchTree());

        int distance = 2;
        System.out.println("printing Nodes at distance : " + distance);
        List<Integer> list = otherTree.getNodesAtDistance(distance);
        for (Integer each : list)
            System.out.print( each + ", ");
        System.out.println();

        System.out.println("Level Order Traversal :: ");
        otherTree.traverseLevelOrder();

        System.out.println("Size of Tree :"+ otherTree.size());

        System.out.println("No Of leaf in Tree :"+ otherTree.getLeafCount());

        System.out.println("get max Value : " + otherTree.getRecursiveMax());

        System.out.println("contains : " + otherTree.contains(10));
        System.out.println("contains : " + otherTree.contains(12));
        System.out.println("contains : " + otherTree.contains(5));
        System.out.println("contains : " + otherTree.contains(7));

        System.out.println("are Siblings : " + otherTree.areSiblings(4,9));
        System.out.println("are Siblings : " + otherTree.areSiblings(10,12));
        System.out.println("are Siblings : " + otherTree.areSiblings(8,10));
        System.out.println("are Siblings : " + otherTree.areSiblings(1,6));

        int findAncestors = 12;
        List<Integer> ancestors = otherTree.getAncestors(findAncestors);
        if (ancestors.isEmpty())
            System.out.println(findAncestors + " Not present in Tree, No Ancestors");
        else {
            System.out.println(findAncestors + " present in Tree, Ancestors are ");
            for (int each : ancestors)
                System.out.print(each + "..");
        }

*/
        BinaryTree tree = new BinaryTree();
        tree.insert(7);
        //tree.insert(4);
        //tree.insert(1);
        System.out.println(tree.min());
    }
}
