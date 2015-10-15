package stack;

import java.util.Stack;

/**
 * Created by yingtan on 10/13/15.
 */
public class TrapRainWater {

    // Solution 1: scan to right and scan to left
    // find max height of right at each position , and max height of right at each postion
    // min(maxRight, maxLeft) - A[i] = current location's trapping water
    public int trap(int[] height) {
        if ( (height == null) || (height.length == 0) ) return 0;
        int max = 0;
        int[] maxHeightScan2Right = new int[height.length];
        for (int i = 0 ; i < height.length; i ++) {
            if (height[i] > max) max = height[i];
            maxHeightScan2Right[i] = max;
        }
        max = 0;
        int[] maxHeightScan2Left = new int[height.length];
        for (int i = height.length-1 ; i >= 0; i --) {
            if (height[i] > max) max = height[i];
            maxHeightScan2Left[i] = max;
        }
        int area  = 0;
        for (int i = 0 ; i < height.length; i ++) {
            area = area +
                    (Math.min(maxHeightScan2Right[i], maxHeightScan2Left[i]) - height[i]);
        }
        return area;

    }

    // Solution 2: using stack
    public int trap_2(int[] height) {
        if ( (height == null) || (height.length == 0) || (height.length == 1) ) return 0;
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(height[0]);
        int area  = 0;
        int i = 1;
        int oneSideIndex = 0; // record the left highest height, all stack's elements are smaller than this height.
        while (i < height.length) {
            if(!stack.isEmpty()) {
                int cur = height[i];
                // if current height is smaller than left highest height, push it to stack.
                if (cur < height[oneSideIndex]) {
                    stack.push(cur);
                    i ++;
                }
                // if current height is larger than previous left highest height, pop the elements in stack one by one.
                else {
                    // copy previous left highest height to curPeekIndex.
                    int curPeekIndex = oneSideIndex;
                    // reset previous left highest height index to be the current left highest index i.
                    oneSideIndex = i;
                    // move i to the current left highest height index
                    i = curPeekIndex + stack.size();

                    // iteratively pop the elements in stack(which is smaller than ith value)
                    while (!stack.isEmpty()) {
                        int prevTop = stack.pop();
                        // IMportant !!!! : left highest height - previous stack's lower height
                        area = area + height[curPeekIndex] - prevTop;
                    }
                    // push current highest height to stack
                    stack.push(cur);
                    // move i to the next elements by the side of current left highest height index.
                    i++;
                }
            }
        }
        // For rest part, all elements are smaller than the buttom element in stack
        int curMaxHeight = 0;
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            if (!stack.isEmpty()) {
                int prev = stack.peek();
                // keep tracking of largest height now
                curMaxHeight = Math.max(curMaxHeight,cur);
                if (curMaxHeight > prev) {
                    // Important !!!!
                    // use largest height - previous all heights.
                    area = area + curMaxHeight - prev;
                }
            }
        }
        return area;
    }

    public static void main(String[] args) {
        TrapRainWater ob = new TrapRainWater();
        int[] height = new int[]{6,8,5,0,0,6,5};
        ob.trap_2(height);
    }

}
