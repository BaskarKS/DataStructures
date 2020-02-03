package com.baskarks.algorithm.search;

/*
* Binary Search is faster than Linear search
* It works only in sorted list.
*
* If our input list is not sorted,
* Sort it first and then use binary search to look for target
*
* 0, 1, 2, 3,  4,  5,  6,  7,  8,  9   => Length - 10
* 3, 5, 6, 9, 11, 18, 20, 21, 24, 30
*
* lest look for item 6 in the list

* first look at the middle item by calculating the middle
* index by middle = (left + right) / 2 => (0 + 9) / 2 => 4
*
* compare value at array[4] if the target is smaller traverse
* left else traverse right half
*  11 > 6, hence traverse left half (0 - 3)
*
* middle = (0 + 3) / 2 => 1
*
* at every step we divide the problem into half, its again an
* example of divide and conquer algorithm, divide a problem into
* smaller problem and find solution, combine the solution to
* find the solution to the actual problem
*
* array[1] => 5 < 6 , hence look into the right half [2-3]
*
* middle = 2 + 3 / 2 => 2
*
* array[2] => 6 == target(6)
*
* we found the target in 3 comparisons, even there are 10 items
* in the list
*
* Complexity Analysis
*              For all scenarios, Time Complexity : O(log n)
*
* Space Complexity :
* if implemented in recursion its O(log n) because of call stack
* if implemented in iteration its O(1)
*
* */
public class BinarySearch {
    public int binarySearchRecursive(int[] array, int target) {
        if (array == null || array.length == 0)
            return -1;
        return binarySearchRecursive(array, target, 0, array.length - 1);
    }
    private int binarySearchRecursive(int[] array, int target, int left, int right)
    {
        if (left > right)
            return -1;

        int midIdx = (left + right) / 2;

        if (array[midIdx] == target)
            return midIdx;

        return (target < array[midIdx]) ?
                binarySearchRecursive(array, target, left, midIdx - 1):
                binarySearchRecursive(array, target, midIdx + 1, right);
    }

    public int binarySearchIterative(int[] array, int target) {
        if (array == null || array.length == 0)
            return -1;

        int left = 0;
        int right = array.length - 1;
        while(left <= right) {
            int mid = (left + right) / 2;
            if (array[mid] == target)
                return mid;
            if (target < array[mid])
                right = mid - 1;
            else
                left = mid + 1;
        }
        return -1;
    }

    private int binarySearchRecMosh(int[] array, int target) {
        return binarySearchRecMosh(array, target, 0, array.length - 1);
    }

    private int binarySearchRecMosh(int[] array, int target,
                                   int left, int right) {

        if (left > right)
            return -1;

        int middle = (left + right) / 2;

        if (array[middle] == target)
            return middle;

        if ( target < array[middle])
            return binarySearchRecMosh(array, target, left, middle - 1);

        return binarySearchRecMosh(array, target, middle + 1, right);
    }

    public int binarySearchIterativeMosh(int[] array, int target) {
        var left = 0;
        var right = array.length - 1;
        while (left <= right) {
            var middle = (left + right) / 2;

            if (array[middle] == target)
                return middle;

            if (target < array[middle])
                right = middle - 1;
            else
                left = middle + 1;
        }
        return -1;
    }
}
