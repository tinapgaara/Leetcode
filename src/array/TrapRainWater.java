package array;

/**
 * Created by yingtan on 1/26/18.
 *
 * 42. Trapping Rain Water
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

 For example,
 Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 */
public class TrapRainWater {

    public int trap(int[] height) {
        if (height == null || height.length == 0) return 0;
        int max = 0;
        int maxIndex = -1;
        for (int i = 0 ; i < height.length; i ++) {
            int h = height[i];
            if (h >= max) {
                max = h;
                maxIndex = i;
            }
        }
        int area = 0;
        int leftmax = 0;
        // left to max
        for (int i = 0 ; i != maxIndex; i ++) {
            if (height[i] < leftmax) {
                area = area + (leftmax - height[i]);
            }
            else {
                leftmax = height[i];
            }
        }

        int rightmax = 0;
        // right to max
        for (int i = height.length - 1 ; i != maxIndex; i --) {
            if (height[i] < rightmax) {
                area = area + (rightmax - height[i]);
            }
            else {
                rightmax = height[i];
            }
        }
        return area;
    }
}
