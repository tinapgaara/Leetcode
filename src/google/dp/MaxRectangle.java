package google.dp;

import java.util.Stack;

/**
 * Created by yingtan on 11/6/15.
 */
public class MaxRectangle {

    public int maximalRectangle(char[][] matrix) {
        if (matrix == null) return 0;
        if (matrix.length == 0) return 0;
        int row = matrix.length;
        int col = matrix[0].length;

        int[][] heights = new int[row][col];
        for (int i = 0 ; i < col; i ++) {
            if (matrix[0][i] == '1') {
                heights[0][i] = 1;
            }
        }
        for (int i = 1 ; i < row; i ++) {
            for (int j = 0 ; j < col; j ++) {
                if (matrix[i][j] == '1') {
                    heights[i][j] = heights[i-1][j] + 1;
                }
                else {
                    heights[i][j] = 0;
                }
            }
        }
        int maxArea = 0;
        for (int i = 0 ; i < heights.length ; i ++) {
            maxArea = Math.max(maxArea, largestRectangleHistogram(heights[i]));
        }

        return maxArea;
    }

    public int largestRectangleHistogram(int[] heights) {

        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0);
        int maxArea = 0;

        for (int nextIndex = 1; nextIndex < heights.length; nextIndex ++) {

            if (!stack.isEmpty()) {
                int curElementIndex = stack.peek();
                int curElement = heights[curElementIndex];

                if (curElement < heights[nextIndex]) {
                    stack.push(nextIndex);
                }
                else {
                    while (!stack.isEmpty() && (heights[stack.peek()] > heights[nextIndex])) {
                        curElementIndex = stack.pop();
                        curElement = heights[curElementIndex];
                        if (!stack.isEmpty()) {
                            int previouSmallerElementIndex = stack.peek();
                            int width = nextIndex - previouSmallerElementIndex - 1;
                            int areaFromPreviousSmallerIndex2Next = width * curElement;
                            maxArea = Math.max(maxArea, areaFromPreviousSmallerIndex2Next);
                        }
                        else {
                            int width = nextIndex;
                            int areaFromStart2Next = width * curElement;
                            System.out.println(areaFromStart2Next);
                            maxArea = Math.max(maxArea, areaFromStart2Next);
                        }
                    }
                    stack.push(nextIndex);
                }
            }
        }
        int endIndex = heights.length;
        while (!stack.isEmpty()) {
            int curIndex = stack.pop();
            int curElement = heights[curIndex];
            if (!stack.isEmpty()) {
                int previouSmallerElementIndex = stack.peek();
                int width = endIndex - previouSmallerElementIndex - 1;
                int areaFromPreviousSmallerIndex2Next = width * curElement;
                maxArea = Math.max(maxArea, areaFromPreviousSmallerIndex2Next);
            }
            else {
                int width = endIndex;
                int areaFromStart2Next = width * curElement;
                maxArea = Math.max(maxArea, areaFromStart2Next);
            }
        }
        return maxArea;
    }
}
