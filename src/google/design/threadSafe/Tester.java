package google.design.threadSafe;

public class Tester {

    public static void main(String[] args) {
    	
    	Tester tester = new Tester();
    	
    	/*
    	tester.doTest_1();
    	//*/
    	tester.test();
    }

	public void test() {

		final int numThreads = 1000;
		Box box = new Box();
		TestThread[] threads = new TestThread[numThreads];

		for (int i = 0; i < numThreads; i++) {
			threads[i] = new TestThread(box);
		}

		for (int i = 0 ; i < numThreads; i ++) {
			threads[i].run(); // run 100 times inc()
		}

		System.out.print(box.get());

	}
    
    public void doTest_1() {
    	
    	ClassForTest objForTest = new ClassForTest();
    	
    	final int numThreads = 1000;
    	
    	TestThread_1[] threads = new TestThread_1[numThreads];
    	
    	for (int i = 0; i < numThreads; i++) {
    		threads[i] = new TestThread_1(objForTest);
    	}
    	
    	for (int i = 0; i < numThreads; i++) {
    		threads[i].start();
    	}

    	//*
    	boolean allFinished = false;
    	while ( ! allFinished ) {
    		allFinished = true;
	    	for (int i = 0; i < numThreads; i++) {
	    		if ( threads[i].getState() != Thread.State.TERMINATED ) {
	    			allFinished = false;
	    		
	    			long millis = 100;
	    			try {
						Thread.sleep(millis);
	    			} catch (InterruptedException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    			
	    			break;
	    		}
	    	}
    	}
    	//*/
    	
    	int result = objForTest.read_NotSafe();	
    	if (result == numThreads)
    	    System.out.println("YES----thread safe, the result = [" + result + "]");
    	else
    	    System.out.println("NOT thread safe, the result = [" + result + "]");
    }

    public void doTest_2() {
    	
    	ClassForTest objForTest = new ClassForTest();
    	
    	final int numThreads = 1000;
    	
    	TestThread_2[] threads = new TestThread_2[numThreads];
    	
    	for (int i = 0; i < numThreads; i++) {
    		threads[i] = new TestThread_2(objForTest);
    	}
    	
    	for (int i = 0; i < numThreads; i++) {
    		threads[i].start();
    	}

    	//*
    	boolean allFinished = false;
    	while ( ! allFinished ) {
    		allFinished = true;
	    	for (int i = 0; i < numThreads; i++) {
	    		if ( threads[i].getState() != Thread.State.TERMINATED ) {
	    			allFinished = false;
	    		
	    			long millis = 100;
	    			try {
						Thread.sleep(millis);
	    			} catch (InterruptedException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    			
	    			break;
	    		}
	    	}
    	}
    	//*/
    	
    	int result = objForTest.read_NotSafe();
    	if (result == numThreads)
    	    System.out.println("YES----thread safe, the result = [" + result + "]");
    	else
    	    System.out.println("NOT thread safe, the result = [" + result + "]");
    }
}
