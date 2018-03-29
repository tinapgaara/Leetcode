package linkedin.string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by yingtan on 11/20/17.
 * 187. Repeated DNA Sequences
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

 Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

 For example,

 Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

 Return:
 ["AAAAACCCCC", "CCCCCAAAAA"].
 */
public class RepeatedDNASequences {

    public List<String> findRepeatedDnaSequences(String s) {
        HashSet<String> saw = new HashSet<String>();
        HashSet<String> repeated = new HashSet<String>();
        for (int i = 0 ; i < s.length() - 9; i ++) {
            String str = s.substring(i, i+10);
            if (saw.contains(str)) {
                repeated.add(str);
            }
            else {
                saw.add(str);
            }
        }
        return new ArrayList(repeated);
    }
}
