package DFSBFS;

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
public class WordLadder {

    public class Node {
        String mNodeStr;
        int mDist;

        public Node(String nodeStr, int dist) {
            mNodeStr = nodeStr;
            mDist = dist;
        }
    }

    // Solution 1: using shortest path in BFS
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {

        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(new Node(beginWord, 1));

        while ( ! queue.isEmpty()) {
            Node node = queue.poll();
            String word = node.mNodeStr;// we
            int dist = node.mDist;

            for (int i = 0 ; i < word.length() ; i ++) { // neighbors: ae, be, ce, ..... wz
                char[] chs = word.toCharArray();
                for (int j = 'a' ; j < 'z'; j ++) {
                    chs[i] = (char)j;
                    String newstr = new String(chs);

                    if (newstr.equals(endWord)) return dist+1;

                    if (wordList.contains(newstr)) { // judge if is WHITE
                        queue.offer(new Node(newstr, dist + 1));
                        wordList.remove(newstr); // change its color to BLACK
                    }
                }
            }
        }
        return 0;
    }

    // Solution 2: do not need to declare Node, can just use another dist Queue.
    public int ladderLength_2(String beginWord, String endWord, Set<String> wordList) {
        Queue<String> words = new LinkedList<String>();
        Queue<Integer> dist = new LinkedList<Integer>();
        dist.offer(1);
        words.offer(beginWord);

        while(!words.isEmpty()) {
            String word = words.poll();
            int dis = dist.poll();
            int len = word.length();

            for(int i = 0 ; i < len; i ++) {
                char[] chs = word.toCharArray();
                for (int k = 'a'; k < 'z' ; k ++) {
                    chs[i] = (char)k;

                    String newstr = new String(chs);
                    if (newstr.equals(endWord)) {
                        return dis + 1;
                    }
                    if (wordList.contains(newstr)) {
                        words.offer(newstr);
                        wordList.remove(newstr);

                        dist.offer(dis+1);
                    }
                }
            }
        }
        return 0;
    }

}

