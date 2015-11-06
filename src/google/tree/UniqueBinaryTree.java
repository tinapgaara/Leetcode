package google.tree;

/**
 * Created by yingtan on 11/6/15.
 */
/* generate all possible complete tree with number of nodes is N
*  ???????
* */
public class UniqueBinaryTree {

    public int numTrees(int n) {
        int sum = 0;
        for (int i = 1; i <= n ; i ++) {
            sum = sum + numTrees(i-1) * numTrees(n-i);
        }
        return sum;
    }
}
