package greedy;

/**
 * Created by yingtan on 10/13/15.
 */
/*
* Leetcode: Container With Most Water
*
* Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai).
* n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
* Find two lines, which together with x-axis forms a container, such that the container contains the most water.
*
* start 和end分别指向数组的头和尾。则当前的container的容积为area=min(height[start],height[end])*(end-start)。
* 假设height[start]<height[end],那么无论end取任何一个值，以start为一条边的容器的area不可能比当前的这个area更大。
* 原因是容器的容积是由较短的那条边决定的。因此这是做start++，进行下一次比较。
* 对于height[end]<height[start]的情况同理end--。
* */
public class ContainWater {

    // two pointers
    public int maxArea(int[] height) {
        if ((height == null) || (height.length == 0))  return 0;
        int low = 0;
        int high = height.length-1;
        int maxArea  = 0;
        while (low < high) {
            if (height[low] < height[high]) {
                maxArea = Math.max(maxArea, (high - low) * height[low]);
                low ++;
            } else {
                maxArea = Math.max(maxArea, (high - low) * height[high]);
                high --;
            }
        }
        return maxArea;
    }
}
