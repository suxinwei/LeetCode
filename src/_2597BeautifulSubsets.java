import java.util.HashMap;
import java.util.Map;

/**
 * created by suxinwei at 2019-05-15
 * description: https://leetcode.cn/problems/the-number-of-beautiful-subsets/description/
 * 给你一个由正整数组成的数组 nums 和一个 正 整数 k 。
 *
 * 如果 nums 的子集中，任意两个整数的绝对差均不等于 k ，则认为该子数组是一个 美丽 子集。
 *
 * 返回数组 nums 中 非空 且 美丽 的子集数目。
 *
 * nums 的子集定义为：可以经由 nums 删除某些元素（也可能不删除）得到的一个数组。只有在删除元素时选择的索引不同的情况下，两个子集才会被视作是不同的子集。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,4,6], k = 2
 * 输出：4
 * 解释：数组 nums 中的美丽子集有：[2], [4], [6], [2, 6] 。
 * 可以证明数组 [2,4,6] 中只存在 4 个美丽子集。
 * 示例 2：
 *
 * 输入：nums = [1], k = 1
 * 输出：1
 * 解释：数组 nums 中的美丽数组有：[1] 。
 * 可以证明数组 [1] 中只存在 1 个美丽子集。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 18
 * 1 <= nums[i], k <= 1000
 */
class _2597BeautifulSubsets {

    private static int ans = 0;
    private static Map<Integer, Integer> cnt = new HashMap<>();

    public static void main(String[] args) {
        int[] nums = {2, 4, 6};
        int k = 2;
        int count = beautifulSubsets(nums, k);
        System.out.println(count);
    }

    public static int beautifulSubsets(int[] nums, int k) {
        dfs(nums, k, 0);
        return ans - 1;
    }

    public static void dfs(int[] nums, int k, int i) {
        if (i == nums.length) {
            ans++;
            return;
        }
        dfs(nums, k, i + 1);
        if (cnt.getOrDefault(nums[i] - k, 0) == 0 && cnt.getOrDefault(nums[i] + k, 0) == 0) {
            cnt.put(nums[i], cnt.getOrDefault(nums[i], 0) + 1);
            dfs(nums, k, i + 1);
            cnt.put(nums[i], cnt.getOrDefault(nums[i], 0) - 1);
        }
    }
}
