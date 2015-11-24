package google.design.topk;

import java.util.HashMap;
import java.util.Map;

public class TopK_FreqStream {

	private int mK;
	private Pair[] mPairBuf;
	private int[] mNewNumBuf;
	private Map<Integer, Long> mMap_Top;
	private Map<Integer, Long> mMap_Bottom;

	private QuickSelector mQuickSelector;
	
    public static void main(String[] args) {
    	
    	int K = 5;
    	TopK_FreqStream obj = new TopK_FreqStream(K);
    	
    	int[] arr = new int[] {1, 3, 10, 808, 7, 20, 30, 7, 8, 202, 5, 3, 7, 707, 4, 8, 1, 808, 808, 808, 10, 10, 10, 10, 10};
    	int batchSize = 3;
    	
    	obj.findTopK_FreqStream(arr, batchSize);
    }
    
	public TopK_FreqStream(int K) {
		mK = K;
		mPairBuf = new Pair[2 * K];
		mNewNumBuf = new int[K];
		mMap_Top = new HashMap<Integer, Long>();
		mMap_Bottom = new HashMap<Integer, Long>();
		
		mQuickSelector = new QuickSelector();
	}
	
	public void findTopK_FreqStream(int[] arr, int batchSize) {
		
		int length = arr.length;
		int cBatches = (int) Math.ceil( (double) length / batchSize);
    	int curPosInArr = 0;
    	for (int batchNo = 0; batchNo < cBatches; batchNo++) {
    		Map<Integer, Long> result = findTopK_Batch(arr, 
        			curPosInArr, Math.min(length - 1, curPosInArr + batchSize - 1));
        	showResult(result);
        	
        	curPosInArr += batchSize;
    	}
	}
    
	private Map<Integer, Long> findTopK_Batch(int[] arr, int left, int right) {
		
		int cCurTopNums = mMap_Top.size();
		
		int cNewNums = 0;
		int num;
		Long freq;
		for (int i = left; i <= right; i++) {
			num = arr[i];
			freq = mMap_Top.get(num);
			if (freq == null) {
				if (cCurTopNums < mK) {
					mMap_Top.put(num, 1L);
					cCurTopNums++;
				}
				else {
					freq = mMap_Bottom.get(num);
					if (freq == null) mMap_Bottom.put(num, 1L);
					else mMap_Bottom.put(num, freq + 1);
					
					mNewNumBuf[cNewNums] = num;
					cNewNums++;
					if (cNewNums >= mK) {
						fillPairBuf(cNewNums);
						mQuickSelector.selectKthLargest_Freq(mPairBuf, 0, (2 * mK) - 1, mK - 1);
						fillTopItems();
						
						cNewNums = 0;
					}
				}
			}
			else {
				mMap_Top.put(num, freq + 1);
			}
		}
		
		if (cNewNums > 0) {
			fillPairBuf(cNewNums);
			mQuickSelector.selectKthLargest_Freq(mPairBuf, 0, mK + cNewNums - 1, mK - 1);
			fillTopItems();
		}
		
		return mMap_Top;
	}
	
	private void fillPairBuf(int cNewNums) {
		int curPosInPairBuf = 0;
		for (Integer num : mMap_Top.keySet()) {
			if (mPairBuf[curPosInPairBuf] == null) mPairBuf[curPosInPairBuf] = new Pair(num, mMap_Top.get(num));
			else {
				mPairBuf[curPosInPairBuf].mNum = num;
				mPairBuf[curPosInPairBuf].mFreq = mMap_Top.get(num);
			}
			curPosInPairBuf++;
		}
		
		for (int i = 0; i < cNewNums; i++) {
			int num = mNewNumBuf[i];
			if (mPairBuf[curPosInPairBuf] == null) mPairBuf[curPosInPairBuf] = new Pair(num, mMap_Bottom.get(num));
			else {
				mPairBuf[curPosInPairBuf].mNum = num;
				mPairBuf[curPosInPairBuf].mFreq = mMap_Bottom.get(num);
			}
			curPosInPairBuf++;
		}
	}
	
	private void fillTopItems() {
		for (Integer num : mMap_Top.keySet()) {
			mMap_Bottom.put(num, mMap_Top.get(num));
		}
		mMap_Top.clear();
		for (int i = 0; i < mK; i++) {
			int num = mPairBuf[i].mNum;
			mMap_Bottom.remove(num);
			mMap_Top.put(num, mPairBuf[i].mFreq);
		}
	}
	
	private void showResult(Map<Integer, Long> result) {
		System.out.print("RESULT: ");
    	for (Integer num : result.keySet()) {
    		System.out.print(" [" + num + ", " + result.get(num) + "]");
    	}
		System.out.println("");
	}
}
