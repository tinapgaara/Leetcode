package apple.array;

public class CicularArrayLoop {
    public boolean circularArrayLoop(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int index = 0;
        while(index < nums.length && index >= 0) {
            int step = nums[index];
            if (step < 0) return false; // cna not use negative steps in loop
            int nextIndex = (index + nums[index]) % nums.length;
            if (nextIndex >= 0 && nums[nextIndex] == 0) return true;
            nums[index] = 0;
            index = nextIndex;
        }
        return false;
    }
}
