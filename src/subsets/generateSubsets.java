package subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yingtan on 9/20/15.
 */
public class generateSubsets {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (nums == null) {
            return res;
        }

        Arrays.sort(nums);

        List<List<Integer>> prevList = new ArrayList<List<Integer>>();
        List<Integer> empty = new ArrayList<Integer>();
        prevList.add(empty);

        List<List<Integer>> curList = new ArrayList<List<Integer>>();
        curList.addAll(prevList);

        for (int i = 0 ; i < nums.length; i ++) {
            int number = nums[i];
            List<List<Integer>> tmpLists = new ArrayList<List<Integer>>();
            for (int j = 0 ; j < prevList.size(); j ++) { // when iterate all lists, not change it, but copy it and store it in cur
                List<Integer> list = prevList.get(j);
                List<Integer> tmpList = new ArrayList<Integer>(list);
                tmpList.add(number);
                tmpLists.add(tmpList);
            }
            curList.addAll(tmpLists); // make sure addAll(tmplists) tmplists will not be changed in future, so must be copied list

            prevList = curList;
        }
        return curList;
    }
}
