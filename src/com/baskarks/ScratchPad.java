package com.baskarks;


import com.baskarks.nonlinear.binarytrees.BinaryTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class ScratchPad {
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

        public void insert(int value) {
            Node current = root;
            if (current == null) {
                root = new Node(value);
                return;
            }
            while(current != null) {
                if (current.value == value)
                    break;
                else if (value < current.value) {
                    //left
                    if (current.leftChild == null) {
                        current.leftChild = new Node(value);
                        break;
                    }
                    current = current.leftChild;
                }
                else {
                    if (current.rightChild == null) {
                        current.rightChild = new Node(value);
                        break;
                    }
                    //right
                    current = current.rightChild;
                }
            }
        }

        public boolean find(int value) {
            Node current = root;
            if (current == null)
                return false;
            while (current != null) {
                if (value == current.value)
                    return true;
                else if (value < current.value)
                    current = current.leftChild;
                else
                    current = current.rightChild;
            }
            return false;
        }

        public void traverseDepthFirstPreOrder() {
            if (root == null)
                return;
           traverseDepthFirstPreOrder(root);
        }
        private void traverseDepthFirstPreOrder(Node root) {
            if (root == null)
                return;
            System.out.println(root.value);
            traverseDepthFirstPreOrder(root.leftChild);
            traverseDepthFirstPreOrder(root.rightChild);
        }

        public void traverseDepthFirstInOrder() {
            if (root == null)
                return;
            traverseDepthFirstInOrder(root);
        }
        // traverse the tree in sorted order
        private void traverseDepthFirstInOrder(Node root) {
            if (root == null)
                return;
            traverseDepthFirstInOrder(root.leftChild);
            System.out.println(root.value);
            traverseDepthFirstInOrder(root.rightChild);
        }

        public void traverseDepthFirstPostOrder() {
            if (root == null)
                return;
            traverseDepthFirstPostOrder(root);
        }
        private void traverseDepthFirstPostOrder(Node root) {
            if (root == null)
                return;
            traverseDepthFirstPostOrder(root.leftChild);
            traverseDepthFirstPostOrder(root.rightChild);
            System.out.println(root.value);
        }

        public void traverseBreadthFirst() {
            if (root == null)
                return;
            traverseBreadthFirst(root);
        }
        private void traverseBreadthFirst(Node root) {
            Queue<Node> queue = new ArrayDeque<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                Node current = queue.poll();
                System.out.println(current.value);
                if (current.leftChild != null) queue.add(current.leftChild);
                if (current.rightChild != null) queue.add(current.rightChild);
            }
        }
    }
