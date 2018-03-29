package google.stack;

import java.util.Stack;

/**
 * Created by yingtan on 11/8/17.
 */
public class LArgestRectangleInHistogram {

    public int largetsRectangleArea(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        for (int i = 0 ; i <= height.length; i ++) {
            int h;
            if (i == height.length) {
                h = 0;
            }
            else {
                h = height[i];
            }
            if (stack.isEmpty() || h >= height[stack.peek()]) {
                stack.push(i);
            }
            else {
                int top = stack.pop();
                int width = i;
                if (! stack.isEmpty()) {
                    width = i - stack.peek() - 1;
                }
                maxArea = Math.max(maxArea, width * height[top]);
                i --;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        LArgestRectangleInHistogram ob = new LArgestRectangleInHistogram();
        ob.largetsRectangleArea(new int[]{4,2,0,3,5});
    }
}
