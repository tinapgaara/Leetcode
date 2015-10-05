package queue;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

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

                        dist.offer(dis + 1);
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        LadderLength ob = new LadderLength();
        Set<String> words = new HashSet<>();
        words.add("miss");
        words.add("dusk");
        words.add("kiss");
        words.add("musk");
        words.add("tusk");
        words.add("diss");
        words.add("disk");
        words.add("sang");
        words.add("ties");
        words.add("muss");
        System.out.println(ob.ladderLength("kiss", "tusk", words));
    }
}
