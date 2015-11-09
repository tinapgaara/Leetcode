package google.design.threadSafe;

public class TestThread_1 extends Thread {

	private ClassForTest mObjForTest;
	/*
	private boolean mFinishFlag;
	//*/
	
	public TestThread_1(ClassForTest obj) {
		mObjForTest = obj;
		/*
		mFinishFlag = false;
		//*/
	}
	
	public void run() {
		/*
		int val = mObjForTest.read();
		//*/
		
		//*
		long millis = 10;
		try {
			sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//*/
		
		/*
		val = val + 1;
		mObjForTest.write(val);
		//*/
		
		mObjForTest.readAndWrite_Safe();
		
		/*
    	System.out.println("A thread Finished");
		mFinishFlag = true;
		//*/
	}
	
	/*
	public boolean isFinished() {
		return mFinishFlag;
	}
	//*/
}
