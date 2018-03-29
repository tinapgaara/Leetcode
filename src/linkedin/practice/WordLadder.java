package linkedin.practice;

import java.util.*;

/**
 * Created by yingtan on 11/22/17.
 *
 * Given:
 beginWord = "hit"
 endWord = "cog"
 wordList = ["hot","dot","dog","lot","log","cog"]
 As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 return its length 5.
 */
public class WordLadder {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>();
        for (String word : wordList) {
            wordSet.add(word);
        }
        Queue<String> wordQueue = new LinkedList<>();
        Queue<Integer> distQueue = new LinkedList<>();
        wordQueue.offer(beginWord);
        distQueue.offer(0);
        while(! wordQueue.isEmpty()) {
            String word = wordQueue.poll();
            int dist = distQueue.poll();
            if (word.equals(endWord)) {
                return dist;
            }
            for (int i = 0 ; i < word.length(); i ++) {
                char[] chs = word.toCharArray();
                for (char j = 'a'; j < 'z'; j ++) {
                    chs[i] = j;
                    String newWord = new String(chs);
                    if (wordSet.contains(newWord)) {
                        wordQueue.offer(newWord);
                        distQueue.offer(dist + 1);
                        wordSet.remove(newWord);
                    }
                }

            }
        }
        return -1;
    }
}
