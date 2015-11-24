package google.design.topk;

public class QuickSelector {

        public static void main(String[] args) {

            QuickSelector obj = new QuickSelector();

            int[] arr = new int[]{303, 505, 10, 8, 7, 20, 30, 101, 1, 202};
            int K = 5;

            int result = obj.selectKthLargest(arr, 0, arr.length - 1, K - 1);
            System.out.println("" + K + "-th Largest = [" + result + "]");
            for (int i = 0; i < K; i++) {
                System.out.print(" " + arr[i]);
            }
        }

        // Returns the K-th largest element of list within left..right inclusive
        // (i.e. left <= n <= right).
        // The search space within the array is changing for each round - but the list
        // is still the same size. Thus, K does not need to be updated with each round.
        public int selectKthLargest(int[] arr, int left, int right, int K) {

            if (left == right) {        // If the list contains only one element,
                return arr[left];        // return that element
            }

            int pivotIndex = calcPivotIndex(left, right);    // select a pivotIndex between left and right
            pivotIndex = partition(arr, left, right, pivotIndex);
            // The pivot is in its final sorted position
            if (K == pivotIndex)
                return arr[K];
            else if (K < pivotIndex)
                return selectKthLargest(arr, left, pivotIndex - 1, K);
            else
                return selectKthLargest(arr, pivotIndex + 1, right, K);
        }

        private int partition(int[] arr, int left, int right, int pivotIndex) {

            int pivotValue = arr[pivotIndex];
            swap(arr, pivotIndex, right);  // Move pivot to end
            int storeIndex = left;
            for (int i = left; i < right; i++) {
                if (arr[i] > pivotValue) {
                    swap(arr, storeIndex, i);
                    storeIndex++;
                }
            }
            swap(arr, right, storeIndex);  // Move pivot to its final place

            return storeIndex;
        }

        public Pair selectKthLargest_Freq(Pair[] arr, int left, int right, int K) {

            if (left == right) {        // If the list contains only one element,
                return arr[left];        // return that element
            }

            int pivotIndex = calcPivotIndex(left, right);    // select a pivotIndex between left and right
            pivotIndex = partition_Freq(arr, left, right, pivotIndex);
            // The pivot is in its final sorted position
            if (K == pivotIndex)
                return arr[K];
            else if (K < pivotIndex)
                return selectKthLargest_Freq(arr, left, pivotIndex - 1, K);
            else
                return selectKthLargest_Freq(arr, pivotIndex + 1, right, K);
        }

        private int partition_Freq(Pair[] arr, int left, int right, int pivotIndex) {

            long pivotValue = arr[pivotIndex].mFreq;
            swap_Freq(arr, pivotIndex, right);  // Move pivot to end
            int storeIndex = left;
            for (int i = left; i < right; i++) {
                if (arr[i].mFreq > pivotValue) {
                    swap_Freq(arr, storeIndex, i);
                    storeIndex++;
                }
            }
            swap_Freq(arr, right, storeIndex);  // Move pivot to its final place

            return storeIndex;
        }

        // select a pivotIndex between left and right, e.g., left + floor(rand() * (right - left + 1))
        private int calcPivotIndex(int left, int right) {

            return left + (int) Math.floor((Math.random() * (right - left + 1)));
        }

        private void swap(int[] arr, int index1, int index2) {
            int temp = arr[index1];
            arr[index1] = arr[index2];
            arr[index2] = temp;
        }

        private void swap_Freq(Pair[] arr, int index1, int index2) {
            int tempNum = arr[index1].mNum;
            long tempFreq = arr[index1].mFreq;
            arr[index1].mNum = arr[index2].mNum;
            arr[index1].mFreq = arr[index2].mFreq;
            arr[index2].mNum = tempNum;
            arr[index2].mFreq = tempFreq;
        }
}
