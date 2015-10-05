package DFSBFS;

import java.util.*;

/**
 * Created by yingtan on 9/17/15.
 */
public class LadderLength {

    // BFS: find shortest path to endWord. So many words can choose, so use BFS to delete most duplicate words
    // Once a word has been used , then it will not be used in future for shortest path, and so can delete it from dic
    // To delete more duplicate words quickly, and ensure shortest path, used BFS.
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        Queue<String> words = new LinkedList<String>();
        Queue<Integer> dist = new LinkedList<>();
        dist.offer(1);
        words.offer(beginWord);

        while (!words.isEmpty()) {
            String word = words.poll();
            int dis = dist.poll();
            int len = word.length();

            for (int i = 0; i < len; i++) {
                char[] chs = word.toCharArray();
                for (int k = 'a'; k < 'z'; k++) {
                    chs[i] = (char) k;

                    String newstr = new String(chs);
                    if (newstr.equals(endWord)) {
                        return dis + 1;
                    }
                    if (wordList.contains(newstr)) {
                        words.offer(newstr);
                        wordList.remove(newstr);

                        dist.offer(dis + 1);
                    }
                }
            }
        }
        return 0;
    }

    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        Queue<Node> words = new LinkedList<Node>();

        Node beginNode = new Node(beginWord, null, 1);
        words.offer(beginNode);

        int minDist = Integer.MAX_VALUE;

        List<List<String>> lists = new ArrayList<List<String>>();
        HashSet<String> visitedSet = new HashSet<String>();
        HashSet<String> unvisitedSet = new HashSet<String>();

        wordList.add(endWord);
        unvisitedSet.addAll(wordList);

        int prevDist = 1;

        while (!words.isEmpty()) {

            Node prevNode = words.poll();
            String word = prevNode.m_word;
            int dist = prevNode.m_dist;

            int len = word.length();
            if (prevDist < dist) {
                unvisitedSet.removeAll(visitedSet);
            }
            prevDist = dist;
            for (int i = 0; i < len; i++) {
                char[] chs = word.toCharArray();
                for (int j = 'a'; j < 'z'; j++) {
                    chs[i] = (char) j;
                    String newStr = new String(chs);

                    if (newStr.equals(endWord)) {
                        if (!newStr.equals(word)) {
                            Node newNode = new Node(newStr, prevNode, (dist + 1));
                            if (newNode.m_dist <= minDist) {
                                lists.add(formList(newNode));
                                minDist = newNode.m_dist;
                            }
                        }
                    } else if (unvisitedSet.contains(newStr)) {

                        if (!newStr.equals(word)) {
                            Node newNode = new Node(newStr, prevNode, (dist + 1));
                            words.offer(newNode);

                            visitedSet.add(newStr);
                        }
                    }
                }
            }
        }
        return lists;
    }

    public List<String> formList(Node curNode) {
        LinkedList<String> list = new LinkedList<String>();
        while (curNode != null) {
            String startWord = curNode.m_word;
            list.addFirst(startWord);
            curNode = curNode.m_prevWord;
        }
        return list;
    }


    public static void main(String[] args) {
        LadderLength ob = new LadderLength();
        Set<String> words = new HashSet<>();

/*
        words.add("ted");
        words.add("tex");
        words.add("red");
        words.add("tax");
        words.add("tad");
        words.add("den");
        words.add("rex");
        words.add("pee");
        System.out.println(ob.findLadders("red", "tax", words));
*/


        words.add("hot");
        words.add("dot");
        words.add("dog");
        words.add("lot");
        words.add("log");
        System.out.println(ob.findLadders("hit", "cog", words));
        String s1 = "tea";
        String s2 = "eat";
        String[] strs = new String[]{s1,s2};
        Arrays.sort(strs);
        System.out.println(strs[0]);

    }
}
