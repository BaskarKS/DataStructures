package com.baskarks.sorting;

/*
* Insertion Sort;
* 0, 1, 2, 3, 4
* 8, 2, 4, 1, 3
* same like card game, we get one item at a time, we insert it at
* the right position
* 8             <= 8
* 2, 8          <= 2
* 2, 4, 8       <= 4
* 1, 2, 4, 8    <= 1
* 1, 2, 3, 4, 8 <= 3
*
* in the given array, move from left to right for every item
*
*  we encounter 8 as first element we dont do anything because
* we assume 8 is in its correct position
*
*  we encounter 2 as second element, we store the 2 in temp location
* and move 8 to its right to make space to place 2  to
* insert it at 0th location.
*
* 2, 8 is sorted part of array and remaining elements are in unsorted
* part of array, we continue to pick a element from unsorted part
* of the array and insert it at the correct position in sorted part
*
* 2, 8, 4, 1, 3
*
* from unsorted part 4, 1, 3 . we pick item 4 and try to insert
* at right position in sorted part 2, 8
* temp= 4
* 2, 8, 8, 1, 3 => moved 8 to right to make space for 4
* no need to move 2 towards right. Hence 4 is inserted at position 1
* 2, 4, 8, 1, 3 => sorted part index 0-2 and unsorted part index 3-4
*
* Time Complexity
*                                   Best          worst
*(read each item
* from unsorted part)    iteration   O(n)          O(n)
*                     shift items    O(1)          O(n)
*                        Total       O(n)          O(n^2)
*                                 Linear         Quadratic
*                       same as Bubble sort
* */
public class InsertionSort {
    public void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int itemToInsert = array[i];
            int insertIndex = i;
            for (; insertIndex > 0; insertIndex--) {
                if (array[insertIndex - 1] < itemToInsert)
                    break;
                array[insertIndex] = array[insertIndex - 1];
            }
            array[insertIndex] = itemToInsert;
        }
    }

    public void moshSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            var current = array[i];
            var j = i - 1;
            while (j >= 0 && array[j] > current) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = current;
        }
    }
}
