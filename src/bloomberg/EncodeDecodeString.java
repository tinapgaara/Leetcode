package bloomberg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 10/26/15.
 */
/*
* Design an algorithm to encode a list of strings to a string.
*
* The encoded string is then sent over the network and is decoded back to the original list of strings.

Machine 1 (sender) has the function:

string encode(vector<string> strs) {
  // ... your code
  return encoded_string;
}
Machine 2 (receiver) has the function:
vector<string> decode(string s) {
  //... your code
  return strs;
}
So Machine 1 does:

string encoded_string = encode(strs);
and Machine 2 does:

vector<string> strs2 = decode(encoded_string);
strs2 in Machine 2 should be the same as strs in Machine 1.

Implement the encode and decode methods.
*
*
*
* Solution:
* each string:
*
* len + "/" + str
*
* just need to find index of "/", and use len to find the back part of str
* */
public class EncodeDecodeString {
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i < strs.size() ; i ++) {
            sb.append(strs.get(i).length()).append("/").append(strs.get(i));
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


// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));
}
