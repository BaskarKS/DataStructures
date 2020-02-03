package com.baskarks.algorithm.search;

/*
* Instead of diving array into parts in binary search, we divide
* the array into three parts.
*
* works only on sorted array, like binary search
* 0, 1, 2, 3,  4,  5,  6,  7,  8,  9
* 3, 5, 6, 9, 11, 18, 20, 21, 24, 30
*          ^           ^
*          |           |
*         mid1        mid2
*
* we have 2 pointers here mid1, mid2
* partition = (right - left) / 3 => 9 / 3 => 3
* mid1 = left + partitionSize;
* mid2 = right - partitionSize;
*
* with few comparisons we can find the target in any of the
* one partition.
* if we couldn't find the index, we continue the same in one
* of the partition. same like binary search.
*
* TimeComplexity:
*                   O(log 3 n)
* Math : 2 ^ x = 8
*        2 ^ x = 2 ^ 3
*            x = 3 or log 2 (8) => 3
* log tells us to which power we have to raise 2 to get result 8
*
*             log 3 (8) = 1.89
*
* in binary search the time complexity is log to base 2
* in ternary search the time complexity is log to base 3
*
* hence is ternary search is faster than binary search ? NO
*
* in binary search at each step we need 3 comparisons
*                target == mid
*                target < mid
*                target > mid
* in ternary search at each step we need 5 comparisons
*                target > mid2
*                target == mid2
*                target < mid2 && target > mid1
*                target == mid1
*                target < mid1
* Hence in worst case scenario, the total comparisons to find
* a number is more in ternary search
*
* Hence binary search is faster than ternary search
*
* */
public class TernarySearch {
    public int ternarySearch(int[] array, int target){
        if (array == null || array.length == 0)
            return -1;
        return ternarySearch(array, target, 0, array.length - 1);
    }

    private int ternarySearch(int[] array, int target, int left,
                              int right) {
        if (left > right)
            return -1;
        int partitionSize = (right - left) / 3;
        int mid1 = left + partitionSize;
        int mid2 = right - partitionSize;

        if (array[mid2] == target)
            return mid2;
        if (array[mid1] == target)
            return mid1;
        if (target > array[mid2])
            return ternarySearch(array, target, mid2 + 1, right);
        else if (target < array[mid1])
            return ternarySearch(array, target, left, mid1 - 1);

        return ternarySearch(array, target, mid1 + 1, mid2 - 1);
    }

    public int ternarySearchMosh(int[] array, int target) {
        return ternarySearchMosh(array, target, 0, array.length - 1);
    }
        private int ternarySearchMosh(int[] array, int target, int left,
                              int right) {
        if (left > right)
            return -1;
        var partitionSize = (right - left) / 3;
        var mid1 = left + partitionSize;
        var mid2 = right - partitionSize;

        if (array[mid1] == target)
            return mid1;
        if (array[mid2] == target)
            return mid2;

        if (target < array[mid1])
            return ternarySearchMosh(array, target, left, mid1 -1);

        if (target > array[mid2])
            return ternarySearchMosh(array, target, mid2 + 1, right);

        return ternarySearchMosh(array, target, mid1 + 1, mid2 - 1);
    }
}
