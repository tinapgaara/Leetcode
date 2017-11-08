package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 12/22/15.
 */
/*
* Given a collection of numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
*
* */
public class Permutations {

    public List<List<Integer>> permute_top2Down(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        if (nums == null) return res;
        return recurPermute(nums, 0);
    }

    public List<List<Integer>> recurPermute(int[] nums, int index) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (index == nums.length - 1) {
            List<Integer> list = new ArrayList<Integer>();
            list.add(nums[index]);
            res.add(list);
            return res;
        }
        int num = nums[index];
        List<List<Integer>> next = recurPermute(nums, index + 1);


        for (List<Integer> list : next) {
            for (int i = 0 ; i < list.size(); i ++) {
                List<Integer> copy = new ArrayList<Integer>(list);
                copy.add(i, num); // add element at position i
                res.add(copy);
            }
            List<Integer> lastCopy = new ArrayList<Integer>(list);
            lastCopy.add(num); // add tail
            res.add(lastCopy);
        }
        return res;
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (nums == null) {
            return res;
        }
        return recurPermute(nums, 0, nums.length - 1);
    }

    public List<List<Integer>> recurPermute(int[] nums, int low, int high) {
        if ((low == high)  && (low < nums.length)) {
            List<List<Integer>> list = new ArrayList<List<Integer>>();
            List<Integer> l = new ArrayList<Integer>();
            l.add(nums[low]);
            list.add(l);
            return list;
        }

        int num = nums[low];
        List<List<Integer>> next = recurPermute(nums, low + 1, high);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for (List<Integer> list : next) {
            for (int i = 0 ; i < list.size(); i ++) {
                List<Integer> copy = new ArrayList<Integer>(list);
                copy.add(i, num); // add element at position i
                res.add(copy);
            }
            List<Integer> lastCopy = new ArrayList<Integer>(list);
            lastCopy.add(num); // add tail
            res.add(lastCopy);
        }
        return res;
    }
}
