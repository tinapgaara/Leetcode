package google.design.family;

public enum Gender {
	
	male(1, "male"), female(2, "female");
	
    private int mIndex;
    private String mName;

    private Gender(int index, String name) {

        mIndex = index;
        mName = name;
    }
    
    public int getIndex() {
        return mIndex;
    }

    public String getName() {
        return mName;
    }
	
}
