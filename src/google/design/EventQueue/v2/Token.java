package google.design.EventQueue.v2;

public class Token {

	private static long mNextTokenId = 0;
	
	private long mId;
	
	public Token() {
		mId = ++mNextTokenId;
	}
	
}
