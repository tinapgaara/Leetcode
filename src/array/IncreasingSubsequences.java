package array;

/**
 * Created by yingtan on 1/22/18.
 *
 * 491. Increasing Subsequences
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Given an integer array, your task is to find all the different possible increasing subsequences of the given array, and the length of an increasing subsequence should be at least 2 .

 Example:
 Input: [4, 6, 7, 7]
 Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]

 */
import java.util.*;
public class IncreasingSubsequences {

    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        List<Integer> path = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0 ; i < nums.length; i ++) {
            if (set.contains(nums[i])) { // important !!! need to use set here
                continue;
            }
            set.add(nums[i]);// important !!! need to use set here
            path.add(nums[i]);
            dfs(nums,i + 1, path, res);
            path.remove(path.size() - 1);
        }
        return res;
    }
    public void dfs(int[] nums, int index, List<Integer> path, List<List<Integer>> res ) {
        if (index == nums.length) {
            return;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = index;  i < nums.length; i ++) {
            if (set.contains(nums[i])) {// important !!! need to use set here , to remove duplicates
                continue;
            }
            if (nums[i] >= path.get(path.size() - 1)) {
                // increasing
                set.add(nums[i]);
                path.add(nums[i]);
                if (path.size() >= 2) {
                    res.add(new ArrayList<>(path));
                }
                dfs(nums, i + 1, path, res);
                path.remove(path.size() - 1);
            }
        }
    }
}
