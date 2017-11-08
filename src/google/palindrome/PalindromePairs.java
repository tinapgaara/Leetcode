package google.palindrome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yingtan on 8/20/17.
 *
 * https://discuss.leetcode.com/topic/40657/150-ms-45-lines-java-solution
 *
 * 336. Palindrome Pairs
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

 Example 1:
 Given words = ["bat", "tab", "cat"]
 Return [[0, 1], [1, 0]]
 The palindromes are ["battab", "tabbat"]
 Example 2:
 Given words = ["abcd", "dcba", "lls", "s", "sssll"]
 Return [[0, 1], [1, 0], [3, 2], [2, 4]]
 The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]

 */
public class PalindromePairs {

    public List<List<Integer>> palindromePairs(String[] words) {
        // word1 can cancate with word1' and become a palindrom:
        // word1.reverse() = word1'

        // for each word: split it to two parts
        // word = word1 + word2
        // if (isPalindrome(word1) && hasReverseInArray(word2)) then this is a good candidate
        // new palindrom:
        // reverse(word2) + word1 + word2

        // hasReverseInArray(word2):
        // map.containsKey(word2.reverse()) == true   && map.get(word2.reverse()) != word's index
        List<List<Integer>> res = new ArrayList<>();
        if (words == null || words.length < 2) return res;

        Map<String, Integer> map = new HashMap<String, Integer>();
        // record each word and its index
        for (int i=0; i<words.length; i++)
            map.put(words[i], i);

        for (int i = 0; i < words.length; i ++) {
            for (int j = 0; j <= words[i].length(); j ++) {
                String word1 = words[i].substring(0, j);
                String word2 = words[i].substring(j);
                if (isPalindrome(word1)) {
                    String word2Reverse = new StringBuilder(word2).reverse().toString();

                    if (map.containsKey(word2Reverse) && map.get(word2Reverse) != i) {
                        List<Integer> list = new ArrayList<Integer>();
                        //System.out.println(word1 + "1," + i + "," + map.get(word2Reverse));
                        list.add(map.get(word2Reverse));
                        list.add(i);

                        res.add(list);
                    }
                }

                if(isPalindrome(word2)) {
                    String word1Reverse = new StringBuilder(word1).reverse().toString();
                    // not duplicate with the first case
                    if (map.containsKey(word1Reverse) && map.get(word1Reverse) != i && word2.length() != 0) {
                        List<Integer> list = new ArrayList<Integer>();
                        //System.out.println(word2 + "2," +  map.get(word1Reverse) + "," + i);

                        list.add(i);
                        list.add(map.get(word1Reverse));
                        res.add(list);
                    }
                }
            }
        }
        return res;
    }

    private boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;
        while (left <= right) {
            if (str.charAt(left++) !=  str.charAt(right--)) return false;
        }
        return true;
    }
}
