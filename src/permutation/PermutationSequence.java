package permutation;

/**
 * Created by yingtan on 10/24/15.
 */
/*
* index = ((k-1) / (n-1)! )
* res = res + nums[index];
* move nums
* k = k - index * ((n-1)!)
* n --
*
* */
public class PermutationSequence {

    public String getPermutation(int n, int k) {
        if (n == 1) {
            if (k < 2) return n + "";
            else return null;
        }
        int[] nums = new int[n];
        int factorial = 1;
        for (int i = 0; i < n ; i ++) {
            nums[i] = i+1;
            factorial = factorial * nums[i]; // trick, record factor
        }

        String res = "";
        while (n > 0) {
            factorial = factorial / (n); // Important !!!
            int index = (k-1) / (factorial);// Important !!!!
            if (index >= 0) {
                int curNum = nums[index];
                res = res  + curNum;
                k = k - index * factorial;
                n --;

                // move nums : important !!!
                for (int i = index; i < nums.length-1; i ++)
                    nums[i] = nums[i + 1];

            } else {
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        PermutationSequence ob = new PermutationSequence();
        ob.getPermutation(8, 8590);
    }
}
