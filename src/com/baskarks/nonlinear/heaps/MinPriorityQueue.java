package com.baskarks.nonlinear.heaps;

public class MinPriorityQueue {
    private MinHeap heap;
    public MinPriorityQueue(int size) {
        heap = new MinHeap(size);
    }

    public void add(String value, int priority) {
        heap.insert(priority, value);
    }
    public String remove() {
        MinHeap.HeapNode node = heap.remove();
        return node.getValue();
    }
    public boolean isEmpty() {
        return heap.isEmpty();
    }
}
