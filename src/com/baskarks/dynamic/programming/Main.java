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
    }
}
