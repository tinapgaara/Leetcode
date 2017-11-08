package google.design;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 8/15/17.
 *
 *
 Add to List
 155. Min Stack
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

 push(x) -- Push element x onto stack.
 pop() -- Removes the element on top of the stack.
 top() -- Get the top element.
 getMin() -- Retrieve the minimum element in the stack.
 Example:
 MinStack minStack = new MinStack();
 minStack.push(-2);
 minStack.push(0);
 minStack.push(-3);
 minStack.getMin();   --> Returns -3.
 minStack.pop();
 minStack.top();      --> Returns 0.
 minStack.getMin();   --> Returns -2.
 */
public class MinStack {
    private List<Integer> elements;// push element, then push min since stack's buttom to current element

    /** initialize your data structure here. */
    public MinStack() {

        elements = new ArrayList<Integer>();

    }

    public void push(int x) {
        int min;
        if (elements.size() == 0) {
            min = x;
        }
        else {
            min = getMin();
            if (x < min) min = x;
        }
        elements.add(x);
        elements.add(min);
    }

    public void pop() {
        int size = elements.size();
        elements.remove(size - 1); // remove min
        elements.remove(size - 2); // remove top element

    }

    public int top() {
        int size = elements.size();
        return elements.get(size - 2);
    }

    public int getMin() {
        int size = elements.size();
        return elements.get(size - 1);
    }
}
