package google.array;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by yingtan on 9/17/17.
 * 506. Relative Ranks
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given scores of N athletes, find their relative ranks and the people with the top three highest scores, who will be awarded medals: "Gold Medal", "Silver Medal" and "Bronze Medal".

 Example 1:
 Input: [5, 4, 3, 2, 1]
 Output: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]
 Explanation: The first three athletes got the top three highest scores, so they got "Gold Medal", "Silver Medal" and "Bronze Medal".
 For the left two athletes, you just need to output their relative ranks according to their scores.
 */
public class RelativeRanks {

    public String[] findRelativeRanks(int[] nums) {
        String[] res = new String[nums.length];
        if(nums == null || nums.length == 0) return res;
        // Important !! index array
        Integer[] index = new Integer[nums.length];

        for (int i = 0; i < nums.length; i++) {
            index[i] = i;
        }
        Comparator<Integer> numComp = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return nums[o2] - nums[o1];
            }
        };

        Arrays.sort(index, numComp);

        for (int i = 0 ; i < index.length; i ++) {
            // Important !! i ==0, which means champion
            if (i == 0) {
                res[index[i]] = "Gold Medal";
            }
            else if (i == 1) {
                res[index[i]] = "Silver Medal";
            }
            else if (i == 2) {
                res[index[i]] = "Bronze Medal";
            }
            else {
                res[index[i]] = "" + (i + 1);
            }
        }
        return res;
    }
}
