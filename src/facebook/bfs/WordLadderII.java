package facebook.bfs;

import java.util.*;

/**
 * Created by yingtan on 3/18/18.
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
        dfs(beginWord, endWord, distance, graph, path, res);
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
            List<String> neighbors = findNeighbors(cur, wordSet);
            for (String neighbor : neighbors) {
                graph.get(cur).add(neighbor);
                if (! distance.containsKey(neighbor)) {
                    distance.put(neighbor, dist + 1);
                    queue.offer(neighbor);
                }
            }
        }
    }
    private void dfs(String destWord, String cur, Map<String, Integer> distance, Map<String, Set<String>> graph,
                     List<String> path, List<List<String>> res) {
        if (cur.equals(destWord)) {
            res.add(new ArrayList<>(path));
            return;
        }
        Set<String> neigbors = graph.get(cur);
        if (neigbors != null) {
            for (String neighbor : neigbors) {
                // make sure this is the shortest path
                if (distance.containsKey(neighbor) && distance.get(neighbor) == distance.get(cur) - 1) {
                    path.add(neighbor);
                    dfs(destWord, neighbor, distance, graph, path, res);
                    path.remove(path.size() - 1);
                }
            }
        }

    }
    private List<String> findNeighbors(String cur, Set<String> wordSet) {
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
}
