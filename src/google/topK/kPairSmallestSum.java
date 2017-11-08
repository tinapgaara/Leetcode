package google.topK;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by yingtan on 1/25/17.
 */
public class kPairSmallestSum {


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

        int curPairNum = 0;
        int lastComparedRow = 0;
        int lastComparedCol = 0;
        boolean isBreak = false;
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                /*
                curPairNum++;
                if (curPairNum > k) {
                    lastComparedRow = i;
                    lastComparedCol = j;
                    isBreak = true;
                    break;
                }
                */
                int[] pair = new int[2];
                pair[0] = nums1[i];
                pair[1] = nums2[j];

                queue.offer(pair);
            }
            if (isBreak) break;
        }
        // compare one further row ???
        /*
        for (int i = lastComparedRow + 1; i < nums1.length; i++) {
            for (int j = 0; j < lastComparedCol; j++) {
                int[] pair = new int[2];
                pair[0] = nums1[i];
                pair[1] = nums2[j];

                queue.offer(pair);
            }
        }
        */
        List<int[]> res = new ArrayList<>();
        for (int i = 0 ; i < k ; i ++) {
            if (queue.isEmpty())
                return res;
            res.add(queue.poll());
        }
        return res;
    }

    public static void main(String[] args) {
        int nums1[] = new int[]{1,1,2};
        int nums2[] = new int[]{1,2,3};
        kPairSmallestSum ob  = new kPairSmallestSum();
        ob.kSmallestPairs(nums1, nums2, 2);
    }
}
