package facebook.twoPointer;

/**
 * Created by yingtan on 5/20/17.
 */
public class SortColors {

    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int red = 0;
        int blue = nums.length - 1;
        int white = 0;
        // p1 +   p2 +    ........ + p3
        //       red   white  blue
        while(white <= blue) {// must use  <= important !!!!
            if (nums[white] == 0) {
                // red
                int tmp = nums[white];
                nums[white] = nums[red];
                nums[red] = tmp;
                red ++;
                white ++;
            }
            else if (nums[white] == 2) {
                // blue
                int tmp = nums[blue];
                nums[blue] = nums[white];
                nums[white] = tmp;
                blue --;
            }
            else {
                // white
                white ++;
            }
        }
    }

    public static void main(String[] args) {
        SortColors ob = new SortColors();
        int[] nums = new int[]{1,1,0,2,1,2,2,1};
        ob.sortColors(nums);
    }
}
