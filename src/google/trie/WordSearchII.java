package google.trie;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 11/7/15.
 */
/*
* Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell,
where "adjacent" cells are those horizontally or vertically neighboring.
The same letter cell may not be used more than once in a word.

For example,
Given words = ["oath","pea","eat","rain"] and board =

[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
Return ["eat","oath"].
*
* */
public class WordSearchII {

/*
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        if (board == null) return res;
        int row = board.length;
        if (row == 0) return res;
        int col = board[0].length;

        // build Trie. Important !!!
        bloomberg.Impl.Trie trie = new Trie();
        for (String word: words) {
            trie.insert(word); // important !!
        }

        for (int i = 0 ; i < row; i ++) {
            for (int j = 0 ; j < col; j ++) {
                DFS(board, i, j, "", 0, res, trie);
            }
        }
        return res;
    }

    public void DFS(char[][] board, int i, int j, String word, int index, List<String> list, Trie trie) {
        if (index > word.length() - 1) return;
        char ch = word.charAt(index);

        if ((i >= 0) && (i < board.length) && (j >= 0) && (j < board[0].length)) {
            if (ch == board[i][j]) {
                String subword = word + board[i][j];
                if (trie.startsWith(subword)) { // important !!!
                    if (trie.search(subword)) { // important !!!!
                        list.add(subword);
                        return;
                    }
                    char tmp = ch;
                    board[i][j] = '#';//mark as visited
                    DFS(board, i + 1, j, word, index + 1, list, trie);
                    DFS(board, i, j + 1, word, index + 1, list, trie);
                    DFS(board, i - 1, j, word, index + 1, list, trie);
                    DFS(board, i, j - 1, word, index + 1, list, trie);

                    board[i][j] = tmp;//recover
                }
            }
        }
    }
    */
}
