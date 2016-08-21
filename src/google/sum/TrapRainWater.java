package google.sum;

/**
 * Created by yingtan on 12/22/15.
 */
public class TrapRainWater {

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
}
