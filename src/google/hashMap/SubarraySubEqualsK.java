package google.hashMap;

import java.util.HashMap;

/**
 * Created by yingtan on 10/8/17.
 *
 * 560. Subarray Sum Equals K
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

 Example 1:
 Input:nums = [1,1,1], k = 2
 Output: 2
 */
public class SubarraySubEqualsK {

    public int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        HashMap < Integer, Integer > map = new HashMap< >();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k))
                count += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
