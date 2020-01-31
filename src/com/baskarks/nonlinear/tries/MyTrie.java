package com.baskarks.nonlinear.tries;

import java.util.ArrayList;
import java.util.List;

public class MyTrie {
    private static final int ALPHABET_COUNT = 26;
    private class Node {
        private char value;
        private Node[] children;
        private boolean isEndOfWord;
        public Node(char ch) {
            this.value = ch;
            children = new Node[ALPHABET_COUNT];
        }
        public Node getChild(int idx) {
            return children[idx];
        }
        public void setChild(int idx, Node child) {
            children[idx] = child;
        }
        private boolean hasChild(int idx) {
            return getChild(idx) != null;
        }
        public boolean getEndOfWord() {
            return isEndOfWord;
        }
        public void setEndOfWord(boolean endOfWord) {
            this.isEndOfWord = endOfWord;
        }
        public Node[] getChildren() {
            return children;
        }
        @Override
        public String toString() {
            return "value=" + value;
        }
        public boolean hasChildren() {
            return children.length > 0;
        }
        public void removeChild(int idx) {
            children[idx] = null;
        }
    }
    public MyTrie() {
        root = new Node(' ');
    }
    private Node getRoot() {
        return root;
    }
    private Node root;
    public void insert(String word) {
        Node current = getRoot();
        for (char ch : word.toLowerCase().toCharArray()) {
            if (!current.hasChild(getIdx(ch)))
                current.setChild(getIdx(ch), new Node(ch));
            current = current.getChild(getIdx(ch));
        }
        current.setEndOfWord(true);
    }
    private int getIdx(char ch) {
        return ch - 'a';
    }

    public void remove(String word) {
        if (word == null || word.isEmpty())
            return;
        remove(root, word, 0);
    }
    private void remove(Node root, String word, int index) {
        if (index == word.length()) {
            root.isEndOfWord = false;
            return;
        }

        char ch = word.charAt(index);
        Node child = root.getChild(ch);

        if (child == null)
            return;
        remove(child, word, index + 1);
/*
        if (root.hasChild(ch))
            remove(child, word, index + 1);
        else
            return;
*/

        if (!child.hasChildren() && !child.isEndOfWord) {
            root.removeChild(getIdx(child.value));
            return;
        }
    }

    public List<String> autoCompleteList(String word) {
        List<String> wordList = new ArrayList<>();
        if (word == null)
            return wordList;
        Node current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (current.hasChild(ch))
                current = current.getChild(ch);
            else return wordList;
        }
        autoComplete(current, word.substring(0, word.length() - 1), wordList);
        return wordList;
    }
    private void autoComplete(Node root, String word, List<String> words) {
        if (root == null)
            return;

        word += root.value;

        if (root.isEndOfWord)
            words.add(word);

        for (Node child : root.getChildren())
            autoComplete(child, word, words);

        return;
    }

}
