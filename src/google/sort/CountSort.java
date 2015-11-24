package google.sort;

/**
 * Created by yingtan on 11/16/15.
 */
public class CountSort {

    public int[] countSort(int[] nums, int maxLen) {

        int[] counts = new int[maxLen+1];
        for (int i = 0 ; i < nums.length ; i ++) {
            int index = nums[i];
            counts[index] ++;
        }

        for (int i = 1; i < counts.length ; i ++) {
            counts[i] = counts[i] + counts[i-1];
        }

        int[] newNums = new int[nums.length];
        for (int i = nums.length-1; i >=0; i --) {
            int num = nums[i];
            int elements = counts[num];
            int putIndex = elements - 1;// Important !!!! must be elements-1
            newNums[putIndex] = num;

            counts[num] --;
        }
        return newNums;
    }

    public static void main(String[] args) {
        CountSort ob = new CountSort();
        int[] nums = new int[]{100,0,4,2,8,19,7,4,2, 0, 9, 7};
        nums = ob.countSort(nums, 100);
        System.out.println("");

    }
}
