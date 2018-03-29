package facebook.stack;

/**
 * Created by yingtan on 2/13/18.
 */
import java.util.*;
public class ExclusiveTimeOfFunctions {

    public int[] exclusiveTime(int n, List<String> logs) {
        // "0:start:0" "0:end:6"
        Stack<Integer> tasks = new Stack<>();
        int[] res = new int[n];
        int prevTime = 0;
        for (String log: logs) {
            String[] parts = log.split(":");
            int id = Integer.parseInt(parts[0]);
            String status = parts[1];
            int time = Integer.parseInt(parts[2]);
            if (! tasks.isEmpty()) {
                int prevTask = tasks.peek();
                // each two log, always cal the time difference
                res[prevTask] = res[prevTask] + time - prevTime;
            }
            prevTime = time;
            if (status.equals("start")) {
                tasks.push(id);
            }
            else {
                int finishedTask = tasks.pop();
                res[finishedTask] = res[finishedTask] + 1;
                // important !!! this is the end, so prevTime ++
                // for task 0: (2- 0) + (6-6) + 1
                // for task 1: (5-2) + 1
                prevTime ++;
            }
        }
        return res;
    }

}
