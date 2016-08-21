package google.design.EventQueue.v1;

public class Token {

	private static long mNextTokenId = 0;
	
	private long mId;
	private int mRefCount;
	
	public Token() {
		mId = ++mNextTokenId;
		mRefCount = 1;
	}
	
	public void incRefCount() {
		mRefCount++;
	}

	public int decRefCount() {
		if (mRefCount > 0) mRefCount--;
		return mRefCount; 
	}
}
