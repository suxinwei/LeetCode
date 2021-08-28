package test;

class Hanoi {
    public static void main(String[] args) {
//        printHanoi(3, "左边", "右边", "中间");
        printHanoi(4, "左边", "中间", "右边");
    }

    public static void printHanoi(int num, String source, String target, String temp) {
        if (num == 1) {
            System.out.println(1 + ":" + source + "->" + target);
            return;
        }
        printHanoi(num - 1, source, temp, target);
        System.out.println(num + ":" + source + "->" + target);
        printHanoi(num - 1, temp, target, source);
    }
}
