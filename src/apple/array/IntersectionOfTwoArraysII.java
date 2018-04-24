package apple.array;

/**
 * Created by yingtan on 2/10/18.
 * 350. Intersection of Two Arrays II
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given two arrays, write a function to compute their intersection.

 Example:
 Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].

 Note:
 Each element in the result should appear as many times as it shows in both arrays.
 The result can be in any order.
 Follow up:
 What if the given array is already sorted? How would you optimize your algorithm?
 What if nums1's size is small compared to nums2's size? Which algorithm is better?
 What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 */

import java.util.*;

public class IntersectionOfTwoArraysII {

    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> num1Count = new HashMap<>();
        List<Integer> reslist = new ArrayList<>();
        for (int num : nums1) {
            if (num1Count.containsKey(num)) {
                num1Count.put(num, num1Count.get(num) + 1);
            }
            else {
                num1Count.put(num, 1);
            }
        }
        for (int num : nums2) {
            if (num1Count.containsKey(num) && num1Count.get(num) > 0) {
                // intersection
                reslist.add(num);
                num1Count.put(num, num1Count.get(num) - 1);
            }
        }
        int[] res = new int[reslist.size()];
        for (int i = 0 ; i < reslist.size(); i ++) {
            res[i] = reslist.get(i);
        }
        return res;
    }

    //What if the given array is already sorted? How would you optimize your algorithm? Two pointer
    public int[] intersect2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int pnt1 = 0;
        int pnt2 = 0;
        ArrayList<Integer> myList = new ArrayList<Integer>();
        while((pnt1 < nums1.length) &&(pnt2< nums2.length)){
            if(nums1[pnt1]<nums2[pnt2]){
                pnt1++;
            }
            else{
                if(nums1[pnt1]>nums2[pnt2]){
                    pnt2++;
                }
                else{
                    myList.add(nums1[pnt1]);
                    pnt1++;
                    pnt2++;
                }
            }
        }
        int[] res = new int[myList.size()];
        for(int i = 0; i<res.length; i++){
            res[i] = (Integer)myList.get(i);
        }
        return res;
    }
    // What if nums1's size is small compared to nums2's size? Which algorithm is better?
    // sorted num2, for each number in num1, find same one in num2: binary search : onlogn

    //What if elements of nums2 are stored on disk, a
    // nd the memory is limited such that you cannot load all elements into the memory at once?
    // load num1 into memory, search num2
}
