package facebook.bit;

/**
 * Created by yingtan on 1/4/18.
 *
 * 461. Hamming Distance
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

 Given two integers x and y, calculate the Hamming distance.

 Note:
 0 ≤ x, y < 231.

 Example:

 Input: x = 1, y = 4

 Output: 2

 Explanation:
 1   (0 0 0 1)
 4   (0 1 0 0)
 ↑   ↑

 The above arrows point to positions where the corresponding bits are different.

 */
public class HammingDistance {
    public int hammingDistance(int x, int y) {
        int diff = 0;
        for (int i = 0 ; i < 32; i ++) {
            int digitx = x & 1;
            int digity = y & 1;
            if (digitx != digity) {
                diff ++;
            }
            x = x >> 1;
            y = y >> 1;
        }
        return diff;
    }
}
