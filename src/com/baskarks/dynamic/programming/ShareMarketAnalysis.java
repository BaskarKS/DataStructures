package com.baskarks.dynamic.programming;

/*
* You are being given an array in which the ith element
* is the price of a given stock on day i. you are permitted
* to complete at most one transaction (buy once and sell once)
* what is the maximum profit you can gain. you cannot
* sell a stock before you buy one.
* */
public class ShareMarketAnalysis {
    public static void main(String[] args) {
        int[] stockPrices = {8, 1, 2, 4, 6, 3};
        int maxStockGain = ShareMarketAnalysis.shareMarket(stockPrices);
        System.out.println("Max Stock Gain : " + maxStockGain);
    }
    public static int shareMarket(int[] prices) {
        int[] minPrices = new int[prices.length];
        int[] maxStocks = new int[prices.length];
        //on first day the same price will be minimum
        int minPrice = minPrices[0] = prices[0];
        //create a min array to maintain minimum stock on ith day
        // minimum value on 5th day, lowest stock value till 5th day
        for (int index = 1; index < prices.length; index++) {
            if (prices[index] < minPrice)
                minPrice = prices[index];
            minPrices[index] = minPrice;
        }
        // subtract price array with min array, to find the
        // max value of stock can be obtained on that particular
        // day
        int maxStockGain = Integer.MIN_VALUE;
        for (int index = 0; index < prices.length; index++) {
            maxStocks[index] = prices[index] - minPrices[index];
            if (maxStockGain < maxStocks[index])
                maxStockGain = maxStocks[index];
        }
        return maxStockGain;
    }
}
