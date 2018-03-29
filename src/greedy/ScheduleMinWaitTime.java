package greedy;

import java.util.Arrays;

/**
 * Created by yingtan on 1/17/18.
 *
 * A database has to respond to a set of client SQL queries. The service time required for each query is known in advance. For the application, queries must be processed by the db once at a time and can be donw in any order. The time a query waits before its turn comes is called is waiting time.
 For example: [2 ,5, 1, 3]. Task 2 need to wait for 2, Task 3 need to wait for (2 + 5), Task 4 need to wait for (2 + 5 + 1). Total wait time: 2 + (2 + 5) + (2 + 5 + 1) = 17.
 Solution :Sort the array, cal from index 0.

 */
public class ScheduleMinWaitTime {

    public int minWaitTime(int[] queries) {
        Arrays.sort(queries);
        int total = 0;
        int sum = 0;
        for (int i = 1 ; i < queries.length; i ++) {
            int time = queries[i - 1];
            sum = sum + time;
            total = sum + total;
        }
        return total;
    }
    public static void main(String[] args) {
        ScheduleMinWaitTime ob = new ScheduleMinWaitTime();
        int[] nums = {2,5,1,3};
        System.out.println(ob.minWaitTime(nums));
    }
}
