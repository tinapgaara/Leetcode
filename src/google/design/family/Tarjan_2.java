package google.design.family;

import java.util.List;

// see http://www.cnblogs.com/scau20110726/archive/2013/05/26/3100812.html

public class Tarjan_2 {

	private boolean[] mVisitedFlags;
	private boolean[] mFinishFlags;
	private DisjointSet mDisjointSet;
	private int[] mAncestors_Male;
	private int[] mAncestors_Female;
	
	public Tarjan_2(int size) {
		mVisitedFlags = new boolean[size];
		mFinishFlags = new boolean[size];
		mDisjointSet = new DisjointSet(size);
		mAncestors_Male = new int[size];
		mAncestors_Female = new int[size];
	}
	
	public Pair findLCA(FamilyTree tree, int node1, int node2) {
		return tarjan(tree, tree.getRootId(), node1, node2);
	}
	
	private Pair tarjan(FamilyTree tree, int u, int node1, int node2) {
		Pair result = null;
		
		boolean isMale = true;
		if (tree.getGender(u) == Gender.female) isMale = false;
		
		mVisitedFlags[u] = true;
		mDisjointSet.makeSet(u);	// make a set with the sole element u
		if (isMale)
		    mAncestors_Male[mDisjointSet.findSet(u)] = u;	// record u is the ancestor of the set containing u
		else
			mAncestors_Female[mDisjointSet.findSet(u)] = u;
		List<Integer> children = tree.getChildIds(u);
	    for (int child : children) {
	        if ( ! mVisitedFlags[child] ) {
	        	result = tarjan(tree, child, node1, node2);
	        	if (result != null) return result;
	        	
	            mDisjointSet.union(u, child);	// combine the set containing child with the set containing u
	            if (isMale) mAncestors_Male[mDisjointSet.findSet(u)] = u;	// assure that u is the ancestor of the set containing u
	            else mAncestors_Female[mDisjointSet.findSet(u)] = u;
	        }
	    }
	    mFinishFlags[u] = true;
	    
	    if ( (u == node1) && mFinishFlags[node2] )
	    	result = new Pair(mAncestors_Male[mDisjointSet.findSet(node2)], mAncestors_Female[mDisjointSet.findSet(node2)]);
	    else if ( (u == node2) && mFinishFlags[node1] )
	    	result = new Pair(mAncestors_Male[mDisjointSet.findSet(node1)], mAncestors_Female[mDisjointSet.findSet(node2)]);
	    
	    return result;
	}
	
	public class Pair {
		public int mMaleNodeId;
		public int mFemaleNodeId;
		
		public Pair(int maleNodeId, int femaleNodeId) {
			mMaleNodeId = maleNodeId;
			mFemaleNodeId = femaleNodeId;
		}
	}
}
