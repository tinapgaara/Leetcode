package google.trie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 7/22/17.
 *
 * 425. Word Squares
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a set of words (without duplicates), find all word squares you can build from them.

 A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).

 For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.

 b a l l
 a r e a
 l e a d
 l a d y
 Note:
 There are at least 1 and at most 1000 words.
 All words will have the exact same length.
 Word length is at least 1 and at most 5.
 Each word contains only lowercase English alphabet a-z.
 Example 1:

 Input:
 ["area","lead","wall","lady","ball"]

 Output:
 [
 [ "wall",
 "area",
 "lead",
 "lady"
 ],
 [ "ball",
 "area",
 "lead",
 "lady"
 ]
 ]

 Explanation:
 The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
 Example 2:

 Input:
 ["abat","baba","atan","atal"]

 Output:
 [
 [ "baba",
 "abat",
 "baba",
 "atan"
 ],
 [ "baba",
 "abat",
 "baba",
 "atal"
 ]
 ]

 Explanation:
 The output consists of two word squares. The order of output does not matter (just the order of words in each word
 */
public class WordSquares {

    public class Trie {
        public Trie[] children;
        public List<String> startWiths;

        public Trie() {
            children = new Trie[27]; // include 0-25 ch and 1 end symbol
            startWiths = new ArrayList<String>();
        }

        public void contructTrie(String[] words) {
            for (String word : words) {
                Trie curNode = this;
                for (int i = 0 ; i < word.length(); i ++) {
                    char ch = word.charAt(i);
                    if (curNode.children[ch - 'a'] == null) {
                        // insert
                        curNode.children[ch - 'a'] = new Trie();
                    }
                    curNode.startWiths.add(word);
                    curNode = curNode.children[ch - 'a'];
                }
                // important
                curNode.children[26] = new Trie();
            }
        }

        public List<String> searchPrefix(String prefix) {
            Trie curNode = this;
            for (int i = 0 ; i < prefix.length(); i ++) {
                char ch = prefix.charAt(i);
                if (curNode.children[ch - 'a'] == null) {
                    // not match
                    return new ArrayList<String>();
                }
                curNode = curNode.children[ch - 'a'];
            }
            return curNode.startWiths;
        }

    }
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> res = new ArrayList<List<String>>();
        if (words == null || words.length == 0) return res;
        int len = words[0].length();

        Trie trie = new Trie();
        trie.contructTrie(words);
        List<String> list = new ArrayList<String>();

        for (String firstCandidate : words) {
            // put the word in list.index row
            list.add(firstCandidate);
            recurFill(trie, list, res);
            // next loop, reset the list
            list.remove(list.size() - 1);
        }
        return res;

    }
    /*
    b a b a       b a b a     b a b a
    a             a b a t     a b a t
    b             b a         b a b a
    a             a t         a t a
    match a       match ba    match ata


    */
    public void recurFill(Trie trie, List<String> list, List<List<String>> res) {
        if (list.size() == list.get(0).length()) {
            // fill the whole matrix
            res.add(new ArrayList<String>(list));
            return;
        }
        // construct current constriants: prefix
        int curFilledRow = list.size();
        String prefix = "";
        for (String str : list) {
            prefix = prefix + str.charAt(curFilledRow);
        }
        List<String> startWith = trie.searchPrefix(prefix);
        for (String nextCandidate : startWith) {
            list.add(nextCandidate);
            recurFill(trie, list, res);
            list.remove(list.size() - 1);
        }
    }
}
