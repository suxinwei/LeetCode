import util.Utils;

/**
 * created by suxinwei at 2019-04-16
 * description: https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * 示例 1:
 * <p>
 * 给定数组 nums = [1,1,2],
 * <p>
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 * <p>
 * 你不需要考虑数组中超出新长度后面的元素。
 * 示例 2:
 * <p>
 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
 * <p>
 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 * <p>
 * 你不需要考虑数组中超出新长度后面的元素。
 * 说明:
 * <p>
 * 为什么返回数值是整数，但输出的答案是数组呢?
 * <p>
 * 请注意，输入数组是以“引用”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 */
class _026RemoveDuplicatesFromSortedArray {
    public static void main(String[] args) {
//        int[] nums = {1};
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int count = removeDuplicates2(nums);
        System.out.println("count=" + count);
        Utils.printArray(nums);
    }

    public static int removeDuplicates2(int[] nums) {
        if (nums == null) {
            return 0;
        }

        int length = nums.length;
        if (length == 0) {
            return 0;
        }

        int i = 0;
        for (int j = 1; j < length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }

        return i + 1;
    }

    public static int removeDuplicates(int[] nums) {
        if (nums == null) {
            return 0;
        }

        int length = nums.length;
        if (length < 2) {
            return length;
        }

        for (int i = 1, size1 = length - 1; i < size1; i++) {
            int min = nums[i - 1];
            for (int j = i; j < length; j++) {
                int num = nums[j];
                if (num > min) {
                    min = num;
                    break;
                }
            }
            nums[i] = min;
        }

        int count = 1;
        for (int i = 1; i < length; i++) {
            if (nums[i] > nums[i - 1]) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }
}
