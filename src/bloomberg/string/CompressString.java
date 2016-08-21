package bloomberg.string;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by yingtan on 10/26/15.
 */
/*
* Huffman coding !!!
*
* */
public class CompressString {

    public class TrieNode {
        Pair<Character, Integer> pair;
        private TrieNode left;
        private TrieNode right;
        private boolean isStop;

        public TrieNode(Pair<Character, Integer> pair) {
            this.pair = pair;
            isStop = false;
        }
    }

    public void constructPriorQueue(String str) {
        char[] chs = str.toCharArray();

        PriorityQueue<TrieNode> queue = new PriorityQueue<>(); // need to pass comparator  to sort according to frequency
        Map<Character, Integer> frequency = new HashMap<>();
        for (int i = 0; i < chs.length; i ++) {
            if (frequency.containsKey(chs[i])) {
                int count = frequency.get(chs[i]);
                frequency.put(chs[i], count + 1);
            } else {
                frequency.put(chs[i], 1);
            }
        }
        for (Map.Entry<Character, Integer> entry: frequency.entrySet()) {
            TrieNode node = new TrieNode(new Pair<>(entry.getKey(), entry.getValue()));
            node.isStop = true;
            queue.offer(node);
        }
    }

    public TrieNode constructTrie(PriorityQueue<TrieNode> queue) {

        for (int i = 0; i < queue.size() - 1; i++) {
            TrieNode node_1 = queue.poll();
            TrieNode node_2 = queue.poll();
            TrieNode node = new TrieNode(new Pair<>(null, node_1.pair.getValue() + node_2.pair.getValue()));
            node.left = node_1;
            node.right = node_2;

            queue.offer(node);
        }
        return queue.peek();
    }

    public int searchDecodeForChar(char ch) {
        // TODO
        /*
        * go left: bit : + 0
        * go right: bit + 1
        *
        * */

        return 0;
    }
}
