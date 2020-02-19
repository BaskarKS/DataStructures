package com.baskarks.dynamic.programming;

/*
  PROBLEM STATEMENT:
* you are given a 2 * N grid, and infinite dominos of size
* each 1 * 2.
*
* Find the no of ways you can fill the grid using these
* dominos.
*
* you can place the dominos only in horizontal (1 * 2) or
* vertical (2 * 1) manner.
*

 Solution:
 either the last column is filled with vertical domino
 either the last 2 columns filled with 2 horizontal domino
 eg: if n = 4 its the combination of solution of n = 3 and 2
 if n = 4 => combination of (2 * 3 grid + 2 * 2 grid)

 ways[i] = ways[i-1] + ways[i-2] for i > 1
*
* */
public class DominoArrangements {
    public static int noOfWays(int n) {
        int[] ways = new int[n + 1];
        ways[0] = 1;
        ways[1] = 1;
        for (int i = 2; i <= n; i++) {
            ways[i] = ways[i - 1] + ways[i - 2];
        }
        return ways[n];
    }
}
