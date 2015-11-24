package google.design.family;

import java.util.List;

public class FamilyMember {

	
	 private int mId;
	 private Gender mGender;
	 private FamilyMember mFather, mMother;
	 private List<FamilyMember> mChildren;
	 private List<FamilyMember> mBrothers;
	 
	 public int getId() {
		 return mId;
	 }
	 
	 public Gender getGender() {
		 return mGender;
	 }
	 
	 public FamilyMember getFather() {
		 return mFather;
	 }

	 public FamilyMember getMother() {
		 return mMother;
	 }

	 public List<FamilyMember> getChildren() {
		 return mChildren;
	 }

	 public List<FamilyMember> getBrothers() {
		 return mBrothers;
	 }
	 
	 public boolean isBrother(FamilyMember member) {
		 return ( (mBrothers != null) && mBrothers.contains(member) );
	 }

	 public boolean isChild(FamilyMember member) {
		 return ( (mChildren != null) && mChildren.contains(member) );
	 }
}
