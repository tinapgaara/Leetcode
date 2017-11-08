package google.greedy;

import java.util.*;

/**
 * Created by yingtan on 8/17/17.
 *
 * 358. Rearrange String k Distance Apart
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a non-empty string s and an integer k, rearrange the string such that the same characters are at least distance k from each other.

 All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".

 Example 1:
 s = "aabbcc", k = 3

 Result: "abcabc"

 The same letters are at least distance 3 from each other.
 Example 2:
 s = "aaabc", k = 3

 Answer: ""

 It is not possible to rearrange the string.
 Example 3:
 s = "aaadbbcc", k = 2

 Answer: "abacabcd"

 Another possible answer is: "abcabcda"

 The same letters are at least distance 2 from each other.
 */
public class RerrangeStrKDistanceApart {

    public String rearrangeString(String s, int k) {
        if(k==0) return s;

        //initialize the counter for each character
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for(int i=0; i< s.length(); i++){
            char c = s.charAt(i);
            if(map.containsKey(c)){
                map.put(c, map.get(c)+1);
            }else{
                map.put(c, 1);
            }
        }

        //sort the chars by frequency
        //
        PriorityQueue<Character> queue = new PriorityQueue<Character>(new Comparator<Character>(){
            public int compare(Character c1, Character c2){
                if(map.get(c2).intValue() != map.get(c1).intValue()){
                    return map.get(c2)-map.get(c1);
                }else{
                    return c1.compareTo(c2);// a is before b
                }
            }
        });

        for(char c: map.keySet()) queue.offer(c);

        StringBuilder sb = new StringBuilder();

        int lenNeedToFill = s.length();
        // 在优先队列中存储以(数量+字符)的对, 以k为区间大小排列k个从数量高到低的字符, 然后再重复此过程, 这种贪心的策略可以保证让数量大的最优先排列, 并且使其间隔最小的距离, 否则到后来可能没有足够的空间.
        while(!queue.isEmpty()) {
            int dist = Math.min(lenNeedToFill, k);
            List<Character> tmp = new ArrayList<Character>();
            // during for dist, must be distinct chs here
            for (int i = 0 ; i < dist ; i ++) {
                // can not rerange the string
                if(queue.isEmpty()) return "";

                // 贪心的策略可以保证间隔了k之后，首先排数量大
                // s = "aaadbbcc", k = 2
                // eg: a _ a _ a
                //     a b a _ a
                //     then, left a: 0, left b:1, left c: 2
                //     a b a c a  这里要填c instead of b，因为b剩1个，c还剩两个，优先排剩的多的，让freq多的ch的间距尽可能的小==k
                char ch = queue.poll();
                sb.append(ch + "");

                map.put(ch, map.get(ch) - 1);
                if (map.get(ch) > 0) {
                    // only add to tmp, outside this loop, add to queue
                    tmp.add(ch);
                }
                lenNeedToFill --;
            }

            for (char ch : tmp) {
                queue.offer(ch);
            }
        }
        return sb.toString();
    }
}
