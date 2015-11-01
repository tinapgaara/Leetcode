package bloomberg.Impl;

/**
 * Created by yingtan on 10/26/15.
 */
public class Heap {

    private int[] nums;
    private int maxHeapSize;
    private int heapSize;

    public Heap(int[] nums) {
        this.nums = nums;
        maxHeapSize = nums.length;
        heapSize = maxHeapSize;

        buildHeap();
    }

    public int extractMax() { //o(1)
        int res = nums[0];
        heapSize --;

        nums[0] = nums[heapSize-1];

        maxHeap(0, heapSize);

        return res;
    }

    public void increaseKeyToK(int index, int k) { // o(logn) : height

        nums[index] = k;
        while ((index >= 0) && (nums[index] > nums[parent(index)])) {
            int parent = parent(index);
            int tmp = nums[index];
            nums[index] = nums[parent];
            nums[parent] = tmp;

            index = parent;
        }
    }

    public void insert(int value) { // o(logn)
        heapSize ++;
        nums[heapSize] = Integer.MIN_VALUE;
        increaseKeyToK(heapSize, value);
    }

    public void extractTopK(int k) { // k(logn)
        for (int i = maxHeapSize-1; i >= maxHeapSize-k; i --) {
            int tmp = nums[i];
            nums[i] = nums[0];
            nums[0] = tmp;

            heapSize --;

            maxHeap(0, heapSize);
        }
    }

    public void buildHeap() { // n * o(logn)
        for (int i = nums.length/ 2; i >= 0 ; i --) {
            maxHeap(i, nums.length);
        }
    }

    public void maxHeap(int i, int heapSize) { //  o(logn)
        int left = left(i);
        int right = right(i);

        int maxNo = i;
        if ((left < heapSize) && (nums[left] > nums[i])) {
            maxNo = left;
        }
        if ((right < heapSize) && (nums[right] > nums[maxNo])) {
            maxNo = right;
        }

        if (maxNo != i) {
            int tmp = nums[maxNo];
            nums[maxNo] = nums[i];
            nums[i] = tmp;

            maxHeap(maxNo, heapSize);
        }
    }

    public int left(int index) {
        return 2 * index + 1;
    }

    public int right (int index) {
        return 2 * index + 2;
    }

    public int parent(int index) {
        return (index)/ 2;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1,4,2,5,7,0};
        Heap ob = new Heap(nums);
        // Important !!!: Find k top largest elements: Time : (O(n) + O(k * logn))
        int k = 3;
        int[] res = new int[k];
        ob.extractTopK(k);
        /*
        for (int i = 0 ; i < k ; i ++) {
            res[i] = ob.extractTopK(k);
        }
        */
    }

}
