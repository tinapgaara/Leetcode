package google.array.jiuzhang;

/**
 * Created by yingtan on 11/3/17.
 *
 * Sort Colors II

 Description
 Notes
 Testcase
 Judge
 Given an array of n objects with k different colors (numbered from 1 to k), sort them so that objects of the same color are adjacent, with the colors in the order 1, 2, ... k.

 Notice

 You are not suppose to use the library's sort function for this problem.

 k <= n

 Have you met this question in a real interview? Yes
 Example
 Given colors=[3, 2, 2, 1, 4], k=4, your code should sort colors in-place to [1, 2, 2, 3, 4].
 */
public class SortColorII {

    public void sortColors2(int[] colors, int k) {
        // write your code here
        if (colors == null || colors.length == 0) {
            return;
        }

        rainbowSort(colors, 0, colors.length - 1, 1, k);
    }
    public void rainbowSort(int[] colors, int low, int high, int colorFrom, int colorTo) {
        // base case
        if (colorFrom == colorTo) {
            return;
        }
        if (low >= high) {
            return;
        }

        // firstly sort 1 - 2/k, 2/k = k
        // and then, 1-1/4k, 1/4k - 2/4k ......
        int i = low;
        int j = high;
        int pivot = colorFrom + (colorTo - colorFrom) / 2;
        //int pivot = colors[mid];

        while(i <= j) {
            while(i <= j && colors[i] <= pivot) {
                i ++;
            }
            while(i <= j && colors[j] > pivot) {
                j --;
            }
            if (i <= j) {
                int tmp = colors[i];
                colors[i] = colors[j];
                colors[j] = tmp;
                i ++;
                j --;
            }
        }
        // sort left
        rainbowSort(colors, low, j, colorFrom, pivot);
        // sort right
        rainbowSort(colors, i, high, pivot + 1, colorTo);
    }
}
