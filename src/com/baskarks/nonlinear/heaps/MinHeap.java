package com.baskarks.nonlinear.heaps;

import java.util.Arrays;

public class MinHeap {

    class HeapNode {
        private String value;
        private int key;
        public HeapNode(int key, String value) {
            this.key = key;
            this.value = value;
        }
        public int getKey() {
            return this.key;
        }
        public String getValue() {
            return this.value;
        }

        @Override
        public String toString() {
            return "HeapNode{" +
                    "value='" + value + '\'' +
                    ", key=" + key +
                    '}';
        }
    }

    private HeapNode[] items;
    private int size;

    public MinHeap(int size) {
        items = new HeapNode[size];
    }

    public boolean isFull() {
        return size == items.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        return "MinHeap{" +
                "items=" + Arrays.toString(items) +
                '}';
    }

    public void insert(int key, String value) {
        if (isFull())
            throw new IllegalStateException("Heap is Full");

        items[size++] = new HeapNode(key, value);
        bubbleUp();
    }
    private void bubbleUp() {
        int index = size - 1;

        while (index > 0 &&
                key(index) < key(parent(index))) {
            swap(index, parent(index));
            index = parent(index);
        }
    }
    private int key(int index) {
        return items[index].getKey();
    }
    private void swap(int fromIdx, int toIdx) {
        HeapNode tempVal = items[toIdx];
        items[toIdx] = items[fromIdx];
        items[fromIdx] = tempVal;
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    public HeapNode remove() {
        if (isEmpty())
            throw new IllegalStateException("Heap is Empty...");

        var removedNode = items[0];
        items[0] = items[--size];
        items[size] = null;
        bubbleDown();
        return removedNode;
    }

    private void bubbleDown() {
        int index = 0;
        while (index < size && !isValidParent(index)) {
            var minIdx = getMinChildIdx(index);
            swap(index, minIdx);
            index = minIdx;
        }
    }
    private int getMinChildIdx(int index) {
        if (!hasLeftChild(index))
            return index;
        if (!hasRightChild(index))
            return leftChildIdx(index);
        return key(leftChildIdx(index)) < key(rightChildIdx(index)) ?
                    leftChildIdx(index) : rightChildIdx(index);
    }
    private boolean isValidParent(int index) {
        if (!hasLeftChild(index))
            return true;
        var status = key(index) <= key(leftChildIdx(index));
        if (hasRightChild(index))
            status &= key(index) <= key(rightChildIdx(index));
        return status;
    }
    private int leftChildIdx(int index) {
        return index * 2 + 1;
    }
    private int rightChildIdx(int index) {
        return index * 2 + 2;
    }
    private boolean hasLeftChild(int index) {
        return leftChildIdx(index) < size;
    }
    private boolean hasRightChild(int index) {
        return rightChildIdx(index) < size;
    }
}
