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
class _test {

    private static int ans = 0;
    private static Map<Integer, Integer> cnt = new HashMap<>();

    public static void main(String[] args) {
        hanota(3, "left", "middle", "right");
    }

    public static void hanota(int num, String from, String tmp, String to) {
        if (num == 1) {
            System.out.println(num + ":" + from + "->" + to);
            return;
        }
        hanota(num - 1, from, to, tmp);
        System.out.println(num + ":" + from + "->" + to);
        hanota(num - 1, tmp, from, to);
    }

}
