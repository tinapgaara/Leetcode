package linkedlist;

/**
 * Created by yingtan on 9/19/15.
 */
public class Intersection {

  public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {

         int sizeA = 0, sizeB = 0;

         ListNode tmpA = headA;
         ListNode tmpB = headB;

         while(tmpA != null) {
             sizeA ++;
             tmpA = tmpA.next;
         }

         while(tmpB != null) {
             sizeB ++;
             tmpB = tmpB.next;
         }

         int sizeDiff = 0;
         ListNode cur = null;
         ListNode another = null;

         if (sizeA > sizeB) {
             sizeDiff = sizeA - sizeB;
             cur = headA;
             another= headB;
         }

         else {
             sizeDiff = sizeB - sizeA;
             cur = headB;
             another = headA;

         }
         if (cur == null) return null;

         for (int i = 0 ; i < sizeDiff; i ++) {
             cur = cur.next;
         }

         while ((cur != null) && (another != null)) {
             if (cur == another) {
                 return cur;
             }
             cur = cur.next;
             another = another.next;
         }
         return null;
     }

    public static void main (String[] args) {
        ListNode node = new ListNode(1);
        ListNode node_2 = new ListNode(1);
        Intersection.getIntersectionNode(node,node_2);
    }
}
