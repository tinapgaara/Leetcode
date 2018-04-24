package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by yingtan on 9/20/15.
 */
public class MinStack {

    public class MinItem {
        int minVal;
        int count;
        public MinItem(int minVal, int count) {
            this.minVal = minVal;
            this.count = count;
        }
    }
    Stack<MinItem> minStack;
    Stack<Integer> stack;
    /** initialize your data structure here. */
    public MinStack() {
        minStack = new Stack<>();
        stack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty()) {
            minStack.push(new MinItem(x, 1));
        }
        else {
            int oldmin = minStack.peek().minVal;
            if (x < oldmin) {
                minStack.push(new MinItem(x, 1));
            }
            else if (x == oldmin) {
                minStack.peek().count = minStack.peek().count + 1;
            }
        }
    }

    public void pop() {
        int popNum = -1;
        if (! stack.isEmpty()) {
            popNum = stack.pop();
            if (popNum == minStack.peek().minVal) {
                minStack.peek().count = minStack.peek().count - 1;
                if (minStack.peek().count == 0) {
                    minStack.pop();
                }
            }
        }
        return;
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        if (! minStack.isEmpty()) {
            return minStack.peek().minVal;
        }
        return -1;
    }

    public static void main(String[] args) {
        MinStack stack = new MinStack();

        char i = '2';
        int  y = (int)(i - '0');
        System.out.print(y);

        /*
        stack.push(-2);
        stack.push(0);
        stack.push(-1);
        */
    }
}
