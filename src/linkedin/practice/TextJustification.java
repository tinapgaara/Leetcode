package linkedin.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 11/22/17.
 */
public class TextJustification {

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        if (words == null) return res;
        int index = 0;
        while(index < words.length) {
            int nextWordToPutIndex = index + 1;
            int sentenceLen = words[index].length();
            while(nextWordToPutIndex < words.length) {
                String nextWord = words[nextWordToPutIndex];
                if (sentenceLen + 1 + nextWord.length() > maxWidth) break;
                sentenceLen = sentenceLen + 1 + nextWord.length();
                nextWordToPutIndex ++;
            }
            int countWordsInThisLine = nextWordToPutIndex - index;
            if (nextWordToPutIndex == words.length || countWordsInThisLine == 1) {
                // left-aligned
                StringBuilder b = new StringBuilder();
                for (int i = index ; i < nextWordToPutIndex; i ++) {
                    b.append(words[i] + " ");
                }
                b.deleteCharAt(b.length() - 1);
                for (int i = b.length(); i < maxWidth; i ++) {
                    b.append(" ");
                }
                res.add(b.toString());
            }
            else {
                // middle
                StringBuilder b = new StringBuilder();
                int spacesEvenlyDistributed = (maxWidth - sentenceLen) / (countWordsInThisLine - 1);
                int leftSpace = (maxWidth - sentenceLen) % (countWordsInThisLine - 1);
                for (int i = index; i < nextWordToPutIndex; i ++) {
                    b.append(words[i]);
                    if (i < nextWordToPutIndex - 1) {
                        // then spaces
                        int addedSpace = 0;
                        if (i - index < leftSpace) {
                            addedSpace = spacesEvenlyDistributed + 1;
                        }
                        else {
                            addedSpace = spacesEvenlyDistributed;
                        }
                        for (int j = 0 ; j <= addedSpace; j ++) {
                            b.append(" ");
                        }
                    }

                }
                res.add(b.toString());
            }
            index = nextWordToPutIndex;
        }
        return res;
    }
}
