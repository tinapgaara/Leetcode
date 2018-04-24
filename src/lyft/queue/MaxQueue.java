package lyft.queue;

/**
 * Created by yingtan on 4/11/18.
 */
import java.util.*;
public class MaxQueue {
    // max operation: o(1)
    public Queue<Integer> queue;
    public Deque<Integer> doubleLinkedList;

    public MaxQueue(Queue<Integer> queue) {
        this.queue = queue;
        doubleLinkedList = new ArrayDeque<>();
    }
    public void enqueue(int x) {
        queue.offer(x);
        while(! doubleLinkedList.isEmpty() && doubleLinkedList.peekLast() < x) {
            doubleLinkedList.pollLast();
        }
        doubleLinkedList.addLast(x); // decreasing linkedlist
    }
    public int dequeue() {
        int x = queue.poll();
        if (x == doubleLinkedList.peekFirst()) {
            doubleLinkedList.pollFirst();
        }
        return x;
    }
    public int max() {
        // o(1)
        return doubleLinkedList.peekFirst();
    }
}
