package array;

/**
 * Created by yingtan on 12/19/17.
 *
 * Given an array, each element represents max steps you can jump from that location. A = {3,3,1,0,2,0,1}. Check if it is possible to advance to the last location.
 Solution: for each index, furthest we can advance from index i is i + A[i], then we can reach any indexs between [i, i + A[i]], so, we keep a variable to track the max position we can reach.
 If: curpos == maxpos and curpos + A[curpos] == maxpos, then the current location is the furthest pos we can reach. If the curepos  < lastindex, we return false. Else, we return true.

 */
public class AdvanceThroughArray {

    public boolean canReach(int[] steps) {
        int furthestPlaceCanReach = 0;
        for (int i = 0 ; i < steps.length ; i ++) {
            furthestPlaceCanReach = Math.max(furthestPlaceCanReach, i + steps[i]);
            if (furthestPlaceCanReach <= i) {
                 return false;
            }
        }
        if (furthestPlaceCanReach >= steps.length - 1) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        AdvanceThroughArray ob = new AdvanceThroughArray();
        int[] steps = {3,3,1,0,2,0,1};
        ob.canReach(steps);
    }
}
