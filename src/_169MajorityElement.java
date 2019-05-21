import java.util.HashMap;
import java.util.Map;

/**
 * created by suxinwei at 2019-05-05
 * description: https://leetcode.com/problems/majority-element/
 * 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在众数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,3]
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 */
class _169MajorityElement {
    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 3, 4};
        System.out.println(majorityElement(nums));
    }

    public static int majorityElement(int[] nums) {
        //该数组中总是存在众数
        //摩尔投票法
        int majority = -1;//候选元素
        int count = 0;//候选元素出现的次数

        for (int num : nums) {
            if (count == 0) {
                majority = num;
                count++;
            } else {
                if (majority == num) {
                    count++;
                } else {
                    count--;
                }
            }
        }

        return majority;
    }

    public static int majorityElement2(int[] nums) {
        int length = nums.length;
        int threshold = length >> 1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int key : nums) {
            Integer integer = map.get(key);
            if (integer == null) {
                map.put(key, 1);
            } else {
                int value = integer + 1;
                if (value > threshold) {
                    return key;
                }
                map.put(key, value);
            }
        }
        return nums[0];
    }

}
