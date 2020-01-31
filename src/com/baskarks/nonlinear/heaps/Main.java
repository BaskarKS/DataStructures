package com.baskarks.nonlinear.heaps;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        var heap = new Heap(15);
        heap.insert(10);
        heap.insert(5);
        heap.insert(17);
        heap.insert(4);
        heap.insert(22);
        System.out.println(heap);
        heap.remove();
        System.out.println(heap);

        int[] numbers = {5, 3, 10, 1, 4, 2};
        heapSort(numbers);
        checkPriorityQueue(numbers);
        System.out.println();
        // Heapify
        //Transforming values in an array into heap (In Place)
        int[] heapify = {5, 3, 8, 4, 1, 2};
        //myHeapify(heapify);
        System.out.println("is Max Heap : " + MaxHeap.isMaxHeap(heapify));
        MaxHeap.heapify(heapify);
        System.out.println(Arrays.toString(heapify));
        System.out.println("is Max Heap : " + MaxHeap.isMaxHeap(heapify));
        int k = 2;
        System.out.println(MaxHeap.getKthLargest(heapify, k));

        //Min Heap Implementation Checking
        var minHeap = new MinHeap(10);
        minHeap.insert(30, "Baskar");
        minHeap.insert(45, "Jaashi");
        minHeap.insert(20, "Geetha");
        minHeap.insert(10, "Ramani");
        minHeap.insert(5, "Babu");
        System.out.println(minHeap);
        minHeap.remove();
        minHeap.remove();
        System.out.println(minHeap);

        //Check Min Priority Queue
        MinPriorityQueue priorityQueue = new MinPriorityQueue(10);
        priorityQueue.add("Bath", 0);
        priorityQueue.add("Yoga", 1);
        priorityQueue.add("eat", 2);
        System.out.println(priorityQueue.remove());
        System.out.println(priorityQueue.isEmpty());
        System.out.println(priorityQueue.remove());
        System.out.println(priorityQueue.remove());
        System.out.println(priorityQueue.isEmpty());


    }

    public static void myHeapify(int[] heapify) {
        System.out.println(Arrays.toString(heapify));
        for (int i = 0; i < heapify.length; i++) {
            int leftIdx = i * 2 + 1;
            int rightIdx = i * 2 + 2;
            int maxIdx = i;
            boolean leftValid = leftIdx < heapify.length;
            boolean rightValid = rightIdx < heapify.length;
            if (leftValid && heapify[i] > heapify[leftIdx] &&
                    rightValid && heapify[i] > heapify[rightIdx])
                continue;
            if (leftValid && rightValid)
                maxIdx = heapify[leftIdx] > heapify[rightIdx] ?
                        leftIdx : rightIdx;
            if (!rightValid && leftValid &&
                    heapify[i] < heapify[leftIdx])
                maxIdx = leftIdx;

            int temp = heapify[i];
            heapify[i] = heapify[maxIdx];
            heapify[maxIdx] = temp;
        }
        System.out.println(Arrays.toString(heapify));
    }
    public static void checkPriorityQueue(int[] numbers) {
        var priorityQueue = new PriorityQueue();
        for (int number : numbers)
            priorityQueue.enqueue(number);
        while (!priorityQueue.isEmpty())
            System.out.print(priorityQueue.dequeue() + "=>");
    }

    public static void heapSort(int[] numbers) {
        var heapSort = new Heap(10);

        for (var number : numbers)
            heapSort.insert(number);

        //Sort in Descending order
        for (var i = 0; i < numbers.length; i++)
            numbers[i] = heapSort.remove();
/*
        //Sort in Ascending order
        for (var i = numbers.length - 1; i >= 0; i--)
            numbers[i] = heapSort.remove();*/
    }
}
