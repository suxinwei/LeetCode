import util.ListNode;
import util.Utils;

/**
 * created by suxinwei at 2025-04-8
 * description: https://leetcode.cn/problems/palindrome-linked-list/description/
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,2,1]
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：head = [1,2]
 * 输出：false
 *
 *
 * 提示：
 *
 * 链表中节点数目在范围[1, 105] 内
 * 0 <= Node.val <= 9
 *
 *
 * 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 */
class _234IsPalindromeLinkedList {

    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 2, 1, 0};
        ListNode head = new ListNode(arr[0]);
        ListNode cur = head;
        for (int i = 1; i < arr.length; i++) {
            ListNode listNode = new ListNode(arr[i]);
            cur.next = listNode;
            cur = listNode;
        }
        Utils.printList(head);
        System.out.println(isPalindrome(head));
        Utils.printList(head);
    }

    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode firstHalfEndNode = getFirstHalfEndNode(head);
        ListNode secondHalfEndNode = reverseList(firstHalfEndNode.next);
        ListNode curSecondHalfNode = secondHalfEndNode;
        boolean result = true;
        while (curSecondHalfNode != null) {
            if (head.val != curSecondHalfNode.val) {
                result = false;
                break;
            }
            head = head.next;
            curSecondHalfNode = curSecondHalfNode.next;
        }
        reverseList(secondHalfEndNode);
        return result;
    }

    private static ListNode  reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
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

    private static ListNode getFirstHalfEndNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
