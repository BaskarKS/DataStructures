package com.baskarks.nonlinear.heaps;

/*
 * Priority Queues
 * Items get removed from the queue based on priority
 * not when the items join the queue
 *
 * if implementing a normal priority queue using array
 * time complexity for insert O(n) :
 * worst case, in order to shift the items to its correct
 * place.
 * time complexity for delete O(1) :
 * item is just fetched and size is reduced, no reordering
 *
 * If implementing a priority queue using Heap
 * time complexity for insert O(log n) :
 * shift the items to its correct place is just travel the
 * height of the tree.
 * time complexity for delete O(log n) :
 * item is just fetched and reordering items to travel height
 * of tree
 *
 * CONCLUSION:
 * For faster inserts and slower deletes
 * use priority queue implementing using Heap
 *
 * For faster deletes and slower inserts
 * use priority queue implementing using arrays
 * */

public class PriorityQueue {
    //This class is wrapper around heap class

    private Heap heap = new Heap(10);

    public void enqueue(int item) {
        heap.insert(item);
    }

    public int dequeue() {
        return heap.remove();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }
}
