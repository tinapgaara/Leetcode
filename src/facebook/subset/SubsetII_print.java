package facebook.subset;
import java.util.*;
public class SubsetII_print {
    public void subsets(int[] nums) {
        if (nums == null || nums.length == 0) return;
        List<Integer> path = new ArrayList<Integer>();
        System.out.println(path);
        printsubset(nums, 0, path);
    }
    public void printsubset(int[] nums, int index, List<Integer> path) {
        if (index >= nums.length) {
            return;
        }
        for (int i = index; i < nums.length; i ++) {
            if (i > index && nums[i] == nums[i-1]) continue;
            path.add(nums[i]);
            // print
            System.out.println(path);
            printsubset(nums, index + 1, path);
            path.remove(path.size() - 1);
        }
    }
    public static void main(String[] args) {
        SubsetII_print ob = new SubsetII_print();
        ob.subsets(new int[]{1,2,3});
    }
}
