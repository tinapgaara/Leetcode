package google.topo;

import java.util.*;

/**
 * Created by yingtan on 8/27/17.
 *
 * 444. Sequence Reconstruction
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs. The org sequence is a permutation of the integers from 1 to n, with 1 ≤ n ≤ 104. Reconstruction means building a shortest common supersequence of the sequences in seqs (i.e., a shortest sequence so that all sequences in seqs are subsequences of it). Determine whether there is only one sequence that can be reconstructed from seqs and it is the org sequence.

 Example 1:

 Input:
 org: [1,2,3], seqs: [[1,2],[1,3]]

 Output:
 false

 Explanation:
 [1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can be reconstructed.
 Example 2:

 Input:
 org: [1,2,3], seqs: [[1,2]]

 Output:
 false

 Explanation:
 The reconstructed sequence can only be [1,2].
 Example 3:

 Input:
 org: [1,2,3], seqs: [[1,2],[1,3],[2,3]]

 Output:
 true

 Explanation:
 The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].
 Example 4:

 Input:
 org: [4,1,5,2,6,3], seqs: [[5,2,6,3],[4,1,5,2]]

 Output:
 true

 Topological Sort: This problem is to determine if there‘s one, and only one sequence to sort a DAG. The method is to check if the queue‘s size is always 1 or not. If the queue has over 1 size when we‘re conducting topological sort, we return false, which implies that there exists more than 1 sequence to sort this DAG

 Some corner case that i missed when write it:

 Input:[1] [[1],[2,3],[3,2]]
 Output:true
 Expected:false
 How to revise: check if index at last equals graph‘s size
 */
public class SequenceReconstruction {

    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();
        // nodeId -> indegree
        HashMap<Integer, Integer> indegree = new HashMap<>();

        //build the graph
        for (List<Integer> seq : seqs) {
            if (seq.size() == 1) {
                if (!graph.containsKey(seq.get(0))) {
                    graph.put(seq.get(0), new HashSet<Integer>());
                    indegree.put(seq.get(0), 0);
                }
            } else {
                for (int i = 0; i < seq.size() - 1; i++) {
                    if (!graph.containsKey(seq.get(i))) {
                        graph.put(seq.get(i), new HashSet<Integer>());
                        indegree.put(seq.get(i), 0);
                    }
                    if (!graph.containsKey(seq.get(i + 1))) {
                        graph.put(seq.get(i + 1), new HashSet<Integer>());
                        indegree.put(seq.get(i + 1), 0);
                    }
                    if (!graph.get(seq.get(i)).contains(seq.get(i + 1))) {
                        graph.get(seq.get(i)).add(seq.get(i + 1));
                        indegree.put(seq.get(i + 1), indegree.get(seq.get(i + 1)) + 1);
                    }
                }

            }
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        for (Map.Entry<Integer, Integer> entry : indegree.entrySet()) {
            // starting from nodes whose indegree == 0
        /*
        Such as:
            1  ->  2 -> 3
        */
            if (entry.getValue() == 0) { // indegree == 0
                queue.offer(entry.getKey());
            }
        }

        int index = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            int curSize = queue.size();
        /*
        Such as:
              ->  2
            1
              ->  3
        there are two nodes 2, 3 , which means could form at least two distinct sequence
        */
            if (curSize > 0) return false;
            // 跟topo形成的sequence顺序不一样
            if (index >= org.length || org[index] != node) return false;
            index ++;
            // loop neighbors, delete current node
            HashSet<Integer> neighbors = graph.get(node);
            if (neighbors != null) {
                for (Integer neighbor : neighbors) {
                    indegree.put(neighbor, indegree.get(neighbor) - 1);
                    if (indegree.get(neighbor) == 0) {
                        // this is the next indegree == 0's node, will visit next
                        queue.offer(neighbor);
                    }
                }
            }
        }
        //corner case:org: [1,2,3], seqs: [[1,2]]
        if ((index == org.length) && (index == indegree.size())) return true;
        else return false;
    }

}
