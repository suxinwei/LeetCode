import util.ListNode;
import util.Utils;

/**
 * created by suxinwei at 2025-04-30
 * description: https://leetcode.cn/problems/reverse-nodes-in-k-group
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 * 示例 2：
 *
 *
 *
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 *
 *
 * 提示：
 * 链表中的节点数目为 n
 * 1 <= k <= n <= 5000
 * 0 <= Node.val <= 1000
 *
 *
 * 进阶：你可以设计一个只用 O(1) 额外内存空间的算法解决此问题吗？
 */
class _25ReverseKGroup {

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
        Utils.printList(reverseKGroup(head, 2));
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode preNode = dummyNode;
        ListNode curNode = head;
        int count = 0;
        while (curNode != null) {
            count++;
            if (count == k) {
                count = 0;
                ListNode next = preNode.next;
                ListNode succ = curNode.next;
                reverseSubList(preNode, succ);
                preNode.next = curNode;
                next.next = succ;
                preNode = next;
                curNode = succ;
            } else {
                curNode = curNode.next;
            }
        }
        return dummyNode.next;
    }

    private static void reverseSubList(ListNode preNode, ListNode endNode) {
        ListNode curNode = preNode.next;
        preNode = null;
        while (curNode != null && curNode != endNode) {
            ListNode next = curNode.next;
            curNode.next = preNode;
            preNode = curNode;
            curNode = next;
        }
    }
}
