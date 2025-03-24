package algorithm;

import util.ListNode;
import util.Utils;

/**
 * @Description:
 * @Author: xinweisu
 * @CreateDate: 2025/3/24
 */
class LinkedListTest {

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode cur = head;
        for (int i = 1; i < 5; i++) {
            ListNode listNode = new ListNode(i);
            cur.next = listNode;
            cur = listNode;
        }
        Utils.printList(head);
        System.out.println();
        Utils.printList(reverse(head));
    }

    public static ListNode reverse(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
