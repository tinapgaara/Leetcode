package facebook.trie;

/**
 * Created by yingtan on 5/18/17.
 *
 * 211. Add and Search Word - Data structure design Add to List
 DescriptionHintsSubmissionsSolutions
 Total Accepted: 50971
 Total Submissions: 234905
 Difficulty: Medium
 Contributor: LeetCode
 Design a data structure that supports the following two operations:

 void addWord(word)
 bool search(word)
 search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

 For example:

 addWord("bad")
 addWord("dad")
 addWord("mad")
 search("pad") -> false
 search("bad") -> true
 search(".ad") -> true
 search("b..") -> true

 */
import java.util.*;
public class AddAndSearchWord {

    public class Trie {
        Trie[] children;
        char ch;
        boolean isend;
        public Trie(char ch) {
            children = new Trie[256];
            this.ch = ch;
        }
    }
    Trie root;
    /** Initialize your data structure here. */
    public AddAndSearchWord() {
        root = new Trie('#') ; //root synmbol
    }
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        if (word == null) return;
        Trie cur = root;
        for (char ch : word.toCharArray()) {
            if (cur.children[ch - 'a'] == null) {
                cur.children[ch - 'a'] = new Trie(ch);
            }
            cur = cur.children[ch - 'a'];
        }
        cur.isend = true;
    }
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        if (word == null) return false;
        return search(word, 0, root);
    }
    public boolean search(String word, int index, Trie cur) {
        // base case
        if (word.length() == index) {
            // important !!!!  reach the outboundary one, check cur.isEnd.  word's index is one level lower than the current TrieNode
            return cur.isend;
        }
        char ch = word.charAt(index);
        if (ch == '.') {
            // returns 256 children, some child are null
            Trie[] children = cur.children;
            // match all children
            for (Trie child : children) {
                // Very Important !!!!!!
                if (child != null) {
                    if (search(word, index + 1, child)) {
                        return true;
                    }
                }
            }
            return false;
        }
        else {
            if (cur.children[ch - 'a'] != null) {
                return search(word, index + 1, cur.children[ch - 'a']);
            }
            return false;
        }
    }



    /**
     * Your AddAndSearchWord object will be instantiated and called as such:
     * AddAndSearchWord obj = new AddAndSearchWord();
     * obj.addWord(word);
     * boolean param_2 = obj.search(word);
     *
     *
     * ["AddAndSearchWord","addWord","addWord","addWord","search","search","search","search"]
     [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
     */
}
