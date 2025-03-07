/**
 * created by suxinwei at 2019-04-18
 * description: https://leetcode.cn/problems/maximum-subarray/
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例:
 * <p>
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 */
class _053MaximumSubarray {
    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(nums));
    }

    public static int maxSubArray(int[] nums) {
        int max = nums[0];
        int sum = nums[0];
        int length = nums.length;
        for (int i = 1; i < length; i++) {
            int num = nums[i];
            sum += num;
            if (num > sum) {
                sum = num;
            }
            if (sum > max) {
                max = sum;
            }
        }
        return max;
    }
}
