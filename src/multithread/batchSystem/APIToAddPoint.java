package multithread.batchSystem;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by yingtan on 4/22/18.
 */
public class APIToAddPoint extends Thread {
    LinkedBlockingQueue queue ;
    public APIToAddPoint(LinkedBlockingQueue<Integer> queue) {
        this.queue = queue;
    }
    public void addQueue(Integer p) {
        queue.offer(p);
    }
    public void run() {
        while(true) {
            for (int i = 0; i < 10; i ++) {
                System.out.println("Add :" + i);
                addQueue(i);
            }
            try {
                Thread.sleep(10000);
            } catch (Exception e) {

            }
        }
    }
    public static void main(String[] args) {
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
        ArrayList<Integer> col = new ArrayList<>();
        Thread add = new APIToAddPoint(queue);
        Thread batchcollector = new BatchCollector(queue, col);
        Thread sender = new BatchSender(col);
        add.start();
        batchcollector.start();
        sender.start();
    }
}
