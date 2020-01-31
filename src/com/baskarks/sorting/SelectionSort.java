package com.baskarks.sorting;

/*
* Like Bubble sort we need multiple passes to sort a Array
*
* in each pass we need to find the next smallest item and move it
* to its correct position.
*
* Pass 1:
* 8, 2, 4, 1, 3 in first pass we can figure the item 1 is smallest
* at index 3. we need to swap it with item at index 0.
* 1, 2, 4, 8, 3
*
* Now the array has sorted part(index 0) and unsorted part
* (index 1 to 4)
*
* pass 2:
* now find th minimum value in unsorted part and swap it with
* item at index 1.
* 1, 2, 4, 8, 3 sorted part(index 0, 1) unsorted part(2, 4)
*
* pass 3:
* now find minimum value in unsorted part which is 3 at index 4
* swap it with index 2
* 1, 2, 3, 8, 4 sorted part (0-2) unsorted part (3-4)
*
* pass 4:
* find minimum value in unsorted part which is 4 at index 4
* swap it with index at 3
* 1, 2, 3, 4, 8 sorted part (0 - 3) unsorted part(4)
*
* we call this as selection sort : in each pass we find / select
* next minimum value and move it to the right place
*
* Time Complexity:
* we need n passes for both best case and worst case scenarios
*                                     Best              Worst
*                        passes       O(n)               O(n)
*                     comparisons     O(n)               O(n)
*                           Total     O(n^2)             O(n^2)
*                                    Quadratic
*
* comparisons : we iterate the unsorted part of array to find the
* minimum value, even unsorted part shrinks in every pass we still
* consider it as O(n) operation
* */

public class SelectionSort {
    public void sort(int[] array) {
        if (array == null) return;
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            for (int j = i; j < array.length; j++) {
                if (array[j] < array[min])
                    min = j;
            }
            swap(array, i, min);
        }
    }

    private void swap(int[] array, int posOne, int posTwo) {
        var temp = array[posOne];
        array[posOne] = array[posTwo];
        array[posTwo] = temp;
    }

    public void moshSort(int[] array) {
        for (var i = 0; i < array.length; i++) {
            var minIdx = getMinIdx(array, i);
            swap(array, minIdx, i);
        }
    }
    private int getMinIdx(int[] array, int index) {
        var minIdx = index;
        for (var j = index; j < array.length; j++)
            if (array[j] < array[minIdx])
                minIdx = j;
        return minIdx;
    }
}
