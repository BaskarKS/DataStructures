package com.baskarks.dynamic.programming;

/*
* Problem Statement:
*
* you want to reach heaven that is at the top of the
* staircase.
* The staircase has 'n' steps. And each staircase has a
* fee associated with it(in fee array). if you pay that
* fee you can take either 1 / 2 steps / 3 steps. you can
* only start from the lower most stair (0). whats the lowest
* fee you need to pay to reach heaven at stair with index n.
*
* Eg:
* */
public class StairWayToHeavenTwo {
    public static int minFeeToReachHeaven(int[] stepCost, int n) {
        int[] minFees = new int[n + 1];
        minFees[0] = 0;
        minFees[1] = stepCost[0];
        minFees[2] = stepCost[0];
        for (var i = 3; i <= n; i++) {
            minFees[i] = Math.min(Math.min((stepCost[i - 1] + minFees[i - 1]),
                    (stepCost[i - 2] + minFees[i - 2])),
                    (stepCost[i - 3] + minFees[i - 3]));
        }
        return minFees[n];
    }

    public static void minimumPathToReachHeaven(int[] stepCost, int n) {
        int[] minFees = new int[n + 1];
        int[] steps = new int[minFees.length];
        minFees[0] = 0;
        minFees[1] = stepCost[0];
        minFees[2] = stepCost[1];

        steps[0] = -1;
        steps[1] = 0;
        steps[2] = 0;

        for (var i = 3; i <= n; i++) {
            minFees[i] = Math.min(Math.min(
                    (stepCost[i - 1] + minFees[i - 1]),
                    (stepCost[i - 2] + minFees[i - 2])),
                    (stepCost[i - 3] + minFees[i - 3]));
            if (minFees[i] == stepCost[i - 1] + minFees[i - 1])
                steps[i] = i - 1;
            else if (minFees[i] == stepCost[i - 2] + minFees[i - 2])
                steps[i] = i - 2;
            else
                steps[i] = i - 3;
        }
        var stepsFromHeaven = n;
        while (stepsFromHeaven >= 0) {
            System.out.print(stepsFromHeaven + " -> ");
            //System.out.print(steps[stepsFromHeaven--] + " -> ");
            stepsFromHeaven = steps[stepsFromHeaven];
        }
    }
}
