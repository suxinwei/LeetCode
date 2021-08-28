import util.Utils;

/**
 * created by suxinwei at 2019/4/19
 * description: https://leetcode.com/problems/merge-sorted-array/
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 * <p>
 * 说明:
 * <p>
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * 示例:
 * <p>
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * <p>
 * 输出: [1,2,2,3,5,6]
 */
class _088MergeSortedArray {

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 4, 5, 6, 0};
        int m = 5;
        int[] nums2 = {3};
        int n = 1;
        merge(nums1, m, nums2, n);
        Utils.printArray(nums1);
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int nums2Index = 0;
        for (int i = 0; nums2Index < n; i++) {
            int num2 = nums2[nums2Index];
            if (nums1[i] > num2) {
                reSort(nums1, i, num2, m + nums2Index - i);
                nums2Index++;
                continue;
            }
            if (i >= m + nums2Index) {
                nums1[i] = nums2[nums2Index];
                nums2Index++;
            }
        }
    }

    private static void reSort(int[] nums1, int index, int num2, int lengh) {
        System.arraycopy(nums1, index, nums1, index + 1, lengh);
        nums1[index] = num2;
    }


}
