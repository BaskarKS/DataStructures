package com.baskarks.nonlinear.heaps;

import java.util.Arrays;

public class ScratchPad {
    private int items[];
    private int size;
    public ScratchPad(int size) {this.size = 0;
        items = new int[size];
    }
    public void insert(int value) {
        if (isFull())
            throw new IllegalStateException("Heap is Full");
        this.items[size++] = value;
        bubbleUp();
    }

    public int remove() {
        if (size == 0)
            throw new IllegalStateException("Heap is Empty");
        int root = items[0];
        items[0] = items[--size];

        bubbleDown();
        return root;
    }

    private void bubbleDown() {
        int index = 0;
        while (index <= size && !isValidParent(index)) {
            var largerChildIndex = getLargerChildIndex(index);
            swap(items, index, largerChildIndex);
            index = largerChildIndex;
        }
    }

    private int getLargerChildIndex(int index) {
        if (!hasLeftChild(index))
            return index;

        if (!hasRightChild(index))
            return leftChildIndex(index);

        return (leftChild(index) > rightChild(index)) ?
            leftChildIndex(index) : rightChildIndex(index);
    }
    private boolean hasLeftChild(int index) {
        return leftChildIndex(index) < size;
    }

    private boolean hasRightChild(int index) {
        return rightChildIndex(index) < size;
    }
    private boolean isValidParent(int index) {
        if (!hasLeftChild(index))
            return true;

        var isValid = items[index] >= leftChild(index);

        if (hasRightChild(index))
            isValid &= items[index] >= rightChild(index);

        return isValid;
    }
    private int rightChild(int index) {
        return items[rightChildIndex(index)];
    }
    private int leftChild(int index) {
        return items[leftChildIndex(index)];
    }
    private int leftChildIndex(int index) {
        return (index * 2) + 1;
    }
    private int rightChildIndex(int index) {
        return (index * 2) + 2;
    }
    private boolean isFull() {
        return size == items.length;
    }

    private void bubbleUp() {
        var index = size - 1;
        while (index > 0 && (items[parent(index)] < items[index])) {
                swap(items, parent(index), index);
                index = parent(index);
        }
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }
    private static void swap (int[] items, int from, int to) {
        int temp = items[from];
        items[from] = items[to];
        items[to] = temp;
     }

    public static void main(String[] args) {
        ScratchPad heap = new ScratchPad(10);
        heap.insert(10);
        heap.insert(5);
        heap.insert(17);
        heap.insert(4);
        heap.insert(22);
        System.out.println("Done");
        int[] array = new int[] {10, 2, 4, 22, 5, 12};
        System.out.println(Arrays.toString(array));
        heapify(array);
        //MaxHeap.heapify(array);
        //Main.myHeapify(array);
        System.out.println(Arrays.toString(array));
    }
    public static void heapify(int[] array) {
        if (array == null || array.length == 0)
            return;
        for (int idx = (array.length / 2) - 1; idx >= 0; idx--) {
            //bubbleDownArrayIterative(array, idx);
            bubbleDownRecursive(array, idx);
        }
    }
    public static void bubbleDownRecursive(int[] array, int index) {
        int maxIdx = index;
        int leftIdx = (index * 2) + 1;
        int rightIdx = (index * 2) + 2;
        if (leftIdx < array.length && array[leftIdx] > array[maxIdx])
            maxIdx = leftIdx;
        if (rightIdx < array.length && array[rightIdx] > array[maxIdx])
            maxIdx = rightIdx;
        if (index == maxIdx)
            return;
        swap(array,index, maxIdx);
        bubbleDownRecursive(array, maxIdx);
    }
    public static void bubbleDownArrayIterative(int[] array, int idx) {
        int maxLen = array.length;
        while (idx < maxLen) {
            int leftChildIdx = 2 * idx + 1;
            int rightChildIdx = 2 * idx + 2;
            boolean hasLeftChild = leftChildIdx < maxLen;
            boolean hasRightChild = rightChildIdx < maxLen;
            if (!hasLeftChild)
                return;

            if (!hasRightChild) {
                if (array[idx] < array[leftChildIdx]) {
                    swap(array, idx, leftChildIdx);
                }
                return;
            }

            int bigChildIdx = array[leftChildIdx] > array[rightChildIdx] ? leftChildIdx : rightChildIdx;

            if (array[idx] > array[bigChildIdx])
                return;

            swap(array, idx, bigChildIdx);
            idx = bigChildIdx;
        }
    }
}
