package google.DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 11/9/15.
 */
public class WordSearchII {

    public static class TrieNode {
        public char ch;
        public boolean isStop;
        public TrieNode[] neighbors;

        public TrieNode(char ch) {
            this.ch = ch;
            neighbors = new TrieNode[27];
        }
    }

    public void insert(String word, int index, TrieNode cur) {
        if (index >= word.length()) {
            TrieNode end = new TrieNode('#');
            end.isStop = true;
            cur.neighbors[26] = end;
            return;
        }
        int pos = word.charAt(index) - 'a';
        TrieNode insertNode = cur.neighbors[pos];
        if (insertNode == null) {
            TrieNode curNode = new TrieNode(word.charAt(index));
            cur.neighbors[pos] = curNode;
        }
        insert(word, index+1, cur.neighbors[pos]);
    }

    public boolean search(String word, int index, TrieNode cur) {
        if (index >= word.length()) {
            TrieNode end = cur.neighbors[26];
            if ((end != null) && (end.isStop) )
                return true;
            else
                return false;
        }
        char ch = word.charAt(index);
        int pos = ch - 'a';
        if (cur.neighbors[pos] != null) {
            return search(word, index+1, cur.neighbors[pos]);
        }
        return false;
    }

    public boolean startsWith(String word, int index, TrieNode cur) {
        if (index >= word.length()) {
            return true;
        }
        char ch = word.charAt(index);
        int pos = ch-'a';
        if (cur.neighbors[pos] != null) {
            return startsWith(word, index +1, cur.neighbors[pos]);
        }
        return false;
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<String>();
        if ((board == null) || (words == null) || (words.length == 0))
            return res;
        int row = board.length;
        if (row == 0) return res;
        int col = board[0].length;

        TrieNode root = new TrieNode('$');// start
        for (int i = 0 ; i < words.length; i ++) {
            String word = words[i];
            insert(word, 0, root);
        }

        boolean[][] visitedFlags = new boolean[row][col];
        for (int i = 0; i < row; i ++) {
            for (int j = 0 ; j < col; j ++) {
                DFS(board, root, visitedFlags, i, j, "", res);
            }
        }
        return res;
    }

    public void DFS(char[][] board, TrieNode root, boolean[][] visitedFlags,
                    int x, int y, String str, List<String> res) {
        if ((x >= 0) && (x < board.length) && (y >= 0) && (y < board[0].length)) {
            if (!visitedFlags[x][y]) {

                String newstr = str + board[x][y];
                if (startsWith(newstr, 0, root)) {
                    visitedFlags[x][y] = true;
                    if (search(newstr, 0, root)) {
                        if (!res.contains(newstr))
                            res.add(newstr);
                        //return;
                    }
                    DFS(board, root, visitedFlags, x+1, y, newstr, res);
                    DFS(board, root, visitedFlags, x, y+1, newstr, res);
                    DFS(board, root, visitedFlags, x-1, y, newstr, res);
                    DFS(board, root, visitedFlags, x, y-1, newstr, res);
                    visitedFlags[x][y] = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        WordSearchII ob = new WordSearchII();
        TrieNode root = new TrieNode('$');
        ob.insert("oath", 0, root);
        ob.insert("hello", 0, root);

        // char[][] board = new char[][]{{'o','a','a','n'}, {'e','t','a','e'}, {'i','h','k','r'}, {'i','f','l','v'}};
        char[][] board = new char[][]{{'a','b', 'c'}, {'a','e', 'd'}, {'a', 'f', 'g'}};
        String[] words = new String[]{"abcdefg","gfedcbaaa","eaabcdgfa","befa","dgc","ade"};
        System.out.println(ob.startsWith("oa", 0, root));
        System.out.println(ob.findWords(board, words));
    }
}
