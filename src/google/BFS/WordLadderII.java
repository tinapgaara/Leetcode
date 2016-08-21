package google.BFS;

import graph.Node;

import java.util.*;

/**
 * Created by yingtan on 11/8/15.
 */
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
