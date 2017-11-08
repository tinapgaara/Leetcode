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
public class AddSearchWord {

    public Trie dict;

    public class Trie {
        public Trie[] children;

        public Trie() {
            // if 26th is not null, which means this is the end
            children = new Trie[27];
        }
    }

    /** Initialize your data structure here. */
    public AddSearchWord() {
        dict = new Trie();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        recurAdd(word, dict, 0);
    }

    public void recurAdd(String word, Trie node, int index) {
        if (word == null) return;
        // last word
        if (index >= word.length()) {
            node.children[26] = new Trie();
            return;}

        char ch = word.charAt(index);
        int pos = ch - 'a';
        if (node.children[pos] == null) {
            node.children[pos] = new Trie();
        }
        recurAdd(word, node.children[pos], index + 1);
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return recurSearch(word, 0, dict);
    }

    public boolean recurSearch(String word, int index, Trie node) {
        if (index >= word.length()) {
            if (node.children[26] != null) {
                // this is the last node
                return true;
            }
            else
                return false;
        }
        char ch = word.charAt(index);
        if (ch == '.') {
            for (int i = 0 ; i < 27; i ++) {
                if (node.children[i] != null) {
                    if (recurSearch(word, index + 1, node.children[i]))
                        return true;
                }
            }
            return false;
        }
        else {
            int pos = ch - 'a';
            if (node.children[pos] != null) {
                return recurSearch(word, index + 1, node.children[pos]);
            }
            else {
                return false;
            }
        }
    }

    /**
     * Your WordDictionary object will be instantiated and called as such:
     * WordDictionary obj = new WordDictionary();
     * obj.addWord(word);
     * boolean param_2 = obj.search(word);
     *
     *
     * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
     [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
     */
}
