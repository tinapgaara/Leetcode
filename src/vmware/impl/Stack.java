package vmware.impl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 10/28/15.
 */
public class Stack {

    private List<Integer> list;
    private int top;

    public Stack() {
        top = -1;
        list = new ArrayList<>();
    }

    public boolean isEmpty() {
        if (top == -1) return true;
        else return false;
    }

    public int peek() {
        if(!isEmpty()) {
            return list.get(top);
        }
        return -1;
    }

    public int pop() {
        int res = -1;
        if(!isEmpty()) {
            res = list.remove(top);
            top --;
            return res;
        }
        return -1;
    }

    public void push(int element) {
        list.add(element);
        top ++;
    }

    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.peek());
    }
}
