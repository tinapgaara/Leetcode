package tree;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * Created by yingtan on 12/10/17.
 *
 * // need to repeated insert, delete and find min/max amongest k elements-> BST

 */
public class FindClosetIntervalInThreeSortedArray {

    public class MinNum {
        int arrayIndex;
        int index;
        public MinNum(int arrayIndex, int index) {
            this.arrayIndex = arrayIndex;
            this.index = index;
        }
    }
    public int[] closetInterval(int[] arr1, int[] arr2, int[] arr3) {
        int[] res = new int[3];
        if (arr1 == null || arr1.length == 0 || arr2 == null || arr2.length == 0 || arr3 == null || arr3.length == 0) {
            return res;
        }
        // initialization, make sure firstly the bst has 3 elements
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        Arrays.sort(arr3);
        TreeSet<Integer> bst = new TreeSet<>();
        bst.add(arr1[0]);
        bst.add(arr2[0]);
        bst.add(arr3[0]);
        int i1 = 0;
        int i2 = 0;
        int i3 = 0;
        int min = bst.last() - bst.first();
        MinNum prev = null;
        // o(nlogk)
        while(i1 < arr1.length && i2 < arr2.length && i3 < arr3.length) {
            MinNum num = calMinIndex(arr1, arr2, arr3, i1, i2, i3);
            // important here to avoid dead loop
            if (prev!= null && num.arrayIndex == prev.arrayIndex && num.index == prev.index) {
                // this is the last combination, break
                break;
            }
            prev = num;
            int arrayIndex = num.arrayIndex;
            // min is from arr1
            if (arrayIndex  == 1) {
                // this is not the last element
                if (num.index < arr1.length - 1) {
                    bst.remove(arr1[num.index]);
                    i1 ++;
                    bst.add(arr1[i1]);
                }
            }
            // min is from arr2
            else if (arrayIndex == 2) {
                // this is not the last element
                if (num.index < arr2.length - 1) {
                    bst.remove(arr2[num.index]);
                    i2 ++;
                    bst.add(arr2[i2]);
                }
            }
            // min is from arr3
            else if (arrayIndex == 3) {
                // this is not the last element
                if (num.index < arr3.length - 1) {
                    bst.remove(arr3[num.index]);
                    i3 ++;
                    bst.add(arr3[i3]);
                }
            }
            // cal the max difference after moving pointer
            int bstMin = bst.first();
            int bstMax = bst.last();
            if (bstMax - bstMin < min) {
                min = bstMax - bstMin;
                res[0] = i1;
                res[1] = i2;
                res[2] = i3;
            }

        }
        System.out.println("min interval is : arr1:" + arr1[res[0]] + ", arr2:" + arr2[res[1]] + ", arr3: " + arr3[res[2]]);
        return res;
    }

    public MinNum calMinIndex(int[] arr1, int[] arr2, int[] arr3, int i1, int i2, int i3) {
        int num1 = Integer.MAX_VALUE;
        int num2 = Integer.MAX_VALUE;
        int num3 = Integer.MAX_VALUE;
        if (i1 < arr1.length) {
            num1 = arr1[i1];
        }
        if (i2 < arr2.length) {
            num2 = arr2[i2];
        }
        if (i3 < arr3.length) {
            num3 = arr3[i3];
        }
        int min = Integer.min(num3,Integer.min(num1, num2));
        if (min == num1) {
            return new MinNum(1, i1);
        }
        else if (min == num2) {
            return new MinNum(2, i2);
        }
        else if (min == num3) {
            return new MinNum(3, i3);
        }
        return null;
    }

    public static void main(String[] args) {
        FindClosetIntervalInThreeSortedArray ob = new FindClosetIntervalInThreeSortedArray();
        int[] num1 = {5,10,15};
        int[] num2 = {3,6,9,12,15};
        int[] num3 = {8,16,24};
        ob.closetInterval(num1, num2, num3);
    }


}
