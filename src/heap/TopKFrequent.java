package heap;

import java.util.*;

/**
 * Created by yingtan on 8/7/16.
 *
 347. Top K Frequent Elements  QuestionEditorial Solution  My Submissions

 Given a non-empty array of integers, return the k most frequent elements.

 For example,
 Given [1,1,1,2,2,3] and k = 2, return [1,2].

 Note:
 You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

 */
public class TopKFrequent {

    public Map<Integer, Integer> counts;

    public List<Integer> topKFrequent(int[] nums, int k) {
        counts = new HashMap<>();
        ItemComparator comparator = new ItemComparator();

        for (int i = 0 ; i < nums.length; i ++) {
            int item = nums[i];
            if (! counts.containsKey(item)) {
                counts.put(item, 1);
            }
            else {
                int count = counts.get(item);
                counts.put(item, count + 1);
            }
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>(comparator);

        for (Integer key : counts.keySet()) {
            queue.offer(key);
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0 ; i < k ; i ++) {
            res.add(queue.poll());
        }
        return res;
    }

    public class ItemComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer i1, Integer i2) {
            int count1 = counts.get(i1);
            int count2 = counts.get(i2);

            if (count1 > count2)
                return -1;
            else if (count1 < count2)
                return 1;
            else
                return 0;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,1,2,2,3,3,3,3};
        TopKFrequent ob = new TopKFrequent();
        System.out.println(ob.topKFrequent(nums, 2));
    }
}
