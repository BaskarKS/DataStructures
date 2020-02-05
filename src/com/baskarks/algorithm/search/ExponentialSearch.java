package com.baskarks.algorithm.search;

import java.util.function.ObjIntConsumer;

/*
*
* works only on sorted array, like binary search
* 0, 1, 2, 3,  4,  5,  6,  7,  8,  9
* 3, 5, 6, 9, 11, 18, 20, 21, 24, 30
*    ^
*    |
*   bound
*
* Ideal exponential search should be to start with a small range
* and see if the target range is in that range or not.
* If the target is not in that range, we increase the range by
* doubling the range at each step.
* once we find a range that the target item might exist, we
* do a binary search in that range
*
* we are looking for item - 19 in the above array
*
* "bound" represents the upper bound in the range, stores
* the index of last item in the range
*
* compare the last item in the range with target Value
* 5 < 19
* if 19 exist in array, it should be definitely after 5
* we increase the range by doubling the bound
*
* bound = 2
* compare last item in the range
* array[bound] => 6 < 19 => it should be definitely after 6
* increase the range => bound += bound => bound = 4
*
* bound = 4
* compare last item in range
* array[bound] => 11 < 19 => it should be definitely after 11
* increase the range => bound += bound => bound = 8
*
* bound = 8
* compare last item in range
* array[bound] => 24 > 19 => should be with in the range
* do binary search from last upper bound (4) to current bound
*  value(8).
*
* range[bound / 2, bound] => [4, 8]
*
* Time Complexity:
*                  O(log i) i is the bound value
*                  in above example i is (8 - 4) => 4 elements
*                  O(log 4) but for binarySearch O(log 10)
* i is the position of target value if it existed in array,
* here 19 should exist before position 8, other items after 8 is
* dropped.
*
*
*
*
* */
public class ExponentialSearch {
    public int exponentialSearch(int[] array, int target) {
        var binarySearch = new BinarySearch();
        int bound = 1;
        while (bound < array.length &&
                array[bound] < target)
            bound += bound;

        var left = bound / 2;
        var right = bound < array.length ? bound : array.length - 1;

        return binarySearch.binarySearchRecursive(array, target,
                left, right);
    }

    public int exponentialSearchMosh(int[] array, int target) {

        int bound = 1;
        while (bound < array.length &&
                array[bound] < target)
            bound *= 2;

        var left = bound / 2;
        var right = Math.min(bound, array.length - 1);
        return new BinarySearch().binarySearchRecMosh(array, target,
                left, right);
    }
}
