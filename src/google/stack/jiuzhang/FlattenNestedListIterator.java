package google.stack.jiuzhang;

import java.util.List;
import java.util.Stack;

/**
 * Created by yingtan on 11/4/17.
 */
public class FlattenNestedListIterator {

    public Stack<NestedInteger> stack;

    public FlattenNestedListIterator(List<NestedInteger> nestedList) {
        stack =  new Stack<NestedInteger>();
        pushListToStack(nestedList);

    }
    // [a, b, c]
    // push to stack:
    // a  <- top
    // b
    // c
    private void pushListToStack(List<NestedInteger> nestedList) {
        Stack<NestedInteger> tmp = new Stack<NestedInteger>();
        for (NestedInteger i : nestedList) {
            tmp.push(i);
        }
        while(! tmp.isEmpty()) {
            stack.push(tmp.pop());
        }
    }

    public Integer next() {
        if (!hasNext()) {
            return null;
        }
        return stack.pop().getInteger();
    }

    public boolean hasNext() {
        while(!stack.isEmpty() && !stack.peek().isInteger()) {
            /*
            a  -> [d, e]       e     d
            b                  d     e
            c
            */
            pushListToStack(stack.pop().getList());
        }
        return !stack.isEmpty();
    }

    public interface NestedInteger {
        public boolean isInteger();
        public Integer getInteger();
        public List<NestedInteger> getList();
    }
}
