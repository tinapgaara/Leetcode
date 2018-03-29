package greedy;

/**
 * Created by yingtan on 1/18/18.
 */
import java.util.*;
public class LargestRectangleArea {

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
