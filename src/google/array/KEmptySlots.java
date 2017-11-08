package google.array;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * Created by yingtan on 10/28/17.
 *
 * 683. K Empty Slots
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 There is a garden with N slots. In each slot, there is a flower. The N flowers will bloom one by one in N days. In each day, there will be exactly one flower blooming and it will be in the status of blooming since then.

 Given an array flowers consists of number from 1 to N. Each number in the array represents the place where the flower will open in that day.

 For example, flowers[i] = x means that the unique flower that blooms at day i will be at position x, where i and x will be in the range from 1 to N.

 Also given an integer k, you need to output in which day there exists two flowers in the status of blooming, and also the number of flowers between them is k and these flowers are not blooming.

 If there isn't such day, output -1.

 Example 1:
 Input:
 flowers: [1,3,2]
 k: 1
 Output: 2
 Explanation: In the second day, the first and the third flower have become blooming.
 Example 2:
 Input:
 flowers: [1,2,3]
 k: 1
 Output: -1
 */
public class KEmptySlots {
    // flower[i] : ith day planet flower at array index flower[i]
    public int kEmptySlots(int[] flowers, int k) {
        /*
        Intuition

        Let's add flowers in the order they bloom.
        When each flower blooms, we check it's neighbors to see if they can satisfy the condition with the current flower.

        Algorithm : o(nlogn)

        We'll maintain active, a sorted data structure containing every flower that has currently bloomed.
        When we add a flower to active, we should check it's lower and higher neighbors. If some neighbor satisfies the condition, we know the condition occurred first on this day.

        */
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int day = 0; day < flowers.length; day++) {
            int flowerLoc = flowers[day];
            //	higher(E e) :Returns the least element in this set strictly greater than the given element, or null if there is no such element. o(logn)
            Integer rightFirstNeighbor = treeSet.higher(flowerLoc);
            if (rightFirstNeighbor != null && rightFirstNeighbor - flowerLoc - 1 == k) {
                return day + 1;
            }
            //lower(E e) Returns the greatest element in this set strictly less than the given element, or null if there is no such element. o(logn)
            Integer leftLastNeighbor = treeSet.lower(flowerLoc);
            if (leftLastNeighbor != null && flowerLoc - leftLastNeighbor - 1 == k) {
                return day + 1;
            }

            treeSet.add(flowerLoc);
        }
        return -1;
    }

    /*
    * Also, we can use somthing like bucket algorithm.
    * Create buckets of k elements, and split all n values between this buckets.
    * Each bucket should store min_left and max_right blooming index.
    * So, at the beginning there are no blooming flowers in buckets.
    * Each day we just set index in bucket (update values if it is needed).
    * And check left and right bucket. O(n/k) additional memory and O(n) time.
    * */
    public int kEmptySlots_bucket(int[] flowers, int k) {
        int bucketNum =  flowers.length / (k + 1);
        if ((k+1) % flowers.length > 0 ) {
            bucketNum ++;
        }
        //create a array which stores lowest flowerLoc in each bucket
        int[] lowestFlowerLocs = new int[bucketNum];
        Arrays.fill(lowestFlowerLocs, Integer.MAX_VALUE);
        // create an array which store highest flowerLoc in each bucket
        // highestFlowerLocs[i] : highest flower location in bucket i
        int[] highestFlowerLocs = new int[bucketNum];
        Arrays.fill(highestFlowerLocs, Integer.MIN_VALUE);

        for (int i = 0 ; i < flowers.length; i ++) {
            int flowerLoc = flowers[i];
            // important !!! should use (flowerLoc - 1) / (k + 1) !!!!! because index of flowerLocs starting from 1 instead of 0
            int bucketId = (flowerLoc - 1) / (k + 1);
            if (flowerLoc < lowestFlowerLocs[bucketId]) {
                lowestFlowerLocs[bucketId] = flowerLoc;
                //find the previous bucket, and if the previous bucket's max value < current flowerLoc,thencheck interval == (k - 1)
                if (bucketId > 0 && (flowerLoc - highestFlowerLocs[bucketId - 1] - 1 == k)) {
                    return i + 1;
                }
            }
            if (flowerLoc > highestFlowerLocs[bucketId]) {
                highestFlowerLocs[bucketId] = flowerLoc;
                if (bucketId < bucketNum - 1 && (lowestFlowerLocs[bucketId + 1] - flowerLoc - 1 == k)) {
                    return i + 1;
                }
            }
        }
        return -1;
    }

}
