package amazon.hashtable;

/**
 * Created by yingtan on 3/25/18.
 *
 * 138. Copy List with Random Pointer
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

 Return a deep copy of the list.


 */
import java.util.*;
public class CopyListWithRandomPointer {
    class RandomListNode {
        int label;
        RandomListNode next, random;
        RandomListNode(int x) { this.label = x; }
    }
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) return null;
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode cur = head;
        RandomListNode curCopy = new RandomListNode(cur.label);
        map.put(cur, curCopy);
        while(cur != null) {
            RandomListNode copy = map.get(cur);
            RandomListNode next = cur.next;
            // copy next
            if (next != null) {
                if (! map.containsKey(next)) {
                    RandomListNode nextCopy = new RandomListNode(next.label);
                    copy.next = nextCopy;
                    map.put(next, nextCopy);
                }
                else {
                    copy.next = map.get(next);
                }
            }
            // copy random
            RandomListNode random = cur.random;
            if (random != null) {
                if (! map.containsKey(random)) {
                    RandomListNode randomCopy = new RandomListNode(random.label);
                    copy.random = randomCopy;
                    map.put(random, randomCopy);
                }
                else {
                    copy.random = map.get(random);
                }
            }
            cur = next;
        }
        return curCopy;
    }
}
