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

        int[] price = {0, 2, 4, 6, 8, 9};
        int rodLen = 5;
        int maxPossibility = RodCutter.getMaxRodCutPossibility(rodLen, price);
        System.out.println("Max possibility : " + maxPossibility);

        int[] stockPrices = {8, 1, 2, 4, 6, 3};
        int maxStockGain = ShareMarketAnalysis.shareMarket(stockPrices);
        System.out.println("Max Stock Gain : " + maxStockGain);

        int[] sumOfRange = {1, -2, 3, 10, -8, 0};
        int from = 1;
        int to = 4;
        var sumOfRangeInstance = new SumOfTheRange(sumOfRange);
        int sum = sumOfRangeInstance.sumOfTheRange(from, to);
        System.out.println("Sum of Range : " + sum);

        int[] getLis = {11, 23, 10, 37, 21, 50, 80};
        int lis = LongIncreaseSubSequence.LISubSequence(getLis);
        System.out.println("LIS : " + lis);

        int lengthOfArray = 5;
        var noOfDominosArrangement = DominoArrangements.noOfWays(lengthOfArray);
        System.out.println("no of Dominos Arrangement : " + noOfDominosArrangement);

        int[] stepCost = {2, 1, 3, 1, 2};
        int noOfSteps = 5;
        int minCost = StairWayToHeavenTwo.minFeeToReachHeaven(stepCost, noOfSteps);
        System.out.println("Min fee to Reach : " + minCost);
        StairWayToHeavenTwo.minimumPathToReachHeaven(stepCost, noOfSteps);
    }
}
