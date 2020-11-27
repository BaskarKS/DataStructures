package com.baskarks.nonlinear.heaps;

/*
 * Its a complete tree which satisfies the heap property
 *
 * complete tree - All level is full of nodes, potentially
 * the last level may not be filled completely, but should
 * be filled from left to right
 *
 * Heap property - every node should be greater than equal to
 * all of its child nodes, its Max Heap. Every node is lesser than
 * equal to its child nodes is Min Heap
 *
 * Binary Heap it tree consist of 2 child for every nodes
 *
 * Applications of Heap:
 * 1. Sorting data (Heap Sort)
 * 2. Implementing Graph algorithms( find shortest path
 * between 2 end points)
 * 3. used to implement priority queues
 * 4. finding the kth smallest and largest values
 *
 * Heap is build while inserting the nodes at leaf, and check
 * for heap property. if it doesn't satisfy, we move it
 * to its right place (move up / bubble up)
 * Max Heap - added new node.value of new node is large value it will bubble up
 * Min Heap - added new node.value of new node is small value it will bubble up
 *
 * Time Complexity - Longest path the value can bubble
 * up is equal to the height of the tree ( equal to the
 * value searched in a binary search tree O(log n))
 *
 * maximum number of comparisons we do is equal to the height of the tree
 *
 * Im Mathematics - in a balanced binary tree, the height
 * of the tree is equal to log(n) => h = log(n)
 *
 * Deletion - fetch / delete the item at root node, pick
 * the last leaf node and place it at root, now compare the
 * placed value at root with its children and swap the largest
 * value and continue the same until the value find its correct
 * place - this process in bubble down.
 *
 * complexity is O(log n) because it can travel the longest
 * distance of equal to height of the tree.
 *
 * In Max heap finding the largest element is O(1) operation
 * by just returning the root value
 *
 * In Min heap finding the smallest element is O(1) operation
 * by just returning the root value
 *
 * Heaps are better to implement using an array, instead
 * of node class and maintaining child nodes
 *
 * Implementing using Array:
 * left child index = 2 * parent_index + 1
 * right child index = 2 * parent_index + 2
 * parent index = (Any child_index - 1) / 2
 *
 * Heaps don't support looking up values because of the nodes
 * are structured in such fashion, they only allow us to inspect the
 * maximum or minimum value depending on type of heap (max/ min heap)
 * in max heap, maximum value reading is O(1).
 * */

import java.util.Arrays;

public class Heap {
    private int[] items;
    private int size;

    public Heap(int size) {
        items = new int[size];
    }

    public boolean isFull() {
        return size == items.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int max() {
        if (isEmpty())
            throw new IllegalStateException("Heap is Empty");
        return items[0];
    }

    public void insert(int value) {
        if (isFull())
            throw new IllegalStateException("Heap is Full");

        items[size++] = value;
        //bubble up
        bubbleUp();
    }
    private void bubbleUp() {
        var index = size - 1;
        while (index > 0 && items[index] > items[parent(index)]) {
            //swap items at current index and parent index
            swap(index, parent(index));
            index = parent(index);
        }
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private void swap(int first, int second) {
        var temp = items[first];
        items[first] = items[second];
        items[second] = temp;
    }
    private int leftChildIndex(int index) {
        return (2 * index) + 1;
    }

    private int rightChildIndex(int index) {
        return (2 * index) + 2;
    }

    private int leftChild(int index) {
        return items[leftChildIndex(index)];
    }
    private int rightChild(int index) {
        return items[rightChildIndex(index)];
    }

    //can go out of bound
    private boolean isValidParent(int index) {
        if (!hasLeftChild(index)) // doesn't have left child
            return true;

        var isValid = items[index] >= leftChild(index); // has left child

        if (hasRightChild(index)) // has right child
            isValid &= items[index] >= rightChild(index); //validate both left and right child

        return isValid;
    }

    //BoundCheck Function
    private boolean hasLeftChild(int index) {
        return leftChildIndex(index) <= size;
    }

    //BoundCheck Function
    private boolean hasRightChild(int index) {
        return rightChildIndex(index) <= size;
    }

    //can go out of bound
    private int largerChildIndex(int index) {
        if (!hasLeftChild(index)) //doesn't have left child
            return index;

        if(!hasRightChild(index)) //doesn't have right child
            return leftChildIndex(index);
        // return larger child index
        return leftChild(index) > rightChild(index) ?
                leftChildIndex(index) : rightChildIndex(index);
    }

    private void bubbleDown() {
        int index = 0;
        while (index <= size && !isValidParent(index)) {
            var largerChildIndex = largerChildIndex(index);
            swap(index, largerChildIndex);
            index = largerChildIndex;
        }
    }

    public int remove() {
        if(isEmpty())
            throw new IllegalStateException("Cannot remove an item on Empty Heap");
        var root = items[0];
        items[0] = items[--size];
        bubbleDown();
        return root;
    }

    @Override
    public String toString() {
        return "Heap{" +
                "items=" + Arrays.toString(items) +
                '}';
    }
}
