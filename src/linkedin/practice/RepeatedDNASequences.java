package linkedin.practice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by yingtan on 11/22/17.
 */
public class RepeatedDNASequences {

    public List<String> findRepeatedDnaSequences(String s) {
        HashSet<String> vis = new HashSet<>();
        HashSet<String> res = new HashSet<>();
        for (int i = 0 ; i < s.length() - 10; i ++) {
            String substr = s.substring(i, i + 10);
            if (vis.contains(substr)) {
                res.add(substr);
            }
            else {
                vis.add(substr);
            }
        }
        return new ArrayList(res);

        // TODO: change to use bits
    }
}
