package google.Imple;

/**
 * Created by yingtan on 12/20/15.
 */
public class Heap {

    private int[] nums;
    private int heapSize;

    public Heap(int[] nums) {
        this.nums = nums;
        this.heapSize = nums.length;

        buildHeap(nums);
    }

    public void buildHeap(int[] nums) {
        for (int i = nums.length/2; i >= 0 ; i --) {
            maxHeap(i, nums.length);
        }
    }

    public void maxHeap(int i, int heapSize) {
        int left = left(i);
        int maxIndex = i;
        if ((left >= 0) && (left < heapSize)) {
            if (nums[left] > nums[i]) {
                maxIndex = left;
            }
        }
        int right = right(i);
        if ((right >= 0) && (right < heapSize)) {
            if (nums[maxIndex] < nums[right]) {
                maxIndex = right;
            }
        }
        if (maxIndex != i) {
            int tmp = nums[maxIndex];
            nums[maxIndex] = nums[i];
            nums[i] = tmp;

            maxHeap(maxIndex, heapSize);
        }
    }

    public void extractTopK(int k) {
        for (int i = 0 ; i < k ; i ++) {
            int tmp = nums[0];
            System.out.println("Top " + i+ " :" + tmp);
            nums[0] = nums[heapSize-1];
            nums[heapSize-1] = tmp;
            heapSize --;
            maxHeap(0, heapSize);
        }
    }

    public int parent(int x) {
        return (x-1)/ 2;
    }

    public int left(int x) {
        return 2*x + 1;
    }

    public int right(int x) {
        return 2*x + 2;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 4, 2, 5, 7, 0};
        Heap ob = new Heap(nums);
        // Important !!!: Find k top largest elements: Time : (O(n) + O(k * logn))
        int k = 3;
        ob.extractTopK(k);
    }

}
