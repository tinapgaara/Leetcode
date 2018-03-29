package facebook.greedy;

import java.util.HashMap;

/**
 * Created by yingtan on 2/8/18.
 *
 * 621. Task Scheduler
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks.Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

 However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.

 You need to return the least number of intervals the CPU will take to finish all the given tasks.

 Example 1:
 Input: tasks = ["A","A","A","B","B","B"], n = 2
 Output: 8
 Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.

 */
import java.util.*;
public class TaskScheduler {
    // using priorityQueue, when want to sort queue according to Map<Character, Integer>, using PriorityQueue<Map.Entry<Character, Integer>> as member
    public int leastInterval(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0) return 0;
        Map<Character, Integer> countMap = new HashMap<>();
        for (char task : tasks) {
            if (countMap.containsKey(task)) {
                countMap.put(task, countMap.get(task) + 1);
            }
            else {
                countMap.put(task, 1);
            }
        }
        TaskComparator comp = new TaskComparator();
        // important !!!! need to add Map.Entry<Character, Integer> to queue
        PriorityQueue<Map.Entry<Character, Integer>> queue = new PriorityQueue<>(comp);
        queue.addAll(countMap.entrySet());
        int interval = n + 1;
        int count = 0;
        while(! queue.isEmpty()) {
            int k = interval;
            List<Map.Entry<Character, Integer>> tmp = new ArrayList<>();
            while(k > 0 && ! queue.isEmpty()) {
                Map.Entry<Character, Integer> top = queue.poll();
                count ++;
                k --;
                if (top.getValue() > 1) {
                    top.setValue(top.getValue() - 1);
                    tmp.add(top);
                }
            }
            queue.addAll(tmp);
            if (queue.isEmpty()) {
                break;// important !!!! for the last tasks, don't need to add idle as the last one
            }
            count = count + k; // idle
        }
        return count;
    }
    public class TaskComparator implements Comparator<Map.Entry<Character, Integer>> {
        public int compare(Map.Entry<Character, Integer> entry1, Map.Entry<Character, Integer> entry2) {
            if (entry1.getValue() == entry2.getValue()) {
                return entry1.getKey() - entry2.getKey();
            }
            else
                return entry2.getValue() - entry1.getValue();
        }
    }
}
