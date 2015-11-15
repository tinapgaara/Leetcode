package bloomberg.design.callNTimesCheck;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yingtan on 11/15/15.
 */
/*
* 我有一个function: doSomething(); 这个function有一个限制十秒内只能被call10次，如果十秒内多于10次，系统会报错。
如何设计一个报错的功

解法：size 10的queue. 没超过10的size, enqueue new call, else dequeue 第一个call. 并比较当前call和第一个call的时间，
超过10秒，输出warning
* */
public class CallNTimesIn10Seconds {

    private Queue<Long> queue;
    private int maxCallTimes;

    public CallNTimesIn10Seconds() {
        queue = new LinkedList<Long>();
    }

    public boolean checkError(long newcall) {
        int size = queue.size();
        if (size < maxCallTimes ) {
            queue.offer(newcall);
        }
        else {
            long oldestcall = queue.poll();
            if ((newcall - oldestcall) > 10 * 10000) { // 10s
                return true;
            }
            queue.offer(newcall);
        }
        return true;
    }

}
