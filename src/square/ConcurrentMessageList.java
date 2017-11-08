package square;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by yingtan on 10/16/17.
 *
 * 2个app要同时读一个message list，message会不断添加。
 * 每次加app要读的时候都是返回新的之前没有读到的message。如果有很多很多app怎么办

 */
public class ConcurrentMessageList {

    private ConcurrentLinkedQueue<String> msgQueue;
    private StreamMsgThread msger;
    private AppThread[] apps;

    public ConcurrentMessageList() {
        msgQueue = new ConcurrentLinkedQueue<>();
        msger = null;  // TTT
        apps = null;   // TTT
    }

    // TTT
    public void test() {
        Iterator<String> iter = new ArrayList<String>(Arrays.asList("msg1", "msg2", "msg3")).iterator();
        msger = new StreamMsgThread(msgQueue, iter);
        msger.setDaemon(true);
        msger.start();

        apps = new AppThread[2];
        for (int i = 0 ; i < apps.length; i ++) {
            apps[i] = new AppThread(msgQueue);
            apps[i].start();
        }
    }

    // TTT
    public void exitGracely() {
        msger.stopThread();
        for (int i = 0 ; i < apps.length; i ++) {
            apps[i].stopThread();
        }
    }

    private class StreamMsgThread extends Thread {

        private ConcurrentLinkedQueue<String> msgQueue;
        private static final long SLEEP_TIME_MS = 10 * 1000;
        private Iterator<String> msgs;
        private boolean isStop;

        public StreamMsgThread(ConcurrentLinkedQueue<String> msgQueue, Iterator<String> msgs) {
            this.msgQueue = msgQueue;
            this.isStop = false;
            this.msgs = msgs;
        }

        public void stopThread() {
            this.isStop = true;  // TTT
        }

        public void offerNextStream(Iterator<String> msgs) {
            this.msgs = msgs;
        }

        @Override
        public void run() {
            while(! isStop) {
                while(msgs.hasNext()) {
                    String writeMsg = msgs.next();
                    msgQueue.offer(writeMsg);
                    System.out.println("StreamMsgThread id " + Thread.currentThread().getId() + " write msg : " + writeMsg);
                }
                try {
                    Thread.sleep(SLEEP_TIME_MS);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class AppThread extends Thread {

        private ConcurrentLinkedQueue<String> msgQueue;
        private static final long SLEEP_TIME_MS = 10 * 1000;
        private boolean isStop;

        public AppThread(ConcurrentLinkedQueue<String> msgQueue) {
            this.msgQueue = msgQueue;
            this.isStop = false;
        }

        public void stopThread() {
            isStop = true;
        }

        @Override
        public void run() {
            while(! isStop) {
                while(! msgQueue.isEmpty()) {
                    String readMsg = msgQueue.poll();
                    System.out.println("AppThread id " + Thread.currentThread().getId() + " Read msg : " + readMsg);
                }
                try {
                    Thread.sleep(SLEEP_TIME_MS);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ConcurrentMessageList ob = new ConcurrentMessageList();
        ob.test();  // TTT
        ob.exitGracely();  // TTT
    }
}
