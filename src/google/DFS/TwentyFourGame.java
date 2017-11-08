package google.DFS;

/**
 * Created by yingtan on 11/4/17.
 *
 * 679. 24 Game
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 You have 4 cards each containing a number from 1 to 9. You need to judge whether they could operated through *, /, +, -, (, ) to get the value of 24.

 Example 1:
 Input: [4, 1, 8, 7]
 Output: True
 Explanation: (8-4) * (7-1) = 24
 Example 2:
 Input: [1, 2, 1, 2]
 Output: False
 */
public class TwentyFourGame {
    public boolean judgePoint24(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        return is24Point(new double[]{nums[0], nums[1], nums[2], nums[3]});
    }

    public boolean is24Point(double[] points) {
        if (points.length == 1) {
            // important !!!!
            if (points[0] == 24 || Math.abs(points[0] - 24.0) < 0.0001)
                return true;
        }
        // cast [_ _ _ _] to [_ _ _]
        for (int i = 0 ; i < points.length; i ++) {
            for (int j = i + 1 ; j < points.length; j ++) {
                double[] newPoints = new double[points.length - 1];
                int start = 0;
                for (int k = 0 ; k < points.length; k ++) {
                    if (k != i && k != j) {
                        newPoints[start] = points[k];
                        // important !!!!!
                        start ++;
                    }
                }
                for (double res : computeRes(points[i], points[j])) {
                    newPoints[newPoints.length - 1] = res;
                    if (is24Point(newPoints)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private double[] computeRes(double num1, double num2) {
        return new double[]{num1 + num2, num1 - num2, num2 - num1,
                num1 * num2, num1 / num2, num2 / num1};

    }

    public static void main(String[] args) {
        TwentyFourGame ob = new TwentyFourGame();
        int[] nums = new int[]{1,9,1,2};
        System.out.println(ob.judgePoint24(nums));
    }
}
