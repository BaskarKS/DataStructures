package com.baskarks.sorting;

/*
* comparison based sorting algorithms
*  BubbleSort
*  SelectionSort
*  InsertionSort
*  MergeSort
*  QuickSort
*
* Non Comparison Based Sorting Algorithms
*  RadixSort
*  CountingSort
*  Bucket Sort
*
* List of Integers in range of 0 - K
* 5, 3, 2, 5, 4, 4, 5
* K is 5
* need to figure out, how many times each item occur in array
* represent the count / frequencies in another array
* 0, 1, 2, 3, 4, 5 <= array index
* 0, 0, 0, 0, 0, 0 <= array frequencies
*
* iterate over input array and update array frequencies
* in counts array
* 0, 1, 2, 3, 4, 5 <= array index
* 0, 0, 1, 1, 2, 3 <= array frequencies
*
* using counts array, update the input array
* 2, 3, 4, 4, 5, 5, 5
*
* Instead of comparisons, we count the occurrences in input array
* using that we sort the data
*
* Time Complexity:
*                  space complexity - dependent on the maximum value
* in the input array. If k is the maximum value - counting
* array needs O(k) space
*
*                    populate counts O(n)
*  iterate counts array and refill input array O(k)
*                         Total O(n + k) => O(n)
*
*  WHen to Use:
*    1. when allocating extra space is not an issue.
*    2. values are positive integers. (if negative cant use as index)
*    3. most of the values in range are present in input array.
* */
public class CountingSort {
    public void sort(int[] array, int k) {
        int[] counts = new int[k + 1];
        for (int each : array)
            counts[each]++;
        int arrayIdx = 0;
        for (int index = 0; index < counts.length; index++) {
            var freq = counts[index];
            while (freq-- > 0)
                array[arrayIdx++] = index;
        }
    }

    public void sortMosh(int[] array, int max) {
        int[] counts = new int[max + 1];
        for (int item : array)
            counts[item]++;

        int k = 0;
        for (var i = 0; i < counts.length; i++) {
            for (var j = 0; j < counts[i]; j++) {
                array[k++] = i;
            }
        }

    }
}
