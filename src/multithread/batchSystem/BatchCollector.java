package multithread.batchSystem;

import java.awt.*;
import java.util.Collection;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by yingtan on 4/22/18.
 */
import java.util.*;
public class BatchCollector extends Thread{
    LinkedBlockingQueue<Integer> queue;
    int maxsize = 10;
    ArrayList<Integer> collection;
    public BatchCollector(LinkedBlockingQueue<Integer> queue, ArrayList<Integer> collection) {
        this.queue = queue;
        this.collection = collection;
    }
    public void run() {
        while(true) {
            synchronized (collection) {
                while (collection.size() >= maxsize) {
                    try {
                        System.out.println("BatchCollector wait!");
                        collection.wait();// wait send batch to finish
                    } catch (InterruptedException e) {

                    }
                }
                try {
                    /*
                    * poll(long timeout, TimeUnit unit) –
                    * retrieves and removes the head of the queue, waiting up to the specified wait time if necessary for an element to become available.
                    * Returns null after a timeout : after time out, return null.
                    * */
                    Integer p = queue.poll(100000, TimeUnit.MILLISECONDS);
                    collection.add(p);
                    System.out.println("Poll:" + p + ", and add to collection" + collection);
                    collection.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
