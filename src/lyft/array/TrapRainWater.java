package lyft.array;

public class TrapRainWater {

    public int trap(int[] height) {
        if (height == null || height.length == 0) return 0;
        int maxHeight = 0;
        int maxIndex = -1;
        for (int i = 0 ; i < height.length; i ++) {
            if (height[i] >= maxHeight) {
                maxHeight = height[i];
                maxIndex = i;
            }
        }
        int leftMaxHeight = 0;
        int leftSum = 0;
        for (int i = 0 ; i != maxIndex; i ++) {
            if (height[i] >= leftMaxHeight) {
                leftMaxHeight = height[i];
            }
            else {
                leftSum = leftSum + leftMaxHeight - height[i];
            }
        }
        int rightMaxHeight = 0;
        int rightSum = 0;
        for(int i = height.length - 1; i != maxIndex; i --) {
            if (height[i] >= rightMaxHeight) {
                rightMaxHeight = height[i];
            }
            else {
                rightSum = rightSum + rightMaxHeight - height[i];
            }
        }
        return leftSum + rightSum;
    }
}
