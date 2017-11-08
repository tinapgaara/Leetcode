package google.binarysearch;

/**
 * Created by yingtan on 1/29/17.
 */
public class SampleBinarySearch {

    // insert value to arr that generates an increasing array, return the inserted index
    // no recur
    public int binaryInsertIndex(int[] arr, int value) {
        int low = 0;
        // important !!
        int high = arr.length;
        // important !! only use < instead of <=
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] < value) {
                // insert righter
                low = mid + 1;
            }
            else {
                // important !!!
                high = mid;
            }
        }
        return high;
    }

    // no recur
    public int binarySearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;  //  important !!!
            if (key > arr[mid])
                low = mid + 1;
            else if (key < arr[mid]) {
                high = mid - 1;
            }
            else return mid;
        }

        return -1;
    }

    // recur
    public int recurBinarySearch(int[] arr, int low, int high, int key) {
        if (high < low) return -1; // important !!!C
        int mid = low + (high - low) / 2; // important !

        if (key > arr[mid]) return recurBinarySearch(arr, mid+1, high, key);
        else if (key < arr[mid]) return recurBinarySearch(arr, low, mid-1, key);
        else return -1;
    }
}
