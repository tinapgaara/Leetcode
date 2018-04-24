package amazon.dp;

/**
 * Created by yingtan on 3/29/18.
 */
import java.util.*;
public class SuperWashingMachines {
    public int findMinMoves(int[] machines) {
        if (machines == null || machines.length == 0) return 0;
        int sum = machines[0];
        String key = machines[0] + "";
        for (int i = 1; i < machines.length; i ++) {
            sum = sum + machines[i];
            key = key + "_" + machines[i];
        }
        if (sum % machines.length != 0) return -1;
        int avg = (sum / machines.length);
        int[] move = new int[machines.length];
        int max = 0;
        for (int i = 0 ; i < machines.length - 1; i ++) {
            if (machines[i] > avg) {
                move[i] = move[i] + machines[i] - avg; // important !!!! using move[i] here
                machines[i+1] = machines[i+1] + machines[i] - avg;
                machines[i] = avg;
                max = Math.max(max, move[i]);
            }
            else {
                move[i+1] = avg - machines[i];// important !!!!
                machines[i+1] = machines[i+1] - (avg - machines[i]);
                machines[i] = avg;
                max = Math.max(max, move[i+1]);
            }
        }
        return max;
        /*
        Map<String, Integer> dp = new HashMap<>();
        int[] start = new int[machines.length];
        Arrays.fill(start, avg);
        dp.put(key, 0);
        dfs(machines, start, 0, dp);
        return dp.get(encode(start));
        */
    }
    public int findMinMoves_overflow(int[] machines) {
        if (machines == null || machines.length == 0) return 0;
        int sum = machines[0];
        String key = machines[0] + "";
        for (int i = 1; i < machines.length; i ++) {
            sum = sum + machines[i];
            key = key + "_" + machines[i];
        }
        if (sum % machines.length != 0) return -1;
        int avg = (sum / machines.length);
        int[] move = new int[machines.length];
        int max = 0;
        for (int i = 0 ; i < machines.length - 1; i ++) {
            if (machines[i] > avg) {
                move[i] = machines[i] - avg;
                machines[i+1] = machines[i+1] + machines[i] - avg;
                machines[i] = avg;
                max = Math.max(max, move[i]);
            }
            else {
                move[i] = avg - machines[i];
                machines[i+1] = machines[i+1] - (avg - machines[i]);
                machines[i] = avg;
                max = Math.max(max, move[i]);
            }
        }
        return max;
        /*
        Map<String, Integer> dp = new HashMap<>();
        int[] start = new int[machines.length];
        Arrays.fill(start, avg);
        dp.put(key, 0);
        dfs(machines, start, 0, dp);
        return dp.get(encode(start));
        */
    }
    // Stack overflow
    public int dfs(int[] machines, int[] nums, int index, Map<String, Integer> dp) {
        String key = encode(nums);
        System.out.println(key);
        if (dp.containsKey(key)) {
            return dp.get(key);
        }
        if (index >= machines.length) {
            // not the key
            return -1;
        }
        int curMachine = nums[index];
        if (curMachine == machines[index]) {
            return dfs(machines, nums, index + 1, dp);
        }
        else if (curMachine > machines[index]) {
            // more, give one to others
            nums[index] = curMachine - 1;
            int min = Integer.MAX_VALUE;
            for (int j = index + 1; j < machines.length; j ++) {
                int num = nums[j];
                // add 1 to j
                nums[j] = num + 1;
                min = Math.min(min, dfs(machines, nums, index, dp));
                nums[j] = num;
            }
            dp.put(key, min + 1);
            nums[index] = curMachine;
        }
        else {
            // less, borrow from others
            nums[index] = curMachine + 1;
            int min = Integer.MAX_VALUE;
            for (int j = index + 1; j < machines.length; j ++) {
                int num = nums[j];
                if (num <= 0) continue;
                // add 1 to j
                nums[j] = num - 1;
                min = Math.min(min, dfs(machines, nums, index, dp));
                nums[j] = num;
            }
            dp.put(key, min + 1);
            nums[index] = curMachine;
        }
        return dp.get(key);
    }
    public String encode(int[] parts) {
        String res = parts[0] + "";
        for (int i = 1; i < parts.length; i ++) {
            res = res + "_" + parts[i];
        }
        return res;

    }
    public static void main(String[] args) {
        SuperWashingMachines ob = new SuperWashingMachines();
        int[] m = new int[]{1,0,5};
        //System.out.println(ob.findMinMoves(m));
        int[] m2 = new int[]{100000,0,100000,0,100000,0,100000,0,100000,0,100000,0};
        System.out.println(ob.findMinMoves(new int[]{0,3,0}));
    }
}
