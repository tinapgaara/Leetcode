package google.bit;

/**
 * Created by yingtan on 8/6/17.
 *
 * 318. Maximum Product of Word Lengths
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. You may assume that each word will contain only lower case letters. If no such two words exist, return 0.

 Example 1:
 Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
 Return 16
 The two words can be "abcw", "xtfn".

 Example 2:
 Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
 Return 4
 The two words can be "ab", "cd".

 Example 3:
 Given ["a", "aa", "aaa", "aaaa"]
 Return 0
 No such pair of words.
 */
public class MaxProductOfWordLength {

    public int maxProduct(String[] words) {
        int n = words.length;
        // represent each word as an integer(32 bits), because each word is constructed of 26 chars, such as, if it contains a, then, the 0th bit is 1 which means a appears in this word.
        int[] elements = new int[n];
        for (int i = 0 ; i < n ; i++){
            String word1 = words[i];

            for(int j = 0;j < word1.length(); j++){
                char ch = word1.charAt(j);
                int transformedInteger = 1 << (ch - 'a');

                // using | instead of + because the case: [a, aa, aaa]
                //
                // such as : e d c b a
                elements[i] = elements[i] | transformedInteger;
            }
        }
        int max = 0;
        for (int i = 0 ; i < n ; i ++) {
            for (int j = i + 1 ; j < n ; j ++) {
                // if (elements[i] & elements[j] == 0) this means, each bit is 1&0, which means no same char
                if ((elements[i] & elements[j]) == 0) {
                    // no common
                    max = Math.max(max, words[i].length() * words[j].length());
                }
            }
        }
        return max;
    }
}
