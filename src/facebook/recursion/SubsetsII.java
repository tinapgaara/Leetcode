package facebook.recursion;

/**
 * Created by yingtan on 12/22/17.
 * 90. Subsets II
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

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
import java.util.*;
public class SubsetsII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        // Important !!! need to sort firstly
        Arrays.sort(nums);
        List<Integer> path = new ArrayList<>();
        recur(nums, 0, path, res);
        List<Integer> empty = new ArrayList<>();
        res.add(empty);
        return res;
    }
    public void recur(int[] nums, int index, List<Integer> path, List<List<Integer>> res) {
        if (index >= nums.length) {
            return;
        }
        for (int i = index; i < nums.length; i ++) {
            // Important !!! i > index
            if (i > index && nums[i] == nums[i-1]) continue;
            path.add(nums[i]);

            res.add(new ArrayList<>(path));
            recur(nums, i + 1, path, res);
            path.remove(path.size() - 1);
        }
    }
}
