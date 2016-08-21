package google.Imple;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 12/20/15.
 */
public class PriorityQueue {

    private List<Integer> nums;
    private int heapSize;

    public PriorityQueue() {
        this.nums = new ArrayList<>();
        this.heapSize = nums.size();
    }

    public void offer(int num) {
        nums.add(Integer.MIN_VALUE);
        heapSize ++;
        increaseKeyTo(heapSize-1, num);
    }

    public int poll() {
        int res = nums.get(0);
        nums.set(0, nums.get(heapSize-1));
        heapSize --;
        maxHeap(0, heapSize);

        return res;
    }

    public void increaseKeyTo(int index, int value) {
        nums.set(index, value);
        while(nums.get(index) > nums.get(parent(index))) {
            int parentVal = nums.get(parent(index));
            int val = nums.get(index);

            nums.set(parent(index), val);
            nums.set(index, parentVal);

            index = parent(index);
            if (index < 0)  break;
        }
    }

    public void maxHeap(int i, int heapSize) {

        int left = left(i);
        int maxIndex = i;
        if ((left >= 0) && (left < heapSize)) {
            if (nums.get(left) > nums.get(i)) {
                maxIndex = left;
            }
        }
        int right = right(i);
        if ((right >= 0) && (right < heapSize)) {
            if (nums.get(maxIndex) < nums.get(right)) {
                maxIndex = right;
            }
        }
        if (maxIndex != i) {

            int tmp = nums.get(maxIndex);
            nums.set(maxIndex, nums.get(i));
            nums.set(i, tmp);

            maxHeap(maxIndex, heapSize);
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

    public void extractTopK(int k) {
        for (int i = 0 ; i < k ; i ++) {
            int tmp = nums.get(0);
            System.out.println("Top " + i+ " :" + tmp);
            nums.set(0, nums.get(heapSize-1));
            nums.set(heapSize-1, tmp);
            heapSize --;
            maxHeap(0, heapSize);
        }
    }

    public static void main(String[] args) {
        PriorityQueue queue = new PriorityQueue();
        queue.offer(3);
        queue.offer(4);
        queue.offer(6);
        queue.offer(5);
        System.out.println(queue.poll());

        //queue.extractTopK(3);
        System.out.println(queue.poll() + "," + queue.poll());//.;
    }

}
