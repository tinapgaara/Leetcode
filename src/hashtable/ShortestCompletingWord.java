package hashtable;

/**
 * Created by yingtan on 3/4/18.
 *
 * 748. Shortest Completing Word
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Find the minimum length word from a given dictionary words, which has all the letters from the string licensePlate. Such a word is said to complete the given string licensePlate

 Here, for letters we ignore case. For example, "P" on the licensePlate still matches "p" on the word.

 It is guaranteed an answer exists. If there are multiple answers, return the one that occurs first in the array.

 The license plate might have the same letter occurring multiple times. For example, given a licensePlate of "PP", the word "pair" does not complete the licensePlate, but the word "supper" does.

 Example 1:
 Input: licensePlate = "1s3 PSt", words = ["step", "steps", "stripe", "stepple"]
 Output: "steps"
 Explanation: The smallest length word that contains the letters "S", "P", "S", and "T".
 Note that the answer is not "step", because the letter "s" must occur in the word twice.
 Also note that we ignored case for the purposes of comparing whether a letter exists in the word.
 Example 2:
 Input: licensePlate = "1s3 456", words = ["looks", "pest", "stew", "show"]
 Output: "pest"
 Explanation: There are 3 smallest length words that contains the letters "s".
 We return the one that occurred first.
 */

public class ShortestCompletingWord {

    public String shortestCompletingWord(String licensePlate, String[] words) {
        if (licensePlate == null || licensePlate.length() == 0 || words == null || words.length == 0) {
            return "";
        }
        int[] count = new int[26];
        for (char ch : licensePlate.toCharArray()) {
            if (Character.isLetter(ch)) {
                char lowCh = Character.toLowerCase(ch);
                count[lowCh - 'a'] ++;
            }
        }
        String res = "";
        int min = Integer.MAX_VALUE;
        for (String word : words) {
            int[] wordCount = new int[26];
            for (char ch : word.toCharArray()) {
                char lowch = Character.toLowerCase(ch);
                wordCount[lowch - 'a'] ++;
            }
            boolean isSame = true;
            for (int i = 0 ;i  < 26; i ++) {
                //Important !!!! this line
                if (count[i] != 0 && count[i] > wordCount[i]) {
                    isSame = false;
                    break;
                }
            }
            if (isSame) {
                if (word.length() < min) {
                    min = word.length();
                    res = word;
                }
            }
        }
        return res;
    }
    public static void main(String[] args) {
        ShortestCompletingWord ob = new ShortestCompletingWord();
        String[] strs = {"measure","other","every","base","according","level","meeting","none","marriage","rest"};
        ob.shortestCompletingWord("GrC8950", strs);
    }
}
