package lyft.bfs;
import java.util.*;
public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord == null || beginWord.length() == 0 || endWord == null || endWord.length() == 0 || wordList == null) {
            return 0;
        }
        Queue<String> words = new LinkedList<>();
        Queue<Integer> dist = new LinkedList<>();
        words.offer(beginWord);
        dist.offer(0);
        Set<String> wordSet = new HashSet<>();
        for (String w : wordList) {
            wordSet.add(w);
        }
        while(! words.isEmpty()) {
            String word = words.poll();
            int dis = dist.poll();
            if (word.equals(endWord)) {
                return dis + 1;
            }
            for (int i = 0 ; i < word.length(); i ++) {
                char[] chs = word.toCharArray();
                for (char ch = 'a'; ch <= 'z'; ch ++) {
                    chs[i] = ch;
                    String neighbor = new String(chs);
                    if (wordSet.contains(neighbor)) {
                        // first time use it, make as visited
                        wordSet.remove(neighbor);
                        words.offer(neighbor);
                        dist.offer(dis + 1);
                    }
                }
            }
        }
        return 0;
    }
}
