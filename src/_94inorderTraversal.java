import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import util.TreeNode;
import util.Utils;

/**
 * created by suxinwei at 2025-4-9
 * description: https://leetcode.cn/problems/binary-tree-inorder-traversal/description/?envType=problem-list-v2&envId=binary-tree
 * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,null,2,3]
 * 输出：[1,3,2]
 * 示例 2：
 *
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：root = [1]
 * 输出：[1]
 *
 *
 * 提示：
 *
 * 树中节点数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 *
 *
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */
class _94inorderTraversal {

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        TreeNode secondNode = new TreeNode(2);
        TreeNode threeNode = new TreeNode(3);

        node.right = secondNode;
        secondNode.left = threeNode;

        List<Integer> results = inorderTraversal2(node);
        Utils.printList(results);
        Utils.printTree(node);
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        return inorderTraversal(root, new ArrayList<>());
    }

    public static List<Integer> inorderTraversal(TreeNode root, List<Integer> results) {
        if (root == null) {
            return results;
        }
        inorderTraversal(root.left, results);
        results.add(root.val);
        inorderTraversal(root.right, results);
        return results;
    }

    public static List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                results.add(root.val);
                root = root.right;
            }
        }
        return results;
    }
}
