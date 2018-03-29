package facebook.array;

/**
 * Created by yingtan on 3/20/18.
 *
 * 801. Minimum Swaps To Make Sequences Increasing
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 We have two integer sequences A and B of the same non-zero length.

 We are allowed to swap elements A[i] and B[i].  Note that both elements are in the same index position in their respective sequences.

 At the end of some number of swaps, A and B are both strictly increasing.  (A sequence is strictly increasing if and only if A[0] < A[1] < A[2] < ... < A[A.length - 1].)

 Given A and B, return the minimum number of swaps to make both sequences strictly increasing.  It is guaranteed that the given input always makes it possible.

 Example:
 Input: A = [1,3,5,4], B = [1,2,3,7]
 Output: 1
 Explanation:
 Swap A[3] and B[3].  Then the sequences are:
 A = [1, 3, 5, 7] and B = [1, 2, 3, 4]
 which are both strictly increasing.
 Note:

 swap[n] means the minimum swaps to make the A[i] and B[i] sequences increasing for 0 <= i <= n in condition that we swap A[n] and B[n]
 not_swap[n] is the same with A[n] and B[n] not swapped.

 */
public class MinimumSwapsToMakeSequencesIncreasing {
    public int minSwap(int[] A, int[] B) {
        if (A == null || B == null) return 0;
        int[] swap = new int[A.length];
        int[] noswap = new int[A.length];
        int len = A.length;
        swap[0] = 1;
        for (int i = 1; i < len; i ++) {
            swap[i] = len;
            noswap[i] = len;
            if (A[i-1] < A[i] && B[i-1] < B[i]) {
                // 0 3 4
                // 0 1 2
                // to swap (4,2) and make sure increasing, it is better to swap [3,1] as well
                // so, swap[i] = swap[i-1] + 1
                swap[i] = swap[i-1] + 1;
                // or, don't swap
                noswap[i] = noswap[i-1];

            }
            if (A[i - 1] < B[i] && B[i-1] < A[i]) {
                // 0 4 4
                // 0 1 6
                // then, can swap A[i]  and B[i] without swapping A[i-1] and B[i-1]
                swap[i] = Math.min(noswap[i-1] + 1, swap[i]);
                // or swap A[i-1] and B[i-1] without swapping A[i] and B[i]
                noswap[i] = Math.min(swap[i-1], noswap[i]);
            }
        }
        return Math.min(swap[A.length - 1], noswap[A.length - 1]);
    }
}
