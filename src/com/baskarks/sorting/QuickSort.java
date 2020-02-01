package com.baskarks.sorting;

public class QuickSort {
    /*
    * Its the most used algorithm, its the most used in all
    * languages and frameworks
    * Its fairly efficient algorithm, doesn't need extra space
    * like merge sort. Can sort in in-place
    *
    * 15, 6, 3, 1, 22, 10, 13
    * we start by selecting an item called pivot, we rearrange
    * all the item less than pivot to its left and larger item
    * to its right. this process is called partitioning
    * we divide the array into two partitions that are seperated
    * by pivot
    *
    * typically we select the last item as pivot
    * there are variations that we select first / last / random
    * item as pivot
    *
    * we select last item as pivot 13
    * rearrange process or moving other items to left and right
    * to pivot
    * 6, 3, 1, 10, 13, 15, 22
    * 6, 3, 1, 10 - left partition (less than pivot)
    * 15, 22 - right partition (larger than pivot)
    * 13 is at its right position
    *
    *  when we pick a pivot and partition the array, the pivot
    * will move to its correct position
    *
    * repeat-perform the same steps to left and right partitions
    * in left partition we pick last item 10 as pivot
    * reorder the other items
    * 6, 3, 1, 10, 13, 15, 22
    * left partition 6, 3, 1
    * right partition 15, 22
    * we repeat, select 1 in left partition as pivot
    * reorder the items
    * as we select a pivot and reorder the items, we bother about
    * only the items in left and place pivot at desired position,
    * here we don't consider about items on right partition
    * this is the reason quick sort have logarithmic complexity
    * because in every step we narrow down our size of partition
    * by breaking them into half
    * 1, 6, 3, 10, 13, 15, 22
    * left partition 6, 3
    * right partition  15, 22
    * pivot is 3
    * reordering the other items 6
    * 1, 3, 6, 10, 13, 15, 22
    * left partition is 3
    * right partition is 15, 22
    * partition with single item is considered as sorted
    *
    * on right partition we consider 22 as pivot and rearrange the items
    * item in partition is 15
    * 1, 3, 6, 10, 13, 15, 22
    * only partition left and with single item is 15
    * item with single item is considered as sorted.
    *
    * Partitioning:
    * 2 pointers needed, firstOne to iterate items in array
    * secondOne to mark the boundary where we are supposed to
    * place the pivot item, will be the end of the left partition
    *  b i                              PIVOT IS 13
    * -1 0   1  2  3  4   5   6
    *    15, 6, 3, 1, 22, 10, 13
    * initially b is -1 which indicates the left partition is empty
    *
    * move i pointer, if the item pointed by i is larger than pivot
    * we just move on, if the item is smaller than pivot we swap and
    * increment the b pointer
    *  b     i                          PIVOT IS 13
    * -1 0   1  2  3  4   5   6
    *    15, 6, 3, 1, 22, 10, 13
    * now i is smaller than pivot , hence we increment the b
    * and swap the element pointed by i
    *    b   i                           PIVOT IS 13
    *    0   1  2  3  4   5   6
    *    6, 15, 3, 1, 22, 10, 13
    *
    * increment the i pointer and check
    *    b      i                         PIVOT IS 13
    *    0   1  2  3  4   5   6
    *    6, 15, 3, 1, 22, 10, 13
    * item point by i is smaller than pivot, increment b
    * and swap the item at b and item at i
    *        b  i                         PIVOT IS 13
    *    0   1  2  3  4   5   6
    *    6,  3, 15, 1, 22, 10, 13
    * now increment i and check
    *        b     i                      PIVOT IS 13
    *    0   1  2  3  4   5   6
    *    6,  3, 15, 1, 22, 10, 13
    * item at i smaller than pivot, increment b and swap it
    *           b  i                      PIVOT IS 13
    *    0   1  2  3  4   5   6
    *    6,  3, 1, 15, 22, 10, 13
    * increment i and check
    *           b      i                    PIVOT IS 13
    *    0   1  2  3   4   5   6
    *    6,  3, 1, 15, 22, 10, 13
    * item at it 22 is greater than pivot, we move on
    * increment i and check
    *           b          i                PIVOT IS 13
    *    0   1  2  3   4   5   6
    *    6,  3, 1, 15, 22, 10, 13
    * item at i is smaller than pivot, increment b and swap
    *              b       i                PIVOT IS 13
    *    0   1  2  3   4   5   6
    *    6,  3, 1, 10, 22, 15, 13
    * increment i and we could figure out it reached pivot
    * increment b and swap so that pivot will move to its
    * correct position
    *                  b        i            PIVOT IS 13
    *    0   1  2  3   4   5   6
    *    6,  3, 1, 10, 13, 15, 22
    * at the end of iteration, b points to pivot
    *
    * Time Complexity
    *                              best        worst
    *             partitioning     O(n)         O(n)
    *      no of times partition  O(log n)      O(n)
    *                Total         O(n log n)   O(n ^ 2)
    * worst case: if our items are arranged in ascending order
    * and we pick last item as pivot, no of times partition
    * will be O(n)
    *
    * Pivot Selection
    * 1. pick randomly
    * 2. use the middle index
    * 3. average of first, middle and last item
    *
    * above pivot selection will reduce the worstness of worst
    * case scenario
    * Space Complexity:
    * sorting takes place in in-place of input array, considering
    * recursive calls into account.
    *                                  best        worst
    *                   space         O(log n)     O(n)
    *
    *
    * */

    public void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }
    public void quickSort(int[] array, int start, int end) {
        if (start >= end)
            return;

        var pivot = array[end];
        var idx = start;
        var swapIdx = idx - 1;
        while (idx <= end) { //have to go to last element
            if (array[idx] <= pivot) // even pivot should be swapped to its position
                swap(array, idx, ++swapIdx);
            idx++;
        }

        quickSort(array, start, swapIdx - 1);
        quickSort(array, swapIdx + 1, end);
    }

    private void swap(int[] array, int idxOne, int idxTwo) {
        var temp = array[idxOne];
        array[idxOne] = array[idxTwo];
        array[idxTwo] = temp;
    }

    public void sortMosh(int[] array) {
        sortMosh(array, 0, array.length - 1);
    }
    private void sortMosh(int[] array, int start, int end) {
        if (start >= end)
            return;
        //partition
        int boundary = partition(array, start, end);
        //sort left
        sortMosh(array, start, boundary - 1);
        //sort right
        sortMosh(array, boundary + 1, end);
    }
    //return index of pivot after moved to its position
    private int partition(int[] array, int start, int end) {
        var pivot = array[end];
        var boundary = start - 1;
        for (var i = start; i <= end; i++) {
            if (array[i] <= pivot)
                swap(array, i, ++boundary);
        }
        return boundary;
    }
}
