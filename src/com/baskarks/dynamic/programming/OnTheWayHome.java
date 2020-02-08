package com.baskarks.dynamic.programming;
/*
* You are located at the top-left corner of m X n grid
* you can move either right or down at any point in time
* your home is located at bottom-right corner of grid.
* how many unique ways you can reach home?
*    1  2  3
*  1 X
*  2
*  3       H
*
*
* * */
public class OnTheWayHome {
    public static int onTheWayHome(int row, int column) {
        int[][] paths = new int[row][column];

        //Base Case Initialization
        // all last column value is initialized to 1
        // because only way to go home is down
        for (int i = 0, lastCol = column - 1;
                        i < paths.length; i++) {
            paths[i][lastCol] = 1;
        }
        // all last row value is initialized to 1
        // because only way to go home is right
        for (int i = 0, lastRow = row - 1;
             i < paths[0].length; i++) {
            paths[lastRow][i] = 1;
        }
        //way to go home is only through right and down
        //no of ways to go home from 0 , 0. which is ways[0][0]
        // sum of ways[0][1] + ways[1][0]
        int lastPrevRow = row - 2;
        int lastPrevCol = column - 2;
        for (int r = lastPrevRow; r >= 0; r--) {
            for (int c = lastPrevCol; c >= 0; c--) {
                paths[r][c] = paths[r][c + 1] + paths[r + 1][c];
            }
        }
        return paths[0][0];
    }
}
