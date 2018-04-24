package multithread.blockingqueue;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by yingtan on 4/22/18.
 */
public class MyBlockingQueue {
    private List<Integer> queue = new LinkedList<>();
    private int limit = 10;
    public MyBlockingQueue(int limit) {
        this.limit = limit;
    }
    public void enqueue(Integer num) throws InterruptedException {
        // if it is full, block it
        synchronized(queue) {
            while(queue.size() == limit) {
                System.out.println("enqueue: queue is full, wait");
                queue.wait();
            }
            queue.add(num);
            queue.notify();
        }

    }
    public Integer dequeue() throws InterruptedException {
        // if it is empty, block it
        Integer res;
        synchronized(queue) {
            while(queue.size() == 0) {
                System.out.println("dequeue: queue is empty, wait");
                queue.wait();
            }
            res = queue.remove(0);
            queue.notifyAll();
        }
        return res;
    }

    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(2);
        System.out.println(queue.offer(3));
        System.out.println(queue.offer(4));
        try {
            queue.offer(5, 5, TimeUnit.SECONDS);
        } catch(InterruptedException e) {
            System.out.println(e);
        }
        //System.out.println();
        MyBlockingQueue queue2 = new MyBlockingQueue(2);
        Thread producer = new Producer(queue2);
        Thread consumer = new Consumer(queue2);
        producer.start();
        consumer.start();
    }
}
