package com.baskarks.dynamic.programming;

public class NumberSplitting {
    public static void main(String[] args) {
        int number = 6;
        int maxNumberSplit = numberSplit(number);
        System.out.println(maxNumberSplit);
    }
    public static int numberSplit(int number) {
        int[] maxVal = new int[number + 1];
        int idx = maxVal.length - 1;
        while (idx >= 0)
            maxVal[idx--] = Integer.MIN_VALUE;

        maxVal[1] = 0;
        maxVal[2] = 1;

        for (var i = 3; i <= number; i++) {
            for (var j = 1; j < i; j++) {
                maxVal[i] = Math.max(Math.max((i - j) * j,
                                     j * maxVal[i - j]),
                                        maxVal[i]);
            }
        }
        return maxVal[number];
    }
}
