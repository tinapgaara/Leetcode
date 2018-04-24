package lyft.bfs;

/**
 * Created by yingtan on 4/11/18.
 */
import java.util.*;
public class WordLadderII {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        // distance from current node to startWord
        Map<String, Integer> distance = new HashMap<>();
        Map<String, Set<String>> graph = new HashMap<>();
        List<List<String>> res = new ArrayList<>();
        Set<String> wordSet = new HashSet<>();
        for (String word : wordList) {
            wordSet.add(word);
        }
        bfs(graph, beginWord, endWord, distance, wordSet);
        List<String> path = new ArrayList<>();
        path.add(endWord);
        dfs(beginWord, endWord, graph, distance, path, res);
        return res;
    }
    private void bfs(Map<String, Set<String>> graph, String beginWord, String endWord,
                     Map<String, Integer> distance, Set<String> wordSet) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        distance.put(beginWord, 0);
        for (String word : wordSet) {
            graph.put(word, new HashSet<>());
        }
        while(! queue.isEmpty()) {
            String word = queue.poll();
            int dist = distance.get(word);
            List<String> neighbors = findNeighbors(word, wordSet);
            for (String neighbor : neighbors) {
                graph.get(neighbor).add(word); // important !!!
                if (! distance.containsKey(neighbor)) {
                    queue.offer(neighbor);
                    distance.put(neighbor, dist + 1);
                }
            }
        }
    }
    private void dfs(String cur, String destWord, Map<String, Set<String>> graph, Map<String, Integer> distance,
                     List<String> path, List<List<String>> res) {
        if (cur.equals(destWord)) {
            List<String> copyPath = new ArrayList<>(path);
            Collections.reverse(copyPath);
            res.add(new ArrayList<>(copyPath));
            return;
        }
        Set<String> neigbors = graph.get(cur);
        //int dist = distance.get(cur); sometimes can be null here
        if (neigbors != null) {
            for (String neighbor : neigbors) {
                if (distance.containsKey(neighbor) && distance.get(neighbor) == distance.get(cur) - 1) {
                    path.add(neighbor);
                    dfs(neighbor, destWord, graph, distance, path, res);
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
