import util.ListNode;
import util.Utils;

/**
 * created by suxinwei at 2025-04-24
 * description: https://leetcode.cn/problems/reverse-linked-list-ii/description/
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 * 示例 2：
 *
 * 输入：head = [5], left = 1, right = 1
 * 输出：[5]
 *
 *
 * 提示：
 *
 * 链表中节点数目为 n
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 *
 *
 * 进阶： 你可以使用一趟扫描完成反转吗？
 */
class _92ReverseBetweenLinkedList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode cur = head;
        for (int i = 2; i < 6; i++) {
            ListNode listNode = new ListNode(i);
            cur.next = listNode;
            cur = listNode;
        }
        Utils.printList(head);
        System.out.println();
        Utils.printList(reverseBetween3(head, 2, 4));
    }

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || right <= 1) {
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;
        ListNode startPre = null;
        ListNode endPre = null;
        int index = 1;
        while (cur != null && index <= right) {
            ListNode next = cur.next;

            if (index == left) {
                startPre = pre;
                endPre = cur;
            }

            if (index >= left && index <= right) {
                cur.next = pre;
            }

            pre = cur;
            cur = next;
            index++;
        }
        if (startPre == null) {
            startPre = head;
        }
        startPre.next = pre;
        if (endPre != null) {
            endPre.next = cur;
        }
        return left == 1 ? pre : head;
    }

    public static ListNode reverseBetween3(ListNode head, int left, int right) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode pre = dummyNode;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next;
        ListNode next;
        for (int i = 0; i < right - left; i++) {
            next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummyNode.next;
    }

    public static ListNode reverseBetween2(ListNode head, int left, int right) {
        // 设置 dummyNode 是这一类问题的一般做法
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode pre = dummyNode;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next;
        ListNode next;
        for (int i = 0; i < right - left; i++) {
            next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummyNode.next;
    }
}
