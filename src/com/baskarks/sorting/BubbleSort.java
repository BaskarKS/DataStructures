package com.baskarks.sorting;

public class BubbleSort {
    /*
    *   Scan array from left to right
    *   if values out of order we swap them
    *   compare index 0, 1 -> if out of order we swap it
    *   compare index 1, 2 -> if out of order we swap it
    *   we go till end of array
    *
    * we need multiple passes to sort this array
    *
    *   1 pass completed , for every pass the largest item will
    * move to its correct position
    *
    *   for every pass the next largest item will bubble up
    *
    *   Time Complexity - n is number of items
    *                          Best               Worst
    *  (BestCase)     passes   O(1)                O(n)
    *            comparisons   O(n)                O(n)
    *                  Total   O(n) Linear       O(n^2)  Quadratic
    * */
    public void sort(int[] array) {
        for (int i = array.length - 1; i > 0 ; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j+1])
                    swap(array, j, j + 1);
            }
        }
    }

    public void moshSort(int[] array) {
        boolean isSorted;
        for (int i = 0; i < array.length; i++) {
            isSorted = true;
            for (int j = 1; j < array.length - i; j++)
                if (array[j - 1] > array[j]) {
                    swap(array, j, j - 1);
                    isSorted = false;
                }
            if (isSorted)
                return;
        }
    }
    private void swap(int[] array, int locOne, int locTwo) {
        var temp = array[locOne];
        array[locOne] = array[locTwo];
        array[locTwo] = temp;
    }
}
