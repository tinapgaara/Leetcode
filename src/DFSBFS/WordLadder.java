package DFSBFS;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created by yingtan on 10/6/15.
 */

/*
* Leetcode: Word Ladder
*
* Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

    Only one letter can be changed at a time
    Each intermediate word must exist in the word list
    For example,

    Given:
    beginWord = "hit"
    endWord = "cog"
    wordList = ["hot","dot","dog","lot","log"]
    As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
    return its length 5.

    Shortest transformation: using BFS property.
* */
    import  java.util.*;
public class WordLadder {

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

