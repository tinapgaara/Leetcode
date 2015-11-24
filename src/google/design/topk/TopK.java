package google.design.topk;

public class TopK {

	private QuickSelector mQuickSelector;
	
    public static void main(String[] args) {
    	
    	TopK obj = new TopK();
    	
    	int[] arr = new int[] {303, 505, 10, 7, 7, 20, 30, 101, 1, 202};
    	int K = 8;
    	
    	int[] result = obj.findTopK(arr, K);
    	for (int i = 0; i < K; i++) {
    		System.out.print(" " + result[i]);
    	}
    }
    
	public TopK() {
		mQuickSelector = new QuickSelector();
	}
	
	public int[] findTopK(int[] arr, int K) {
		
		long length = arr.length;
		if (length <= K) {
			return arr;
		}
		
		int[] buf = new int[2 * K];
		
		for (int j = 0; j < K; j++) {
			buf[j] = arr[j];
		}
		int curPos_InBuf, curPos_InArr = K;
		
		long pass = (long) Math.ceil((double) length / K);
		for (int i = 1; i < pass; i++) {
			curPos_InBuf = K;
			for (int j = 0; j < K; j++) {
				buf[curPos_InBuf] = arr[curPos_InArr];
				curPos_InBuf++;
				curPos_InArr++;
				if (curPos_InArr >= length) break;
			}
			
			mQuickSelector.selectKthLargest(buf, 0, curPos_InBuf - 1, K - 1);
		}
		
		return buf;
	}
}
