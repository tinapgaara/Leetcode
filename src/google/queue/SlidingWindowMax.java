package google.queue;

import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Created by yingtan on 8/5/17.
 */
public class SlidingWindowMax {

    public int[] maxSlidingWindow(int[] nums, int k) {

        /*
        我们用双向队列可以在O(N)时间内解决这题。当我们遇到新的数时，将新的数和双向队列的末尾比较，如果末尾比新数小，则把末尾扔掉，直到该队列的末尾比新数大或者队列为空的时候才住手。这样，我们可以保证队列里的元素是从头到尾降序的，由于队列里只有窗口内的数，所以他们其实就是窗口内第一大，第二大，第三大...的数。保持队列里只有窗口内数的方法和上个解法一样，也是每来一个新的把窗口最左边的扔掉，然后把新的加进去。然而由于我们在加新数的时候，已经把很多没用的数给扔了，这样队列头部的数并不一定是窗口最左边的数。这里的技巧是，我们队列中存的是那个数在原数组中的下标，这样我们既可以直到这个数的值，也可以知道该数是不是窗口最左边的数。这里为什么时间复杂度是O(N)呢？因为每个数只可能被操作最多两次，一次是加入队列的时候，一次是因为有别的更大数在后面，所以被扔掉，或者因为出了窗口而被扔掉。
        */
        //时间 O(N) 空间 O(K) double queue 双向队列，可以直接拿头 peek 以及尾 peekLast

        if(nums == null || nums.length == 0) return new int[0];

        LinkedList<Integer> queueIndex = new LinkedList<Integer>();
        int[] res = new int[nums.length + 1 - k];
        for (int i = 0; i < nums.length; i ++) {
            // when more than k, removing mostleft elements, if mostleft number in window is not the max one in queue, we don't need to remove it because removing this will not cause the changing of max values. mostleft number's index = i-k, beacause
            if (! queueIndex.isEmpty() && queueIndex.peekFirst() == (i -k )) {
                queueIndex.poll();
            }
            // insure the queue is decreasing queue
            while (! queueIndex.isEmpty() && nums[queueIndex.peekLast()] < nums[i]) {
                // we need to poll the last one
                queueIndex.removeLast();
            }
            queueIndex.offerLast(i);

            if (i >= k -1) {
                res[i - k + 1] = nums[queueIndex.peekFirst()];
            }
        }
        return res;
    }
}
