package linkedin.stack;

import java.util.Stack;

/**
 * Created by yingtan on 11/19/17.
 *
 * 716. Max Stack
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Design a max stack that supports push, pop, top, peekMax and popMax.

 push(x) -- Push element x onto stack.
 pop() -- Remove the element on top of the stack and return it.
 top() -- Get the element on the top.
 peekMax() -- Retrieve the maximum element in the stack.
 popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements, only remove the top-most one.
 Example 1:
 MaxStack stack = new MaxStack();
 stack.push(5);
 stack.push(1);
 stack.push(5);
 stack.top(); -> 5
 stack.popMax(); -> 5
 stack.top(); -> 1
 stack.peekMax(); -> 5
 stack.pop(); -> 1
 stack.top(); -> 5

 */
public class MaxStack {

    Stack<Integer> stack;
    Stack<Integer> maxStack;
    /** initialize your data structure here. */
    public MaxStack() {
        stack = new Stack<Integer>();
        maxStack = new Stack<Integer>();
    }

    public void push(int x) {
        stack.push(x);
        if (maxStack.isEmpty()) {
            maxStack.push(x);
        }
        else {
            maxStack.push(Math.max(maxStack.peek(), x));
        }
    }

    public int pop() {
        if (!stack.isEmpty()) {
            Integer res = stack.pop();
            maxStack.pop();
            return res;
        }
        return -1;
    }

    public int top() {
        if (!stack.isEmpty()) {
            return stack.peek();
        }
        else {
            return -1;
        }
    }

    public int peekMax() {
        if (!maxStack.isEmpty()) {
            return maxStack.peek();
        }
        return -1;
    }

    public int popMax() {
        // 1. find out max value
        // 2. in stack, pop up all elements which not equal to max value, at the same time, pop up the maxStack
        // 3. push back elements which were pop up from stack before, re-calculate max value, and
        // push back to maxStack
        if (!maxStack.isEmpty()) {
            int max = maxStack.peek();
            Stack<Integer> temp = new Stack<Integer>();
            // find out the max value in stack
            while(! stack.isEmpty()) {
                if(stack.peek() != max) {
                    temp.push(stack.pop());
                    maxStack.pop();
                }
                else {
                    stack.pop();
                    maxStack.pop();
                    break;
                }
            }
            while(! temp.isEmpty()) {
                push(temp.pop());
            }
            return max;
        }
        return -1;
    }
}
