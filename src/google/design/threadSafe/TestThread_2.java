package google.design.threadSafe;

public class TestThread_2 extends Thread {

	private ClassForTest mObjForTest;
	
	public TestThread_2(ClassForTest obj) {
		mObjForTest = obj;
	}
	
	public void run() {
		

		ClassForTest.Token token = null;		
		while (token == null) {
			token = mObjForTest.getToken();
			
			if (token == null) {
				long waitTimeInMillis = 10;
				try {
					sleep(waitTimeInMillis);
				} catch (InterruptedException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		}
		
		try {
			int val = mObjForTest.read_Safe(token);
			val = val + 1;
			mObjForTest.write_Safe(val, token);
		}
		catch (SyncException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		//*/

		/*
		int val = mObjForTest.read();
		val = val + 1;
		mObjForTest.write(val);
		//*/
	}
	
}
