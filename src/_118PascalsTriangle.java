import java.util.ArrayList;
import java.util.List;

/**
 * created by suxinwei at 2019/4/20
 * description: https://leetcode.com/problems/pascals-triangle/
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * <p>
 * 示例:
 * <p>
 * 输入: 5
 * 输出:
 * [
 * [1],
 * [1,1],
 * [1,2,1],
 * [1,3,3,1],
 * [1,4,6,4,1]
 * ]
 */
class _118PascalsTriangle {

    public static void main(String[] args) {
        List<List<Integer>> generate = generate(3);

        for (int i = 0; i < generate.size(); i++) {
            for (int k = 0; k < (generate.size() - i + 1) / 2; k++) {
                System.out.print("\t");
            }
            List<Integer> list = generate.get(i);
            for (Integer integer : list) {
                System.out.print(integer + "\t");
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> pascalsTriangle = new ArrayList<>();
        if (numRows == 0) {
            return pascalsTriangle;
        }
        pascalsTriangle.add(new ArrayList<>());
        pascalsTriangle.get(0).add(1);
        for (int i = 1; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(1);
            for (int j = 1; j < i; j++) {
                List<Integer> above = pascalsTriangle.get(i - 1);
                list.add(above.get(j - 1) + above.get(j));
            }
            list.add(1);
            pascalsTriangle.add(list);
        }
        return pascalsTriangle;
    }
}
