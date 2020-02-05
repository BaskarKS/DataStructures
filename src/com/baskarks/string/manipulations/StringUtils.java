package com.baskarks.string.manipulations;

public class StringUtils {
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
}
