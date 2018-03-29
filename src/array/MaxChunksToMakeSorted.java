package array;

/**
 * Created by yingtan on 2/26/18.
 *
 * 769. Max Chunks To Make Sorted
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given an array arr that is a permutation of [0, 1, ..., arr.length - 1], we split the array into some number of "chunks" (partitions), and individually sort each chunk.  After concatenating them, the result equals the sorted array.

 What is the most number of chunks we could have made?

 Example 1:

 Input: arr = [4,3,2,1,0]
 Output: 1
 Explanation:
 Splitting into two or more chunks will not return the required result.
 For example, splitting into [4, 3], [2, 1, 0] will result in [3, 4, 0, 1, 2], which isn't sorted.
 Example 2:

 Input: arr = [1,0,2,3,4]
 Output: 4
 Explanation:
 We can split into two chunks, such as [1, 0], [2, 3, 4].
 However, splitting into [1, 0], [2], [3], [4] is the highest number of chunks possible.
 */
import java.util.*;
public class MaxChunksToMakeSorted {
    public int maxChunksToSorted(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        //Time Exceed Limit
        //return bfs(arr);
        // using the property: Given an array arr that is a permutation of [0, 1, ..., arr.length - 1]
        return greedy(arr);
    }
    public int greedy(int[] arr) {
        int count = 0;
        int max = 0;
        for (int i = 0; i < arr.length; i ++) {
            max = Math.max(max, arr[i]);
            if (max == i) {
                count ++;
            }
        }
        return count;
    }
    public int bfs(int[] arr) {
        Queue<List<int[]>> queue = new LinkedList<>();
        List<int[]> first = new ArrayList<>();
        for (int num : arr) {
            first.add(new int[]{num, num});
        }
        queue.add(first);
        while(! queue.isEmpty()) {
            List<int[]> nums = queue.poll();
            if (valid(nums)) {
                return nums.size();
            }
            for (int i = 0 ; i < nums.size() - 1; i ++) {
                List<int[]> newlist = new ArrayList<>();
                int j = 0;
                while(j < nums.size()) {
                    if (i != j) {
                        newlist.add(nums.get(j));
                    }
                    else {
                        int max = Math.max(nums.get(j)[1], nums.get(j + 1)[1]);
                        int min = Math.min(nums.get(j)[0], nums.get(j+ 1)[0]);
                        newlist.add(new int[]{min, max});
                        j ++;
                    }
                    j ++;
                }
                queue.offer(newlist);
            }
        }
        return 1;
    }
    public boolean valid(List<int[]> nums) {
        int prevMin = Integer.MAX_VALUE;
        int prevMax = Integer.MIN_VALUE;
        for (int[] num : nums) {
            int min = num[0];
            int max = num[1];
            if (min < prevMax) {
                return false;
            }
            prevMin = min;
            prevMax = max;
        }
        return true;
    }
    public static void main(String[] args) {
        MaxChunksToMakeSorted ob = new MaxChunksToMakeSorted();
        int[] n = {0,5,9,6,2,8,4,1,7,3};
        ob.maxChunksToSorted(n);
    }
}
