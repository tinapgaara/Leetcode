package trie;

/**
 * Created by yingtan on 10/14/15.
 */
public class TrieNode {

    public TrieNode[] childrens;
    public double frequency;
    public int id;
    public char ch;

    public TrieNode() {
        childrens = new TrieNode[26];
    }

    public void addTreNode(TrieNode root, String word, int id) {
        if (word == null) return;
        int pos = word.charAt(0) - 'a';

        if (root.childrens[pos] == null) {
            TrieNode node = new TrieNode();
            node.ch = word.charAt(0);

            root.childrens[pos] = node;
        }

        String nextWord = word.substring(1);
        if (nextWord.length() == 0) {
            // current word is the last word: calculate frequency
            root.childrens[pos].frequency ++;
        }
        addTreNode(root.childrens[pos], word, id);
    }

}
