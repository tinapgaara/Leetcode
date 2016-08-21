package google.unionfind;

/**
 * Created by max2 on 11/24/15.
 */
public class DisjointSet {

    private int[] parent ; // pass index, return parent index of this index
    private int MAX_NUM;

    public DisjointSet(int maxnum) {
        MAX_NUM = maxnum;
        parent = new int[maxnum];
    }

    public void makeSet(int x) {
        parent[x] = x;
    }

    public int findSet(int x) {
        if (x != parent[x]) {
            parent[x] = findSet(parent[x]); // x = parent[x]
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int xParent = findSet(x);
        int yParent = findSet(y);
        parent[xParent] = yParent;
    }
}
