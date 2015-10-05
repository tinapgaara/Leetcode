package substring;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 9/28/15.
 */
public class EncodeDecodeStr {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i < strs.size() ; i ++) {
            sb.append(strs.get(i).length()).append("/").append(strs.get(i));// record word's length + "/' + word
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> res = new ArrayList<String>();
        int i = 0;
        while(i < s.length()) {
            int index = s.indexOf('/', i);

            int len = Integer.parseInt(s.substring(i, index));
            String str = s.substring(index+1, index + 1 + len);
            res.add(str);
            i = index + 1 + len;
        }
        return res;
    }
}
