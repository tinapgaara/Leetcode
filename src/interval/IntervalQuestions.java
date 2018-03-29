package interval;

import java.util.*;

/**
 * Created by yingtan on 12/13/17.
 */
public class IntervalQuestions {

    // Question 1: given a set of distinct numbers, and an interval, find out which numbers are inside the interval
    public Set<Integer> findOverlapNumbers(Set<Integer> nums, Interval interval) {
        TreeSet<Integer> bst = new TreeSet<>();
        for (Integer num : nums) {
            bst.add(num);
        }
        // log(n)
        // totalnum - setSmaller(interval.start) - setLarger(interval.end)
        return bst.subSet(interval.start, interval.end);
    }

    // Question 2: given a array, for each j, find out how many i (i < j) which satisfy: lower <= sum[j] - sum[i-1] <= upper
    // this is actually follow up of question 1
    // given a set of numbers, may have duplicates, and an interval, find out how many numbers are inside the interval
    // here, we can not use treeset since it does not deal with duplicates.
    // but we can build our own bst tree, with selfcount, smallercount and largercount field.
    // or, choose to use TreeMap<key, dupicateCount>

    public class TreeNode{
        TreeNode left;
        TreeNode right;
        int val;
        int selfcount;
        int leftcount;
        int rightcount;

        public TreeNode(int val) {
            this.val = val;
            selfcount = 1;
        }
    }
    public int countOverlapNumbersWithDuplicate_treeMap(Set<Integer> nums, Interval interval) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (Integer num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            }
            else {
                map.put(num, 1);
            }
        }
        // o(logn)
        Map<Integer, Integer> submap = map.subMap(interval.start, interval.end);
        int count = 0;
        for (Integer value : submap.values()) {
            count = count + value;
        }
        return count;
    }

    public int countOverlapNumbersWithDuplicate(Set<Integer> nums, Interval interval) {
        TreeNode root = null;
        for (Integer num : nums) {
            if (root == null) {
                root = new TreeNode(num);
            }
            else {
                insert(root, num);
            }
        }
        return getOverlapCount(root, interval.start, interval.end);
    }
    // logn
    public TreeNode insert(TreeNode root, int num) {
        // base case
        if (root == null) {
            return new TreeNode(num);
        }
        if (num == root.val) {
            root.selfcount ++;
            return root;
        }
        else if (num < root.val) {
            root.left = insert(root.left, num);
        }
        else {
            root.right = insert(root.right, num);
        }
        return root;
    }
    //logn
    public int getOverlapCount(TreeNode root, int low, int high) {
        if (root == null) return 0;
        return root.selfcount + root.leftcount + root.rightcount - countSmaller(root, low) - countLarger(root, high);
    }
    // logn
    public int countSmaller(TreeNode root, int low) {
        if (root == null) return 0;
        if (low == root.val) {
            return root.leftcount;
        }
        else if (low < root.val) {
            return countSmaller(root.left, low);
        }
        else {
            return root.selfcount + root.leftcount + countSmaller(root.right, low);
        }
    }
    // logn
    public int countLarger(TreeNode root, int high) {
        if (root == null) return 0;
        if (high == root.val) {
            return root.rightcount;
        }
        else if (high < root.val) {
            return root.selfcount + root.rightcount + countLarger(root.left, high);
        }
        else {
            return countLarger(root.right, high);
        }
    }

    // Question 3: given a set of intervals and a number, find out intervals which contains this number
    // treeMap<start, Set<end>>
    public List<Interval> overlapIntervals(int number, List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        TreeMap<Integer, Set<Integer>> startToEnd = new TreeMap<>();
        for (Interval interval : intervals) {
            if (startToEnd.containsKey(interval.start)) {
                startToEnd.get(interval.start).add(interval.end);
            }
            else {
                Set<Integer> ends = new HashSet<>();
                ends.add(interval.end);
                startToEnd.put(interval.start, ends);
            }
        }
        Map<Integer, Set<Integer>> smallers = startToEnd.headMap(number);
        for (Map.Entry<Integer,Set<Integer>> entries : smallers.entrySet()) {
            for (Integer end : entries.getValue()) {
                if (end >= number) {
                    // overlap
                    res.add(new Interval(entries.getKey(), end));
                }
            }
        }
        return res;
    }

    // Question 4: given a set of intervals and an interval candidate, find out if there is an interval in the set which overlaps with the candidate
    public boolean isOverlap(Interval candidate, List<Interval> intervals) {
        TreeMap<Integer, Set<Integer>> startToEnd = new TreeMap<>();
        for (Interval interval : intervals) {
            if (startToEnd.containsKey(interval.start)) {
                startToEnd.get(interval.start).add(interval.end);
            }
            else {
                Set<Integer> ends = new HashSet<>();
                ends.add(interval.end);
                startToEnd.put(interval.start, ends);
            }
        }
        // min key larger than candidate start
        int minKey = startToEnd.ceilingKey(candidate.start);
        if (minKey < candidate.end) {
            return true;
        }
        // keys smaller than candidate start
        Map<Integer, Set<Integer>> smallers = startToEnd.headMap(candidate.start);
        for (Map.Entry<Integer,Set<Integer>> entries : smallers.entrySet()) {
            for (Integer end : entries.getValue()) {
                if (end >= candidate.start) {
                    // overlap
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        IntervalQuestions ob = new IntervalQuestions();
        Interval i1 = new Interval(0,1);
        Interval i2 = new Interval(-1,3);
        List<Interval> list = new ArrayList<>();
        list.add(i2);
        list.add(i1);

    }

}
