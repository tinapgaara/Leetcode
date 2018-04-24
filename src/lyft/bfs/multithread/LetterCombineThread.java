package lyft.bfs.multithread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class LetterCombineThread extends Thread {
    BlockingQueue<String> queue;
    boolean isStop;
    String[] letters;
    String digits;
    long MAX_WAIT_TIME = 10; // ms
    public LetterCombineThread(BlockingQueue queue, String[] letters, String digits) {
        this.queue = queue;
        isStop = false;
        this.letters = letters;
        this.digits = digits;
    }
    public void run() {
        while(! isStop) {
            while(! queue.isEmpty()) {
                try {
                    String str = queue.poll(MAX_WAIT_TIME, TimeUnit.MILLISECONDS);
                    if (str != null) {
                        if (str.length() == digits.length()) {
                            System.out.println("result:" + str +  "," + System.currentTimeMillis());
                            continue;
                        }
                        int curIndex = digits.charAt(str.length()) - '0';
                        String curStr = letters[curIndex];
                        for (char ch : curStr.toCharArray()) {
                            queue.offer(str + ch);
                        }
                    }

                } catch(InterruptedException exeption) {
                    isStop = true;
                    break;
                }
            }
        }
    }
    public void setStop() {
        isStop = true;
    }
}
