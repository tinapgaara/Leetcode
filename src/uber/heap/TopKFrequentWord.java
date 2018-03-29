package uber.heap;

/**
 * Created by yingtan on 12/3/17.
 *
 * 692. Top K Frequent Words
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a non-empty list of words, return the k most frequent elements.

 Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.

 Example 1:
 Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 Output: ["i", "love"]
 Explanation: "i" and "love" are the two most frequent words.
 Note that "i" comes before "love" due to a lower alphabetical order.
 Example 2:
 Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 Output: ["the", "is", "sunny", "day"]
 Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
 with the number of occurrence being 4, 3, 2 and 1 respectively.

 */
import java.util.*;

public class TopKFrequentWord {

    Map<String,Integer> map = new HashMap<>();

    public List<String> topKFrequent(String[] words, int k) {
        List<String> res = new ArrayList<String>();
        if (words == null || words.length == 0) return res;
        for (String word : words) {
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
            }
            else {
                map.put(word, 1);
            }
        }
        WordComparator comp = new WordComparator();
        PriorityQueue<String> queue = new PriorityQueue<>(comp);
        for (String word : map.keySet()) {
            queue.offer(word);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        String[] resint = new String[k];
        for (int i = k-1 ; i >= 0 ; i --) {
            resint[i] = queue.poll();
        }
        return Arrays.asList(resint);


    }
    public class WordComparator implements Comparator<String> {
        public int compare(String word1, String word2) {
            int count1 = map.get(word1);
            int count2 = map.get(word2);
            if (count1  != count2) {
                return count1 - count2;
            }
            else {
                return word2.compareTo(word1);
            }
        }
    }
}
