package DP;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * Created by yingtan on 9/27/15.
 */
/*
* Leetcode: Maximal Rectangle
*
* Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its area.
*
* Refers: Leetcode LargestRectangleHistogram
* */
public class MaxRectangle {

    public int maximalRectangleHist(char[][] matrix) {
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
        for (int i = 0 ; i < heights.length ; i ++) { // Pay Attention Here, should go one by one row.
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



    public int maximalRectangle(char[][] matrix) {
        if (matrix == null) return 0;
        int row = matrix.length;
        if (row == 0) return 0;
        int col = matrix[0].length;

        int maxArea = 0;

        Point[][] points = new Point[row][col];
        HashMap<Point, List<Point>> map = new HashMap<>();
        if (matrix[0][0] == '0')
            points[0][0] = new Point(0,0);
        else {
            points[0][0] = new Point(1,1);
            maxArea = 1;
        }

        for (int i = 1 ; i < col; i ++) {
            if (matrix[0][i] == '1') {
                Point prev = points[0][i-1];
                int len = prev.x;
                int width = prev.y;
                if (width == 0)
                    width = 1;
                points[0][i] = new Point(len+1, width);
                maxArea = Math.max(maxArea, (len+1)* width);
            }
            else
                points[0][i] = new Point(0, 0);

            List<Point> list = new ArrayList<>();
            list.add(points[0][i]);
            Point index = new Point(0,i);
            map.put(index, list);
        }
        for (int i = 1 ; i < row; i ++) {
            if (matrix[i][0] == '1') {
                Point prev = points[i-1][0];
                int len = prev.x;
                int width = prev.y;
                if (len == 0)
                    len = 1;
                points[i][0] = new Point(len, width+1);
                maxArea = Math.max(maxArea, len* (width+1));
            }
            else
                points[i][0] = new Point(0, 0);

            List<Point> list = new ArrayList<>();
            list.add(points[i][0]);
            Point index = new Point(i,0);
            map.put(index, list);
        }

        for (int i = 1; i < row; i ++) {
            for (int j = 1; j < col; j ++) {
                if (matrix[i][j] == '1') {
                    // System.out.println(i + "," + j);
                    maxArea = calculateMaxArea(i, j, maxArea, map, points, matrix);
                }
                else
                    points[i][j] = new Point(0, 0);
            }
        }
        return maxArea;
    }

    public int calculateMaxArea(int i, int j, int maxArea, HashMap<Point, List<Point>> map,
                                Point[][] points, char[][] matrix) {
        int maxArea1 = 0 , maxArea2 = 0;
        Point p1 = null;
        Point p2 = null;
        if (map.containsKey(new Point(i-1,j))) {
            List<Point> upPoints = map.get(new Point(i-1,j));
            if (map.containsKey(new Point(i,j-1))) {
                List<Point> leftPoints = map.get(new Point(i,j-1));
                if (map.containsKey(new Point(i-1,j-1))) {
                    List<Point> diagonalPoints = map.get(new Point(i-1,j-1));
                    for (Point up: upPoints) {
                        for (Point left : leftPoints) {
                            for (Point diagonal: diagonalPoints) {
                                int x1 = left.x + 1;
                                int y1 = Math.min(left.y, up.y) ;
                                y1 = Math.min(y1, diagonal.y) + 1;
                                y1 = calculateWidth(i, j, x1, y1, matrix, points);
                                int area1 = x1 * y1;
                                if (maxArea1 < area1) {
                                    maxArea1 = area1;
                                    p1 = new Point(x1, y1);
                                }

                                int y2 = up.y + 1;
                                int x2 = Math.min(left.x, up.x);
                                x2 = Math.min(x2, diagonal.x) + 1;
                                x2 = calculateLen(i, j, x2, y2, matrix, points);
                                int area2 = x2 * y2;
                                if (maxArea2 < area2) {
                                    maxArea2 = area2;
                                    p2 = new Point(x2, y2);
                                }
                            }
                        }
                    }
                }
            }
        }
        Point index = new Point(i,j);
        List<Point> list = new ArrayList<>();
        if ((maxArea1 == maxArea2) && (p1 != null) && (p2 != null)){
            list.add(p1);
            list.add(p2);
            maxArea = Math.max(maxArea, maxArea2);
        }
        else {
            if ((maxArea1 < maxArea2)&& (p2 != null)) {
                list.add(p2);
                points[i][j] = p2;
                maxArea = Math.max(maxArea, maxArea2);
            }
            else {
                list.add(p1);
                points[i][j] = p1;
                maxArea = Math.max(maxArea, maxArea1);
            }
        }
        map.put(index, list);
        // System.out.println("area1:"+x1 + "," + y1);
        // System.out.println("area2:"+x2 + "," + y2);
        return maxArea;
    }
    public int calculateWidth(int curX, int curY, int len, int width,
                              char[][] matrix, Point[][] points) {
        if (width == 1) return 1;
        int prev = curX - 1;
        int res = Integer.MAX_VALUE;
        int start = curY - len + 1;
        for (int i = start ; i < curY; i ++) {
            res = Math.min(res, points[prev][i].y);
        }
        return Math.min(res + 1, width);
    }

    public int calculateLen(int curX, int curY, int len, int width,
                            char[][] matrix, Point[][] points) {
        if (len == 1) return 1;
        int prev = curY - 1;
        int res = Integer.MAX_VALUE;
        int start = curX - width + 1;
        // System.out.println("curX:"+curX+", width:"+width + "prev:"+prev + "curY:"+curY);
        for (int i = start ; i < curX; i ++) {
            res = Math.min(res, points[i][prev].x);
        }
        return Math.min(res + 1, len);
    }
}
