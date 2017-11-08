package google.binarysearch;

import java.util.Arrays;

/**
 * Created by yingtan on 9/15/17.
 *
 * 475. Heaters
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Winter is coming! Your first job during the contest is to design a standard heater with fixed warm radius to warm all the houses.

 Now, you are given positions of houses and heaters on a horizontal line, find out minimum radius of heaters so that all houses could be covered by those heaters.

 So, your input will be the positions of houses and heaters seperately, and your expected output will be the minimum radius standard of heaters.

 Note:
 Numbers of houses and heaters you are given are non-negative and will not exceed 25000.
 Positions of houses and heaters you are given are non-negative and will not exceed 10^9.
 As long as a house is in the heaters' warm radius range, it can be warmed.
 All the heaters follow your radius standard and the warm radius will the same.
 Example 1:
 Input: [1,2,3],[2]
 Output: 1
 Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.
 Example 2:
 Input: [1,2,3,4],[1,4]
 Output: 1
 Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard, then all t
 */
public class Heaters {

    // Sol2: binary search
    public int findRadius_best(int[] houses, int[] heaters) {
        if(houses == null || houses.length == 0 || heaters == null || heaters.length == 0) return 0;
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int clostHeaterDist = 0;

        // Sol2 :
        /*
        找离house最近的heater ，即找第一个大于当前house i的heater j，然后算：abs(house[i]-heater[j-1])和abs(house[i]-heater[j])看是jth heater离house近还是j-1th heater离house近
        我们可以用二分查找法来快速找到第一个大于等于当前house位置的数，如果这个数存在，那么我们可以算出其和house的差值，并且如果这个数不是heater的首数字，我们可以算出house和前面一个数的差值，这两个数中取较小的为cover当前house的最小半径，然后我们每次更新结果res即可
        */
        for (int i = 0 ; i < houses.length; i ++) {
            int house = houses[i];
            // find first heater which is larger than this house
            int left = 0;
            // Important !!!! using heaters.length instead of heaters.length - 1
            int right = heaters.length;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (heaters[mid] < houses[i]) {
                    // must can not be mid since mid heater is still lefter of house i
                    // search right
                    left  = mid + 1;
                }
                else {
                    // search left
                    // since could be this mid, we use right = mid instead of right = mid - 1, and include heaters[mid]==houses[i]
                    right = mid;
                }
            }
            // right is the search result
            int rightDist = Integer.MAX_VALUE;
            int leftDist = Integer.MAX_VALUE;
            if (right < heaters.length) {
                rightDist = heaters[right] - houses[i];
            }
            if (right > 0) {
                leftDist = houses[i] - heaters[right - 1];
            }
            clostHeaterDist = Math.max(clostHeaterDist, Math.min(rightDist,leftDist));
        }
        return clostHeaterDist;
    }

    /*
    * Sol 1:我们在heater中两个数一组进行检查，如果后面一个数和当前house位置差的绝对值小于等于前面一个数和当前house位置差的绝对值，那么我们继续遍历下一个位置的数。跳出循环的条件是遍历到heater中最后一个数，或者上面的小于等于不成立，此时heater中的值和当前house位置的差的绝对值就是能cover当前house的最小半径
    *其实就是尝试在每个house的左边和右边找到最近的取暖器，然后在左边和右边的取暖器中找出个最小半径。最后再在所有house的最小半径中，找出一个最大值，就可以保证所有house都能取暖了
     *  */

    public int findRadius(int[] houses, int[] heaters) {
        if(houses == null || houses.length == 0 || heaters == null || heaters.length == 0) return 0;
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int clostHeaterDist = 0;
        // important !!!  declare j = 0  before for loop
        int j = 0;
        for (int i = 0 ; i < houses.length; i ++) {
            //int j = 0; // wrong
            while (j < heaters.length - 1 && Math.abs(heaters[j+1] - houses[i]) <= Math.abs(heaters[j] - houses[i])) {
                // heat1   house heat2 , then, could have more heaters on the right which is closer than heat1, so
                j ++;
            }
            // heat1 house    heat2   heat3, then heat3's dist to house will be larger than heat2, no need to check
            // then, abs(heaters[j] - houses[i]) is the min dist from houses[i] to one heater
            // we need to find max dist across diff houses
            clostHeaterDist = Math.max(clostHeaterDist, Math.abs(heaters[j] - houses[i]));
        }
        return clostHeaterDist;
    }
}
