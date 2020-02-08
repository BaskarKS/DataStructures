package com.baskarks.dynamic.programming;

public class Main {
    public static void main(String[] args) {
        System.out.println("Dynamic Programming");
        var steps = StairCaseToHeaven.
                getNoOfWaysToHeaven(5);
        System.out.println(steps);

        int matrixRow = 4, matrixCol = 5;
        System.out.println(OnTheWayHome.
                onTheWayHome(matrixRow, matrixCol));

        int[] cost = {0, 2, 4, 5, 7, 11, 13, 17};
        int rodLength = 4;
        int max = RodCutter.getMaxRodCutPrice(rodLength, cost);
        System.out.println("Max Rod Price : " + max);
    }
}
