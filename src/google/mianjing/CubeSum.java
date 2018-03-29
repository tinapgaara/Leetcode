package google.mianjing;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by erict on 2017/11/9.
 */
public class CubeSum {
    public class Pair {
        public int m_num1, m_num2;
        public Pair(int num1, int num2) {
            m_num1 = num1;
            m_num2 = num2;
        }
    }

    public List<Pair> findAllPairs(int n) {
        int limit = 0;//calcLimitNum(n);
        long[] arr = new long[limit];
        for (int i = 0; i < limit; i++) {
            long j = i + 1;
            arr[i] = j * j * j;
        }
        List<Pair> pairs = new ArrayList<>();
        find(pairs, arr, 0, limit - 1, n);
        return pairs;
    }

    public void find(List<Pair> pairs, long[] arr, int begin, int end, int target) {
        int left = begin, right = end;
        while (left < right) {
            if (arr[left] + arr[right] == target) {
                pairs.add(new Pair(left + 1, right + 1));
                left++;
                right--;
            }
            else if (arr[left] + arr[right] < target) {
                left++;
            }
            else {
                right--;
            }
        }
    }
}
