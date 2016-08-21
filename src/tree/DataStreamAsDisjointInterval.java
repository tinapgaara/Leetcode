package tree;

import interval.Interval;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 8/21/16.
 *
 * 352. Data Stream as Disjoint Intervals  QuestionEditorial Solution  My Submissions
 Total Accepted: 4956
 Total Submissions: 13071
 Difficulty: Hard
 Given a data stream input of non-negative integers a1, a2, ..., an, ..., summarize the numbers seen so far as a list of disjoint intervals.

 For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:

 [1, 1]
 [1, 1], [3, 3]
 [1, 1], [3, 3], [7, 7]
 [1, 3], [7, 7]
 [1, 3], [6, 7]
 Follow up:
 What if there are lots of merges and the number of disjoint intervals are small compared to the data stream's size?
 */
public class DataStreamAsDisjointInterval {

    /**
     * Definition for an interval.
     * public class Interval {
     *     int start;
     *     int end;
     *     Interval() { start = 0; end = 0; }
     *     Interval(int s, int e) { start = s; end = e; }
     * }
     */
    public class SummaryRanges {

        private Node root;

        /** Initialize your data structure here. */
        public SummaryRanges() {
            root = null;
        }

        public void addNum(int val) {
            if (root == null) {
                root = new Node(val);
            }
            else {
                insertNode(root, val);
            }
        }

        public List<Interval> getIntervals() {
            List<Interval> res = new ArrayList<Interval>();

            recurBST(res, root);
            return res;
        }

        public void recurBST(List<Interval> res, Node root) {
            if (root == null) return;

            recurBST(res, root.left);
            if (res.size() > 0) {
                Interval latest = res.get(res.size()-1);
                if (latest.end == root.val - 1) {
                    latest = res.remove(res.size()-1);
                    latest.end = root.val;
                    res.add(latest);
                }
                else {
                    Interval newInterval = new Interval(root.val, root.val);
                    res.add(newInterval);
                }
            }
            else {
                Interval newInterval = new Interval(root.val, root.val);
                res.add(newInterval);
            }

            recurBST(res, root.right);
        }

        public void insertNode(Node root, int val) {
            int rootVal = root.val;
            if (val < rootVal) {
                if (root.left != null)
                    insertNode(root.left, val);
                else
                    root.left = new Node(val);
            }
            else if (val > rootVal) {
                if (root.right != null)
                    insertNode(root.right, val);
                else
                    root.right =  new Node(val);
            }
        }

        public class Node {
            public int val;
            public Node left;
            public Node right;

            public Node(int val) {
                this.val = val;

                left = null;
                right = null;
            }
        }
    }
}
