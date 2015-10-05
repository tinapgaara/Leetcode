package sort;

/**
 * Created by yingtan on 10/5/15.
 */
public class HeapSort {

    // running time :  O(nlogn)
    // place: in place
    /*
    * Heap:
    * Max-heap :  for heapsort
    * Min-heap :  for priority queues
    * */

    int parent(int i) {
        return ((i-1) / 2);
    }
    int left (int i) {
        return 2 * i + 1;
    }
    int right (int i) {
        return 2*i + 2;
    }

    // heap's root is at ith root, and i refers to a parent
    // ascending
    public void maxHeap(int[] num, int i, int heapSize) {
        int leftNo = left(i);
        int rightNo = right(i);
        int maxNo;
        if ( (leftNo < heapSize) && (num[leftNo] > num[i]) ) {
            maxNo = leftNo;
        }
        else {
            maxNo = i;
        }
        if ((rightNo < heapSize) && (num[rightNo] > num[maxNo]) ) maxNo = rightNo;
        if (maxNo != i) {
            int tmp = num[maxNo];
            num[maxNo] = num[i];
            num[i] = tmp;

            maxHeap(num, maxNo, heapSize);
        }
    }

    // time : O(nlogn)
    public void buildMaxHeap(int[] num) {
        int heapSize = num.length;
        int innerNodeNum = heapSize / 2;
        for (int i = innerNodeNum; i >= 0 ; i --) {
            maxHeap(num, i, heapSize);
        }
    }

    // time: O(nlogn)
    public void maxHeapSort(int[] num) {
        buildMaxHeap(num); // O(nlogn)
        int heapSize = num.length;
        for (int i = num.length -1 ; i >= 0 ; i --) { // O(nlogn)
            int tmp = num[i];
            num[i] = num[0];
            num[0] = tmp;

            heapSize --;
            maxHeap(num, 0, heapSize);
        }
    }

    /*
    * For min heap
    *
    * */
    public void minHeap(int[] num, int heapSize, int i) {
        int left = left(i);
        int right = right(i);
        int minNo;
        if ( (left < heapSize) && (num[left] < num[i]) ) {
            minNo = left;
        }
        else minNo = i;

        if ( (right < heapSize) && (num[right] < num[minNo])) {
            minNo = right;
        }
        if (minNo != i) {
            int tmp = num[i];
            num[i] = num[minNo];
            num[minNo] = tmp;

            minHeap(num, heapSize, minNo);
        }
    }

    public void buildMinHeap(int[] num) {
        int heapSize = num.length;
        for (int i = num.length / 2; i >= 0 ; i --) {
            minHeap(num, i, heapSize);
        }
    }

    public void minHeapSort(int[] num) {
        buildMinHeap(num);
        int heapSize = num.length;

        for (int i = num.length-1; i >= 0 ; i --) {
            int tmp = num[0];
            num[0] = num[i];
            num[i] = tmp;

            heapSize --;
            minHeap(num, heapSize, 0);
        }
    }

    public static void main(String[] args) {
        HeapSort ob = new HeapSort();
        int[] nums = new int[]{1,5,2,4};
        ob.minHeapSort(nums);
    }
}
