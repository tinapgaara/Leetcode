package google.design.topk;


public class TopK_Stream {

	private int[] mBuf;
	private int mK, mCount;
	private QuickSelector mQuickSelector;
	
    public static void main(String[] args) {
    	
    	int K = 5;
    	TopK_Stream obj = new TopK_Stream(K);
    	
    	int[] arr = new int[] {303, 505, 10, 808, 7, 20, 30, 101, 1, 202, 5, 3, 606, 707, 4, 8, 909};
    	int batchSize = 3;
    	
		int length = arr.length;
		int cBatches = (int) Math.ceil( (double) length / batchSize);
    	int curPosInArr = 0;
    	for (int batchNo = 0; batchNo < cBatches; batchNo++) {
    		int num = obj.findTopK_Stream(arr, 
        			curPosInArr, Math.min(length - 1, curPosInArr + batchSize - 1));
    		int[] result = obj.getResult();
    		showResult(result, num);
        	
        	curPosInArr += batchSize;
    	}
    }
    
	private static void showResult(int[] result, int length) {
		System.out.print("RESULT: ");
    	for (int i = 0; i < length; i++) {
    		System.out.print(" " + result[i]);
    	}
		System.out.println("");
	}
	
    
	public TopK_Stream(int K) {
		mK = K;
		mBuf = new int[2 * K];
		mCount = 0;
		mQuickSelector = new QuickSelector();
	}
	
	public int[] getResult() {
		return mBuf;
	}
	
	public int findTopK_Stream(int[] arr, int left, int right) {
		
		int cNums = 0;
		
		int cNewNums = 0;
		for (int i = left; i <= right; i++) {
			if (mCount < mK) {
				mBuf[mCount] = arr[i];
				mCount++;
				cNums++;
			}
			else {
				cNums = mK;
				mBuf[mK + cNewNums] = arr[i];
				cNewNums++;
				if (cNewNums >= mK) {
					mQuickSelector.selectKthLargest(mBuf, 0, 2 * mK - 1, mK - 1);
					cNewNums = 0;
				}
			}
		}
		
		if (cNewNums > 0) {
			mQuickSelector.selectKthLargest(mBuf, 0, mK + cNewNums - 1, mK - 1);
		}
		
		return cNums;
	}

}
