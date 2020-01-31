package com.baskarks.nonlinear.heaps;

public class MaxHeap {
    public static void heapify(int[] array) {
        // index of last parent is (n/2 - 1)
        var lastParentIndex = array.length / 2 - 1;
        //instead of going from top to last parent
        //heapify from last parent to top of the tree
        // will have less recursions and more optimisation
        for (int i = lastParentIndex; i >= 0 ; i--) {
            heapify(array, i);
        }
    }
    private static void heapify(int[] array, int index) {
        var largerIndex = index;
        var leftIdx = index * 2 + 1;
        if (leftIdx < array.length &&
                        array[leftIdx] > array[largerIndex])
            largerIndex = leftIdx;
        var rightIdx = index * 2 + 2;
        if (rightIdx < array.length &&
                array[rightIdx] > array[largerIndex])
            largerIndex = rightIdx;
        if (index == largerIndex)
            return;
        swap(array, index, largerIndex);
        heapify(array, largerIndex);
    }
    private static void swap(int[] array, int first, int second) {
        int temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

    public static int getKthLargest(int[] array, int k) {
        if (k < 1 || k > array.length)
            throw new IllegalArgumentException("not valid K value");
        var heap = new Heap(array.length);
        for (int element : array)
            heap.insert(element);
        for (int i = 0; i < k - 1; i++)
            heap.remove();
        return heap.max();
    }

    public static boolean isMaxHeap(int[] array) {
        for (int i = 0; i < array.length / 2 - 1; i++) {
            if (leftChildLarger(array, i) ||
                    rightChildLarger(array, i))
               return false;
        }
        return true;
    }
    private static boolean leftChildLarger(int[] array, int index) {
        int leftIdx = index * 2 + 1;
        boolean isLeftValid = leftIdx < array.length;

        return isLeftValid && array[index] < array[leftIdx];
    }
    private static boolean rightChildLarger(int[] array, int index) {
        int rightIdx = index * 2 + 2;
        boolean isRightValid = rightIdx < array.length;

        return isRightValid && array[index] < array[rightIdx];
    }
}
