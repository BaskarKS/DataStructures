package com.baskarks.string.manipulations;

import java.util.*;

public class StringUtilsMosh {

    public static int countVowels(String str) {
        int count = 0;
        String vowels = "aeiou";
        for (var ch : str.toLowerCase().toCharArray())
            if (vowels.contains(Character.toString(ch)))
                count++;
/*
  // if the string chars to look up is very large, its ideal
  //to use Set, Set data structure look up is very fast
        int count = 0;
        String vowels = "aeiou";
        Set<Character> vowelEasyLookUp = new HashSet<>();
        for (var ch : vowels.toCharArray())
            vowelEasyLookUp.add(ch);
        for (var ch : str.toCharArray())
            if (vowelEasyLookUp.contains(ch))
                count++;
*/
        return count;
    }

    public static String reverseString(String str) {
        if (str == null)
            return "";
/* Way 1
        read each char from string and add to a stack O(n)
        pop chars from stack and add it to a new String O(n)
        Total O(n)
*/

/* Way 2
        iterate string from backwards and add each char
        to a new string, its simpler. no need to create a stack
        more efficient to space
        String is immutable, changes to a string creates
        new string because of changes, all characters from
        old string is copied to new string and then the
        character is added, old string remains in memory
        Total Time Complexity O(n^2)
*/
/*
        String reversed = "";
        for (var i = str.length() - 1; i >= 0; i--) //O(n)
            reversed += str.charAt(i); //O(n)
        return reversed;
*/

/* Way 3*/
        //use string builder instead String - Time Comp : O(n)
        StringBuilder reversed = new StringBuilder();
        for (var i = str.length() - 1; i >= 0; i--) //O(n)
            reversed.append(str.charAt(i)); //O(1)
        return reversed.toString();

    }

    // REVERSE WORDS
    // trees are beautiful
    //beautiful are trees
    public static String reverseWords(String sentence) {
        if (sentence == null)
            return "";
        String[] words = sentence.trim().split(" +");
        //Way 1
        //push all words in stack and pop and form a sentence, so
        //the sentence is reversed.

        //Way 2
        //Iterate the array from end to beginning and form a sentence
/*
        StringBuilder reversed = new StringBuilder();
        for (var i = words.length - 1; i >= 0; i--)
            reversed.append(words[i] + " ");

        return reversed.toString().trim();
*/
        //Way 3
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }

    public static boolean areRotations(String str1, String str2) {
        if (str1 == null || str2 == null)
            return false;
        //Way 1
        /* take the first character in str2 and using that find the location
        of that character in str1 and go forward to match the chars of both
        str1 and str2. need two pointers, if pointer reaches end, then
        start matching from beginning.
        * */
        return (str1.length() == str2.length() && (str1 + str1).contains(str2));
    }

    public static String removeDuplicates(String str) {
         //iterate through each character to input string and add to output
        // if its not seen before
        if (str == null)
            return "";
        StringBuilder output = new StringBuilder();
        Set<Character> seen = new HashSet<>();

        for (var ch : str.toCharArray()) {
            if (!seen.contains(ch))
                output.append(ch);
        }
        return output.toString();
    }

    public static char getMaxOccurringChar(String str) {
        if (str == null || str.isEmpty())
            throw new IllegalArgumentException();
        final int ASCII_SIZE = 256;
        int[] frequencies = new int[ASCII_SIZE];
        for (var ch : str.toCharArray())
            frequencies[ch]++;
        int max = Integer.MIN_VALUE;
        char result = ' ';
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] > max) {
                max = frequencies[i];
                result = (char) i;
            }
        }
        return result;
    }

    public static String capitalize(String sentence) {
        if (sentence == null || sentence.trim().isEmpty())
            return "";
        String[] words = sentence
                            .trim()
                            .replaceAll(" +", " ")
                            .split(" ");
        for (var i = 0; i < words.length; i++) {
            words[i] = words[i].substring(0, 1).toUpperCase() +
                    words[i].substring(1).toLowerCase();
        }
        return String.join(" ", words);
    }

    //Are Anagrams:
    /*
    * convert each string into a character array, sort both the arrays,
    * finally compare both arrays for equality
    * */

    //O(n log n)
    public static boolean areAnagrams(String first, String second) {
        if (first == null || second == null ||
                (first.length() != second.length()))
            return false;
        //O(n)
        char[] array1 = first.toLowerCase().toCharArray();
        //O(n Log n)
        Arrays.sort(array1);
        char[] array2 = second.toLowerCase().toCharArray();
        Arrays.sort(array2);
        //O(n)
        return Arrays.equals(array1, array2);
    }

    /*
    counting no of occurrence in first string and check the
    count in second string and verify the count - Histogram Approach
    */

    //O(n)
    public static boolean areAnagramByHistogram(String first,
                                                String second) {
        if (first == null || second == null)
            return false;
        final int ENGLISH_ALPHABET = 26;
        int[] frequencies = new int[ENGLISH_ALPHABET];
        first = first.toLowerCase();
        //O(n)
        for (var i = 0; i < first.length(); i++)
            frequencies[first.charAt(i) - 'a']++;
        second = second.toLowerCase();
        //O(n)
        for (var i = 0;i < second.length(); i++) {
            var index = second.charAt(i) - 'a';
            if (frequencies[index] == 0)
                return false;
            frequencies[index]--;
        }
        return true;
    }

    public static boolean isPalindrome(String word) {
        if (word == null)
            return false;
/*
        var input = new StringBuilder(word);
        input.reverse();
        return input.toString().equals(word);
*/
        int left = 0, right = word.length() - 1;

        while (left < right) {
            if (word.charAt(left) != word.charAt(right))
                return false;
            left++;
            right--;
        }

        return true;
    }
}
