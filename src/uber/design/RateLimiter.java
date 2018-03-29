package uber.design;

/**
 * Created by yingtan on 11/8/17.
 * 359. Logger Rate Limiter
 */
public class RateLimiter {
    int max = 10;
    int[] requestTimes = new int[max];

    boolean isPass(int timestamp) {
        int index = timestamp % max;
        if (requestTimes[index] != -1) {
            // occupied
            if (timestamp - requestTimes[index] >= 1) {
                requestTimes[index] = timestamp;
                return true;
            }
            else {
                return false;
            }
        }
        else {
            requestTimes[index] = timestamp;
            return true;
        }
    }

}
