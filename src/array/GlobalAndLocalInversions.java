package array;

/**
 * Created by yingtan on 3/1/18.
 *
 * 775. Global and Local Inversions
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 We have some permutation A of [0, 1, ..., N - 1], where N is the length of A.

 The number of (global) inversions is the number of i < j with 0 <= i < j < N and A[i] > A[j].

 The number of local inversions is the number of i with 0 <= i < N and A[i] > A[i+1].

 Return true if and only if the number of global inversions is equal to the number of local inversions.

 Example 1:

 Input: A = [1,0,2]
 Output: true
 Explanation: There is 1 global inversion, and 1 local inversion.
 Example 2:

 Input: A = [1,2,0]
 Output: false
 Explanation: There are 2 global inversions, and 1 local inversion.
 */
public class GlobalAndLocalInversions {
    public boolean isIdealPermutation(int[] A) {
        if (A == null || A.length == 0) return true;
        for (int i = 0; i < A.length - 1; i ++) {
            if (A[i] > A[i+1]) {
                if (i != 0 && A[i+1] < A[i-1]) {
                    return false;
                }
                //important !!! need to reset
                A[i+1] = A[i];
            }
        }
        return true;
    }
}
