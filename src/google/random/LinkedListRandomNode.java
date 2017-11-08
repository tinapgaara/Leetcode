package google.random;

import linkedlist.ListNode;

import java.util.Random;

/**
 * Created by yingtan on 8/24/17.
 *
 * 382. Linked List Random Node
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a singly linked list, return a random node's value from the linked list. Each node must have the same probability of being chosen.

 Follow up:
 What if the linked list is extremely large and its length is unknown to you? Could you solve this efficiently without using extra space?

 Example:

 // Init a singly linked list [1,2,3].
 ListNode head = new ListNode(1);
 head.next = new ListNode(2);
 head.next.next = new ListNode(3);
 Solution solution = new Solution(head);

 // getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
 solution.getRandom();
 */
public class LinkedListRandomNode {

    ListNode head;
    Random randomGenerator;

    /** @param head The linked list's head.
    Note that the head is guaranteed to be not null, so it contains at least one node. */
    public LinkedListRandomNode(ListNode head) {
        this.head = head;
        this.randomGenerator = new Random();
    }

    /** Returns a random node's value. */
    public int getRandom() {
        int res = head.val;
        int i = 2;
        ListNode cur = head.next;
        while (cur != null) {
            int random = randomGenerator.nextInt(i);
            if (random == 0) {
                res = cur.val;
            }
            cur = cur.next;
            i ++;
        }
        return res;
    }
}

