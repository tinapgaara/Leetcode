package facebook.greedy;

import bloomberg.random.Random2ImpleRandom6;

/**
 * Created by yingtan on 5/16/17.
 *
 * 277. Find the Celebrity Add to List
 DescriptionHintsSubmissionsSolutions
 Total Accepted: 27030
 Total Submissions: 76474
 Difficulty: Medium
 Contributor: LeetCode
 Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.

 Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).

 You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int findCelebrity(n), your function should minimize the number of calls to knows.

 Note: There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.
 */
public class FindCelebrity {

    public int findCelebrity(int n) {
        if (n == 0) return -1;
        int candidate = 0;
        for (int i = 1; i <n ; i++) {
            // if ith does not know candidate, this means i could be candidate
            // if i is not candidate, then while looping to a real candidate, i must know this, so must siwtch to the real celebrity
            // so this must will find the real celebrity
            if ( ! knows(i, candidate)) {
                candidate = i;
            }
        }

        // validate if this is celebrity
        for ( int i = 0; i < n; i ++ ){
            if ( i != candidate ){
                if ( !knows ( i, candidate ) || knows ( candidate, i ))
                    return -1;
            }
        }
        return candidate;
    }

    public boolean knows(int i, int j) {
        return false;
    }
}
