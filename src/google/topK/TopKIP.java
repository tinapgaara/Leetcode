package google.topK;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by yingtan on 11/26/15.
 */
/*
* 个SYstem DESIGN, 很常规的MAIL SYSTEM找TOP K% IP,
* mail is in stream
*
* 海量日志数据，提取出某日访问google次数最多的那个IP。
* */
public class TopKIP {

    public class IP {
        int count;
        String name;

        public IP(int count, String name) {
            this.count = count;
            this.name = name;
        }
    }

    public class IPComparator implements Comparator<IP> {
        public int compare(IP i1, IP i2) {
            if (i1.count < i2.count) { // return i2: 1
                return 1;
            }
            else {
                return -1;
            }
        }
    }

    public PriorityQueue<IP> queue;
    public Map<String, Integer> counts;
    public int windowSize;
    public int curSize;

    public TopKIP(int k) {
        IPComparator comparator = new IPComparator();
        queue = new PriorityQueue(comparator); // minHeap with Size K.
        counts = new HashMap<>();
        windowSize = k; // restrict size to be k
        curSize = 0;
    }

    public void update(String IP) {
        int count = 0;
        if (counts.containsKey(IP)) {
           count = counts.get(IP) + 1;
           counts.put(IP, count);
        }
        else {
           count = 1;
           counts.put(IP, count);
        }

        if (curSize < windowSize) {
            // queue.decreaseKey(IP, count);
            queue.offer(new IP(count, IP));
            curSize ++;
        }
        else {
            IP top = queue.peek();
            if (count > top.count) {
                queue.poll();
                // queue.decreaseKey(IP, count);
                queue.offer(new IP(count, IP));
            }
        }
    }

    public void extractTopK() {
        for (int i = 0 ; i < curSize; i ++) {
            IP top = queue.poll();
            System.out.println("Top " + i + " 's IP:" + top.name + " ; count:" + top.count);
        }
    }

    public static void main(String[] args) {
        TopKIP ob = new TopKIP(4);
        ob.update("hello");
        ob.update("hello");
        ob.update("why");

        ob.extractTopK();
    }

}
