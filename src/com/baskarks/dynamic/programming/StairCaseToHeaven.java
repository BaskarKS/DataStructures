package com.baskarks.dynamic.programming;

public class StairCaseToHeaven {
    /*
    * You want to reach heaven that is at the top of this
    * staircase. The Staircase has 'n' steps, and at each step
    * you can climb either 1 step or 2 steps further. In
    * how many ways you can reach heaven.
    *
    * The last step can be reached either by 1 step,
    * or it can be reached by 2 steps.
    *
    * if you consider n as 4 steps:
    * possibilities are :
    * 1 + 1 + 1 + 1
    *     1 + 2 + 1
    *     2 + 1 + 1
    *     1 + 1 + 2
    *         2 + 2
    *
    * to find possibilities of n steps =
    *    possibilities for n - 1 steps +
    *           possibilities for n - 2 steps.
    * possibilities for n - 1 steps =
    *            possibilities for n - 2 step + possibilities
    *                               for n - 3 steps.
    * possibilities for n - 2 steps =
    *            possibilities for n - 3 steps +
    *               possibilities for n - 4 steps..
    *
    * if we remember the early computation results,
    *    we can solve the original problem.
    * */

    public static int getNoOfWaysToHeaven(int nSteps) {
        int[] prevComputations = new int[nSteps + 1];
        prevComputations[1] = 1;
        prevComputations[2] = 2;
        for (var i = 3; i < prevComputations.length; i++) {
            prevComputations[i] = prevComputations[i - 1] +
                                prevComputations[i - 2];
        }
        return prevComputations[nSteps];
    }
}
