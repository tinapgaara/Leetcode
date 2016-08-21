package google.topK;

import java.util.LinkedList;

public class PlayLog {

	private long mTimes;
	private LinkedList<Long> mTimestamps;
	
	public PlayLog() {
		mTimes = 0;
		mTimestamps = new LinkedList<Long>();
	}
	
	public void addLogItem(long timestamp) {
		mTimestamps.add(timestamp);
	}

	// get played times of this PlayLog
	public long refreshAndGetPlayTimes(long startTime) {
		refresh(startTime);
		return mTimes;
	}

	// kick off old timestamp
	public void refresh(long startTime) {
		while ( ! mTimestamps.isEmpty() ) {
			long timestamp = mTimestamps.removeFirst(); // oldest timestamp
			if (timestamp >= startTime) {
				mTimestamps.addFirst(timestamp);
				break; // important !!!!
			}
			mTimes--;
		}
	}
}
