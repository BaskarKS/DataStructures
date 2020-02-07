package com.baskarks.string.manipulations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StringUtils {
    private final int ASCII_SIZE = 128;
    public int countVowels(String word) {
        if (word == null || word.isEmpty())
            return -1;
        String vowels = "aeiou";
        int count = 0;
        for (char ch : word.toLowerCase().toCharArray())
            if (vowels.indexOf(ch) != -1)
                count++;

        return count;
    }

    public String reverseString(String str) {
        if (str == null || str.isEmpty())
            throw new IllegalArgumentException("Input is Invalid");

        StringBuilder reverse = new StringBuilder();
        for (int index = str.length() - 1; index >= 0; index--)
            reverse.append(str.charAt(index));
        return reverse.toString();

/*
        StringBuilder reverseString = new StringBuilder(str);
        return reverseString.reverse().toString();
*/
    }

    public String reverseWords(String words) {
        if (words == null || words.isEmpty())
            return "";
        StringBuilder reverseWords = new StringBuilder();
        String[] wordArray = words.trim().split(" +");
        for (var index = wordArray.length - 1;index >= 0; index--) {
            reverseWords.append(wordArray[index]);
            reverseWords.append(" ");
        }
        return reverseWords.toString().trim();
    }

    public boolean isRotation(String first, String second) {
        //Way 1
/*
        String isRotation = first + first;
        return isRotation.contains(second);
*/
        //Way 2
/*        for (int idx = 0; idx < first.length(); idx++) {
            //String rotated = getRotatedString(first, idx);
            String rotated = stringRotateWithoutHelper(first, idx);
            if (rotated.equals(second))
                return true;
        }
        return false;
*/
        //Way 3
        /* take the first character in str2 and using that find the location
        of that character in str1 and go forward to match the chars of both
        str1 and str2. need two pointers, if pointer reaches end, then
        start matching from beginning.
        * */
        var firstIdx = 0;
        var secondIdx = second.indexOf(first.charAt(0));
        var counter = first.length();
        while (counter > 0) {
            if (first.charAt(firstIdx) !=
                                second.charAt(secondIdx))
                return false;
            firstIdx++;
            secondIdx = (secondIdx + 1) < second.length() ? secondIdx + 1 : 0;
            counter--;
        }
        return true;
    }

    private String getRotatedString(String str, int idx) {
        int length = str.length();
        return str.substring(length - idx, length) + str.substring(0, length - idx);
    }

    private String stringRotateWithoutHelper(String str, int idx) {
        char[] rotated = new char[str.length()];
        char[] string = str.toCharArray();

        int location = str.length() - idx;
        int rotateIdx = 0, beg = 0;

        while (location < str.length())
            rotated[rotateIdx++] = string[location++];

        location = str.length() - idx;
        while (beg < location)
            rotated[rotateIdx++] = string[beg++];

        return new String(rotated);
    }

    //Remove Duplicate Characters in the input String
    public String removeDuplicates(String str) {
        if (str == null || str.isEmpty())
            return "";
        char[] dupeString = str.toCharArray();
        StringBuilder dupeRemoved = new StringBuilder();
        dupeRemoved.append(dupeString[0]);
        for (int i = 1; i < dupeString.length; i++) {
            if (dupeString[i] != dupeString[i - 1])
                dupeRemoved.append(dupeString[i]);
        }
        return dupeRemoved.toString();
    }

    public String removeDuplicateCharacters(String str) {
        boolean[] charsSeen = new boolean[ASCII_SIZE];
        StringBuilder removedDupeChars = new StringBuilder();

        for (char ch : str.toCharArray()) {
            if (charsSeen[ch])
                continue;
            removedDupeChars.append(ch);
            charsSeen[ch] = true;
        }
        return removedDupeChars.toString();
    }

    public char getMostRepeatedCharacter(String str) {
        if (str == null)
            return ' ';
        Map<Character, Integer> count = new HashMap<>();
        int max = Integer.MIN_VALUE;
        char repeatedChar = str.charAt(0);
        for (char ch : str.toCharArray()) {
            if (!count.containsKey(ch))
                count.put(ch, 0);
            int occurrence = count.get(ch) + 1;
            count.put(ch, occurrence);
            if (max <= occurrence) {
                max = occurrence;
                repeatedChar = ch;
            }
        }
        return repeatedChar;
    }

    public String capitalize(String sentence) {
        if (sentence == null || sentence.trim().isEmpty())
            return "";
        String[] words = sentence.trim().split(" +");
        for (int idx = 0; idx < words.length; idx++)
            words[idx] = words[idx].substring(0, 1).toUpperCase()
                                    + words[idx].substring(1);
        return String.join(" ", words);
    }

    public boolean areAnagrams(String str1, String str2) {
        if ((str1 == null || str2 == null) ||
                str1.length() != str2.length())
            return false;

        int[] str1Count = new int[ASCII_SIZE];
        for (char ch : str1.toCharArray())
            str1Count[ch]++;
        for (char ch : str2.toCharArray()) {
            if (--str1Count[ch] < 0)
                return false;
        }

        return true;
    }

    public boolean areAnagramsUsingMap(String str1, String str2) {
        if ((str1 == null || str2 == null) ||
                str1.length() != str2.length())
            return false;

        Map<Character, Integer> str1Count = new HashMap<>();
        for (char ch : str1.toCharArray()) {
            if (!str1Count.containsKey(ch))
                str1Count.put(ch, 0);
            str1Count.replace(ch, str1Count.get(ch) + 1);
        }

        for (char ch : str2.toCharArray()) {
            if (!str1Count.containsKey(ch))
                return false;
            if (str1Count.replace(ch, str1Count.get(ch) - 1) < 0)
                return false;
        }
        return true;
    }

    public boolean isPalindrome(String str) {
        int start = 0, end = str.length() - 1;
        str = str.toLowerCase();
        while (start <= end) {
            if (str.charAt(start) !=
                    str.charAt(end))
                return false;
            start++;
            end--;
        }
        return true;
    }
}
