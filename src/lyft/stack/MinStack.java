package lyft.stack;
import java.util.*;
public class MinStack {
    Stack<MinItem> minstack;
    Stack<Integer> stack;

    public class MinItem {
        public int minval;
        public int count; // don't need to store all mins for all nums. Only need to store variance
        public MinItem(int min) {
            this.minval = min;
            this.count = 1;
        }
    }

    public MinStack() {
        stack = new Stack<>();
        minstack = new Stack<>();
    }
    public void push(int x) {
        stack.push(x);
        if (minstack.isEmpty()) {
            minstack.push(new MinItem(x));
        }
        else {
            MinItem peek = minstack.peek();
            if (x < peek.minval) {
                minstack.push(new MinItem(x));
            }
            else if (x == peek.minval) {
                minstack.peek().count = minstack.peek().count + 1;
            }
        }
    }
    public void pop() {
        if (! stack.isEmpty()) {
            int num = stack.pop();
            if (num == minstack.peek().minval) {
                int count = minstack.peek().count;
                count -- ;
                if (count == 0) {
                    minstack.pop();
                }
                else {
                    minstack.peek().count = count;
                }
            }
        }
    }
    public int top() {
        return stack.peek();
    }
    public int getMin() {
        return minstack.peek().minval;
    }
}
