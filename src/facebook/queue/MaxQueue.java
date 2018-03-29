package facebook.queue;
import java.util.*;
public class MaxQueue {
    Deque<Integer> doubleLinkedList ;
    Queue<Integer> queue;
    public MaxQueue() {
        doubleLinkedList = new ArrayDeque<>();
    }
    public int getMax() {
        return doubleLinkedList.getFirst();
    }
    public void enqueue(int number) {
        queue.offer(number);
        while(! doubleLinkedList.isEmpty() && doubleLinkedList.getLast() < number) {
            doubleLinkedList.pollLast();
        }
        doubleLinkedList.addLast(number);
    }
    public int dequeue() {
        int first = queue.poll();
        if (doubleLinkedList.peekFirst() == first) {
            doubleLinkedList.removeFirst();
        }
        return first;
    }

}
