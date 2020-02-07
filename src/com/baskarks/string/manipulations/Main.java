package com.baskarks.string.manipulations;

public class Main {
    public static void main(String[] args) {
        String word = " go IndIa";
        var utils = new StringUtils();
        System.out.println(utils.countVowels(word));
        System.out.println(utils.reverseString(word));

        var statement = "Trees are Beautiful";
        System.out.println("'"+StringUtilsMosh.reverseWords(statement)+"'");

        String rotate = "ABCD";
        String rotated = "CABD";
        System.out.println(utils.isRotation(rotate, rotated));

        System.out.println(utils.removeDuplicateCharacters("baasskk as aww %$%$%*((*"));
        System.out.println(utils.getMostRepeatedCharacter(statement));

        String words = "   india is  my   country";
        System.out.println(utils.capitalize(words));

        String str1 = "$baskar", str2 = "ksabar$";
        System.out.println(utils.areAnagramsUsingMap(str1, str2));

        String palindrome = "malayalam";
        System.out.println(palindrome + " is Palindrome : " +
                                utils.isPalindrome(palindrome));
    }
}
