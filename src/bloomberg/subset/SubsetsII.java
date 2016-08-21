package bloomberg.subset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yingtan on 11/15/15.
 */
/*
* contains duplicate
*
*
* If nums = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]

* */
public class SubsetsII {

    public List<List<Integer>> subsetsWithDup(int[] nums) {

        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        List<List<Integer>> prevLevel = new ArrayList<>();
        List<Integer> last = new ArrayList<>();
        last.add(nums[nums.length -1]);

        res.add(last);
        prevLevel.add(last);

        int prev = nums[nums.length -1];
        for (int i = nums.length-2; i >= 0 ; i --) {

            List<List<Integer>> newList = new ArrayList<>();
            List<List<Integer>> curLevel = new ArrayList<>();
            newList.addAll(res);

            if (nums[i] == prev) {
                for (List<Integer> list : prevLevel) {
                    List<Integer> copyList = new ArrayList<>(list);
                    copyList.add(0,nums[i]);

                    newList.add(copyList);
                    curLevel.add(copyList);
                }
            }
            else {
                for (List<Integer> list : res) {
                    List<Integer> copyList = new ArrayList<>(list);
                    copyList.add(0, nums[i]);

                    newList.add(copyList);
                    curLevel.add(copyList);
                }

                List<Integer> single = new ArrayList<>();
                single.add(nums[i]);
                newList.add(single);
                curLevel.add(single);
            }

            prev = nums[i];
            res = newList;
            prevLevel  = curLevel;
        }

        List<Integer> empty = new ArrayList<>();
        res.add(empty);

        return res;
    }

    public static void main(String[] args) {
        SubsetsII ob = new SubsetsII();
        int[] nums = new int[]{1,1,2,2};
        System.out.println(ob.subsetsWithDup(nums));
    }
}
