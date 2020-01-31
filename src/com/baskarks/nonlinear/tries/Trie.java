package com.baskarks.nonlinear.tries;

/*
 * Tries are another kind of trees but not Binary trees
 * Each child can have several nodes
 *
 * Word Trie comes from the word reTRIEval, some call it
 * digital, prefix, radix
 *
 * we use tries to implement AutoCompletion, search query in
 * google search, auto suggestion
 *
 * why not arrays?
 * 1. if large no of words, lot of space wasted because lot
 * of words have same prefix.
 * 2. looking up words starts with some prefix using arrays is
 * relatively slow, have to go through every word and check
 * for prefix
 *
 * Tries allows to store millions of words to store and
 * easier look up
 *
 * English we have 26 alphabets, each node in trie
 * can have to max of 26 children
 *
 * Root node is always null / empty character, english language
 * start with any of the character, we cannot have 26 roots in
 * tree, hence we have empty root.
 *
 * Time Complexity
 * Look Up - O(L) - L is the length of the word we are looking
 * Doesn't depend on the number of nodes in the trie.
 * If the longest word in trie is 20 characters long, time
 * complexity of looking a word is O(20) in worst case
 *
 * Adding, Inserting a word - O(L)
 * For each letter in given word, we have to walk down the
 * our tree, if the current character doesn't exit we have to
 * add it to the tree. the number of operations involved is
 * equal to the number of characters in word we insert.
 *
 * Removing / Deleting a word - O(L)
 *
 * */

import java.util.*;

public class Trie {
    public static int ALPHABET_SIZE = 26;
    private class Node {
        private char value;
        private Map<Character, Node> children = new HashMap<>();
        private boolean isEndOfWord;

        public Node(char value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "value=" + value;
        }

        public boolean hasChild(char ch) {
            return children.containsKey(ch);
        }

        public void addChild(char ch) {
            children.put(ch, new Node(ch));
        }

        public Node getChild(char ch) {
            return children.get(ch);
        }
        public Node getChild() {
            return children.size() > 0 ?
                    children.values().iterator().next() : null;
        }
        public Node[] getChildren() {
            return children.values().toArray(new Node[0]);
        }
        public int childrenCount() {
            return children.size();
        }
        public boolean hasChildren() {
            return !children.isEmpty();
        }
        public void removeChild(char ch) {
            children.remove(ch);
        }
    }
    private Node root = new Node(' ');
    public Node getRoot() {
        return root;
    }

    public void insert(String word) {
        var current = root;
        for (var ch : word.toCharArray()) {
            if (!current.hasChild(ch))
                current.addChild(ch);
            current = current.getChild(ch);
        }
        current.isEndOfWord = true;
    }

    public boolean contains(String word) {
        if (word == null || word.isEmpty())
            return false;
        Node current = root;
        for (char ch : word.toCharArray()) {
            if (!current.hasChild(ch))
                return false;
            current = current.getChild(ch);
        }
        return current.isEndOfWord;
    }

    public boolean containsRecursive(String word) {
        if (word == null || word.isEmpty())
            return false;
        return containsRecursive(root, word, 0);
    }
    private boolean containsRecursive(Node root, String word, int index) {
        if (root == null)
            return false;
        if (index == word.length())
            return root.isEndOfWord;
        char ch = word.charAt(index);
        Node child = root.getChild(ch);
        return containsRecursive(child, word, index + 1);
    }
    /*
    * 2 ways to traverse Tries
    * Preorder => visit each node first before visiting its
    * children
    * Post Order => we go deep in Trie, start from leaf node
    * and come back up
    * */

    //Pre-Order is used probably to read from the trie
    public void traversePreOrder() {
        traversePreOrder(root);
    }
    private void traversePreOrder(Node root) {
        //visit root
        System.out.print(root.value+" ");
        //visit all of its children
        for (Node child : root.getChildren())
            traversePreOrder(child);
    }

    //Pre-Order is used probably to delete from the trie
    public void traversePostOrder() {
        traversePostOrder(root);
    }
    private void traversePostOrder(Node root) {
        //visit all of its children
        for (Node child : root.getChildren())
            traversePostOrder(child);
        //visit root
        System.out.println(root.value);
    }

    public void remove(String word) {
        if (word == null)
            return;
        remove(root, word, 0);
    }
    private void remove(Node root,String word, int index) {
        if (index == word.length()) {
            root.isEndOfWord = false;
            return;
        }
        var ch = word.charAt(index);
        var child = root.getChild(ch);
        if (child == null)
            return;

        remove(child, word, index + 1);
        if (!child.hasChildren() && !child.isEndOfWord)
            root.removeChild(ch);
    }

    public List<String> findWords(String prefix) {
        List<String> wordList = new ArrayList<>();
        if (prefix == null)
            return wordList;

        findWords(findLastNode(prefix), prefix, wordList);
        return wordList;
    }
    private Node findLastNode(String prefix) {
        Node current = root;
        for (char ch : prefix.toCharArray()) {
            current = current.getChild(ch);
            if (current == null)
                return null;
        }
        return current;
    }
    private void findWords(Node root, String prefix, List<String> words) {
        if (root == null)
            return;

        if (root.isEndOfWord)
            words.add(prefix);

        for (Node child : root.getChildren())
            findWords(child, prefix + child.value, words);
    }

    public int wordCount() {
        return wordCount(root);
    }
    private int wordCount(Node root) {
        if (root == null)
           return 0;
        int count = 0;
        for (Node child : root.getChildren())
            count += wordCount(child);
        if (root.isEndOfWord)
            count += 1;
        return count;
    }

    public String longestCommonPrefix(String[] words) {
        if (words == null || words.length == 0)
            return "";

        Trie trie = buildTrie(words);

        Node current = getFirstNode(trie);
        if (current == null)
            return "";

        return getString(current);
    }
    // provide common string from the provided node
    private String getString(Node current) {
        StringBuilder commonString = new StringBuilder();
        while (current != null) {
            commonString.append(current.value);
            if (current.childrenCount() < 2)
                current = current.getChild();
            else
                break;
        }
        return commonString.toString();
    }

    // provide first node if only trie has single children else null
    private Node getFirstNode(Trie trie) {
        Node current = trie.getRoot();
        if (current.childrenCount() > 1)
            return null;
        else
            current = current.getChild();
        return current;
    }
    // build trie based on the input word list
    private Trie buildTrie(String[] words) {
        Trie trie = new Trie();
        for (String word : words)
            trie.insert(word);
        return trie;
    }
}
