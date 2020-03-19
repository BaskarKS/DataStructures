package com.baskarks.dynamic.programming;
/*
* Problem:
* a burglar has come to your colony at night.
* your colony has many houses arranged in linear fashion and each house
* has some amount of money. the burglar wishes to take as much money
* as possible, but he cant rob 2 consecutive houses otherwise the security
* bill will ring and he will get caught. the money in the houses are given
* in the form of an array named houses.
* 
* */
public class BurglarsNightOut {
    public static void main(String[] args) {
        int[] houses_money = { 2, 4, 3, 5, 8, 3};
                       //1.  { 0, 0, 0, 0, 0, 3, 0}
                       //2.  { 0, 0, 0, 0, 8, 3, 0} MAX(8 + 0, 3)
                       //3.  { 0, 0, 0, 8, 8, 3, 0} MAX(5 + 3, 8)
                       //4.  { 0, 0,11, 8, 8, 3, 0} MAX(3 + 8, 8)
                       //5.  { 0,12,11, 8, 8, 3, 0} MAX(4 + 8, 11)
                       //6.  {13,12,11, 8, 8, 3, 0} MAX(2 + 11, 12)

        System.out.println(burglarNightOut(houses_money));
    }

    public static int burglarNightOut(int[] houseMoney) {
        if (houseMoney == null || houseMoney.length == 0)
            return 0;

        int[] maxMoney = new int[houseMoney.length + 1];
        var lastIndex = maxMoney.length - 1;

        maxMoney[lastIndex] = 0;
        maxMoney[lastIndex - 1] = houseMoney[houseMoney.length - 1];

        for (var index = lastIndex - 2; index >= 0; index--) {
            maxMoney[index] = Math.max(houseMoney[index] + maxMoney[index + 2],
                                            maxMoney[index + 1]);
        }

        return maxMoney[0];
    }
}
