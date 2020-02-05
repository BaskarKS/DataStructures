package com.baskarks.string.manipulations;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
}
