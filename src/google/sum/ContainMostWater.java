package google.sum;

/**
 * Created by yingtan on 12/22/15.
 */
public class ContainMostWater {

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
