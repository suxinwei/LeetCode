package test;

class Hanoi {
    public static void main(String[] args) {
        printHanoi(2, "left", "right", "middle");
    }

    public static void printHanoi(int num, String source, String target, String temp) {
        if (num == 1) {
            System.out.println(1 + ": From " + source + " to " + target);
            return;
        }
        printHanoi(num - 1, source, temp, target);
        System.out.println(num + ": From " + source + " to " + target);
        printHanoi(num - 1, temp, target, source);
    }
}
