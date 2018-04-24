package google.trie;

/**
 * Created by yingtan on 4/18/18.
 *
 * 676. Implement Magic Dictionary
 DescriptionHintsSubmissionsDiscussSolution
 Implement a magic directory with buildDict, and search methods.

 For the method buildDict, you'll be given a list of non-repetitive words to build a dictionary.

 For the method search, you'll be given a word, and judge whether if you modify exactly one character into another character in this word, the modified word is in the dictionary you just built.

 Example 1:
 Input: buildDict(["hello", "leetcode"]), Output: Null
 Input: search("hello"), Output: False
 Input: search("hhllo"), Output: True
 Input: search("hell"), Output: False
 Input: search("leetcoded"), Output: False
 Note:
 You may assume that all the inputs are consist of lowercase letters a-z.
 For contest purpose, the test data is rather small by now. You could think about highly efficient algorithm after the contest.
 Please remember to RESET your class variables declared in class MagicDictionary, as static/class variables are persisted across multiple test cases. Please see here for more details.
 */
public class ImplementMagicDictionary {
    public class Trie {
        Trie[] children;
        char ch;
        boolean isEnd;
        public Trie(char ch) {
            children = new Trie[26];
            this.ch = ch;
        }
    }
    Trie root;
    public ImplementMagicDictionary() {
        root = new Trie('#');
    }

    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        for (String word : dict) {
            // do insert
            insert(word);
        }
    }
    public void insert(String word) {
        Trie cur = root;
        for (char ch : word.toCharArray()) {
            if (cur.children[ch - 'a'] == null) {
                cur.children[ch - 'a'] = new Trie(ch);
            }
            cur = cur.children[ch - 'a'];
        }
        cur.isEnd = true;
    }
    public boolean search(String word) {
        Trie cur = root;
        for (int i = 0 ; i < word.length(); i ++) {
            char ch = word.charAt(i);
            // try to change it to other chs
            for (char newch = 'a'; newch <= 'z'; newch ++) {
                if (cur.children[newch - 'a'] != null && newch != ch) {
                    if (recurSearch(word, i+1, cur.children[newch - 'a'])) {
                        return true;
                    }
                }
            }
            cur = cur.children[ch - 'a']; // can match
            if (cur == null) return false; // very important !!!! if not match and can't return true in previous search
        }
        return false;
    }
    public boolean recurSearch(String word, int index, Trie cur) {
        if (index >= word.length()) {
            if (cur.isEnd) return true;
            else return false;
        }
        char ch = word.charAt(index);
        if (cur.children[ch - 'a'] != null) {
            return recurSearch(word, index + 1, cur.children[ch- 'a']);
        }
        else {
            return false;
        }
    }
}
