package google.design.family;

// see http://dongxicheng.org/structure/union-find-set/

public class DisjointSet {

	private int[] mFather;	// mFather[x] is the parent of node x 
	private int[] mRank;  	// mRank[x] is the rank of node x, control depth of tree

	public DisjointSet(int maxNumNodes) {
		mFather = new int[maxNumNodes];
		mRank = new int[maxNumNodes];
	}
	
	public void makeSet(int x) { 
		mFather[x] = x; 
	    mRank[x] = 0; 
	}
	
	// find the set containing x, and compress paths when backtracing
	public int findSet(int x) {
	    if (x != mFather[x]) {
	    	mFather[x] = findSet(mFather[x]);
	    }
	    return mFather[x];
	}
	
	// combine two sets containing x and y respectively, according to their ranks
	public void union(int x, int y) {
	    x = findSet(x);
	    y = findSet(y);
	    if (x == y) return;
	    
	    if (mRank[x] > mRank[y]) {
	    	mFather[y] = x;
	    }
	    else {
	    	if (mRank[x] == mRank[y]) {
	    		mRank[y]++;
	    	}
	    	mFather[x] = y;
	    }
	 
	}
}
