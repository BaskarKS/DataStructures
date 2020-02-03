package com.baskarks.algorithm.search;

/*
* Its an improvement to linear search, but not as fast as
* binary search
*
* with Jump search we divide a list into few blocks, instead
* of checking every item we jump to the block where the target
* item may exist
*
* then we do a linear search only in that block
*
*
* works only on sorted array, like binary search
* 0, 1, 2, 3,  4,  5,  6,  7,  8,  9
* 3, 5, 6, 9, 11, 18, 20, 21, 24, 30
*          ^           ^
*          |           |
*         mid1        mid2
*
* calculate the size of block for n items
* block size = sqrt(n)
* block size = sqrt(10) => 3
* block 1 => 3, 5, 6
* block 2 => 9, 11, 18
* block 3 => 20, 21, 24
* block 4 => 30
*
* target item = 23
* start from first block
* check last item of first block with target value
* look for last item in second block with target value
* look for last item in third block (24) with target (23)
* hence the target should be in 3rd block
* do linear search in 3rd block and target is not there, we
* return -1.
*
* time Complexity is : sqrt(n)
*
* we will maintain 2 pointers to implement this algorithm
* 1st pointer will point to beginning of the block
* 2nd pointer will point to beginning of next block
* if the last item in block is smaller than target, which
* means the target is not in that block, we advance both the
* pointers.
*
* 2 edge cases
* 1st . start / first pointer should be less than the array length
* 2nd . if the next pointer length crosses length of array, its
* set to the length of array
*
* */

public class JumpSearch {
    public int jumpSearch(int[] array, int target) {
        int blockSize = (int) Math.floor(Math.sqrt(array.length));
        var start = 0;
        var next = start + blockSize;
        while (start < array.length &&
                    start + blockSize < array.length &&
                    target > array[start + blockSize]) {
            start = (next < array.length) ? next : array.length - 1;
            next = (next + blockSize) < array.length ?
                    next + blockSize : array.length - 1;
        }
        while (start <= next) {
            if (array[start] == target)
                return start;
            start++;
        }
        return -1;
    }

    public int jumpSearchMosh(int[] array, int target) {
        var blockSize = (int) Math.sqrt(array.length);
        var start = 0;
        var next = blockSize;

        while (start < array.length &&
                array[next - 1] < target) {
            start = next;
            next += blockSize;
            if (next > array.length)
                next = array.length;
        }
        for (var i = start; i < next; i++)
            if (array[i] == target)
                return i;

        return -1;
    }
}
