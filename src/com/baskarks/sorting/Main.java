package com.baskarks.sorting;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] array = {1, 6, 3, 10, 34, 1, 3, 11, 0};

        //var bubbleSort = new BubbleSort();
        System.out.println(Arrays.toString(array));
        //bubbleSort.sort(array);

        //var selectionSort = new SelectionSort();
        //selectionSort.moshSort(array);

        //var insertionSort = new InsertionSort();
        //insertionSort.sort(array);

        var mergeSort = new MergeSort();
        mergeSort.mergeSort(array);
        System.out.println(Arrays.toString(array));


    }
}
