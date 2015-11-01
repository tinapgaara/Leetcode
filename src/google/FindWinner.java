package google;

import sun.rmi.runtime.Log;

import java.util.*;

/**
 * Created by yingtan on 11/1/15.
 */
public class FindWinner {
    public class LogEntry {
        String candidate;
        int time;

        public LogEntry(String candidate, int time) {
            this.candidate = candidate;
            this.time = time;
        }
    }

    // Solution 1: use hashmap, need to scan twice, can we do in scanning once ?
    public String findWinnder(List<LogEntry> entries, int time) {
        HashMap<String, Integer> counts = new HashMap<>();
        for (LogEntry entry : entries) {
            if (entry.time <= time) {
                if (counts.containsKey(entry.candidate)) {
                    int count = counts.get(entry.candidate);
                    counts.put(entry.candidate, count + 1);
                }
                else {
                    counts.put(entry.candidate, 1);
                }
            }
        }

        int maxCount = 0;
        String winner = "";
        for (Map.Entry<String, Integer> entry :  counts.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                winner = entry.getKey();
            }
        }
        return winner;
    }

    // Solution 1: use priorityQueue
    public String[] findKWinners_priorQueue(List<LogEntry> entries, int time, int k) {
        HashMap<String, Integer> counts = new HashMap<>();
        for (LogEntry entry : entries) {
            if (entry.time <= time) {
                if (counts.containsKey(entry.candidate)) {
                    int count = counts.get(entry.candidate);
                    counts.put(entry.candidate, count + 1);
                }
                else {
                    counts.put(entry.candidate, 1);
                }
            }
        }

        LogEntryComparator comparator = new LogEntryComparator();
        PriorityQueue<LogEntry> queue = new PriorityQueue<>(comparator);

        for (Map.Entry<String,Integer> entry : counts.entrySet()) {
            LogEntry logEntry = new LogEntry(entry.getKey(), entry.getValue());
            queue.offer(logEntry);
        }
        String[] res = new String[k];
        for (int i = 0 ; i < k ; i ++) {
            res[i] = queue.poll().candidate;
        }
        return res;
    }

    // Solution 2: use heap
    public String[] findKWinners_heap(List<LogEntry> entries, int time, int k) {
        HashMap<String, Integer> counts = new HashMap<>();
        for (LogEntry entry : entries) {
            if (entry.time <= time) {
                if (counts.containsKey(entry.candidate)) {
                    int count = counts.get(entry.candidate);
                    counts.put(entry.candidate, count + 1);
                } else {
                    counts.put(entry.candidate, 1);
                }
            }
        }

        LogEntry[] candidates = new LogEntry[counts.size()];
        int i = 0;
        for (Map.Entry<String,Integer> entry : counts.entrySet()) {
            LogEntry logEntry = new LogEntry(entry.getKey(), entry.getValue());
            candidates[i] = logEntry;
            i ++;
        }

        candidates = buildHeap(candidates);
        return heapSort(candidates, k);
    }

    public LogEntry[] buildHeap(LogEntry[] candidates) {
        int heapSize = candidates.length;
        for (int i = heapSize/2; i >= 0 ; i --) {
            maxHeap(i, candidates, heapSize);
        }
        return candidates;
    }

    public String[] heapSort(LogEntry[] candidates, int k) {
        String[] res = new String[k];
        int heapSize = candidates.length;
        int j = 0;
        for (int i = candidates.length - 1; i >= candidates.length  - k ; i --) {
            LogEntry top = candidates[0];
            candidates[0] = candidates[i];
            candidates[i] = top;

            res[j] = top.candidate;
            heapSize --;
            j ++;
            maxHeap(0, candidates, heapSize);
        }
        return res;
    }

    public void maxHeap(int i, LogEntry[] candidates, int heapSize) {
        LogEntry entry = candidates[i];
        int left = left(i);
        int right = right(i);

        int max = candidates[i].time;
        int maxIndex = i;
        if ((i < heapSize) && (candidates[left].time > candidates[i].time)) {
            maxIndex = left;
            max = candidates[left].time;
        }
        if ((i < heapSize) && (candidates[right].time > max)) {
            maxIndex = right;
            max = candidates[right].time;
        }

        if (maxIndex != i) {
            candidates[i] = candidates[maxIndex];
            candidates[maxIndex] = entry;

            maxHeap(maxIndex, candidates, heapSize);
        }
    }

    public int left(int i) {
        return 2*i + 1;
    }

    public int right(int i) {
        return 2* i + 2;
    }

    public class LogEntryComparator implements Comparator<LogEntry> {
        @Override
        public int compare(LogEntry e1, LogEntry e2) {
            if (e1.time < e2.time) {
                return 1;
            }
            else if (e1.time > e2.time) {
                return -1;
            }
            return 0;
        }
    }

}
