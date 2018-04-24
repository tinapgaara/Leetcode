package multithread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yingtan on 4/3/18.
 */
public class ThreadPool {
    private int threadNum = 100;
    private ExecutorService exec = Executors.newFixedThreadPool(threadNum);

    public class TaskThread implements Runnable {
        public void run() {
            for (int i = 0 ; i < 100; i ++) {
                System.out.println("hello" + i);
            }
        }
    }
    public void startPool() {
        for (int i = 0 ; i< 10; i ++) {
            exec.submit(new TaskThread(), "thread" + i);
        }
        exec.shutdown();
    }
    public static void main(String[] args) {
        ThreadPool ob = new ThreadPool();
        ob.startPool();
    }
}
