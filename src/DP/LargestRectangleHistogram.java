package DP;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by yingtan on 10/12/15.
 */
/*
* Leetcode: Largest Rectangle in Histogram
*
* Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
* find the area of largest rectangle in the histogram.
*
*
*
* */
public class LargestRectangleHistogram {

    public int largestRectangleArea(int[] height) {
        if ((height == null) || (height.length == 0) ) return 0;
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0);
        int maxArea = 0;
        int curElement = height[0];
        int curElementIndex = 0;

        for (int nextIndex = 1; nextIndex < height.length; nextIndex ++) {
            if ( ! stack.isEmpty()) { // not empty, then can get cur element from stack.
                curElementIndex = stack.peek();
                // larger than current element, can push
                if (height[nextIndex] >= height[curElementIndex]) {
                    stack.push(nextIndex);
                }
                else {
                    // nextElement is smaller than current element, need to pop current element iteratively
                    // PAY Attention !!!
                    // MUST BE: heights[stack.peek()] > heights[nextIndex]!!!!!!!!!!!
                    while (!stack.isEmpty() && (height[stack.peek()] > height[nextIndex])) {
                        curElement = height[stack.pop()];
                        // If there is previous element before(smaller) than cur element:
                        if (!stack.isEmpty()) {
                            int previousSmallerIndex = stack.peek();
                            int width = nextIndex - previousSmallerIndex - 1;
                            int areaFromPreviousSmall2NextIndex = width * curElement;
                            maxArea = Math.max(maxArea, areaFromPreviousSmall2NextIndex);
                        } else {
                            // This element is smallest one, must cover whole array from 0 - nextIndex.
                            // Since all elements are poped besides this one, so these elements are larger than this one.
                            int width = nextIndex;
                            int areaFromStart2NextIndex = width * curElement;
                            maxArea = Math.max(maxArea, areaFromStart2NextIndex);
                        }
                    }
                    stack.push(nextIndex);
                }
            }
        }
        int end = height.length;
        // The rest increasing elements.
        while (!stack.isEmpty() ) {
            int curIndex = stack.pop();
            curElement = height[curIndex];
            if (stack.isEmpty())  {
                int areaFromStart2NextIndex = curElement * (end);
                maxArea = Math.max(maxArea,areaFromStart2NextIndex);
                break;
            }else {
                int previousSmallerIndex = stack.peek();
                int width = end - previousSmallerIndex - 1;
                int areaFromPreviousSmall2NextIndex = width * curElement;
                maxArea = Math.max(maxArea, areaFromPreviousSmall2NextIndex);
            }
        }

        return maxArea;
    }

    public int largestRectangleArea2(int[] height) {
        Stack<Integer> stack = new Stack<Integer>();
        int i = 0;
        int maxArea = 0;
        int[] h = new int[height.length + 1];
        h = Arrays.copyOf(height, height.length + 1);
        while(i < h.length){
            if(stack.isEmpty() || h[stack.peek()] <= h[i]){
                stack.push(i++);
            }else {
                int t = stack.pop();
                if (!stack.isEmpty())
                    System.out.println(i - stack.peek() - 1);
                else {
                    System.out.println("Empty !!!");
                }
                maxArea = Math.max(maxArea, h[t] * (stack.isEmpty() ? i : i - stack.peek() - 1));
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        LargestRectangleHistogram ob = new LargestRectangleHistogram();
        int[] height = new int[]{2,1,2};
        ob.largestRectangleArea(height);
    }
}
