package multithread.batchSystem;

import java.awt.*;
import java.util.*;

/**
 * Created by yingtan on 4/22/18.
 */
public class BatchSender extends Thread {
    ArrayList<Integer> collection;
    int maxsize = 10;
    public BatchSender(ArrayList<Integer> collection) {
        this.collection = collection;
    }
    public void run() {
        while(true) {
            synchronized (collection) {
                while(collection.size() < maxsize) {
                    try {
                        System.out.println("Batchsender wait!");
                        collection.wait();
                    }
                    catch(InterruptedException e) {

                    }
                }
                // send batch
                for (int i = 0 ; i < collection.size(); i ++) {
                    System.out.println("Batch sender send  point:" + i);
                }
                collection.clear();
                collection.notifyAll();
            }
        }
    }
}
