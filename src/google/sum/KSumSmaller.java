package google.sum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 11/27/15.
 */
//

public class KSumSmaller {

    public List<List<Integer>> kSumSmaller(int nums[], int k, int target) {

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<Integer>();
        recurSumSmaller(res, path, nums, k, target, 0);
        return res;
    }

    public void recurSumSmaller(List<List<Integer>> res, List<Integer> path, int[] nums, int k,
                                int remain, int nextInt) {
        if (path.size() == k) {
            if (remain > 0) {
                res.add(new ArrayList<>(path));
            }
            return;
        }

        for (int i = nextInt; i < nums.length; i ++) {
            path.add(nums[i]);
            recurSumSmaller(res, path, nums, k, remain - nums[i], i + 1);
            path.remove(path.size() -1);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4};
        int k = 3;
        int target = 8;
        KSumSmaller ob = new KSumSmaller();
        System.out.println(ob.kSumSmaller(nums, k, target));
    }
}
