package google.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yingtan on 11/4/17.
 *
 * 560. Subarray Sum Equals K
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

 Example 1:
 Input:nums = [1,1,1], k = 2
 Output: 2
 */
public class SubarraySumEqualsKCount {

    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //  map: sum[0 ~ i] -> how many sub continuous array starting from 0th equals to sum
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, 1);
        int sum = 0;
        int count = 0;

        for (int i = 0 ; i < nums.length; i ++) {
            sum = sum + nums[i];
            if (map.containsKey(sum - k)) {
                count = count + map.get(sum - k);
            }
            // important !!!!!
            if (map.containsKey(sum)) {
                map.put(sum, map.get(sum) + 1);
            }
            else {
                map.put(sum, 1);
            }

        }
        return count;
    }
}
