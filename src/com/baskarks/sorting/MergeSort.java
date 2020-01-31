package com.baskarks.sorting;
/*
* Merge Sort : to break down a list to smaller and smaller sub Lists
* sort those and merge them back to build a completely sorted list
*
* Recursively divide an array, until we cannot divide it anymore (
* element of array should be 1)
*
* length of Array / 2 => will get the middle index.
*
* Array with single item is already sorted.
* 8, 2, 4, 1, 3 => 5/2 => 2
* 8, 2 (2 / 2 => 1)    4, 1, 3 (3 / 2 => 1)
* 8    2               4    1, 3 ( 2 / 2 => 1)
* 8    2               4       1     3 => all items are divided
* start merging the divided array
* 2, 8 (while merging, sort and merge  4    (1, 3)
* 2, 8                                 1,3,4
* 1,2,3,4,8 => all divided arrays ar merged to form complete array
*
* Divide and Conquer algorithm => divides a problem into sub problems
* until its easy enough to solve and then combines the solutions
* to build the solution to the original problem
*
* Time Complexity:
*                                 Best            Worst
*            Dividing           O(log n)         O(log n)
*            Merging            O(n)             O(n)
* merging involves reading each item to compare and solve
*            Total              O(n log n)       O(n log n)
*
* Its cost is space
*           Space Complexity    O(n)             O(n)
*
* */
public class MergeSort {
    public void sort(int[] array) {
        if (array.length < 2)
            return;
        //divide this array into half
        var middle = array.length / 2;

        int[] left = new int[middle];
        for (var i = 0; i < middle; i++)
            left[i] = array[i];

        int[] right = new int[array.length - middle];
        for (var i = middle; i < array.length; i++)
            right[i - middle] = array[i];

        //sort each half
        sort(left);
        sort(right);
        //merge the result
        mergeArrays(left, right, array);
    }
    private void mergeArrays(int[] left, int[] right, int[] result) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] < right[j])
                result[k++] = left[i++];
            else
                result[k++] = right[j++];
        }
        while (i < left.length)
            result[k++] = left[i++];
        while (j < right.length)
            result[k++] = right[j++];
    }

    public void mergeSort(int[] array) {
        mergeSort(array, 0, array.length);
    }

    private void mergeSort(int[] array, int start, int end) {
        int mid = (start + end) / 2;
        if (start == mid)
            return;
        mergeSort(array, start, mid);
        mergeSort(array, mid, end);
        merge(array, start, mid, end);
    }
    public void merge(int[] array, int start, int mid, int end) {
        int[] temp = new int[end - start];
        int i = start, j = mid, tempIdx = 0;
        while(i < mid && j < end) {
            if (array[i] <= array[j])
                temp[tempIdx++] = array[i++];
            else
                temp[tempIdx++] = array[j++];
        }
        while (i < mid)
            temp[tempIdx++] = array[i++];
        while (j < end)
            temp[tempIdx++] = array[j++];
        tempIdx = 0;
        while (tempIdx < temp.length)
            array[start + tempIdx] = temp[tempIdx++];
    }
}
