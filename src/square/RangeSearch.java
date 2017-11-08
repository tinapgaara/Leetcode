package square;

/**
 * Created by yingtan on 10/15/17.
 *
 * Range search 实现两个接口，add添加value，search(lower, upper)，找到这个区间里的所有元素，并且按照顺序返回一个list
 Binary search:类似题34. Search for a Range
 */
public class RangeSearch {
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        res[0] = -1;
        res[1] = -1;
        if (nums == null || nums.length == 0) return res;
        int lowerBoundary = findFirstEquals(nums, target);
        int higherBoundary = findLastEquals(nums, target);

        res[0] = lowerBoundary;
        res[1] = higherBoundary;
        return res;
    }
    // ologn because of sorted array
    // if we need to find the element which is equals to target, we must use, (0, len-1), while(low<=high), and use equals to judge
    //  find the first element which is equals to the target
    private int findFirstEquals(int[] nums, int target){
        int start = 0;
        int end = nums.length - 1;
        while(start < end - 1) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                end = mid;
            }
            else if (nums[mid] > target) {
                end = mid - 1;
            }
            else {
                start = mid + 1;
            }
        }

        if (nums[start] == target) return start;
        else if (nums[end] == target) return end;
        return -1;
    }

    //  find the last element which is equals to the target
    private int findLastEquals(int[] nums, int target){
        int start = 0;
        int end = nums.length - 1;
        while(start < end - 1) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                start = mid;
            }
            else if (nums[mid] > target) {
                end = mid - 1;
            }
            else {
                start = mid + 1;
            }
        }

        if (nums[end] == target) return end;
        else if (nums[start] == target) return start;
        return -1;
    }

    public static void main(String[] args) {
        RangeSearch ob = new RangeSearch();
        System.out.println(ob.findFirstEquals(new int[]{6, 6, 6, 7}, 7));
    }
}
