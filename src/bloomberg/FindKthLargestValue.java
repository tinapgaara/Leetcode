package bloomberg;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by yingtan on 10/25/15.
 */
public class FindKthLargestValue {

    // 1. use bubble sort
    public int[] findFirstAndSecondLargestValue(int[] arr) {

        for (int i = 0 ; i < 2; i ++) {
            for (int j = i+1; j < arr.length; j ++) {
                if (arr[i] < arr[j]) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
        int[] res = new int[2];
        res[0] = arr[0];
        res[1] = arr[1];

        return res;
    }

    // use bubble sort
    public int[] findKthLargestValue(int[] arr, int k) {
        for (int i = 0 ; i < k; i ++) {
            for (int j = i+1; j < arr.length; j ++) {
                if (arr[i] < arr[j]) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
        return arr;
    }

    // use PriorityQueue
    public int[] findKthLargestValue_priorqueue(int[] arr, int k) {
        PriorQueueComparator comparator = new PriorQueueComparator();
        PriorityQueue<Integer> queue = new PriorityQueue<>(comparator);

        for (int i = 0 ; i < arr.length; i ++) {
            queue.offer(arr[i]);
        }

        int[] res = new int[k];
        for (int i = 0 ; i < k ; i ++) {
            res[i] = queue.poll();
        }
        return res;
    }

    // use heap ??
    /*
    public int[] findKthLargestValue_heap(int[] arr, int k) {
        buildHeap(arr);

        return res;
    }
    */

    public class PriorQueueComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer i1, Integer i2) {
            if (i1.intValue() < i2.intValue()) return 1;
            else if (i1.intValue() > i2.intValue()) return -1;
            else return 0;
        }
    }

    public static void main(String[] args) {
        FindKthLargestValue ob = new FindKthLargestValue();
        int[] arr = new int[]{4,1,5,2,6};
        ob.findFirstAndSecondLargestValue(arr);
        ob.findKthLargestValue_priorqueue(arr, 3);
    }
}
