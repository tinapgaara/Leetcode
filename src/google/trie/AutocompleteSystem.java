package google.trie;

import java.util.*;

/**
 * Created by yingtan on 11/15/17.
 *
 * 642. Design Search Autocomplete System
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and end with a special character '#'). For each character they type except '#', you need to return the top 3 historical hot sentences that have prefix the same as the part of sentence already typed. Here are the specific rules:

 The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
 The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences have the same degree of hot, you need to use ASCII-code order (smaller one appears first).
 If less than 3 hot sentences exist, then just return as many as you can.
 When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.
 Your job is to implement the following functions:

 The constructor function:

 AutocompleteSystem(String[] sentences, int[] times): This is the constructor. The input is historical data. Sentences is a string array consists of previously typed sentences. Times is the corresponding times a sentence has been typed. Your system should record these historical data.

 Now, the user wants to input a new sentence. The following function will provide the next character the user types:

 List<String> input(char c): The input c is the next character typed by the user. The character will only be lower-case letters ('a' to 'z'), blank space (' ') or a special character ('#'). Also, the previously typed sentence should be recorded in your system. The output will be the top 3 historical hot sentences that have prefix the same as the part of sentence already typed.


 Example:
 Operation: AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2])
 The system have already tracked down the following sentences and their corresponding times:
 "i love you" : 5 times
 "island" : 3 times
 "ironman" : 2 times
 "i love leetcode" : 2 times
 Now, the user begins another search:

 Operation: input('i')
 Output: ["i love you", "island","i love leetcode"]
 Explanation:
 There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree. Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman". Also we only need to output top 3 hot sentences, so "ironman" will be ignored.

 Operation: input(' ')
 Output: ["i love you","i love leetcode"]
 Explanation:
 There are only two sentences that have prefix "i ".

 Operation: input('a')
 Output: []
 Explanation:
 There are no sentences that have prefix "i a".

 Operation: input('#')
 Output: []
 Explanation:
 The user finished the input, the sentence "i a" should be saved as a historical sentence in system. And the following input will be counted as a new search.

 */
public class AutocompleteSystem {

    public class Trie {
        public char ch;
        Map<Character, Trie> children;
        Map<String, Integer> matchedWords;
        boolean isEnd;

        public Trie(char ch) {
            this.ch = ch;
            children = new HashMap<>();
            matchedWords = new HashMap<>();
        }
    }

    Trie root = new Trie('$');
    String searchWord = "";

    public AutocompleteSystem(String[] sentences, int[] times) {
        for (int i = 0 ; i < sentences.length; i ++) {
            String sentence = sentences[i];
            int count = times[i];
            add(root, sentence, count);
        }
    }

    public void add(Trie root, String sentence, int count) {
        Trie cur = root;
        for (char ch : sentence.toCharArray()) {
            if (cur.children.get(ch) == null) {
                Trie child = new Trie(ch);
                cur.children.put(ch, child);
            }
            cur = cur.children.get(ch);
            if (! cur.matchedWords.containsKey(sentence)) {
                cur.matchedWords.put(sentence, count);
            }
            else {
                cur.matchedWords.put(sentence, cur.matchedWords.get(sentence) + count);
            }
        }
        cur.isEnd = true;
    }

    public List<String> input(char c) {
        if(c == '#') {
            //  this is the end
            add(root, searchWord, 1);
            // clear this word
            searchWord = "";
            return new ArrayList<String>();
        }
        else {
            Trie cur = root;
            searchWord = searchWord + c;
            for (char ch : searchWord.toCharArray()) {
                if (cur.children.containsKey(ch)) {
                    cur = cur.children.get(ch);
                }
                else {
                    return new ArrayList<String>();
                }
            }
            // get the matched words map
            Map<String, Integer> freq = cur.matchedWords;
            // select top 3
            EntryComparator comparator = new EntryComparator();
            PriorityQueue<Map.Entry> queue = new PriorityQueue<Map.Entry>(comparator);
            for (Map.Entry<String, Integer> entry : freq.entrySet()) {
                queue.offer(entry);
                if (queue.size() > 3) {
                    queue.poll();
                }
            }
            String[] res = new String[3];
            int i = 3;
            while(! queue.isEmpty()) {
                res[i-1] = (String)queue.poll().getKey();
                i --;
            }
            if (i > 0) {
                List<String> resl = new ArrayList<>();
                for (; i < 3; i ++) {
                    resl.add(res[i]);
                }
                return resl;
            }
            return Arrays.asList(res);
        }
    }

    public class EntryComparator implements Comparator<Map.Entry> {
        public int compare(Map.Entry e1, Map.Entry e2) {
            if ((int)e1.getValue() != (int)e2.getValue()) {
                return (int)e1.getValue() - (int)e2.getValue();
            }
            else {
                return ((String)e2.getKey()).compareTo((String)e1.getKey());
            }
        }
    }

    public static void main(String[] args) {

        String[] sentences = {"i love you","island","iroman","i love leetcode"};
        int[] times = {5,3,2,2};
        AutocompleteSystem ob = new AutocompleteSystem(sentences, times);
        System.out.println(ob.input('i'));
        System.out.println(ob.input(' '));
        //["i"],[" "],["a"],["#"]};
    }
}
