package square;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by yingtan on 10/16/17.
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
 */
public class WordSearchII {

    public class Trie {
        Trie[] children;
        char val;
        boolean isLeaf;

        public Trie(char val) {
            children = new Trie[26];
            this.val = val;
            isLeaf = false;
        }
    }

    public void add(Trie node, String word) {
        Trie cur = node;
        for (int i = 0 ; i < word.length(); i ++) {
            char ch = word.charAt(i);
            if (cur.children[ch - 'a'] == null) {
                cur.children[ch - 'a'] = new Trie(ch);
            }
            cur = cur.children[ch - 'a'];
        }
        cur.isLeaf = true;
    }

    public boolean search(Trie node, String word) {
        Trie cur = node;
        for (int i = 0 ; i < word.length(); i ++) {
            char ch = word.charAt(i);
            if (cur.children[ch - 'a'] == null) {
                return false;
            }
            cur = cur.children[ch - 'a'];
        }
        if (cur.isLeaf) return true;
        else return false;
    }

    public boolean startsWith(Trie node, String prefix) {
        Trie cur = node;
        for (int i = 0 ; i < prefix.length(); i ++) {
            char ch = prefix.charAt(i);
            if (cur.children[ch - 'a'] == null) {
                return false;
            }
            cur = cur.children[ch - 'a'];
        }
        return true;
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<String>();
        if (board == null || board.length == 0 || words == null) return res;
        int row = board.length;
        int col = board[0].length;
        boolean[][] vis = new boolean[row][col];

        Trie root = new Trie('*');
        for (String word : words) {
            add(root, word);
        }

        HashSet<String> set = new HashSet<>();
        for (int i = 0 ; i < row; i ++) {
            for (int j = 0 ;j < col; j ++) {
                recurSearch(root, board, vis, i, j, set, "");
            }
        }
        for (String w : set) {
            res.add(w);
        }
        return res;
    }

    public void recurSearch(Trie node, char[][] board, boolean[][] vis, int i, int j, HashSet<String> set, String str) {
        if ( (i >= 0) && (i < board.length) && (j >= 0) && (j < board[0].length) ) {
            if (!vis[i][j]) {
                vis[i][j] = true;
            }
            String word = str + board[i][j];
            if (startsWith(node, word)) {
                if (search(node, word)) {
                    set.add(word);
                }

                // only when words startsWith this new word, then go further DFS
                recurSearch(node, board, vis, i + 1, j, set, word);
                recurSearch(node, board, vis, i - 1, j, set, word);
                recurSearch(node, board, vis, i, j + 1, set, word);
                recurSearch(node, board, vis, i, j - 1, set, word);
            }
            vis[i][j] = false;
        }
    }
}
