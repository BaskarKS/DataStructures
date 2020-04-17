package com.baskarks.scratchpad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trie {
    private class Node {
        private char value;
        private Map<Character,Node> children;
        private boolean isEndOfWord;

        @Override
        public String toString() {
            return "value=" + value +
                    ", isEndOfWord=" + isEndOfWord;
        }

        public Node(char value) {
            this.value = value;
            children = new HashMap<>();
        }
        public boolean hasChild(char ch) {
            return children.containsKey(ch);
        }
        public Node getChild(char ch) {
            return hasChild(ch)? children.get(ch) : null;
        }
        public void addChild(char ch) {
            children.put(ch, new Node(ch));
        }
        public Node[] getChildren() {
            return children.values().toArray(new Node[0]);
        }
        public Node getChild() {
            return hasChildren()?children.values().iterator().next() : null;
        }
        public boolean hasChildren() {
            return !children.isEmpty();
        }
        public void removeChild(char ch) {
            if (hasChildren() && hasChild(ch))
                children.remove(ch);
        }
    }
    private Node root = new Node(' ');
    private Node getRoot() {
        return root;
    }
    public void insert(String word) {
        Node current = getRoot();
        for (char ch : word.toCharArray()) {
            if (!current.hasChild(ch))
                current.addChild(ch);
            current = current.getChild(ch);
        }
        current.isEndOfWord = true;
    }

    public void printTopDown() {
        preOrderTraversal();
    }
    public void printDownTop() {
        postOrderTraversal();
    }
    public void preOrderTraversal() {
        System.out.println();
        traversePreOrder(getRoot());
        System.out.println();
    }
    private void traversePreOrder(Node node) {
        System.out.print(node.value + " ");
        for (Node child : node.getChildren()) {
            traversePreOrder(child);
        }
    }
    public void postOrderTraversal() {
        System.out.println();
        traversePostOrder(getRoot());
        System.out.println();
    }
    private void traversePostOrder(Node node) {
        for (Node child : node.getChildren()) {
            traversePostOrder(child);
        }
        System.out.print(node.value + " ");
    }

    public boolean contains(String word) {
        Node current = getRoot();
        for (char ch : word.toCharArray()) {
            if (!current.hasChild(ch))
                return false;
            current = current.getChild(ch);
        }
        return current.isEndOfWord;
    }

    public boolean containsRecursive(String word) {
        return checkContains(getRoot(), word, 0);
    }
    private boolean checkContains(Node root, String word, int index) {
        if (word.length() == index)
            return root.isEndOfWord;

        char ch = word.charAt(index);
        return root.hasChild(ch) ? checkContains(root.getChild(ch),
                                word, index + 1) : false;
    }

    public void remove(String word) {
        if (word == null)
            return;
        remove(getRoot(), word, 0);
    }

    public void remove(Node root, String word, int index) {
        if (root == null)
            return;
        if (word.length() == index) {
            root.isEndOfWord = false;
            return;
        }
        char ch = word.charAt(index);
        Node child = root.getChild(ch);
        if (child == null)
            return;
        remove(child, word, index + 1);
        if (!child.hasChildren() && !child.isEndOfWord)
            root.removeChild(ch);
    }

    public List<String> findWords(String prefix) {
        List<String> words = new ArrayList<>();

        if (prefix == null || prefix.length() == 0)
            return words;

        getAllWords(getLastNode(prefix), prefix, words);

        return words;
    }

    private Node getLastNode(String prefix) {
        Node current = getRoot();
        for (char ch : prefix.toCharArray()) {
            if (!current.hasChild(ch))
                return null;
            current = current.getChild(ch);
        }
        return current;
    }

    private void getAllWords(Node current, String prefix, List<String> words) {
        if (current == null)
            return;

        if (current.isEndOfWord)
            words.add(prefix);

        for (Node child : current.getChildren())
            getAllWords(child, prefix + child.value, words);
    }

    public int wordCount() {
        return wordCount(getRoot());
    }
    private int wordCount(Node root) {
        int count = 0;
        if (root == null)
            return count;

        for (Node child : root.getChildren())
            count += wordCount(child);

        if (root.isEndOfWord)
            count+=1;

        return count;
    }
    public String longestCommonPrefix() {
        Node current = getRoot();
        int length = current.getChildren().length;
        if (length == 0 || length > 1)
            return "";
        Node child = current.getChild();
        StringBuilder common = new StringBuilder();
        while (child != null) {
            char ch = child.value;
            common.append(ch);
            if (child.getChildren().length > 1 || child.isEndOfWord)
                break;
            child = child.getChild();
        }
        return common.toString();
    }
    public static void main(String[] args) {
        Trie trie = new Trie();
        String[] words = {"cat", "can", "cannot", "ball"};
        for (String word : words)
            trie.insert(word);
        trie.printTopDown();
        System.out.println();
      //  System.out.println(trie.wordCount());
        System.out.println(trie.containsRecursive("cat"));
        System.out.println(trie.containsRecursive("ball"));
        System.out.println(trie.containsRecursive("cannot"));
        System.out.println(trie.containsRecursive("cot"));
        trie.printDownTop();
        trie.remove("cannot");
        trie.printDownTop();
        trie.insert("cannot");
        trie.insert("cot");
        trie.insert("caterpillar");
        trie.insert("cattle");
        System.out.println(trie.findWords("cat"));
        Trie newTrie = new Trie();
        newTrie.insert("battle");
        newTrie.insert("bat");
//        newTrie.insert("cat");
//        newTrie.insert("cattle");
        System.out.println(newTrie.wordCount());
        System.out.println(newTrie.longestCommonPrefix());
    }
}
