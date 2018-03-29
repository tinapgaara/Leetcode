package array;

/**
 * Created by yingtan on 3/1/18.
 *
 * 624. Maximum Distance in Arrays
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given m arrays, and each array is sorted in ascending order. Now you can pick up two integers from two different arrays (each array picks one) and calculate the distance. We define the distance between two integers a and b to be their absolute difference |a-b|. Your task is to find the maximum distance.

 Example 1:
 Input:
 [[1,2,3],
 [4,5],
 [1,2,3]]
 Output: 4
 Explanation:
 One way to reach the maximum distance 4 is to pick 1 in the first or third array and pick 5 in the second array.
 Note:
 */
import java.util.*;
public class MaximumDistanceInArrays {
    public int maxDistance(List<List<Integer>> arrays) {
        if (arrays == null || arrays.size() == 0) return 0;
        int max = -10001;
        int secondMax = -10001;
        int maxId = -1;
        int secondMaxId = -1;

        int min = 10001;
        int secondMin = 10001;
        int minId = -1;
        int secondMinId = -1;

        for (int i = 0; i < arrays.size(); i ++) {
            List<Integer> list = arrays.get(i);
            int localmin = list.get(0);
            int localmax = list.get(list.size() - 1);
            if (localmin <= min) {
                secondMin = min;
                secondMinId = minId;
                min = localmin;
                minId = i;
            }
            else if (localmin < secondMin) {
                secondMin = localmin;
                secondMinId = i;
            }

            if (localmax >= max) { // IMPORTANT !!! USE >= here, since [1,1] 1 is max and the second 1 is also secondMax
                secondMax = max;
                secondMaxId = maxId;
                max = localmax;
                maxId = i;
            }
            else if (localmax > secondMax) {
                secondMax = localmax;
                secondMaxId = i;
            }

        }
        if (minId != maxId) {
            return max - min;
        }
        else {
            return Math.max(Math.abs(secondMax - min), Math.abs(max - secondMin));
        }
    }
}
