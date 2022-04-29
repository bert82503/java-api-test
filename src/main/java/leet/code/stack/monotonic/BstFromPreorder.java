package leet.code.stack.monotonic;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 1008. 前序遍历构造二叉搜索树
 * <p></p>
 * https://leetcode-cn.com/problems/construct-binary-search-tree-from-preorder-traversal/
 * <pre>
 * 给定一个整数数组，它表示BST(即 二叉搜索树 )的 先序遍历 ，构造树并返回其根。
 *
 * 保证 对于给定的测试用例，总是有可能找到具有给定需求的二叉搜索树。
 *
 * 二叉搜索树 是一棵二叉树，其中每个节点， Node.left 的任何后代的值 严格小于 Node.val , 
 * Node.right 的任何后代的值 严格大于 Node.val。
 *
 * 二叉树的 前序遍历 首先显示节点的值，然后遍历Node.left，最后遍历Node.right。
 *
 * 提示：
 * 1 <= preorder.length <= 100
 * 1 <= preorder[i] <= 10^8
 * preorder 中的值 互不相同
 * </pre>
 * 递减栈的从左往右遍历/正序遍历，等价于，二叉搜索树的前序遍历
 *
 * @author guangyi
 */
public class BstFromPreorder {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
    public TreeNode bstFromPreorder(int[] preorder) {
        int length = preorder.length;
        TreeNode[] result = new TreeNode[length];
        // 递减栈
        Deque<Integer> indexMonoStack = new LinkedList<>();
        // 从左往右遍历，正序遍历
        for (int i = 0; i < length; i++) {
            int value = preorder[i];
            int prevIndex = -1;
            while (!indexMonoStack.isEmpty() && value > result[indexMonoStack.getFirst()].val) {
                // 出栈
                prevIndex = indexMonoStack.removeFirst();
            }
            // 构造二叉搜索树
            TreeNode treeNode = new TreeNode(value);
            result[i] = treeNode;
            if (prevIndex >= 0) {
                result[prevIndex].right = treeNode;
            } else if (!indexMonoStack.isEmpty()) {
                result[indexMonoStack.getFirst()].left = treeNode;
            }
            // 入栈
            indexMonoStack.push(i);
        }
        return result[0];
    }

    private static final class TreeNode {
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
