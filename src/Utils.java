class Utils {
    public static void printArray(int[] arr) {
        if (arr == null) {
            System.out.println("null");
            return;
        }
        if (arr.length == 0) {
            System.out.println("empty");
            return;
        }
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
