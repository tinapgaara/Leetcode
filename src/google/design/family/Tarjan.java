package google.design.family;

import java.util.List;

// see http://www.cnblogs.com/scau20110726/archive/2013/05/26/3100812.html

public class Tarjan {

	private boolean[] mVisitedFlags;
	private boolean[] mFinishFlags;
	private DisjointSet mDisjointSet;
	private int[] mAncestors;
	
	public Tarjan(int size) {
		mVisitedFlags = new boolean[size];
		mFinishFlags = new boolean[size];
		mDisjointSet = new DisjointSet(size);
		mAncestors = new int[size];
	}
	
	public int findLCA(FamilyTree tree, int node1, int node2) {
		return tarjan(tree, tree.getRootId(), node1, node2);
	}
	
	private int tarjan(FamilyTree tree, int u, int node1, int node2) {
		int result = -1;
		
		mVisitedFlags[u] = true;
		mDisjointSet.makeSet(u);	// make a set with the sole element u
		mAncestors[mDisjointSet.findSet(u)] = u;	// record u is the ancestor of the set containing u
		List<Integer> children = tree.getChildIds(u);
	    for (int child : children) {
	        if ( ! mVisitedFlags[child] ) {
	        	result = tarjan(tree, child, node1, node2);
	        	if (result >= 0) return result;

				// 如果result<0. 说明一定不是在child这个子树下面，所以将child和u合并：即把child融入u中，当成一个node
	            mDisjointSet.union(u, child);	// combine the set containing child with the set containing u
				// 将u中的代表元素指向u, 则所有u中的元素都是指向u
	            mAncestors[mDisjointSet.findSet(u)] = u;
	            // assure that u is the ancestor of the set containing u
	        }
	    }
	    mFinishFlags[u] = true;
	    
	    if ( (u == node1) && mFinishFlags[node2] ) result = mAncestors[mDisjointSet.findSet(node2)];
	    else if ( (u == node2) && mFinishFlags[node1] ) result = mAncestors[mDisjointSet.findSet(node1)];
	    
	    return result;
	}
}
