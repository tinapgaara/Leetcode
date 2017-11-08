package google.array;

/**
 * Created by yingtan on 11/5/17.
 *
 * 287. Find the Duplicate Number
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

 Note:
 You must not modify the array (assume the array is read only).
 You must use only constant, O(1) extra space.
 Your runtime complexity should be less than O(n2).
 There is only one duplicate number in the array, but it could be repeated more than once.

 */
public class FindDuplicateNumber {

    public int findDuplicate(int[] nums) {
        if (nums ==null || nums.length == 0) return 0;
        for (int i = 0 ; i < nums.length; i ++) {
            int index = nums[i] - 1;
            if (i == index) continue;
            while(nums[i] != i + 1) {
                int indexOfNum = nums[i] - 1;
                if (nums[i] == nums[indexOfNum]) return nums[i];
                int tmp = nums[indexOfNum];
                nums[indexOfNum] = nums[i];
                nums[i] = tmp;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        FindDuplicateNumber ob = new FindDuplicateNumber();
        System.out.println(ob.findDuplicate(new int[]{3, 2, 2, 2, 4}));
    }
}
