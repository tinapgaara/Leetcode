package google.BFS;

import graph.Node;

import java.util.*;

/**
 * Created by yingtan on 11/8/15.
 */
public class WordLadderII {

    // jiuzhang algor
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        // distance from current node to startWord
        Map<String, Integer> distance = new HashMap<String, Integer>();
        Map<String, List<String>> graph = new HashMap<String, List<String>>();
        List<List<String>> res = new ArrayList<List<String>>();
        if (! wordList.contains(endWord)) return res;
        wordList.add(beginWord);
        wordList.add(endWord);

        bfs(beginWord, endWord, wordList, distance, graph);

        List<String> path = new ArrayList<String>();
        path.add(endWord);
        dfs(endWord, beginWord, distance, graph, path, res);
        return res;
    }
    private void bfs(String beginWord, String endWord, List<String> wordList,
                     Map<String, Integer> distance, Map<String, List<String>> graph) {
        Queue<String> queue = new LinkedList<String>();
        queue.offer(beginWord);
        distance.put(beginWord, 0);
        for (String s: wordList) {
            graph.put(s, new ArrayList<String>());
        }
        while(! queue.isEmpty()) {
            String cur = queue.poll();
            int dist = distance.get(cur);

            List<String> neighbors = expand(cur, wordList);
            for (String neighbor : neighbors) {
                graph.get(neighbor).add(cur);
                // important !!! if not vis
                // if vis, then this is re-vis, and this is must have longer distance
                if(! distance.containsKey(neighbor)) {
                    distance.put(neighbor, dist + 1);
                    queue.offer(neighbor);
                }
            }
        }
    }

    private void dfs(String cur, String startWord, Map<String, Integer> distance, Map<String, List<String>> graph,
                     List<String> path, List<List<String>> res) {
        // base case, stop sign
        if (cur.equals(startWord)) {
            Collections.reverse(path);
            res.add(new ArrayList<String>(path));
            Collections.reverse(path);
        }
        else {
            for (String neighbor : graph.get(cur)) {
                // the current word's dist to the start point is stored in distance, if the current word's dist to start point
                // is 5,then, we need to dfs the word whose dist to the start point is 4 ( 5 - 1)
                if (distance.containsKey(cur) && distance.get(cur) - 1 == distance.get(neighbor)) {
                    path.add(neighbor);
                    dfs(neighbor, startWord, distance, graph, path, res);
                    path.remove(path.size() - 1);
                }
            }
        }
    }
    private List<String> expand(String cur, List<String> wordList) {
        List<String> res = new ArrayList<String>();
        for (int i = 0 ; i < cur.length(); i ++) {
            char[] chs = cur.toCharArray();
            char ch = chs[i];
            for (int j = 'a'; j <= 'z'; j++) {
                if (chs[i] != (char)j) {
                    chs[i] = (char)j;
                    String newWord = new String(chs);
                    if (wordList.contains(newWord)) {
                        res.add(newWord);
                    }
                    chs[i] = ch;
                }
            }
        }
        return res;
    }


    public class Node {
        String mNodeStr;
        int mDist;
        Node mPi;
        int mColor;

        public Node(String nodestr, int dist, Node pi, int color) {
            mNodeStr = nodestr;
            mDist = dist;
            mPi = pi;
            mColor = color;
        }
    }


    public List<List<String>> findLadders(String beginWord, String endWord,   Set<String> wordList) {
        Queue<Node> queue = new LinkedList<>();
        List<List<String>> res = new ArrayList<List<String>>();
        queue.offer(new Node(beginWord, 1, null, 1)); // color = grey

        int prevLevel = 0;
        int minDist = Integer.MAX_VALUE;
        Set<String> curLevelVisited = new HashSet<>();

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            String word = cur.mNodeStr; // hit
            int dist = cur.mDist;
            if (dist != prevLevel) {
                wordList.removeAll(curLevelVisited);
                prevLevel = dist;
                curLevelVisited.clear();
            }
            if (word.equals(endWord)) {
                if (dist <= minDist) { // must be <=
                    minDist = dist;
                    constructResList(res, cur);
                }
                continue;
            }
            for (int i = 0 ; i < word.length(); i ++) {
                for (int j = 0 ; j < 26; j ++) {
                    char[] chs = word.toCharArray();
                    char newch = (char)(j + 'a');
                    chs[i] = newch;
                    String newword = new String(chs);
                    if (wordList.contains(newword)) {
                        curLevelVisited.add(newword);
                        queue.offer(new Node(newword, dist+1, cur, 1));
                    }
                }
            }
            cur.mColor = 2;
        }
        return res;
    }

    public void constructResList(List<List<String>> res, Node node) {
        List<String> level = new ArrayList<String>();

        while(node != null) {
            level.add(0, node.mNodeStr);
            node = node.mPi;
        }
        res.add(level);
    }

    public static void main(String[] args) {
        WordLadderII ob = new WordLadderII();
        String begin = "hit";
        String end = "cog";
        Set<String> word = new HashSet<>(Arrays.asList("ted","tex","red","tax","tad","den","rex","pee"));
        List<String> words = new ArrayList<>(Arrays.asList("hot","dot","dog","lot","log"));

        System.out.println(ob.findLadders(begin, end, words));
    }

}
