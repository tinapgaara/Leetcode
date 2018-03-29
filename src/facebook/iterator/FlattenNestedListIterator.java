package facebook.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import google.stack.jiuzhang.FlattenNestedListIterator.*;

/**
 * Created by yingtan on 12/20/17.
 *
 * 341. Flatten Nested List Iterator
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Given a nested list of integers, implement an iterator to flatten it.

 Each element is either an integer, or a list -- whose elements may also be integers or other lists.

 Example 1:
 Given the list [[1,1],2,[1,1]],

 By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].

 Example 2:
 Given the list [1,[4,[6]]],

 By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].


 */
public class FlattenNestedListIterator {

    Stack<NestedInteger> stack = new Stack<>();
    public FlattenNestedListIterator(List<google.stack.jiuzhang.FlattenNestedListIterator.NestedInteger> nestedList) {
        for (int i = nestedList.size() - 1; i >= 0; i --) {
            stack.push(nestedList.get(i));
        }
    }
    // here , we make sure the poped nested integer must be an integer istead of a list
    public Integer next() {
        return stack.pop().getInteger();
    }

    public boolean hasNext() {
        // important !!! using while here
        while (! stack.isEmpty()) {
            NestedInteger top = stack.peek();
            if (top.isInteger()) {
                return true;
            }
            else {
                // this is nested integer, expand it
                // keep expanding until hit an integer
                NestedInteger nestedInteger = stack.pop();
                List<NestedInteger> list = nestedInteger.getList();
                for (int i = list.size() -1 ; i >= 0 ; i --) {
                    stack.push(list.get(i));
                }
            }
        }
        return false;
    }

}
