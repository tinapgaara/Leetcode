package facebook.design;

/**
 * Created by yingtan on 1/6/18.
 *
 * 535. Encode and Decode TinyURL
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Note: This is a companion problem to the System Design problem: Design TinyURL.
 TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk.

 Design the encode and decode methods for the TinyURL service. There is no restriction on how your encode/decode algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.


 */
import java.util.*;
public class EncodeDecodeTinyURL {

    Map<String, String> keyToUrl = new HashMap<>();
    int i = 0;
    String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    Random rnd = new Random();
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        // Sol 1: using counter. easy to predict, can encode #sizeOfInteger different urls. encoded length is longer than orig url
        /*
        String key = i + "";
        keyToUrl.put(key, longUrl);
        i ++;
        */
        // Sol 2: using random number with fixed length 6 key
        String key = genRandKey();
        while (keyToUrl.containsKey(key)) {
            key = genRandKey();
        }
        keyToUrl.put(key, longUrl);
        return key;
    }
    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return keyToUrl.get(shortUrl);
    }
    public String genRandKey() {
        String key = "";
        for (int i = 0 ; i < 6; i ++) { // choose 6 chs randomly from alphabet(62)
            key = key + alphabet.charAt(rnd.nextInt(62));

        }
        return key;
    }
}
