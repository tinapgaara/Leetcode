package linkedin.array;

/**
 * Created by yingtan on 11/19/17.
 *
 * 605. Can Place Flowers
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Suppose you have a long flowerbed in which some of the plots are planted and some are not. However, flowers cannot be planted in adjacent plots - they would compete for water and both would die.

 Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty), and a number n, return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.

 Example 1:
 Input: flowerbed = [1,0,0,0,1], n = 1
 Output: True
 Example 2:
 Input: flowerbed = [1,0,0,0,1], n = 2
 Output: False

 greedy method, scan from left to right, once find a flower to plant, plant it.
 */
public class CanPlaceFlowers {

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (flowerbed == null) return false;
        // boundary case
        /*
        [1,0,1,0,1,0,1]  0
        */
        if (n == 0) return true;
        for (int i = 0 ; i < flowerbed.length; i ++) {
            if (isValid(flowerbed, i)) {
                n --;
                // important !!! here
                if (n == 0) return true;
            }
        }
        return false;
    }

    public boolean isValid(int[] flowerbed, int index) {
        // head  [0, 0]  [0]
        if (index == 0) {
            if (flowerbed[index] == 0) {
                if (index+ 1 < flowerbed.length) {
                    if (flowerbed[index + 1] == 0) {
                        flowerbed[index] = 1;
                        return true;
                    }
                    else {
                        return false;
                    }
                }
                else {
                    return true;
                }
            }
        }
        // tail [0, 0]
        else if (index == flowerbed.length - 1) {
            if (flowerbed[index] == 0) {
                if (index -1 >= 0) {
                    if (flowerbed[index - 1] == 0) {
                        flowerbed[index] = 1;
                        return true;
                    }
                    else {
                        return false;
                    }
                }
                else {
                    return true;
                }
            }
        }
        // [0 0 0]
        else {
            if (flowerbed[index] == 0) {
                if (flowerbed[index - 1] == 0 && flowerbed[index+1] == 0) {
                    flowerbed[index] = 1;
                    return true;
                }
            }
        }
        return false;
    }
}
