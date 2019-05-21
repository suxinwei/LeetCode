import java.util.List;

class Utils {
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

        if (list.size() == 0) {
            System.out.println("列表empty");
            return;
        }

        for (Object o : list) {
            System.out.println(o);
        }
    }
}
