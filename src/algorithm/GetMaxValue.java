package algorithm;

import java.util.Arrays;

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
        Node head = getMaxValue(arr, count);
        while (head != null) {
            int value = head.value;
            head = head.next;
            System.out.println(value);
        }
    }

    private static Node getMaxValue(int[] arr, int count) {
        if (arr == null) {
            return null;
        }

        int length = arr.length;
        if (length < count) {
            return null;
        }

        Node head = new Node();
        head.value = arr[0];
        for (int i = 1; i < count; i++) {
            int value = arr[i];
            if (value <= head.value) {
                Node node = new Node();
                node.value = value;
                node.next = head;
                head = node;
            } else {
                updateNode(head, value);
            }
        }

        for (int i = count; i < length; i++) {
            int value = arr[i];
            if (value > head.value) {
                updateNode(head, value);
                head = head.next;
            }
        }
        return head;
    }

    private static void updateNode(Node head, int value) {
        Node node = new Node();
        node.value = value;
        Node tmp = head;
        while (true) {
            Node next = tmp.next;
            if (next == null) {
                tmp.next = node;
                break;
            } else {
                if (value <= next.value) {
                    tmp.next = node;
                    node.next = next;
                    break;
                } else {
                    tmp = next;
                }
            }
        }
    }

    private static class Node {
        public int value;
        public Node next;
    }
}
