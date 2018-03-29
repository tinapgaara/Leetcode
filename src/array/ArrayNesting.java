package array;

/**
 * Created by yingtan on 3/1/18.
 *
 * 565. Array Nesting
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 A zero-indexed array A of length N contains all integers from 0 to N-1. Find and return the longest length of set S, where S[i] = {A[i], A[A[i]], A[A[A[i]]], ... } subjected to the rule below.

 Suppose the first element in S starts with the selection of element A[i] of index = i, the next element in S should be A[A[i]], and then A[A[A[i]]]… By that analogy, we stop adding right before a duplicate element occurs in S.

 Example 1:
 Input: A = [5,4,0,3,1,6,2]
 Output: 6
 Explanation:
 A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.

 One of the longest S[K]:
 S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
 */
public class ArrayNesting {
    // key: this is a circular array: so, 5->6->2->0, no mattter which node you starts from, the length is always 4.
    // so, we can just calculate from the earest node 5, and mark 6,2,0 as visited, then, we don't need to cal the length starts from 6,2,0(always 4)
    // mark vis: use -1 in place
    // so, time: o(n), space: o(1)
    public int arrayNesting(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int max = 0;
        for (int i = 0 ; i < nums.length; i ++) {
            if (nums[i] != -1) {
                int len = 0;
                int nextindex = nums[i];
                while (nums[nextindex] != -1) {
                    int newnextindex = nums[nextindex];
                    nums[nextindex] = -1 ;// mark visited
                    nextindex = newnextindex;
                    len ++;
                }
                max = Math.max(max, len);
            }
        }
        return max;
    }

}
