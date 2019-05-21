/**
 * created by suxinwei at 2019-05-15
 * description: https://leetcode.com/problems/rotate-array/
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3,4,5,6,7] 和 k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 * 示例 2:
 * <p>
 * 输入: [-1,-100,3,99] 和 k = 2
 * 输出: [3,99,-1,-100]
 * 解释:
 * 向右旋转 1 步: [99,-1,-100,3]
 * 向右旋转 2 步: [3,99,-1,-100]
 * 说明:
 * <p>
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 要求使用空间复杂度为 O(1) 的原地算法。
 */
class _189RotateArray {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        rotate2(nums, k);
        Utils.printArray(nums);
    }

    public static void rotate3(int[] nums, int k) {
        if (k == 0) {
            return;
        }
        int length = nums.length;
        k = k % length;

        int[] temp = new int[k];
        for (int i = 0; i < k; i++) {
            temp[i] = nums[length - k + i];
        }

        for (int i = length - 1; i >= k; i--) {
            nums[i] = nums[i - k];
        }

        for (int i = 0; i < k; i++) {
            nums[i] = temp[i];
        }

    }

    public static void rotate(int[] nums, int k) {
        if (k == 0) {
            return;
        }
        int length = nums.length;

        for (int i = length - 1; i > k - 1; i--) {
            int j = (i + k) % length;
            nums[i] = nums[j];
        }

        k = k % length;
        for (int i = 0; i < length; i++) {
            int temp = nums[i];
            nums[i] = nums[length - k];
        }
    }

    public static void rotate2(int[] nums, int k) {
        int length = nums.length;
        k = k % length;
        int temp;
        for (int i = 0; i < k; i++) {
            temp = nums[length - 1];
            for (int j = length - 1; j > 0; j--) {
                nums[j] = nums[j - 1];
            }
            nums[0] = temp;
        }
    }
}
