package google.subset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yingtan on 11/1/15.
 */
/*
*Given a collection of integers that might contain duplicates, nums, return all possible subsets.

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
*
* */
public class SubSetIIDuplicates {

    public List<List<Integer>> subsetsWithDup_easy(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) return res;

        Arrays.sort(nums);
        boolean[] vis = new boolean[nums.length];
        List<Integer> level = new ArrayList<Integer>();
        recurSubsets(res, nums, 0, level, vis);
        return res;
    }

    public void recurSubsets(List<List<Integer>> res, int[] nums, int index, List<Integer> level, boolean[] vis) {
        res.add(new ArrayList<Integer>(level));
        for (int i = index; i < nums.length; i ++) {
            // this item already been added to the result
            if (vis[i]) continue;
            // skip same items
            // important !!! must define !vis[i-1] here , case:
            // 1 + [1, 2] when add the second one to it, the first one is the caller, so the first one is already visited.in this case, we still need to add [1,2] to 1
            // 1 + [1, 1, 2] when add the third one to it, the first one is the caller, but the second one and the third one is the same one, and we need to skip the third one.

            if (i > 0 && nums[i] == nums[i-1] && !vis[i-1]) continue;
            level.add(nums[i]);
            vis[i]= true;
            // important !!!! should be (i+1) instead of (index + 1)
            recurSubsets(res, nums, i + 1, level, vis);
            level.remove(level.size() - 1);
            vis[i] = false;
        }
        return;
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if ((nums == null) || (nums.length == 0)) {
            return res;
        }
        Arrays.sort(nums);

        Integer prev  = null;
        List<List<Integer>> prevLevel = new ArrayList<>();
        for (int i = nums.length - 1 ; i >= 0; i --) {
            List<List<Integer>> newres = new ArrayList<>();
            // copy to save added integers to current level.
            List<List<Integer>> prevLevelCopy = new ArrayList<>();

            // when duplcates, based on prevLevel's list to add new element
            if ((prev != null) && (nums[i] == prev)) {
                for (List<Integer> list : prevLevel) {
                    List<Integer> copy = new ArrayList<>(list);
                    copy.add(0, nums[i]);
                    newres.add(copy);
                    prevLevelCopy.add(copy);
                }
            }
            // no duplciates, just add elements on previous all res
            else {
                for (List<Integer> list : res) {
                    List<Integer> copy = new ArrayList<>(list);
                    copy.add(0, nums[i]);
                    newres.add(copy);
                    prevLevelCopy.add(copy);
                }
            }

            if ( (prev == null) || (nums[i] != prev) ) {
                // when different element, add single element
                List<Integer> single = new ArrayList<>();
                single.add(nums[i]);
                prevLevelCopy.add(single);
                newres.add(single);
            }

            // finally, add all previous results
            newres.addAll(res);
            res = newres;
            prevLevel = prevLevelCopy;
            prev = nums[i];
            System.out.println(res);
        }

        // finally, and finally, add [] : important !!!!
        List<Integer> empty = new ArrayList<>();
        res.add(empty);
        return res;
    }

    public static void main(String[] args) {
        SubSetIIDuplicates ob =new SubSetIIDuplicates();
        int[] nums = new int[]{1,1,2,2};
        System.out.println(ob.subsetsWithDup(nums));
    }
}
