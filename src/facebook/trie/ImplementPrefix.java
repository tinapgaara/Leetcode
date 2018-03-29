package facebook.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yingtan on 12/21/17.
 *
 * 208. Implement Trie (Prefix Tree)
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Implement a trie with insert, search, and startsWith methods.

 Note:
 You may assume that all inputs are consist of lowercase letters a-z.
 */
public class ImplementPrefix {
    public class TrieNode {
        char val;
        Map<Character, TrieNode> children;
        Map<String, Integer> strToCount;
        boolean isEnd;
        public TrieNode(char val) {
            this.val = val;
            this.children = new HashMap<>();
            strToCount = new HashMap<>();
            this.isEnd = false;
        }
    }
    TrieNode root;
    /** Initialize your data structure here. */
    public ImplementPrefix() {
        root = new TrieNode('$');
    }
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode cur = root;
        for (int i = 0 ; i < word.length(); i ++) {
            char ch = word.charAt(i);
            if (! cur.children.containsKey(ch)) {
                TrieNode child = new TrieNode(ch);
                cur.children.put(ch, child);
            }
            cur = cur.children.get(ch);
        }
        cur.isEnd = true;
    }
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode cur = root;
        for (int i = 0 ; i < word.length(); i ++) {
            char ch = word.charAt(i);
            if (! cur.children.containsKey(ch)) {
                // does not match
                return false;
            }
            else {
                cur = cur.children.get(ch);
            }
        }
        return cur.isEnd;
    }
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        for (int i = 0 ; i < prefix.length(); i ++) {
            char ch = prefix.charAt(i);
            if (! cur.children.containsKey(ch)) {
                // does not match
                return false;
            }
            else {
                cur = cur.children.get(ch);
            }
        }
        return true;
    }
}
