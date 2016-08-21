package graph;

import java.util.*;

/**
 * Created by yingtan on 10/14/15.
 */
public class AlienDictionary {
    int mtime;

    // Attention: !!! Node only stores value and time
    public class Node {
        char val;
        int time;

        public Node(char val, int color, int time) {
            this.val = val;
            this.time = time;
        }
    }

    public Map<Character, List<Node>> buildGraph(String[] words) {
        // Attention !!!! Edges are showed as a map, with key = int
        Map<Character, List<Node>> nodes = new HashMap<Character, List<Node>>();
        boolean[][] visitedFlags = new boolean[26][26];
        for (int i = 0 ; i < words.length-1; i ++) {
            for (int j = i + 1; j < words.length; j ++) {
                int minLen = Math.min(words[i].length(), words[j].length());
                for (int k = 0 ; k < minLen; k ++) {
                    char ch1 = words[i].charAt(k);
                    char ch2 = words[j].charAt(k);
                    if (ch1 != ch2) {
                        System.out.println(ch1+ ","+ch2+ ":"+ visitedFlags[(ch1-'a')][(ch2-'a')]);
                        if (!visitedFlags[(ch1-'a')][(ch2-'a')]) {
                            if ((nodes.containsKey(ch1))) {
                                nodes.get(ch1).add(new Node(ch2, 0, 0));
                            } else if (!nodes.containsKey(ch1)) {
                                List<Node> list = new ArrayList<Node>();
                                list.add(new Node(ch2, 0, 0));
                                nodes.put(ch1, list);
                            }
                            visitedFlags[(ch1 - 'a')][(ch2 - 'a')] = true;
                        }
                        // Important !!!!; Need to break !!!
                        break;
                    }
                }
            }
        }
        return nodes;
    }

    public String topoSort(String[] words, Map<Character, List<Node>> nodes) {
        LinkedList<Character> res = new LinkedList<Character>();
        // Attention !!!! colors should be showed as an int[]
        int[] colors = new int[26];
        for (Map.Entry<Character, List<Node>> entry : nodes.entrySet()) {
            // // Attention !!!! Edges are showed as a map, with key = Character or Integer
            Node curNode = new Node(entry.getKey(), 0, 0);
            // Attention !!!! Should judge colors==0 here
            if((colors[entry.getKey()- 'a'] == 0) && !DFS(curNode, res,colors, nodes)) return "";
        }

        String s = combineIndependentNode(words, res);
        return s;
    }

    public boolean DFS(Node node, LinkedList<Character> list, int[] colors, Map<Character, List<Node>> nodes) {
        mtime ++;
        node.time = mtime;
        // Pay attention !!!!!:
        // Use map to get neighbors can be null !!!!!
        // When it is the last node.
        List<Node> neighbors = nodes.get(node.val);
        colors[node.val-'a'] = 1; //1: grey, null: unvisited; 2:black
        if (neighbors != null) {
            for (Node neighbor : neighbors) {
                if (colors[neighbor.val - 'a'] == 0) { // unvisited
                    // Pay attention !!! Need to judge false here !!!!
                    if (!DFS(neighbor, list, colors, nodes)) return false;
                } else if (colors[neighbor.val - 'a'] == 1)
                    return false;
            }
        }
        mtime ++;
        node.time = mtime;
        colors[node.val - 'a'] = 2; // black;
        list.addFirst(node.val);

        return true;
    }

    public String combineIndependentNode(String[] words, LinkedList<Character> res) {

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
        AlienDictionary ob = new AlienDictionary();
        String[] words = new String[]{"zy","zx"};
        ob.alienOrder(words);
    }
}
