package heap;

/**
 * Created by yingtan on 3/18/18.
 */
import java.util.*;
public class ReorganizeString {

    public String reorganizeString(String S) {
        if (S == null || S.length() == 0) return S;
        EntryComparator comp = new EntryComparator();
        PriorityQueue<Map.Entry<Character, Integer>> queue = new PriorityQueue<>(comp);
        Map<Character, Integer> chToCount = new HashMap<>();
        for (char ch : S.toCharArray()) {
            if (chToCount.containsKey(ch)) {
                chToCount.put(ch, chToCount.get(ch) + 1);
            }
            else {
                chToCount.put(ch, 1);
            }
        }
        queue.addAll(chToCount.entrySet());
        StringBuilder st = new StringBuilder();
        while(! queue.isEmpty()) {
            List<Map.Entry<Character, Integer>> list = new ArrayList<>();
            if (queue.size() == 1) {
                Map.Entry<Character, Integer> entry = queue.poll();
                if (entry.getValue() > 1) return "";
                else {
                    st.append(entry.getKey());
                }
            }
            else {
                for (int i = 0; i < 2; i ++) {
                    // don't allow two is same, 2-distance-apart
                    if (! queue.isEmpty()) {
                        Map.Entry<Character, Integer> entry = queue.poll();
                        st.append(entry.getKey());
                        entry.setValue(entry.getValue() - 1);
                        if (entry.getValue() > 0) {
                            //need to offer to queue in the next time
                            list.add(entry);
                        }
                    }
                }
                queue.addAll(list);
            }
        }
        return st.toString();
    }
    public class EntryComparator implements Comparator<Map.Entry<Character, Integer>> {
        public int compare(Map.Entry<Character, Integer> e1, Map.Entry<Character, Integer> e2) {
            return e2.getValue() - e1.getValue();
        }
    }
}
