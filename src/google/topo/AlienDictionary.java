package google.topo;

import java.util.Arrays;

/**
 * Created by yingtan on 8/6/17.
 */
public class AlienDictionary {

    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        // represent a graph: using a int[] color and boolean[][] adj
        int[] color = new int[26];
        boolean[][] adj = buildGraph(words, color);

        StringBuilder res = new StringBuilder();

        return topoSort(adj, color, res, words);
    }

    public boolean[][] buildGraph(String[] words, int[] color) {

        Arrays.fill(color, -1);
        boolean[][] adj = new boolean[26][26];

        for (int i = 0 ; i < words.length; i ++) {
            char[] word1 = words[i].toCharArray();
            // Important !!!
            // Mark each word appeared in words as 0 in color for future topo sort, for boundary case : ["we", "we"]
            for (char ch : word1) color[ch - 'a'] = 0;

            for (int j = i + 1 ; j < words.length; j ++) {
                char[] word2 = words[j].toCharArray();

                int minLen = Math.min(word1.length,  word2.length);

                for (int k = 0 ; k < minLen ; k ++) {
                    char ch2 = word1[k];
                    char ch1 = word2[k];

                    if (ch1 != ch2) {
                        if (! adj[ch1-'a'][ch2-'a']) {
                            adj[ch1-'a'][ch2-'a'] = true;
                        }
                        break;
                    }
                }
            }
        }
        return adj;
    }

    public String topoSort(boolean[][] adj, int[] color, StringBuilder res, String[] words) {
        for (int i = 0 ; i < 26 ; i ++) { // 0->a
            if (color[i] == 0) {
                // Important !! need to return "" immediately here for cycle
                if (! dfs(i, adj, res, color)) return "";
            }
        }

        return res.toString();
    }

    public boolean dfs(int i, boolean[][] adj, StringBuilder res, int[] color) {
        color[i] = 1;
        for (int j = 0 ; j < 26; j ++) {
            if (adj[i][j]) { // i has edge to j
                if (color[j] == 1) return false; // cycle
                else if (color[j] == 0) {
                    if (! dfs(j, adj, res, color)) return false;
                }
            }
        }
        color[i] = 2;
        res.append((char)(i + 'a'));
        return true;
    }
}
