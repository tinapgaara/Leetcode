package lyft.stack;

import java.util.*;
// follow up: what if I want to support different types?
public class MaxTStack<T extends Comparable<T>> {
    public class MaxItem<T extends Comparable<T>> {
        T maxVal;
        int count;
        public MaxItem(T max) {
            this.maxVal = max;
            this.count = 1;
        }
        public T getMax()  { return this.maxVal; }
    }

    Stack<T> stack;
    Stack<MaxItem> maxStack; // store increasing maxs
    public MaxTStack() {
        stack = new Stack<T>();
        maxStack = new Stack<MaxItem>();
    }
    public void push(T x) {
        stack.push(x);
        if (maxStack.isEmpty()) {
            maxStack.push(new MaxItem(x));
        }
        else {
            T max = (T)maxStack.peek().getMax();
            if (x.compareTo(max) > 0) {
                // x > max
                maxStack.push(new MaxItem(x));
            }
            else if (x == max) {
                maxStack.peek().count = maxStack.peek().count + 1;
            }
        }
    }
    public void pop() {
        if (! stack.isEmpty()) {
            T num = stack.pop();
            if (num.compareTo((T)maxStack.peek().getMax()) == 0) {
                int count = maxStack.peek().count;
                count --;
                if (count == 0) {
                    maxStack.pop();
                }
                else{
                    maxStack.pop().count = count;
                }
            }
        }
    }
    public T top() {
        return stack.peek();
    }
    public T getMax() {
        T max = (T)maxStack.peek().getMax();
        return max;
    }
    public static void main(String[] args) {
        MaxTStack<Integer> stack = new MaxTStack<>();
        stack.push(1);
        System.out.println(stack.getMax());
        stack.push(2);
        System.out.println(stack.getMax());
        stack.pop();
        System.out.println(stack.getMax());

    }
}
