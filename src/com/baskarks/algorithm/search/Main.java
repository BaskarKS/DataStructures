package com.baskarks.algorithm.search;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] array = {7, 1, 3, 6, 5};
        int target = 70;

        //System.out.println(Arrays.toString(array));
        //var search = new LinearSearch();
        //int targetIdx = search.linearSearch(array, target);
        //System.out.println("Target index : " + targetIdx);

        Arrays.sort(array);
        System.out.println(Arrays.toString(array));
        var search = new BinarySearch();
        int targetIdx = search.binarySearchRecursive(array, target);
        System.out.println("Target index : " + targetIdx);
    }
}
