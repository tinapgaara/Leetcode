package google.heapPriorityQueue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by yingtan on 8/17/17.
 *
 * 373. Find K Pairs with Smallest Sums
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.

 Define a pair (u,v) which consists of one element from the first array and one element from the second array.

 Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.

 Example 1:
 Given nums1 = [1,7,11], nums2 = [2,4,6],  k = 3

 Return: [1,2],[1,4],[1,6]

 The first 3 pairs are returned from the sequence:
 [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 Example 2:
 Given nums1 = [1,1,2], nums2 = [1,2,3],  k = 2

 Return: [1,1],[1,1]

 The first 2 pairs are returned from the sequence:
 [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 Example 3:
 Given nums1 = [1,2], nums2 = [3],  k = 3

 Return: [1,3],[2,3]

 All possible pairs are returned from the sequence:
 [1,3],[2,3]
 */
public class FindKPairsSmallestSums {

    public class PairComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] i1, int[] i2) {
            if (i1[0] + i1[1] > i2[0] + i2[1]) return 1;
            else if (i1[0] + i1[1] < i2[0] + i2[1]) return -1;
            else return 0;
        }
    }
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        if ((nums1 == null) || (nums2 == null)) return null;
        PairComparator comparator = new PairComparator();
        PriorityQueue<int[]> queue = new PriorityQueue(comparator);

        // only need to loop nums1's first k, and nums2 first k
        // then top k must be exist in k*k combinations
        for (int i = 0; i < Math.min(nums1.length,k); i++) {
            for (int j = 0; j < Math.min(nums2.length, k); j++) {
                int[] pair = new int[2];
                pair[0] = nums1[i];
                pair[1] = nums2[j];

                queue.offer(pair);
            }
        }
        List<int[]> res = new ArrayList<>();
        for (int i = 0 ; i < k ; i ++) {
            if (queue.isEmpty())
                return res;
            res.add(queue.poll());
        }
        return res;
    }
}
