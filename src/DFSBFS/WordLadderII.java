package DFSBFS;

import java.util.*;

/**
 * Created by yingtan on 10/7/15.
 */
/*
* Leetcode: WordLadderII
*
* Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:

    Only one letter can be changed at a time
    Each intermediate word must exist in the word list
    For example,

    Given:
    beginWord = "hit"
    endWord = "cog"
    wordList = ["hot","dot","dog","lot","log"]
    Return
  [
    ["hit","hot","dot","dog","cog"],
    ["hit","hot","lot","log","cog"]
  ]
* */
public class WordLadderII {


// IMportant !!!1 : How to keep a level information in queue : use prevLevel and cureDist

    // BFS : how to record the level's information
    // When one level (s.t. dist all == 3) finished:
    // 1) delete all visitedNodes   2) update level No
    // 3) when reach endWord, use PI pointer to form result list.
    //    Can not return when find one,
    //    Need to go through all this levels' words to add other result lists.
    // 4) When using PI pointer to form list, can not ensure the shortest size of list (not like BFS)
    //    Need to keep minDist to judge if this list's size() is <= minDist. If so, need to add to result

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<List<String>>();
        if (beginWord == null || endWord == null || wordList.size() == 0) {
            return res;
        }
        Map<String, Integer> distance = new HashMap<>();
        Map<String, Set<String>> graph = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        Set<String> wordSet = new HashSet<>();

        for (String word : wordList) {
            wordSet.add(word);
        }
        bfs(beginWord, endWord, distance, graph, queue, wordSet);
        List<String> path = new ArrayList<>();
        path.add(endWord);
        dfs(beginWord, endWord, distance, graph, path, res);
        return res;
    }
    public void bfs(String beginWord, String endWord, Map<String, Integer> distance, Map<String, Set<String>> graph, Queue<String> queue,  Set<String> wordSet) {
        queue.offer(beginWord);
        distance.put(beginWord, 0);
        for (String word :wordSet) {
            graph.put(word, new HashSet<>());
        }
        graph.put(beginWord, new HashSet<>());
        while(! queue.isEmpty()) {
            String word = queue.poll();
            int dist = distance.get(word);
            boolean reachEnd = false;
            for (int i = 0 ; i < word.length(); i ++) {
                for (char ch = 'a'; ch <= 'z'; ch ++) {
                    char[] chs = word.toCharArray();
                    chs[i] = ch;
                    String newWord = new String(chs);
                    // need to do this
                    if (! newWord.equals(word) && wordSet.contains(newWord)) {
                        graph.get(newWord).add(word);
                        if (! distance.containsKey(newWord)) {
                            distance.put(newWord, dist + 1);
                            queue.offer(newWord);
                        }
                    }
                    if (newWord.equals(endWord)) {
                        reachEnd = true;
                    }
                }
                if (reachEnd) {
                    // word -> endWord, no need to try other positions in word
                    break;
                }
            }
        }
    }
    public void dfs(String beginWord, String curWord, Map<String, Integer> distance, Map<String, Set<String>> graph, List<String> path, List<List<String>> res) {
        if (curWord.equals(beginWord)) {
            List<String> copyPath = new ArrayList<>(path);
            Collections.reverse(copyPath);
            res.add(new ArrayList<>(copyPath));
            return;
        }
        if (! graph.containsKey(curWord)) {
            return;
        }
        for (String neighbor : graph.get(curWord)) {
            if (distance.get(neighbor) + 1 == distance.get(curWord)) {
                // go neighbor the next time
                path.add(neighbor);
                dfs(beginWord, neighbor, distance, graph, path, res);
                path.remove(path.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        WordLadderII ob = new WordLadderII();
        String begin = "hit";
        String end = "cog";
        List<String> word = new ArrayList<>(Arrays.asList("hot","dot","dog","lot","log"));

        System.out.println(ob.findLadders(begin, end, word));
    }
}
