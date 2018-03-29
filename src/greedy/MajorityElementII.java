package greedy;

/**
 * Created by yingtan on 1/18/18.
 *
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. The algorithm should run in linear time and in O(1) space.
 */
import java.util.*;
public class MajorityElementII {

    // common solution for n/k
    public List<Integer> majorityElement(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        Map<Integer, Integer> count = new HashMap<>(); // create a hashmap with size 3(n/k, k is 3 here)
        for (int num : nums) {
            if (count.containsKey(num)) {
                count.put(num, count.get(num) + 1);
            }
            else {
                count.put(num, 1);
            }
            // reach k distinct nums
            if (count.size() == k) {
                Set<Integer> delKeys = new HashSet<>();
                // remove k distinct nums per one in hashmap
                for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
                    if (entry.getValue() > 1) {
                        count.put(entry.getKey(), entry.getValue() - 1);
                    }
                    else {
                        delKeys.add(entry.getKey());
                    }
                }
                for (Integer delKey : delKeys) {
                    count.remove(delKey);
                }
            }
        }
        // reset table
        for (int key: count.keySet()) {
            count.put(key, 0);
        }
        for (int num : nums) {
            if (count.containsKey(num)) {
                count.put(num, count.get(num) + 1);
            }
        }
        // cal if larger than n / k
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            if (entry.getValue() > (nums.length * 1.0 / k)) {
                res.add(entry.getKey());
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,2,3,2,2};
        MajorityElementII ob = new MajorityElementII();
        System.out.println(ob.majorityElement(nums, 4));
    }
}
