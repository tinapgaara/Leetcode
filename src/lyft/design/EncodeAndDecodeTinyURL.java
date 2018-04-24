package lyft.design;

/**
 * Created by yingtan on 4/21/18.
 *
 * 535. Encode and Decode TinyURL
 DescriptionHintsSubmissionsDiscussSolution
 Note: This is a companion problem to the System Design problem: Design TinyURL.
 TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk.

 Design the encode and decode methods for the TinyURL service. There is no restriction on how your encode/decode algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.


 */
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class EncodeAndDecodeTinyURL {
    Random rnd = new Random();
    // Need to use concurrent hashmap
    ConcurrentHashMap<String, String> shortToLongUrl = new ConcurrentHashMap<>();
    String digits = "0123456789abcdefghijklmnopqrstuawxyzABCDEFGHIGKLMNOPQRSTUVWXYZ";

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        // to a fixed 6-digits short url
        while(true) {
            String key = genRandkey();
            // atomic action. If shortToLongUrl does not contain this key, put to it.
            // else, return oldurl, then, keep doing it
            String prevVal = shortToLongUrl.putIfAbsent(key, longUrl);
            if (prevVal == null) { // this key not exist before
                return key;
            }
        }
    }
    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return shortToLongUrl.get(shortUrl);
    }
    public String genRandkey() {
        String key = "";
        for (int i = 0 ;i < 6; i ++) {
            int rndIndex = rnd.nextInt(62);
            char ch = digits.charAt(rndIndex);
            key = key + ch;
        }
        return key;
    }

    public static void main(String[] args) {
        EncodeAndDecodeTinyURL ob = new EncodeAndDecodeTinyURL();
        System.out.println(ob.encode("hello"));
        System.out.println(ob.encode("hello"));
        System.out.println(ob.encode("hello"));
    }
}
