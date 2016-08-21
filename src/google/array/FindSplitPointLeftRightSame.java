package google.array;

/**
 * Created by yingtan on 11/6/15.
 */
/*
* array， 找⼀一个point，两边总和相等， 很简单，要注意负数情
况
* */
public class FindSplitPointLeftRightSame {

    // Solution 1: cal sum, and use minus operation , space:o(n)
    // Solution 2: scan from left 2 right + scan from right 2 left, space:2o(n)
    public int findSplitPoint(int[] nums) {
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];
        left[0] = nums[0];
        right[nums.length -1] = nums[nums.length -1];

        for (int i = 1; i < nums.length ; i ++) {
            left[i] = left[i-1] + nums[i];
        }
        for (int i = nums.length -2; i >= 0 ; i --) {
            right[i] = right[i+1] + nums[i];
        }

        // scan twice, can be used to calculate so many split points.
        for (int i = 0 ; i < nums.length ; i ++) {
            if (left[i] == right[i]) return i;
        }
        return 0;
    }


    /*
    *给定⼀一个array，只包含0，1， 找到⼀一个分割位置，使左侧0出
        现的个数和右侧1出现的个数之和最⼤大化
    * */
    // Solution 1: scan left, + scan right
    // Solution 2: count 1, count 0. Then, use tmp0 = 0 , encounter 0: tmp0 ++;  tmp1 = #1, encounter 1: tmp1 --
    //      make sure: Max(tmp0 + tmp1)
}
