package google.design.threadSafe;

import java.util.Random;

public class ClassForTest {

	private int mVar;
	private Token mCurToken;
	
	public ClassForTest() {
		mVar = 0;
		mCurToken = null;
	}
	
	public synchronized Token getToken() {
		if (mCurToken != null) { // lock be occupied by others function
			return null;
		}
		
		mCurToken = new Token(); // lock has not been occupied by others function
		return mCurToken;
	}
	
	//*
	public synchronized int read_Safe(Token token) throws SyncException {
		if ( (token != null) && (token == mCurToken) ) {
			return mVar;
		}
		else {
			throw new SyncException();
		}
	}
	
	public synchronized void write_Safe(int val, Token token) throws SyncException {
		if ( (token != null) && (token == mCurToken) ) {
		    mVar = val;
		    mCurToken = null;
		}
		else {
			throw new SyncException();
		}
	}
	//*/

	//*
	public synchronized void readAndWrite_Safe() {
		int newVal = mVar + 1;
		mVar = newVal;
	}
	//*/


	/*
	int val = read_NotSafe();
	val = val + 1;
	write_NotSafe(val);
	//*/
	
	//*
	public synchronized int read_NotSafe() {
		return mVar;
	}
	//*/
	
	//*
	public synchronized void write_NotSafe(int val) {
		mVar = val;
	}
	//*/

	/*
	public void readAndWrite() {
		int newVal = mVar + 1;
		mVar = newVal;
	}
	//*/
	
	public class Token {
		
		private long mTokenId;
		
		public Token() {
			Random random = new Random();
			mTokenId = random.nextLong();
		}
	}
}
