package google.design.EventQueue.v2;

import java.util.LinkedList;

public class CountThread extends Thread {

	public static final long ONE_MINUTE_InMilliseconds = 60000;
	public static final long ONE_HOUR_InMilliseconds = 60 * ONE_MINUTE_InMilliseconds;
	public static final long ONE_DAY_InMilliseconds = 24 * ONE_HOUR_InMilliseconds;
	
	private LinkedList<CountWithTimestamp> mCountList;
	private EventQueue mEventQueue;
	private ResponsePool mResponsePool;
	private boolean mStopFlag;
	
	private long mLastTimestamp;
	
	public CountThread(EventQueue eventQueue, ResponsePool responsePool) {
		mCountList = new LinkedList<CountWithTimestamp>();
		mEventQueue = eventQueue;
		mResponsePool = responsePool;
		mStopFlag = false;
		mLastTimestamp = 0;
	}
	
	public void setStopFlag() {
		mStopFlag = true;
	}
	
	public void run() {
		
		while ( ! mStopFlag ) {
			Event event = mEventQueue.fetchEvent();
			if (event == null) {
				eliminateStale();
				try {
					Thread.sleep(EventHandler.SLEEP_TIME_InMilliseconds);
				}
				catch (InterruptedException e) {
					// nothing to do
				}
			}
			else {
				long startTime = 0, sum;
				switch (event.mCmd) {
				case IncCount:
					mCountList.add(new CountWithTimestamp(1, event.mTimestamp));
					break;
					
				case GetCount_LastMin:
					startTime = event.mTimestamp - ONE_MINUTE_InMilliseconds;
					break;
				case GetCount_LastHour:
					startTime = event.mTimestamp - ONE_HOUR_InMilliseconds;
					break;
				case GetCount_LastDay:
					startTime = event.mTimestamp - ONE_DAY_InMilliseconds;
					break;
				}
				
				if (startTime != 0) {
					sum = countSum(startTime);
					mResponsePool.addResponse(event.mToken, new Response(sum));
				}
			}
		}
	}
	
	private long countSum(long startTime) {
		long sum = 0;
		for (int i = mCountList.size() - 1; i >= 0; i--) {
			CountWithTimestamp count = mCountList.get(i);
			if (count.mTimestamp < startTime) {
				break;
			}
			sum += count.mCount;
		}
		return sum;
	}
	
	private void eliminateStale() {
		if (mLastTimestamp > 0) {
			long startTime = mLastTimestamp - ONE_DAY_InMilliseconds;
			CountWithTimestamp count;
			while ( ! mCountList.isEmpty() ) {
				count = mCountList.remove();
				if (count.mTimestamp >= startTime) {
					mCountList.addFirst(count);
					break;
				}
			}
		}
	}
	
	public class CountWithTimestamp {
		public long mCount;
		public long mTimestamp;
		
		public CountWithTimestamp(long count, long timestamp) {
			mCount = count;
			mTimestamp = timestamp;
		}
	}
}
