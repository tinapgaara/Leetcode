package multithread.blockingqueue;

/**
 * Created by yingtan on 4/23/18.
 */
public class Producer extends Thread{
    MyBlockingQueue queue;
    public Producer(MyBlockingQueue queue) {
        this.queue = queue;
    }
    public void run() {
        while(true) {
            for (int i = 1; i <= 10 ; i++) {
                try {
                    queue.enqueue(i);
                    System.out.println("Enqueue:" + i);
                } catch (InterruptedException e) {

                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }

        }
    }
}
