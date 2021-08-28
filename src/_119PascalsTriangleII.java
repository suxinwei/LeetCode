import java.util.ArrayList;
import java.util.List;

import util.Utils;

/**
 * created by suxinwei at 2019/4/20
 * description: https://leetcode.com/problems/pascals-triangle-ii/
 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * <p>
 * 示例:
 * <p>
 * 输入: 3
 * 输出: [1,3,3,1]
 * 进阶：
 * <p>
 * 你可以优化你的算法到 O(k) 空间复杂度吗？
 */
class _119PascalsTriangleII {
    public static void main(String[] args) {
        List<Integer> row = getRow(5);
        Utils.printList(row);
    }

    public static List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            int aboveLeft = 1, aboveRight;
            for (int j = 1; j < i; j++) {
                aboveRight = list.get(j);
                list.set(j, aboveLeft + aboveRight);
                aboveLeft = aboveRight;
            }
            list.add(1);
        }
        return list;
    }
}
