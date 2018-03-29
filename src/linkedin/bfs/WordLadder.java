package linkedin.bfs;

import java.util.*;

/**
 * Created by yingtan on 11/28/17.
 */
public class WordLadder {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord == null || endWord == null || wordList == null) return 0;
        Queue<String> words = new LinkedList<String>();
        Queue<Integer> dist = new LinkedList<Integer>();
        dist.offer(1);
        words.offer(beginWord);

        // Important ! must translate list to a set
        Set<String> wordSet = new HashSet<String>();
        for (String w : wordList) {
            wordSet.add(w);
        }

        while(!words.isEmpty()) {
            String word = words.poll();
            int dis = dist.poll();
            int len = word.length();

            for(int i = 0 ; i < len; i ++) {
                char[] chs = word.toCharArray();
                for (int k = 'a'; k < 'z' ; k ++) {
                    chs[i] = (char)k;

                    String newstr = new String(chs);

                    if (wordSet.contains(newstr)) {
                        // Important !!! endWord must exist in wordSet
                        if (newstr.equals(endWord)) {
                            return dis + 1;
                        }
                        words.offer(newstr);
                        //Important !!! must remove this str from set
                        wordSet.remove(newstr);

                        dist.offer(dis+1);
                    }
                }
            }
        }
        return 0;
    }
}
