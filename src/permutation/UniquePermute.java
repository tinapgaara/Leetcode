package permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yingtan on 9/22/15.
 */
/*
* [1 1 2 3]
* start = 0 : move ith element to start position
* i = 0: [1] + permute([1 2 3])
* i = 1: equals to i = 0's [1], so continue
* i = 2; [1 1 2 3] -> [2 1 1 3]    // move 2 to the 0th position, 其他elements顺势往下一个元素位置挪一格
*        then = [2] + permute[1 1 3]
* i = 3：[1 1 2 3] -> [3 1 1 2]
*       then = [3] + permute[1 1 2]
* */
public class UniquePermute {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        LinkedList<Integer> list = new LinkedList<Integer>();
        for (int num : nums) list.add(num);
        permute(list, 0, res);
        return res;
    }
    private void permute(LinkedList<Integer> nums, int start, List<List<Integer>> res){
        if (start == nums.size() - 1){
            res.add(new LinkedList<Integer>(nums));
            return;
        }
        for (int i = start; i < nums.size(); i++){
            if (i > start && nums.get(i) == nums.get(i - 1)) continue;
            nums.add(start, nums.get(i)); // insert current ith element to start position
            nums.remove(i + 1); // remove the inserted element
            permute(nums, start + 1, res);
            nums.add(i + 1, nums.get(start));
            nums.remove(start);
        }
    }

    public static void main(String[] args) {
        UniquePermute ob = new UniquePermute();
        int[] nums = new int[]{1,1,2,3};
        System.out.println(ob.permuteUnique(nums));
    }
}
