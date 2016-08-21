package google.graph;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yingtan on 11/6/15.
 */
/*
* There is a new alien language which uses the latin alphabet.
* However, the order among letters are unknown to you. You receive a list of words from the dictionary,
* where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

For example,
Given the following words in dictionary,

[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
The correct order is: "wertf".
*
* */
public class AllenDictionary {

    int mtime;
    // Attention: !!! Node only stores value and time
    public class Node {
        char val;
        int time;

        public Node(char val, int time) {
            this.val = val;
            this.time = time;
        }
    }

    public Map<Character, List<Node>> buildGraph(String[] words) {
        Map<Character, List<Node>> nodes = new HashMap<Character, List<Node>>();
        boolean[][] visitedFlags = new boolean[26][26];

        for (int i = 0 ; i < words.length ; i ++) {
            for (int j = i + 1; j < words.length; j ++) {
                String word1 = words[i];
                String word2 = words[j];

                int minLen = Math.min(word1.length(),word2.length());

                for (int k = 0 ; k < minLen; k ++) {
                    char ch1 = word1.charAt(k);
                    char ch2 = word2.charAt(k);

                    if (ch1 != ch2) {
                        if (!visitedFlags[ch1-'a'][ch2-'a']) {
                            if (nodes.containsKey(ch1)) {
                                nodes.get(ch1).add(new Node(ch2, 0));
                            }
                            else {
                                List<Node> newlist = new ArrayList<>();
                                newlist.add(new Node(ch2,0));
                                nodes.put(ch1, newlist);
                            }
                            visitedFlags[ch1-'a'][ch2-'a'] = true;
                        }
                        break;
                    }
                }
            }
        }
        return nodes;
    }

    public String topoSort(String[] words, Map<Character, List<Node>> nodes) {
        int[] colors = new int[26];
        List<Character> res = new ArrayList<>();
        for (Map.Entry<Character, List<Node>> node : nodes.entrySet()) {
            if (colors[node.getKey() - 'a'] == 0)
                if(!DFS(node.getKey(), nodes, colors, res)) return ""; // need to return immediately
        }

        String s = combineIndependentNode(words, res);
        return s;
    }

    public boolean DFS(char curNode, Map<Character, List<Node>> nodes, int[] colors, List<Character> res) {
        colors[curNode - 'a'] = 1; // grey
        List<Node> neighbors = nodes.get(curNode);
        if (neighbors != null) {
            for (Node neighbor : neighbors) {
                if (colors[neighbor.val - 'a'] == 0) {
                    // need to return false immediately
                    if (!DFS(neighbor.val, nodes, colors, res)) return false;
                }
                else if (colors[neighbor.val - 'a'] == 1) {
                    return false; // return immediately
                }
            }
        }
        colors[curNode - 'a'] = 2;
        res.add(0, curNode);
        return true;
    }

    public String combineIndependentNode(String[] words, List<Character> res) {
        String str = "";
        for (int i = 0 ; i < res.size() ; i ++) {
            str = str + res.get(i);
        }
        for (int i = 0 ; i < words.length; i ++) {
            String s = words[i];
            for (int j = 0; j < s.length(); j ++) {
                if (!res.contains(s.charAt(j)) && (!str.contains(s.charAt(j)+""))) {
                    str  = str + s.charAt(j);
                }
            }
        }
        return str;
    }

    public String alienOrder(String[] words) {
        Map<Character, List<Node>> graph = buildGraph(words);
        return topoSort(words, graph);
    }

    public static void main(String[] args) {
        AllenDictionary ob = new AllenDictionary();
        String[] words = new String[]{"a","b", "a"};
        ob.alienOrder(words);
    }


}
