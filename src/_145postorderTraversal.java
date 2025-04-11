import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import util.TreeNode;
import util.Utils;

/**
 * created by suxinwei at 2025-4-9
 * description: https://leetcode.cn/problems/binary-tree-postorder-traversal/description/
 * 给你一棵二叉树的根节点 root ，返回其节点值的 后序遍历 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：root = [1,null,2,3]
 *
 * 输出：[3,2,1]
 *
 * 解释：
 *
 *
 *
 * 示例 2：
 *
 * 输入：root = [1,2,3,4,5,null,8,null,null,6,7,9]
 *
 * 输出：[4,6,7,5,2,9,8,3,1]
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
 * 树中节点的数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 *
 *
 * 进阶：递归算法很简单，你可以通过迭代算法完成吗？
 */
class _145postorderTraversal {

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        TreeNode secondNode = new TreeNode(2);
        TreeNode threeNode = new TreeNode(3);

        node.right = secondNode;
        secondNode.left = threeNode;

        List<Integer> results = postorderTraversal2(node);
        Utils.printList(results);
    }

    public static List<Integer> postorderTraversal(TreeNode root) {
        return postorderTraversal(root, new ArrayList<>());
    }

    public static List<Integer> postorderTraversal(TreeNode root, List<Integer> results) {
        if (root == null) {
            return results;
        }
        postorderTraversal(root.left, results);
        postorderTraversal(root.right, results);
        results.add(root.val);
        return results;
    }

    public static List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        Stack<TreeNode> helpStack = new Stack<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            helpStack.push(root);
            if (root.left != null) {
                stack.push(root.left);
            }
            if (root.right != null) {
                stack.push(root.right);
            }
        }
        while (!helpStack.isEmpty()) {
            results.add(helpStack.pop().val);
        }
        return results;
    }

    public static List<Integer> postorderTraversal3(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            results.addFirst(root.val);
            if (root.left != null) {
                stack.push(root.left);
            }
            if (root.right != null) {
                stack.push(root.right);
            }
        }
        return results;
    }
}
