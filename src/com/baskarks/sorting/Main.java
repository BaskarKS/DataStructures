package com.baskarks.sorting;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] array = {1, 6, 3, 10, 34, 1, 3, 11, 0};
        //int[] array = {7, 3, 1, 5 , 2};

        //var bubbleSort = new BubbleSort();
        //System.out.println(Arrays.toString(array));
        //bubbleSort.sort(array);

        //var selectionSort = new SelectionSort();
        //selectionSort.moshSort(array);

        //var insertionSort = new InsertionSort();
        //insertionSort.sort(array);

        //var mergeSort = new MergeSort();
        //mergeSort.mergeSort(array);

        //var quickSort = new QuickSort();
        //quickSort.sort(array);
        //System.out.println(Arrays.toString(array));

        System.out.println("Counting Sort:");
        int[] countArray = {5, 3, 2, 5, 4, 4, 5};
        System.out.println(Arrays.toString(countArray));
        var countSort = new CountingSort();
        countSort.sortMosh(countArray, 5);
        System.out.println(Arrays.toString(countArray));

        System.out.println("Bucket Sort:");
        System.out.println(Arrays.toString(array));
        var bucketSort = new BucketSort();
        bucketSort.sort(array);
        System.out.println(Arrays.toString(array));



    }
}
