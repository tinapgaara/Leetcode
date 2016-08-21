package google.heap;

import java.util.*;

/**
 * Created by yingtan on 11/7/15.
 */
public class NoSameAdjChar {

    // use heap
    // every time, extrac two top chars and cancat
    // then change hashmap's values

    // greedy algorithm

    public String noSameAdjChar(String str) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        char[] ss = str.toCharArray();
        for (int i = 0; i < ss.length; i++) {

            if (map.containsKey(ss[i])) {
                map.put(ss[i], map.get(ss[i]) + 1);
            } else {
                map.put(ss[i], 1);
            }
        }

        Queue<Character> queue = new PriorityQueue<Character>(1, new Comparator<Character>() {
            @Override
            public int compare(Character c1, Character c2) {
                int num1 = map.get(c1);
                int num2 = map.get(c2);
                if (num1 > num2) {
                    return -1;
                } else if (num1 < num2) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        for (Map.Entry<Character, Integer> entry: map.entrySet()) {
            queue.add(entry.getKey());
        }

        StringBuilder builder = new StringBuilder();
        while (!queue.isEmpty()) {
            char top = queue.poll();
            if (queue.isEmpty()) {
                builder.append(top);
                break;
            }
            else {
                char secondtop = queue.poll();
                builder.append(top);
                builder.append(secondtop);

                if (map.get(top) > 1) {
                    map.put(top, map.get(top) -1);
                    queue.offer(top);
                }
                if (map.get(secondtop) > 1) {
                    map.put(secondtop, map.get(secondtop) - 1);
                    queue.offer(secondtop);
                }

            }
        }
        return builder.toString();
    }
}
