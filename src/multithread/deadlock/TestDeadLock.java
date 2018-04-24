package multithread.deadlock;

/**
 * Created by yingtan on 4/23/18.
 */
public class TestDeadLock {
    static Object lock1 = new Object();
    static Object lock2 = new Object();
    public static class Thread1 extends Thread {
        public void run() {
            synchronized (lock1) {
                System.out.println("Thread1 hold lock1");
                try {
                    Thread1.sleep(1000);
                } catch (InterruptedException e) {

                }
                System.out.println("Thread1 waiting for lock2");
                synchronized (lock2) {
                    System.out.println("Thread2 hold lock2");
                }
            }
        }
    }
    public static class Thread2 extends Thread {
        public void run() {
            synchronized (lock2) {
                System.out.println("Thread2 hold lock2");
                try {
                    Thread2.sleep(1000);
                } catch (InterruptedException e) {

                }
                System.out.println("Thread2 waiting for lock1");
                synchronized (lock1) {
                    System.out.println("Thread2 hold lock1");
                }
            }
        }
    }
    public static void main(String[] args) {
        Thread t1 = new Thread1();
        Thread t2 = new Thread2();
        t1.start();
        t2.start();
    }
}
