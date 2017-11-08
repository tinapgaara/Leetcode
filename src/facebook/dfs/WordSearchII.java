package facebook.dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by yingtan on 5/20/17.
 *
 * 212. Word Search II
 *
 * Given a 2D board and a list of words from the dictionary, find all words in the board.

 Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

 For example,
 Given words = ["oath","pea","eat","rain"] and board =

 [
 ['o','a','a','n'],
 ['e','t','a','e'],
 ['i','h','k','r'],
 ['i','f','l','v']
 ]
 Return ["eat","oath"].



 click to show hint.

 You would need to optimize your backtracking to pass the larger test. Could you stop backtracking earlier?

 If the current candidate does not exist in all words' prefix, you could stop backtracking immediately. What kind of data structure could answer such query efficiently? Does a hash table work? Why or why not? How about a Trie? If you would like to learn how to implement a basic trie, please work on this problem: Implement Trie (Prefix Tree) first.

 */
public class WordSearchII {

    // construct a trie using array of words, and when we dfs board, we check if the existing candidate from dfs are in trie
    //  instead of compare each ch one by one in recursion

    // everytime, when saw: You may assume that all inputs are consist of lowercase letters a-z. means Trie !!!!
    public class Trie {
        public Trie[] children;

        public Trie() {
            children = new Trie[27];
        }
    }

    public void add(Trie node, String word, int index) {
        if (index >= word.length()) {
            node.children[26] = new Trie();
            return;
        }

        char ch = word.charAt(index);
        int pos = ch - 'a';
        if (node.children[pos] == null) {
            node.children[pos] = new Trie();
        }
        add(node.children[pos], word, index + 1);
    }

    public boolean search(Trie node, String word, int index) {
        if (index >= word.length()) {
            if (node.children[26] != null) return true;
            else return false;
        }

        char ch = word.charAt(index);
        int pos = ch - 'a';
        if (node.children[pos] == null) {
            return false;
        }
        else {
            return search(node.children[pos], word, index + 1);
        }
    }

    public boolean startsWith(Trie node, String word, int index) {
        if (index >= word.length()) return true;

        char ch = word.charAt(index);
        int pos = ch - 'a';
        if (node.children[pos] == null) return false;
        else {
            return startsWith(node.children[pos], word, index + 1);
        }
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<String>();
        if (board == null || board.length == 0 || words == null) return res;

        int row = board.length;
        int col = board[0].length;

        // create Trie
        Trie trie = new Trie();
        for (int i = 0 ; i < words.length; i ++) {
            add(trie, words[i], 0);
        }

        boolean[][] vis = new boolean[row][col];
        // Need to use hashset, instead of arraylist
        HashSet<String> set = new HashSet<String>();
        for (int i = 0 ; i < row ; i ++) {
            for (int j = 0 ; j < col; j ++) {
                recurSearch(trie, board, i, j, vis, "", set);
            }
        }

        for (String w : set) {
            res.add(w);
        }
        return res;
    }

    public void recurSearch(Trie node, char[][] board, int i, int j, boolean[][] vis, String str, HashSet<String> set) {
        if ( (i >= 0) && (i < board.length) && (j >= 0) && (j < board[0].length) ) {
            if (! vis[i][j]) {
                vis[i][j] = true;

                String newWord = str + board[i][j];
                if (startsWith(node, newWord, 0)) {
                    if (search(node, newWord, 0)) {
                        if (! set.contains(newWord)) { // Important !!!
                            set.add(newWord);
                        }
                    }
                    // only when words startsWith this new word, then go further DFS
                    recurSearch(node, board, i+1, j, vis, newWord, set);
                    recurSearch(node, board, i-1, j, vis, newWord, set);
                    recurSearch(node, board, i, j+1, vis, newWord, set);
                    recurSearch(node, board, i, j-1, vis, newWord, set);
                }
                vis[i][j] = false;
            }
        }
    }
}
