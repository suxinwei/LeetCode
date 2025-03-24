package algorithm;

import java.util.Arrays;
import util.ListNode;

/**
 * created by 80288791 at 2021/7/20
 * description:
 */
class GetMaxValue {

    public static void main(String[] args) {
        int[] arr = {1, 0, 8, 4, 5, 6, 2, 1, 6, 6};
        int count = arr.length / 2;
        System.out.println("初始数组：" + Arrays.toString(arr));
        System.out.println("最大个数count=" + count);
        ListNode head = getMaxValue(arr, count);
        while (head != null) {
            int value = head.val;
            head = head.next;
            System.out.println(value);
        }
    }

    private static ListNode getMaxValue(int[] arr, int count) {
        if (arr == null) {
            return null;
        }

        int length = arr.length;
        if (length < count) {
            return null;
        }

        ListNode head = new ListNode(arr[0]);
        for (int i = 1; i < count; i++) {
            int value = arr[i];
            if (value <= head.val) {
                ListNode listNode = new ListNode(value);
                listNode.next = head;
                head = listNode;
            } else {
                updateNode(head, value);
            }
        }

        for (int i = count; i < length; i++) {
            int value = arr[i];
            if (value > head.val) {
                updateNode(head, value);
                head = head.next;
            }
        }
        return head;
    }

    private static void updateNode(ListNode head, int value) {
        ListNode listNode = new ListNode(value);
        ListNode tmp = head;
        while (true) {
            ListNode next = tmp.next;
            if (next == null) {
                tmp.next = listNode;
                break;
            } else {
                if (value <= next.val) {
                    tmp.next = listNode;
                    listNode.next = next;
                    break;
                } else {
                    tmp = next;
                }
            }
        }
    }

}
