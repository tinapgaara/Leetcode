package greedy;

/**
 * Created by yingtan on 1/18/18.
 *
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

 You may assume that the array is non-empty and the majority element always exist in the array.

 m /n >= 1/2
 m represents majority element

 if there is a majority element in an array, say x, then it is okay to discard a part of that array that has no majority element, the remaining array will still have
 x as the majority element

 */
public class MajorityElement {

    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int candidate = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i ++) {
            // when count ==0, abandon this the subset, the left we have: (m)/(n-x), or has (m - x/2)/(n - x), still >= 1/2
            if (count == 0) {
                // candidiate is not more than [n/2]
                candidate = nums[i];
                count = 1;
            }
            else if (nums[i] == candidate) {
                count ++;
            }
            else {
                count --;
            }
        }
        return candidate;
    }
}
