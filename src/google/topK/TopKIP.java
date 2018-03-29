package google.topK;

import java.util.*;

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

    // 2017.11.15
    Map<String, Integer> ipCount = new HashMap<>();
    public class IPComparator2 implements Comparator<String> {
        public int compare(String ip1, String ip2) {
            return ipCount.get(ip1) - ipCount.get(ip2);
        }
    }
    IPComparator2 p = new IPComparator2();
    PriorityQueue<String> ipQueue = new PriorityQueue<String>(p);
    int k = 3;
    public void update2(String ip) {
        if (ipCount.containsKey(ip)) {
            ipCount.put(ip, ipCount.get(ip) + 1);
        }
        else {
            ipCount.put(ip, 1);
            ipQueue.offer(ip);
            if (ipQueue.size() > k) {
                ipQueue.poll();
            }
        }
    }
    public void extractTopK2() {
        List<String> res = new ArrayList<>();
        while(! ipQueue.isEmpty()) {
            String s = ipQueue.poll();
            res.add(0, s);
            System.out.println(s + ", count:" + ipCount.get(s));
        }

    }

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
        ob.update2("hello");
        ob.update2("hello");
        ob.update2("why");
        ob.update2("me");
        ob.update2("me");
        ob.update2("hello");

        ob.extractTopK2();
    }

}
