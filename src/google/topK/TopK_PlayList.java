package google.topK;

import google.design.topk.QuickSelector;
import jdk.internal.util.xml.impl.Pair;

import java.util.HashMap;
import java.util.Map;
public class TopK_PlayList {

	public static final long ONE_MINUTE_InMilliseconds = 60000;
	public static final long ONE_HOUR_InMilliseconds = 60 * ONE_MINUTE_InMilliseconds;
	public static final long ONE_DAY_InMilliseconds = 24 * ONE_HOUR_InMilliseconds;
	public static final long THIRTY_DAYS_InMilliseconds = 30 * ONE_DAY_InMilliseconds;
	
	private Map<Long, PlayLog> mHistory;
	
	private int mK;
	private Pair[] mPairBuf; // union previous old topk and new batch data
	private long[] mBuf; // new batch data
	private Map<Long, Long> mTopPlayList;

	private QuickSelector mQuickSelector;
	
	public TopK_PlayList(int K) {
		mHistory = new HashMap<Long, PlayLog>(); // musicId -> (PlayLog(List<Timestamp> , count of musicId played))
		
		mK = K;
		mPairBuf = new Pair[2 * K]; // 2k space, stores 2k stream input : general solution
		mBuf = new long[K];
		mTopPlayList = new HashMap<Long, Long>();
		
		mQuickSelector = new QuickSelector();
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

	// step 1:  2k space, stores 2k stream input
	// step 2:  use  fillPairBuf -> getPlayTimes to calculate each music's play times before startTime
	// step 3: select top k musics
	private Map<Long, Long> findTopK_Batch(long[] musicIds, long[] timestamps,
			int left, int right, long startTime) {
		
		int curTopMusicIdNum = mTopPlayList.size();
		
		int newMusicIdNum = 0;
		long musicId, timestamp;
		for (int i = left; i <= right; i++) {
			musicId = musicIds[i]; timestamp = timestamps[i];
			addPlayLogItem(musicId, timestamp);
			
			if (mTopPlayList.containsKey(musicId)) {
				continue;
			}
			
			if (curTopMusicIdNum < mK) {
				mTopPlayList.put(musicId, 0L);
				curTopMusicIdNum++;
			}
			else {
				mBuf[newMusicIdNum] = musicId;
				newMusicIdNum++;
				if (newMusicIdNum >= mK) {
					fillPairBuf(newMusicIdNum, startTime);
					//mQuickSelector.selectKthLargest_Freq(mPairBuf, 0, (2 * mK) - 1, mK - 1);
					fillTopItems();
					
					newMusicIdNum = 0;
				}
			}
		}
		
		if (newMusicIdNum > 0) {
			fillPairBuf(newMusicIdNum, startTime);
			//mQuickSelector.selectKthLargest_Freq(mPairBuf, 0, mK + newMusicIdNum - 1, mK - 1);
			fillTopItems();
		}
		else {
			refreshTopItems(startTime);
		}
		
		return mTopPlayList;
	}
	
	private void fillPairBuf(int newMusicIdNum, long startTime) {
		int curPosInPairBuf = 0;
		for (Long musicId : mTopPlayList.keySet()) {
			//if (mPairBuf[curPosInPairBuf] == null) mPairBuf[curPosInPairBuf] = new Pair(musicId, getPlayTimes(musicId, startTime));
			//else {
				//mPairBuf[curPosInPairBuf].mNum = musicId;
				//mPairBuf[curPosInPairBuf].mFreq = getPlayTimes(musicId, startTime);
			//}
			curPosInPairBuf++;
		}
		
		for (int i = 0; i < newMusicIdNum; i++) {
			long musicId = mBuf[i];
			//if (mPairBuf[curPosInPairBuf] == null) mPairBuf[curPosInPairBuf] = new Pair(musicId, getPlayTimes(musicId, startTime));
			//else {
				//mPairBuf[curPosInPairBuf].mNum = musicId;
				//mPairBuf[curPosInPairBuf].mFreq = getPlayTimes(musicId, startTime);
			//}
			curPosInPairBuf++;
		}
	}
	
	private void fillTopItems() {
		mTopPlayList.clear();
		for (int i = 0; i < mK; i++) {
			//long musicId = mPairBuf[i].mNum;
			//mTopPlayList.put(musicId, mPairBuf[i].mFreq);
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
