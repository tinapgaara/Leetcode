package google.iterator;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yingtan on 8/3/17.
 *
 * Given a nested list of integers, implement an iterator to flatten it.

 Each element is either an integer, or a list -- whose elements may also be integers or other lists.

 Example 1:
 Given the list [[1,1],2,[1,1]],

 By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].

 Example 2:
 Given the list [1,[4,[6]]],

 By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].


 */
public class FlattenNestedListIterator {

    public Queue<Integer> queue;
/*
    public NestedIterator(List<NestedInteger> nestedList) {
        queue = new LinkedList<Integer>();
        for (NestedInteger i : nestedList) {
            recurAdd(i);
        }
    }

    public void recurAdd(NestedInteger i) {
        if (i.isInteger()) {
            queue.offer(i.getInteger());
            return;
        }
        else {
            List<NestedInteger> list = i.getList();
            for (NestedInteger integer : list) {
                recurAdd(integer);
            }
        }
    }

    public Integer next() {
        if (hasNext()) {
            Integer res = queue.poll();
            return res.intValue();
        }
        return -1;
    }

    public boolean hasNext() {
        if (! queue.isEmpty()) return true;
        else return false;
    }
    */
}
