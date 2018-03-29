package queue;

import bloomberg.design.drawboard.Circular;

/**
 * Created by yingtan on 1/20/18.
 */
public class CircularQueue {
    public int[] nums;
    public int head;
    public int tail;
    public int size;
    public CircularQueue(int maxsize) {
        head = 0;
        tail = 0;
        size = 0;
        nums = new int[maxsize];
    }
    public void enqueue(int num) {
        // 1. check if is full
        if ((head == 0 && tail == size -1) ||
                (tail == head - 1)) {
            // FULL !!!
        }
        else {
            nums[tail] = num;
            tail = (tail + 1) % nums.length;
            size ++;
        }
    }
    public void dequeue() {
        // check if is empty
        if (head == tail) {
            // this is empty, can not dequeue
        }
        else {
            head = (head - 1) % nums.length;
            size --;
        }
    }
}
