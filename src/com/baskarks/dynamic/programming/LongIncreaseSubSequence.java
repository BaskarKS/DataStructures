package com.baskarks.dynamic.programming;

/*
* you are being given an array nums and you need to find the
* longest forming increasing subsequence from it
* Longest increasing subsequence is the longest sub-sequence
* such that all elements of the sub-sequence are in
* increasing order.
*  nums : 11, 23, 10, 37, 21, 50, 80
*  LIS  :  1,  2,   ,  3,   ,  4,  5
*
*  10, 21, 50, 80 is also a sub-seq but its length is 4
*
* */
public class LongIncreaseSubSequence {
    public static int LISubSequence(int[] array) {
        int[] lis = new int[array.length];
        for (int i = 0; i < array.length; i++)
            lis[i] = 1;
        for (int outer = 1; outer < array.length; outer++)
            for (int inner = 0; inner < outer; inner++)
                if (array[outer] > array[inner] &&
                        lis[outer] < lis[inner] + 1) {
                    lis[outer] = lis[inner] + 1;
                }
        int max = Integer.MIN_VALUE;
        for (int each : lis)
            if (max < each)
                max = each;
        return max;
    }
}
