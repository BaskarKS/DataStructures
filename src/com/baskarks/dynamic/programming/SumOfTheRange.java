package com.baskarks.dynamic.programming;

/*
* you are being given an integer array named my_array,
* you need to find the sum of elements between the range
* i and j (i <= j) inclusive. note: There are many such
* queries need to be answered.
*
* sum_until[i] = sum_until[i - 1] + my_array[i];
* i > 0  => sumRange(i, j) = sum_until[j] - sum_until[i - 1]
* i == 0 => sumRange(i, j) = sum_until[j]
* sumRange(2, 5) = sum_until[5] - sum_until[1];
* */
public class SumOfTheRange {
    private int[] sumUntil;
    public SumOfTheRange(int[] array) {
        sumUntil = new int[array.length];
        preProcess(array);
    }
    private void preProcess(int[] array) {
        sumUntil[0] = array[0];
        for (int i = 1; i < array.length; i++)
            sumUntil[i] = sumUntil[i - 1] + array[i];
    }
    public int sumOfTheRange(int from, int to) {
        if (from == 0)
            return sumUntil[to];
        return sumUntil[to] - sumUntil[from - 1];
    }
}
