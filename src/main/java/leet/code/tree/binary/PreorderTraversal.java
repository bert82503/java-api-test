package leet.code.tree.binary;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 144. 二叉树的前序遍历
 * <p></p>
 * https://leetcode.cn/problems/binary-tree-preorder-traversal/
 * <pre>
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 * </pre>
 *
 * @author guangyi
 */
public class PreorderTraversal {
    /**
     * 进阶：递归算法很简单，你可以通过迭代算法完成吗？
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        // 栈
        Deque<TreeNode> deque = new LinkedList<>();
        // 初始化，根节点入栈
        if (root != null) {
            deque.addFirst(root);
        }
        while (!deque.isEmpty()) {
            //System.out.printf("deque=%s\n", deque.size());
            // 出栈
            TreeNode node = deque.removeFirst();
            // 结果计算
            result.add(node.val);
            // 右节点入栈
            if (node.right != null) {
                deque.addFirst(node.right);
            }
            // 左节点入栈
            if (node.left != null) {
                deque.addFirst(node.left);
            }
        }
        return result;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
