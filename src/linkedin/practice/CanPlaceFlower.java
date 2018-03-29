package linkedin.practice;

/**
 * Created by yingtan on 11/22/17.
 *
 * Suppose you have a long flowerbed in which some of the plots are planted and some are not. However, flowers cannot be planted in adjacent plots - they would compete for water and both would die.

 Given a flowerbed (represented as an array containing 0 and 1,
 where 0 means empty and 1 means not empty), and a number n,
 return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.

 Example 1:
 Input: flowerbed = [1,0,0,0,1], n = 1
 Output: True
 Example 2:
 Input: flowerbed = [1,0,0,0,1], n = 2
 Output: False

 greedy method, scan from left to right, once find a flower to plant, plant it.
 */
public class CanPlaceFlower {

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (flowerbed == null) return false;
        // boundary case
        /*
        [1,0,1,0,1,0,1]  0
        */
        if (n == 0) return true;

        for (int i = 0 ; i < flowerbed.length; i ++) {
            if (isValid(flowerbed, i)) {
                flowerbed[i] = 1;
                n --;

                if (n == 0) return true;
            }
        }
        return false;
    }

    public boolean isValid(int[] flowerbed, int i) {
        //left side should be 0 zne right side should also be 0
        // firstly check left side
        boolean leftSideGood = false;
        boolean rightSideGood = false;
        if (i == 0 || (i > 0 && flowerbed[i-1] == 0)) {
            leftSideGood = true;
        }
        if ( (i == flowerbed.length - 1) ||
                ((i < flowerbed.length -1) && (flowerbed[i+1] == 0)) ) {
            rightSideGood = true;
        }
        if (leftSideGood && rightSideGood) {
            return true;
        }
        return false;
    }
}
