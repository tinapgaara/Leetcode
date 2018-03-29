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
        if (beginWord == null || beginWord.length() == 0 || endWord == null || endWord.length() == 0 || wordList == null || wordList.size() == 0) {
            return 0;
        }
        Queue<String> words = new LinkedList<String>();
        Queue<Integer> dist = new LinkedList<Integer>();
        words.offer(beginWord);
        Set<String> wordSet = new HashSet<String>();
        wordSet.addAll(wordList);
        dist.offer(0);
        while(! words.isEmpty()) {
            String curWord = words.poll();
            int dis = dist.poll();
            if (curWord.equals(endWord)) {
                return dis + 1;
            }
            for (int i = 0; i < curWord.length(); i ++) {
                char[] chs = curWord.toCharArray();
                for (char c = 'a'; c <= 'z'; c ++) {
                    chs[i] = c;
                    String newWord = new String(chs);
                    if (wordSet.contains(newWord)) {
                        words.offer(newWord);
                        dist.offer(dis + 1);
                        // important!!!  replace the visited flag array, make sure the same word will not be added to the queue multiple time,
                        // and the first time we hit this word, must is the shortest distance. don't need to add this word back to dict
                        wordSet.remove(newWord);
                    }
                }
            }
        }
        return 0;
    }

}
