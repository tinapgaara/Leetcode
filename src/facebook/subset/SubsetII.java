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
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) return res;

        Arrays.sort(nums);

        // scan from tail to head
        List<List<Integer>> prevLevel = new ArrayList<List<Integer>>();
        int last = nums[nums.length - 1];
        int prev = last;

        // Important: Everytime need to create a list for res and another copy of list for prevLevel separately
        List<Integer> reslist = new ArrayList<Integer>();
        reslist.add(prev);
        res.add(reslist);

        List<Integer> prevList = new ArrayList<Integer>(reslist);
        prevLevel.add(prevList);


        for (int i = nums.length - 2; i >= 0; i --) {
            int cur = nums[i];
            if (cur == prev) {
                // res = curNumber + prevLevel union res
                // prevLevel = curNum + prevLevel
                for (List<Integer> prevLevelList : prevLevel) {
                    // curNumber + prevLevel
                    prevLevelList.add(0, cur);

                    // union res
                    List<Integer> newResList = new ArrayList<Integer>(prevLevelList);
                    res.add(newResList);
                }
            }
            else {
                // res = curNum + res + self union res
                // prevLevel = curNum + res + self
                prevLevel = new ArrayList<List<Integer>>();
                List<List<Integer>> newResLists = new ArrayList<List<Integer>>();

                for (List<Integer> resList : res) {
                    // curNum + res union res
                    List<Integer> newResList = new ArrayList<Integer>(resList);
                    newResList.add(0, cur);
                    newResLists.add(newResList);

                    // create new prevLevel
                    List<Integer> newPrevList = new ArrayList<Integer>(newResList);
                    prevLevel.add(newPrevList);
                }
                // res union
                res.addAll(newResLists);

                // add self
                List<Integer> resSelf = new ArrayList<Integer>();
                resSelf.add(cur);
                res.add(resSelf);

                List<Integer> prevSelf = new ArrayList<Integer>(resSelf);
                prevLevel.add(prevSelf);
            }
            prev = cur;
        }
        List<Integer> empty = new ArrayList<Integer>();
        res.add(empty);
        return res;
    }
}
