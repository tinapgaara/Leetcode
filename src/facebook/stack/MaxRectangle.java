package facebook.stack;

import java.util.Stack;

/**
 * Created by yingtan on 1/6/18.
 */
public class MaxRectangle {

    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int[] height = new int[matrix[0].length];
        int max = 0;
        for (int i = 0 ; i < matrix.length; i ++) {
            for (int j = 0 ; j < matrix[0].length; j ++) {
                if (matrix[i][j] == '1') {
                    height[j] = height[j] + 1;
                }
                else {
                    height[j] = 0;
                }
            }
            int maxArea = largestRectangleArea(height);
            max = Math.max(max, maxArea);
        }
        return max;
    }
    public int largestRectangleArea(int[] heights) {
        if (heights == null | heights.length == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        stack.push(0);
        for (int i = 1 ; i < heights.length; i ++) {
            // how leftmost/rightmost can extend without lowering height
            int top = heights[stack.peek()];
            if (heights[i] >= top) { // increasing mono in stack
                stack.push(i);
            }
            else {
                // this is the end(block) of previous taller heights
                while(! stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                    // pop this, and calcualte area which is this height
                    int index = stack.pop();
                    int tallerHeight = heights[index];
                    // how far it can extend to right
                    int rightExtendIndex = i;
                    // how far it can extend to left, depend on its previous element(previous lower element in stack)
                    int leftExtendIndex;
                    if (stack.isEmpty()) {
                        leftExtendIndex = 0;
                    }
                    else {
                        leftExtendIndex = stack.peek() + 1;
                    }
                    int areaOfTallerElement = (rightExtendIndex - leftExtendIndex) * tallerHeight;
                    max = Math.max(max, areaOfTallerElement);
                }
                // pop up all heights which is larger than the current one, push the current one
                stack.push(i);
            }
        }
        if(! stack.isEmpty()) {
            int rightExtendIndex = heights.length;
            while(! stack.isEmpty()) {
                int index = stack.pop();
                int leftExtendIndex;
                if (stack.isEmpty()) {
                    leftExtendIndex = 0;
                }
                else {
                    leftExtendIndex = stack.peek() + 1;
                }
                max = Math.max(max, (rightExtendIndex - leftExtendIndex) * heights[index]);
            }
        }
        return max;
    }
}
