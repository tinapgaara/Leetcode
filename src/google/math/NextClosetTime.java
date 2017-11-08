package google.math;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yingtan on 10/28/17.
 *
 * 681. Next Closest Time
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits. There is no limit on how many times a digit can be reused.

 You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.

 Example 1:

 Input: "19:34"
 Output: "19:39"
 Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later.  It is not 19:33, because this occurs 23 hours and 59 minutes later.
 Example 2:

 Input: "23:59"
 Output: "22:22"
 Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22. It may be assumed that the ret
 */
public class NextClosetTime {

    // we can try to increase the minute and the hour one by one. If all these four digits of the next time is in hashset, we find it and output! because these four digits are all reused.
    public String nextClosestTime(String time) {
        if (time == null || time.length() == 0) return "";
        String[] val = time.split(":");
        Set<Integer> set = new HashSet<>();
        int hour = Integer.parseInt(val[0]);
        int minu = Integer.parseInt(val[1]);
        set.add(hour / 10);
        set.add(hour % 10);
        set.add(minu / 10);
        set.add(minu % 10);
        int[] times = new int[]{hour, minu};
        next(times);
        while(! isValid(times, set)) {
            next(times);
        }
        return constructRes(times);
    }

    public boolean isValid(int[] time, Set<Integer> set) {
        int hour = time[0];
        int minu = time[1];
        if (set.contains(hour / 10) && set.contains(hour % 10) && set.contains(minu / 10) && set.contains(minu % 10)) {
            return true;
        }
        else {
            return false;
        }
    }

    public void next(int[] time) {
        int hour = time[0];
        int minu = time[1];
        minu ++;
        if (minu >= 60) {
            minu = 0;
            hour ++;
            if (hour >= 24) {
                hour = 0;
            }
        }
        time[0] = hour;
        time[1] = minu;
    }

    public String constructRes(int[] time) {
        String res = "";
        if(time[0] >= 0 && time[0] <= 9) {
            res = res + "0" + time[0];
        }
        else {
            res = res + time[0];
        }
        res = res + ":";
        if(time[1] >= 0 && time[1] <= 9) {
            res = res + "0" + time[1];
        }
        else {
            res = res + time[1];
        }
        return res;
    }
}
