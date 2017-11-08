package facebook.trie;

/**
 * Created by yingtan on 5/18/17.
 *
 * 208. Implement Trie (Prefix Tree) Add to List
 DescriptionHintsSubmissionsSolutions
 Total Accepted: 72101
 Total Submissions: 265482
 Difficulty: Medium
 Contributor: LeetCode
 Implement a trie with insert, search, and startsWith methods.

 Note:
 You may assume that all inputs are consist of lowercase letters a-z.
 */
public class Trie {

    public Trie[] children;

        /** Initialize your data structure here. */
        public Trie() {
            children = new Trie[27];
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            recurInsert(word, 0, this);
        }

        public void recurInsert(String word, int index, Trie node) {
            if (index == word.length()) {
                // this is the end flag
                node.children[26] = new Trie();
                return;
            }

            char ch = word.charAt(index);
            int pos = ch - 'a';
            if (node.children[pos] == null) {
                node.children[pos] = new Trie();
            }
            recurInsert(word, index + 1, node.children[pos]);
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            return recurSearch(word, 0, this);
        }
        public boolean recurSearch(String word, int index, Trie node) {
            if (index == word.length()) {
                if (node.children[26] != null) return true;
                else return false;
            }

            char ch = word.charAt(index);
            int pos = ch - 'a';
            if (node.children[pos] != null) {
                return recurSearch(word, index + 1, node.children[pos]);
            }
            else
                return false;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            return recurStartWith(prefix, 0, this);
        }

        public boolean recurStartWith(String prefix, int index, Trie node) {
            if (index == prefix.length()) {
                return true;
            }

            char ch = prefix.charAt(index);
            int pos = ch - 'a';
            if (node.children[pos] != null) {
                return recurStartWith(prefix, index + 1, node.children[pos]);
            }
            else
                return false;
        }

}
