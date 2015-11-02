package google.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 11/1/15.
 */
public class DecodeEncodeStr {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String str: strs) {
            sb.append(str.length()).append("/").append(str);
        }
        return sb.toString();

    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> res = new ArrayList<>();
        int low = 0;
        while (low < s.length()) {
            int high = s.indexOf('/', low);
            if (high == -1) break;
            int size = Integer.parseInt(s.substring(low, high)); // Attention !!!
            String str = s.substring(high + 1, high + 1 + size);
            low = high + size + 1;

            res.add(str);
        }
        return res;
    }
}
