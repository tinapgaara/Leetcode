package google.design.EventQueue.v1;

public class EventHandler {

	public static final long SLEEP_TIME_InMilliseconds = 10;
	
	private EventQueue mQueue;
	private ResponsePool mResponsePool;
	private CountThread mThread;
	
	public EventHandler() {
		mQueue = new EventQueue();
		mResponsePool = new ResponsePool();
		
		startBackThread();
	}
	
	private void startBackThread() {
		mThread = new CountThread(mQueue, mResponsePool);
		mThread.start();
	}
	
	public synchronized void stopBackThread() {
		mThread.setStopFlag();
	}
	
	public synchronized void incCount() {
		mQueue.addEvent(new Event(Cmd.IncCount, 1L));
	}
	
	public synchronized long getCount_LastMin() {
		return getCount(Cmd.GetCount_LastMin);
	}
	
	public synchronized long getCount_LastHour() {
		return getCount(Cmd.GetCount_LastHour);
	}
	
	public synchronized long getCount_LastDay() {
		return getCount(Cmd.GetCount_LastDay);
	}
	
	private long getCount(Cmd cmd) {
		long result = 0;
		Event event = new Event(cmd);
		Token token = mQueue.addEvent(event);
		while (token != null) {	
			try {
				Thread.sleep(SLEEP_TIME_InMilliseconds);
			}
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Response res = mResponsePool.fetchResponse(token);
			if (res != null) {
				result = res.mResult;
				token = null;	// break the loop
			}
		}
		return result;
	}
}
