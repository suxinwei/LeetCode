package util;

import java.util.List;

public class Utils {

    public static void printArray(byte[] arr) {
        if (arr == null) {
            System.out.println("数组为null");
            return;
        }
        if (arr.length == 0) {
            System.out.println("数组empty");
            return;
        }
        for (int i : arr) {
            System.out.println(i);
        }
    }

    public static void printArray(int[] arr) {
        if (arr == null) {
            System.out.println("数组为null");
            return;
        }
        if (arr.length == 0) {
            System.out.println("数组empty");
            return;
        }
        for (int i : arr) {
            System.out.println(i);
        }
    }

    public static void printList(List list) {
        if (list == null) {
            System.out.println("list为null");
            return;
        }

        if (list.isEmpty()) {
            System.out.println("列表empty");
            return;
        }

        for (Object o : list) {
            System.out.println(o);
        }
    }

    public static void printList(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }
}
