package queue;

/**
 * Created by yingtan on 1/20/18.
 */
import java.util.*;
public class QueueWithMax {
    public Queue<Integer> queue;
    public Deque<Integer> doubleLinkedList = new ArrayDeque<>();

    public void enqueue(int x) {
        queue.offer(x);
        // make sure doublelinkedlist store decreasing numbers, this is max value among numbers righter than this number
        while(! doubleLinkedList.isEmpty() && doubleLinkedList.peekLast() < x) {
            doubleLinkedList.pollLast();
        }
        doubleLinkedList.offerLast(x);
    }
    public int dequeue() {
        int x = queue.poll();
        if (x == doubleLinkedList.peekFirst()) {
            // if this removed element is the max
            doubleLinkedList.pollFirst();
        }
        return x;
    }
    public int max() {
        return doubleLinkedList.peekFirst();
    }
}
