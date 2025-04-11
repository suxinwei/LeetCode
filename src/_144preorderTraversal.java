import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import util.TreeNode;
import util.Utils;

/**
 * created by suxinwei at 2025-4-9
 * description:
 * https://leetcode.cn/problems/binary-tree-preorder-traversal/description/?envType=problem-list-v2&envId=binary-tree
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 *
 *
 *
 * 示例 1：
 *
 * 输入：root = [1,null,2,3]
 *
 * 输出：[1,2,3]
 *
 * 解释：
 *
 *
 *
 * 示例 2：
 *
 * 输入：root = [1,2,3,4,5,null,8,null,null,6,7,9]
 *
 * 输出：[1,2,4,5,6,7,3,8,9]
 *
 * 解释：
 *
 *
 *
 * 示例 3：
 *
 * 输入：root = []
 *
 * 输出：[]
 *
 * 示例 4：
 *
 * 输入：root = [1]
 *
 * 输出：[1]
 *
 *
 *
 * 提示：
 *
 * 树中节点数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 *
 *
 * 进阶：递归算法很简单，你可以通过迭代算法完成吗？
 */
class _144preorderTraversal {

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        TreeNode secondNode = new TreeNode(2);
        TreeNode threeNode = new TreeNode(3);

        node.right = secondNode;
        secondNode.left = threeNode;

        List<Integer> results = preorderTraversal2(node);
        Utils.printList(results);
    }

    public static List<Integer> preorderTraversal(TreeNode root) {
        return preorderTraversal(root, new ArrayList<>());
    }

    public static List<Integer> preorderTraversal(TreeNode root, List<Integer> results) {
        if (root == null) {
            return results;
        }
        results.add(root.val);
        preorderTraversal(root.left, results);
        preorderTraversal(root.right, results);
        return results;
    }

    public static List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            results.add(root.val);
            if (root.right != null) {
                stack.push(root.right);
            }
            if (root.left != null) {
                stack.push(root.left);
            }
        }
        return results;
    }
}
