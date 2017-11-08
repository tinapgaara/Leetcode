package google.backtrack;

/**
 * Created by yingtan on 1/16/17.
 *
 *418. Sentence Screen Fitting   Add to List QuestionEditorial Solution  My Submissions
 Total Accepted: 6999
 Total Submissions: 25509
 Difficulty: Medium
 Contributors: Admin
 Given a rows x cols screen and a sentence represented by a list of non-empty words, find how many times the given sentence can be fitted on the screen.

 Note:

 A word cannot be split into two lines.
 The order of words in the sentence must remain unchanged.
 Two consecutive words in a line must be separated by a single space.
 Total words in the sentence won't exceed 100.
 Length of each word is greater than 0 and won't exceed 10.
 1 ≤ rows, cols ≤ 20,000.
 Example 1:

 Input:
 rows = 2, cols = 8, sentence = ["hello", "world"]

 Output:
 1

 Explanation:
 hello---
 world---

 The character '-' signifies an empty space on the screen.
 Example 2:

 Input:
 rows = 3, cols = 6, sentence = ["a", "bcd", "e"]

 Output:
 2

 Explanation:
 a-bcd-
 e-a---
 bcd-e-

 The character '-' signifies an empty space on the screen.
 Example 3:

 Input:
 rows = 4, cols = 5, sentence = ["I", "had", "apple", "pie"]

 Output:
 1

 Explanation:
 I-had
 apple
 pie-I
 had--

 The character '-' signifies an empty space on the screen.
 *
 *
 *
 * 这道题给我们了一个句子，由若干个单词组成，然后给我们了一个空白屏幕区域，让我们填充单词，
 * 前提是单词和单词之间需要一个空格隔开，而且单词不能断开，如果当前行剩余位置放下不下某个单词，则必须将该单词整个移动到下一行。
 * 我刚开始想的是便利句子，每个单词分别处理，但是这种做法很不高效，因为有可能屏幕的宽度特别大，而单词可能就一两个，
 * 那么我们这样遍历的话就太浪费时间了，应该直接用宽度除以句子加上空格的长度之和，可以快速的得到能装下的个数。
 * 下面这种方法设计的很巧妙，
 * 思路是用start变量来记录下能装下的句子的总长度，最后除以一个句子的长度，就可以得到个数。
 * 而句子的总长度的求法时要在每个单词后面加上一个空格(包括最后一个单词)，我们遍历屏幕的每一行，然后每次start都加上宽度，
 * 然后看all[start%len]是否为空格，是的话就start加1，这样做的好处是可以处理末尾是没有空格的情况，
 * 比如宽度为1，只有一个单词a，那么我们都知道是这样放的 a ，start变为1，len是2，all[start%len]是空格，所以start自增1，变成2，
 * 这样我们用start/len就知道能放下几个了。对于all[start%len]不为空格的情况，如果all[(start-1)%len]也不为空格，那么start就自减1，
 * 进行while循环，直至其为空格为止。大家可以自己带例子尝试，个人觉得想出此方法的人真是太聪明了：
 */
public class SentenceScreenFitting {

    public int wordsTyping(String[] sentence, int rows, int cols) {
        //该直接用宽度除以句子加上空格的长度之和，可以快速的得到能装下的个数
        if ((sentence == null) || (sentence.length == 0)) return 0;

        String concatAllWordsAddSpace = "";
        for (int i = 0 ; i < sentence.length; i ++) {
            concatAllWordsAddSpace = concatAllWordsAddSpace + sentence[i] + " ";
        }

        int index = 0;
        for (int i = 0 ; i < rows; i ++) {
            index = index  + cols;
            int spacePos = index % concatAllWordsAddSpace.length();
            if (concatAllWordsAddSpace.charAt(spacePos) == ' ') {
                // subsentence in concatAllWordsAddSpace can be fitted to this row without space ending

                // next loop, fit the rest of sentences
                index ++;
            }
            else {
                // After fitting this subsentence, there are still some extra space left in this row
                // trace back to find out how many cells in this row required to fit a subsentence

                // !!!!! important : (index - 1) % concatAllWordsAddSpace.len
                while ( (index > 0) &&
                        (concatAllWordsAddSpace.charAt( (index - 1) % concatAllWordsAddSpace.length()) != ' ') ) {
                    index --;
                }
            }
        }
        return index / concatAllWordsAddSpace.length();
    }
}
