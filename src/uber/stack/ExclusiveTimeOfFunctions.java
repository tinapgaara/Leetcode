package uber.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Created by yingtan on 12/3/17.
 *
 * Given the running logs of n functions that are executed in a nonpreemptive single threaded CPU, find the exclusive time of these functions.

 Each function has a unique id, start from 0 to n-1. A function may be called recursively or by another function.

 A log is a string has this format : function_id:start_or_end:timestamp. For example, "0:start:0" means function 0 starts from the very beginning of time 0. "0:end:0" means function 0 ends to the very end of time 0.

 Exclusive time of a function is defined as the time spent within this function, the time spent by calling other functions should not be considered as this function's exclusive time. You should return the exclusive time of each function sorted by their function id.

 Example 1:
 Input:
 n = 2
 logs =
 ["0:start:0",
 "1:start:2",
 "1:end:5",
 "0:end:6"]
 Output:[3, 4]
 Explanation:
 Function 0 starts at time 0, then it executes 2 units of time and reaches the end of time 1.
 Now function 0 calls function 1, function 1 starts at time 2, executes 4 units of time and end at time 5.
 Function 0 is running again at time 6, and also end at the time 6, thus executes 1 unit of time.
 So function 0 totally execute 2 + 1 = 3 units of time, and function 1 totally execute 4 units of time.
 */
public class ExclusiveTimeOfFunctions {

    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        if (logs == null) return res;
        Stack<Integer> stack = new Stack<>();
        int runningFuncId = 0; int prevTime = 0;
        for (String log : logs) {
            String[] parts = log.split(":");
            Integer funcId = Integer.parseInt(parts[0]);
            boolean start = parts[1].equals("start");
            int time = Integer.parseInt(parts[2]);
            if (! start) {
                time ++;
            }
            // before pushing the next funct, calculate the time diff right now
            res[runningFuncId] += time - prevTime;
            if (start) {
                stack.push(funcId);
                runningFuncId = funcId;
            }
            else {
                stack.pop();
                if (! stack.isEmpty()) {
                    // has the function running right now
                    runningFuncId = stack.peek();
                }
            }
            prevTime = time;
        }
        return res;
    }

    public static void main(String[] args) {
        ExclusiveTimeOfFunctions ob = new ExclusiveTimeOfFunctions();
        List<String> strs = new ArrayList<>(Arrays.asList("0:start:0","0:end:0","1:start:1","1:end:1","2:start:2","2:end:2","2:start:3","2:end:3"));
        ob.exclusiveTime(3,strs);
    }
}
