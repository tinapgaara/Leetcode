package linkedin.recursion;
import google.stack.jiuzhang.FlattenNestedListIterator;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by yingtan on 11/18/17.
 *
 * 364. Nested List Weight Sum II
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

 Each element is either an integer, or a list -- whose elements may also be integers or other lists.

 Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.

 Example 1:
 Given the list [[1,1],2,[1,1]], return 8. (four 1's at depth 1, one 2 at depth 2)

 Example 2:
 Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17)


 */
public class NestedListWeightSumII {

    public int depthSumInverse(List<FlattenNestedListIterator.NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() == 0) return 0;
        Queue<FlattenNestedListIterator.NestedInteger> queue = new LinkedList<FlattenNestedListIterator.NestedInteger>();
        for (FlattenNestedListIterator.NestedInteger num : nestedList) {
            queue.offer(num);
        }
        int curLevelSum = 0;
        int total = 0;
        while(! queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0 ; i < size; i ++) {
                FlattenNestedListIterator.NestedInteger num = queue.poll();
                if (num.isInteger()) {
                    curLevelSum = curLevelSum + num.getInteger();
                }
                else {
                    for (FlattenNestedListIterator.NestedInteger nextNum : num.getList()) {
                        queue.offer(nextNum);
                    }
                }
            }
            // this level ends, important !!! previous level sum will be aggregated to the next level
            // []  2  []  -> 2
            // 1 1    1 1  -> 2 + (1+1) + (1+1) -> 6
            // total : 2+ 6 = 8
            // store this sum to total sum
            total = total + curLevelSum;
        }
        return total;
    }
}
