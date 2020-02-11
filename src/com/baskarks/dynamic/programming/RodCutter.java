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

    /*
    * If suppose you have found the maximum value that we could
    * get by cutting the rod and selling it, how many different
    * combinations of rod pieces are there in which we could get
    * that maximum value? Order of the rods matter. You can write
    * either pseudocode or just write your approach in brief.

    For example:-

    We are given a rod of length 5 inches and the cost array is:-
    cost = {0 , 2, 4, 6, 8, 9 }
    Maximum value = 10.
    Number of ways to make 10 units of money = 15 which are:-
    1. {1, 4} unit rods having 2 combinations
    2. {2, 2, 1} unit rods having 3 combinations
    3. {2, 3} unit rods having 2 combinations
    4. {2, 1, 1, 1} unit rods having 4 combinations
    5. {3, 1, 1} unit rods having 3 combinations
    6. {1, 1, 1, 1, 1} unit rods having 1 combination
    *
    * */
    public static int getMaxRodCutPossibility(int rodLength, int[] cost) {
        int[] rod = new int[rodLength + 1];
        rod[0] = 0;

        int[] possibility = new int[rodLength + 1];
        possibility[0] = 1;

        for (int rodLen = 1; rodLen <= rodLength; rodLen++) {
            int max = Integer.MIN_VALUE;
            for (int pieceCost = 1; pieceCost <= rodLen; pieceCost++) {

                if (max < (cost[pieceCost] + rod[rodLen - pieceCost])) {
                    possibility[rodLen] = possibility[rodLen - pieceCost];
                    max = cost[pieceCost] + rod[rodLen - pieceCost];
                } else if (max == cost[pieceCost] + rod[rodLen - pieceCost]) {
                    possibility[rodLen] = possibility[rodLen] +
                            possibility[rodLen - pieceCost];
                }
            }
            rod[rodLen] = max;
        }
        return possibility[rodLength];
    }
}
