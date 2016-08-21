package bloomberg.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yingtan on 10/25/15.
 */
/*
* All 2 sum problems here.
*
* */
/*
*，2sum，
输出有效的number pair的对数，输入的数组里有重复，输出的不能有重复，假设给定sum是9，（3,6）和
（6,3）是一样的，或者给定sum是12，(6,6)只算一次只要一个


输入(1,1,1,48,22),sum = 49,返回3
输入(3,6,1,2,3)sum = 9,返回 2
输入(3,3,3,3,3,3,6) sum = 6，返回15


我觉得可以表述为返回元素index pair的个数。比如（3，3，3，6）， sum = 6, 则答案为{{0，1}，{0，2}，{1，
2}}.length()。
*
* */
public class Sum2 {

    public int get2SumPairsNum(int sum, int[] nums) {
        // because allow dupliate here, so can not save  value - index.
        // need to save: value - count
        Map<Integer, Integer> map = new HashMap<>(); // map save the  value ->  count
        for (int i = 0 ; i < nums.length ; i ++) {
            if (map.containsKey(nums[i])) {
                int count = map.get(nums[i]);
                map.put(nums[i], count + 1);
            } else {
                map.put(nums[i], 1);
            }
        }

        int res = 0;
        for (int i = 0 ; i < nums.length ; i ++) {
            int num = nums[i];
            int numCount = map.get(num);
            numCount --;
            map.put(num, numCount);

            int num_2 = sum - num;
            if (map.containsKey(num_2)) {
                int count_2 = map.get(num_2);
                res = res + count_2;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Sum2 ob = new Sum2();
        int[] nums = new int[]{3,3,3,3,3,3,6};
        ob.get2SumPairsNum(6, nums);
    }

}
