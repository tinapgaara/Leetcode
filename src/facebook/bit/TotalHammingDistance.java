package facebook.bit;

/**
 * Created by yingtan on 5/29/17.
 *
 * 477. Total Hamming Distance Add to List
 DescriptionHintsSubmissionsSolutions
 Total Accepted: 14370
 Total Submissions: 31044
 Difficulty: Medium
 Contributors:
 kevin.xinzhao@gmail.com
 The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

 Now your job is to find the total Hamming distance between all pairs of the given numbers.

 Example:
 Input: 4, 14, 2

 Output: 6

 Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is 0010 (just
 showing the four bits relevant in this case). So the answer will be:
 HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.
 Note:
 Elements of the given array are in the range of 0 to 10^9
 Length of the array will not exceed 10^4.
 */
public class TotalHammingDistance {
    public int totalHammingDistance(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int count = 0;
        for (int i = 0 ; i < 32; i ++) {
            // each bit from lower to higher
            int zeroCount = 0;
            int oneCount = 0;
            for (int j = 0; j < nums.length; j ++) {
                int num = nums[j];
                int lowestBit = (num) & 1; // using i here
                if (lowestBit == 1) {
                    oneCount ++;
                }
                else {
                    zeroCount ++;
                }
                nums[j] = nums[j] >> 1;
            }
            count = count + zeroCount * oneCount;
        }
        return count;
    }

}

