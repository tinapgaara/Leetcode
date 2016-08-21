package google.string;

import java.util.LinkedList;

/**
 * Created by yingtan on 12/21/15.
 */
/*
* Given a string which contains only lowercase letters,
* remove duplicate letters so that every letter appear once and only once.
* You must make sure your result is the smallest in lexicographical order among all possible results.

Example:
Given "bcabc"
Return "abc"

Given "cbacdcbc"
Return "acdb"
*
* */
public class RemoveDupLetters {

    public String removeDuplicateLetters(String s) {
        if ((s == null) || (s.length() == 0)) return "";
        LinkedList<Integer>[] indexs = new LinkedList[26];
        String res = "";
        int numDiffChs = 0;
        //  "cbacdcbc"
        /*
        * Forms:
        * a [2]
        * b [1, 6]
        * c: [0, 3, 5, 7]
        * d: [4]
        * */
        for (int i = 0 ; i < s.length(); i ++) {
            char ch = s.charAt(i);
            if (indexs[ch - 'a'] == null) {
                LinkedList<Integer> list = new LinkedList<Integer>();
                list.add(i);
                indexs[ch - 'a'] = list;
                numDiffChs ++;
            }
            else {
                indexs[ch - 'a'].add(i);
            }
        }

        // all chs before this index has been dealed
        int curMinIndex  = -1;
        // how many of chs we need to add to new string
        while (numDiffChs > 0) {
            // go through all candidate, find proper one
            for (int candidateIndex = 0; candidateIndex < indexs.length; candidateIndex ++) {
                LinkedList<Integer> candidate = indexs[candidateIndex];
                if (candidate == null) continue;
                // we need to make sure the candidate's first index should appear after current scaned index: curMinIndex
                while ((candidate.size() > 0) && (candidate.peekFirst() < curMinIndex)) {
                    candidate.pollFirst();
                }

                // flag to show if this is a proper candidate
                boolean isGoodCandidate = true;
                // a proper candidate need to make sure that all largerCandidate should be put after them
                for (int largerCandidateIndex = candidateIndex + 1;
                     largerCandidateIndex< indexs.length; largerCandidateIndex ++) {
                    LinkedList<Integer> largerCandidate = indexs[largerCandidateIndex];

                    // this larger candidate can not be put after candidate, so we say that candidate is not proper
                    if ((largerCandidate != null) && (largerCandidate.peekLast() < candidate.peekFirst())) {
                        isGoodCandidate = false;
                        break;
                    }
                }
                if (!isGoodCandidate) continue;

                // this candidate is proper, and can add to result
                curMinIndex = candidate.peekFirst();
                res = res + (char)(candidateIndex + 'a');
                indexs[candidateIndex] = null;
                numDiffChs --;
                // we do not need to scan rest of candidates for this round
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        RemoveDupLetters ob = new RemoveDupLetters();
        System.out.println(ob.removeDuplicateLetters("bcabc"));
    }
}
