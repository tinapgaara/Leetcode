package greedy;

import java.util.Arrays;

/**
 * Created by yingtan on 1/17/18.
 *
 * We consider assign tasks to workers so as to minimize how long it takes before all task are completed. Take an input as a set of tasks and returns an optimum assignment. For example, [5, 2, 1, 6 , 4, 4], then assign worker 1: [5,2], assign worker 2: [1, 6], assign worker 3: [4,4]
 Solution: Sort input tasks, pair the shortest, second shortest, third shortest with the longest, second longest, third longest.

 */
public class ComputeOptimumAssignmentTask {

    public int maxTimeToCompleteTask(int[] tasks) {
        Arrays.sort(tasks);
        int low = 0;
        int high = tasks.length - 1;
        int max = 0;
        while(low < high) {
            max = Math.max(max, tasks[low] + tasks[high]);
            low ++;
            high --;
        }
        return max;
    }

}
