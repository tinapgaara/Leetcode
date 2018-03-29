package linkedin.practice;

import java.util.Stack;

/**
 * Created by yingtan on 11/22/17.
 *
 * * 716. Max Stack
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

 solution: treeMap + doubleLinkedList ?????
 */
public class MaxStack {

    Stack<Integer> stack;
    Stack<Integer> maxstack;
    /** initialize your data structure here. */
    public MaxStack() {
        stack = new Stack<>();
        maxstack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if (! maxstack.isEmpty()) {
            maxstack.push(Math.max(maxstack.peek(), x));
        }
        else {
            maxstack.push(x);
        }
    }

    public int pop() {
        int res = stack.pop();
        maxstack.pop();
        return res;
    }

    public int top() {
        return stack.peek();
    }

    public int peekMax() {
        return maxstack.peek();
    }

    public int popMax() {
        int max = maxstack.peek();
        Stack<Integer> tmp = new Stack<>();
        // o(n)
        while(! stack.isEmpty()) {
            if (stack.peek() != max) {
                tmp.push(stack.pop());
                maxstack.pop();
            }
            else {
                stack.pop();
                maxstack.pop();
                break;
            }
        }
        while(! tmp.isEmpty()) {
            push(tmp.pop());
        }
        return max;
    }
}
