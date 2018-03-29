package array;

/**
 * Created by yingtan on 12/21/17.
 *
 * P <2,0,1,3> applied to A = <a,b,c,d>  => <b,c,a,d>
 This is a cycle. We just keep going forward from i to P[i] until we get back to i.
 For (int i = 0 ; i < array.len; i ++) {
 // while(ith location does not have the correct number) {
 Keep swapping
 }
 }
 How to define has the correct number ?
 When we put an element to the correct, place, we set P[i] = P[i] - A.len.  if P[i] - A.len < 0, this means this position has the correct number.
 Then, after reset everything, we still can store back the original P by P[i] + A.len

 */
public class ApplyPermutatoin {

    public void applyPermutation(int[] pos, char[] nums) {
        for (int i = 0 ; i < nums.length; i ++) {
            // keep swapping until a cycle ends, before the cycle ends, using i as a temporiarly place to save elements
            int next = i;
            // we have not touched the number at pos next
            while (pos[next] >= 0) {
                char movedCh = nums[i];
                int correctPos = pos[next];
                nums[i] = nums[correctPos];
                nums[correctPos] = movedCh;

                pos[next] = pos[next] - nums.length;
                next = correctPos;
            }
        }
        for (int i = 0 ; i < pos.length ; i ++) {
            // add the nums.len back to restore the original pattern
            pos[i] = pos[i] + nums.length;
        }
    }

    public static void main(String[] args) {
        ApplyPermutatoin ob = new ApplyPermutatoin();
        int[] p = {2,0,1,3};
        char[] chs = {'a', 'b', 'c', 'd'};
        ob.applyPermutation(p, chs);
    }
}
