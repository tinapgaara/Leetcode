package facebook.subset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yingtan on 5/21/17.
 *
 * 90. Subsets II Add to List
 DescriptionHintsSubmissionsSolutions
 Total Accepted: 106382
 Total Submissions: 301517
 Difficulty: Medium
 Contributor: LeetCode
 Given a collection of integers that might contain duplicates, nums, return all possible subsets.

 Note: The solution set must not contain duplicate subsets.

 For example,
 If nums = [1,2,2], a solution is:

 [
 [2],
 [1],
 [1,2,2],
 [2,2],
 [1,2],
 []
 ]

 */
public class SubsetII {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
// assume nums are sorted
        Arrays.sort(nums);
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
