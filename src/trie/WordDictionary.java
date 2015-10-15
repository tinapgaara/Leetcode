package trie;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yingtan on 9/23/15.
 */
public class WordDictionary {
    TrieNode root;

    public class TrieNode {
        char ch;
        TrieNode[] childrens;
        boolean isStop;

        public TrieNode(char ch) {
            this.ch = ch;
            childrens = new TrieNode[26];
            isStop = false;
        }
    }

    public WordDictionary() {
        root = new TrieNode('0'); // start
    }

    // Adds a word into the data structure.
    public void addWord(String word) {
        if ((word == null) || (word.length() == 0)) return;
        TrieNode copy = root;

        for (int i = 0 ; i < word.length(); i ++) {
            int pos = word.charAt(i) - 'a';

            if (copy.childrens[pos] == null) {
                TrieNode newnode = new TrieNode(word.charAt(i));
                copy.childrens[pos] = newnode;
            }
            if (i == word.length() - 1) {
                copy.isStop = true;
            } else {
                copy.isStop = false;
                copy = copy.childrens[pos];
            }
        }
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        if ((word == null) || (word.length() == 0)) return false;

        return recurSearch(root, word, 0);
    }

    public boolean recurSearch(TrieNode cur, String word, int index) {
        if (index >= word.length()) return false;
        TrieNode copy = cur;
        char ch = word.charAt(index);
        if (ch == '.') {
            for (int i = 0; i < copy.childrens.length; i++) {
                if ((copy.childrens[i] != null) && (!copy.isStop)) {
                    System.out.println(copy.childrens[i].ch);
                    if (recurSearch(copy.childrens[i], word, index + 1));
                        return true;
                } else if ((copy.childrens[i] != null) &&(word.length() - index == 1)) return true;
            }
            return false;
        } else {
            int pos = ch - 'a';
            if ((!copy.isStop) && (copy.childrens[pos] != null)) {
                copy = copy.childrens[pos];
                return recurSearch(copy, word, index + 1);
            } else {
                if ((copy.childrens[pos] != null) && (word.length() - index == 1)) return true;
                else return false;
            }
        }
    }


    public static void main(String[] args) {
        WordDictionary dict = new WordDictionary();
        dict.addWord("a");
        dict.addWord("ab");

        System.out.println(dict.search("a"));
        System.out.println(dict.search("a."));
        /*
        System.out.println(dict.search("ab"));
        System.out.println(dict.search(".a"));
        System.out.println(dict.search(".b"));
        System.out.println(dict.search("ab."));
        System.out.println(dict.search("ba."));
        System.out.println(dict.search("."));
        System.out.println(dict.search(".."));
        */
    }
}
