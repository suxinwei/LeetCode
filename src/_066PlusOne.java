import util.Utils;

/**
 * created by suxinwei at 2019-04-18
 * description: https://leetcode.com/problems/plus-one/
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 * <p>
 * 最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。
 * <p>
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 * 解释: 输入数组表示数字 123。
 * 示例 2:
 * <p>
 * 输入: [4,3,2,1]
 * 输出: [4,3,2,2]
 * 解释: 输入数组表示数字 4321。
 */
class _066PlusOne {
    public static void main(String[] args) {
        int[] digits = {9, 4, 9};
        int[] ints = plusOne(digits);
        Utils.printArray(ints);
    }

    public static int[] plusOne(int[] digits) {
        int length = digits.length;
        for (int i = length - 1; i >= 0; i--) {
            int sum = digits[i] + 1;
            if (sum > 9) {
                digits[i] = 0;
            } else {
                digits[i] = sum;
                break;
            }
        }
        if (digits[0] == 0) {
            int[] destArr = new int[length + 1];
            destArr[0] = 1;
            System.arraycopy(digits, 0, destArr, 1, length);
            return destArr;
        }
        return digits;
    }
}
