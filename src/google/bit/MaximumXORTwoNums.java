package google.bit;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yingtan on 8/21/17.
 *
 * 421. Maximum XOR of Two Numbers in an Array
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.

 Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.

 Could you do this in O(n) runtime?

 Example:

 Input: [3, 10, 5, 25, 2, 8]

 Output: 28

 Explanation: The maximum result is 5 ^ 25 = 28.
 then 5 ^ 28 = 25

 思路
 利用XOR的性质，a^b = c, 则有 a^c = b，且 b^c = a;
 所以每次从高位开始，到某一位为止，所能获得的最大的数。设置变量mask用来表示能形成的值，
 每一次将mask和其他的num相与得到的值加入set，表示在当前这一位上，数组里有这么多prefix。

 假定在某一位上的任意两数xor能得到的最大值是tmp,那么他一定满足a(xor)b = tmp,
 其中set.contains(a) && set.contains(b). 所以，我们只需要判断b(xor)tmp的结果是不是在当前这一位下的set内，
 就可以知道这个tmp能不能又这个set中的任意两个数组成。

 */
    public class MaximumXORTwoNums {

    public int findMaximumXOR(int[] nums) {
        // test each bit pose, 判断能不能构成所需要的值；
        //2^31 − 1 = 2147483647
        //2^31 = -2147483648
        int mask = 0, max = 0;
        for (int i = 31; i >= 0; i--) {

            //n the first iteration, we will have all numbers that have the highest bit as 1
            // 10000000000000000000000000000000
            // 11000000000000000000000000000000
            mask |= (1 << i);
            Set<Integer> prefixes = new HashSet<>();
            for (int n : nums) {
                // 取每个num的最高xxx位,当i=31,取每个num的最高1位,其余位为0,i=30,取每个num的最高2位,其余位为0
                prefixes.add(mask & n);
            }
            // 假设当前所能达到的最大值是这个temp值；
            /*
            * Then we go through the set, for each number in the set,
            * we want to see if we can find a number in this set that by XOR it
            * will give us 1 in the highest bit.
            * If we can find one, we know the highest bit is the current bit we have.
            * And we keep looking for the next bit.
            * */
            int tmp = max | (1 << i);
            for (int pre : prefixes) {
                if (prefixes.contains(tmp ^ pre)) {
                    max = tmp;
                    break;
                }
            }
        }
        return max;
    }


    public static void main(String[] args) {
        MaximumXORTwoNums ob = new MaximumXORTwoNums();
        ob.findMaximumXOR(new int[]{3, 10, 5, 25, 2, 8});
    }
}
