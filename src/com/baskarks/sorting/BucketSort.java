package com.baskarks.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
* Distribute all items into number of buckets
* sort this buckets using another sorting algorithm
* and combine these buckets
*
* Instead of sorting entire large array, we sort distributed
* smaller buckets which will take less time
*
* can do sorting of buckets in parallel
*
* 6, 2, 5, 4, 3, 7
*
* distribute the above items into 3 buckets (number of buckets
* has no particular reason, can choose any number of buckets)
* Increasing number of buckets will affect performance.
*
* elements in the buckets should be stored in linked list
* because it can have duplicate items
*
* parse each element of input array, and find to push it
* in respective bucket.
*
* bucket = item / number of Buckets
* number of buckets => 3
* 6 / 3 => 2
* 2 / 3 => 0
* 5 / 3 => 1
* 4 / 3 => 1
* 3 / 3 => 1
* 7 / 3 => 2
*
* 0 -> 2
* 1 -> 5, 4, 3
* 2 -> 6 , 7
*
* sort items in buckets using another algorithm
* 0 -> 2
* 1 -> 3, 4, 5
* 2 -> 6, 7
*
* combine items in bucket
* read from bucket 0, 1, 2 and put in input array
* 2, 3, 4, 5, 6, 7
*
* the more buckets we need, make to have more memory
*
* Space Complexity:
* assuming we have k buckets, we need to allocate an array
* of k items, each item in this array will be linked list. the
* total number of items in across all the linked list is
* equal to number of items in input array n
* Space complexity is O(n + k)
*
* Time Complexity
*                                  best            worst
* distribute item in k buckets     O(n)            O(n)
* iterating over bucket is         O(k)            O(k)
* sorting each bucket              O(1)           O(n ^ 2)
*                Total            O(n + k)        O(n ^ 2)
*                               if k is n,
*                             then, total is
*                              O(2n) => O(n)
*
* time complexity reduces if we allocate more space by allocating
* more buckets, and increases if buckets are less.
* Its a Time, memory trade off
*
*  */
public class BucketSort {
    public void sortMosh(int[] array) {
        var numberOfBuckets =getNoOfBuckets(array);
        int inputIdx = 0;
        for (List<Integer> bucket : createBuckets(array, numberOfBuckets)) {
            Collections.sort(bucket);
            for (int item : bucket)
                array[inputIdx++] = item;
        }

    }
    private List<List<Integer>> createBuckets(int[] array, int numberOfBuckets) {
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < numberOfBuckets; i++)
            buckets.add(new ArrayList<>());

        for (int item : array)
            buckets.get(item / numberOfBuckets).add(item);
        return buckets;
    }
    public void sort(int[] array) {
        int noOfBuckets = getNoOfBuckets(array);
        List<Integer>[] buckets = new ArrayList[noOfBuckets];
        for (int i = 0; i < noOfBuckets; i++)
            buckets[i] = new ArrayList<>();

        for (int each : array) {
            var whichBucket = (each / noOfBuckets);
            buckets[whichBucket].add(each);
        }

        int inputIdx = 0;
        for (List<Integer> each : buckets) {
            Collections.sort(each);
            for (int item : each)
                array[inputIdx++] = item;
        }
    }
    public int getNoOfBuckets(int[] array) {
        if (array == null || array.length == 0)
            return -1;
        int max = array[0];
        for (int idx = 1; idx < array.length; idx++)
            if (array[idx] > max)
                max = array[idx];
        return (int) Math.ceil(Math.sqrt(max));
    }
}
