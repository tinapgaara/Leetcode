package apple.design;

import java.util.HashMap;
import java.util.Map;
/*
*
* https://blog.figma.com/an-alternative-approach-to-rate-limiting-f8a06cf7c94c
* https://www.careercup.com/question?id=5147519440912384
* */
public class RateLimiter {
    int maxRequests;
    int interval; // seconds
    Map<Integer, Integer> secondToRequests = new HashMap<>();
    public RateLimiter(int maxRequests) {
        this.maxRequests = maxRequests;
        this.interval = 60; // 60 seconds window
    }
    public boolean allowRequest(int curTime) {
        int sum = 0;
        int secondInPerMinute = curTime; // extract second unit from the current time
        // loop interval, cal sum of requests per interval
        for (int i = 0 ; i < interval; i ++) { // query 60 times.
            int key = secondInPerMinute - i;
            if (secondToRequests.containsKey(key)) {
                sum = sum + secondToRequests.get(key);
            }
        }
        if (sum < maxRequests) {
            // allow
            secondToRequests.put(secondInPerMinute, secondToRequests.get(secondInPerMinute) + 1);
            return true;
        }
        else {
            return false;
        }
    }
}
