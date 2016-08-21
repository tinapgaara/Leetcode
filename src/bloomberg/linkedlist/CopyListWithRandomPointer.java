package bloomberg.linkedlist;

import java.util.HashMap;

/**
 * Created by yingtan on 11/11/15.
 */
/*
* A linked list is given such that each node contains an additional random pointer
* which could point to any node in the list or null.

Return a deep copy of the list.
* */

public class CopyListWithRandomPointer {

    public class RandomListNode {
        int label;
        RandomListNode next, random;
        RandomListNode(int x) {
            this.label = x;
        }
    }

    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) return null;

        HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode,RandomListNode>();
        RandomListNode tmp = head;
        RandomListNode copyHead = new RandomListNode(head.label);
        map.put(head, copyHead);

        RandomListNode copyTmp = copyHead;

        while (tmp != null) {
            // create a copy node of current tmp node : tmpCopy
            if (map.containsKey(tmp)) {
                copyTmp = map.get(tmp);
            }
            else {
                copyTmp = new RandomListNode(tmp.label);
                map.put(tmp, copyTmp);
            }

            // connect current tmpCopy node with next node
            RandomListNode nextNode = tmp.next;
            if (nextNode == null) {
                copyTmp.next = null;
            }
            else {
                if (map.containsKey(nextNode)) { // error contains(null) ??
                    copyTmp.next = map.get(nextNode);
                }
                else {
                    RandomListNode copyNextNode = new RandomListNode(nextNode.label);
                    copyTmp.next = copyNextNode;
                    map.put(nextNode, copyNextNode);
                }
            }

            // connect current tmpCopy node with random node
            RandomListNode randNode = tmp.random;
            if (randNode == null) {
                copyTmp.random = null;
            }
            else {
                if (map.containsKey(randNode)) {
                    copyTmp.random = map.get(randNode);
                }
                else {
                    RandomListNode copyRandNode = new RandomListNode(randNode.label);
                    copyTmp.random = copyRandNode;
                    map.put(randNode, copyRandNode);
                }
            }
            tmp = tmp.next;
        }
        return copyHead;
    }

}
