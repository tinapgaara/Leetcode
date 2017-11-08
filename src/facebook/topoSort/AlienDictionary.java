package facebook.topoSort;

import java.nio.charset.CharacterCodingException;
import java.util.*;

/**
 * Created by yingtan on 5/28/17.
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


    public String alienOrder_map(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }

        // Use map to represent a graph
        Map<Character, Set<Character>> map = buildGraph_map(words);

        LinkedList<Character> res = new LinkedList<Character>();

        topoSort_map(map, res);

        String s  = "";
        for (Character ch : res) {
            s = s + ch;
        }
        return s;
    }

    public Map<Character, Set<Character>> buildGraph_map(String[] words) {
        Map<Character, Set<Character>> map = new HashMap<Character, Set<Character>>();
        boolean[][] vis = new boolean[26][26];

        for (int i = 0 ; i < words.length - 1; i ++) {
            for (int j = i + 1 ; j < words.length; j ++) {
                char[] word1 = words[i].toCharArray();
                char[] word2 = words[j].toCharArray();
                int minLen = Math.min(word1.length,  word2.length);

                for (int k = 0 ; k < minLen ; k ++) {
                    char ch2 = word1[k];
                    char ch1 = word2[k];

                    if (ch1 != ch2) {
                        if (! vis[ch1-'a'][ch2-'a']) {
                            vis[ch1-'a'][ch2-'a'] = true;

                            if (map.containsKey(ch1)) {
                                map.get(ch1).add(ch2);
                            }
                            else {
                                Set<Character> neighbors = new HashSet<Character>();
                                neighbors.add(ch2);
                                map.put(ch1, neighbors);
                            }
                        }
                        break;
                    }
                }
            }
        }
        return map;
    }

    public void topoSort_map(Map<Character, Set<Character>> map, LinkedList<Character> res) {
        int[] color = new int[26];
        for (Map.Entry<Character, Set<Character>> entry : map.entrySet()) {
            char node = entry.getKey();
            if (color[node-'a'] == 0) {
                dfs_map(node, map, res, color);
            }
        }
    }

    public boolean dfs_map(char node, Map<Character, Set<Character>> map, LinkedList<Character> res, int[] color) {
        color[node - 'a'] = 1;
        Set<Character> neighbors = map.get(node);
        if (neighbors != null) {
            for (Character neighbor : neighbors) {
                if (color[neighbor.charValue()-'a'] == 0) {

                    if(! dfs_map(neighbor.charValue(), map, res, color))
                        // has cycle
                        return false;
                }
                else if (color[neighbor.charValue()-'a'] == 1) return false;
            }
        }

        color[node - 'a'] = 2;
        res.add(node);
        return true;
    }


    public String combineIndependentNodes(String[] words, LinkedList<Character> res) {
        String str  = "";
        for (Character ch : res) {
            str = str + ch;
        }

        for (String s : words) {
            for (int j = 0 ; j < s.length(); j ++) {
                if (!res.contains(s.charAt(j)) && (!str.contains(s.charAt(j)+""))) {
                    str  = str + s.charAt(j);
                }
            }
        }
        return str;
    }

    public static void main(String[] args)  {
        AlienDictionary ob = new AlienDictionary();
        String[] words = new String[]{"z","x"};
        ob.alienOrder(words);
    }
}
