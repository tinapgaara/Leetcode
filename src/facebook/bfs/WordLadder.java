package facebook.bfs;

import java.util.*;

/**
 * Created by yingtan on 5/28/17.
 *
 * 127. Word Ladder Add to List
 DescriptionHintsSubmissionsSolutions
 Total Accepted: 120166
 Total Submissions: 622404
 Difficulty: Medium
 Contributor: LeetCode
 Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

 Only one letter can be changed at a time.
 Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 For example,

 Given:
 beginWord = "hit"
 endWord = "cog"
 wordList = ["hot","dot","dog","lot","log","cog"]
 As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 return its length 5.
 */
public class WordLadder {

    /*
        Need to use BFS for  shortest distance
        If use DFS, since this ends when word == endWord, however, the sequence can be infinite, so can not go one path infinitely at first, need to do bfs

        Keep two queue, one for storing words, one for storing distance

        judge if word == endWord, word must be in wordList
        so, replacing chs at firstly, and then judge if new string is in wordlist, and then if word == endWord

    */
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
