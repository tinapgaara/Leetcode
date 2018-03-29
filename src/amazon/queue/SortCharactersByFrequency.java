package amazon.queue;

/**
 * Created by yingtan on 3/25/18.
 *
 * 451. Sort Characters By Frequency
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given a string, sort it in decreasing order based on the frequency of characters.

 Example 1:

 Input:
 "tree"

 Output:
 "eert"

 Explanation:
 'e' appears twice while 'r' and 't' both appear once.
 So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
 Example 2:

 Input:
 "cccaaa"

 Output:
 "cccaaa"

 Explanation:
 Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
 Note that "cacaca" is incorrect, as the same characters must be together.
 Example 3:

 Input:
 "Aabb"

 Output:
 "bbAa"

 Explanation:
 "bbaA" is also a valid answer, but "Aabb" is incorrect.
 Note that 'A' and 'a' are treated as two different characters.
 */
import java.util.*;
public class SortCharactersByFrequency {

    public String frequencySort(String s) {
        if (s == null || s.length() == 0) return s;
        Map<Character, Integer> map = new HashMap<>();
        EntryCompartaor comp = new EntryCompartaor();
        PriorityQueue<Map.Entry<Character, Integer>> queue = new PriorityQueue<>(comp);
        for (Character ch : s.toCharArray()) {
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) + 1);
            }
            else {
                map.put(ch, 1);
            }
        }
        queue.addAll(map.entrySet());
        StringBuilder st = new StringBuilder();
        while(! queue.isEmpty()) {
            Map.Entry<Character, Integer> entry = queue.poll();
            char ch = entry.getKey();
            int count = entry.getValue();
            for (int i = 0 ; i < count ; i ++) {
                st.append(ch);
            }
        }
        return st.toString();
    }
    public class EntryCompartaor implements Comparator<Map.Entry<Character, Integer>> {
        public int compare(Map.Entry<Character, Integer> e1, Map.Entry<Character, Integer> e2) {
            return e2.getValue() - e1.getValue();
        }
    }
}
