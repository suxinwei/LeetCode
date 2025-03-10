import java.util.Arrays;

import util.Utils;

/**
 * created by suxinwei at 2019/4/22
 * description: https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 * <p>
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 * <p>
 * 说明:
 * <p>
 * 返回的下标值（index1 和 index2）不是从零开始的。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 * 示例:
 * <p>
 * 输入: numbers = [2, 7, 11, 15], target = 9
 * 输出: [1,2]
 * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 */
class _167TwoSumII {
    public static void main(String[] args) {
        int[] numbers = {2, 7, 11, 15};
        int target = 9;
        int[] ints = twoSum2(numbers, target);
        Utils.printArray(ints);
    }

    public static int[] twoSum2(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum < target) {
                left++;
            } else if (sum > target) {
                right--;
            } else {
                return new int[]{left + 1, right + 1};
            }
        }
        return null;
    }

    public static int[] twoSum(int[] numbers, int target) {
        int length = numbers.length;
        for (int i = 0; i < length; i++) {
            int complement = target - numbers[i];
            int index = Arrays.binarySearch(numbers, i + 1, length, complement);
            if (index > 0) {
                return new int[]{i + 1, index + 1};
            }
        }
        return null;
    }
}
