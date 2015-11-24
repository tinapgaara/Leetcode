package google.design.family;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public List<FamilyMember> findCommonAncestors(FamilyMember member1, FamilyMember member2) {
		
		if ( (member1 == null) || (member2 == null) ) {
			return null;
		}
		
		List<FamilyMember> result = null;
		
		if ( member1.isBrother(member2) || member1.isChild(member2) ) {
			result = new ArrayList<FamilyMember>();
			result.add(member1.getFather());
			result.add(member1.getMother());
			return result;
		}
		
		if (member2.isChild(member1)) {
			result = new ArrayList<FamilyMember>();
			result.add(member2.getFather());
			result.add(member2.getMother());
			return result;
		}
		
		List<FamilyMember> result1 = findCommonAncestors(member1, member2.getFather());
		List<FamilyMember> result2 = findCommonAncestors(member1, member2.getMother());
		result = combine(result1, result2);
		if (result == null) {
			result1 = findCommonAncestors(member1.getFather(), member2);
			result2 = findCommonAncestors(member1.getMother(), member2);
			result = combine(result1, result2);
		}
		
		return result;
	}
	
	private List<FamilyMember> combine(List<FamilyMember> list1, List<FamilyMember> list2) {
		if (list1 == null) {
			if (list2 == null) return null;
			else return list2;
		}
		else if (list2 == null) {
			return list1;
		}
		
		for (FamilyMember member : list2) {
			if ( ! list1.contains(member) ) list1.add(member);
		}
		list2.clear();
		
		return list1;
	}
}
