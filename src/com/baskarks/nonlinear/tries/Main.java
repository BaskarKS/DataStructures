package com.baskarks.nonlinear.tries;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("cat");
        trie.insert("can");
        trie.insert("cannot");
        System.out.println("Checking Contains Method");
        System.out.println(trie.containsRecursive("can"));
        System.out.println(trie.containsRecursive("ca"));
        System.out.println(trie.containsRecursive(" "));
        System.out.println(trie.containsRecursive(null));
        trie.traversePreOrder();
        System.out.println();
        trie.traversePostOrder();
        trie.remove("cannot");
        trie.traversePreOrder();

        System.out.println();
        System.out.println();
        Trie autoComplete = new Trie();
        autoComplete.insert("car");
        autoComplete.insert("card");
        autoComplete.insert("care");
        autoComplete.insert("cared");
        autoComplete.insert("carrot");
        autoComplete.insert("ball");
        autoComplete.insert("bar");
        System.out.println("Searched Words : ");
        List<String> words = autoComplete.findWords("");
        System.out.println(words);
        System.out.println("word count : " + autoComplete.wordCount());

        String[] commonWords = {"cargo", "carnival"};
        System.out.println(autoComplete.longestCommonPrefix(commonWords));
    }
}
