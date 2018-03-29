package linkedin.array;

/**
 * Created by yingtan on 11/28/17.
 */
public class FindCelebrity {

    public int findCelebrity(int n) {
        if (n == 0) return -1;
        int candidate = 0;
        for (int i = 1 ; i < n ; i ++) {
            if (knows(candidate, i) && !knows(i, candidate)) {
                candidate = i;
            }
        }
        for(int i = 0; i < n ; i ++) {
            if (candidate != i) {
                if(!knows(i, candidate) || knows(candidate, i)) {
                    return -1;
                }
            }
        }
        return candidate;
    }

    public boolean knows(int a, int b) {
        return false;
    }
}
