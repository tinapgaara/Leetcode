package google.design.family;

import java.util.ArrayList;
import java.util.List;

public class FamilyTree {

	private FamilyMember mRoot;
	private FamilyMember[] mMembers;
	
	public int getRootId() {
		return mRoot.getId();
	}
	
	public List<Integer> getChildIds(int nodeId) {
		List<Integer> result = new ArrayList<Integer>();
		
		FamilyMember member = mMembers[nodeId];
		List<FamilyMember> children = member.getChildren();
		if (children != null) {
		    for (FamilyMember child : children) {
		    	result.add(new Integer(child.getId()));
		    }
		}
		
		return result;
	}
	
	public Gender getGender(int nodeId) {
		return mMembers[nodeId].getGender();
	}
}
