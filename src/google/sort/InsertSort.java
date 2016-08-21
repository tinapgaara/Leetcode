package google.sort;

/**
 * Created by yingtan on 11/16/15.
 */
public class InsertSort {

    public void insertSort(int[] nums) {
        for (int i = 1; i < nums.length; i ++) {
            int compareNumIndex = i;
            for (int j = i-1; j>= 0; j --) {
                if (nums[j] > nums[compareNumIndex]) {
                    int tmp = nums[j];
                    nums[j] = nums[compareNumIndex];
                    nums[compareNumIndex] = tmp;

                    compareNumIndex = j;
                }
                else break;
            }
        }
    }

    public int[] insertSort_2(int[] nums) {
        int[] res = new int[nums.length];

        res[0] = nums[0];
        int curLen = 1;
        for (int i = 1; i < nums.length ; i ++) {
            int insertNum = nums[i];
            boolean ifInsertHead = true;
            for (int j = curLen-1; j >= 0; j --) {
                if ((res[j] > insertNum)) {
                    res[j+1] = res[j]; // out of bound
                    if ((j-1 >= 0) && (res[j-1] < insertNum)) {
                        res[j] = insertNum;
                        curLen ++;
                        ifInsertHead = false;
                        break;
                    }
                }
                else if (res[j]< insertNum) {
                    res[j+1] = insertNum;
                    curLen ++;
                    ifInsertHead = false;
                    break;
                }
            }
            if (ifInsertHead) {
                res[0] = insertNum;
                curLen ++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        InsertSort ob = new InsertSort();
        int[] nums = new int[]{100,0,4,2,8,19,7,4,2,0,9,7};
        ob.insertSort(nums);
        System.out.println("");

    }
}
