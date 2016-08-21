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

    public class Node {

        String mNodeStr;
        int mDist;
        Node mPi;
        int mColor;

        public Node(String nodestr, int dist, Node pi, int color) {
            mNodeStr = nodestr;
            mDist = dist;
            mPi = pi;
            mColor = color;
        }
    }

// IMportant !!!1 : How to keep a level information in queue : use prevLevel and cureDist

    // BFS : how to record the level's information
    // When one level (s.t. dist all == 3) finished:
    // 1) delete all visitedNodes   2) update level No
    // 3) when reach endWord, use PI pointer to form result list.
    //    Can not return when find one,
    //    Need to go through all this levels' words to add other result lists.
    // 4) When using PI pointer to form list, can not ensure the shortest size of list (not like BFS)
    //    Need to keep minDist to judge if this list's size() is <= minDist. If so, need to add to result



    public List<List<String>> findLadders(String beginWord, String endWord,   Set<String> wordList) {
        Queue<Node> queue = new LinkedList<>();
        List<List<String>> res = new ArrayList<List<String>>();
        queue.offer(new Node(beginWord, 1, null, 1)); // color = grey

        int prevLevel = 0;
        int minDist = Integer.MAX_VALUE;
        Set<String> curLevelVisited = new HashSet<>();

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            String word = cur.mNodeStr; // hit
            int dist = cur.mDist;
            int color = cur.mColor;

            if (dist != prevLevel) {
                wordList.removeAll(curLevelVisited);
                prevLevel = dist;
                curLevelVisited.clear();
            }
            if (word.equals(endWord)) {
                if (dist <= minDist) { // must be <=
                    minDist = dist;
                    constructResList(res, cur);
                }
                continue;
            }
            for (int i = 0 ; i < word.length(); i ++) {
                for (int j = 0 ; j < 26; j ++) {
                    char[] chs = word.toCharArray();
                    char newch = (char)(j + 'a');
                    chs[i] = newch;
                    String newword = new String(chs);
                    if (wordList.contains(newword)) {
                        curLevelVisited.add(newword);
                        queue.offer(new Node(newword, dist+1, cur, 1));
                    }
                }
            }
            cur.mColor = 2;
        }
        return res;
    }

    public void constructResList(List<List<String>> res, Node node) {
        List<String> level = new ArrayList<String>();

        while(node != null) {
            level.add(0, node.mNodeStr);
            node = node.mPi;
        }
        res.add(level);
    }

    public static void main(String[] args) {
        WordLadderII ob = new WordLadderII();
        String begin = "red";
        String end = "tax";
        Set<String> word = new HashSet<>(Arrays.asList("ted","tex","red","tax","tad","den","rex","pee"));

        System.out.println(ob.findLadders(begin, end, word));
    }
}
