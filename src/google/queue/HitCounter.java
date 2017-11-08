package google.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yingtan on 7/31/17.
 * 362. Design Hit Counter
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Design a hit counter which counts the number of hits received in the past 5 minutes.

 Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made to the system in chronological order (ie, the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1.

 It is possible that several hits arrive roughly at the same time.

 Example:
 HitCounter counter = new HitCounter();

 // hit at timestamp 1.
 counter.hit(1);

 // hit at timestamp 2.
 counter.hit(2);

 // hit at timestamp 3.
 counter.hit(3);

 // get hits at timestamp 4, should return 3.
 counter.getHits(4);

 // hit at timestamp 300.
 counter.hit(300);

 // get hits at timestamp 300, should return 4.
 counter.getHits(300);

 // get hits at timestamp 301, should return 3.
 counter.getHits(301);
 Follow up:
 What if the number of hits per second could be very large? Does your design scale?

 Credits:
 Special thanks to @elmirap for adding this problem and creating all test cases.

 Seen this question in a real interview before?   Yes  No
 Difficulty:Medium
 Total Accepted:15.9K
 Total Submissions:29.7K
 Contributor: LeetCode
 Companies
 Dropbox Google
 Related Topics
 Design
 Similar Questions
 Logger Rate Limiter


sol 1:
 这道题让我们设计一个点击计数器，能够返回五分钟内的点击数，
 提示了有可能同一时间内有多次点击。由于操作都是按时间顺序的，下一次的时间戳都会大于等于本次的时间戳，
 那么最直接的方法就是用一个队列queue，每次点击时都将当前时间戳加入queue中，
 然后在需要获取点击数时，我们从队列开头开始看，如果开头的时间戳在5分钟以外了，
 就删掉，直到开头的时间戳在5分钟以内停止，然后返回queue的元素个数即为所求的点击数，参见代码如下：

 Follow up:
 What if the number of hits per second could be very large? Does your design scale?
 由于Follow up中说每秒中会有很多点击，下面这种方法就比较巧妙了，定义了两个大小为300的一维数组times和hits，
 分别用来保存时间戳和点击数，在点击函数中，将时间戳对300取余，
 然后看此位置中之前保存的时间戳和当前的时间戳是否一样，一样说明是同一个时间戳，
 那么对应的点击数自增1，如果不一样，说明已经过了五分钟了，
 那么将对应的点击数重置为1。
 那么在返回点击数时，我们需要遍历times数组，找出所有在5分中内的位置，
 然后把hits中对应位置的点击数都加起来即可，参见代码如下：

 */
public class HitCounter {


    public Queue<Integer> queue;
    // sol2 : follow up
    // sol 2
    int[] timestamps = new int[300];
    int[] count = new int[300];

    /** Initialize your data structure here. */
    public HitCounter() {
        queue = new LinkedList<Integer>();
    }

    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        queue.offer(timestamp);
    }

    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        while ( (! queue.isEmpty()) && (timestamp - queue.peek() >= 300) ) {
            queue.poll();
        }
        return queue.size();
    }




    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    public void hit2(int timestamp) {

        int index = timestamp % 300;
        // timestamp 0 -> 0, timestamp 1- > 1, timestamp 300 -> 300  , then 300 != 0 , this means is over 60 min
        if (timestamps[index] != timestamp) {

            // reset
            timestamps[index] = timestamp;
            count[index] = 1;
        }
        else {
            count[index] ++;
        }
    }

    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits2(int timestamp) {


        int res = 0;
        for (int i = 0 ; i < 300; i ++) {
            if (timestamp - timestamps[i] < 300) {
                res = res + count[i];
            }
        }
        return res;
    }
}
