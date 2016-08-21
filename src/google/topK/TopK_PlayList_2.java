package google.topK;

import jdk.internal.util.xml.impl.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Set;


public class TopK_PlayList_2 {

	public static final long ONE_MINUTE_InMilliseconds = 60000;
	public static final long ONE_HOUR_InMilliseconds = 60 * ONE_MINUTE_InMilliseconds;
	public static final long ONE_DAY_InMilliseconds = 24 * ONE_HOUR_InMilliseconds;
	public static final long THIRTY_DAYS_InMilliseconds = 30 * ONE_DAY_InMilliseconds;
	
	private Map<Long, PlayLog> mHistory;// musicId : <count, List<Timestamp>>
	
	private int mK;// k-size window
	/*
	private Pair[] mPairBuf;
	//*/
	private Set<Long> mBuf; // new arrived data using hashset
	private Map<Long, Long> mTopPlayList; // previous top-k musicId, and play times

	private PriorityQueue<Pair> mHeap; // Pair<musicId, playTimes>
	
	public TopK_PlayList_2(int K) {
		mHistory = new HashMap<Long, PlayLog>();
		
		mK = K;
		/*
		mPairBuf = new Pair[2 * K];
		//*/
		mBuf = new HashSet<Long>();
		mTopPlayList = new HashMap<Long, Long>();
		
		mHeap = new PriorityQueue<Pair>(2 * K, new Comparator<Pair>() {  
			public int compare(Pair pair_1, Pair pair_2) {  
              //long c = pair_2.mFreq - pair_1.mFreq;
              //if (c == 0) return 0;
              //else if (c < 0) return -1;
              //else return +1;
				return 0;
            }
		});
	}
	
	public void findTopK_PlayList(long[] musicIds, long[] timestamps, int batchSize) {
		
		int length = musicIds.length;
		int cBatches = (int) Math.ceil( (double) length / batchSize);
    	int curPosInArr = 0;
    	for (int batchNo = 0; batchNo < cBatches; batchNo++) {
    		Map<Long, Long> result = findTopK_Batch(musicIds, timestamps,
        			curPosInArr, Math.min(length - 1, curPosInArr + batchSize - 1),
        			System.currentTimeMillis() - THIRTY_DAYS_InMilliseconds);
        	showResult(result);
        	
        	curPosInArr += batchSize;
    	}
	}
    
	public void eliminateStale(long startTime) {
		for (Long musicId : mHistory.keySet()) {
			PlayLog playLog = mHistory.get(musicId);
			if (playLog != null) playLog.refresh(startTime);
		}
	}
	
	private Map<Long, Long> findTopK_Batch(long[] musicIds, long[] timestamps,
			int left, int right, long startTime) {
		
		int curTopMusicIdNum = mTopPlayList.size();
		
		int newMusicIdNum = 0;
		long musicId, timestamp;
		for (int i = left; i <= right; i++) {
			musicId = musicIds[i]; timestamp = timestamps[i];
			addPlayLogItem(musicId, timestamp);// store musicId and timestamp to history
			
			if (mTopPlayList.containsKey(musicId)) {
				continue;
			}
			
			if (curTopMusicIdNum < mK) {
				mTopPlayList.put(musicId, 0L); // playTimes = 0, just record there is once playing
				curTopMusicIdNum++;
			}
			else {
				if ( ! mBuf.contains(musicId) ) {
					mBuf.add(musicId);
					newMusicIdNum++;
					if (newMusicIdNum >= mK) {
						fillPairBuf(startTime);
						/*
						mQuickSelector.selectKthLargest_Freq(mPairBuf, 0, (2 * mK) - 1, mK - 1);
						//*/
						fillTopItems();
						
						mBuf.clear();
						newMusicIdNum = 0;
					}
				}
			}
		}
		// right side has not been completedly filled, stream has stopped. So store it to heap
		if (newMusicIdNum > 0) {
			fillPairBuf(startTime);
			/*
			mQuickSelector.selectKthLargest_Freq(mPairBuf, 0, mK + newMusicIdNum - 1, mK - 1);
			//*/
			fillTopItems();
			mBuf.clear();
		}
		else {// always come the same music ids compared to before, here, do not go through heap, so we need refresh playtimes in topkplaylist
			refreshTopItems(startTime);
		}
		
		return mTopPlayList;
	}
	
	private void fillPairBuf(long startTime) {
		mHeap.clear();
		for (Long musicId : mTopPlayList.keySet()) {
			//mHeap.add(new Pair(musicId, getPlayTimes(musicId, startTime)));
		}
		
		for (Long musicId : mBuf) {
			//mHeap.add(new Pair(musicId, getPlayTimes(musicId, startTime)));
		}
	}
	
	private void fillTopItems() {
		mTopPlayList.clear();
		for (int i = 0; i < mK; i++) {
			Pair pair = mHeap.poll();
			if (pair == null) break;
			//mTopPlayList.put(pair.mNum, pair.mFreq);
		}
	}
	
	private void refreshTopItems(long startTime) {
		for (Long musicId : mTopPlayList.keySet()) {
			mTopPlayList.put(musicId, getPlayTimes(musicId, startTime));
		}
	}
	
	private long getPlayTimes(long musicId, long startTime) {
		PlayLog playLog = mHistory.get(musicId);
		if (playLog == null) return 0;
		return playLog.refreshAndGetPlayTimes(startTime);
	}
	
	private void addPlayLogItem(long musicId, long timestamp) {
		PlayLog playLog = mHistory.get(musicId);
		if (playLog == null) {
			playLog = new PlayLog();
			mHistory.put(musicId, playLog);
		}
		playLog.addLogItem(timestamp);
	}
	
	private void showResult(Map<Long, Long> result) {
		System.out.print("RESULT: ");
    	for (Long musicId : result.keySet()) {
    		System.out.print(" [" + musicId + ", " + result.get(musicId) + "]");
    	}
		System.out.println("");
	}
	
}
