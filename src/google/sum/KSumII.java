package google.sum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 11/27/15.
 */
/*
* Given n unique integers, number k (1<=k<=n)  and target. Find all possible k integers where their sum is target.
*
*
* Example
Given [1,2,3,4], k=2, target=5, [1,4] and [2,3] are possible solutions.
http://www.cnblogs.com/EdwardLiu/p/4314783.html
* */
public class KSumII {

    public List<List<Integer>> kSumII(int nums[], int k, int target) {

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<Integer>();
        recurSum(res, path, nums, k, target, 0);
        return res;
    }

    public void recurSum(List<List<Integer>> res, List<Integer> path, int[] nums,
                                        int k, int remain, int nextIndex) {
        if (path.size() == k) {
            if (remain == 0) {
                res.add(new ArrayList<>(path));
            }
            return ;
        }
        // [1,2] [1,3] [1,4] [2,3] [2,4] [3,4]
        for (int i = nextIndex; i < nums.length; i ++) {
            path.add(nums[i]);
            recurSum(res, path, nums, k, remain - nums[i], i + 1); // Important !!!
            path.remove(path.size() -1);
        }
    }

}
