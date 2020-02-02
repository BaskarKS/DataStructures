package com.baskarks.algorithm.search;
/*
* we iterate over the list, inspect each item. If we find it we
* return its index, otherwise we return -1 indicates the item
* not present in the list.
*
* Time Complexity
*                                    Best               Worst
*  Target is present    (first item) O(1)   (Last item)  O(n)
*  target is not present             O(n)                O(n)
*
* its relatively slow compared to other searching algorithm
* best if list is small.
*
* */
public class LinearSearch {
    public int linearSearch(int[] array, int target) {
        for (var i = 0; i < array.length; i++)
            if (array[i] == target)
                return i;
        return -1;
    }
}
