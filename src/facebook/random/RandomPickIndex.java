package facebook.random;

import java.util.Random;

/**
 * Created by yingtan on 5/21/17.
 *
 * 398. Random Pick Index
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Given an array of integers with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.

 Note:
 The array size can be very large. Solution that uses too much extra space will not pass the judge.

 Example:

 int[] nums = new int[] {1,2,3,3,3};
 Solution solution = new Solution(nums);

 // pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
 solution.pick(3);

 // pick(1) should return 0. Since in the array only nums[0] is equal to 1.
 solution.pick(1);
 */
public class RandomPickIndex {
    /*
    * The reason to use reservoir sample:
    * You want to pick a element with probability k/p, but, you don't know the number of p until processing all elements.
    * Here, we don't know how many count of nums will equal to target, so p is changing all the time.
    * For this sceneior:
    * 1). p is changing on the real time;
    * 2). can not preprocess the total count of p in memory
    * We need to use reservoir sample.
    *
    * We keep k slot. Each time when process a new element, we record how many total of nums we process, and we get a random number
    * in this range, and check if this random number drops in k slot. If so, we replace that slot with this random number.
    * Then, we pick up k numbers with probability k/p.
    *
    *
    * prove:  choose one from n:  prob 1/ n
    *
    * choose mth element as the res prob = mth is choosen * rest of elements are not choosen
    *   = 1/m * (m-1/m * m-2/m-1 * m-3/m-2 * .... n-1/n) = 1/n
    *
    * */
    int reservoirIndex;
    int[] nums;
    Random rnd;
    public RandomPickIndex(int[] nums) {
        this.nums = nums;
        reservoirIndex = 0;
        rnd = new Random();
    }
    public int pick(int target) {
        int numOfTargets = 0;
        int resIndex = -1;
        for (int i = 0 ; i < nums.length; i ++) {
            int num = nums[i];
            if (num == target) {
                numOfTargets ++;
                // do reservoir sample
                int index = rnd.nextInt(numOfTargets);
                if (index == reservoirIndex) {
                    // drop into reservoir;
                    resIndex = i;
                }
            }
        }
        return resIndex;
    }
}
