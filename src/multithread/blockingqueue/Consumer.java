package multithread.blockingqueue;

/**
 * Created by yingtan on 4/23/18.
 */
public class Consumer extends Thread{
    MyBlockingQueue queue;
    public Consumer(MyBlockingQueue queue) {
        this.queue = queue;
    }
    public void run() {
        while(true) {
            for (int i = 1; i <= 10 ; i++) {
                try {
                    Integer num = queue.dequeue();
                    System.out.println("Dequeue:" + num);
                } catch (InterruptedException e) {

                }
            }
            try {
                Thread.sleep(1200);
            } catch (InterruptedException e) {

            }

        }
    }
}
