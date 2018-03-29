package linkedin.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 11/20/17.
 *
 * 68. Text Justification
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.

 You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.

 Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

 For the last line of text, it should be left justified and no extra space is inserted between words.

 For example,
 words: ["This", "is", "an", "example", "of", "text", "justification."]
 L: 16.

 Return the formatted lines as:
 [
 "This    is    an",
 "example  of text",
 "justification.  "
 ]
 */
public class TextJustification {

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> lines = new ArrayList<>();
        int index = 0;
        while(index < words.length) {
            // keep adding words to one row
            int len = words[index].length();
            int sentenceLastWordIndex = index + 1;
            int sentenceLen = len;
            // put as much as words as possible in one line
            while(sentenceLastWordIndex < words.length) {
                String nextWord = words[sentenceLastWordIndex];
                // curLen + space + nextWord.length() > maxWidth
                if ((nextWord.length() + sentenceLen + 1) > maxWidth) {
                    break;
                }
                sentenceLen = sentenceLen + nextWord.length() + 1;
                sentenceLastWordIndex = sentenceLastWordIndex + 1;
            }

            StringBuilder builder  = new StringBuilder();
            int countOfWordsInLine = sentenceLastWordIndex - index;
            // already fit all sentences to this line || only contains one word in this line
            if (sentenceLastWordIndex == words.length || countOfWordsInLine == 1) {
                // adjust left-aligned
                for (int i = index; i < sentenceLastWordIndex; i ++) {
                    builder.append(words[i] + " ");
                }
                builder.deleteCharAt(builder.length() - 1);
                for (int i = builder.length(); i < maxWidth; i ++) {
                    builder.append(" ");
                }
            }
            else {
                // middle -distributed
                // firstly find out how many spaces left, then evenly distribute to the words
                builder  = new StringBuilder();
                int spaceDistributedEvenly = (maxWidth - sentenceLen) / (countOfWordsInLine - 1);
                int spaceLeft = (maxWidth - sentenceLen) % (countOfWordsInLine - 1);
                for (int i = index; i < sentenceLastWordIndex; i ++) {
                    builder.append(words[i]);
                    // if this is not the last word, distribute spaces
                    if (i < sentenceLastWordIndex - 1) {
                        int addedSpace = 0;
                        if (i - index < spaceLeft) {
                            addedSpace = spaceDistributedEvenly + 1;
                        }
                        else {
                            addedSpace = spaceDistributedEvenly;
                        }
                        for (int j = 0 ; j <= addedSpace; j ++) {
                            builder.append(" ");
                        }
                    }
                }
            }
            lines.add(builder.toString());
            index = sentenceLastWordIndex;
        }
        return lines;
    }

    public static void main(String[] args) {
        TextJustification ob = new TextJustification();
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        ob.fullJustify(words, 16);
    }
}
