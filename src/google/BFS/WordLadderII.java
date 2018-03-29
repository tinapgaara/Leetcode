package google.BFS;

import graph.Node;

import java.util.*;

/**
 * Created by yingtan on 11/8/15.
 *
 * 126. Word Ladder II
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:

 Only one letter can be changed at a time
 Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 For example,

 Given:
 beginWord = "hit"
 endWord = "cog"
 wordList = ["hot","dot","dog","lot","log","cog"]

 */
public class WordLadderII {

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        // distance from current node to startWord
        Map<String, Integer> distance = new HashMap<>();
        Map<String, Set<String>> graph = new HashMap<>();
        List<List<String>> res = new ArrayList<>();
        Set<String> wordSet = new HashSet<>();
        for(String word : wordList) {
            wordSet.add(word);
        }
        bfs(graph, beginWord, endWord, distance, wordSet);
        List<String> path = new ArrayList<>();
        path.add(endWord);
        dfs2(beginWord, endWord, distance, graph, path, res);
        return res;
    }
    // build a graph starting with beginWord, ends with endWord, with shortest path bewteen these two words
    private void bfs(Map<String, Set<String>> graph, String beginWord, String endWord,
                     Map<String, Integer> distance, Set<String> wordSet) {
        Queue<String> queue = new LinkedList<String>();
        queue.offer(beginWord);
        distance.put(beginWord, 0);
        // init graph
        for (String s: wordSet) {
            graph.put(s, new HashSet<String>());
        }
        while(! queue.isEmpty()) {
            String cur = queue.poll();
            int dist = distance.get(cur);
            /* can not judge equals endWord here
             be may have multiple node points to endWord

            if (cur.equals(endWord)) {
                // record the shortest distance from startword t endword
                // if calculated before, then, this is must a longer path, no need to record
                if (! distance.containsKey(endWord) || distance.get(endWord) == dist) {
                    distance.put(endWord, dist);
                    graph.get(endWord).add(cur);
                }
                continue;
            }
            */
            List<String> neighbors = findNext(cur, wordSet);
            for (String neighbor : neighbors) {
                // important !!! must put this before judge vist or not
                // no matter what, create the edge
                // for
                // 1 <-- 2
                // 1 <-- 3
                graph.get(neighbor).add(cur);
                // not visited before, then we add record dist and put that to queue
                if(! distance.containsKey(neighbor)) {

                    distance.put(neighbor, dist + 1);
                    queue.offer(neighbor);
                }
            }
        }
    }
    private void dfs2(String destWord, String cur, Map<String, Integer> distance, Map<String, Set<String>> graph,
                      List<String> path, List<List<String>> res) {
        if (cur.equals(destWord)) {
            List<String> copyPath = new ArrayList<>(path);
            Collections.reverse(copyPath);
            res.add(copyPath);
            return;
        }
        Set<String> neighbors = graph.get(cur);
        if (neighbors != null) {
            for (String neighbor : neighbors) {
                // make sure this is the shortest path
                if (distance.containsKey(neighbor) && distance.get(neighbor) + 1 == distance.get(cur)) {
                    path.add(neighbor);
                    dfs2(destWord, neighbor, distance, graph, path, res);
                    path.remove(path.size() - 1);
                }
            }
        }
    }

    private List<String> findNext(String cur, Set<String> wordSet) {
        List<String> res = new ArrayList<String>();
        for (int i = 0 ; i < cur.length(); i ++) {
            char[] chs = cur.toCharArray();
            char ch = chs[i];
            for (int j = 'a'; j <= 'z'; j++) {
                if (chs[i] != (char)j) {
                    chs[i] = (char)j;
                    String newWord = new String(chs);
                    if (wordSet.contains(newWord)) {
                        res.add(newWord);
                    }
                    // revert back
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
        List<String> wordss = new ArrayList<>(Arrays.asList("hot","dot","dog","lot","log","cog"));
        System.out.println(ob.findLadders(begin, end, wordss));
        System.out.println(ob.findLadders(begin, end, wordss));
    }

}
