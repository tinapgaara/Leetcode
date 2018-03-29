package sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 10/9/17.
 *
 * At the start of the second pass, the largest value is now in place. There are n−1n−1 items left to sort, meaning that there will be
 */
public class BubbleSort {

    void bubbleSort(int arr[])
    {
        int n = arr.length;
        // change this loop to k times, then could find out top k
        for (int i = 0; i < n-1; i++)
            // each time put max value to the right place
            for (int j = 0; j < n-i-1; j++)
                if (arr[j] > arr[j+1])
                {
                    // swap temp and arr[i]
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
    }
}
