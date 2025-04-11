import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import util.TreeNode;
import util.Utils;

/**
 * created by suxinwei at 2025-4-9
 * description: https://leetcode.cn/problems/binary-tree-level-order-traversal/description/
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[3],[9,20],[15,7]]
 * 示例 2：
 *
 * 输入：root = [1]
 * 输出：[[1]]
 * 示例 3：
 *
 * 输入：root = []
 * 输出：[]
 *
 *
 * 提示：
 *
 * 树中节点数目在范围 [0, 2000] 内
 * -1000 <= Node.val <= 1000
 */
class _102levelOrderTraversal {

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        TreeNode secondNode = new TreeNode(2);
        TreeNode threeNode = new TreeNode(3);

        node.right = secondNode;
        secondNode.left = threeNode;

        List<List<Integer>> lists = levelOrder(node);
        Utils.printList(lists);
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int currentLevelSize = queue.size();
            for (int i = 0; i < currentLevelSize; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            results.add(level);
        }
        return results;
    }

    public static List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        levelOrderHelper(root, ans, 1);
        return ans;
    }

    public static void levelOrderHelper(TreeNode root, List<List<Integer>> ans, int level) {
        if (root == null) {
            return;
        }

        if (ans.size() < level) {
            List<Integer> tempAns = new ArrayList<>();
            ans.add(tempAns);
        }
        ans.get(level - 1).add(root.val);
        levelOrderHelper(root.left, ans, level + 1);
        levelOrderHelper(root.right, ans, level + 1);
    }
}
