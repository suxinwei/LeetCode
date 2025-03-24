import util.ListNode;

/*
 *
 * created by suxinwei at 2019/4/2
 * description: https://leetcode-cn.com/problems/add-two-numbers/
 * <p>
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
class _002AddTwoNums {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(0);
//        l1.next = new ListNode(4);
//        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(0);
//        l2.next = new ListNode(9);
//        l2.next.next = new ListNode(4);
//        l2.next.next.next = new ListNode(9);
//        l2.next.next.next.next = new ListNode(9);
//        l2.next.next.next.next.next = new ListNode(9);
//        l2.next.next.next.next.next.next = new ListNode(9);
//        l2.next.next.next.next.next.next.next = new ListNode(9);
//        l2.next.next.next.next.next.next.next.next = new ListNode(9);
//        l2.next.next.next.next.next.next.next.next.next = new ListNode(9);

        ListNode listNode = addTwoNumbers2(l1, l2);
        for (int i = 0; listNode != null; i++) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode headNode = new ListNode(0);
        ListNode current = headNode;
        int quotient = 0;
        while (l1 != null || l2 != null) {
            int val1 = 0;
            int val2 = 0;
            ListNode tmp = null;
            if (l1 != null) {
                val1 = l1.val;
                tmp = l1;
                l1 = l1.next;
            }
            if (l2 != null) {
                val2 = l2.val;
                tmp = l2;
                l2 = l2.next;
            }
            int sum = val1 + val2 + quotient;
            tmp.val = sum % 10;
            current.next = tmp;
            current = tmp;
            quotient = sum / 10;
        }
        if (quotient > 0) {
            current.next = new ListNode(quotient);
        }
        return headNode.next;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int sum = l1.val + l2.val;
        ListNode listNode = new ListNode(sum % 10);
        ListNode current = listNode;
        int quotient = sum / 10;
        l1 = l1.next;
        l2 = l2.next;
        while (l1 != null || l2 != null) {
            int val1 = 0;
            int val2 = 0;
            ListNode tmp = null;
            if (l1 != null) {
                val1 = l1.val;
                tmp = l1;
                l1 = l1.next;
            }
            if (l2 != null) {
                val2 = l2.val;
                tmp = l2;
                l2 = l2.next;
            }
            sum = val1 + val2 + quotient;
            tmp.val = sum % 10;
            current.next = tmp;
            current = tmp;
            quotient = sum / 10;
        }
        if (quotient > 0) {
            current.next = new ListNode(quotient);
        }
        return listNode;
    }
}
