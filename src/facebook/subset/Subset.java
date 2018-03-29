package facebook.subset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yingtan on 5/20/17.
 *
 * 78. Subsets Add to List
 DescriptionHintsSubmissionsSolutions
 Total Accepted: 157182
 Total Submissions: 403119
 Difficulty: Medium
 Contributor: LeetCode
 Given a set of distinct integers, nums, return all possible subsets.

 Note: The solution set must not contain duplicate subsets.

 For example,
 If nums = [1,2,3], a solution is:

 [
 [3],
 [1],
 [2],
 [1,2,3],
 [1,3],
 [2,3],
 [1,2],
 []
 ]
 */
public class Subset {

    // Solution 1: use bits [1, 2, 3] & [0, 0, 0], [0, 0, 1], [0, 1, 0] ....
    public List<List<Integer>> subsets_bits(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) return res;
        Arrays.sort(nums);

        int len = nums.length;
        int bits = (int)Math.pow(2, len);
        for (int i = 0 ; i < bits; i ++) {
            int numToAnd = i;
            List<Integer> list = new ArrayList<Integer>();
            for (int j = 0; j < len ; j ++) {
                int digit = (numToAnd) & 1;
                if (digit == 1)
                    list.add(nums[j]);
                numToAnd = numToAnd >> 1;
            }
            res.add(list);
        }

        return res;
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
// assume nums are sorted
        List<Integer> path = new ArrayList<>();
        res.add(new ArrayList<>());
        recur(nums, 0, path, res);
        return res;
    }

    public void recur(int[] nums, int index, List<Integer> path, List<List<Integer>> res) {
        for (int i = index; i < nums.length; i ++) {
            if (i != index && nums[i] == nums[i - 1]) continue;
            path.add(nums[i]); // [1] [2] [3]
            res.add(new ArrayList<>(path));
            recur(nums, i + 1, path, res);
            path.remove(path.size() - 1);
        }
    }

}
