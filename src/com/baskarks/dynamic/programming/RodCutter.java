package com.baskarks.dynamic.programming;

/*
* You are given a rod of length 'n' and array of prices,
* This array contains prices of smaller pieces of that rod
* when sold. find the maximum value that could be earned
* by cutting up the rod and selling the pieces.
*
* 0,  1,  2,  3,  4,  5,  6,  7
* 0,  2,  4,  5,  7, 11, 13, 17
*
* */
public class RodCutter {
    public static int getMaxRodCutPrice(int rodLength, int[] cost) {
        int[] rod = new int[rodLength + 1];
        rod[0] = 0;

        for (int rodLen = 1; rodLen <= rodLength; rodLen++) {
            int max = Integer.MIN_VALUE;
            for (int pieceCost = 1; pieceCost <= rodLen; pieceCost++) {
                max = Math.max(max, cost[pieceCost] + rod[rodLen - pieceCost]);
            }
            rod[rodLen] = max;
        }
        return rod[rodLength];
    }
}
