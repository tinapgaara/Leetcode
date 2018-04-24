package vmware;

/**
 * Created by yingtan on 4/18/18.
 */
import java.util.*;
public class WordLadder {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord == null || endWord == null || wordList == null) return 0;
        Set<String> wordSet = new HashSet<>();
        for (String word : wordList) {
            wordSet.add(word);
        }
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        Queue<Integer> dist = new LinkedList<>();
        dist.offer(0);
        while(! queue.isEmpty()) {
            String word = queue.poll();
            int dis = dist.poll();
            if (word.equals(endWord)) {
                return dis + 1;
            }
            // change word
            for (int i = 0 ; i < word.length(); i ++) {
                char[] chs = word.toCharArray();
                for (char ch = 'a'; ch <= 'z'; ch ++) {
                    chs[i] = ch;
                    String newword = new String(chs);
                    if (wordSet.contains(newword)) {
                        wordSet.remove(newword);
                        queue.offer(newword);
                        dist.offer(dis + 1);
                    }
                }
            }
        }
        return 0;
    }
}
